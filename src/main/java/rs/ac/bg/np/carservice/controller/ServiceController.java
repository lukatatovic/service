package rs.ac.bg.np.carservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.np.carservice.domain.Service;
import rs.ac.bg.np.carservice.service.ServiceService;

@RestController
@RequestMapping("/api/service")
public class ServiceController {
    @Autowired
    private ServiceService serviceService;

    @PostMapping
    public Service createService(@RequestBody Service service) throws Exception{
        return serviceService.createService(service);
    }
    @GetMapping("/{serviceId}")
    public Service getOneService(@PathVariable long serviceId) throws Exception{
        return serviceService.getOneService(serviceId);
    }
}
