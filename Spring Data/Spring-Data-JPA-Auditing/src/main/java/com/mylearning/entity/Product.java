package com.mylearning.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="Product_Details")
@EntityListeners(AuditingEntityListener.class)

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private String description;
    private String productType;

    //Auditors
    @CreatedBy
    private String createdBy;

    @CreatedDate
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @LastModifiedBy
    private String lastModifiedBy;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
}

/*
Note:
TemporalType.DATE: Stores only the date (year, month, day) without the time.
TemporalType.TIME: Stores only the time (hours, minutes, seconds) without the date.
TemporalType.TIMESTAMP: Stores both the date and time (date and time up to
*/
