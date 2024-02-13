package rs.ac.bg.np.carservice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import rs.ac.bg.np.carservice.domain.Car;
import rs.ac.bg.np.carservice.domain.Repair;
import rs.ac.bg.np.carservice.domain.Servicer;
import rs.ac.bg.np.carservice.repository.CarRepository;
import rs.ac.bg.np.carservice.repository.RepairRepository;
import rs.ac.bg.np.carservice.repository.ServicerRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class RepairServiceTest {
    @Mock private RepairRepository repairRepository;
    @InjectMocks private RepairService repairService;
    @Mock private CarRepository carRepository;
    @InjectMocks private CarService carService;
    @Mock private ServicerRepository servicerRepository;
    @InjectMocks private ServicerService servicerService;

    @Test
    void getAllRepairs() {
        Car car1= new Car(1L,"Fiat","Evo","BG231EE","Dizel",80,null,new HashSet<>());
        Car car2= new Car(2L,"Opel","Astra","BG1029KE","Dizel",90,null,new HashSet<>());
        Servicer servicer1= new Servicer(1L,"Luka","Tatovic",null,null);
        Servicer servicer2= new Servicer(1L,"Luka","Tatovic",null,null);
        Repair repair1= new Repair(1L,servicer1,car1,new Date(),2000,new HashSet<>());
        Repair repair2= new Repair(2L,servicer2,car2,new Date(),3000,new HashSet<>());
        List<Repair> repairs= new ArrayList<>();
        repairs.add(repair1);
        repairs.add(repair2);
        Mockito.when(repairRepository.findAll()).thenReturn(repairs);
        List<Repair> result= repairService.getAllRepairs();
        Mockito.verify(repairRepository,Mockito.times(1)).findAll();
        assertEquals(result,repairs);
    }

    @Test
    void getOneRepairs() throws Exception {
        Car car= new Car(1L,"Fiat","Evo","BG231EE","Dizel",80,null,new HashSet<>());
        Servicer servicer= new Servicer(1L,"Luka","Tatovic",null,null);
        Repair repair= new Repair(1L,servicer,car,new Date(),2000,new HashSet<>());

        Mockito.when(repairRepository.findById(repair.getRepairID())).thenReturn(Optional.of(repair));
        Repair result= repairService.getOneRepairs(repair.getRepairID());
        Mockito.verify(repairRepository,Mockito.times(1)).findById(repair.getRepairID());
        assertEquals(result,repair);
    }
    @Test
    void getOneRepairsException() throws Exception {
        Car car= new Car(1L,"Fiat","Evo","BG231EE","Dizel",80,null,new HashSet<>());
        Servicer servicer= new Servicer(1L,"Luka","Tatovic",null,null);
        Repair repair= new Repair(1L,servicer,car,new Date(),2000,new HashSet<>());

        Mockito.when(repairRepository.findById(repair.getRepairID())).thenReturn(Optional.empty());
        assertThrows(Exception.class,()->{repairService.getOneRepairs(repair.getRepairID());});
        Mockito.verify(repairRepository,Mockito.times(1)).findById(repair.getRepairID());

    }

    @Test
    void servicerNewRepair() throws Exception {
        Car car= new Car(1L,"Fiat","Evo","BG231EE","Dizel",80,null,new HashSet<>());
        Servicer servicer= new Servicer(1L,"Luka","Tatovic",null,new HashSet<>());
        Repair repair= new Repair(1L,null,null,new Date(),2000,new HashSet<>());

        Mockito.when(servicerRepository.findById(servicer.getServicerID())).thenReturn(Optional.of(servicer));
        Mockito.when(repairRepository.findById(repair.getRepairID())).thenReturn(Optional.of(repair));
        Mockito.when(carRepository.findById(car.getCarID())).thenReturn(Optional.of(car));
        Repair result= repairService.servicerNewRepair(servicer.getServicerID(),repair.getRepairID(),car.getCarID());
        Mockito.verify(repairRepository,Mockito.times(1)).findById(repair.getRepairID());
        assertEquals(result,repair);

    }
    @Test
    void servicerNewRepairExceptonRepair() throws Exception {
        Car car= new Car(1L,"Fiat","Evo","BG231EE","Dizel",80,null,new HashSet<>());
        Servicer servicer= new Servicer(1L,"Luka","Tatovic",null,new HashSet<>());
        Repair repair= new Repair(1L,null,null,new Date(),2000,new HashSet<>());
        Mockito.when(servicerRepository.findById(servicer.getServicerID())).thenReturn(Optional.of(servicer));
        Mockito.when(repairRepository.findById(repair.getRepairID())).thenReturn(Optional.empty());
        assertThrows(Exception.class,()->{repairService.servicerNewRepair(servicer.getServicerID(),repair.getRepairID(),car.getCarID());});

    }
    @Test
    void servicerNewRepairExceptonServicer() throws Exception {
        Car car= new Car(1L,"Fiat","Evo","BG231EE","Dizel",80,null,new HashSet<>());
        Servicer servicer= new Servicer(1L,"Luka","Tatovic",null,new HashSet<>());
        Repair repair= new Repair(1L,null,null,new Date(),2000,new HashSet<>());


        Mockito.when(servicerRepository.findById(servicer.getServicerID())).thenReturn(Optional.empty());
        assertThrows(Exception.class,()->{repairService.servicerNewRepair(servicer.getServicerID(),repair.getRepairID(),car.getCarID());});

    }
    @Test
    void servicerNewRepairExceptonCar() throws Exception {
        Car car= new Car(1L,"Fiat","Evo","BG231EE","Dizel",80,null,new HashSet<>());
        Servicer servicer= new Servicer(1L,"Luka","Tatovic",null,new HashSet<>());
        Repair repair= new Repair(1L,null,null,new Date(),2000,new HashSet<>());

        Mockito.when(servicerRepository.findById(servicer.getServicerID())).thenReturn(Optional.of(servicer));
        Mockito.when(repairRepository.findById(repair.getRepairID())).thenReturn(Optional.of(repair));
        Mockito.when(carRepository.findById(car.getCarID())).thenReturn(Optional.empty());

        assertThrows(Exception.class,()->{repairService.servicerNewRepair(servicer.getServicerID(),repair.getRepairID(),car.getCarID());});

    }

    @Test
    void getAllRepairsFromServicer() throws Exception {
        Car car= new Car(1L,"Fiat","Evo","BG231EE","Dizel",80,null,new HashSet<>());
        Servicer servicer= new Servicer(1L,"Luka","Tatovic",null,null);
        Repair repair1= new Repair(1L,servicer,car,new Date(),2000,null);
        Repair repair2= new Repair(1L,servicer,car,new Date(),4000,null);
        Set<Repair> repairs= new HashSet<>();
        repairs.add(repair1);
        repairs.add(repair2);
        servicer.setRepairs(repairs);

        Mockito.when(servicerRepository.findById(servicer.getServicerID())).thenReturn(Optional.of(servicer));
        Set<Repair> result= repairService.getAllRepairsFromServicer(servicer.getServicerID());
        Mockito.verify(servicerRepository,Mockito.times(1)).findById(servicer.getServicerID());
        assertEquals(result,repairs);

    }
    @Test
    void getAllRepairsFromServicerException() throws Exception {
        Car car= new Car(1L,"Fiat","Evo","BG231EE","Dizel",80,null,new HashSet<>());
        Servicer servicer= new Servicer(1L,"Luka","Tatovic",null,null);
        Repair repair1= new Repair(1L,servicer,car,new Date(),2000,null);
        Repair repair2= new Repair(1L,servicer,car,new Date(),4000,null);
        Set<Repair> repairs= new HashSet<>();
        repairs.add(repair1);
        repairs.add(repair2);
        servicer.setRepairs(repairs);

        Mockito.when(servicerRepository.findById(servicer.getServicerID())).thenReturn(Optional.empty());
        assertThrows(Exception.class,()->{repairService.getAllRepairsFromServicer(servicer.getServicerID());});

    }

    @Test
    void getAllRepairsFromCar() throws Exception {
        Car car= new Car(1L,"Fiat","Evo","BG231EE","Dizel",80,null,null);
        Servicer servicer= new Servicer(1L,"Luka","Tatovic",null,null);
        Repair repair1= new Repair(1L,servicer,car,new Date(),2000,null);
        Repair repair2= new Repair(1L,servicer,car,new Date(),4000,null);
        Set<Repair> repairs= new HashSet<>();
        repairs.add(repair1);
        repairs.add(repair2);
        car.setRepairs(repairs);

        Mockito.when(carRepository.findById(car.getCarID())).thenReturn(Optional.of(car));
        Set<Repair> result= repairService.getAllRepairsFromCar(car.getCarID());
        Mockito.verify(carRepository,Mockito.times(1)).findById(car.getCarID());
        assertEquals(result,repairs);
    }
    @Test
    void getAllRepairsFromCarException() throws Exception {
        Car car= new Car(1L,"Fiat","Evo","BG231EE","Dizel",80,null,null);
        Servicer servicer= new Servicer(1L,"Luka","Tatovic",null,null);
        Repair repair1= new Repair(1L,servicer,car,new Date(),2000,null);
        Repair repair2= new Repair(1L,servicer,car,new Date(),4000,null);
        Set<Repair> repairs= new HashSet<>();
        repairs.add(repair1);
        repairs.add(repair2);
        car.setRepairs(repairs);

        Mockito.when(carRepository.findById(car.getCarID())).thenReturn(Optional.empty());
        assertThrows(Exception.class,()->{repairService.getAllRepairsFromCar(car.getCarID());});
    }

    @Test
    void addNewRepair() throws Exception {
        Car car= new Car(1L,"Fiat","Evo","BG231EE","Dizel",80,null,null);
        Servicer servicer= new Servicer(1L,"Luka","Tatovic",null,null);
        Repair repair= new Repair(1L,servicer,car,new Date(),2000,null);

        Mockito.when(repairRepository.save(repair)).thenReturn(repair);
        Repair result= repairService.addNewRepair(repair);
        Mockito.verify(repairRepository,Mockito.times(1)).save(repair);
        assertEquals(result,repair);
    }
    @Test
    void addNewRepairException() throws Exception {
        Car car= new Car(1L,"Fiat","Evo","BG231EE","Dizel",80,null,null);
        Servicer servicer= new Servicer(1L,"Luka","Tatovic",null,null);
        Repair repair= new Repair(1L,servicer,car,new Date(),2000,null);

        Mockito.when(repairRepository.findById(repair.getRepairID())).thenReturn(Optional.of(repair));
        assertThrows(Exception.class,()->{repairService.addNewRepair(repair);});
        Mockito.verify(repairRepository,Mockito.times(1)).findById(repair.getRepairID());

    }
}