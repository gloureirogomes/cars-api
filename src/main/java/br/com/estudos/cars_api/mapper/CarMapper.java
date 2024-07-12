package br.com.estudos.cars_api.mapper;

import br.com.estudos.cars_api.controller.dto.CarDto;
import br.com.estudos.cars_api.domain.Car;

import java.time.LocalDateTime;
import java.util.UUID;

public final class CarMapper {

    private CarMapper() {
    }

    public static Car toDomain(CarDto carDto) {
        return Car.builder()
                .carId(UUID.randomUUID())
                .model(carDto.getModel())
                .brand(carDto.getBrand())
                .color(carDto.getColor())
                .year(carDto.getYear())
                .automatic(carDto.getAutomatic())
                .createdAt(LocalDateTime.now())
                .updatedAt(null)
                .build();
    }

    public static CarDto toDto(Car car) {
        return CarDto.builder()
                .model(car.getModel())
                .brand(car.getBrand())
                .color(car.getColor())
                .year(car.getYear())
                .automatic(car.getAutomatic())
                .build();
    }

}
