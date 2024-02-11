package rs.ac.bg.np.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.np.carservice.domain.Servicer;
import rs.ac.bg.np.carservice.repository.ServiceRepository;
import rs.ac.bg.np.carservice.repository.ServicerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ServicerService {
    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ServicerRepository servicerRepository;
    public List<Servicer> getAllServicers() {
        return servicerRepository.findAll();
    }

    public Servicer addNewServicer(Servicer servicer) throws Exception{
        Optional<Servicer> optionalServicer= servicerRepository.findByNameAndSurname(servicer.getName(),servicer.getSurname());
        if(optionalServicer.isPresent()){
            throw new Exception("Serviser vec postoji");
        }
        servicerRepository.save(servicer);
        return servicer;
    }

    public Servicer addServiceForServicer(long serviceId, long servicerID) throws Exception{
        Optional<rs.ac.bg.np.carservice.domain.Service> optionalService= serviceRepository.findById(serviceId);
        Optional<Servicer> optionalServicer= servicerRepository.findById(servicerID);
        if(!optionalService.isPresent()){
            throw new Exception("Servis ne postoji");
        }
        if(!optionalServicer.isPresent()){
            throw new Exception("Serviser ne postoji");
        }

        rs.ac.bg.np.carservice.domain.Service service= optionalService.get();
        Servicer servicer= optionalServicer.get();

        if(!service.equals(servicer.getService()) && servicer.getService()!=null){
            throw new Exception("Serviser vec radi u nekom servisu");
        }
        if(service.getServicers().contains(servicer)){
            throw new Exception("Serviser vec radi u servisu");
        }
        servicer.setService(service);
        service.getServicers().add(servicer);

        servicerRepository.save(servicer);
        serviceRepository.save(service);
        return servicer;
    }
}
