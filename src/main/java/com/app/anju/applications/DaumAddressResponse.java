package com.app.anju.applications;

import java.util.List;
import lombok.Data;

@Data
public class DaumAddressResponse {

  private List<Document> documents;

  @Data
  public static class Document {
    private double x;
    private double y;
  }
}