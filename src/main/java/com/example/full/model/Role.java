package com.example.full.model;

import javax.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "roles")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true, length = 20)
    private String name;

}
