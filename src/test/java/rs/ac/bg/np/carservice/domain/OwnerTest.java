package rs.ac.bg.np.carservice.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerTest {
    private Owner owner;
    @BeforeEach
    void setUp() {
        owner=new Owner();
    }

    @AfterEach
    void tearDown() {
        owner=null;
    }

    @Test
    void setOwnerID() {
        owner.setOwnerID(1L);
        assertEquals(1L,owner.getOwnerID());
    }
    @Test
    void setOwnerIDException() {
        assertThrows(IllegalArgumentException.class,()->owner.setOwnerID(-1L));
    }


    @Test
    void setName() {
        owner.setName("Luka");
        assertEquals("Luka",owner.getName());
    }
    @Test
    void setNameEmpty() {
        assertThrows(IllegalArgumentException.class,()->owner.setName(""));
    }

    @Test
    void setSurname() {
        owner.setSurname("Tatovic");
        assertEquals("Tatovic",owner.getSurname());
    }
    @Test
    void setSurnameEmpty() {
        assertThrows(IllegalArgumentException.class,()->owner.setSurname(""));
    }
    @Test
    void setPhoneNumber() {
        owner.setPhoneNumber("0684810328");
        assertEquals("0684810328",owner.getPhoneNumber());
    }
    @Test
    void setPhoneNumberEmpty() {
        assertThrows(IllegalArgumentException.class,()->owner.setPhoneNumber(""));
    }

    @Test
    void setCars() {
        Set<Car> cars= new HashSet<>();
        cars.add(new Car(1L,"Fiat","Evo","BG238EI","Plin",64,null,null));
        cars.add(new Car(2L,"Fiat","Punto","BG238EG","Plin",64,null,null));
        owner.setCars(cars);
        assertEquals(cars,owner.getCars());
    }
    @ParameterizedTest
    @CsvSource({
            "1,Luka,Tatovic,0649183049,2:Fiat:Punto:BG232EI:Plin:64:null:null",
            "2,Uros,Tatovic,0649138948,1:Fiat:Evo:BG238EI:Plin:80:null:null"

    })
    void parameterizedConstrictor(long ownerID,String name,String surname,String phoneNumber,String carT){
        Car car= new Car();
        String[] carInfo= carT.split(":");
        car.setCarID(Long.parseLong(carInfo[0]));
        car.setBrand(carInfo[1]);
        car.setModel(carInfo[2]);
        car.setLicensePlates(carInfo[3]);
        car.setFuel(carInfo[4]);
        car.setHorsePower(Integer.parseInt(carInfo[5]));
        Owner owner1= new Owner();
        Owner resultOwner1=carInfo[6].equals("null")?null:owner1;
        car.setOwner(resultOwner1);
        Set<Repair> repairSet= new HashSet<>();
        Repair repair= new Repair();
        Set<Repair> resultRepair= carInfo[7].equals("null")?null:repairSet;
        car.setRepairs(resultRepair);
        Set<Car> cars= new HashSet<>();
        cars.add(car);
        Owner test= new Owner(ownerID,name,surname,phoneNumber,cars);
        assertEquals(test.getOwnerID(),ownerID);
        assertEquals(test.getName(),name);
        assertEquals(test.getSurname(),surname);
        assertEquals(test.getPhoneNumber(),phoneNumber);
        assertEquals(test.getCars(),cars);


    }
}