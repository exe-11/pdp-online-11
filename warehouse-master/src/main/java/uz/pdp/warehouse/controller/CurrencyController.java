package uz.pdp.warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Currency;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.CurrencyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/currency")
public class CurrencyController {
    
    private final CurrencyService currencyService;

    @PostMapping
    public Result add(@RequestBody Currency currency) {
        return currencyService.add(currency);
    }

    @GetMapping
    public List<Currency> getAll() {
        return currencyService.getAll();
    }

    @GetMapping("/{id}")
    public Currency getOne(@PathVariable Integer id) {
        return currencyService.getOne(id);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody Currency currency) {
        return currencyService.edit(id, currency);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return currencyService.delete(id);
    }
    
}
