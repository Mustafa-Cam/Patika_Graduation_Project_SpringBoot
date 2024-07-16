package com.patika.adservice.Service;
import com.patika.adservice.client.packages.service.PackageService;
import com.patika.adservice.client.user.UserClient;
import com.patika.adservice.model.Ad;
import com.patika.adservice.model.User;
import com.patika.adservice.model.enums.AdStatus;
import com.patika.adservice.repository.AdRepository;
import com.patika.adservice.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.patika.adservice.Product.ProductData;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AdService {
    @Autowired
    private AdRepository adRepository;

    @Autowired
    private PackageService adPackageService;

    @Autowired
    private ProductData productData;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserClient userClient;

    @Transactional
    public Ad createAd(Ad ad, String userName, int ProductId) {

        if (!adPackageService.canUserPostAd(userName)) {
            throw new RuntimeException("User does not have a valid package to post an ad");
        }

        Optional<User> optionalUser = userClient.findByUsername(userName);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = optionalUser.get();
        ad.setUser(user);
        ad.setCreatedAt(LocalDateTime.now());
        ad.setUpdatedAt(LocalDateTime.now());
        ad.setExpiryDate(LocalDateTime.now().plusDays(30));
        ad.setStatus(AdStatus.IN_REVIEW);
        ad.setProduct(productData.getProductById(ProductId));
        adPackageService.decrementAdCount(userName);
        return adRepository.save(ad);
    }

    @Transactional
    public Ad updateAdStatus(Long adId, AdStatus status) {
        Ad ad = adRepository.findById(adId).orElseThrow(() -> new RuntimeException("Ad not found"));
        ad.setStatus(status);
        ad.setUpdatedAt(LocalDateTime.now());
        return adRepository.save(ad);
    }

    public List<Ad> findAdsByUserAndStatus(User user, AdStatus status) {
        return adRepository.findByUserAndStatus(user, status);
    }
    public String Test(){return "Test is successfully";}


    public Optional<Ad> findById(Long id) {
        return adRepository.findById(id);
    }

    public List<Ad> findAll() {
        return adRepository.findAll();
    }

    public void deleteById(Long id) {
        adRepository.deleteById(id);
    }

    public Ad save(Ad ad) {
        return adRepository.save(ad);
    }

    @Scheduled(fixedRate = 86400000) // 24 hours
    @Transactional
    public void setExpiredAdsToPassive() {
        List<Ad> ads = adRepository.findAll();
        LocalDateTime now = LocalDateTime.now();
        for (Ad ad : ads) {
            if (ad.getExpiryDate().isBefore(now) && ad.getStatus() == AdStatus.ACTIVE) {
                ad.setStatus(AdStatus.PASSIVE);
                adRepository.save(ad);
            }
        }
    }
}
