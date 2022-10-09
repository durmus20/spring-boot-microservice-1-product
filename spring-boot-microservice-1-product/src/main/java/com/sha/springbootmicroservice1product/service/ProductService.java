package com.sha.springbootmicroservice1product.service;

import com.sha.springbootmicroservice1product.model.Product;
import com.sha.springbootmicroservice1product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{

    private final ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product){

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id){

        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findAllProducts(){

        return productRepository.findAll();
    }
}
