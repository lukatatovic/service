package rs.ac.bg.np.carservice.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class SuplierTest {
    private Suplier suplier;
    @BeforeEach
    void setUp(){
        suplier= new Suplier();
    }
    @AfterEach
    void tearDown(){
        suplier=null;
    }

    @Test
    void setSuplierId() {
        suplier.setSuplierId(1);
        assertEquals(1,suplier.getSuplierId());
    }
    @Test
    void setSuplierIdException() {
        assertThrows(IllegalArgumentException.class,()-> suplier.setSuplierId(0));
    }

    @Test
    void setName() {
        suplier.setName("Luka");
        assertEquals("Luka",suplier.getName());
    }
    @Test
    void setNameExceptionEmpty() {
        assertThrows(IllegalArgumentException.class,()->suplier.setName(""));
    }
    @Test
    void setNameExceptionNull() {
        assertThrows(IllegalArgumentException.class,()->suplier.setName(null));
    }

    @Test
    void setAdress() {
        suplier.setAdress("Adresa 1");
        assertEquals("Adresa 1",suplier.getAdress());
    }
    @Test
    void setAdressEmpty() {
        assertThrows(IllegalArgumentException.class,()->suplier.setAdress(""));
    }
    @Test
    void setAdressNull() {
        assertThrows(IllegalArgumentException.class,()->suplier.setAdress(null));
    }

    @Test
    void setPhoneNumber() {
        suplier.setPhoneNumber("0653942320");
        assertEquals("0653942320",suplier.getPhoneNumber());
    }
    @Test
    void setPhoneNumberEmpty() {
        assertThrows(IllegalArgumentException.class,()->suplier.setPhoneNumber(""));
    }
    @ParameterizedTest
    @CsvSource({
            "1,Tifa,Adresa 1,0694173942",
            "2,Naultre,Adresa 2,0694827193"

    })
    void parameterizedConstrictor(long suplierId, String name, String adress, String phoneNumber){
        suplier= new Suplier(suplierId,name,adress,phoneNumber);
        assertEquals(suplierId,suplier.getSuplierId());
        assertEquals(name,suplier.getName());
        assertEquals(adress,suplier.getAdress());
        assertEquals(phoneNumber,suplier.getPhoneNumber());
    }
}