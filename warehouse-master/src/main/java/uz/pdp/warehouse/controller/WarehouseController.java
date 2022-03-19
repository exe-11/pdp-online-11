package uz.pdp.warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Warehouse;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.WarehouseService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/warehouse")
public class WarehouseController {

    private final WarehouseService warehouseService;

    @PostMapping
    public Result add(@RequestBody Warehouse warehouse) {
        return warehouseService.add(warehouse);
    }

    @GetMapping
    public List<Warehouse> getAll() {
        return warehouseService.getAll();
    }

    @GetMapping("/{id}")
    public Warehouse getOne(@PathVariable Integer id) {
        return warehouseService.getOne(id);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody Warehouse warehouse) {
        return warehouseService.edit(id, warehouse);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return warehouseService.delete(id);
    }
    
}
