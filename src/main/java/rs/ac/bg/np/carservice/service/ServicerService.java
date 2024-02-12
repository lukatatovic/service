package rs.ac.bg.np.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.np.carservice.domain.Servicer;
import rs.ac.bg.np.carservice.repository.ServiceRepository;
import rs.ac.bg.np.carservice.repository.ServicerRepository;

import java.util.List;
import java.util.Optional;
/**
 * Sadrzi poslovnu logiku rada sa serviserom.
 * Klasa sluzi da manipulise i upravlja modelom i podacima vezanim sa serviserom.
 *
 * Omogucava vracanje svih servisera, pravljenje novog servisera i povezivanje servisa sa serviserom.
 * @author Luka
 */

@Service
public class ServicerService {
    /**
     * Broker baze podataka koji je posrednik ka tabeli service.
     */
    @Autowired
    private ServiceRepository serviceRepository;
    /**
     * Broker baze podataka koji je posrednik ka tabeli servicer.
     */

    @Autowired
    private ServicerRepository servicerRepository;
    /**
     * Metoda koja vraca listu svih servisera.
     * @return List<Servicer> lista svih servisera.
     */
    public List<Servicer> getAllServicers() {
        return servicerRepository.findAll();
    }
    /**
     * Metoda za perzistiranje servisera u sistem kroz trajno cuvanje.
     * Novi serviser se cuva u bazi podataka.
     * @param servicer serviser koji se treba perzistirati.
     * @return perzistirani automobil.
     * @throws Exception ako serviser sa istim imenom i prezimenom postoji u bazi podataka.
     */
    public Servicer addNewServicer(Servicer servicer) throws Exception{
        Optional<Servicer> optionalServicer= servicerRepository.findByNameAndSurname(servicer.getName(),servicer.getSurname());
        if(optionalServicer.isPresent()){
            throw new Exception("Serviser vec postoji");
        }
        servicerRepository.save(servicer);
        return servicer;
    }
    /**
     * Metoda koja vraca servisera zajedno sa servisom sa kojim je povezan.
     * @param serviceId ID servisa sa kojim ce se serviser povezati.
     * @param servicerID ID servisera.
     * @return servisera sa servisom.
     * @throws Exception ako serviser ili servis ne postoje u bazi podataka.
     */
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
