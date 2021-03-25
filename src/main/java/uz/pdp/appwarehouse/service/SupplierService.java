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

    public Result add(SupplierDto supplierDto) {
        Supplier supplier = new Supplier();

        final boolean phoneNumber = supplierRepository.existsByPhoneNumber(supplierDto.getPhoneNumber());
        if (phoneNumber) {
            return new Result("Phone Number already exists", false);
        }
        supplier.setPhoneNumber(supplierDto.getPhoneNumber());
        supplier.setName(supplierDto.getName());
        supplierRepository.save(supplier);
        return new Result("Saved", true);
    }

    public Result editSupplier(Integer id, SupplierDto supplierDto) {
        final Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isEmpty()) {
            return new Result("Supplier not found", false);
        }
        final Supplier supplier = optionalSupplier.get();
        final boolean phoneNumber = supplierRepository.existsByPhoneNumber(supplierDto.getPhoneNumber());
        if (phoneNumber) {
            return new Result("Phone Number already exists", false);
        }
        supplier.setPhoneNumber(supplierDto.getPhoneNumber());
        supplier.setName(supplierDto.getName());
        supplierRepository.save(supplier);
        return new Result("Edited", true);
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
