package rs.ac.bg.np.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.np.carservice.domain.Car;
import rs.ac.bg.np.carservice.domain.Owner;
import rs.ac.bg.np.carservice.repository.CarRepository;
import rs.ac.bg.np.carservice.repository.OwnerRepository;

import java.util.List;
import java.util.Optional;

/**
 * Sadrzi poslovnu logiku rada sa automobilom.
 * Klasa sluzi da manipulise i upravlja modelom i podacima vezanim sa automobilom.
 *
 * Omogucava vracanje svih automobila, pravljenje novog automobila i povezivanje vlasnika sa automobilom.
 *
 */

@Service
public class CarService {
    /**
     * Broker baze podataka koji je posrednik ka tabeli car.
     */
    @Autowired
    private CarRepository carRepository;
    /**
     * Broker baze podataka koji je posrednik ka tabeli owner.
     */
    @Autowired
    private OwnerRepository ownerRepository;

    /**
     * Metoda koja vraca listu svih automobila.
     * @return List<Car> lista svih automobila.
     */
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    /**
     * Metoda za perzistiranje automobila u sistem kroz trajno cuvanje.
     * Novi automobil se cuva u bazi podataka.
     * @param car automobil koji se treba perzistirati.
     * @return perzistirani automobil.
     * @throws Exception ako automobil sa istom markom, brendom i brojem konjskih snaga postoji u bazi podataka.
     */
    public Car addNewCar(Car car) throws Exception{
        Optional<Car> optionalCar= carRepository.findCarByBrandAndModelAndHorsePower(car.getBrand(),car.getModel(),car.getHorsePower());
        if(optionalCar.isPresent()){
            throw new Exception("Automobil vec postoji");
        }
        carRepository.save(car);
        return car;
    }

    /**
     * Metoda koja vraca automobil zajedno sa vlasnikom sa kojim je povezan.
     * @param carId ID automobila.
     * @param ownerId ID vlasnika sa kojim ce se automobil povezati.
     * @return automobil sa vozacem.
     * @throws Exception ako automobil ili vlasnik ne postoje u bazi podataka.
     */
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
