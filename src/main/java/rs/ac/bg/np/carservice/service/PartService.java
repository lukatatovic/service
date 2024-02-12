package rs.ac.bg.np.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.np.carservice.domain.Part;
import rs.ac.bg.np.carservice.domain.Suplier;
import rs.ac.bg.np.carservice.repository.PartRepository;
import rs.ac.bg.np.carservice.repository.SuplierRepository;

import java.util.List;
import java.util.Optional;
/**
 * Sadrzi poslovnu logiku rada sa delom.
 * Klasa sluzi da manipulise i upravlja modelom i podacima vezanim sa delom.
 *
 * Omogucava vracanje svih delova, pravljenje novog dela i povezivanje dobavljaca sa delom.
 * @author Luka
 */

@Service
public class PartService {
    /**
     * Broker baze podataka koji je posrednik ka tabeli part.
     */
    @Autowired
    private PartRepository partRepository;
    /**
     * Broker baze podataka koji je posrednik ka tabeli suplier.
     */
    @Autowired
    private SuplierRepository suplierRepository;
    /**
     * Metoda koja vraca listu svih delova.
     * @return List<Part> lista svih delova.
     */
    public List<Part> getAllParts() {
        return partRepository.findAll();
    }
    /**
     * Metoda za perzistiranje dela u sistem kroz trajno cuvanje.
     * Novi deo se cuva u bazi podataka.
     * @param part deo koji se treba perzistirati.
     * @return perzistirani deo.
     * @throws Exception ako deo sa istim nazivom, markom i brendom postoji u bazi podataka.
     */
    public Part addNewPart(Part part) throws Exception{
        Optional<Part> optionalPart= partRepository.findByNameAndBrandAndModel(part.getName(),part.getBrand(),part.getModel());
        if(optionalPart.isPresent()){
            throw new Exception("Vec postoji zadati deo");

        }
        return partRepository.save(part);

    }
    /**
     * Metoda koja vraca deo zajedno sa dobavljacem sa kojim je povezan.
     * @param partId ID dela.
     * @param suplierId ID dobavljaca sa kojim ce se deo povezati.
     * @return deo sa dobavljacem.
     * @throws Exception ako deo ili dobavljac ne postoje u bazi podataka.
     */
    public Part addSuplierForPart(long partId, long suplierId) throws Exception{
        Optional<Part> optionalPart= partRepository.findById(partId);
        Optional<Suplier> optionalSuplier=suplierRepository.findById(suplierId);

        if(!optionalPart.isPresent()){
            throw new Exception("Deo ne postoji");
        }
        if(!optionalSuplier.isPresent()){
            throw new Exception("Dobavljac ne postoji");
        }

        Part part= optionalPart.get();
        Suplier suplier= optionalSuplier.get();
        part.setSuplier(suplier);
        partRepository.save(part);



        return part;
    }
}
