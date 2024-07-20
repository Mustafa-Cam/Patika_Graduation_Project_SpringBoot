package com.patika.adservice.converter;

import com.patika.adservice.dto.AdResponse;
import com.patika.adservice.model.Ad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class AdMapper {

    public static AdResponse toDto(Ad ad) {
        AdResponse dto = new AdResponse();
        dto.setTitle(ad.getTitle());
        dto.setProductOwner(ad.getUser().getUsername());
        dto.setDescription(ad.getDescription());
        dto.setStatus(ad.getStatus());
        dto.setCreatedAt(ad.getCreatedAt());
        dto.setUpdatedAt(ad.getUpdatedAt());
        dto.setExpiryDate(ad.getExpiryDate());
        dto.setProduct(ad.getProduct());
        return dto;
    }

    public static List<AdResponse> toDtoList(List<Ad> ads) {
        return ads.stream().map(AdMapper::toDto).collect(Collectors.toList());
    }
}
