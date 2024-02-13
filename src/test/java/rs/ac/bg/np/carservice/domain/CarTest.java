package rs.ac.bg.np.carservice.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {
    private Car car;
    @BeforeEach
    void setUp() {
        car= new Car();
    }

    @AfterEach
    void tearDown() {
        car= null;
    }

    @Test
    void setCarID() {
        car.setCarID(1L);
        assertEquals(1L,car.getCarID());
    }
    @Test
    void setCarIDException() {
        assertThrows(IllegalArgumentException.class,()->car.setCarID(0L));
    }

    @Test
    void setBrand() {
        car.setBrand("Fiat");
        assertEquals("Fiat",car.getBrand());
    }
    @Test
    void setBrandEmpty() {
        assertThrows(IllegalArgumentException.class,()->car.setBrand(""));
    }

    @Test
    void setModel() {
        car.setModel("Grande Punto");
        assertEquals("Grande Punto",car.getModel());
    }
    @Test
    void setModelEmpty() {
        assertThrows(IllegalArgumentException.class,()->car.setModel(""));
    }

    @Test
    void setLicensePlates() {
        car.setLicensePlates("BG2391DF");
        assertEquals("BG2391DF",car.getLicensePlates());
    }
    @Test
    void setLicensePlatesEmpty() {
        assertThrows(IllegalArgumentException.class,()->car.setLicensePlates(""));
    }

    @Test
    void setFuel() {
        car.setFuel("Benzin");
        assertEquals("Benzin",car.getFuel());
    }
    @Test
    void setFuelEmpty() {
        assertThrows(IllegalArgumentException.class,()->car.setFuel(""));
    }

    @Test
    void setHorsePower() {
        car.setHorsePower(1);
        assertEquals(1,car.getHorsePower());
    }
    @Test
    void setHorsePowerException() {
        assertThrows(IllegalArgumentException.class,()->car.setHorsePower(0));
    }

    @Test
    void setOwner() {
        car.setOwner(new Owner(1L,"Luka","Tatovic","059324881",null));
        assertEquals(1L,car.getOwner().getOwnerID());
    }

    @Test
    void setRepairs() {
        Set<Repair> repairs=new HashSet<>();
        Repair repair1=new Repair(1,null,null,new Date(),1,null);
        Repair repair2=new Repair(2,null,null,new Date(),1,null);
        repairs.add(repair1);
        repairs.add(repair2);
        car.setRepairs(repairs);
        assertEquals(repairs,car.getRepairs());
    }
    @ParameterizedTest
    @CsvSource({
            "1,Fiat,Evo,BG111RR,Benzin,80,null,null",
            "2,Fiat,Punto,BG222RR,Dizel,65,null,null"

    })
    void parameterizedConstrictor(long carID, String brand, String model, String licensePlates, String fuel, int horsePower, String owner,String repairs){
        Set<Repair> repairs1= new HashSet<>();
        Repair repair= new Repair();
        Set<Repair> result= repairs.equals("null")?null:repairs1;
        Owner owner1= new Owner();
        Owner resultOwner= owner.equals("null")?null:owner1;
        car= new Car(carID,brand,model,licensePlates,fuel,horsePower,resultOwner,result);
        assertEquals(carID,car.getCarID());
        assertEquals(brand,car.getBrand());
        assertEquals(model,car.getModel());
        assertEquals(licensePlates,car.getLicensePlates());
        assertEquals(fuel,car.getFuel());
        assertEquals(horsePower,car.getHorsePower());
        assertEquals(resultOwner,car.getOwner());
        assertEquals(result,car.getRepairs());

    }
}