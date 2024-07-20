package br.com.estudos.cars_api.controller;

import br.com.estudos.cars_api.controller.dto.CarDto;
import br.com.estudos.cars_api.exception.CarNotFoundException;
import br.com.estudos.cars_api.exception.SaveCarException;
import br.com.estudos.cars_api.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CarsController {

    private final CarService carService;

    @PostMapping("/car")
    public ResponseEntity<CarDto> createCar(@RequestBody CarDto carDto) {
        try {
            var carToReturn = carService.saveCar(carDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(carToReturn);
        } catch (SaveCarException sce) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao inserir no banco de dados");
        }
    }

    @DeleteMapping("/car/{carId}")
    public ResponseEntity<Void> deleteCar(@PathVariable UUID carId) {
        carService.deleteCar(carId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/car/{carId}")
    public ResponseEntity<CarDto> findCar(@PathVariable UUID carId) {
        try {
            var carToReturn = carService.findCarById(carId);
            return ResponseEntity.ok((carToReturn));
        } catch (CarNotFoundException cnfe) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro não existe no banco");
        }
    }
}


