package rs.ac.bg.np.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.np.carservice.domain.Part;
import rs.ac.bg.np.carservice.domain.Suplier;
import rs.ac.bg.np.carservice.repository.PartRepository;
import rs.ac.bg.np.carservice.repository.SuplierRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PartService {
    @Autowired
    private PartRepository partRepository;
    @Autowired
    private SuplierRepository suplierRepository;
    public List<Part> getAllParts() {
        return partRepository.findAll();
    }

    public Part addNewPart(Part part) throws Exception{
        Optional<Part> optionalPart= partRepository.findByNameAndBrandAndModel(part.getName(),part.getBrand(),part.getModel());
        if(optionalPart.isPresent()){
            throw new Exception("Vec postoji zadati deo");

        }
        return partRepository.save(part);

    }

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
