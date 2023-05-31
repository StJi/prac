package com.example.laba_practice;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "traffic_stat")
public class traffic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer traffic;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private Date date;

}
