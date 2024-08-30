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

    private String baseDate = "20240830";
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

        // 하나의 DTO 객체만 생성
        weatherDto dto = new weatherDto();

        // jsonItemList의 각 item에 대해 필드 설정
        for (Object o : jsonItemList) {
            JSONObject item = (JSONObject) o;
            updateWeatherDto(dto, item);
        }

        // DTO 객체 출력 또는 다른 작업 수행
        System.out.println(dto);
    }

    private void updateWeatherDto(weatherDto dto, JSONObject item) {
        String category = (String) item.get("category");
        String obsrValue = (String) item.get("obsrValue");

        // 기본 정보 설정 (첫번째 item에서만 설정하도록 조건 추가 가능)
        if (dto.getBaseDate() == null) {
            dto.setBaseDate((String) item.get("baseDate"));
            dto.setBaseTime((String) item.get("baseTime"));
        }

        // category에 따라 값을 설정
        switch (category) {
            case "PTY":
                dto.setPrecipitationType(obsrValue);
                break;
            case "REH":
                dto.setHumidity(obsrValue);
                break;
            case "RN1":
                dto.setHourlyPrecipitation(obsrValue);
                break;
            case "T1H":
                dto.setTemperature(obsrValue);
                break;
            // 다른 category 값들에 대한 처리가 필요하다면 여기 추가
            default:
                // 처리되지 않은 카테고리 경우
                break;
        }
    }


}
