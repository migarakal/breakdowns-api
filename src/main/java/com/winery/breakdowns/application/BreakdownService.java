package com.winery.breakdowns.application;

import com.winery.breakdowns.api.Breakdown;
import com.winery.breakdowns.api.BreakdownResponse;
import com.winery.breakdowns.repository.wine.entity.ComponentDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BreakdownService {
  public BreakdownResponse build(String t, Stream<ComponentDto> cds) {

    switch (t) {
      case "year":
        return buildYearBreakdown(cds);
      case "region":
        return buildRegionBreakdown(cds);
      case "variety":
        return buildVarietyBreakdown(cds);
      case "year-variety":
        return buildYearVarietyBreakdown(cds);
    }

    throw new IllegalArgumentException("Received invalid type: " + t);
  }

  public BreakdownResponse buildYearBreakdown(Stream<ComponentDto> cds) {
    List<Breakdown> b = new ArrayList<>();
    cds
        .parallel()
        .collect(
            Collectors.groupingBy(
                componentDto -> componentDto.year,
                Collectors.summingDouble(componentDto -> componentDto.percentage)
            ))
        .forEach((year, percentage) -> {
          Breakdown breakdown = new Breakdown(String.valueOf(percentage), String.valueOf(year));
          b.add(breakdown);
        });
    return new BreakdownResponse("year", b);
  }

  public BreakdownResponse buildVarietyBreakdown(Stream<ComponentDto> cds) {
    List<Breakdown> b = new ArrayList<>();
    cds
        .parallel()
        .collect(
            Collectors.groupingBy(
                componentDto -> componentDto.variety,
                Collectors.summingDouble(componentDto -> componentDto.percentage)
            ))
        .forEach((variety, percentage) -> {
          Breakdown breakdown = new Breakdown(String.valueOf(percentage), String.valueOf(variety));
          b.add(breakdown);
        });
    return new BreakdownResponse("variety", b);
  }

  public BreakdownResponse buildRegionBreakdown(Stream<ComponentDto> cds) {
    List<Breakdown> b = new ArrayList<>();
    cds
        .parallel()
        .collect(
            Collectors.groupingBy(
                componentDto -> componentDto.region,
                Collectors.summingDouble(componentDto -> componentDto.percentage)
            ))
        .forEach((region, percentage) -> {
          Breakdown breakdown = new Breakdown(String.valueOf(percentage), String.valueOf(region));
          b.add(breakdown);
        });
    return new BreakdownResponse("region", b);
  }

  public BreakdownResponse buildYearVarietyBreakdown(Stream<ComponentDto> cds) {
    List<Breakdown> b = new ArrayList<>();
    cds
        .parallel()
        .collect(
            Collectors.groupingBy(
                componentDto -> componentDto.year + "-" + componentDto.variety,
                Collectors.summingDouble(componentDto -> componentDto.percentage)
            ))
        .forEach((yearVariety, percentage) -> {
          Breakdown breakdown = new Breakdown(String.valueOf(percentage), String.valueOf(yearVariety));
          b.add(breakdown);
        });
    return new BreakdownResponse("year-variety", b);
  }
}