package com.patika.adservice.Product;

import com.patika.adservice.model.Product;
import com.patika.adservice.repository.ProductRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Getter
@Component
public class ProductData {

    @Autowired
    private ProductRepository productRepository;

    private final List<Product> products = Arrays.asList(
            new Product(0, "Mountain Bike", "Mavi 16 jant, 21 vites", 299.99,500),
            new Product(0, "Laptop", "Intel i7, 16GB RAM, 512GB SSD", 999.99,500),
            new Product(0, "T-Shirt", "Beyaz, %100 pamuk, M beden", 19.99,500),
            new Product(0, "Smartphone", "6.5 inç ekran, 128GB hafıza", 699.99,500),
            new Product(0, "Refrigerator", "500L, No Frost, Beyaz", 1199.99,500),
            new Product(0, "Running Shoes", "Siyah, 42 numara, su geçirmez", 79.99,500),
            new Product(0, "Gaming Console", "1TB depolama, 4K destekli", 499.99,500),
            new Product(0, "Smart TV", "55 inç, 4K Ultra HD, Akıllı TV", 799.99,500),
            new Product(0, "Microwave Oven", "Solo mikrodalga, 23L", 149.99,500),
            new Product(0, "Washing Machine", "9kg kapasite, A+++ enerji sınıfı", 699.99,500),
            new Product(0, "Tablet", "10 inç ekran, 64GB hafıza", 329.99,500),
            new Product(0, "Blender", "1000W, 1.5L kapasiteli", 89.99,500),
            new Product(0, "Air Conditioner", "12000 BTU, Inverter", 1499.99,500),
            new Product(0, "Headphones", "Bluetooth, Gürültü Önleyici", 199.99,500),
            new Product(0, "Coffee Maker", "Filtre kahve makinesi, 1.2L", 69.99,500),
            new Product(0, "Sofa", "3 kişilik, kumaş döşeme, gri", 599.99,500),
            new Product(0, "Watch", "Analog, deri kayış, su geçirmez", 129.99,500),
            new Product(0, "Camera", "DSLR, 24MP, 18-55mm lens", 749.99,500),
            new Product(0, "Vacuum Cleaner", "Kuru ve ıslak, 1400W", 199.99,500),
            new Product(0, "Bookshelf", "Ahşap, 5 raflı, kahverengi", 149.99,500),
            new Product(0, "Smart Speaker", "WiFi ve Bluetooth, sesli asistan", 99.99,500),
            new Product(0, "Electric Scooter", "Katlanabilir, 25km menzil", 399.99,500),
            new Product(0, "Bed", "Çift kişilik, ahşap, beyaz", 799.99,500),
            new Product(0, "Printer", "Lazer yazıcı, WiFi destekli", 129.99,500),
            new Product(0, "Backpack", "Su geçirmez, 20L kapasiteli", 49.99,500),
            new Product(0, "Hair Dryer", "2200W, iyonik teknoloji", 29.99,500),
            new Product(0, "Office Chair", "Ergonomik, ayarlanabilir yükseklik", 199.99,500),
            new Product(0, "Electric Kettle", "1.7L, paslanmaz çelik", 39.99,500),
            new Product(0, "Smartwatch", "Kalp atış hızı ölçer, GPS", 149.99,500),
            new Product(0, "Wireless Earbuds", "Bluetooth 5.0, şarj kutusu", 59.99,500),
            new Product(0, "Desk", "Çalışma masası, ahşap, beyaz", 199.99,500),
            new Product(0, "Monitor", "27 inç, 144Hz, Full HD", 299.99,500),
            new Product(0, "Electric Toothbrush", "Şarj edilebilir, 3 modlu", 49.99,500),
            new Product(0, "Bicycle Helmet", "Ayarlanabilir, havalandırmalı", 39.99,500),
            new Product(0, "Fitness Tracker", "Adım sayar, kalori ölçer", 29.99,500),
            new Product(0, "Gaming Chair", "Ergonomik, ayarlanabilir", 249.99,500),
            new Product(0, "Action Camera", "4K, su geçirmez, WiFi", 199.99,500),
            new Product(0, "Treadmill", "Katlanabilir, LCD ekranlı", 499.99,500),
            new Product(0, "Electric Grill", "2200W, çıkarılabilir plakalar", 89.99,500),
            new Product(0, "Pressure Cooker", "6L, çok fonksiyonlu", 69.99,500),
            new Product(0, "Kindle", "E-kitap okuyucu, 8GB hafıza", 129.99,500),
            new Product(0, "Fan", "Ayaklı, 3 hız ayarı, uzaktan kumandalı", 49.99,500),
            new Product(0, "Projector", "Full HD, 3000 lümen", 299.99,500),
            new Product(0, "Water Purifier", "3 aşamalı, musluk altı", 99.99,500),
            new Product(0, "Electric Bike", "Katlanabilir, 50km menzil", 899.99,500),
            new Product(0, "Bluetooth Speaker", "Su geçirmez, taşınabilir", 59.99,500),
            new Product(0, "Electric Shaver", "Şarj edilebilir, 5 başlık", 79.99,500),
            new Product(0, "Camping Tent", "4 kişilik, su geçirmez", 129.99,500),
            new Product(0, "Massage Gun", "Derin doku masajı, taşınabilir", 99.99,500),
            new Product(0, "Smart Light Bulb", "Renk değiştirilebilir, WiFi", 19.99,500));

//    public Optional<Product> getProductById(int id) {
//        return productRepository.findById(id);
//    }
}
