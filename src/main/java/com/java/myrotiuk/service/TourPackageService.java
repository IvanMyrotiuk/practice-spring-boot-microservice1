package com.java.myrotiuk.service;

import com.java.myrotiuk.entity.TourPackage;
import com.java.myrotiuk.repository.TourPackageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * @author Ivan_Myrotiuk
 */
@Service
@RequiredArgsConstructor
public class TourPackageService {
    private final TourPackageRepository tourPackageRepository;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public TourPackage createTourPackage(String code, String name) {
        return tourPackageRepository.findById(code)
                .orElseGet(() -> tourPackageRepository.save(new TourPackage(code, name)));
    }

    @Transactional(readOnly = true)
    public List<TourPackage> lookup() {
        return tourPackageRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Long total() {
        return tourPackageRepository.count();
    }

}
