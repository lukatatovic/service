package rs.ac.bg.np.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.np.carservice.domain.Car;
import rs.ac.bg.np.carservice.domain.Repair;
import rs.ac.bg.np.carservice.domain.Servicer;
import rs.ac.bg.np.carservice.repository.CarRepository;
import rs.ac.bg.np.carservice.repository.RepairRepository;
import rs.ac.bg.np.carservice.repository.ServicerRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
/**
 * Sadrzi poslovnu logiku rada sa popravkom.
 * Klasa sluzi da manipulise i upravlja modelom i podacima vezanim sa popravkom.
 *
 * Omogucava vracanje svih popravki, pravljenje nove popravke, povezivanje servisera i automobila sa popravkom, vracanje svih popravki za servisera i vracanje svih popravki za automobil.
 * @author Luka
 */
@Service
public class RepairService {
    /**
     * Broker baze podataka koji je posrednik ka tabeli repair.
     */
    @Autowired
    private RepairRepository repairRepository;
    /**
     * Broker baze podataka koji je posrednik ka tabeli servicer.
     */
    @Autowired
    private ServicerRepository servicerRepository;
    /**
     * Broker baze podataka koji je posrednik ka tabeli car.
     */
    @Autowired
    private CarRepository carRepository;
    /**
     * Metoda koja vraca listu svih popravki.
     * @return List<Repair> lista svih popravki.
     */
    public List<Repair> getAllRepairs() {
        return repairRepository.findAll();
    }
    /**
     * Metoda koja vraca popravku sa odredjenim ID-jem iz baze podatka.
     * @param repairId ID popravke koja se trazi.
     * @return popravka iz baze sa odredjenim ID-jem.
     * @throws Exception ako popravka sa odredjenim ID-jem ne postoji u bazi podataka.
     */
    public Repair getOneRepairs(long repairId)throws Exception {
        Optional<Repair> optionalRepair= repairRepository.findById(repairId);
        if(!optionalRepair.isPresent()){
            throw new Exception("Ne postoji popravka sa datim ID-jem");
        }
        return optionalRepair.get();
    }

    /**
     * Metoda koja vraca popravku zajedno sa serviserom i automobilom sa kojima je povezana.
     * @param servicerId ID servisera sa kojim ce se popravka povezati.
     * @param repId ID popravke.
     * @param carId ID automobila sa kojim ce se popravka povezati
     * @return popravku sa serviserom i automobilom.
     * @throws Exception ako popravka ili serviser ili automobil ne postoje u bazi podataka.
     */
    public Repair servicerNewRepair(long servicerId, long repId, long carId)throws Exception {
        Optional<Servicer> optionalServicer= servicerRepository.findById(servicerId);
        if(!optionalServicer.isPresent()){
            throw new Exception("Ne postoji serviser sa datim ID-jem");
        }
        Optional<Repair> optionalRepair= repairRepository.findById(repId);
        if(!optionalRepair.isPresent()){
            throw new Exception("Ne postoji popravka sa datim ID-jem");
        }
        Optional<Car> optionalCar= carRepository.findById(carId);
        if(!optionalCar.isPresent()){
            throw new Exception("Ne postoji automobil sa datim ID-jem");
        }
        Servicer servicer=optionalServicer.get();
        Car car= optionalCar.get();

        Repair repair= optionalRepair.get();
        repair.setCar(car);
        repair.setServicer(servicer);
        servicer.getRepairs().add(repair);
        car.getRepairs().add(repair);
        servicerRepository.save(servicer);
        carRepository.save(car);
        return repairRepository.save(repair);
    }

    /**
     * Metoda koja vraca sve popravke koje je izvrsio serviser.
     * @param servicerId ID servisera koji je izvrsio popravke.
     * @return List<Repair> lista popravki koje je izvrsio serviser.
     * @throws Exception ako serviser ne postoji u bazi podataka.
     */
    public Set<Repair> getAllRepairsFromServicer(long servicerId) throws Exception{
        Optional<Servicer> optionalServicer= servicerRepository.findById(servicerId);
        if(!optionalServicer.isPresent()){
            throw new Exception("Ne postoji serviser sa datim ID-jem");
        }
        Set<Repair> repairs= optionalServicer.get().getRepairs();
        return repairs;
    }
    /**
     * Metoda koja vraca sve popravke koje su izvrsene na automobilu.
     * @param carId ID automobilu na kojem su izvrsene popravke.
     * @return List<Repair> lista popravki koje su izvrsene na automobilu.
     * @throws Exception ako automobil ne postoji u bazi podataka.
     */
    public Set<Repair> getAllRepairsFromCar(long carId) throws Exception {
        Optional<Car> optionalCar= carRepository.findById(carId);
        if(!optionalCar.isPresent()){
            throw new Exception("Ne postoji automobil sa datim ID-jem");
        }
        Set<Repair> repairs= optionalCar.get().getRepairs();
        return repairs;
    }
    /**
     * Metoda za perzistiranje popravke u sistem kroz trajno cuvanje.
     * Nova popravka se cuva u bazi podataka.
     * @param repair popravka koji se treba perzistirati.
     * @return perzistirana popravka.
     * @throws Exception ako popravka sa istim ID-jem postoji u bazi podataka.
     */
    public Repair addNewRepair(Repair repair) throws Exception{
        Optional<Repair> optionalRepair= repairRepository.findById(repair.getRepairID());
        if(optionalRepair.isPresent()){
            throw new Exception("Vec postoji popravka sa datim ID-jem");
        }
        return repairRepository.save(repair);
    }
}
