package uz.pdp.warehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Category;
import uz.pdp.warehouse.entity.Currency;
import uz.pdp.warehouse.payload.CategoryDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService implements BaseService<CategoryDto, Category> {

    private final CategoryRepository categoryRepository;

    @Override
    public Result add(CategoryDto categoryDto) {
        if(categoryRepository.existsByNameAndParentCategoryId(categoryDto.getName(), categoryDto.getParentCategoryId()))
            return new Result("This category already exists", false);

        Category category = new Category();
        category.setName(categoryDto.getName());

        if(categoryDto.getParentCategoryId() != null) {
            Optional<Category> optionalParentCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
            if(optionalParentCategory.isEmpty())
                return new Result("Parent Category not found", false);
            category.setParentCategory(optionalParentCategory.get());
        }

        categoryRepository.save(category);
        return new Result("Category successfully added", true);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAllByActiveIsTrue();
    }

    @Override
    public Category getOne(Integer id) {
        return categoryRepository.findByIdAndActiveIsTrue(id).orElse(null);
    }

    @Override
    public Result edit(Integer id, CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);

        if(optionalCategory.isEmpty())
            return new Result("Category not found", false);

        if(categoryRepository.existsByNameAndParentCategoryId(categoryDto.getName(), categoryDto.getParentCategoryId()))
            return new Result("This category already exists", false);

        Category editingCategory = optionalCategory.get();
        editingCategory.setName(categoryDto.getName());

        if(categoryDto.getParentCategoryId() != null) {
            Optional<Category> optionalParentCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
            if(optionalParentCategory.isEmpty())
                return new Result("Parent Category not found", false);
            editingCategory.setParentCategory(optionalParentCategory.get());
        }

        categoryRepository.save(editingCategory);

        return new Result("Category successfully edited", true);
    }

    @Override
    public Result delete(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findByIdAndActiveIsTrue(id);
        if(optionalCategory.isEmpty())
            return new Result("Category not found", false);
        Category deletingCategory = optionalCategory.get();
        deletingCategory.setActive(false);
        categoryRepository.save(deletingCategory);

        return new Result("Category successfully deleted", true);
    }
}
