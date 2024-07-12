package br.com.estudos.cars_api.controller;

import br.com.estudos.cars_api.domain.Cars;
import br.com.estudos.cars_api.repository.CarsRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarsController {

    private CarsRepository carsRepository;

    @PostMapping("/car")
    public void createCar() {
        Cars car = new Cars();
        carsRepository.save(car);
    }

}
