package br.com.estudos.cars_api.service;

import br.com.estudos.cars_api.controller.dto.CarDto;
import br.com.estudos.cars_api.domain.Car;
import br.com.estudos.cars_api.exception.CarNotFoundException;
import br.com.estudos.cars_api.exception.DeleteCarException;
import br.com.estudos.cars_api.exception.FindCarException;
import br.com.estudos.cars_api.exception.SaveCarException;
import br.com.estudos.cars_api.mapper.CarMapper;
import br.com.estudos.cars_api.repository.CarsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarService {

    private final CarsRepository carsRepository;

    public CarDto saveCar(CarDto carDto) {
        try {
            Car car = CarMapper.toDomain(carDto);
            Car persistedCar = carsRepository.save(car);
            return CarMapper.toDto(persistedCar);
        } catch (DataAccessException dae) {
            log.error("Error to insert on database! Error: {}", dae.getMessage());
            throw new SaveCarException(dae.getMessage());
        }
    }

    public void deleteCar(UUID carId) {
        findCarById(carId);
        try {
            carsRepository.deleteById(carId);
        } catch (DataAccessException dae) {
            log.error("Error to delete on database! Error: {}", dae.getMessage());
            throw new DeleteCarException(dae.getMessage());
        }
    }

    public CarDto findCarById(UUID carId) {
        try {
            var persistedCar = carsRepository.findById(carId);
            return persistedCar.map(CarMapper::toDto).orElseThrow(() -> new CarNotFoundException("Registro não existe no banco de dados"));
        } catch (DataAccessException dae) {
            log.error("Error to find on database! Error: {}", dae.getMessage());
            throw new FindCarException(dae.getMessage());
        }
    }
}
