package uz.pdp.warehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.*;
import uz.pdp.warehouse.entity.OutputProduct;
import uz.pdp.warehouse.payload.OutputProductDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.OutputProductRepository;
import uz.pdp.warehouse.repository.OutputRepository;
import uz.pdp.warehouse.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OutputProductService implements BaseService<OutputProductDto, OutputProduct> {

    private final OutputProductRepository outputProductRepository;
    private final OutputRepository outputRepository;
    private final ProductRepository productRepository;

    @Override
    public Result add(OutputProductDto outputProductDto) {
        Optional<Product> optionalProduct = productRepository.findByIdAndActiveIsTrue(outputProductDto.getProductId());
        if(optionalProduct.isEmpty())
            return new Result("Product not found", false);

        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if(optionalOutput.isEmpty())
            return new Result("Output not found", false);

        OutputProduct outputProduct = new OutputProduct();
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProduct.setOutput(optionalOutput.get());
        outputProductRepository.save(outputProduct);

        return new Result("Product successfully added", true);
    }

    @Override
    public List<OutputProduct> getAll() {
        return outputProductRepository.findAll();
    }

    @Override
    public OutputProduct getOne(Integer id) {
        return outputProductRepository.findById(id).orElse(null);
    }

    @Override
    public Result edit(Integer id, OutputProductDto outputProductDto) {
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if(optionalOutputProduct.isEmpty())
            return new Result("Product not found", false);

        Optional<Product> optionalProduct = productRepository.findByIdAndActiveIsTrue(outputProductDto.getProductId());
        if(optionalProduct.isEmpty())
            return new Result("Product not found", false);

        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if(optionalOutput.isEmpty())
            return new Result("Output not found", false);

        OutputProduct editingOutputProduct = optionalOutputProduct.get();
        editingOutputProduct.setProduct(optionalProduct.get());
        editingOutputProduct.setAmount(outputProductDto.getAmount());
        editingOutputProduct.setPrice(outputProductDto.getPrice());
        editingOutputProduct.setOutput(optionalOutput.get());
        outputProductRepository.save(editingOutputProduct);

        return new Result("Product successfully edited", true);
    }

    @Override
    public Result delete(Integer id) {
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if(optionalOutputProduct.isEmpty())
            return new Result("Product not found", false);
        outputProductRepository.deleteById(id);
        return new Result("Product successfully deleted", true);
    }
    
}
