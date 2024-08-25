package com.mosquito_forecast.mosquito_forecast.controller;

import com.mosquito_forecast.mosquito_forecast.dto.weatherDto;
import com.mosquito_forecast.mosquito_forecast.service.WeatherService;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(produces = "application/json;charset=UTF-8")
public class WeatherContoller {

    private WeatherService weatherService = new WeatherService();

    @GetMapping("/") // 기본 페이지
    public String openWeatherPage()
    {
        return "index";
    }

    @GetMapping("/weather")
    public String updateWeather() {
        return weatherService.
    }
}
