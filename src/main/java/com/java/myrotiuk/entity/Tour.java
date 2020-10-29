package com.java.myrotiuk.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

/**
 * @author Ivan_Myrotiuk
 */
@Document
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Tour {

    @Id
    private String id;

    @Indexed
    private String title;

    @Indexed
    private String tourPackageCode;
    
    private String tourPackageName;

    private Map<String, String> details;

}
