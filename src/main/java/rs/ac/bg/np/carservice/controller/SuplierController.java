package rs.ac.bg.np.carservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.np.carservice.domain.Suplier;
import rs.ac.bg.np.carservice.service.SuplierService;

import java.util.List;

@RestController
@RequestMapping("/api/suplier")
public class SuplierController {
    @Autowired
    private SuplierService suplierService;
    @GetMapping
    public List<Suplier> getAll(){
        return  suplierService.getAll();
    }
    @PostMapping
    public Suplier addNewSuplier(@RequestBody Suplier suplier) throws Exception{
        return suplierService.addNewSuplier(suplier);
    }

    @DeleteMapping("/{suplierId}")
    public void deleteSuplier(@PathVariable long suplierId){
        suplierService.deleteSuplier(suplierId);
    }

}
