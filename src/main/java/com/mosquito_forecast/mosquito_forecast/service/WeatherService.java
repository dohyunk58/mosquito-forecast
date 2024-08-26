package com.mosquito_forecast.mosquito_forecast.service;

import com.mosquito_forecast.mosquito_forecast.domain.Region;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@NoArgsConstructor
@Service
public class WeatherService {

    @Value("${weatherKey}") private String weatherKey;
    @Value("${weatherKeyDecode}") private String weatherKeyDecode;

    private String baseDate = "20240826";
    private String baseTime = "2300";
    private int nx = 60;
    private int ny = 127;

    private final String apiUrl = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst";

    public URI buildWeatherUri() throws URISyntaxException {
        String requestUrl = apiUrl +
                "?serviceKey=" + weatherKey +
                "&pageNo=1" +
                "&numOfRows=1000" +
                "&dataType=JSON" +
                "&base_date=" + baseDate +
                "&base_time=" + baseTime +
                "&nx=" + nx +
                "&ny=" + ny;

        return new URI(requestUrl);  // Create URI object
    }

    @Autowired
    RestTemplate restTemplate;

    public void updateWeather() {
        try{
            URI uri = buildWeatherUri();
            ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
            System.out.println(result.getStatusCode());
            System.out.println(result.getBody());
        } catch (URISyntaxException e) {
            e.printStackTrace();  // Handle possible URI syntax errors
        }
    }
}
