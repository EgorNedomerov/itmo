package com.example.itmo.service;

import com.example.itmo.model.dto.request.CarInfoRequest;
import com.example.itmo.model.dto.response.CarInfoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CarService {
    CarInfoResponse createCar(CarInfoRequest request);

    CarInfoResponse getCar(Long id);

    CarInfoResponse updateCar(CarInfoRequest request, Long id);

    void deleteCar(Long id);

    Page<CarInfoResponse> getAllCar(Integer page, Integer perPage, String sort, Sort.Direction order, String filter);

    CarInfoResponse linkCarAndDriver(Long userId, Long carId);

    List<CarInfoResponse> getAllCars();
}
