package uz.pdp.appwarehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Warehouse;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.payload.WarehouseDto;
import uz.pdp.appwarehouse.service.WarehouseService;

import java.util.List;

@RestController
public class WarehouseController {

    WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @DeleteMapping("/{id}")
    public Result deleteWarehouse(@PathVariable Integer id) {
        return warehouseService.deleteWarehouse(id);
    }

    @PutMapping("/{id}")
    public Result editWarehouse(@PathVariable Integer id, @RequestBody WarehouseDto warehouseDto) {
        return warehouseService.editWarehouse(warehouseDto, id);
    }

    @PostMapping
    public Result addWarehouse(@RequestBody WarehouseDto warehouseDto) {
        return warehouseService.addWarehouse(warehouseDto);
    }

    @GetMapping
    public List<Warehouse> getWarehouseList() {
        return warehouseService.getWarehouseList();
    }

    @GetMapping("/{id}")
    public Warehouse getWarehouseById(@PathVariable Integer id) {
        return warehouseService.getWarehouseById(id);
    }
}
