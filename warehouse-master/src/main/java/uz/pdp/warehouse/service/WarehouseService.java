package uz.pdp.warehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Warehouse;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WarehouseService implements BaseService<Warehouse, Warehouse> {
    
    private final WarehouseRepository warehouseRepository;

    @Override
    public Result add(Warehouse warehouse) {
        if (warehouseRepository.existsByName(warehouse.getName()))
            return new Result("This warehouse already exists", false);
        warehouseRepository.save(warehouse);
        return new Result("Warehouse successfully added", true);
    }

    @Override
    public List<Warehouse> getAll() {
        return warehouseRepository.findAllByActiveIsTrue();
    }

    @Override
    public Warehouse getOne(Integer id) {
        return warehouseRepository.findByIdAndActiveIsTrue(id).orElse(null);
    }

    @Override
    public Result edit(Integer id, Warehouse warehouse) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findByIdAndActiveIsTrue(id);
        if(optionalWarehouse.isEmpty())
            return new Result("Warehouse not found", false);
        Warehouse editingWarehouse = optionalWarehouse.get();
        editingWarehouse.setName(warehouse.getName());
        warehouseRepository.save(editingWarehouse);

        return new Result("Warehouse successfully edited", true);
    }

    @Override
    public Result delete(Integer id) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findByIdAndActiveIsTrue(id);
        if(optionalWarehouse.isEmpty())
            return new Result("Warehouse not found", false);
        Warehouse deletingWarehouse = optionalWarehouse.get();
        deletingWarehouse.setActive(false);
        warehouseRepository.save(deletingWarehouse);

        return new Result("Warehouse successfully deleted", true);
    }
    
}
