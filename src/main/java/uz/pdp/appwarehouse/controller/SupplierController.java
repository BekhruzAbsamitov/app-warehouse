package uz.pdp.appwarehouse.controller;

import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appwarehouse.repository.SupplierRepository;

@RestController
public class SupplierController {

    SupplierRepository supplierRepository;

    public SupplierController(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }



}
