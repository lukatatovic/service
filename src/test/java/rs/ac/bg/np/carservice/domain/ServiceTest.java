package rs.ac.bg.np.carservice.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    private Service service;
    @BeforeEach
    void setUp() {
        service= new Service();
    }

    @AfterEach
    void tearDown() {
        service=null;
    }

    @Test
    void setServiceID() {
        service.setServiceID(1L);
        assertEquals(1L,service.getServiceID());
    }
    @Test
    void setServiceIDException() {
        assertThrows(IllegalArgumentException.class,()-> service.setServiceID(0L));
    }

    @Test
    void setName() {
        service.setName("Servis 1");
        assertEquals("Servis 1",service.getName());
    }
    @Test
    void setNameEmpty() {
        assertThrows(IllegalArgumentException.class,()-> service.setName(""));
    }
    @Test
    void setNameNull() {
        assertThrows(IllegalArgumentException.class,()-> service.setName(null));
    }

    @Test
    void setAdress() {
        service.setAdress("Adresa 1");
        assertEquals("Adresa 1",service.getAdress());
    }
    @Test
    void setAdressEmpty() {
        assertThrows(IllegalArgumentException.class,()-> service.setAdress(""));
    }
    @Test
    void setAdressNull() {
        assertThrows(IllegalArgumentException.class,()-> service.setAdress(null));
    }
    @Test
    void setPhoneNumber() {
        service.setPhoneNumber("068302803892");
        assertEquals("068302803892",service.getPhoneNumber());
    }
    @Test
    void setPhoneNumberEmpty() {
        assertThrows(IllegalArgumentException.class,()-> service.setPhoneNumber(""));
    }
    @Test
    void setPhoneNumberNull() {
        assertThrows(IllegalArgumentException.class,()-> service.setPhoneNumber(null));
    }
    @Test
    void setServicers() {
        Set<Servicer> servicers= new HashSet<>();
        Servicer servicer1= new Servicer(1L,"Luka","Tatovic",null,null);
        Servicer servicer2= new Servicer(2L,"Uros","Tatovic",null,null);
        servicers.add(servicer1);
        servicers.add(servicer2);
        service.setServicers(servicers);
        assertEquals(servicers,service.getServicers());
    }
    @ParameterizedTest
    @CsvSource({
            "1,1,true",
            "2,1,false"
    })
    void equalsTest(long id1,long id2,boolean equal) {
        Service service1= new Service();
        service1.setServiceID(id1);
        Service service2=new Service();
        service2.setServiceID(id2);
        assertEquals(equal,service1.equals(service2));
    }

}