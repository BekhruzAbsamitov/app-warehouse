package uz.pdp.appwarehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.InputProduct;
import uz.pdp.appwarehouse.entity.OutputProduct;
import uz.pdp.appwarehouse.payload.InputProductDto;
import uz.pdp.appwarehouse.payload.OutputProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.InputProductService;
import uz.pdp.appwarehouse.service.OutputProductService;

import java.util.List;

@RestController
@RequestMapping("/inputProduct")
public class OutputProductController {

    OutputProductService inputProductService;
    public OutputProductController(OutputProductService inputProductService) {
        this.inputProductService = inputProductService;
    }

    @PostMapping
    public Result add(@RequestBody OutputProductDto inputProductDto) {
        return inputProductService.add(inputProductDto);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody OutputProductDto inputProductDto) {
        return inputProductService.put(id, inputProductDto);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return inputProductService.delete(id);
    }

    @GetMapping
    public List<OutputProduct> get() {
        return inputProductService.get();
    }

    @GetMapping("/{id}")
    public OutputProduct getById(@PathVariable Integer id) {
        return inputProductService.getById(id);
    }
}
