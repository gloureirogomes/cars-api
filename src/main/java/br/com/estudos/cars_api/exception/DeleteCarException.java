package br.com.estudos.cars_api.exception;

public class DeleteCarException extends RuntimeException {

    public DeleteCarException(String message){
        super(message);
    }

}
