package uz.pdp.warehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Input;
import uz.pdp.warehouse.entity.InputProduct;
import uz.pdp.warehouse.entity.Product;
import uz.pdp.warehouse.payload.InputProductDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.InputProductRepository;
import uz.pdp.warehouse.repository.InputRepository;
import uz.pdp.warehouse.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InputProductService implements BaseService<InputProductDto, InputProduct> {

    private final InputProductRepository inputProductRepository;
    private final InputRepository inputRepository;
    private final ProductRepository productRepository;

    @Override
    public Result add(InputProductDto inputProductDto) {
        Optional<Product> optionalProduct = productRepository.findByIdAndActiveIsTrue(inputProductDto.getProductId());
        if(optionalProduct.isEmpty())
            return new Result("Product not found", false);

        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if(optionalInput.isEmpty())
            return new Result("Input not found", false);

        InputProduct inputProduct = new InputProduct();
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpireDate(inputProductDto.getExpireDate());
        inputProduct.setInput(optionalInput.get());
        inputProductRepository.save(inputProduct);

        return new Result("Product successfully added", true);
    }

    @Override
    public List<InputProduct> getAll() {
        return inputProductRepository.findAll();
    }

    @Override
    public InputProduct getOne(Integer id) {
        return inputProductRepository.findById(id).orElse(null);
    }

    @Override
    public Result edit(Integer id, InputProductDto inputProductDto) {
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if(optionalInputProduct.isEmpty())
            return new Result("Product not found", false);

        Optional<Product> optionalProduct = productRepository.findByIdAndActiveIsTrue(inputProductDto.getProductId());
        if(optionalProduct.isEmpty())
            return new Result("Product not found", false);

        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if(optionalInput.isEmpty())
            return new Result("Input not found", false);

        InputProduct editingInputProduct = optionalInputProduct.get();
        editingInputProduct.setProduct(optionalProduct.get());
        editingInputProduct.setAmount(inputProductDto.getAmount());
        editingInputProduct.setPrice(inputProductDto.getPrice());
        editingInputProduct.setExpireDate(inputProductDto.getExpireDate());
        editingInputProduct.setInput(optionalInput.get());
        inputProductRepository.save(editingInputProduct);

        return new Result("Product successfully edited", true);
    }

    @Override
    public Result delete(Integer id) {
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if(optionalInputProduct.isEmpty())
            return new Result("Product not found", false);
        inputProductRepository.deleteById(id);
        return new Result("Product successfully deleted", true);
    }
}
