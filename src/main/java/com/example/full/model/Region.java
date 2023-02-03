package com.example.full.model;

import javax.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name="regiones")
@Data
public class Region implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

}
