package com.groupeisi.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Declarant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false,length = 80)
    private String raisonSociale;
    @Column(nullable = false,length = 100)
    private String adresse;
    @Column(unique=true,nullable = false,length = 80)
    private String email;
    @Column(unique=true,nullable = false,length=12)
    private String telephone;

    @OneToMany(mappedBy = "declarant")
    private List<Declaration> declarations;
}
