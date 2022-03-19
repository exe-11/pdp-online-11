package uz.pdp.warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Input;
import uz.pdp.warehouse.payload.InputDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.InputService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/input")
public class InputController {

    private final InputService inputService;

    @PostMapping
    public Result add(@RequestBody InputDto inputDto) {
        return inputService.add(inputDto);
    }

    @GetMapping
    public List<Input> getAll() {
        return inputService.getAll();
    }

    @GetMapping("/{id}")
    public Input getOne(@PathVariable Integer id) {
        return inputService.getOne(id);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody InputDto inputDto) {
        return inputService.edit(id, inputDto);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return inputService.delete(id);
    }

}
