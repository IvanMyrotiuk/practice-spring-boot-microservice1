package com.java.myrotiuk.repository;

import com.java.myrotiuk.entity.Difficulty;
import com.java.myrotiuk.entity.Region;
import com.java.myrotiuk.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ivan_Myrotiuk
 */
@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {
    List<Tour> findByTourPackageCodeAndRegion(String code, Region region);

    List<Tour> findByRegionIn(List<Region> regions);

    List<Tour> findByPriceLessThan(Integer maxPrice);

    List<Tour> findByKeywordsContains(String keyword);

    //Page<Tour> findByKeywordsContains(String keyword, Pageable pageable);

    List<Tour> findByTourPackageAndBulletsLike(String code, String searchString);

    List<Tour> findByTourPackageCodeAndDifficultyAndRegionAndPriceLessThan
            (String code, Difficulty difficulty, Region region, Integer maxPrice);

    @Query("SELECT t FROM Tour t WHERE t.tourPackage.code=:code " +
            "AND t.difficulty=:difficulty AND t.region=:region AND t.price<=:price")
    List<Tour> lookupTour(@Param("code") String code, @Param("difficulty") Difficulty difficulty,
                          @Param("region") Region region, @Param("price") Integer maxPrice);
}
