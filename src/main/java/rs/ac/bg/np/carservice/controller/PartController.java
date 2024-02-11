package rs.ac.bg.np.carservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.np.carservice.domain.Part;
import rs.ac.bg.np.carservice.domain.Servicer;
import rs.ac.bg.np.carservice.service.PartService;

import java.util.List;

@RestController
@RequestMapping("/api/part")
public class PartController {

    @Autowired
    private PartService partService;
    @GetMapping
    public List<Part> getAllParts() {
        return partService.getAllParts();
    }

    @PostMapping
    public Part addPart(@RequestBody Part part) throws Exception{
        return partService.addNewPart(part);
    }
    @PostMapping("/{partId}/addSuplier/{suplierId}")
    public Part suplierForPart(@PathVariable long partId,@PathVariable long suplierId) throws Exception{
        return partService.addSuplierForPart(partId,suplierId);
    }
}
