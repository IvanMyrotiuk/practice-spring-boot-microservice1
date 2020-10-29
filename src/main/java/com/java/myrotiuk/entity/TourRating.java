package com.java.myrotiuk.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TourRating {

    @Id
    private String id;

    private String tourId;

    private Long customerId;

    private Integer score;

    private String comment;
}
