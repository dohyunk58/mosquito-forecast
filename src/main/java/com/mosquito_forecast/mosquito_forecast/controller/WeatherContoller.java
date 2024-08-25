package com.mosquito_forecast.mosquito_forecast.controller;

import com.mosquito_forecast.mosquito_forecast.dto.weatherDto;
import com.mosquito_forecast.mosquito_forecast.service.WeatherService;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = "application/json;charset=UTF-8")
public class WeatherContoller {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/") // 기본 페이지
    public String openWeatherPage()
    {
        return "index";
    }

    @GetMapping("/weather")
    public void updateWeather() {
        weatherService.updateWeather();
    }

}
