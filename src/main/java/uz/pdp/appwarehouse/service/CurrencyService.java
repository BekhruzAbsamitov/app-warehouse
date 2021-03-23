package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Currency;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.CurrencyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {

    CurrencyRepository currencyRepository;

    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public Result delete(Integer id) {
        currencyRepository.deleteById(id);
        return new Result("Deleted", true);
    }

    public Result edit(Integer id, Currency currency) {
        final Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (optionalCurrency.isEmpty()) {
            return new Result("Currency not found", false);
        }
        final Currency currency1 = optionalCurrency.get();
        currency1.setName(currency.getName());
        currencyRepository.save(currency1);
        return new Result("Edited", true);
    }

    public Result add(Currency currency) {
        currencyRepository.save(currency);
        return new Result("Currency saved", false);
    }

    public List<Currency> get() {
        return currencyRepository.findAll();
    }

    public Currency getById(Integer id) {
        final Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (optionalCurrency.isEmpty()) {
            return null;
        }
        return optionalCurrency.get();
    }
}
