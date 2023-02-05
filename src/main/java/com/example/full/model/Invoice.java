package com.example.full.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "facturas")
@AllArgsConstructor
@NoArgsConstructor
public class Invoice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descripcion")
    private String description;

    @Column(name = "observacion")
    private String observation;

    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @JsonIgnoreProperties(value={"invoices","hibernateLazyInitializer", "handler"},allowSetters = true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cliente_id")
    private Client client;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
        return total;
    }

}
