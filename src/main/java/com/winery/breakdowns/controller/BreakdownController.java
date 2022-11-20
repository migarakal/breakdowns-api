package com.winery.breakdowns.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.winery.breakdowns.api.BreakdownResponse;
import com.winery.breakdowns.application.BreakdownService;
import com.winery.breakdowns.repository.wine.entity.ComponentDto;
import com.winery.breakdowns.repository.wine.entity.WineDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;

@RestController
@RequestMapping("/api/breakdown")
public class BreakdownController {

  private final BreakdownService breakdownService = new BreakdownService();

  @RequestMapping(value = "/year/{lotCode}", method = RequestMethod.GET)
  public ResponseEntity<BreakdownResponse> getBreakdownByYear(@PathVariable("lotCode") String lc) throws IOException {
    ClassLoader cl = getClass().getClassLoader();
    URL r1 = cl.getResource(lc + ".json");
    WineDto w = new ObjectMapper().readValue(r1, WineDto.class);

    if (w == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found");
    }

    if (!Arrays.asList("year", "variety", "region", "year-variety").contains("year")) {
      throw new IllegalArgumentException("Received wrong type: " + "year");
    }

    BreakdownResponse r = breakdownService.build("year", w.components.stream().sorted(Comparator.comparing((ComponentDto c) -> c.percentage).reversed()));

    return new ResponseEntity<>(r, HttpStatus.OK);
  }

  @RequestMapping(value = "/variety/{lotCode}", method = RequestMethod.GET)
  public ResponseEntity<BreakdownResponse> getBreakdownByVariety(@PathVariable("lotCode") String lc) throws IOException {
    ClassLoader cl = getClass().getClassLoader();
    URL r1 = cl.getResource(lc + ".json");
    WineDto w = new ObjectMapper().readValue(r1, WineDto.class);

    if (w == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found");
    }

    if (!Arrays.asList("year", "variety", "region", "year-variety").contains("variety")) {
      throw new IllegalArgumentException("Received wrong type: " + "variety");
    }

    BreakdownResponse r = breakdownService.build("variety", w.components.stream().sorted(Comparator.comparing((ComponentDto c) -> c.percentage).reversed()));

    return new ResponseEntity<>(r, HttpStatus.OK);
  }

  @RequestMapping(value = "/region/{lotCode}", method = RequestMethod.GET)
  public ResponseEntity<BreakdownResponse> getBreakdownByRegion(@PathVariable("lotCode") String lc) throws IOException {
    ClassLoader cl = getClass().getClassLoader();
    URL r1 = cl.getResource(lc + ".json");
    WineDto w = new ObjectMapper().readValue(r1, WineDto.class);

    if (w == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found");
    }

    if (!Arrays.asList("year", "variety", "region", "year-variety").contains("region")) {
      throw new IllegalArgumentException("Received wrong type: " + "region");
    }

    BreakdownResponse r = breakdownService.build("region", w.components.stream().sorted(Comparator.comparing((ComponentDto c) -> c.percentage).reversed()));

    return new ResponseEntity<>(r, HttpStatus.OK);
  }

  @RequestMapping(value = "/year-variety/{lotCode}", method = RequestMethod.GET)
  public ResponseEntity<BreakdownResponse> getBreakdownByYearAndVariety(@PathVariable("lotCode") String lc) throws IOException {
    ClassLoader cl = getClass().getClassLoader();
    URL r1 = cl.getResource(lc + ".json");
    WineDto w = new ObjectMapper().readValue(r1, WineDto.class);

    if (w == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found");
    }

    if (!Arrays.asList("year", "variety", "region", "year-variety").contains("year-variety")) {
      throw new IllegalArgumentException("Received wrong type: " + "year-variety");
    }

    BreakdownResponse r = breakdownService.build("year-variety", w.components.stream().sorted(Comparator.comparing((ComponentDto c) -> c.percentage).reversed()));

    return new ResponseEntity<>(r, HttpStatus.OK);
  }

}