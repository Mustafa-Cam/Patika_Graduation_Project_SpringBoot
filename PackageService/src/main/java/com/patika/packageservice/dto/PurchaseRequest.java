package com.patika.packageservice.dto;

import lombok.Data;

@Data
public class PurchaseRequest {
    private String userId;
    private int enteredNumber;
}
