package com.winery.breakdowns.repository.wine.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ComponentDto {
  public double percentage;
  public int year;
  public String variety;
  public String region;

  @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
  public ComponentDto(
      @JsonProperty("percentage") double p,
      @JsonProperty("year") int y,
      @JsonProperty("variety") String v,
      @JsonProperty("region") String r
  ) {
    this.percentage = p;
    this.year = y;
    this.variety = v;
    this.region = r;
  }
}