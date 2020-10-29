package com.java.myrotiuk.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Tour_Rating", schema = "")
public class TourRating {

    @GeneratedValue
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;

    @Column(nullable = false)
    private Long customerId;

    @Column(nullable = false)
    private Integer score;

    @Column
    private String comment;
}
