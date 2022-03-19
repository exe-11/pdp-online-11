package uz.pdp.warehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Measurement;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.MeasurementRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MeasurementService implements BaseService<Measurement, Measurement> {

    private final MeasurementRepository measurementRepository;

    @Override
    public Result add(Measurement measurement) {
        if (measurementRepository.existsByName(measurement.getName()))
            return new Result("This measurement already exists", false);
        measurementRepository.save(measurement);
        return new Result("Measurement successfully added", true);
    }

    @Override
    public List<Measurement> getAll() {
        return measurementRepository.findAllByActiveIsTrue();
    }

    @Override
    public Measurement getOne(Integer id) {
        return measurementRepository.findByIdAndActiveIsTrue(id).orElse(null);
    }

    @Override
    public Result edit(Integer id, Measurement measurement) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findByIdAndActiveIsTrue(id);
        if(optionalMeasurement.isEmpty())
            return new Result("Measurement not found", false);
        Measurement editingMeasurement = optionalMeasurement.get();
        editingMeasurement.setName(measurement.getName());
        measurementRepository.save(editingMeasurement);
        
        return new Result("Measurement successfully edited", true);
    }

    @Override
    public Result delete(Integer id) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findByIdAndActiveIsTrue(id);
        if(optionalMeasurement.isEmpty())
            return new Result("Measurement not found", false);
        Measurement deletingMeasurement = optionalMeasurement.get();
        deletingMeasurement.setActive(false);
        measurementRepository.save(deletingMeasurement);

        return new Result("Measurement successfully deleted", true);
    }
}
