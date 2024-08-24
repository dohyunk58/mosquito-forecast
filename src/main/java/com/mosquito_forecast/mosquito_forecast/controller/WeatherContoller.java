package com.mosquito_forecast.mosquito_forecast.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(produces = "application/json;charset=UTF-8")
public class WeatherContoller {

    @GetMapping("/") // 기본 페이지
    public String openWeatherPage()
    {
        return "index";
    }
}
