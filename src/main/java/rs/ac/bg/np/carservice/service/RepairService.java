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
@Service
public class RepairService {
    @Autowired
    private RepairRepository repairRepository;
    @Autowired
    private ServicerRepository servicerRepository;
    @Autowired
    private CarRepository carRepository;
    public List<Repair> getAllRepairs() {
        return repairRepository.findAll();
    }

    public Repair getOneRepairs(long repairId)throws Exception {
        Optional<Repair> optionalRepair= repairRepository.findById(repairId);
        if(!optionalRepair.isPresent()){
            throw new Exception("Ne postoji popravka sa datim ID-jem");
        }
        return optionalRepair.get();
    }

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

    public Set<Repair> getAllRepairsFromServicer(long servicerId) throws Exception{
        Optional<Servicer> optionalServicer= servicerRepository.findById(servicerId);
        if(!optionalServicer.isPresent()){
            throw new Exception("Ne postoji serviser sa datim ID-jem");
        }
        Set<Repair> repairs= optionalServicer.get().getRepairs();
        return repairs;
    }

    public Set<Repair> getAllRepairsFromCar(long carId) throws Exception {
        Optional<Car> optionalCar= carRepository.findById(carId);
        if(!optionalCar.isPresent()){
            throw new Exception("Ne postoji automobil sa datim ID-jem");
        }
        Set<Repair> repairs= optionalCar.get().getRepairs();
        return repairs;
    }

    public Repair addNewRepair(Repair repair) throws Exception{
        Optional<Repair> optionalRepair= repairRepository.findById(repair.getRepairID());
        if(optionalRepair.isPresent()){
            throw new Exception("Vec postoji popravka sa datim ID-jem");
        }
        return repairRepository.save(repair);
    }
}
