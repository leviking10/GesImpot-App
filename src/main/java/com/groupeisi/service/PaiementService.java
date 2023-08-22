package com.groupeisi.service;

import com.groupeisi.Entities.Declaration;
import com.groupeisi.Entities.Paiement;
import java.util.List;

public interface PaiementService {
    List<Paiement> getAllPaiements();
    Paiement savePaiement(Paiement paiement);
    Paiement getPaiementById(Long id);
    void deletePaiement(Long id);

    List<Paiement> getPaiementsForDeclaration(Long declarationId);
    double getTotalPaiementForDeclaration(Declaration declaration);
}
