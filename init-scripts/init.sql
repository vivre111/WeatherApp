USE weather_db;

CREATE TABLE IF NOT EXISTS weather_record (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    city VARCHAR(100) NOT NULL,
    temperature VARCHAR(20),
    weather_desc VARCHAR(200),
    query_time DATETIME DEFAULT CURRENT_TIMESTAMP
);
