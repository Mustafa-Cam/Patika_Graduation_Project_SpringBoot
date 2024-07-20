//package com.patika.userservice.converter;
//
//import com.patika.adservice.dto.AdResponse;
//import com.patika.adservice.model.Ad;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class AdMapper {
//
//    public static AdResponse toDto(Ad ad) {
//        AdResponse dto = new AdResponse();
//        dto.setTitle(ad.getTitle());
//        dto.setDescription(ad.getDescription());
//        dto.setStatus(ad.getStatus());
//        dto.setCreatedAt(ad.getCreatedAt());
//        dto.setUpdatedAt(ad.getUpdatedAt());
//        dto.setExpiryDate(ad.getExpiryDate());
//        dto.setProduct(ad.getProduct());
//        return dto;
//    }
//
//    public static List<AdResponse> toDtoList(List<Ad> ads) {
//        return ads.stream().map(AdMapper::toDto).collect(Collectors.toList());
//    }
//}
