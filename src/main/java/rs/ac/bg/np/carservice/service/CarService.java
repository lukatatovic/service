package rs.ac.bg.np.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.np.carservice.domain.Car;
import rs.ac.bg.np.carservice.domain.Owner;
import rs.ac.bg.np.carservice.repository.CarRepository;
import rs.ac.bg.np.carservice.repository.OwnerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private OwnerRepository ownerRepository;
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car addNewCar(Car car) throws Exception{
        Optional<Car> optionalCar= carRepository.findCarByBrandAndModelAndHorsePower(car.getBrand(),car.getModel(),car.getHorsePower());
        if(optionalCar.isPresent()){
            throw new Exception("Automobil vec postoji");
        }
        carRepository.save(car);
        return car;
    }

    public Car addOwnerForCar(long carId, long ownerId)throws Exception {
        Optional<Car> optionalCar= carRepository.findById(carId);
        Optional<Owner> optionalOwner=ownerRepository.findById(ownerId);
        if(!optionalCar.isPresent()){
            throw new Exception("Automobil ne postoji");
        }
        if(!optionalOwner.isPresent()){
            throw new Exception("Vlasnik ne postoji");
        }
        Car car= optionalCar.get();
        Owner owner= optionalOwner.get();
        car.setOwner(owner);
        carRepository.save(car);
        owner.getCars().add(car);
        ownerRepository.save(owner);
        return car;
    }
}
