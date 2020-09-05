package be.vdab.frituurfrida.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("taal")
 class TaalController {

//    private static final String nederlands = "nl";

    @GetMapping
    public ModelAndView nederlands(@RequestHeader("Accept-Language") String language){
//        var modelAndView = new ModelAndView("taal");
//        if(acceptLanguage.startsWith(nederlands)){
//            modelAndView.addObject("taal", "");
//        } else {
//            modelAndView.addObject("taal", " geen");
//        }
//        return modelAndView;
        return new ModelAndView("taal", "nederlands", language.startsWith("nl"));
    }
}



//
//    private static final String[] OSS = {"Windows","Macintosh","Android","Linux"};
//
//    @GetMapping
//    public ModelAndView os(@RequestHeader("User-Agent") String userAgent) {
//        var modelAndView = new ModelAndView("os");
//        Arrays.stream(OSS).filter(os -> userAgent.contains(os))
//            .findFirst().ifPresent(os -> modelAndView.addObject("os", os));
//        return modelAndView;
//    }
//}