package rs.ac.bg.np.carservice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import rs.ac.bg.np.carservice.domain.Suplier;
import rs.ac.bg.np.carservice.repository.SuplierRepository;

import javax.swing.plaf.LabelUI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class SuplierServiceTest {
    @Mock private SuplierRepository suplierRepository;
    @InjectMocks private SuplierService suplierService;
    @Test
    void getAll() {
        Suplier suplier1= new Suplier(1L,"Tifa","Adresa 1","06318231749");
        Suplier suplier2= new Suplier(2L,"Naultre","Adresa 12","063293489");
        List<Suplier> supliers= new ArrayList<>();
        supliers.add(suplier1);
        supliers.add(suplier2);
        Mockito.when(suplierRepository.findAll()).thenReturn(supliers);
        List<Suplier> result= suplierService.getAll();
        Mockito.verify(suplierRepository,Mockito.times(1)).findAll();
        assertEquals(result,supliers);
    }

    @Test
    void addNewSuplier() throws Exception {
        Suplier suplier= new Suplier(1L,"Tifa","Adresa 1","06318231749");
        Mockito.when(suplierRepository.save(suplier)).thenReturn(suplier);
        Suplier result= suplierService.addNewSuplier(suplier);
        Mockito.verify(suplierRepository,Mockito.times(1)).save(suplier);
        assertEquals(result,suplier);

    }
    @Test
    void addNewSuplierException() throws Exception {
        Suplier suplier= new Suplier(1L,"Tifa","Adresa 1","06318231749");
        Mockito.when(suplierRepository.findByAdress(suplier.getAdress())).thenReturn(Optional.of(suplier));
        assertThrows(Exception.class,()->{suplierService.addNewSuplier(suplier);});
        Mockito.verify(suplierRepository,Mockito.times(1)).findByAdress(suplier.getAdress());


    }

    @Test
    void deleteSuplier() {
        Suplier suplier= new Suplier(1L,"Tifa","Adresa 1","06318231749");
        long id= suplier.getSuplierId();
        Mockito.when(suplierRepository.findById(id)).thenReturn(Optional.of(suplier));
        suplierService.deleteSuplier(id);
        Mockito.verify(suplierRepository,Mockito.times(1)).deleteById(id);
        assertTrue(suplierRepository.findById(id).isPresent());
    }
}