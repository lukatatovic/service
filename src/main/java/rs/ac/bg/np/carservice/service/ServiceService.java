package rs.ac.bg.np.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import rs.ac.bg.np.carservice.domain.Service;
import rs.ac.bg.np.carservice.domain.Servicer;
import rs.ac.bg.np.carservice.repository.ServiceRepository;

import java.util.Optional;
/**
 * Sadrzi poslovnu logiku rada sa servisom.
 * Klasa sluzi da manipulise i upravlja modelom i podacima vezanim sa servisom.
 *
 * Omogucava pravljenje novog servisa i vracanje pojedinacnog servisa.
 * @author Luka
 */
@org.springframework.stereotype.Service
public class ServiceService {
    /**
     * Broker baze podataka koji je posrednik ka tabeli service.
     */
    @Autowired
    private ServiceRepository serviceRepository;
    /**
     * Metoda za perzistiranje servisa u sistem kroz trajno cuvanje.
     * Novi servis se cuva u bazi podataka.
     * @param service servis koji se treba perzistirati.
     * @return perzistirani servis.
     * @throws Exception ako servis sa istim nazivom postoji u bazi podataka.
     */
    public Service createService(Service service) throws Exception{
        Optional<Service> optionalService= serviceRepository.findByName(service.getName());
        if(optionalService.isPresent()){
            throw new Exception("Ovaj servis vec postoji");
        }
        return  serviceRepository.save(service);
    }
    /**
     * Metoda koja vraca servis sa odredjenim ID-jem iz baze podatka.
     * @param serviceId ID servisa koji se trazi.
     * @return servis iz baze sa odredjenim ID-jem.
     * @throws Exception ako servis sa odredjenim ID-jem ne postoji u bazi podataka.
     */
    public Service getOneService(long serviceId) throws Exception {
        Optional<Service> optionalService= serviceRepository.findById(serviceId);
        if(!optionalService.isPresent()){
            throw new Exception("Ne postoji servis sa datim ID-jem");
        }
        Service service= optionalService.get();
        return service;
    }
}
