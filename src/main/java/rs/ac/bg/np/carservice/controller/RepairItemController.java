package rs.ac.bg.np.carservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.np.carservice.domain.Car;
import rs.ac.bg.np.carservice.domain.RepaitItem;
import rs.ac.bg.np.carservice.service.CarService;
import rs.ac.bg.np.carservice.service.RepairItemService;

import java.util.List;

@RestController
@RequestMapping("/api/repairitem")
public class RepairItemController {
    @Autowired
    private RepairItemService repairItemService;

    @GetMapping
    public List<RepaitItem> getAllRepairItems()
    {
        return repairItemService.getAllRepairItems();
    }
    @PostMapping("/addFor/{partId}")
    public RepaitItem addNewRepairItem(@RequestBody RepaitItem repaitItem,@PathVariable long partId) throws Exception{
        return repairItemService.addNewRepairtItem(repaitItem,partId);
    }
    @PostMapping("/{repairitemId}/addRepair/{repairId}")
    public RepaitItem addRepairForRepairItem(@PathVariable long repairitemId, @PathVariable long repairId) throws Exception{
        return repairItemService.addRepairForRepairItem(repairitemId,repairId);
    }
}
