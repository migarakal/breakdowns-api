package com.winery.breakdowns.api;

import java.util.List;

public class BreakdownResponse {
  public String breakdownType;
  public List<Breakdown> breakdown;

  public BreakdownResponse(String bt, List<Breakdown> b) {
    this.breakdownType = bt;
    this.breakdown = b;
  }
}