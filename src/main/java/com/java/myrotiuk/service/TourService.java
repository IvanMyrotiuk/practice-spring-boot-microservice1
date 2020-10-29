package com.java.myrotiuk.service;

import com.java.myrotiuk.entity.Difficulty;
import com.java.myrotiuk.entity.Region;
import com.java.myrotiuk.entity.Tour;
import com.java.myrotiuk.entity.TourPackage;
import com.java.myrotiuk.repository.TourPackageRepository;
import com.java.myrotiuk.repository.TourRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Ivan_Myrotiuk
 */
@Service
@RequiredArgsConstructor
public class TourService {
    private final TourRepository tourRepository;
    private final TourPackageRepository tourPackageRepository;

    @Transactional
    public Tour createTour(String title, String description, String blurb, Double price,
                           String duration, String bullets, String keywords, String tourPackageName,
                           Difficulty difficulty, Region region) {

        TourPackage tourPackage = tourPackageRepository.findByName(tourPackageName)
                .orElseThrow(() -> new IllegalArgumentException(String.format("There is no such tour package: %s", tourPackageName)));

        return tourRepository.save(Tour.builder()
                .title(title)
                .description(description)
                .blurb(blurb)
                .price(price)
                .duration(duration)
                .bullets(bullets)
                .keywords(keywords)
                .tourPackage(tourPackage)
                .difficulty(difficulty)
                .region(region)
                .build());
    }

    @Transactional(readOnly = true)
    public Long total() {
        return tourRepository.count();
    }
}
