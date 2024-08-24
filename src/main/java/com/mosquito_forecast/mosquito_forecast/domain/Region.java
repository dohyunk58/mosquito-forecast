package com.mosquito_forecast.mosquito_forecast.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Region {

    @Id @Column(name="region_id")
    private Long id; // 지역 id

    @Column(name="region_parent")
    private String parentRegion = "서울특별시";

    @Column(name="region_child")
    private String childRegion = "중구";

    private int nx = 60; // x좌표
    private int ny = 127; // y좌표

    @Embedded
    private Weather weather; // 날씨 정보 객체

    // 생성자
    public Region(Long id, String parentRegion, String childRegion, int nx, int ny) {
        this.id = id;
        this.parentRegion = parentRegion;
        this.childRegion = childRegion;
        this.nx = nx;
        this.ny = ny;
    }

    @Override
    public String toString() {
        return parentRegion + " " + childRegion;
    }

}
