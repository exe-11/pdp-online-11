package uz.pdp.warehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Attachment;
import uz.pdp.warehouse.entity.Category;
import uz.pdp.warehouse.entity.Measurement;
import uz.pdp.warehouse.entity.Product;
import uz.pdp.warehouse.payload.ProductDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.AttachmentRepository;
import uz.pdp.warehouse.repository.CategoryRepository;
import uz.pdp.warehouse.repository.MeasurementRepository;
import uz.pdp.warehouse.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService implements BaseService<ProductDto, Product> {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final MeasurementRepository measurementRepository;
    private final AttachmentRepository attachmentRepository;

    @Override
    public Result add(ProductDto productDto) {
        if(productRepository.existsByNameAndCategoryId(productDto.getName(), productDto.getCategoryId()))
            return new Result("This product already exists in this category", false);

        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if(optionalCategory.isEmpty())
            return new Result("Category not found", false);

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if(optionalAttachment.isEmpty())
            return new Result("Photo not found", false);

        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if(optionalMeasurement.isEmpty())
            return new Result("Measurement not found", false);

        Product product = new Product();
        product.setName(productDto.getName());
        product.setCode(UUID.randomUUID().toString());
        product.setCategory(optionalCategory.get());
        product.setPhoto(optionalAttachment.get());
        product.setMeasurement(optionalMeasurement.get());
        productRepository.save(product);

        return new Result("Product successfully added", true);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAllByActiveIsTrue();
    }

    @Override
    public Product getOne(Integer id) {
        return productRepository.findByIdAndActiveIsTrue(id).orElse(null);
    }

    @Override
    public Result edit(Integer id, ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if(optionalProduct.isEmpty())
            return new Result("Product not found", false);

        if(productRepository.existsByNameAndCategoryId(productDto.getName(), productDto.getCategoryId()))
            return new Result("This product already exists in this category", false);

        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if(optionalCategory.isEmpty())
            return new Result("Category not found", false);

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if(optionalAttachment.isEmpty())
            return new Result("Photo not found", false);

        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if(optionalMeasurement.isEmpty())
            return new Result("Measurement not found", false);

        Product editingProduct = optionalProduct.get();
        editingProduct.setName(productDto.getName());
        editingProduct.setCode(UUID.randomUUID().toString());
        editingProduct.setCategory(optionalCategory.get());
        editingProduct.setPhoto(optionalAttachment.get());
        editingProduct.setMeasurement(optionalMeasurement.get());
        productRepository.save(editingProduct);

        return new Result("Product successfully edited", true);
    }

    @Override
    public Result delete(Integer id) {
        Optional<Product> optionalProduct = productRepository.findByIdAndActiveIsTrue(id);
        if(optionalProduct.isEmpty())
            return new Result("Product not found", false);
        Product deletingProduct = optionalProduct.get();
        deletingProduct.setActive(false);
        productRepository.save(deletingProduct);

        return new Result("Product successfully deleted", true);
    }
}
