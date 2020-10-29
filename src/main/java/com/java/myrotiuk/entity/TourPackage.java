package com.java.myrotiuk.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Ivan_Myrotiuk
 */
@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TourPackage {
    @Id
    private String code;

    private String name;

}
