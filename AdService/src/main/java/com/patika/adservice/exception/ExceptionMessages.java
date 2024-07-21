package com.patika.adservice.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionMessages {

    public static final String CUSTOMER_NOT_FOUND = "customer bulunamadı.";
    public static final String CUSTOMER_NOT_ACTIVE = "customer aktif değil.";
    public static final String EMAIL_ALREADY_EXIST = "bu email ile kayıtlı kullanıcı var";
}
