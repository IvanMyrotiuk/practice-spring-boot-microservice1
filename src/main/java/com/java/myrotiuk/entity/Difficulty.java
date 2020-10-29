package com.java.myrotiuk.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

/**
 * @author Ivan_Myrotiuk
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Difficulty {

    EASY("Easy"),
    MEDIUM("Medium"),
    DIFFICULT("Difficult"),
    VARIES("Varies");

    private final String label;

    public static Difficulty findByLabel(String label) {
        for (Difficulty difficulty : Difficulty.values()) {
            if (difficulty.label.equalsIgnoreCase(label)) {
                return difficulty;
            }
        }
        throw new IllegalArgumentException(String.format("There is no such difficulty: %s", label));
    }

}
