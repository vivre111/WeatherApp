package com.example.weather.repository;

import com.example.weather.entity.WeatherRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherRecord, Long> {
    List<WeatherRecord> findTop10ByOrderByQueryTimeDesc();
}
