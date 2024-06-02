package com.fpt.edu.controller;

import com.fpt.edu.dto.ProductDTO;
import com.fpt.edu.entity.Product;
import com.fpt.edu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping("/create/newproduct")
    public ProductDTO createProduct( @RequestBody ProductDTO productDTO) {
        ProductDTO newProductDTO = productService.createProduct(productDTO) ;
        return newProductDTO;
    }
}
