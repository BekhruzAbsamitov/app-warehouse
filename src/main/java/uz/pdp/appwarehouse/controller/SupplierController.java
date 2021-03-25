package uz.pdp.appwarehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Supplier;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.payload.SupplierDto;
import uz.pdp.appwarehouse.repository.SupplierRepository;
import uz.pdp.appwarehouse.service.SupplierService;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    SupplierService service;

    public SupplierController(SupplierService service) {
        this.service = service;
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return service.deleteSupplier(id);
    }

    @GetMapping
    public List<Supplier> get() {
        return service.getSupplierList();
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody SupplierDto supplierDto) {
        return service.editSupplier(id, supplierDto);
    }

    @GetMapping("/{id}")
    public Supplier getById(@PathVariable Integer id) {
        return service.getSupplierById(id);
    }

    @PostMapping
    public Result add(@RequestBody SupplierDto supplierDto) {
        return service.add(supplierDto);
    }

}
