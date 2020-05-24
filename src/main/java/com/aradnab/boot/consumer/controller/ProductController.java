package com.aradnab.boot.consumer.controller;

import com.aradnab.boot.consumer.model.ProductModel;
import com.aradnab.boot.consumer.service.ProductService;
import com.aradnab.boot.consumer.service.service_controller.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ProductModel> create(@RequestBody ProductModel productModel) {

        return ResponseEntity.ok().body(null);
    }
}
