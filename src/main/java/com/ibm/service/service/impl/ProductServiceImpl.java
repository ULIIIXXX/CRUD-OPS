package com.ibm.service.service.impl;

import com.ibm.service.domain.Product;
import com.ibm.service.model.ProductRequest;
import com.ibm.service.model.ProductResponseModel;
import com.ibm.service.repository.ProductRepository;
import com.ibm.service.service.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.modelmapper.convention.MatchingStrategies;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ProductServiceImpl implements IProductService {

    @Autowired
    ProductRepository productRepository;

    private final ModelMapper mapper = new ModelMapper();

    @Override
    public List<ProductResponseModel> findAll() {
        return productRepository.findAll()
                .stream()
                .map(product -> mapper.map(product, ProductResponseModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseModel getById(UUID productID) {
        Product product =findProductById(productID);
        return mapper.map(product, ProductResponseModel.class);
    }

    @Override
    public ProductResponseModel saveProduct(ProductRequest request) {
        Product product = Product.builder()
                .name(request.name())
                .price(request.price())
                .createAt(LocalDateTime.now())
                .build();

        try{
            productRepository.saveAndFlush(product);
            log.info("Product has been created {}",product.getId());
        }catch (Exception ex){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Internal server error"
            );
        }
        return mapper.map(product, ProductResponseModel.class);
    }

    @Override
    public String deleteProduct(UUID productId) {
        productRepository.deleteById(productId);
        return "Succesfully deleted [%s]".formatted(productId);
    }

    @Override
    public ProductResponseModel updateProduct(UUID productId, ProductRequest request) {
        log.info("Updating product {}",request);
        Product product = findProductById(productId);
        mapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setSkipNullEnabled(true);
        mapper.map(request,product);
        productRepository.saveAndFlush(product);
        return mapper.map(product,ProductResponseModel.class);
    }

    private Product findProductById(UUID productId){
        return productRepository.findById(productId)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,"El producto no fue encontrado [%s]"
                                        .formatted(productId)
                        )
                );
    }
}
