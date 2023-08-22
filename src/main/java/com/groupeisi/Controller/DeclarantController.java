package com.groupeisi.Controller;

import com.groupeisi.DTO.Declarantdto;
import com.groupeisi.Entities.Declarant;
import com.groupeisi.service.DeclarantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/declarants")
public class DeclarantController {

    private final DeclarantService declarantService;

    @Autowired
    public DeclarantController(DeclarantService declarantService) {
        this.declarantService = declarantService;
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        Declarant declarant =new Declarant();
        model.addAttribute("declarant",declarant);
        return "add-declarant";
    }

    @PostMapping("/saveDeclarant")
    public String saveDeclarant(@ModelAttribute("declarant") Declarant declarant) {
        declarantService.saveDeclarant(declarant);
        return "redirect:/declarants/";
    }

    @GetMapping("/ShowFormFoUpdate/{id}")
    public String ShowFormFoUpdate(@PathVariable( value ="id")long id,Model model){
     Declarant declarant=declarantService.getDeclarantById(id);
        model.addAttribute("declarant",declarant);
        return "update_declarant";
    }
    @GetMapping("/")
    public String listDeclarants(Model model) {
        model.addAttribute("listdeclarants", declarantService.getAllDeclarants());
        return "listdeclarants";
    }
    @GetMapping("/deleteDeclarant/{id}")
    public String deleteDeclarant(@PathVariable(value = "id") long id) {

        this.declarantService.deleteDeclarantById(id);
        return "redirect:/declarants/";
    }
}
