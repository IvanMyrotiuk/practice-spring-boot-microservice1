package com.java.myrotiuk.helper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.myrotiuk.entity.Difficulty;
import com.java.myrotiuk.entity.Region;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;

/**
 * Helper class to import ExploreCalifornia.json
 */
@Getter
@NoArgsConstructor
public class TourFromFileHelper {
    //fields
    private String packageType, title, description, blurb, price, length,
            bullets, keywords, /*difficulty,*/ region;
    private Difficulty difficulty;

    //reader
    static List<TourFromFileHelper> read(String fileToImport) throws IOException {
        return new ObjectMapper().setVisibility(FIELD, ANY).
                readValue(new FileInputStream(fileToImport), new TypeReference<List<TourFromFileHelper>>() {
                });
    }

    Double getPrice() {
        return Double.parseDouble(price);
    }

//    Difficulty getDifficulty() {
//        return Difficulty.findByLabel(difficulty);
//    }

    Region getRegion() {
        return Region.findByLabel(region);
    }
}