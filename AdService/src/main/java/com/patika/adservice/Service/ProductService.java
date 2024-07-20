package com.patika.adservice.Service;

import com.patika.adservice.model.Product;
import com.patika.adservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Optional<Product> findById(long id){
        return productRepository.findById(id);
    }

    public void updateProduct(Product product){
         productRepository.save(product);
    }

    public void decrementStock(long productId) {
        Optional<Product> optionalProduct = findById(productId);

        // Ürünün var olup olmadığını kontrol ediyoruz
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();

            // Stok miktarını 10 azaltıyoruz
            long newStock = product.getStock() - 10;

            // Stok miktarının negatif olmamasını sağlıyoruz
            if (newStock < 0) {
                newStock = 0;
            }

            product.setStock(newStock);

            // Değişiklikleri veritabanına kaydediyoruz
            updateProduct(product);
        } else {
            // Ürün bulunamadığında yapılacak işlemler
            System.out.println("Product not found with ID: " + productId);
        }
    }

}
