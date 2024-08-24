package com.mosquito_forecast.mosquito_forecast.service;

import com.mosquito_forecast.mosquito_forecast.domain.Region;
import com.mosquito_forecast.mosquito_forecast.domain.Weather;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private Weather weather;

    private String weatherKey = "5lgjZJOE7BcXoqm6cw154W1alfFyYwOyw9IDRHQALubgS8fG7PuFJWYlX3d52C8nEm54T2N%2BoQKt%2FouqqXAVvw%3D%3D";

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



    public void updateWeather() {

    }
}
