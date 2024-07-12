package br.com.estudos.cars_api.repository;

import br.com.estudos.cars_api.domain.Cars;
import org.springframework.stereotype.Repository;

@Repository
public interface CarsRepository {

    void save(Cars car);

}
