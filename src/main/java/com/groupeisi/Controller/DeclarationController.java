package com.groupeisi.Controller;

import com.groupeisi.Entities.Declarant;
import com.groupeisi.Entities.Declaration;
import com.groupeisi.service.DeclarantService;
import com.groupeisi.service.DeclarationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/declarations")
public class DeclarationController {

    private final DeclarationService declarationService;
    @Autowired
    private DeclarantService declarantService;

    @Autowired
    public DeclarationController(DeclarationService declarationService) {
        this.declarationService = declarationService;
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        Declaration declaration = new Declaration();
        model.addAttribute("declaration", declaration);

        List<Declarant> listDeclarants = declarantService.getAllDeclarants();
        model.addAttribute("listDeclarants", listDeclarants);

        return "add-declaration";
    }

    @PostMapping("/saveDeclaration")
    public String saveDeclaration(@ModelAttribute("declaration") Declaration declaration) {
        declarationService.saveDeclaration(declaration);
        return "redirect:/declarations/";
    }

    @GetMapping("/ShowFormForUpdate/{id}")
    public String ShowFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        Declaration declaration = declarationService.getDeclarationById(id);
        model.addAttribute("declaration", declaration);

        List<Declarant> listDeclarants = declarantService.getAllDeclarants();
        model.addAttribute("listDeclarants", listDeclarants);

        return "update-declaration";
    }


    @GetMapping("/")
    public String listDeclarations(Model model) {
        model.addAttribute("listDeclarations", declarationService.getAllDeclarations());
        return "list-Declarations";
    }

    @GetMapping("/deleteDeclaration/{id}")
    public String deleteDeclaration(@PathVariable(value = "id") long id) {
        declarationService.deleteDeclaration(id);
        return "redirect:/declarations/";
    }
}
