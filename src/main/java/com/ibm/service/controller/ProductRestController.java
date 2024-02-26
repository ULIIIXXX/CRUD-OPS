package com.ibm.service.controller;

import com.ibm.service.model.ProductRequest;
import com.ibm.service.model.ProductResponseModel;
import com.ibm.service.service.impl.ProductServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/product")
public class ProductRestController {

    @Autowired
    ProductServiceImpl productService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponseModel> findAll(){
        return  productService.findAll();
    }

    @GetMapping(value = "/{productId}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ProductResponseModel findById(@PathVariable("productId") UUID productId){
        return productService.getById(productId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseModel saveProduct(@RequestBody ProductRequest productRequest){
        return productService.saveProduct(productRequest);
    }

    @DeleteMapping(value = "/{productId}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteById(@PathVariable("productId") UUID productId){
        return productService.deleteProduct(productId);
    }

    @PutMapping(value = "/{productId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ProductResponseModel updateProduct(@PathVariable("productId") UUID productId,@RequestBody ProductRequest request){
        return productService.updateProduct(productId,request);
    }




}
