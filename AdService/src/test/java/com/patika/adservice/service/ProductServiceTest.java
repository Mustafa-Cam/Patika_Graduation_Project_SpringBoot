package com.patika.adservice.service;

import com.patika.adservice.Service.ProductService;
import com.patika.adservice.model.Product;
import com.patika.adservice.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        Product product = new Product();
        when(productRepository.findAll()).thenReturn(Collections.singletonList(product));

        List<Product> products = productService.findAll();

        assertNotNull(products);
        assertEquals(1, products.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        Product product = new Product();
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Optional<Product> foundProduct = productService.findById(1L);

        assertTrue(foundProduct.isPresent());
        assertEquals(product, foundProduct.get());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    public void testUpdateProduct() {
        Product product = new Product();
        productService.updateProduct(product);

        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testDecrementStock() {
        Product product = new Product();
        product.setStock(20);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        productService.decrementStock(1L);

        assertEquals(10, product.getStock());
        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testDecrementStock_InsufficientStock() {
        Product product = new Product();
        product.setStock(5);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        productService.decrementStock(1L);

        assertEquals(0, product.getStock());
        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testDecrementStock_ProductNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        productService.decrementStock(1L);

        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, never()).save(any(Product.class));
    }
}
