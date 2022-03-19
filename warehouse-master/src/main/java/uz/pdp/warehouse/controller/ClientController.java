package uz.pdp.warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Client;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.ClientService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientController {
    
    private final ClientService clientService;

    @PostMapping
    public Result add(@RequestBody Client client) {
        return clientService.add(client);
    }

    @GetMapping
    public List<Client> getAll() {
        return clientService.getAll();
    }

    @GetMapping("/{id}")
    public Client getOne(@PathVariable Integer id) {
        return clientService.getOne(id);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody Client client) {
        return clientService.edit(id, client);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return clientService.delete(id);
    }
    
}
