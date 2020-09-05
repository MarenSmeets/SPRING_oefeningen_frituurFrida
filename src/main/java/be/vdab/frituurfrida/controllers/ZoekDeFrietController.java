package be.vdab.frituurfrida.controllers;

import be.vdab.frituurfrida.sessions.ZoekDeFriet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("zoekDeFriet")
class ZoekDeFrietController {

    private final ZoekDeFriet zoekDeFriet;

    ZoekDeFrietController(ZoekDeFriet zoekDeFriet) {
        this.zoekDeFriet = zoekDeFriet;
    }

    @GetMapping("zoeken")
    public ModelAndView zoekDeFriet(){
        return new ModelAndView("zoekDeFriet").addObject(zoekDeFriet);
    }

    @PostMapping("nieuwSpel")
    public String nieuwSpel(){
        zoekDeFriet.reset();
        return "redirect:/zoekDeFriet/zoeken";
    }

    @PostMapping("zoeken/openEenDeur")
    public String openEenDeur(int index){
        zoekDeFriet.openEenDeur(index);
        return "redirect:/zoekDeFriet/zoeken";
    }

}
