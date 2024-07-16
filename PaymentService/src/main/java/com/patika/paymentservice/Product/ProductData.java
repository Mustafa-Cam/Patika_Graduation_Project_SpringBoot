package com.patika.paymentservice.Product;

import com.patika.paymentservice.model.Product;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ProductData {

    private final List<Product> products = Arrays.asList(
            new Product("1", "Mountain Bike", "Mavi 16 jant, 21 vites", 299.99,500),
            new Product("2", "Laptop", "Intel i7, 16GB RAM, 512GB SSD", 999.99,500),
            new Product("3", "T-Shirt", "Beyaz, %100 pamuk, M beden", 19.99,500),
            new Product("4", "Smartphone", "6.5 inç ekran, 128GB hafıza", 699.99,500),
            new Product("5", "Refrigerator", "500L, No Frost, Beyaz", 1199.99,500),
            new Product("6", "Running Shoes", "Siyah, 42 numara, su geçirmez", 79.99,500),
            new Product("7", "Gaming Console", "1TB depolama, 4K destekli", 499.99,500),
            new Product("8", "Smart TV", "55 inç, 4K Ultra HD, Akıllı TV", 799.99,500),
            new Product("9", "Microwave Oven", "Solo mikrodalga, 23L", 149.99,500),
            new Product("10", "Washing Machine", "9kg kapasite, A+++ enerji sınıfı", 699.99,500),
            new Product("11", "Tablet", "10 inç ekran, 64GB hafıza", 329.99,500),
            new Product("12", "Blender", "1000W, 1.5L kapasiteli", 89.99,500),
            new Product("13", "Air Conditioner", "12000 BTU, Inverter", 1499.99,500),
            new Product("14", "Headphones", "Bluetooth, Gürültü Önleyici", 199.99,500),
            new Product("15", "Coffee Maker", "Filtre kahve makinesi, 1.2L", 69.99,500),
            new Product("16", "Sofa", "3 kişilik, kumaş döşeme, gri", 599.99,500),
            new Product("17", "Watch", "Analog, deri kayış, su geçirmez", 129.99,500),
            new Product("18", "Camera", "DSLR, 24MP, 18-55mm lens", 749.99,500),
            new Product("19", "Vacuum Cleaner", "Kuru ve ıslak, 1400W", 199.99,500),
            new Product("20", "Bookshelf", "Ahşap, 5 raflı, kahverengi", 149.99,500),
            new Product("21", "Smart Speaker", "WiFi ve Bluetooth, sesli asistan", 99.99,500),
            new Product("22", "Electric Scooter", "Katlanabilir, 25km menzil", 399.99,500),
            new Product("23", "Bed", "Çift kişilik, ahşap, beyaz", 799.99,500),
            new Product("24", "Printer", "Lazer yazıcı, WiFi destekli", 129.99,500),
            new Product("25", "Backpack", "Su geçirmez, 20L kapasiteli", 49.99,500),
            new Product("26", "Hair Dryer", "2200W, iyonik teknoloji", 29.99,500),
            new Product("27", "Office Chair", "Ergonomik, ayarlanabilir yükseklik", 199.99,500),
            new Product("28", "Electric Kettle", "1.7L, paslanmaz çelik", 39.99,500),
            new Product("29", "Smartwatch", "Kalp atış hızı ölçer, GPS", 149.99,500),
            new Product("30", "Wireless Earbuds", "Bluetooth 5.0, şarj kutusu", 59.99,500),
            new Product("31", "Desk", "Çalışma masası, ahşap, beyaz", 199.99,500),
            new Product("32", "Monitor", "27 inç, 144Hz, Full HD", 299.99,500),
            new Product("33", "Electric Toothbrush", "Şarj edilebilir, 3 modlu", 49.99,500),
            new Product("34", "Bicycle Helmet", "Ayarlanabilir, havalandırmalı", 39.99,500),
            new Product("35", "Fitness Tracker", "Adım sayar, kalori ölçer", 29.99,500),
            new Product("36", "Gaming Chair", "Ergonomik, ayarlanabilir", 249.99,500),
            new Product("37", "Action Camera", "4K, su geçirmez, WiFi", 199.99,500),
            new Product("38", "Treadmill", "Katlanabilir, LCD ekranlı", 499.99,500),
            new Product("39", "Electric Grill", "2200W, çıkarılabilir plakalar", 89.99,500),
            new Product("40", "Pressure Cooker", "6L, çok fonksiyonlu", 69.99,500),
            new Product("41", "Kindle", "E-kitap okuyucu, 8GB hafıza", 129.99,500),
            new Product("42", "Fan", "Ayaklı, 3 hız ayarı, uzaktan kumandalı", 49.99,500),
            new Product("43", "Projector", "Full HD, 3000 lümen", 299.99,500),
            new Product("44", "Water Purifier", "3 aşamalı, musluk altı", 99.99,500),
            new Product("45", "Electric Bike", "Katlanabilir, 50km menzil", 899.99,500),
            new Product("46", "Bluetooth Speaker", "Su geçirmez, taşınabilir", 59.99,500),
            new Product("47", "Electric Shaver", "Şarj edilebilir, 5 başlık", 79.99,500),
            new Product("48", "Camping Tent", "4 kişilik, su geçirmez", 129.99,500),
            new Product("49", "Massage Gun", "Derin doku masajı, taşınabilir", 99.99,500),
            new Product("50", "Smart Light Bulb", "Renk değiştirilebilir, WiFi", 19.99,500)
    );

    public List<Product> getProducts() {
        return products;
    }
}