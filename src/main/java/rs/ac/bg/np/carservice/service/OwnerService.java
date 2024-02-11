package rs.ac.bg.np.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.np.carservice.domain.Owner;
import rs.ac.bg.np.carservice.repository.OwnerRepository;

import java.util.Optional;

@Service
public class OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;
    public Owner createOwner(Owner owner) throws Exception {
        Optional<Owner> optionalOwner= ownerRepository.findByPhoneNumber(owner.getPhoneNumber());
        if(optionalOwner.isPresent()){
            throw new Exception("Vlasnik vec postoji");
        }
        return ownerRepository.save(owner);
    }

    public Owner getOneOwner(long ownerId) throws Exception{
        Optional<Owner> optionalOwner= ownerRepository.findById(ownerId);
        if(!optionalOwner.isPresent()){
            throw new Exception("Ne postoji sa datim ID-jem");
        }
        Owner owner= optionalOwner.get();
        return owner;
    }
}
