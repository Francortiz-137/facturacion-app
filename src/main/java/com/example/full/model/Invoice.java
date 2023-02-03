package com.example.full.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "facturas")
public class Invoice implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String description;

    private Long observation;

    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cliente_id")
    private Client client;

    @PrePersist
    public void prePersist(){
        this.createdAt = new Date();
    }


}
