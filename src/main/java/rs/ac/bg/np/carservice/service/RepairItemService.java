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
/**
 * Sadrzi poslovnu logiku rada sa stavkom popravke.
 * Klasa sluzi da manipulise i upravlja modelom i podacima vezanim sa stavkom popravke.
 *
 * Omogucava vracanje svih stavki popravke, pravljenje nove stavke popravke i povezivanje popravke sa stavkom popravke.
 * @author Luka
 */
@Service
public class RepairItemService {
    /**
     * Broker baze podataka koji je posrednik ka tabeli repait_item.
     */
    @Autowired
    private RepairItemRepository repairItemRepository;
    /**
     * Broker baze podataka koji je posrednik ka tabeli repair.
     */
    @Autowired
    private RepairRepository repairRepository;
    /**
     * Broker baze podataka koji je posrednik ka tabeli part.
     */
    @Autowired
    private PartRepository partRepository;
    /**
     * Metoda koja vraca listu svih stavki popravke.
     * @return List<RepaitItem> lista svih stavki popravke.
     */
    public List<RepaitItem> getAllRepairItems() {
        return repairItemRepository.findAll();
    }

    /**
     * Metoda za perzistiranje stavke popravke u sistem kroz trajno cuvanje.
     * Nova stavka popravke se cuva u bazi podataka.
     * @param repaitItem stavka popravke koja se treba perzistirati.
     * @param partId ID dela na koji se odnosi stavka popravke.
     * @return perzistirana stavka poprvake.
     * @throws Exception ako ne postoji deo sa odredjenim ID-jem ili vec postoji stavka popravke u bazi podataka.
     */
    public RepaitItem addNewRepairtItem(RepaitItem repaitItem, long partId) throws Exception{
       // Optional<RepaitItem> optionalRepaitItem= repairItemRepository.
       Optional<Part> optionalPart= partRepository.findById(partId);
       if(!optionalPart.isPresent()){
           throw new Exception("Ne postoji deo sa datim ID-jem");
       }
       Optional<RepaitItem> optionalRepaitItem= repairItemRepository.findByPart(optionalPart.get());

       Part part=optionalPart.get();
       repaitItem.setPart(part);
       return repairItemRepository.save(repaitItem);

    }

    /**
     * Metoda koja vraca stavku popravke zajedno sa popravkom sa kojom je povezana.
     * @param repairitemId ID stavke popravke
     * @param repairId ID stavke sa kojom ce se stavka popravke povezati.
     * @return stavka popravke sa popravkom
     * @throws Exception ako ne postoji stavka ili popravka sa odredjenim ID-jem, ako je stavka popravke vec navedena u popravci, ako deo stavke popravke ne moze da se stavi na automobil koji se popravja znog pogresne marke ili modela
     */
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
        repairItemRepository.save(repaitItem);
        return repaitItem;
    }
}
