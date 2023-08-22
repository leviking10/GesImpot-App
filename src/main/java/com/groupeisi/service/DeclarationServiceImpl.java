package com.groupeisi.service;

import com.groupeisi.DAO.IDeclarationRepository;
import com.groupeisi.DAO.IPaiementRepository;
import com.groupeisi.Entities.Declaration;
import com.groupeisi.Entities.Paiement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeclarationServiceImpl implements DeclarationService{

    @Autowired
    private IDeclarationRepository declarationRepository;
    @Autowired
    private IPaiementRepository paiementRepository;
    @Override
    public List<Declaration> getAllDeclarations() {
        return declarationRepository.findAll();
    }

    @Override
    public Declaration saveDeclaration(Declaration declaration) {
        return declarationRepository.save(declaration);
    }

    @Override
    public Declaration getDeclarationById(Long id) {

        return declarationRepository.findById(id).orElse(null);
    }
    public void validateAndSaveDeclaration(Declaration declaration) {

        double montantTotalPaiements = paiementRepository
                .findByDeclaration(declaration)
                .stream()
                .mapToDouble(Paiement::getMontantPaiement)
                .sum();

        if (montantTotalPaiements <= declaration.getMontantDeclaration()) {
            declarationRepository.save(declaration);
        } else {
            throw new RuntimeException("Le montant total des paiements dépasse le montant de la déclaration");
        }
    }

    @Override
    public void deleteDeclaration(Long id) {
        declarationRepository.deleteById(id);
    }
}
