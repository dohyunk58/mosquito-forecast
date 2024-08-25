package com.mosquito_forecast.mosquito_forecast.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Schema(description = "Weather Data DTO")
@Data
@Getter
@Setter
public class weatherDto {
    @Schema(description = "날씨 데이터 ID")
    private int weatherId;
    @Schema(description = "날씨 데이터 기준 날짜")
    // private LocalDate weatherDate;
    private String baseDate;
    @Schema(description = "날씨 데이터 기준 시간")
    // private LocalDate weatherTime;
    private String baseTime;
    /* 초단기 실황 */
    @Schema(description = "PTY 강수 형태 / 없음(0), 비(1), 비/눈(2), 눈(3), 소나기(4)")
    private String precipitationType;
    @Schema(description = "REH 습도(%)")
    private String humidity;
    @Schema(description = "RN1 1시간 강수량 / -, null, 0값은 ‘강수없음’")
    private String hourlyPrecipitation;
    @Schema(description = "T1H 기온")
    private String temperature;
}
