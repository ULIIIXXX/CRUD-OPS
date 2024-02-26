package com.ibm.service.service;

import com.ibm.service.model.ProductRequest;
import com.ibm.service.model.ProductResponseModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface IProductService {

    List<ProductResponseModel> findAll();

    ProductResponseModel getById(UUID productID);

    ProductResponseModel saveProduct(ProductRequest request);

    String deleteProduct(UUID productId);

    ProductResponseModel updateProduct(UUID productId,ProductRequest request);

}
