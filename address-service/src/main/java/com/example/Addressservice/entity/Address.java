package com.example.Addressservice.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "lane1")
    private String lane1;
    @Column(name = "lane2")
    private String lane2;
    @Column(name = "state")
    private String state;
    @Column(name = "zip")
    private String zip;
}
