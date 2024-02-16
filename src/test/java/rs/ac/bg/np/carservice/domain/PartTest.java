package rs.ac.bg.np.carservice.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class PartTest {
    private Part part;
    @BeforeEach
    void setUp() {
        part=new Part();
        ;
    }

    @AfterEach
    void tearDown() {
        part= null;
    }
    @Test
    void setPartID() {
        part.setPartID(1L);
        assertEquals(1L,part.getPartID());
    }
    @Test
    void setPartIDExceotion() {
        assertThrows(IllegalArgumentException.class,()-> part.setPartID(0L));
    }

    @Test
    void setName() {
        part.setName("Auspuh");
        assertEquals("Auspuh",part.getName());
    }
    @Test
    void setNameEmpty() {
        assertThrows(IllegalArgumentException.class,()-> part.setName(""));
    }
    @Test
    void setNameNull() {
        assertThrows(IllegalArgumentException.class,()-> part.setName(null));
    }

    @Test
    void setPrice() {
        part.setPrice(1000);
        assertEquals(1000,part.getPrice());
    }
    @Test
    void setPriceException() {
        assertThrows(IllegalArgumentException.class,()-> part.setPrice(-1));
    }

    @Test
    void setBrand() {
        part.setBrand("Fiat");
        assertEquals("Fiat",part.getBrand());
    }
    @Test
    void setBrandEmpty() {
        assertThrows(IllegalArgumentException.class,()-> part.setBrand(""));
    }
    @Test
    void setBrandNull() {
        assertThrows(IllegalArgumentException.class,()-> part.setBrand(null));
    }

    @Test
    void setModel() {
        part.setModel("Punto");
        assertEquals("Punto",part.getModel());
    }
    @Test
    void setModelEmpty() {
        assertThrows(IllegalArgumentException.class,()-> part.setModel(""));
    }
    @Test
    void setModelNull() {
        assertThrows(IllegalArgumentException.class,()-> part.setModel(null));
    }
    @Test
    void setSuplier() {
        part.setSuplier(new Suplier(1L,"Tifa","Adresa 1","0685327394"));
        assertEquals(1L,part.getSuplier().getSuplierId());
    }
    @ParameterizedTest
    @CsvSource({
            "1,Auspuh,1500,Fiat,Punto,null",
            "2,Kocnice,2500,Fiat,Punto,null"

    })
    void parameterizedConstrictor(long partID, String name, int price, String brand,String model,String suplier){
        Suplier suplier1= new Suplier();
        Suplier result= suplier.equals("null")?null:suplier1;
        part.setPartID(partID);
        part.setName(name);
        part.setPrice(price);
        part.setBrand(brand);
        part.setModel(model);
        part.setSuplier(result);
        assertEquals(partID,part.getPartID());
        assertEquals(name,part.getName());
        assertEquals(price,part.getPrice());
        assertEquals(brand,part.getBrand());
        assertEquals(model,part.getModel());
        assertEquals(result,part.getSuplier());
    }
    @ParameterizedTest
    @CsvSource({
            "1,1,true",
            "2,1,false"
    })
    void equalsTest(long id1,long id2,boolean equal) {
        Part part2= new Part();
        part.setPartID(id1);
        part2.setPartID(id2);
        assertEquals(equal,part.equals(part2));
    }
    @Test
    void testToString(){
        part.setName("Auspuh");
        part.setPrice(1000);
        part.setBrand("Fiat");
        part.setModel("Evo");
        assertTrue(part.toString().contains("Auspuh"));
        assertTrue(part.toString().contains("1000"));
        assertTrue(part.toString().contains("Evo"));
        assertTrue(part.toString().contains("Fiat"));
        assertTrue(part.toString().contains("Evo"));
    }
}