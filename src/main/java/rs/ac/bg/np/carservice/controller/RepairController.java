package rs.ac.bg.np.carservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.np.carservice.domain.Car;
import rs.ac.bg.np.carservice.domain.Repair;
import rs.ac.bg.np.carservice.domain.Servicer;
import rs.ac.bg.np.carservice.service.RepairService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/repair")
public class RepairController {

    @Autowired
    private RepairService repairService;
    @GetMapping
    public List<Repair> getAllRepairs(){
        return repairService.getAllRepairs();
    }
    @GetMapping("/{repairId}")
    public Repair getOneRepair(@PathVariable long repairId)throws Exception{
        return repairService.getOneRepairs(repairId);
    }
    @PostMapping
    public Repair addNewRepair(@RequestBody Repair repair) throws Exception{
        return repairService.addNewRepair(repair);
    }
    @PostMapping("/{repId}/{servicerId}/for/{carId}")
    public Repair servicerNewRepair(@PathVariable long servicerId, @PathVariable long repId, @PathVariable long carId) throws Exception{
        return repairService.servicerNewRepair(servicerId,repId,carId);
    }

    @GetMapping("/servicer/{servicerId}")
    public Set<Repair> getAllRepairsFromServicer(@PathVariable long servicerId) throws Exception{
        return  repairService.getAllRepairsFromServicer(servicerId);
    }
    @GetMapping("/car/{carId}")
    public Set<Repair> getAllRepairsFromCar(@PathVariable long carId) throws Exception{
        return  repairService.getAllRepairsFromCar(carId);
    }


}
