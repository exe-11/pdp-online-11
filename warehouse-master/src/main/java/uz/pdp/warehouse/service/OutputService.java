package uz.pdp.warehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.*;
import uz.pdp.warehouse.payload.OutputDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.*;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OutputService implements BaseService<OutputDto, Output> {

    private final OutputRepository outputRepository;
    private final WarehouseRepository warehouseRepository;
    private final CurrencyRepository currencyRepository;
    private final ClientRepository clientRepository;

    @Override
    public Result add(OutputDto outputDto) {
        if (outputRepository.existsByCode(outputDto.getCode()))
            return new Result("This output already exists", false);

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findByIdAndActiveIsTrue(outputDto.getWarehouseId());
        if (optionalWarehouse.isEmpty())
            return new Result("Warehouse not found", false);

        Optional<Currency> optionalCurrency = currencyRepository.findByIdAndActiveIsTrue(outputDto.getCurrencyId());
        if (optionalCurrency.isEmpty())
            return new Result("Currency not found", false);

        Optional<Client> optionalClient = clientRepository.findByIdAndActiveIsTrue(outputDto.getClientId());
        if (optionalClient.isEmpty())
            return new Result("Client not found", false);

        Output output = new Output();
        output.setDate(outputDto.getDate());
        output.setCode(outputDto.getCode());
        output.setFactureNumber(outputDto.getFactureNumber());
        output.setWarehouse(optionalWarehouse.get());
        output.setCurrency(optionalCurrency.get());
        output.setClient(optionalClient.get());
        outputRepository.save(output);

        return new Result("Output successfully added", true);
    }

    @Override
    public List<Output> getAll() {
        return outputRepository.findAll();
    }

    @Override
    public Output getOne(Integer id) {
        return outputRepository.findById(id).orElse(null);
    }

    @Override
    public Result edit(Integer id, OutputDto outputDto) {
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (optionalOutput.isEmpty())
            return new Result("Output not found", false);

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findByIdAndActiveIsTrue(outputDto.getWarehouseId());
        if (optionalWarehouse.isEmpty())
            return new Result("Warehouse not found", false);

        Optional<Currency> optionalCurrency = currencyRepository.findByIdAndActiveIsTrue(outputDto.getCurrencyId());
        if (optionalCurrency.isEmpty())
            return new Result("Currency not found", false);

        Optional<Client> optionalClient = clientRepository.findByIdAndActiveIsTrue(outputDto.getClientId());
        if (optionalClient.isEmpty())
            return new Result("Client not found", false);

        Output editingOutput = optionalOutput.get();
        editingOutput.setDate(outputDto.getDate());
        editingOutput.setCode(outputDto.getCode());
        editingOutput.setFactureNumber(outputDto.getFactureNumber());
        editingOutput.setWarehouse(optionalWarehouse.get());
        editingOutput.setCurrency(optionalCurrency.get());
        editingOutput.setClient(optionalClient.get());
        outputRepository.save(editingOutput);
        return new Result("Output successfully edited", true);
    }

    @Override
    public Result delete(Integer id) {
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (optionalOutput.isEmpty())
            return new Result("Output not found", false);
        outputRepository.deleteById(id);
        return new Result("Output successfully deleted", true);
    }

}
