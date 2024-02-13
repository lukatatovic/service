package rs.ac.bg.np.carservice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import rs.ac.bg.np.carservice.domain.Car;
import rs.ac.bg.np.carservice.domain.Owner;
import rs.ac.bg.np.carservice.repository.OwnerRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class OwnerServiceTest {
    @Mock
    private OwnerRepository ownerRepository;
    @InjectMocks
    private OwnerService ownerService;
    @Test
    void createOwner() throws Exception {
        Owner owner1= new Owner(1L,"Luka","Tatovic","06458271293",null);
        Car car1= new Car(1L,"Fiat","Evo","BG231AI","Dizel",80,owner1,null);
        Car car2= new Car(2L,"Fiat","Punto","BG1892AI","Benzin",60,owner1,null);
        Set<Car> cars= new HashSet<>();
        cars.add(car1);
        cars.add(car2);
        owner1.setCars(cars);

        Mockito.when(ownerRepository.save(owner1)).thenReturn(owner1);
        Owner result= ownerService.createOwner(owner1);

        Mockito.verify(ownerRepository,Mockito.times(1)).save(owner1);
        assertEquals(result,owner1);

    }
    @Test
    void createOwnerException() throws Exception {
        Owner owner1= new Owner(1L,"Luka","Tatovic","06458271293",null);
        Car car1= new Car(1L,"Fiat","Evo","BG231AI","Dizel",80,owner1,null);
        Car car2= new Car(2L,"Fiat","Punto","BG1892AI","Benzin",60,owner1,null);
        Set<Car> cars= new HashSet<>();
        cars.add(car1);
        cars.add(car2);
        owner1.setCars(cars);

        Mockito.when(ownerRepository.findByPhoneNumber(owner1.getPhoneNumber())).thenReturn(Optional.of(owner1));
        assertThrows(Exception.class,()->{ownerService.createOwner(owner1);});
        Mockito.verify(ownerRepository,Mockito.times(1)).findByPhoneNumber(owner1.getPhoneNumber());

    }

    @Test
    void getOneOwner() throws Exception {
        Owner owner1= new Owner(1L,"Luka","Tatovic","06458271293",null);
        Car car1= new Car(1L,"Fiat","Evo","BG231AI","Dizel",80,owner1,null);
        Car car2= new Car(2L,"Fiat","Punto","BG1892AI","Benzin",60,owner1,null);
        Set<Car> cars= new HashSet<>();
        cars.add(car1);
        cars.add(car2);
        owner1.setCars(cars);

        Mockito.when(ownerRepository.findById(owner1.getOwnerID())).thenReturn(Optional.of(owner1));
        Owner result= ownerService.getOneOwner(owner1.getOwnerID());
        Mockito.verify(ownerRepository,Mockito.times(1)).findById(owner1.getOwnerID());
        assertEquals(result,owner1);
    }
    @Test
    void getOneOwnerException() throws Exception {
        Owner owner1= new Owner(1L,"Luka","Tatovic","06458271293",null);
        Car car1= new Car(1L,"Fiat","Evo","BG231AI","Dizel",80,owner1,null);
        Car car2= new Car(2L,"Fiat","Punto","BG1892AI","Benzin",60,owner1,null);
        Set<Car> cars= new HashSet<>();
        cars.add(car1);
        cars.add(car2);
        owner1.setCars(cars);

        Mockito.when(ownerRepository.findById(owner1.getOwnerID())).thenReturn(Optional.empty());
        assertThrows(Exception.class,()->{ownerService.getOneOwner(owner1.getOwnerID());});
        Mockito.verify(ownerRepository,Mockito.times(1)).findById(owner1.getOwnerID());

    }
}