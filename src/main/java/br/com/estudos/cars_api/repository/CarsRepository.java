package br.com.estudos.cars_api.repository;

import br.com.estudos.cars_api.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CarsRepository extends JpaRepository<Car, UUID> {
    Car save(Car car);
    void deleteById(UUID carId);
    Optional<Car> findById(UUID carId);
}

