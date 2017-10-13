package adopet.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SobreController {
 @RequestMapping(value = "/sobre", method = RequestMethod.GET) 
    public ModelAndView read() {
        ModelAndView mv = new ModelAndView("/sobre/sobre");
        
        return mv;
    }   
}
