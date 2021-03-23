package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Input;
import uz.pdp.appwarehouse.entity.InputProduct;
import uz.pdp.appwarehouse.entity.Product;
import uz.pdp.appwarehouse.payload.InputProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.InputProductRepository;
import uz.pdp.appwarehouse.repository.InputRepository;
import uz.pdp.appwarehouse.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
@Service
public class InputProductService {

    InputProductRepository inputProductRepository;
    InputRepository inputRepository;
    ProductRepository productRepository;

    public InputProductService(InputProductRepository inputProductRepository, InputRepository inputRepository, ProductRepository productRepository) {
        this.inputProductRepository = inputProductRepository;
        this.inputRepository = inputRepository;
        this.productRepository = productRepository;
    }

    public Result put(Integer id, InputProductDto inputProductDto) {
        final Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (optionalInputProduct.isEmpty()) {
            return new Result("Not found", false);
        }
        final InputProduct inputProduct = optionalInputProduct.get();
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProduct.getPrice());
        final Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (optionalProduct.isEmpty()) {
            return new Result("Product not found", false);
        }
        inputProduct.setProduct(optionalProduct.get());

        final Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (optionalInput.isEmpty()) {
            return new Result("Input not found", false);
        }
        inputProduct.setInput(optionalInput.get());

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

    public Result add(InputProductDto inputProductDto) {
        InputProduct inputProduct = new InputProduct();
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProduct.getPrice());
        final Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (optionalProduct.isEmpty()) {
            return new Result("Product not found", false);
        }
        inputProduct.setProduct(optionalProduct.get());

        final Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (optionalInput.isEmpty()) {
            return new Result("Input not found", false);
        }
        inputProduct.setInput(optionalInput.get());

        inputProductRepository.save(inputProduct);
        return new Result("Saved!", true);
    }

    public InputProduct getById(Integer id) {
        final Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (optionalInputProduct.isEmpty()) {
            return null;
        }
        return optionalInputProduct.get();
    }

    public List<InputProduct> get() {
        return inputProductRepository.findAll();
    }
}
