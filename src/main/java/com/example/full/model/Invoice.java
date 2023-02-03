package com.example.full.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "facturas")
@AllArgsConstructor
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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="factura_id")
    private List<ItemInvoice> items;

    @PrePersist
    public void prePersist(){
        this.createdAt = new Date();
    }

    public Double getTotal(){
        Double total = 0.0;
        for (ItemInvoice item: items){
            total += item.getSubTotal();
        }
        return 0.0;
    }

}
