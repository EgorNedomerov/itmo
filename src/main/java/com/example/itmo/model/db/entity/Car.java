package com.example.itmo.model.db.entity;

import com.example.itmo.model.enums.CarStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

@FieldDefaults (level = AccessLevel.PRIVATE)
@Entity
@Setter
@Getter
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String model;
    String brand;
    Integer weight;
    String color;
    Integer price;
    @Column(name = "is_new")
    Boolean isNew;
    @Column(name = "created_at")
    LocalDateTime createdAT;
    @Column(name = "updated_at")
    LocalDateTime updatedAT;
    CarStatus status;
    @ManyToOne
    @JsonBackReference(value = "driver_cars")
    User user;
}
