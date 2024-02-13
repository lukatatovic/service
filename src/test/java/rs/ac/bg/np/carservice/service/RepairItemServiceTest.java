package rs.ac.bg.np.carservice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import rs.ac.bg.np.carservice.domain.*;
import rs.ac.bg.np.carservice.repository.PartRepository;
import rs.ac.bg.np.carservice.repository.RepairItemRepository;
import rs.ac.bg.np.carservice.repository.RepairRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class RepairItemServiceTest {
    @Mock
    private RepairItemRepository repairItemRepository;
    @InjectMocks private RepairItemService repairItemService;
    @Mock private RepairRepository repairRepository;
    @InjectMocks private RepairService repairService;
    @Mock private PartRepository partRepository;
    @InjectMocks private PartService partService;
    @Test
    void getAllRepairItems() {
        Suplier suplier1= new Suplier(1L,"Tifa","Adresa 1","0649284928");
        Part part1= new Part(1L,"Auspuh",1000,"Fiat","Evo",suplier1);
        Part part2= new Part(2L,"Auspuh",2000,"Renault","Megane",suplier1);
        Repair repair= new Repair(1L,null,null,new Date(),3000,new HashSet<>());
        RepaitItem repaitItem1= new RepaitItem(1L,repair,part1);
        RepaitItem repaitItem2= new RepaitItem(1L,repair,part2);
        List<RepaitItem> repaitItems= new ArrayList<>();
        repaitItems.add(repaitItem1);
        repaitItems.add(repaitItem2);

        Mockito.when(repairItemRepository.findAll()).thenReturn(repaitItems);
        List<RepaitItem> result= repairItemService.getAllRepairItems();
        Mockito.verify(repairItemRepository,Mockito.times(1)).findAll();
        assertEquals(result,repaitItems);
    }

    @Test
    void addNewRepairtItem() throws Exception {
        Suplier suplier1= new Suplier(1L,"Tifa","Adresa 1","0649284928");
        Part part1= new Part(4L,"Auspuh",1000,"Fiat","Evo",suplier1);
        Repair repair= new Repair(1L,null,null,new Date(),3000,new HashSet<>());
        RepaitItem repaitItem1= new RepaitItem(1L,repair,part1);


        Mockito.when(repairItemRepository.save(repaitItem1)).thenReturn(repaitItem1);
        Mockito.when(partRepository.findById(part1.getPartID())).thenReturn(Optional.of(part1));
        RepaitItem result= repairItemService.addNewRepairtItem(repaitItem1,part1.getPartID());

        Mockito.verify(repairItemRepository,Mockito.times(1)).save(repaitItem1);
        Mockito.verify(partRepository,Mockito.times(1)).findById(part1.getPartID());
        assertEquals(result,repaitItem1);

    }
    @Test
    void addNewRepairtItemExceptionPart() throws Exception {
        Suplier suplier1= new Suplier(1L,"Tifa","Adresa 1","0649284928");
        Part part1= new Part(4L,"Auspuh",1000,"Fiat","Evo",suplier1);
        Repair repair= new Repair(1L,null,null,new Date(),3000,new HashSet<>());
        RepaitItem repaitItem1= new RepaitItem(1L,repair,part1);

        Mockito.when(partRepository.findById(part1.getPartID())).thenReturn(Optional.empty());
        assertThrows(Exception.class,()->{repairItemService.addNewRepairtItem(repaitItem1,part1.getPartID());});
    }

    @Test
    void addNewRepairtItemExceptionAlreadyExisting() throws Exception {
        Suplier suplier1= new Suplier(1L,"Tifa","Adresa 1","0649284928");
        Part part1= new Part(4L,"Auspuh",1000,"Fiat","Evo",suplier1);
        Repair repair= new Repair(1L,null,null,new Date(),3000,new HashSet<>());
        RepaitItem repaitItem1= new RepaitItem(1L,repair,part1);
        Set<RepaitItem> items= new HashSet<>();
        items.add(repaitItem1);
        repair.setItems(items);
        assertThrows(Exception.class,()->{repairItemService.addNewRepairtItem(repaitItem1,part1.getPartID());});

    }

    @Test
    void addRepairForRepairItem() throws Exception {
        Suplier suplier1= new Suplier(1L,"Tifa","Adresa 1","0649284928");
        Part part1= new Part(4L,"Auspuh",1000,"Fiat","Evo",suplier1);
        Repair repair= new Repair(1L,null,null,new Date(),3000,new HashSet<>());
        RepaitItem repaitItem1= new RepaitItem(1L,null,part1);
        Car car= new Car(1L,"Fiat","Evo","BG231EE","Dizel",80,null,new HashSet<>());
        repair.setCar(car);

        Mockito.when(repairItemRepository.findById(repaitItem1.getRepaiItemId())).thenReturn(Optional.of(repaitItem1));
        Mockito.when(repairRepository.findById(repair.getRepairID())).thenReturn(Optional.of(repair));

        RepaitItem result= repairItemService.addRepairForRepairItem(repaitItem1.getRepaiItemId(),repair.getRepairID());

        assertEquals(repairRepository.findById(repair.getRepairID()).get(),repair);
        Mockito.verify(repairRepository,Mockito.times(2)).findById(repair.getRepairID());
        Mockito.verify(repairItemRepository,Mockito.times(1)).findById(repaitItem1.getRepaiItemId());
        assertEquals(result,repaitItem1);

    }
    @Test
    void addRepairForRepairItemExceptionRepair() throws Exception {
        Suplier suplier1= new Suplier(1L,"Tifa","Adresa 1","0649284928");
        Part part1= new Part(4L,"Auspuh",1000,"Fiat","Evo",suplier1);
        Repair repair= new Repair(1L,null,null,new Date(),3000,new HashSet<>());
        RepaitItem repaitItem1= new RepaitItem(1L,null,part1);
        Car car= new Car(1L,"Fiat","Evo","BG231EE","Dizel",80,null,new HashSet<>());
        repair.setCar(car);
        Mockito.when(repairItemRepository.findById(repaitItem1.getRepaiItemId())).thenReturn(Optional.of(repaitItem1));
        Mockito.when(repairRepository.findById(repair.getRepairID())).thenReturn(Optional.empty());
        assertThrows(Exception.class,()->repairItemService.addRepairForRepairItem(repaitItem1.getRepaiItemId(),repair.getRepairID()));
    }
    @Test
    void addRepairForRepairItemExceptionRepairItem() throws Exception {
        Suplier suplier1= new Suplier(1L,"Tifa","Adresa 1","0649284928");
        Part part1= new Part(4L,"Auspuh",1000,"Fiat","Evo",suplier1);
        Repair repair= new Repair(1L,null,null,new Date(),3000,new HashSet<>());
        RepaitItem repaitItem1= new RepaitItem(1L,null,part1);
        Car car= new Car(1L,"Fiat","Evo","BG231EE","Dizel",80,null,new HashSet<>());
        repair.setCar(car);
        Mockito.when(repairItemRepository.findById(repaitItem1.getRepaiItemId())).thenReturn(Optional.empty());
        assertThrows(Exception.class,()->{repairItemService.addRepairForRepairItem(repaitItem1.getRepaiItemId(),repair.getRepairID());});
    }
    @Test
    void addRepairForRepairItemExceptionBrand() throws Exception {
        Suplier suplier1= new Suplier(1L,"Tifa","Adresa 1","0649284928");
        Part part1= new Part(4L,"Auspuh",1000,"Skoda","Evo",suplier1);
        Repair repair= new Repair(1L,null,null,new Date(),3000,new HashSet<>());
        RepaitItem repaitItem1= new RepaitItem(1L,null,part1);
        Car car= new Car(1L,"Fiat","Evo","BG231EE","Dizel",80,null,new HashSet<>());
        repair.setCar(car);

        Mockito.when(repairItemRepository.findById(repaitItem1.getRepaiItemId())).thenReturn(Optional.of(repaitItem1));
        Mockito.when(repairRepository.findById(repair.getRepairID())).thenReturn(Optional.of(repair));

        assertThrows(Exception.class,()->repairItemService.addRepairForRepairItem(repaitItem1.getRepaiItemId(),repair.getRepairID()));

    }
    @Test
    void addRepairForRepairItemExceptionModel() throws Exception {
        Suplier suplier1= new Suplier(1L,"Tifa","Adresa 1","0649284928");
        Part part1= new Part(4L,"Auspuh",1000,"Fiat","Punto",suplier1);
        Repair repair= new Repair(1L,null,null,new Date(),3000,new HashSet<>());
        RepaitItem repaitItem1= new RepaitItem(1L,null,part1);
        Car car= new Car(1L,"Fiat","Evo","BG231EE","Dizel",80,null,new HashSet<>());
        repair.setCar(car);

        Mockito.when(repairItemRepository.findById(repaitItem1.getRepaiItemId())).thenReturn(Optional.of(repaitItem1));
        Mockito.when(repairRepository.findById(repair.getRepairID())).thenReturn(Optional.of(repair));

        assertThrows(Exception.class,()->repairItemService.addRepairForRepairItem(repaitItem1.getRepaiItemId(),repair.getRepairID()));

    }
    @Test
    void addRepairForRepairItemExceptionAlreadyExcisting() throws Exception {
        Suplier suplier1= new Suplier(1L,"Tifa","Adresa 1","0649284928");
        Part part1= new Part(4L,"Auspuh",1000,"Fiat","Evo",suplier1);
        Repair repair= new Repair(1L,null,null,new Date(),3000,new HashSet<>());
        RepaitItem repaitItem1= new RepaitItem(1L,null,part1);
        Car car= new Car(1L,"Fiat","Evo","BG231EE","Dizel",80,null,new HashSet<>());
        repair.setCar(car);
        Set<RepaitItem> repaitItemSet= new HashSet<>();
        repaitItemSet.add(repaitItem1);
        repair.setItems(repaitItemSet);
        Mockito.when(repairItemRepository.findById(repaitItem1.getRepaiItemId())).thenReturn(Optional.of(repaitItem1));
        Mockito.when(repairRepository.findById(repair.getRepairID())).thenReturn(Optional.of(repair));

        assertThrows(Exception.class,()->repairItemService.addRepairForRepairItem(repaitItem1.getRepaiItemId(),repair.getRepairID()));

    }
}