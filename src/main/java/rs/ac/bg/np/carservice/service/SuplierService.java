package rs.ac.bg.np.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.np.carservice.domain.Suplier;
import rs.ac.bg.np.carservice.repository.SuplierRepository;

import java.util.List;
import java.util.Optional;
/**
 * Sadrzi poslovnu logiku rada sa dobavljacem.
 * Klasa sluzi da manipulise i upravlja modelom i podacima vezanim sa dobavljacem.
 *
 * Omogucava vracanje svih dobavljaca, pravljenje novog dobavljaca i brisanje dobavljaca.
 * @author Luka
 */

@Service
public class SuplierService {
    /**
     * Broker baze podataka koji je posrednik ka tabeli suplier.
     */
    @Autowired
    private SuplierRepository suplierRepository;
    /**
     * Metoda koja vraca listu svih dobavljaca.
     * @return List<Suplier> lista svih dobavljaca.
     */
    public List<Suplier> getAll() {
        return suplierRepository.findAll();
    }
    /**
     * Metoda za perzistiranje dobavljaca u sistem kroz trajno cuvanje.
     * Novi dobavljac se cuva u bazi podataka.
     * @param suplier dobavljaca koji se treba perzistirati.
     * @return perzistirani dobavljac.
     * @throws Exception ako dobavljac sa istom adresom postoji u bazi podataka.
     */
    public Suplier addNewSuplier(Suplier suplier) throws Exception{
        Optional<Suplier> optionalSuplier= suplierRepository.findByAdress(suplier.getAdress());
        if(optionalSuplier.isPresent()){
            throw new Exception("Dobavljac sa datom adresom vec postoji");
        }
        return  suplierRepository.save(suplier);
    }

    /**
     * Metoda koja brise dobavljaca sa odredjenim ID-jem iz baze.
     * @param suplierId ID dobavljaca koji se brise.
     */
    public void deleteSuplier(long suplierId) {
        suplierRepository.deleteById(suplierId);
    }
}
