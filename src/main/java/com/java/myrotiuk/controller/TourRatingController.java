package com.java.myrotiuk.controller;

import com.java.myrotiuk.dto.RatingDto;
import com.java.myrotiuk.service.TourRatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "tours/{tourId}/tour-ratings")
public class TourRatingController {

    private final TourRatingService tourRatingService;

    //@ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<RatingDto> createTourRatings(@PathVariable("tourId") String tourId,
                                                       @RequestBody @Validated RatingDto ratingDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tourRatingService.createTourRating(tourId, ratingDto));
    }

    @GetMapping
    public ResponseEntity<List<RatingDto>> getAllRatings(@PathVariable("tourId") String tourId) {
        return ResponseEntity.ok(tourRatingService.getAllTourRatings(tourId));
    }

    @GetMapping(value = "/average")
    public ResponseEntity<Map<String, Double>> getAverage(@PathVariable("tourId") String tourId) {
        return ResponseEntity.ok(tourRatingService.getAverage(tourId));
    }

    /**
     * http://localhost:9090/tours/1/tour-ratings/page?size=2&page=2&sort=score,desc
     */
    @GetMapping("/page")
    public ResponseEntity<Page<RatingDto>> getTourRatingPage(@PathVariable("tourId") String tourId, Pageable pageable) {
        return ResponseEntity.ok(tourRatingService.findByTourId(tourId, pageable));
    }

    @PutMapping
    public ResponseEntity<RatingDto> updateTourRating(@PathVariable("tourId") String tourId,
                                                      @RequestBody @Validated RatingDto ratingDto) {
        return ResponseEntity.ok(tourRatingService.updateTourRating(tourId, ratingDto));
    }

    @PatchMapping
    public ResponseEntity<RatingDto> patchTourRating(@PathVariable("tourId") String tourId,
                                                     @RequestBody @Validated RatingDto ratingDto) {
        return ResponseEntity.ok(tourRatingService.updateTourRatingWithPatch(tourId, ratingDto));
    }

    @DeleteMapping(value = "/{customerId}")
    public void deleteTourRating(@PathVariable("tourId") String tourId, @PathVariable("customerId") Long customerId){
        tourRatingService.deleteTourRating(tourId, customerId);
    }


}
