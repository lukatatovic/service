package rs.ac.bg.np.carservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.np.carservice.domain.Owner;
import rs.ac.bg.np.carservice.service.OwnerService;

@RestController
@RequestMapping("/api/owner")
public class OwnerController {
    @Autowired
    private OwnerService ownerService;
    @PostMapping
    public Owner createOwner(@RequestBody Owner owner) throws Exception{
        return ownerService.createOwner(owner);
    }
    @GetMapping("/{ownerId}")
    public Owner getOneOwner(@PathVariable long ownerId) throws Exception{
        return ownerService.getOneOwner(ownerId);
    }

}
