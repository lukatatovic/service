package rs.ac.bg.np.carservice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import rs.ac.bg.np.carservice.domain.Part;
import rs.ac.bg.np.carservice.domain.Suplier;
import rs.ac.bg.np.carservice.repository.PartRepository;
import rs.ac.bg.np.carservice.repository.SuplierRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class PartServiceTest {
    @Mock
    private PartRepository partRepository;
    @InjectMocks private PartService partService;
    @Mock
    private SuplierRepository suplierRepository;
    @InjectMocks private SuplierService suplierService;
    @Test
    void getAllParts() {
        Suplier suplier1= new Suplier(1L,"Tifa","Adresa 1","0649284928");
        Suplier suplier2= new Suplier(2L,"Naultre","Adresa 12","06512048192");
        Part part1= new Part(1L,"Auspuh",1000,"Fiat","Evo",suplier1);
        Part part2= new Part(2L,"Auspuh",2000,"Renault","Megane",suplier2);
        List<Part> parts= new ArrayList<>();
        parts.add(part1);
        parts.add(part2);
        Mockito.when(partRepository.findAll()).thenReturn(parts);
        List<Part> result= partService.getAllParts();
        Mockito.verify(partRepository,Mockito.times(1)).findAll();
        assertEquals(result,parts);
    }

    @Test
    void addNewPart() throws Exception {
        Suplier suplier= new Suplier(1L,"Tifa","Adresa 1","0649284928");
        Part part= new Part(1L,"Auspuh",1000,"Fiat","Evo",suplier);
        Mockito.when(partRepository.save(part)).thenReturn(part);
        Part result= partService.addNewPart(part);
        Mockito.verify(partRepository,Mockito.times(1)).save(part);
        assertEquals(result,part);
    }
    @Test
    void addNewPartException() throws Exception {
        Suplier suplier= new Suplier(1L,"Tifa","Adresa 1","0649284928");
        Part part= new Part(2L,"Kocnice",3000,"Fiat","Evo",suplier);
        Mockito.when(partRepository.findByNameAndBrandAndModel(part.getName(),part.getBrand(),part.getModel())).thenReturn(Optional.of(part));

        assertThrows(Exception.class,()->{partService.addNewPart(part);});
        Mockito.verify(partRepository,Mockito.times(1)).findByNameAndBrandAndModel(part.getName(),part.getBrand(),part.getModel());

    }

    @Test
    void addSuplierForPart() throws Exception {
        Suplier suplier= new Suplier(1L,"Tifa","Adresa 1","0649284928");
        Part part= new Part(1L,"Auspuh",1000,"Fiat","Evo",suplier);

        Mockito.when(suplierRepository.findById(suplier.getSuplierId())).thenReturn(Optional.of(suplier));
        Mockito.when(partRepository.findById(part.getPartID())).thenReturn(Optional.of(part));

        List<Part> parts= partService.getAllParts();
        Part result= partService.addSuplierForPart(part.getPartID(),suplier.getSuplierId());

        Mockito.verify(partRepository,Mockito.times(1)).findById(part.getPartID());
        assertEquals(result,part);

    }
    @Test
    void addSuplierForPartExceptionPart() throws Exception {
        Suplier suplier= new Suplier(1L,"Tifa","Adresa 1","0649284928");
        Part part= new Part(1L,"Auspuh",1000,"Fiat","Evo",suplier);

        Mockito.when(suplierRepository.findById(suplier.getSuplierId())).thenReturn(Optional.of(suplier));
        Mockito.when(partRepository.findById(part.getPartID())).thenReturn(Optional.empty());

        assertThrows(Exception.class,()->{partService.addSuplierForPart(part.getPartID(),suplier.getSuplierId());});

    }
    @Test
    void addSuplierForPartExceptionSuplier() throws Exception {
        Suplier suplier= new Suplier(1L,"Tifa","Adresa 1","0649284928");
        Part part= new Part(1L,"Auspuh",1000,"Fiat","Evo",suplier);

        Mockito.when(suplierRepository.findById(suplier.getSuplierId())).thenReturn(Optional.empty());
        Mockito.when(partRepository.findById(part.getPartID())).thenReturn(Optional.of(part));

        assertThrows(Exception.class,()->{partService.addSuplierForPart(part.getPartID(),suplier.getSuplierId());});

    }
}