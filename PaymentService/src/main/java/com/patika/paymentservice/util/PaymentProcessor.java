package com.patika.paymentservice.util;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class PaymentProcessor {

    // Kullanıcıya gönderilecek random sayıyı oluşturur
    public int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(1000); // 0 ile 999 arası random bir sayı
    }

    // Kullanıcının girdiği sayıyı kontrol eder
    public boolean verifyUserInput(int userInput, int randomNumber) {
        return userInput == randomNumber;
    }

    // Ödeme işlemini simüle eder
    public boolean processPayment(int userInput, int randomNumber) {
        if (verifyUserInput(userInput, randomNumber)) {
            // Burada gerçek bir ödeme servisi çağrısı olabilir
            return true; // Ödeme başarılı
        } else {
            return false; // Ödeme başarısız
        }
    }
}

