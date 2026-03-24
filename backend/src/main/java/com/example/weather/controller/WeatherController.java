package com.example.weather.controller;

import com.example.weather.entity.WeatherRecord;
import com.example.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/weather")
@CrossOrigin(origins = "*")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @PostMapping("/query")
    public WeatherRecord queryWeather(@RequestBody Map<String, String> request) {
        String city = request.get("city");
        return weatherService.queryWeather(city);
    }

    @GetMapping("/history")
    public List<WeatherRecord> getHistory() {
        return weatherService.getRecentQueries();
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}
