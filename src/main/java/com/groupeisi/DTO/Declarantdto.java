package com.groupeisi.DTO;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;





@Data
@NoArgsConstructor
@AllArgsConstructor
public class Declarantdto {

    private Long id;
    @NotNull(message = "la raison sociale ne doit pas etre nulle")
    private String raisonSociale;
    @NotNull(message = "l'addresse ne doit pas etre nulle")
    private String adresse;
    @NotNull(message = "l'email ne doit pas etre nulle")
    private String email;
    @NotNull(message = "le numéro de téléphone ne doit pas etre nul")
    private String telephone;

}
