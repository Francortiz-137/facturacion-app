package com.example.full.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "no puede estar vacio")
    @Size(min = 4, max = 12, message = "el tamaño debe estar entre 4 y 12 caracteres")
    @Column(nullable = false)
    private String name;
    @NotEmpty(message = "no puede estar vacio")
    private String lastName;
    @NotEmpty(message = "no puede estar vacio")
    @Email(message = "no es una direccion de correo bien formada")
    @Column(nullable = false, unique = true)
    private String email;
    @NotNull(message = "no puede estar vacio")
    private Date createdAt;

    private String img;

    @NotNull(message="La region no puede ser vacia")
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="region_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Region region;
}
