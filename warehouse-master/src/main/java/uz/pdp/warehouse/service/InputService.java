package uz.pdp.warehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Currency;
import uz.pdp.warehouse.entity.Input;
import uz.pdp.warehouse.entity.Supplier;
import uz.pdp.warehouse.entity.Warehouse;
import uz.pdp.warehouse.payload.InputDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.CurrencyRepository;
import uz.pdp.warehouse.repository.InputRepository;
import uz.pdp.warehouse.repository.SupplierRepository;
import uz.pdp.warehouse.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InputService implements BaseService<InputDto, Input> {

    private final InputRepository inputRepository;
    private final WarehouseRepository warehouseRepository;
    private final CurrencyRepository currencyRepository;
    private final SupplierRepository supplierRepository;

    @Override
    public Result add(InputDto inputDto) {
        if(inputRepository.existsByCode(inputDto.getCode()))
            return new Result("This input already exists", false);

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findByIdAndActiveIsTrue(inputDto.getWarehouseId());
        if(optionalWarehouse.isEmpty())
            return new Result("Warehouse not found", false);

        Optional<Currency> optionalCurrency = currencyRepository.findByIdAndActiveIsTrue(inputDto.getCurrencyId());
        if(optionalCurrency.isEmpty())
            return new Result("Currency not found", false);

        Optional<Supplier> optionalSupplier = supplierRepository.findByIdAndActiveIsTrue(inputDto.getSupplierId());
        if(optionalSupplier.isEmpty())
            return new Result("Supplier not found", false);

        Input input = new Input();
        input.setDate(inputDto.getDate());
        input.setCode(inputDto.getCode());
        input.setFactureNumber(inputDto.getFactureNumber());
        input.setWarehouse(optionalWarehouse.get());
        input.setCurrency(optionalCurrency.get());
        input.setSupplier(optionalSupplier.get());
        inputRepository.save(input);

        return new Result("Input successfully added", true);
    }

    @Override
    public List<Input> getAll() {
        return inputRepository.findAll();
    }

    @Override
    public Input getOne(Integer id) {
        return inputRepository.findById(id).orElse(null);
    }

    @Override
    public Result edit(Integer id, InputDto inputDto) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        if(optionalInput.isEmpty())
            return new Result("Input not found", false);

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findByIdAndActiveIsTrue(inputDto.getWarehouseId());
        if(optionalWarehouse.isEmpty())
            return new Result("Warehouse not found", false);

        Optional<Currency> optionalCurrency = currencyRepository.findByIdAndActiveIsTrue(inputDto.getCurrencyId());
        if(optionalCurrency.isEmpty())
            return new Result("Currency not found", false);

        Optional<Supplier> optionalSupplier = supplierRepository.findByIdAndActiveIsTrue(inputDto.getSupplierId());
        if(optionalSupplier.isEmpty())
            return new Result("Supplier not found", false);

        Input editingInput = optionalInput.get();
        editingInput.setDate(inputDto.getDate());
        editingInput.setCode(inputDto.getCode());
        editingInput.setFactureNumber(inputDto.getFactureNumber());
        editingInput.setWarehouse(optionalWarehouse.get());
        editingInput.setCurrency(optionalCurrency.get());
        editingInput.setSupplier(optionalSupplier.get());
        inputRepository.save(editingInput);
        return new Result("Input successfully edited", true);
    }

    @Override
    public Result delete(Integer id) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        if(optionalInput.isEmpty())
            return new Result("Input not found", false);
        inputRepository.deleteById(id);
        return new Result("Input successfully deleted", true);
    }
}
