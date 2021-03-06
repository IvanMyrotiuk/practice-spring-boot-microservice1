package com.java.myrotiuk.service;

import com.java.myrotiuk.dto.RatingDto;
import com.java.myrotiuk.entity.Tour;
import com.java.myrotiuk.entity.TourRating;
import com.java.myrotiuk.repository.TourRatingRepository;
import com.java.myrotiuk.repository.TourRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TourRatingService {

    private final TourRatingRepository tourRatingRepository;
    private final TourRepository tourRepository;

    @Transactional
    public RatingDto createTourRating(Long tourId, RatingDto ratingDto) {
        Tour tour = verifyTour(tourId);
        TourRating tourRating = TourRating.builder()
                .comment(ratingDto.getComment())
                .customerId(ratingDto.getCustomerId())
                .score(ratingDto.getScore())
                .tour(tour)
                .build();
        return getRatingDto(tourRatingRepository.save(tourRating));
    }

    @Transactional
    public List<RatingDto> getAllTourRatings(Long tourId) {
        verifyTour(tourId);
        return tourRatingRepository.findByTourId(tourId).stream()
                .map(this::getRatingDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public Map<String, Double> getAverage(Long tourId) {
        verifyTour(tourId);
        return Map.of("avarage", tourRatingRepository.findByTourId(tourId).stream()
                .mapToInt(TourRating::getScore)
                .average()
                .orElseThrow(() -> new NoSuchElementException("Tour has no ratings"))
        );
    }

    //add mappers
    @Transactional
    public RatingDto updateTourRating(Long tourId, RatingDto ratingDto) {
        TourRating tourRating = verifyTourRating(tourId, ratingDto.getCustomerId());
        tourRating.setComment(ratingDto.getComment());
        tourRating.setScore(ratingDto.getScore());
        TourRating updated = tourRatingRepository.save(tourRating);
        return getRatingDto(updated);
    }

    @Transactional
    public RatingDto updateTourRatingWithPatch(Long tourId, RatingDto ratingDto) {
        TourRating tourRating = verifyTourRating(tourId, ratingDto.getCustomerId());
        if (ratingDto.getComment() != null) {
            tourRating.setComment(ratingDto.getComment());
        }
        if (ratingDto.getScore() != null) {
            tourRating.setScore(ratingDto.getScore());
        }
        return getRatingDto(tourRatingRepository.save(tourRating));
    }

    @Transactional
    public void deleteTourRating(Long tourId, Long customerId) {
        TourRating tourRating = verifyTourRating(tourId, customerId);
        tourRatingRepository.delete(tourRating);
    }

    @Transactional(readOnly = true)
    public Page<RatingDto> findByTourId(Long tourId, Pageable pageable) {
        Page<TourRating> pageByTourId = tourRatingRepository.findByTourId(tourId, pageable);

        return new PageImpl<>(pageByTourId.get()
                .map(this::getRatingDto).collect(Collectors.toList()),
                pageable,
                pageByTourId.getTotalElements());
    }

    private Tour verifyTour(Long tourId) {
        return tourRepository.findById(tourId)
                .orElseThrow(() -> new EntityNotFoundException("There is no tour with id:" + tourId));
    }

    private TourRating verifyTourRating(Long tourId, Long customerId) {
        return tourRatingRepository.findByTourIdAndCustomerId(tourId, customerId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("There is no tour rating with tourId: %s and customerId: %d", tourId, customerId)));
    }

    private RatingDto getRatingDto(TourRating tourRating) {
        return RatingDto.builder()
                .score(tourRating.getScore())
                .comment(tourRating.getComment())
                .customerId(tourRating.getCustomerId())
                .build();
    }
}
