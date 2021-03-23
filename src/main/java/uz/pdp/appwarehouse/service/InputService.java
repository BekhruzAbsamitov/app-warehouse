package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Currency;
import uz.pdp.appwarehouse.entity.Input;
import uz.pdp.appwarehouse.entity.Supplier;
import uz.pdp.appwarehouse.entity.Warehouse;
import uz.pdp.appwarehouse.payload.InputDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.CurrencyRepository;
import uz.pdp.appwarehouse.repository.InputRepository;
import uz.pdp.appwarehouse.repository.SupplierRepository;
import uz.pdp.appwarehouse.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InputService {

    InputRepository inputRepository;
    CurrencyRepository currencyRepository;
    SupplierRepository supplierRepository;
    WarehouseRepository warehouseRepository;

    public InputService(InputRepository inputRepository) {
        this.inputRepository = inputRepository;
    }

    public Result delete(Integer id) {
        final boolean b = inputRepository.existsById(id);
        if (b) {
            inputRepository.deleteById(id);
            return new Result("Deleted", false);
        }
        return new Result("Not found", false);
    }

    public Result add(InputDto inputDto) {
        Input input = new Input();
        input.setDate(inputDto.getTimestamp());
        input.setCode(inputDto.getCode());

        final Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (optionalCurrency.isEmpty()) {
            return new Result("Currency not found", false);
        }
        input.setCurrency(optionalCurrency.get());

        final Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (optionalSupplier.isEmpty()) {
            return new Result("Supplier not found", false);
        }
        input.setSupplier(optionalSupplier.get());

        final Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if (optionalWarehouse.isEmpty()) {
            return new Result("Warehouse not found", false);
        }
        input.setWarehouse(optionalWarehouse.get());
        inputRepository.save(input);
        return new Result("Input saved", true);
    }

    public List<Input> get() {
        return inputRepository.findAll();
    }

    public Input getById(Integer id) {
        final Optional<Input> optionalInput = inputRepository.findById(id);
        if (optionalInput.isEmpty()) {
            return null;
        }
        return optionalInput.get();
    }
}
