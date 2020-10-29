package com.java.myrotiuk.service;

import com.java.myrotiuk.entity.Tour;
import com.java.myrotiuk.entity.TourPackage;
import com.java.myrotiuk.repository.TourPackageRepository;
import com.java.myrotiuk.repository.TourRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author Ivan_Myrotiuk
 */
@Service
@RequiredArgsConstructor
public class TourService {
    private final TourRepository tourRepository;
    private final TourPackageRepository tourPackageRepository;

    @Transactional(propagation = Propagation.MANDATORY)
    public Tour createTour(String title, String tourPackageName,
                           Map<String, String> details) {

        TourPackage tourPackage = tourPackageRepository.findByName(tourPackageName)
                .orElseThrow(() -> new IllegalArgumentException(String.format("There is no such tour package: %s", tourPackageName)));

        return tourRepository.save(Tour.builder()
                .title(title)
                .tourPackageName(tourPackage.getName())
                .tourPackageCode(tourPackage.getCode())
                .details(details)
                .build());
    }

    @Transactional(readOnly = true)
    public Long total() {
        return tourRepository.count();
    }
}
