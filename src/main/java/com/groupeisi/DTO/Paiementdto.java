package com.groupeisi.DTO;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paiementdto {
    private Long id;
    @NotNull(message = "la date de paiement ne doit pas etre nulle")
    private Date datePaiement;
    @NotNull(message = "la montant  ne doit pas etre nulle")
    private double montantPaiement;
    @NotNull
    private Long idDeclaration;
}