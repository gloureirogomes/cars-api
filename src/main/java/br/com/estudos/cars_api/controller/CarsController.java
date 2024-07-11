package br.com.estudos.cars_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarsController {

    @GetMapping("/cars")
    public void getCars() {
        System.out.println("TESTE");
    }

}
