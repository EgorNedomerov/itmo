package com.example.itmo.controllers;

import com.example.itmo.model.dto.request.CarInfoRequest;
import com.example.itmo.model.dto.response.CarInfoResponse;
import com.example.itmo.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @PostMapping
    public CarInfoResponse createCar(@RequestBody CarInfoRequest request) {
        return carService.createCar(request);
    }

    @GetMapping("/{id}")
    public CarInfoResponse getCar(@PathVariable Long id) {
        return carService.getCar(id);
    }

    @PutMapping("/{id}")
    public CarInfoResponse updateCar(@PathVariable Long id, @RequestBody CarInfoRequest request) {
        return carService.updateCar(request, id);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }

    @GetMapping("/all")
    public List<CarInfoResponse> getAllUsers() {
        return carService.getAllCars();}

    @PostMapping("/linkCarAndDriver/{userId}/{carId}")
    public CarInfoResponse linkCarAndDriver(@PathVariable Long userId, @PathVariable Long carId) {
        return carService.linkCarAndDriver(userId, carId);}

//        @GetMapping("/all")
//        public Page<CarInfoResponse> getAllCar (@RequestParam (defaultValue = "1") Integer page,
//                @RequestParam(defaultValue = "10") Integer perPage,
//                @RequestParam(defaultValue = "firstName") String sort,
//                @RequestParam(defaultValue = "ASC") Sort.Direction order,
//                @RequestParam(defaultValue = "ASC") String filter) {
//            return carService.getAllCar(page, perPage, sort, order, filter);
        }



