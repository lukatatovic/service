package rs.ac.bg.np.carservice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import rs.ac.bg.np.carservice.domain.Car;
import rs.ac.bg.np.carservice.domain.Owner;
import rs.ac.bg.np.carservice.repository.CarRepository;
import rs.ac.bg.np.carservice.repository.OwnerRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class CarServiceTest {
    @Mock
    private CarRepository carRepository;
    @InjectMocks
    private CarService carService;
    @Mock
    private OwnerRepository ownerRepository;
    @InjectMocks
    private OwnerService ownerService;
    @Test
    void getAllCars() {
        Owner owner1= new Owner(1L,"Luka","Tatovic","06458271293",null);
        Owner owner2= new Owner(2L,"Uros","Tatovic","06458139123",null);
        Car car1= new Car(1L,"Fiat","Evo","BG231AI","Dizel",80,owner1,null);
        Car car2= new Car(2L,"Fiat","Punto","BG1892AI","Benzin",60,owner2,null);

        List<Car>cars= new ArrayList<>();
        cars.add(car1);
        cars.add(car2);
        Mockito.when(carRepository.findAll()).thenReturn(cars);
        List<Car> result= carService.getAllCars();
        Mockito.verify(carRepository,Mockito.times(1)).findAll();
        assertEquals(result,cars);
    }

    @Test
    void addNewCar() throws Exception {
        Owner owner= new Owner(1L,"Luka","Tatovic","06458271293",null);
        Car car= new Car(1L,"Fiat","Evo","BG231AI","Dizel",80,owner,null);

        Mockito.when(carRepository.save(car)).thenReturn(car);
        Car result=carService.addNewCar(car);

        Mockito.verify(carRepository,Mockito.times(1)).save(car);
        assertEquals(result,car);
    }
    @Test
    void addNewCarException() throws Exception {
        Owner owner= new Owner(1L,"Luka","Tatovic","06458271293",null);
        Car car= new Car(1L,"Fiat","Evo","BG231AI","Dizel",80,owner,null);

        Mockito.when(carRepository.findCarByLicensePlates(car.getLicensePlates())).thenReturn(Optional.of(car));

        assertThrows(Exception.class,()->{carService.addNewCar(car);});
        Mockito.verify(carRepository,Mockito.times(1)).findCarByLicensePlates(car.getLicensePlates());
    }

    @Test
    void addOwnerForCar() throws Exception {
        Owner owner= new Owner(1L,"Luka","Tatovic","06458271293",new HashSet<>());
        Car car= new Car(1L,"Fiat","Evo","BG231AI","Dizel",80,owner,null);

        Mockito.when(carRepository.findById(car.getCarID())).thenReturn(Optional.of(car));
        Mockito.when(ownerRepository.findById(owner.getOwnerID())).thenReturn(Optional.of(owner));

        List<Car> cars= carService.getAllCars();
        Car result= carService.addOwnerForCar(car.getCarID(),owner.getOwnerID());
        Mockito.verify(carRepository,Mockito.times(1)).findById(car.getCarID());
        assertEquals(result,car);
    }
    @Test
    void addOwnerForCarExceptionCar() throws Exception {
        Owner owner= new Owner(1L,"Luka","Tatovic","06458271293",new HashSet<>());
        Car car= new Car(1L,"Fiat","Evo","BG231AI","Dizel",80,owner,null);

        Mockito.when(carRepository.findById(car.getCarID())).thenReturn(Optional.empty());
        Mockito.when(ownerRepository.findById(owner.getOwnerID())).thenReturn(Optional.of(owner));

        assertThrows(Exception.class,()->{carService.addOwnerForCar(car.getCarID(),owner.getOwnerID());});
    }
    @Test
    void addOwnerForCarExceptionOwner() throws Exception {
        Owner owner= new Owner(1L,"Luka","Tatovic","06458271293",new HashSet<>());
        Car car= new Car(1L,"Fiat","Evo","BG231AI","Dizel",80,owner,null);

        Mockito.when(carRepository.findById(car.getCarID())).thenReturn(Optional.of(car));
        Mockito.when(ownerRepository.findById(owner.getOwnerID())).thenReturn(Optional.empty());

        assertThrows(Exception.class,()->{carService.addOwnerForCar(car.getCarID(),owner.getOwnerID());});
    }
}