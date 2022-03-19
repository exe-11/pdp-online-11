package uz.pdp.warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Supplier;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.SupplierService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/supplier")
public class SupplierController {
    
    private final SupplierService supplierService;

    @PostMapping
    public Result add(@RequestBody Supplier supplier) {
        return supplierService.add(supplier);
    }

    @GetMapping
    public List<Supplier> getAll() {
        return supplierService.getAll();
    }

    @GetMapping("/{id}")
    public Supplier getOne(@PathVariable Integer id) {
        return supplierService.getOne(id);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody Supplier supplier) {
        return supplierService.edit(id, supplier);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return supplierService.delete(id);
    }
    
}
