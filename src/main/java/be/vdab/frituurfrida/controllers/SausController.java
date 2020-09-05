package be.vdab.frituurfrida.controllers;

import be.vdab.frituurfrida.forms.SausRadenForm;
import be.vdab.frituurfrida.services.SausService;
import be.vdab.frituurfrida.sessions.SausRaden;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.concurrent.ThreadLocalRandom;

@Controller
@RequestMapping("sauzen")
class SausController {


//    private final Saus[] sauzen = {
//            new Saus(1, "cocktail", new String[]{"mayo", "ketchup"}),
//            new Saus(2, "mayonaise", new String[]{"ei", "azijn"}),
//            new Saus(3, "mosterd", new String[]{"ei", "kruiden"}),
//            new Saus(4, "tartare", new String[]{"mayo", "ui", "kruiden"}),
//            new Saus(5, "vinaigrette", new String[]{"azijn", "kruiden"})
//    };
    private final char[] alfabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private final SausService sausService;

    private final SausRaden sausRaden;


    SausController(SausService sausService, SausRaden sausRaden) {
        this.sausService = sausService;
        this.sausRaden = sausRaden;
    }

    @GetMapping
    public ModelAndView sauzen(){
//        return new ModelAndView("sauzen", "sauzen", sauzen);
        return new ModelAndView("sauzen", "sauzen", sausService.findAll());
    }

    @GetMapping("{nummer}")
    public ModelAndView saus(@PathVariable long nummer){
        var modelAndView = new ModelAndView("saus");
//        Arrays.stream(sauzen).filter(saus -> saus.getNummer() == nummer).findFirst().ifPresent(
//                saus -> modelAndView.addObject(saus)
//        );

//        return modelAndView;
        sausService.findByNummer(nummer).ifPresent(modelAndView::addObject);
        return modelAndView;
    }

    @GetMapping("alfabet")
    public ModelAndView alfabet() {
        return new ModelAndView("sausAlfabet", "alfabet", alfabet);
    }

//    private List<Saus> sauzenDieBeginnenMet(char letter) {
//        return Arrays.stream(sauzen).filter(saus->saus.getNaam().charAt(0)==letter)
//                .collect(Collectors.toList());
//    }


    @GetMapping("alfabet/{letter}")
    public ModelAndView sauzenBeginnendMet(@PathVariable char letter) {
//        return new ModelAndView("sausAlfabet", "alfabet", alfabet)
//                .addObject("sauzenDieBeginnenMet", sauzenDieBeginnenMet(letter));
        var modelAndView = new ModelAndView("sausAlfabet", "alfabet", alfabet);
        modelAndView.addObject("sauzenDieBeginnenMet", sausService.findByNaamBegintMet(letter));
        return modelAndView;
    }

    private String randomSaus(){
        var sauzen = sausService.findAll();
        return sauzen.get(
                ThreadLocalRandom.current().nextInt(sauzen.size())
        ).getNaam();
    }

    @GetMapping("raden")
    public ModelAndView radenForm(){
        sausRaden.reset(randomSaus());
        return new ModelAndView("sausRaden")
                .addObject(sausRaden)
                .addObject(new SausRadenForm(null));
    }

    @PostMapping("raden/nieuwSpel")
    public String radenNieuwSpel(){
        return "redirect:/sauzen/raden";
    }

    @PostMapping(value = "raden")
    public ModelAndView raden(@Valid SausRadenForm form, Errors errors) {
        if(errors.hasErrors()){
            return new ModelAndView("sausRaden").addObject(sausRaden);
        }
        sausRaden.gok(form.getLetter());
        return new ModelAndView("redirect:/sauzen/raden/volgendeGok");
    }

    @GetMapping("raden/volgendeGok")
    public ModelAndView volgendeGok(){
        return new ModelAndView("sausRaden")
                .addObject(sausRaden)
                .addObject(new SausRadenForm(null));
    }

}
