package com.winery.breakdowns.api;

public class Breakdown {
  public String percentage;
  public String key;

  public Breakdown(String p, String k) {
    this.percentage = p;
    this.key = k;
  }
}