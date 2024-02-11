package rs.ac.bg.np.carservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.np.carservice.domain.Servicer;
import rs.ac.bg.np.carservice.repository.ServicerRepository;
import rs.ac.bg.np.carservice.service.ServicerService;

import java.util.List;

@RestController
@RequestMapping("/api/servicer")
public class ServicerController {
    @Autowired
    private ServicerService servicerService;

    @GetMapping
    public List<Servicer> getAllServicers() {
        return servicerService.getAllServicers();
    }
    @PostMapping
    public Servicer addServicer(@RequestBody Servicer servicer) throws Exception{
        return servicerService.addNewServicer(servicer);
    }
    @PostMapping("/{serviceId}/addService/{servicerId}")
    public Servicer serviceForServicer(@PathVariable long serviceId, @PathVariable long servicerId)throws Exception{
        return servicerService.addServiceForServicer(serviceId,servicerId);
    }

}
