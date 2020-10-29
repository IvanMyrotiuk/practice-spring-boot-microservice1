package com.java.myrotiuk.repository;

import com.java.myrotiuk.entity.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Ivan_Myrotiuk
 */
@Repository
public interface TourRepository extends PagingAndSortingRepository<Tour, String> {
//    List<Tour> findByTourPackageCodeAndRegion(String code, String region);
//
//    List<Tour> findByRegionIn(List<String> regions);
//
//    List<Tour> findByPriceLessThan(Integer maxPrice);
//
//    List<Tour> findByKeywordsContains(String keyword);
//
//    //Page<Tour> findByKeywordsContains(String keyword, Pageable pageable);
//
//    List<Tour> findByTourPackageAndBulletsLike(String code, String searchString);
//
//    List<Tour> findByTourPackageCodeAndDifficultyAndRegionAndPriceLessThan
//            (String code, String difficulty, String region, Integer maxPrice);
//
//    @Query("SELECT t FROM Tour t WHERE t.tourPackage.code=:code " +
//            "AND t.difficulty=:difficulty AND t.region=:region AND t.price<=:price")
//    List<Tour> lookupTour(@Param("code") String code, @Param("difficulty") String difficulty,
//                          @Param("region") String region, @Param("price") Integer maxPrice);

    /**
     * Only return the main fields of a Tour, not details
     * only return id, title, tourPackageCode and tourPackageName fields
     */
    @Query(value = "{'tourPackageCode' =: code }",
            fields = "{ 'id':1, 'title':1, 'tourPackageCode':1, 'tourPackageName':1}")
    Page<Tour> findSummaryByTourPackageCode(@Param("code") String code, Pageable pageable);
}
