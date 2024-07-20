package com.patika.adservice.config;


import com.patika.adservice.Product.ProductData;
import com.patika.adservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductData productData;

    @Bean
    public void initData() {
        if (productRepository.count() == 0) {
            productData.getProducts().forEach(product -> product.setId(0)); // ID'leri sıfırlayın
            productRepository.saveAll(productData.getProducts());
        }
    }
}
