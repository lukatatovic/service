package rs.ac.bg.np.carservice.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RepairTest {
    private Repair repair;
    @BeforeEach
    void setUp() {
        repair=new Repair();
    }

    @AfterEach
    void tearDown() {
        repair=null;
    }

    @Test
    void setRepairID() {
        repair.setRepairID(1L);
        assertEquals(1L,repair.getRepairID());
    }
    @Test
    void setRepairIDException() {
        assertThrows(IllegalArgumentException.class,()->repair.setRepairID(0L));
    }

    @Test
    void setServicer() {
        Servicer servicer= new Servicer(1L,"Luka","Tatovic",null,null);
        repair.setServicer(servicer);
        assertEquals(servicer,repair.getServicer());
    }

    @Test
    void setCar() {
        Car car= new Car(1L,"Fiat","Evo","BG239AL","Dizel",80,null,null);
        repair.setCar(car);
        assertEquals(car,repair.getCar());
    }

    @Test
    void setDate() {
        repair.setDate(new Date());
        assertEquals(new Date(),repair.getDate());
    }
    @Test
    void setDateException() {
        assertThrows(IllegalArgumentException.class,()->repair.setDate(null));
    }


    @Test
    void setPrice() {
        repair.setPrice(0);
        assertEquals(0,repair.getPrice());
    }
    @Test
    void setPriceException() {
        assertThrows(IllegalArgumentException.class,()->repair.setPrice(-1));
    }

    @Test
    void setItems() {
        Set<RepaitItem> repaitItems= new HashSet<>();
        Part part1= new Part(1L,"Auspuh",1000,"Fiat","Evo",null);
        Part part2= new Part(2L,"Kocnice",2000,"Fiat","Evo",null);
        RepaitItem rp1= new RepaitItem(1L,null,part1);
        RepaitItem rp2= new RepaitItem(2L,null,part2);
        repaitItems.add(rp2);
        repaitItems.add(rp1);
        repair.setItems(repaitItems);
        assertEquals(repaitItems,repair.getItems());

    }
    @ParameterizedTest
    @CsvSource({
            "1,null,null,2024-02-01,1000,null",
            "2,null,null,2023-10-11,3000,null"

    })
    void parameterizedConstrictor(long repairID, String servicer, String car, String date, int price,String items){
        Car car1= new Car();
        Car resultCar = car.equals("null")?null:car1;
        Servicer servicer1= new Servicer();
        Servicer resultServicer=servicer.equals("null")?null:servicer1;
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        Date date1;
        try {
            date1= sdf.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Set<RepaitItem> repaitItems= new HashSet<>();
        Set<RepaitItem> resultItems= items.equals("null")?null:repaitItems;
        repair.setRepairID(repairID);
        repair.setServicer(resultServicer);
        repair.setCar(resultCar);
        repair.setDate(date1);
        repair.setPrice(price);
        repair.setItems(resultItems);
        assertEquals(repairID,repair.getRepairID());
        assertEquals(resultServicer,repair.getServicer());
        assertEquals(resultCar,repair.getCar());
        assertEquals(date1,repair.getDate());
        assertEquals(price,repair.getPrice());
        assertEquals(resultItems,repair.getItems());


    }
}