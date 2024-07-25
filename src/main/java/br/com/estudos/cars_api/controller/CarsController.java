package br.com.estudos.cars_api.controller;

import br.com.estudos.cars_api.controller.dto.CarDto;
import br.com.estudos.cars_api.exception.CarNotFoundException;
import br.com.estudos.cars_api.exception.DeleteCarException;
import br.com.estudos.cars_api.exception.FindCarException;
import br.com.estudos.cars_api.exception.SaveCarException;
import br.com.estudos.cars_api.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        try {
            carService.deleteCar(carId);
            return ResponseEntity.noContent().build();
        } catch (CarNotFoundException cnfe) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, cnfe.getMessage());
        } catch (FindCarException | DeleteCarException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao deletar no banco de dados");
        }
    }

    @GetMapping("/car/{carId}")
    public ResponseEntity<CarDto> findCar(@PathVariable UUID carId) {
        try {
            var carToReturn = carService.findCarById(carId);
            return ResponseEntity.ok((carToReturn));
        } catch (CarNotFoundException cnfe) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, cnfe.getMessage());
        } catch (FindCarException fce) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao buscar no banco de dados");
        }
    }
}


