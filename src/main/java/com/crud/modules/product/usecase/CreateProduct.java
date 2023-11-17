package com.crud.modules.product.usecase;

import com.crud.modules.product.DTO.ProductRequest;
import com.crud.modules.product.DTO.ProductResponse;
import com.crud.modules.product.entity.Product;
import com.crud.modules.product.repository.ProductRepository;
import com.crud.utils.ProductConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateProduct {
  @Autowired
  ProductRepository repository;

  public ProductResponse execute(ProductRequest productRequest) {
    Product product = ProductConvert.toEntity(productRequest);
    return ProductConvert.toResponse(repository.save(product));
  }
}
