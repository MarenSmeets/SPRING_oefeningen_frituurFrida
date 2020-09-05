package be.vdab.frituurfrida.controllers;

import be.vdab.frituurfrida.domain.Saus;
import be.vdab.frituurfrida.domain.Snack;
import be.vdab.frituurfrida.exceptions.SnackNietGevondenException;
import be.vdab.frituurfrida.forms.ZoekSnackBeginNaamForm;
import be.vdab.frituurfrida.services.SausService;
import be.vdab.frituurfrida.services.SnackService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.standard.expression.GreaterThanExpression;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("snacks")
class SnackController {

    private final char[] alfabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private final SnackService snackService;

    SnackController(SnackService snackService) {
        this.snackService = snackService;
    }

    @GetMapping("alfabet")
    public ModelAndView alfabet() {
        return new ModelAndView("snackAlfabet", "alfabet", alfabet);
    }

    private List<Saus> sauzenDieBeginnenMet(char letter) {
        return null;
    }


    @GetMapping("alfabet/{letter}")
    public ModelAndView snacksBeginnendMet(@PathVariable char letter) {
        var modelAndView = new ModelAndView("snackAlfabet", "alfabet", alfabet);
        modelAndView.addObject("snacksDieBeginnenMet", snackService.findByBeginNaam(String.valueOf(letter)));
        return modelAndView;
    }

    @GetMapping("zoekformulier/form")
    public ModelAndView zoekSnackBeginNaamForm(){
        return new ModelAndView("zoekformulier")
                .addObject(new ZoekSnackBeginNaamForm(""));
    }

    @GetMapping("zoekformulier")
    public ModelAndView zoekSnackBeginNaam(@Valid ZoekSnackBeginNaamForm form, Errors errors){
        var modelAndView = new ModelAndView("zoekformulier");
        if(errors.hasErrors()){
            return modelAndView;
        }
        return new ModelAndView("zoekformulier")
                .addObject("snacks", snackService.findByBeginNaam(form.getBeginNaam()));
    }

    @GetMapping("{id}/snackAanpassen/form")
    public ModelAndView aanpassenForm(@PathVariable long id){
        var modelAndView = new ModelAndView("snackAanpassen");
        snackService.read(id).ifPresent(
                snack -> modelAndView.addObject(snack)
        );
        return modelAndView;
    }

    @PostMapping
    public String snackAanpassen(@Valid Snack snack, Errors errors, RedirectAttributes redirect){
        if(errors.hasErrors()){
            return "snackAanpassen";
        }
        try{
            snackService.update(snack);
            return "redirect:/";
        } catch (SnackNietGevondenException ex){
            redirect.addAttribute("snackNietGevonden", snack.getId());
            return "redirect:/";
        }
    }
}
