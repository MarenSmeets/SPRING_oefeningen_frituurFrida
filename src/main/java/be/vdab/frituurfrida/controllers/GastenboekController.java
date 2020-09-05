package be.vdab.frituurfrida.controllers;

import be.vdab.frituurfrida.forms.GastenboekEntryForm;
import be.vdab.frituurfrida.services.GastenboekEntryService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("gastenboek")
public class GastenboekController {

    private final GastenboekEntryService gastenboekEntryService;

    public GastenboekController(GastenboekEntryService gastenboekEntryService) {
        this.gastenboekEntryService = gastenboekEntryService;
    }

    @GetMapping
    public ModelAndView findAll(){
        return new ModelAndView("gastenboek", "gastenboekEntries", gastenboekEntryService.findAll());
    }

    @GetMapping("toevoegen/form")
    public ModelAndView toevoegenForm(){
        return new ModelAndView("gastenboek", "gastenboekEntries", gastenboekEntryService.findAll())
                .addObject(new GastenboekEntryForm("",""));
    }

    @PostMapping("toevoegen")
    public ModelAndView toevoegen(@Valid GastenboekEntryForm form, Errors errors){
        if(errors.hasErrors()){
            return new ModelAndView("gastenboek", "gastenboekEntries", gastenboekEntryService.findAll());
        }
        gastenboekEntryService.create(form);
        return new ModelAndView("redirect:/gastenboek");
    }

    @PostMapping("verwijderen")
    public String delete(long[] id){
        if(id!=null) {
            gastenboekEntryService.delete(id);
        }
        return "redirect:/gastenboek";
    }
}
