package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import uz.pdp.appwarehouse.entity.Warehouse;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.payload.WarehouseDto;
import uz.pdp.appwarehouse.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {
    final
    WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public List<Warehouse> getWarehouseList() {
        return warehouseRepository.findAll();
    }

    public Warehouse getWarehouseById(Integer id) {
        final Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (optionalWarehouse.isEmpty()) {
            return null;
        }
        return optionalWarehouse.get();
    }

    public Result editWarehouse(WarehouseDto warehouseDto, Integer id) {
        final Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (optionalWarehouse.isEmpty()) {
            return new Result("Warehouse not found", false);
        }
        final Warehouse warehouse = optionalWarehouse.get();
        warehouse.setName(warehouseDto.getName());
        warehouseRepository.save(warehouse);
        return new Result("Edited!", true);
    }

    public Result addWarehouse(WarehouseDto warehouseDto) {
        Warehouse warehouse = new Warehouse();

        final boolean existsByName = warehouseRepository.existsByName(warehouseDto.getName());
        if (existsByName) {
            return new Result("Warehouse already exists", false);
        }

        warehouse.setName(warehouseDto.getName());
        warehouseRepository.save(warehouse);
        return new Result("Warehouse saved", true);
    }

    public Result deleteWarehouse(Integer id) {
        warehouseRepository.deleteById(id);
        return new Result("Warehouse deleted", true);
    }
}
