package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.payload.MeasurementDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.MeasurementService;

import java.util.List;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {
    MeasurementService measurementService;

    @Autowired
    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @PutMapping("/{id}")
    public Result editMeasurement(@RequestBody MeasurementDto measurementDto, @PathVariable Integer id) {
       return measurementService.editMeasurement(measurementDto, id);
    }

    @DeleteMapping("/{id}")
    public Result deleteMeasurement(@PathVariable Integer id) {
        return measurementService.deleteMeasurement(id);
    }

    @GetMapping("/{id}")
    public Measurement getMeasurementById(@PathVariable Integer id) {
        return measurementService.getMeasurementById(id);
    }

    @GetMapping
    public List<Measurement> getMeasurement() {
        return measurementService.getProductList();
    }

    @PostMapping
    public Result addMeasurementController(@RequestBody Measurement measurement) {
        return measurementService.addMeasurementService(measurement);
    }
}
