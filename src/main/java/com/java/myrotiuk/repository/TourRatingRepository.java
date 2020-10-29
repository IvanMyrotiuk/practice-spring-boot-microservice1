package com.java.myrotiuk.repository;

import com.java.myrotiuk.entity.TourRating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TourRatingRepository extends CrudRepository<TourRating, String> {

    List<TourRating> findByTourId(String tourId);

    Optional<TourRating> findByTourIdAndCustomerId(String tourId, Long customerId);

    Page<TourRating> findByTourId(String tourId, Pageable pageable);
}
