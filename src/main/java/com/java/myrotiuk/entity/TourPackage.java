package com.java.myrotiuk.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Ivan_Myrotiuk
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "Tour_Package", schema = "")
public class TourPackage {
    @Id
    private String code;

    @Column(name = "name")
    private String name;

}
