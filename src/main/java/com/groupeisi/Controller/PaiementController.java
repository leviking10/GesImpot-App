package com.groupeisi.Controller;

import com.groupeisi.Entities.Declaration;
import com.groupeisi.Entities.Paiement;
import com.groupeisi.service.PaiementService;
import com.groupeisi.service.DeclarationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Controller
@RequestMapping("/paiements")
public class PaiementController {
    private static final Logger logger = LoggerFactory.getLogger(PaiementController.class);

    private final PaiementService paiementService;
    private final DeclarationService declarationService;

    @Autowired
    public PaiementController(PaiementService paiementService, DeclarationService declarationService) {
        this.paiementService = paiementService;
        this.declarationService = declarationService;
    }

    @GetMapping("/new/{declarationId}")
    public String nouvellePaiement(@PathVariable Long declarationId, Model model) {
        Declaration declaration = declarationService.getDeclarationById(declarationId);


        Paiement paiement = new Paiement();
        paiement.setDeclaration(declaration);

        model.addAttribute("paiement", paiement);
        model.addAttribute("listDeclarations", declarationService.getAllDeclarations());

        return "add-paiement";
    }

    @PostMapping("/savePaiement")
    public String savePaiement(@ModelAttribute("paiement") Paiement paiement, Model model) {
        try {
            // Vérifier si le paiement ou la déclaration est nul
            if (paiement == null || paiement.getDeclaration() == null) {
                throw new IllegalArgumentException("Paiement ou déclaration est null");
            }

            // Log du paramètre
            logger.info("Récupération du montant total des paiements pour la déclaration : {}", paiement.getDeclaration().getId());

            Declaration currentDeclaration = declarationService.getDeclarationById(paiement.getDeclaration().getId());
            if (currentDeclaration == null) {
                throw new IllegalArgumentException("Déclaration introuvable dans la base de données pour l'ID: " + paiement.getDeclaration().getId());
            }

            // Log des détails de la déclaration récupérée
            logger.info("Détails de la déclaration récupérée: ID = {}, Montant = {}", currentDeclaration.getId(), currentDeclaration.getMontantDeclaration());

            double totalPaiements = paiementService.getTotalPaiementForDeclaration(currentDeclaration);

            // Vérifiez si le paiement a un ID (signifiant qu'il est déjà enregistré)
            if (paiement.getId() != null) {
                Paiement oldPaiement = paiementService.getPaiementById(paiement.getId());
                if (oldPaiement != null) {
                    totalPaiements -= oldPaiement.getMontantPaiement();
                }
            }

            logger.info("Montant total ajusté des paiements pour la déclaration {}: {}", currentDeclaration.getId(), totalPaiements);

            if (totalPaiements + paiement.getMontantPaiement() > currentDeclaration.getMontantDeclaration()) {
                model.addAttribute("listDeclarations", declarationService.getAllDeclarations());
                model.addAttribute("error", "Le montant du paiement ne peut pas être supérieur au montant de la déclaration.");
                return "add-paiement";  // Return to the same view with error message
            }

            paiementService.savePaiement(paiement);
        } catch (Exception e) {
            // Utilisez un logger pour enregistrer l'exception
            logger.error("Erreur lors de la sauvegarde du paiement", e);
            model.addAttribute("error", "Une erreur est survenue. Veuillez réessayer.");
            return "add-paiement"; // Redirigez vers la même vue avec le message d'erreur
        }

        return "redirect:/paiements/";
    }



    @GetMapping("/details/{declarationId}")
    public String detailsPaiements(@PathVariable Long declarationId, Model model) {
        List<Paiement> listPaiements = paiementService.getPaiementsForDeclaration(declarationId);
        model.addAttribute("listPaiements", listPaiements);
        return "details-paiements"; // Vue pour les détails des paiements
    }

    @GetMapping("/ShowFormForUpdate/{id}")
    public String ShowFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        Paiement paiement = paiementService.getPaiementById(id);
        model.addAttribute("listDeclarations", declarationService.getAllDeclarations());
        model.addAttribute("paiement", paiement);
        return "update-paiement";
    }

    @GetMapping("/")
    public String listPaiements(Model model) {
        model.addAttribute("listPaiements", paiementService.getAllPaiements());
        return "list-Paiements";
    }

    @GetMapping("/deletePaiement/{id}")
    public String deletePaiement(@PathVariable(value = "id") long id) {
        paiementService.deletePaiement(id);
        return "redirect:/paiements/";
    }
}
