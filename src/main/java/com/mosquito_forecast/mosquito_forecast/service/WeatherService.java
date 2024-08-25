package com.mosquito_forecast.mosquito_forecast.service;

import com.mosquito_forecast.mosquito_forecast.domain.Region;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@NoArgsConstructor
@Service
public class WeatherService {

    @Value("${weatherKey}") private String weatherKey;

    private String baseDate = "20240825";
    private String baseTime = "2300";
    private int nx = 60;
    private int ny = 127;

    private String requestUrl;

    private final String apiUrl = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst";

    // uriString 값을 저장할 인스턴스 변수
    private String uriString;

    public String weatherUrl() {
        return "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst?" +
                "serviceKey=" + weatherKey + "&numOfRows=10&pageNo=1&dataType=JSON&base_date=" +
                baseDate + "&base_time=" + baseTime + "&nx=" + nx + "&ny=" + ny;
    }

    public void weatherUriBuilder() {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                //.queryParam("serviceKey", requestApiConfig.getWeatherApiEncodingKey())
                .queryParam("pageNo", 1)
                .queryParam("numOfRows", 1000)
                .queryParam("dataType", "JSON")
                .queryParam("base_date", baseDate)
                .queryParam("base_time", baseTime)
                .queryParam("nx", nx)
                .queryParam("ny", ny);

        this.uriString = uriBuilder.toUriString() + "&serviceKey=" + weatherKey;
        // 이미 인코딩된 serviceKey 추가 (UriComponentsBuilder 에서 인코딩 가능성이 있음)
    }

    @Autowired
    RestTemplate restTemplate;

    public void updateWeather() {
        // weatherUriBuilder();
        // ResponseEntity<String> result = restTemplate.getForEntity(this.uriString, String.class);
        requestUrl = weatherUrl();
        ResponseEntity<String> result = restTemplate.getForEntity(requestUrl, String.class);
        System.out.println(result.getStatusCode());
        System.out.println(result.getBody());
    }
}
