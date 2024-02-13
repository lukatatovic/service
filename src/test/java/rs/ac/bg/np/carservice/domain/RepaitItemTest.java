package rs.ac.bg.np.carservice.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class RepaitItemTest {
    private RepaitItem rp;
    @BeforeEach
    void setUp() {
        rp= new RepaitItem();
    }

    @AfterEach
    void tearDown() {
        rp=null;
    }

    @Test
    void setRepaiItemId() {
        rp.setRepaiItemId(1L);
        assertEquals(1L,rp.getRepaiItemId());
    }
    @Test
    void setRepaiItemIdException() {
        assertThrows(IllegalArgumentException.class,()-> rp.setRepaiItemId(0L));
    }

    @Test
    void setRepair() {
        Repair repair= new Repair(1L,null,null,new Date(),1000,null);
        rp.setRepair(repair);
        assertEquals(repair,rp.getRepair());
    }

    @Test
    void setPart() {
        Part part= new Part(1L,"Auspuh",1000,"Fiat","Evo",null);
        rp.setPart(part);
        assertEquals(part,rp.getPart());
    }
    @Test
    void setPartNull() {
        assertThrows(IllegalArgumentException.class,()-> rp.setPart(null));
    }
    @ParameterizedTest
    @CsvSource({
            "1,null,1:Auspuh:1000:Fiat:Evo:null",
            "2,null,2:Kocnice:3000:Fiat:Evo:null"

    })
    void parameterizedConstrictor(long id, String repair, String part){
        Repair repair1= new Repair();
        Repair resultRepair= repair.equals("null")?null:repair1;
        String[] partTxt= part.split(":");
        Part partRes= new Part();
        partRes.setPartID(Long.parseLong(partTxt[0]));
        partRes.setName(partTxt[1]);
        partRes.setPrice(Integer.parseInt(partTxt[2]));
        partRes.setBrand(partTxt[3]);
        partRes.setModel(partTxt[4]);
        Suplier s= new Suplier();
        Suplier supRes= partTxt[5].equals("null")?null:s;
        partRes.setSuplier(supRes);
        rp.setRepaiItemId(id);
        rp.setRepair(resultRepair);
        rp.setPart(partRes);
        assertEquals(id,rp.getRepaiItemId());
        assertEquals(resultRepair,rp.getRepair());
        assertEquals(partRes,rp.getPart());

    }
    @ParameterizedTest
    @CsvSource({
            "1,1,true",
            "2,1,false"
    })
    void equalsTest(long id1,long id2,boolean equal) {
        RepaitItem rp2= new RepaitItem();
        rp2.setRepaiItemId(id2);
        rp.setRepaiItemId(id1);
        assertEquals(equal,rp.equals(rp2));
    }
}