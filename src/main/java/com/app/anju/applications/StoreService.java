package com.app.anju.applications;

import com.app.anju.applications.DaumAddressResponse.Document;
import com.app.anju.domain.FilterDto;
import com.app.anju.domain.Store;
import com.app.anju.domain.StoreCreateDto;
import com.app.anju.domain.StoreKeyValueDto;
import com.app.anju.domain.StoreMenu;
import com.app.anju.domain.StoreMenuRepository;
import com.app.anju.domain.StoreResponseDto;
import com.app.anju.domain.querydsl.QueryFoodRepository;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.geo.Box;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class StoreService {

  private final MongoTemplate mongoTemplate;
  private final StoreMenuRepository storeMenuRepository;
  private final QueryFoodRepository queryFoodRepository;
  private final RestTemplate restTemplate = new RestTemplate();

  @Value("${kakao.apiKey}")
  private String apiKey;

  private final static String apiUrl = "https://dapi.kakao.com/v2/local/search/address.json";

  public void insertStore(StoreCreateDto storeCreateDto) throws MalformedURLException, UnsupportedEncodingException {

    storeCreateDto.addLocation(getStoreCoordinates(storeCreateDto.getAddress()));

    System.out.println("storeCreateDto = " + storeCreateDto);
    Store store = mongoTemplate.insert(storeCreateDto.toStore());
  }

  public List<StoreResponseDto> getStores(FilterDto filterDto) {

    List<Store> stores;

    if (filterDto.getGeoBoundary() == null) {

      stores = mongoTemplate.findAll(Store.class);
    } else {

      Point leftBottom = new Point(filterDto.getGeoBoundary().get(0), filterDto.getGeoBoundary().get(1));
      Point rightTop = new Point(filterDto.getGeoBoundary().get(2), filterDto.getGeoBoundary().get(3));
      Box box = new Box(leftBottom, rightTop);

      Query condition = new Query(Criteria.where("location").within(box));

      if (filterDto.getPoint() != null) {
        condition.addCriteria(Criteria.where("point").gte(filterDto.getPoint()));
      }

      if (filterDto.getVisited() != null) {
        condition.addCriteria(Criteria.where("visited").is(filterDto.getVisited()));
      }

      stores = mongoTemplate.find(condition, Store.class);
    }

    List<String> storeIds = stores.stream().map(Store::get_id).collect(Collectors.toList());
    List<StoreMenu> filteredStoreMenu = queryFoodRepository.getFilteredFoodDetail(filterDto, storeIds);

    if (filteredStoreMenu.isEmpty()) {

      return Collections.emptyList();
    }

    HashMap<String, ArrayList<StoreMenu>> mappedStoreIdAndFoodDetails = filteredStoreMenu.stream()
        .collect(
            Collectors.groupingBy(
                StoreMenu::getStoreId,
                HashMap::new,
                Collectors.toCollection(ArrayList::new)
            )
        );

    return stores.stream()
        .filter(store ->
            mappedStoreIdAndFoodDetails.get(store.get_id()) != null
        )
        .map(store ->
            new StoreResponseDto(
                store,
                mappedStoreIdAndFoodDetails.get(store.get_id())
            )
        )
        .collect(Collectors.toList());
  }

  public List<StoreKeyValueDto> getStoreValue() {

    return mongoTemplate.findAll(Store.class).stream().map(StoreKeyValueDto::new).collect(Collectors.toList());
  }

  private Document getStoreCoordinates(String roadAddr) throws UnsupportedEncodingException, MalformedURLException {

    byte[] bytes = roadAddr.getBytes(StandardCharsets.UTF_8);

    String utf8EncodedString = new String(bytes, StandardCharsets.UTF_8);

    String encode = URLEncoder.encode(roadAddr, "UTF-8");

    URI uri = UriComponentsBuilder.fromHttpUrl(apiUrl)
        .queryParam("query", utf8EncodedString)
        .encode()
        .build()
        .toUri();

    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "KakaoAK " + apiKey);
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    HttpEntity<Void> httpEntity = new HttpEntity<>(headers);
    ResponseEntity<DaumAddressResponse> response = restTemplate
        .exchange(
            uri,
            HttpMethod.GET,
            httpEntity,
            DaumAddressResponse.class
        );

    if (response.getBody() == null || response.getBody().getDocuments().isEmpty()) {

      return new Document();
    }

    return response.getBody().getDocuments().get(0);
  }
}