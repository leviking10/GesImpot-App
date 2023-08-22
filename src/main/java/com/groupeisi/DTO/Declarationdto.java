package com.groupeisi.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Declarationdto {
    @NotNull
    private Long id;
    @NotNull(message = "la date ne doit pas etre nulle")
    private Date dateDeclaration;
    @NotNull(message = "le montant ne doit pas etre vide")
    private double montantDeclaration;
    @NotNull
    private Long idDeclarant;
}