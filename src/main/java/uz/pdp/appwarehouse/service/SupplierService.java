package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import uz.pdp.appwarehouse.entity.Supplier;
import uz.pdp.appwarehouse.entity.User;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.payload.SupplierDto;
import uz.pdp.appwarehouse.repository.SupplierRepository;
import uz.pdp.appwarehouse.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public Result editSupplier(Integer id, SupplierDto supplierDto) {
        final Optional<Supplier> optionalSupplier = supplierRepository.findById(id);

    }

    public Result deleteSupplier(Integer id) {
        supplierRepository.deleteById(id);
        return new Result("Supplier deleted", true);
    }

    public Supplier getSupplierById(Integer id) {
        final Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isEmpty()) {
            return null;
        }
        return optionalSupplier.get();
    }

    public List<Supplier> getSupplierList() {
        return supplierRepository.findAll();
    }
}
