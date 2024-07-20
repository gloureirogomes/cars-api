package br.com.estudos.cars_api.service;

import br.com.estudos.cars_api.controller.dto.CarDto;
import br.com.estudos.cars_api.domain.Car;
import br.com.estudos.cars_api.exception.SaveCarException;
import br.com.estudos.cars_api.repository.CarsRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @InjectMocks
    private CarService carService;

    @Mock
    private CarsRepository carRepository;

    @Nested
    class SaveCarTest {

        @Test
        @DisplayName("Should save on database")
        public void shouldSaveOnDatabase() {
            Car car = Car.builder()
                    .carId(UUID.randomUUID())
                    .model("Palio")
                    .brand("Fiat")
                    .color("Black")
                    .year(2005)
                    .automatic(false)
                    .createdAt(LocalDateTime.now().minusDays(3))
                    .updatedAt(null)
                    .build();

            CarDto carDto = CarDto.builder()
                    .model("Palio")
                    .brand("Fiat")
                    .color("Black")
                    .year(2005)
                    .automatic(false)
                    .build();

            given(carRepository.save(any(Car.class))).willReturn(car);

            var result = carService.saveCar(carDto);

            assertEquals(car.getModel(), result.getModel());
            assertEquals(car.getBrand(), result.getBrand());
            assertEquals(car.getColor(), result.getColor());
            assertEquals(car.getYear(), result.getYear());
            assertEquals(car.getAutomatic(), result.getAutomatic());
        }

        @Test
        @DisplayName("Should throw SaveCarException when error occurs to save on database")
        public void shouldThrowSaveCarExceptionWhenErrorOccursToSaveOnDatabase() {
            CarDto carDto = CarDto.builder()
                    .model("Palio")
                    .brand("Fiat")
                    .color("Black")
                    .year(2005)
                    .automatic(false)
                    .build();

            given(carRepository.save(any(Car.class))).willThrow(DataIntegrityViolationException.class);

            assertThatExceptionOfType(SaveCarException.class).isThrownBy(() -> carService.saveCar(carDto));
        }
    }

    @Nested
    class FindCarByIdTest {

        @Test
        @DisplayName("Should find car on database when given valid id")
        public void shouldFindCarOnDatabaseWhenGivenValidId() {
            UUID validCarId = UUID.randomUUID();

            Car car = Car.builder()
                    .carId(validCarId)
                    .model("Palio")
                    .brand("Fiat")
                    .color("Black")
                    .year(2005)
                    .automatic(false)
                    .createdAt(LocalDateTime.now().minusDays(3))
                    .updatedAt(null)
                    .build();

            given(carRepository.findById(any(UUID.class))).willReturn(Optional.of(car));

            var result = carService.findCarById(validCarId);

            assertEquals(car.getModel(), result.getModel());
            assertEquals(car.getBrand(), result.getBrand());
            assertEquals(car.getColor(), result.getColor());
            assertEquals(car.getYear(), result.getYear());
            assertEquals(car.getAutomatic(), result.getAutomatic());
        }

        @Test
        @DisplayName("Should return null when not found car on database with given car id")
        public void shouldReturnNullWhenNotFoundCarOnDatabaseWithGivenCarId() {
            UUID carId = UUID.randomUUID();

            given(carRepository.findById(any(UUID.class))).willReturn(Optional.empty());

            var result = carService.findCarById(carId);

            assertNull(result);
        }
    }

    @Nested
    class DeleteCarTest {

        @Test
        @DisplayName("Should delete car on database when given valid id")
        public void shouldDeleteCarOnDatabaseWhenGivenValidId() {
            UUID validCarId = UUID.randomUUID();

            doNothing().when(carRepository).deleteById(any(UUID.class));

            assertThatNoException().isThrownBy(() -> carService.deleteCar(validCarId));
        }
    }
}