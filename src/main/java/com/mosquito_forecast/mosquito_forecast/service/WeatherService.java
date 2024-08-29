package com.mosquito_forecast.mosquito_forecast.service;

import com.mosquito_forecast.mosquito_forecast.dto.weatherDto;
import lombok.NoArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Service
public class WeatherService {

    @Value("${weatherKey}") private String weatherKey;
    @Value("${weatherKeyDecode}") private String weatherKeyDecode;

    private String baseDate = "20240829";
    private String baseTime = "2000";
    private int nx = 60;
    private int ny = 127;

    private final String apiUrl = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst";
    private String JsonString;

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

    public void updateWeather() throws ParseException{
        try{
            URI uri = buildWeatherUri();
            ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
            //System.out.println(result.getStatusCode());
            //System.out.println(result.getBody());
            JsonString = result.getBody();
            JsonParser();
        } catch (URISyntaxException e) {
            e.printStackTrace();  // Handle possible URI syntax errors
        }
    }

    public void JsonParser() throws ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(JsonString);
        // 1. 최상단 response
        JSONObject jsonResponse = (JSONObject) jsonObject.get("response");
        // 2. 하단 body
        JSONObject jsonBody = (JSONObject) jsonResponse.get("body");
        // 3. 하단 items
        JSONObject jsonItems = (JSONObject) jsonBody.get("items");
        // 4. 하단 item
        JSONArray jsonItemList = (JSONArray) jsonItems.get("item");

        List<weatherDto> result = new ArrayList<>();

        for (Object o : jsonItemList) {
            JSONObject item = (JSONObject) o;
            result.add(makeLocationDto(item));
        }

        System.out.println(result.get(0));
        System.out.println(result.get(0).getBaseDate());
    }

    // 콘텐츠 정보 JSON을 DTO로 변환
    private weatherDto makeLocationDto(JSONObject item) {
        weatherDto dto = weatherDto.builder().
                baseDate((String) item.get("baseDate")).
                baseTime((String) item.get("baseTime")).
                build();
        return dto;
    }
}
