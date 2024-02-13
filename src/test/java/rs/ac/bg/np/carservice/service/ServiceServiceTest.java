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

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ServiceServiceTest {
    @Mock private ServiceRepository serviceRepository;
    @InjectMocks private ServiceService serviceService;
    @Test
    void createService() throws Exception {
        Service service= new Service(1L,"Servis 1","Adresa 1","0649139172",null);
        Servicer servicer1= new Servicer(1L,"Luka","Tatovic",service,null);
        Servicer servicer2= new Servicer(1L,"Luka","Tatovic",service,null);
        Set<Servicer> servicers= new HashSet<>();
        servicers.add(servicer1);
        servicers.add(servicer2);
        service.setServicers(servicers);
        Mockito.when(serviceRepository.save(service)).thenReturn(service);
        Service result= serviceService.createService(service);
        Mockito.verify(serviceRepository,Mockito.times(1)).save(service);
        assertEquals(result,service);

    }
    @Test
    void createServiceException() throws Exception {
        Service service= new Service(1L,"Servis 1","Adresa 1","0649139172",null);
        Servicer servicer1= new Servicer(1L,"Luka","Tatovic",service,null);
        Servicer servicer2= new Servicer(1L,"Luka","Tatovic",service,null);
        Set<Servicer> servicers= new HashSet<>();
        servicers.add(servicer1);
        servicers.add(servicer2);
        service.setServicers(servicers);
        Mockito.when(serviceRepository.findByName(service.getName())).thenReturn(Optional.of(service));
        assertThrows(Exception.class,()->{serviceService.createService(service);});
        Mockito.verify(serviceRepository,Mockito.times(1)).findByName(service.getName());


    }

    @Test
    void getOneService() throws Exception {
        Service service= new Service(1L,"Servis 1","Adresa 1","0649139172",null);
        Servicer servicer1= new Servicer(1L,"Luka","Tatovic",service,null);
        Servicer servicer2= new Servicer(1L,"Luka","Tatovic",service,null);
        Set<Servicer> servicers= new HashSet<>();
        servicers.add(servicer1);
        servicers.add(servicer2);
        service.setServicers(servicers);

        Mockito.when(serviceRepository.findById(service.getServiceID())).thenReturn(Optional.of(service));
        Service result= serviceService.getOneService(service.getServiceID());
        Mockito.verify(serviceRepository,Mockito.times(1)).findById(service.getServiceID());
        assertEquals(result,service);
    }
    @Test
    void getOneServiceException() throws Exception {
        Service service= new Service(1L,"Servis 1","Adresa 1","0649139172",null);
        Servicer servicer1= new Servicer(1L,"Luka","Tatovic",service,null);
        Servicer servicer2= new Servicer(1L,"Luka","Tatovic",service,null);
        Set<Servicer> servicers= new HashSet<>();
        servicers.add(servicer1);
        servicers.add(servicer2);
        service.setServicers(servicers);

        Mockito.when(serviceRepository.findById(service.getServiceID())).thenReturn(Optional.empty());
        assertThrows(Exception.class,()->{serviceService.getOneService(service.getServiceID());});
        Mockito.verify(serviceRepository,Mockito.times(1)).findById(service.getServiceID());

    }
}