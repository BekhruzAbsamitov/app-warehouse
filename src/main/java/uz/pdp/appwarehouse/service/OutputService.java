package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.*;
import uz.pdp.appwarehouse.payload.InputDto;
import uz.pdp.appwarehouse.payload.OutputDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class OutputService {

    OutputRepository outputRepository;
    CurrencyRepository currencyRepository;
    ClientRepository clientRepository;
    WarehouseRepository warehouseRepository;

    public OutputService(OutputRepository outputRepository) {
        this.outputRepository = outputRepository;
    }

    public Result delete(Integer id) {
        final boolean b = outputRepository.existsById(id);
        if (b) {
            outputRepository.deleteById(id);
            return new Result("Deleted", false);
        }
        return new Result("Not found", false);
    }

    public Result add(OutputDto outputDto) {
        Output output = new Output();
        output.setDate(outputDto.getTimestamp());
        output.setCode(outputDto.getCode());

        final Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (optionalCurrency.isEmpty()) {
            return new Result("Currency not found", false);
        }
        output.setCurrency(optionalCurrency.get());

        final Optional<Client> optionalSupplier = clientRepository.findById(outputDto.getClientId());
        if (optionalSupplier.isEmpty()) {
            return new Result("Supplier not found", false);
        }
        output.setClient(optionalSupplier.get());

        final Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if (optionalWarehouse.isEmpty()) {
            return new Result("Warehouse not found", false);
        }
        output.setWarehouse(optionalWarehouse.get());
        outputRepository.save(output);
        return new Result("Input saved", true);
    }

    public List<Output> get() {
        return outputRepository.findAll();
    }

    public Output getById(Integer id) {
        final Optional<Output> optionalInput = outputRepository.findById(id);
        if (optionalInput.isEmpty()) {
            return null;
        }
        return optionalInput.get();
    }
}
