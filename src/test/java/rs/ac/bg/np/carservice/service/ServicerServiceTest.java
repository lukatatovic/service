package rs.ac.bg.np.carservice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import rs.ac.bg.np.carservice.domain.Service;
import rs.ac.bg.np.carservice.domain.Servicer;
import rs.ac.bg.np.carservice.repository.ServiceRepository;
import rs.ac.bg.np.carservice.repository.ServicerRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ServicerServiceTest {
    @Mock private ServicerRepository servicerRepository;
    @InjectMocks private ServicerService servicerService;
    @Mock private ServiceRepository serviceRepository;
    @InjectMocks private ServiceService serviceService;
    @Test
    void getAllServicers() {
        Servicer servicer1= new Servicer(1L,"Luka","Tatovic",null,null);
        Servicer servicer2= new Servicer(2L,"Uros","Tatovic",null,null);
        Service service= new Service(1L,"Servis 1","Adresa 1","0659318321",new HashSet<>());
        servicer1.setService(service);
        servicer2.setService(service);
        List<Servicer> servicers= new ArrayList<>();
        servicers.add(servicer1);
        servicers.add(servicer2);
        Mockito.when(servicerRepository.findAll()).thenReturn(servicers);
        List<Servicer> result= servicerService.getAllServicers();
        Mockito.verify(servicerRepository,Mockito.times(1)).findAll();
        assertEquals(result,servicers);

    }

    @Test
    void addNewServicer() throws Exception {
        Servicer servicer= new Servicer(1L,"Luka","Tatovic",null,null);
        Service service= new Service(1L,"Servis 1","Adresa 1","0659318321",new HashSet<>());
        servicer.setService(service);

        Mockito.when(servicerRepository.save(servicer)).thenReturn(servicer);
        Servicer result= servicerService.addNewServicer(servicer);
        Mockito.verify(servicerRepository,Mockito.times(1)).save(servicer);
        assertEquals(result,servicer);
    }
    @Test
    void addNewServicerException() throws Exception {
        Servicer servicer= new Servicer(1L,"Luka","Tatovic",null,null);
        Service service= new Service(1L,"Servis 1","Adresa 1","0659318321",new HashSet<>());
        servicer.setService(service);

        Mockito.when(servicerRepository.findByNameAndSurname(servicer.getName(),servicer.getSurname())).thenReturn(Optional.of(servicer));
        assertThrows(Exception.class,()->{servicerService.addNewServicer(servicer);});
        Mockito.verify(servicerRepository,Mockito.times(1)).findByNameAndSurname(servicer.getName(),servicer.getSurname());

    }

    @Test
    void addServiceForServicer() throws Exception {
        Servicer servicer= new Servicer(1L,"Luka","Tatovic",null,null);
        Service service= new Service(1L,"Servis 1","Adresa 1","0659318321",new HashSet<>());

        Mockito.when(servicerRepository.findById(servicer.getServicerID())).thenReturn(Optional.of(servicer));
        Mockito.when(serviceRepository.findById(service.getServiceID())).thenReturn(Optional.of(service));
        List<Servicer> servicers=servicerService.getAllServicers();
        Servicer result= servicerService.addServiceForServicer(service.getServiceID(),servicer.getServicerID());
        Mockito.verify(servicerRepository,Mockito.times(1)).findById(servicer.getServicerID());
        assertEquals(result,servicer);
    }
    @Test
    void addServiceForServicerExceptionServicer() throws Exception {
        Servicer servicer= new Servicer(1L,"Luka","Tatovic",null,null);
        Service service= new Service(1L,"Servis 1","Adresa 1","0659318321",new HashSet<>());

        Mockito.when(servicerRepository.findById(servicer.getServicerID())).thenReturn(Optional.empty());
        Mockito.when(serviceRepository.findById(service.getServiceID())).thenReturn(Optional.of(service));
        assertThrows(Exception.class,()->{servicerService.addServiceForServicer(service.getServiceID(),servicer.getServicerID());});
    }
    @Test
    void addServiceForServicerExceptionService() throws Exception {
        Servicer servicer= new Servicer(1L,"Luka","Tatovic",null,null);
        Service service= new Service(1L,"Servis 1","Adresa 1","0659318321",new HashSet<>());

        Mockito.when(servicerRepository.findById(servicer.getServicerID())).thenReturn(Optional.of(servicer));
        Mockito.when(serviceRepository.findById(service.getServiceID())).thenReturn(Optional.empty());
        assertThrows(Exception.class,()->{servicerService.addServiceForServicer(service.getServiceID(),servicer.getServicerID());});
    }
}