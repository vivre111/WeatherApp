package com.example.weather.service;

import com.example.weather.entity.WeatherRecord;
import com.example.weather.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class WeatherService {

    @Autowired
    private WeatherRepository weatherRepository;

    private final WebClient webClient = WebClient.builder()
            .baseUrl("https://wttr.in")
            .build();

    public WeatherRecord queryWeather(String city) {
        try {
            String response = webClient.get()
                    .uri("/" + city + "?format=%C|%t")
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            String[] parts = response.split("\\|");
            String weatherDesc = parts[0].trim();
            String temperature = parts.length > 1 ? parts[1].trim() : "N/A";

            WeatherRecord record = new WeatherRecord();
            record.setCity(city);
            record.setTemperature(temperature);
            record.setWeatherDesc(weatherDesc);

            return weatherRepository.save(record);

        } catch (Exception e) {
            WeatherRecord record = new WeatherRecord();
            record.setCity(city);
            record.setTemperature("Error");
            record.setWeatherDesc("Failed: " + e.getMessage());
            return weatherRepository.save(record);
        }
    }

    public List<WeatherRecord> getRecentQueries() {
        return weatherRepository.findTop10ByOrderByQueryTimeDesc();
    }
}
