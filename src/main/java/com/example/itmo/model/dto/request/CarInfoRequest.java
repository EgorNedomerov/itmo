package com.example.itmo.model.dto.request;

import com.example.itmo.model.enums.CarStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Setter
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults (level = AccessLevel.PRIVATE)
public class CarInfoRequest {
    String model;
    String brand;
    Integer weight;
    String color;
    Integer price;
    Boolean isNew;
    LocalDateTime createdAT;
    LocalDateTime updatedAT;
    CarStatus status;
}
