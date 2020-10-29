package com.java.myrotiuk.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

/**
 * @author Ivan_Myrotiuk
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Region {

    CENTRAL_COAST("Central Coast"),
    SOUTHERN_CALIFORNIA("Southern California"),
    NORTHERN_CALIFORNIA("Northern California"),
    VARIES("Varies"),
    NAPA_SONOMA_COUNTIES("Napa/Sonoma Counties");

    private String label;

    public static Region findByLabel(String label) {
        for (Region region : Region.values()) {
            if (region.label.equalsIgnoreCase(label)) {
                return region;
            }
        }
        throw new IllegalArgumentException(String.format("There is no such region with label: %s", label));
    }
}
