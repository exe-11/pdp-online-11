package uz.pdp.warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Measurement;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.MeasurementService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/measurement")
public class MeasurementController {

    private final MeasurementService measurementService;

    @PostMapping
    public Result add(@RequestBody Measurement measurement) {
        return measurementService.add(measurement);
    }

    @GetMapping
    public List<Measurement> getAll() {
        return measurementService.getAll();
    }

    @GetMapping("/{id}")
    public Measurement getOne(@PathVariable Integer id) {
        return measurementService.getOne(id);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody Measurement measurement) {
        return measurementService.edit(id, measurement);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return measurementService.delete(id);
    }

}
