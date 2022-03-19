package uz.pdp.warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.InputProduct;
import uz.pdp.warehouse.payload.InputProductDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.InputProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/input-product")
public class InputProductController {
    
    private final InputProductService inputProductService;

    @PostMapping
    public Result add(@RequestBody InputProductDto inputProductDto) {
        return inputProductService.add(inputProductDto);
    }

    @GetMapping
    public List<InputProduct> getAll() {
        return inputProductService.getAll();
    }

    @GetMapping("/{id}")
    public InputProduct getOne(@PathVariable Integer id) {
        return inputProductService.getOne(id);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody InputProductDto inputProductDto) {
        return inputProductService.edit(id, inputProductDto);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return inputProductService.delete(id);
    }
    
}
