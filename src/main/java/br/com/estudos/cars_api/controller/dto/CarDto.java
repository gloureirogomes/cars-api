package br.com.estudos.cars_api.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CarDto {

    @JsonProperty("model")
    private String model;

    @JsonProperty("brand")
    private String brand;

    @JsonProperty("color")
    private String color;

    @JsonProperty("year")
    private Integer year;

    @JsonProperty("automatic")
    private Boolean automatic;

}
