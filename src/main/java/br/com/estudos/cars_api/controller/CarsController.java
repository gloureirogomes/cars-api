package br.com.estudos.cars_api.controller;

import br.com.estudos.cars_api.controller.dto.CarDto;
import br.com.estudos.cars_api.domain.Car;
import br.com.estudos.cars_api.mapper.CarMapper;
import br.com.estudos.cars_api.repository.CarsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CarsController {

    @Autowired
    private CarsRepository carsRepository;

    @PostMapping("/car")
    public ResponseEntity<CarDto> createCar(@RequestBody CarDto carDto) {
        Car car = CarMapper.toDomain(carDto);
        Car persistedCar = carsRepository.save(car);
        CarDto carToReturn = CarMapper.toDto(persistedCar);
        return ResponseEntity.status(HttpStatus.CREATED).body(carToReturn);
    }

    @DeleteMapping("/car/{carId}")
    public ResponseEntity<Void> deleteCar(@PathVariable UUID carId) {
        carsRepository.deleteById(carId);
        return ResponseEntity.noContent().build();
    }
}
