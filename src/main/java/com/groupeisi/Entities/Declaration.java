package com.groupeisi.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Declaration {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false,length = 80)
    private String nomDeclaration;
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateDeclaration;
    @Column(nullable = false,length = 80)
    private double montantDeclaration;

    @ManyToOne
    @JoinColumn(name = "id_declarant", nullable = false)
    private Declarant declarant;

    @OneToMany(mappedBy = "declaration")
    private List<Paiement> paiements;
}
