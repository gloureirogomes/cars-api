package br.com.estudos.cars_api.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @Column(name = "car_id")
    private UUID carId;

    @Column(name = "model")
    private String model;

    @Column(name = "brand")
    private String brand;

    @Column(name = "color")
    private String color;

    @Column(name = "year")
    private Integer year;

    @Column(name = "automatic")
    private Boolean automatic;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Car() {
    }

    public Car(UUID carId, String model, String brand, String color, Integer year, Boolean automatic, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.carId = carId;
        this.model = model;
        this.brand = brand;
        this.color = color;
        this.year = year;
        this.automatic = automatic;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
