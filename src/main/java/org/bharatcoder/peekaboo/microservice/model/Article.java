package org.bharatcoder.peekaboo.microservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Article {
  private String id;
  private String content;
  private String author;
  private String datePublished;
  private int wordCount;

}
