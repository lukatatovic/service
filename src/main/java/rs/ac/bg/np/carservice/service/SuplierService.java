package rs.ac.bg.np.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.np.carservice.domain.Suplier;
import rs.ac.bg.np.carservice.repository.SuplierRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SuplierService {

    @Autowired
    private SuplierRepository suplierRepository;
    public List<Suplier> getAll() {
        return suplierRepository.findAll();
    }

    public Suplier addNewSuplier(Suplier suplier) throws Exception{
        Optional<Suplier> optionalSuplier= suplierRepository.findByAdress(suplier.getAdress());
        if(optionalSuplier.isPresent()){
            throw new Exception("Dobavljac sa datom adresom vec postoji");
        }
        return  suplierRepository.save(suplier);
    }

    public void deleteSuplier(long suplierId) {
        suplierRepository.deleteById(suplierId);
    }
}
