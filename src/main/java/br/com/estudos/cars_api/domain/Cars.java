package br.com.estudos.cars_api.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cars {
    private String model;
    private String brand;
    private String color;
    private Integer year;
    private Boolean automatic;
}
