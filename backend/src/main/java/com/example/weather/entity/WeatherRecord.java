package com.example.weather.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "weather_record")
public class WeatherRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String city;

    private String temperature;

    private String weatherDesc;

    private LocalDateTime queryTime = LocalDateTime.now();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getTemperature() { return temperature; }
    public void setTemperature(String temperature) { this.temperature = temperature; }

    public String getWeatherDesc() { return weatherDesc; }
    public void setWeatherDesc(String weatherDesc) { this.weatherDesc = weatherDesc; }

    public LocalDateTime getQueryTime() { return queryTime; }
    public void setQueryTime(LocalDateTime queryTime) { this.queryTime = queryTime; }
}
