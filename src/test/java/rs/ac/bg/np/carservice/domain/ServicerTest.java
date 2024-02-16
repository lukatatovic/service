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

class ServicerTest {
    private Servicer servicer;
    @BeforeEach
    void setUp() {
        servicer=new Servicer();
    }

    @AfterEach
    void tearDown() {
        servicer=null;
    }

    @Test
    void setServicerID() {
        servicer.setServicerID(1L);
        assertEquals(1L,servicer.getServicerID());
    }
    @Test
    void setServicerIDException() {
        assertThrows(IllegalArgumentException.class,()-> servicer.setServicerID(0L));
    }

    @Test
    void setName() {
        servicer.setName("Luka");
        assertEquals("Luka",servicer.getName());
    }
    @Test
    void setNameEmpty() {
        assertThrows(IllegalArgumentException.class,()-> servicer.setName(""));
    }
    @Test
    void setNameNull() {
        assertThrows(IllegalArgumentException.class,()-> servicer.setName(null));
    }

    @Test
    void setSurname() {
        servicer.setSurname("Tatovic");
        assertEquals("Tatovic",servicer.getSurname());
    }
    @Test
    void setSurnameEmpty() {
        assertThrows(IllegalArgumentException.class,()-> servicer.setSurname(""));
    }
    @Test
    void setSurnameNull() {
        assertThrows(IllegalArgumentException.class,()-> servicer.setSurname(null));
    }

    @Test
    void setService() {
        Service service= new Service(1L,"Servis","Adresa 12","06491278492",null);
        servicer.setService(service);
        assertEquals(service,servicer.getService());
    }

    @Test
    void setRepairs() {
        Set<Repair> repairs= new HashSet<>();
        Repair repair1= new Repair(1L,null,null,new Date(),1000,null);
        Repair repair2= new Repair(2L,null,null,new Date(),3000,null);
        repairs.add(repair2);
        repairs.add(repair1);
        servicer.setRepairs(repairs);
        assertEquals(repairs,servicer.getRepairs());

    }
    @ParameterizedTest
    @CsvSource({
            "1,Luka,Tatovic,null,null",
            "2,Uros,Tatovic,null,null"

    })
    void parameterizedConstrictor(long servicerID, String name, String surname, String service, String repairs){
        Service service1= new Service();
        Service resultService= service.equals("null")?null:service1;
        Set<Repair> repairSet= new HashSet<>();
        Repair repair= new Repair();
        Set<Repair> resultRepair= repairs.equals("null")?null:repairSet;
        servicer.setRepairs(resultRepair);
        servicer.setService(resultService);
        servicer.setServicerID(servicerID);
        servicer.setName(name);
        servicer.setSurname(surname);
        assertEquals(servicerID,servicer.getServicerID());
        assertEquals(name,servicer.getName());
        assertEquals(resultService,servicer.getService());
        assertEquals(surname,servicer.getSurname());
        assertEquals(resultRepair,servicer.getRepairs());
    }
    @ParameterizedTest
    @CsvSource({
            "Luka,Tatovic",
            "Uros,Tatovic"

    })
    void parameterizedConstrictor(String name, String surname){
        servicer.setName(name);
        servicer.setSurname(surname);
        assertEquals(name,servicer.getName());
        assertEquals(surname,servicer.getSurname());
    }
    @ParameterizedTest
    @CsvSource({
            "1,1,true",
            "2,1,false"
    })
    void equalsTest(long id1,long id2,boolean equal) {
        Servicer servicer2= new Servicer();
        servicer.setServicerID(id1);
        servicer2.setServicerID(id2);
        assertEquals(equal,servicer.equals(servicer2));
    }
    @Test
    void testToString(){
        servicer.setName("Luka");
        servicer.setSurname("Tatovic");
        assertTrue(servicer.toString().contains("Luka"));
        assertTrue(servicer.toString().contains("Tatovic"));
    }
}