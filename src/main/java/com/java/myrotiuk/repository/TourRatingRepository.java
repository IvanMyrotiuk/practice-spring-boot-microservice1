package com.java.myrotiuk.repository;

import com.java.myrotiuk.entity.TourRating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TourRatingRepository extends JpaRepository<TourRating, Long> {

    List<TourRating> findByTourId(Long tourId);

    Optional<TourRating> findByTourIdAndCustomerId(Long tourId, Long customerId);

    Page<TourRating> findByTourId(Long tourId, Pageable pageable);
}
