package rs.ac.bg.np.carservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.np.carservice.domain.Car;
import rs.ac.bg.np.carservice.service.CarService;

import java.util.List;

@RestController
@RequestMapping("/api/car")
public class CarController {
    @Autowired
    private CarService carService;
    @GetMapping
    public List<Car> getAllCars()
    {
       return carService.getAllCars();
    }
    @PostMapping
    public Car addNewCar(@RequestBody Car car) throws Exception{
        return carService.addNewCar(car);
    }
    @PostMapping("/{carId}/addOwner/{ownerId}")
    public Car addOwnerForCar(@PathVariable long carId,@PathVariable long ownerId) throws Exception{
        return carService.addOwnerForCar(carId,ownerId);
    }
}
