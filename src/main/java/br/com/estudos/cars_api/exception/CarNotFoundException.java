package br.com.estudos.cars_api.exception;

public class CarNotFoundException extends RuntimeException {

    public CarNotFoundException(String message){
        super(message);
    }

}
