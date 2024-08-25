package com.mosquito_forecast.mosquito_forecast.service;

import com.mosquito_forecast.mosquito_forecast.domain.Region;
import com.mosquito_forecast.mosquito_forecast.domain.Weather;
import com.mosquito_forecast.mosquito_forecast.dto.weatherDto;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@NoArgsConstructor
@Service
public class WeatherService {

    private Weather weather;

    @Value("${weatherKey}")
    private String weatherKey;

    private String baseDate = "20240824";

    private String baseTime = "1200";

    private Region region;
    //private int nx = region.getNx();
    private int nx = 60;
    //private int ny = region.getNy();
    private int ny = 127;

    private String requestUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst" +
            "?serviceKey=" + weatherKey + "&numOfRows=10&pageNo=1&dataType=JSON&base_date=" +
            baseDate + "&base_time=" + baseTime + "&nx=" + nx + "&ny=" + ny;

    // private String apiUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst";

    RestTemplate restTemplate = new RestTemplate();

    public weatherDto updateWeather() {
        ResponseEntity<weatherDto> result = restTemplate.getForEntity(requestUrl, weatherDto.class);

        System.out.println(result.getStatusCode());
        System.out.println(result.getBody());

        return result.getBody();
    }
}
