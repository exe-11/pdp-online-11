package uz.pdp.warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Output;
import uz.pdp.warehouse.payload.OutputDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.OutputService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/output")
public class OutputController {
    
    private final OutputService outputService;

    @PostMapping
    public Result add(@RequestBody OutputDto outputDto) {
        return outputService.add(outputDto);
    }

    @GetMapping
    public List<Output> getAll() {
        return outputService.getAll();
    }

    @GetMapping("/{id}")
    public Output getOne(@PathVariable Integer id) {
        return outputService.getOne(id);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody OutputDto outputDto) {
        return outputService.edit(id, outputDto);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return outputService.delete(id);
    }
    
}
