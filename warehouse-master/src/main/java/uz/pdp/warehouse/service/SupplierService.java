package uz.pdp.warehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Supplier;
import uz.pdp.warehouse.entity.Supplier;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.SupplierRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SupplierService implements BaseService<Supplier, Supplier> {
    
    private final SupplierRepository supplierRepository;

    @Override
    public Result add(Supplier supplier) {
        if (supplierRepository.existsByPhoneNumber(supplier.getPhoneNumber()))
            return new Result("This supplier already exists", false);
        supplierRepository.save(supplier);
        return new Result("Supplier successfully added", true);
    }

    @Override
    public List<Supplier> getAll() {
        return supplierRepository.findAllByActiveIsTrue();
    }

    @Override
    public Supplier getOne(Integer id) {
        return supplierRepository.findByIdAndActiveIsTrue(id).orElse(null);
    }

    @Override
    public Result edit(Integer id, Supplier supplier) {
        Optional<Supplier> optionalSupplier = supplierRepository.findByIdAndActiveIsTrue(id);
        if(optionalSupplier.isEmpty())
            return new Result("Supplier not found", false);

        Supplier editingSupplier = optionalSupplier.get();
        editingSupplier.setName(supplier.getName());
        editingSupplier.setPhoneNumber(supplier.getPhoneNumber());
        supplierRepository.save(editingSupplier);

        return new Result("Supplier successfully edited", true);
    }

    @Override
    public Result delete(Integer id) {
        Optional<Supplier> optionalSupplier = supplierRepository.findByIdAndActiveIsTrue(id);
        if(optionalSupplier.isEmpty())
            return new Result("Supplier not found", false);
        Supplier deletingSupplier = optionalSupplier.get();
        deletingSupplier.setActive(false);
        supplierRepository.save(deletingSupplier);

        return new Result("Supplier successfully deleted", true);
    }
}
