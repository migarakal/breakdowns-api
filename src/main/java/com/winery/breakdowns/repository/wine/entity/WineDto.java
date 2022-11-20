package com.winery.breakdowns.repository.wine.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WineDto {
  public String lotCode;
  public double volume;
  public String description;
  public String tankCode;
  public String productState;
  public String ownerName;
  public List<ComponentDto> components;

  @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
  public WineDto(
      @JsonProperty("lotCode") String lc,
      @JsonProperty("volume") double v,
      @JsonProperty("description") String d,
      @JsonProperty("tankCode") String tc,
      @JsonProperty("productState") String ps,
      @JsonProperty("ownerName") String on,
      @JsonProperty("components") List<ComponentDto> c
  ) {
    this.lotCode = lc;
    this.volume = v;
    this.description = d;
    this.tankCode = tc;
    this.productState = ps;
    this.ownerName = on;
    this.components = c;
  }
}