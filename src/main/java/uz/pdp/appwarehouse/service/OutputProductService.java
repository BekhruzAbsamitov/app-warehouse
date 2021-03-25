package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.*;
import uz.pdp.appwarehouse.payload.InputProductDto;
import uz.pdp.appwarehouse.payload.OutputProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class OutputProductService {

    OutputProductRepository inputProductRepository;
    OutputRepository inputRepository;
    ProductRepository productRepository;

    public OutputProductService(OutputProductRepository inputProductRepository, OutputRepository inputRepository, ProductRepository productRepository) {
        this.inputProductRepository = inputProductRepository;
        this.inputRepository = inputRepository;
        this.productRepository = productRepository;
    }

    public Result put(Integer id, OutputProductDto inputProductDto) {
        final Optional<OutputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (optionalInputProduct.isEmpty()) {
            return new Result("Not found", false);
        }
        final OutputProduct inputProduct = optionalInputProduct.get();
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProduct.getPrice());
        final Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (optionalProduct.isEmpty()) {
            return new Result("Product not found", false);
        }
        inputProduct.setProduct(optionalProduct.get());

        final Optional<Output> optionalInput = inputRepository.findById(inputProductDto.getOutputId());
        if (optionalInput.isEmpty()) {
            return new Result("Input not found", false);
        }
        inputProduct.setOutput(optionalInput.get());

        inputProductRepository.save(inputProduct);
        return new Result("Edited!", true);
    }

    public Result delete(Integer id) {
        final boolean exists = inputProductRepository.existsById(id);
        if (exists) {
            inputProductRepository.deleteById(id);
            return new Result("Deleted", true);
        }
        return new Result("Not found", false);
    }

    public Result add(OutputProductDto inputProductDto) {
        OutputProduct inputProduct = new OutputProduct();
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProduct.getPrice());
        final Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (optionalProduct.isEmpty()) {
            return new Result("Product not found", false);
        }
        inputProduct.setProduct(optionalProduct.get());

        final Optional<Output> optionalInput = inputRepository.findById(inputProductDto.getOutputId());
        if (optionalInput.isEmpty()) {
            return new Result("Input not found", false);
        }
        inputProduct.setOutput(optionalInput.get());

        inputProductRepository.save(inputProduct);
        return new Result("Saved!", true);
    }

    public OutputProduct getById(Integer id) {
        final Optional<OutputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (optionalInputProduct.isEmpty()) {
            return null;
        }
        return optionalInputProduct.get();
    }

    public List<OutputProduct> get() {
        return inputProductRepository.findAll();
    }
}
