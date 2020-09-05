package be.vdab.frituurfrida.controllers;

import be.vdab.frituurfrida.domain.Adres;
import be.vdab.frituurfrida.domain.Gemeente;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalTime;

@Controller
@RequestMapping("/")
class IndexController {

    private static final int EEN_JAAR_IN_SECONDEN = 31_536_000;


    private String boodschap() {
        var dag = LocalDate.now().getDayOfWeek().name(); //dayOfWeek = ENUM
        if (dag.equals("MONDAY") || dag.equals("THURSDAY"))  {
            return "gesloten";
        }
        return "open";
    }

//    @GetMapping
//    public ModelAndView index() {
//        var modelAndView = new ModelAndView("index", "boodschap", boodschap());
//        modelAndView.addObject("adres", new Adres("zoete waters", "15", new Gemeente(3050, "Oud-Heverlee")));
//        return modelAndView;
//    }

    @GetMapping
    public ModelAndView index(@CookieValue(name = "reedsBezocht", required = false)
                                      String reedsBezocht, HttpServletResponse response) {

        var modelAndView = new ModelAndView("index", "boodschap", boodschap());

        modelAndView.addObject("adres", new Adres("zoete waters", "15", new Gemeente(3050, "Oud-Heverlee")));

        var cookie = new Cookie("reedsBezocht", "ja");
        cookie.setMaxAge(EEN_JAAR_IN_SECONDEN);
        cookie.setPath("/");
        response.addCookie(cookie);
        if(reedsBezocht != null){
            modelAndView.addObject("reedsBezocht", true);
        }


        return modelAndView;
    }
}
