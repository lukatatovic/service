package rs.ac.bg.np.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import rs.ac.bg.np.carservice.domain.Service;
import rs.ac.bg.np.carservice.domain.Servicer;
import rs.ac.bg.np.carservice.repository.ServiceRepository;

import java.util.Optional;
@org.springframework.stereotype.Service
public class ServiceService {
    @Autowired
    private ServiceRepository serviceRepository;
    public Service createService(Service service) throws Exception{
        Optional<Service> optionalService= serviceRepository.findByName(service.getName());
        if(optionalService.isPresent()){
            throw new Exception("Ovaj servis vec postoji");
        }
        return  serviceRepository.save(service);
    }

    public Service getOneService(long serviceId) throws Exception {
        Optional<Service> optionalService= serviceRepository.findById(serviceId);
        if(!optionalService.isPresent()){
            throw new Exception("Ne postoji servis sa datim ID-jem");
        }
        Service service= optionalService.get();
        return service;
    }
}
