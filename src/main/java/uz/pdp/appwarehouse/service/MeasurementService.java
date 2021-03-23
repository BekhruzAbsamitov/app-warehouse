package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.entity.Product;
import uz.pdp.appwarehouse.payload.MeasurementDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.MeasurementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {

    MeasurementRepository measurementRepository;

    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    public Result addMeasurementService(Measurement measurement) {

        final boolean exists = measurementRepository.existsByName(measurement.getName());
        if (exists) {
            return new Result("Not exists!", false);
        }
        measurementRepository.save(measurement);

        return new Result("Saved!", true);
    }

    public List<Measurement> getProductList() {
        return measurementRepository.findAll();
    }

    public Measurement getMeasurementById(Integer id) {
        final Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (optionalMeasurement.isEmpty()) {
            return null;
        }
        return optionalMeasurement.get();
    }

    public Result editMeasurement(MeasurementDto measurementDto, Integer id) {
        final Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (optionalMeasurement.isEmpty()) {
            return new Result("Measurement not found", false);
        }
        final Measurement measurement = optionalMeasurement.get();
        measurement.setName(measurementDto.getName());
        measurementRepository.save(measurement);
        return new Result("Measurement saved", false);
    }

    public Result deleteMeasurement(Integer id) {
        measurementRepository.deleteById(id);
        return new Result("Measurement deleted", false);
    }
}
