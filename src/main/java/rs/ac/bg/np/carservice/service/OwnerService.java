package rs.ac.bg.np.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.np.carservice.domain.Owner;
import rs.ac.bg.np.carservice.repository.OwnerRepository;

import java.util.Optional;
/**
 * Sadrzi poslovnu logiku rada sa vlasnikom.
 * Klasa sluzi da manipulise i upravlja modelom i podacima vezanim sa vlasnikom.
 *
 * Omogucava pravljenje novog vlasnika i vracanje pojedinacnog vlasnika.
 * @author Luka
 */


@Service
public class OwnerService {
    /**
     * Broker baze podataka koji je posrednik ka tabeli owner.
     */
    @Autowired
    private OwnerRepository ownerRepository;

    /**
     * Metoda za perzistiranje vlasnika u sistem kroz trajno cuvanje.
     * Novi vlasnik se cuva u bazi podataka.
     * @param owner vlasnik koji se treba perzistirati.
     * @return perzistirani vlasnik.
     * @throws Exception ako vlasnik sa istim brojem telefona postoji u bazi podataka.
     */
    public Owner createOwner(Owner owner) throws Exception {
        Optional<Owner> optionalOwner= ownerRepository.findByPhoneNumber(owner.getPhoneNumber());
        if(optionalOwner.isPresent()){
            throw new Exception("Vlasnik vec postoji");
        }
        return ownerRepository.save(owner);
    }

    /**
     * Metoda koja vraca vlasnika sa odredjenim ID-jem iz baze podatka.
     * @param ownerId ID vlasnika koji se trazi.
     * @return vlasnik iz baze sa odredjenim ID-jem.
     * @throws Exception ako vlasnik sa odredjenim ID-jem ne postoji u bazi podataka.
     */
    public Owner getOneOwner(long ownerId) throws Exception{
        Optional<Owner> optionalOwner= ownerRepository.findById(ownerId);
        if(!optionalOwner.isPresent()){
            throw new Exception("Ne postoji sa datim ID-jem");
        }
        Owner owner= optionalOwner.get();
        return owner;
    }
}
