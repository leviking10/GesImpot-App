package com.groupeisi.service;

import com.groupeisi.DAO.IPaiementRepository;
import com.groupeisi.Entities.Declaration;
import com.groupeisi.Entities.Paiement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaiementServiceImpl implements PaiementService {

    private final IPaiementRepository paiementRepository;

    @Autowired
    public PaiementServiceImpl(IPaiementRepository paiementRepository) {
        this.paiementRepository = paiementRepository;
    }

    @Override
    public List<Paiement> getAllPaiements() {
        return paiementRepository.findAll();
    }

    @Override
    public Paiement savePaiement(Paiement paiement) {
        return paiementRepository.save(paiement);
    }

    @Override
    public Paiement getPaiementById(Long id) {
        return paiementRepository.findById(id)
                .orElseThrow(() -> new PaiementNotFoundException("Paiement avec ID " + id + " non trouvé"));
    }


    @Override
    public double getTotalPaiementForDeclaration(Declaration declaration) {
        List<Paiement> paiements = paiementRepository.findByDeclaration(declaration);
        return paiements.stream()
                .mapToDouble(Paiement::getMontantPaiement)
                .sum();
    }

    @Override
    public void deletePaiement(Long id) {
        if (!paiementRepository.existsById(id)) {
            throw new PaiementNotFoundException("Paiement avec ID " + id + " non trouvé");
        }
        paiementRepository.deleteById(id);
    }

    @Override
    public List<Paiement> getPaiementsForDeclaration(Long declarationId) {
        return paiementRepository.findByDeclarationId(declarationId);
    }
}

// Exception personnalisée
class PaiementNotFoundException extends RuntimeException {
    public PaiementNotFoundException(String message) {
        super(message);
    }
}
