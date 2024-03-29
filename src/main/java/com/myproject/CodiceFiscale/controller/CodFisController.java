package com.myproject.CodiceFiscale.controller;

import com.myproject.CodiceFiscale.Entity.CodiceFiscale;
import com.myproject.CodiceFiscale.service.FisCodServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CodFisController {

    CodiceFiscale codiceFiscale = new CodiceFiscale();
    @Autowired
    FisCodServiceImpl fisCodService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("codiceFiscale", this.codiceFiscale);
        return "index";
    }

    @PostMapping("/submit")
    public String newCodFis(@ModelAttribute CodiceFiscale codFis, Model model) {
        String cod = null;
        try {
            cod = fisCodService.codFisGenerator(codFis.getNome(),
                    codFis.getCognome(),
                    codFis.getData(),
                    codFis.getGender(),
                    codFis.getComune());
        } catch (Exception ex) {
            model.addAttribute("error", "Errore durante la generazione del codice fiscale: " + ex.getMessage());
            return "index";
        }
        System.out.println(cod);
        model.addAttribute("codice", cod);
        return "index";


    }
}
