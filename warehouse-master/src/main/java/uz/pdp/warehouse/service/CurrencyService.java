package uz.pdp.warehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Currency;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.CurrencyRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CurrencyService implements BaseService<Currency, Currency> {
    
    private final CurrencyRepository currencyRepository;

    @Override
    public Result add(Currency currency) {
        if (currencyRepository.existsByName(currency.getName()))
            return new Result("This currency already exists", false);
        currencyRepository.save(currency);
        return new Result("Currency successfully added", true);
    }

    @Override
    public List<Currency> getAll() {
        return currencyRepository.findAllByActiveIsTrue();
    }

    @Override
    public Currency getOne(Integer id) {
        return currencyRepository.findByIdAndActiveIsTrue(id).orElse(null);
    }

    @Override
    public Result edit(Integer id, Currency currency) {
        Optional<Currency> optionalCurrency = currencyRepository.findByIdAndActiveIsTrue(id);
        if(optionalCurrency.isEmpty())
            return new Result("Currency not found", false);
        Currency editingCurrency = optionalCurrency.get();
        editingCurrency.setName(currency.getName());
        currencyRepository.save(editingCurrency);

        return new Result("Currency successfully edited", true);
    }

    @Override
    public Result delete(Integer id) {
        Optional<Currency> optionalCurrency = currencyRepository.findByIdAndActiveIsTrue(id);
        if(optionalCurrency.isEmpty())
            return new Result("Currency not found", false);
        Currency deletingCurrency = optionalCurrency.get();
        deletingCurrency.setActive(false);
        currencyRepository.save(deletingCurrency);

        return new Result("Currency successfully deleted", true);
    }
    
}
