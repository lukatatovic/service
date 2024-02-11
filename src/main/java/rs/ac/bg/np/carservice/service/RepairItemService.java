package rs.ac.bg.np.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.np.carservice.domain.Car;
import rs.ac.bg.np.carservice.domain.Part;
import rs.ac.bg.np.carservice.domain.Repair;
import rs.ac.bg.np.carservice.domain.RepaitItem;
import rs.ac.bg.np.carservice.repository.PartRepository;
import rs.ac.bg.np.carservice.repository.RepairItemRepository;
import rs.ac.bg.np.carservice.repository.RepairRepository;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class RepairItemService {
    @Autowired
    private RepairItemRepository repairItemRepository;
    @Autowired
    private RepairRepository repairRepository;
    @Autowired
    private PartRepository partRepository;
    public List<RepaitItem> getAllRepairItems() {
        return repairItemRepository.findAll();
    }

    public RepaitItem addNewRepairtItem(RepaitItem repaitItem, long partId) throws Exception{
       // Optional<RepaitItem> optionalRepaitItem= repairItemRepository.
       Optional<Part> optionalPart= partRepository.findById(partId);
       if(!optionalPart.isPresent()){
           throw new Exception("Ne postoji deo sa datim ID-jem");
       }
       Optional<RepaitItem> optionalRepaitItem= repairItemRepository.findByPart(optionalPart.get());
       if(optionalRepaitItem.isPresent()){
           throw new Exception("Vec postoji zadata stavka");
       }
       Part part=optionalPart.get();
       repaitItem.setPart(part);
       return repairItemRepository.save(repaitItem);

    }

    public RepaitItem addRepairForRepairItem(long repairitemId, long repairId) throws Exception{
        Optional<RepaitItem> optionalRepaitItem= repairItemRepository.findById(repairitemId);
        if(!optionalRepaitItem.isPresent()){
            throw new Exception("Ne postoji stavka sa datim ID-jem");
        }
        Optional<Repair> optionalRepair= repairRepository.findById(repairId);
        if(!optionalRepair.isPresent()){
            throw new Exception("Ne postoji popravka sa datim ID-jem");
        }
        RepaitItem repaitItem= optionalRepaitItem.get();
        Repair repair= optionalRepair.get();
        if(repair.getItems().contains(repaitItem)){
            throw new Exception("Stavka je vec u popravci");
        }
        if(!repair.getCar().getBrand().equals(repaitItem.getPart().getBrand())){
            throw new Exception("Deo ne moze da se stavi na auto druge marke");
        }
        if(!repair.getCar().getModel().equals(repaitItem.getPart().getModel())){
            throw new Exception("Deo ne moze da se stavi na drugi model automobila");
        }
        repaitItem.setRepair(repair);
        repair.getItems().add(repaitItem);
        int price= repaitItem.getPart().getPrice();
        int repairPrice= repair.getPrice();
        int newPrice = price+repairPrice;
        repair.setPrice(newPrice);
        repairRepository.save(repair);
        return repairItemRepository.save(repaitItem);
    }
}
