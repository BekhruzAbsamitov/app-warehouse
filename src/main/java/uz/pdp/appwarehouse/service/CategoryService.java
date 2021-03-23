package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Category;
import uz.pdp.appwarehouse.payload.CategoryDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Result addCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        if (categoryDto.getParentId() != null) {
            final Optional<Category> optionalCategory = categoryRepository.findById(categoryDto.getParentId());
            if (optionalCategory.isEmpty()) {
                return new Result("Parent category is not exists", false);
            }
            category.setParentCategory(optionalCategory.get());
        }
        categoryRepository.save(category);
        return new Result("Saved!", true);
    }

    public Result editCategory(CategoryDto categoryDto, Integer id) {
        final Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            return new Result("Category not  found", false);
        }
        final Category category = optionalCategory.get();
        category.setName(categoryDto.getName());

        if (categoryDto.getParentId() != null) {
            final Optional<Category> byId = categoryRepository.findById(categoryDto.getParentId());
            if (byId.isEmpty()) {
                return new Result("Parent id not found", false);
            }
            category.setParentCategory(byId.get());

        }
        categoryRepository.save(category);
        return new Result("Category edited", false);
    }

    public Result deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
        return new Result("Category deleted", false);
    }

    public List<Category> getCategoryList() {
        return categoryRepository.findAll();
    }

    public Result getCategoryById(Integer id) {
        final Optional<Category> byId =
                categoryRepository.findById(id);
        if (byId.isEmpty()) {
            return new Result("category not found", false);
        }
        return new Result("Category deleted", true);
    }
}
