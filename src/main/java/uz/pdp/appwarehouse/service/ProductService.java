package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import uz.pdp.appwarehouse.entity.Attachment;
import uz.pdp.appwarehouse.entity.Category;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.entity.Product;
import uz.pdp.appwarehouse.payload.ProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.AttachmentRepository;
import uz.pdp.appwarehouse.repository.CategoryRepository;
import uz.pdp.appwarehouse.repository.MeasurementRepository;
import uz.pdp.appwarehouse.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    AttachmentRepository attachmentRepository;
    MeasurementRepository measurementRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository,
                          AttachmentRepository attachmentRepository, MeasurementRepository measurementRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.attachmentRepository = attachmentRepository;
        this.measurementRepository = measurementRepository;
    }

    public Result addProduct(ProductDto productDto) {
        final boolean b = productRepository.existsByNameAndCategoryId(productDto.getName(), productDto.getCategoryId());
        if (b) {
            return new Result("Product already exist", false);
        }

        Product product = new Product();
        product.setName(productDto.getName());
        product.setCode("1");

        final Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (optionalCategory.isEmpty()) {
            return new Result("Category not found", false);
        }

        final Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (optionalAttachment.isEmpty()) {
            return new Result("Photo not found", false);
        }

        final Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (optionalMeasurement.isEmpty()) {
            return new Result("Measurement not found", false);
        }

        product.setCode(product.getCode());
        product.setCategory(optionalCategory.get());
        product.setMeasurement(optionalMeasurement.get());
        product.setPhoto(optionalAttachment.get());
        productRepository.save(product);
        return new Result("Product saved!", true);
    }

    public Result editProduct(ProductDto productDto, Integer productId) {
        final Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            return new Result("Product not found", false);
        }
        final Product product = optionalProduct.get();

        final Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (optionalCategory.isEmpty()) {
            return new Result("category not found", false);
        }

        final Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (optionalAttachment.isEmpty()) {
            return new Result("Photo not found", false);
        }

        final Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (optionalMeasurement.isEmpty()) {
            return new Result("Measurement not found", false);
        }


        product.setName(productDto.getName());
        product.setCode(productDto.getCode());
        product.setMeasurement(optionalMeasurement.get());
        product.setPhoto(optionalAttachment.get());
        product.setCategory(optionalCategory.get());

        final Product save = productRepository.save(product);
        return new Result("Product edited", true);

    }

    public Result deleteProduct(Integer productId) {
        productRepository.deleteById(productId);
        return new Result("Product deleted", true);
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Integer id) {
        final Optional<Product> optionalProduct =
                productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            return null;
        }
        return optionalProduct.get();
    }
}