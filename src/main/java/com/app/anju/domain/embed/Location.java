package com.app.anju.domain.embed;

import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Access(AccessType.FIELD)
@AllArgsConstructor
@Builder
public class Location {

  private List<Double> coordinates;

  private String type;

}
