package com.example.itmo.service.impl;

import com.example.itmo.model.db.entity.Car;
import com.example.itmo.model.db.entity.User;
import com.example.itmo.model.db.repository.CarRepo;
import com.example.itmo.model.dto.request.CarInfoRequest;
import com.example.itmo.model.dto.response.CarInfoResponse;
import com.example.itmo.model.dto.response.UserInfoResponse;
import com.example.itmo.model.enums.CarStatus;
import com.example.itmo.service.CarService;
import com.example.itmo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CarServiceImpl implements CarService {
    private final CarRepo carRepo;
    private final ObjectMapper mapper;
    private final UserService userService;

    @Override
    public CarInfoResponse createCar(CarInfoRequest request) {
        Car car = mapper.convertValue(request, Car.class);
        car.setStatus(CarStatus.CREATED);
        car = carRepo.save(car);
        return mapper.convertValue(car, CarInfoResponse.class);
    }

    @Override
    public CarInfoResponse getCar(Long id) {
        return mapper.convertValue(getCarDb(id), CarInfoResponse.class);
    }

    private Car getCarDb(Long id) {
        return carRepo.findById(id).orElse(new Car());
    }

    @Override
    public CarInfoResponse updateCar(CarInfoRequest request, Long id) {
        Car car = getCarDb(id);
        if (car.getId() != null) {
            car.setModel(request.getModel() == null ? car.getModel() : request.getModel());
            car.setBrand(request.getBrand() == null ? car.getBrand() : request.getBrand());
            car.setColor(request.getColor() == null ? car.getColor() : request.getColor());
            car.setWeight(request.getWeight() == null ? car.getWeight() : request.getWeight());
            car.setPrice(request.getPrice() == null ? car.getPrice() : request.getPrice());
            car.setUpdatedAT(LocalDateTime.now());
            car = carRepo.save(car);

        } else {
            log.error("Car not found");
        }
        return mapper.convertValue(car, CarInfoResponse.class);
    }

    @Override
    public void deleteCar(Long id) {
        Car car = getCarDb(id);
        if (car.getId() != null) {
            car.setStatus(CarStatus.DELETED);
            car.setUpdatedAT(LocalDateTime.now());
            carRepo.save(car);
        } else {
            log.error("Car not found");

        }

    }

    @Override
    public Page<CarInfoResponse> getAllCar(Integer page, Integer perPage, String sort, Sort.Direction order, String filter) {
        return (Page<CarInfoResponse>) carRepo.findAll(). stream()
                .map (car-> {
                    CarInfoResponse carInfoResponse = mapper.convertValue(car, CarInfoResponse.class);
                    UserInfoResponse user = mapper.convertValue(car.getUser(), UserInfoResponse.class);
                    carInfoResponse.setUser(user);
              return carInfoResponse;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CarInfoResponse linkCarAndDriver(Long userId, Long carId) {
        Car car = getCarDb(carId);
        User user = userService.getUserDb(userId);
        user. getCars().add(car);
        userService.updateCarList(user);
        car.setUser(user);
        carRepo.save(car);
     UserInfoResponse userInfoResponse =   mapper.convertValue(user, UserInfoResponse.class);
  CarInfoResponse carInfoResponse =  mapper.convertValue(car, CarInfoResponse.class);
    carInfoResponse.setUser(userInfoResponse);
     return null;
    }

    @Override
    public List<CarInfoResponse> getAllCars() {
        return null;
    }
}
