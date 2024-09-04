package com.mosquito_forecast.mosquito_forecast.controller;

import com.mosquito_forecast.mosquito_forecast.dto.regionForm;
import com.mosquito_forecast.mosquito_forecast.dto.weatherDto;
import com.mosquito_forecast.mosquito_forecast.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Weather API", description = "Get and Offer weather data")
@RestController
@RequestMapping(produces = "application/json;charset=UTF-8")
public class WeatherContoller {

    @Autowired
    private WeatherService weatherService;

    private weatherDto weatherdto;

    @Operation(summary = "기본 페이지")
    @GetMapping("/") // 기본 페이지
    public String openWeatherPage()
    {
        return "index";
    }


    @Operation(summary = "날씨 데이터 제공", description = "선택한 지역의 x,y 좌표를 받아 날씨 데이터 불러오기")
    @PostMapping("/weather/new")
    public weatherDto updateWeather(regionForm form) throws ParseException {
        // weatherService에서 weatherdto를 생성 후 반환
        return weatherService.updateWeather(form);
    }

}
