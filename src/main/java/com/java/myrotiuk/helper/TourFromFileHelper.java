package com.java.myrotiuk.helper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;

/**
 * Helper class to import ExploreCalifornia.json
 */
@Getter
@NoArgsConstructor
public class TourFromFileHelper {

    private String title;

    private String tourPackageName;

    private Map<String, String> details;

    TourFromFileHelper(Map<String, String> record) {
        this.title = record.get("title");
        this.tourPackageName = record.get("packageType");
        this.details = record;
        this.details.remove("packageType");
        this.details.remove("title");
    }

    //reader
    static List<TourFromFileHelper> read(String fileToImport) throws IOException {
        List<Map<String, String>> records = new ObjectMapper().setVisibility(FIELD, ANY).
                readValue(new FileInputStream(fileToImport), new TypeReference<List<Map<String, String>>>() {
                });
        return records.stream()
                .map(TourFromFileHelper::new)
                .collect(Collectors.toList());
    }
}