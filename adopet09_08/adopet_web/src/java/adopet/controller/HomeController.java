package adopet.controller;

import adopet.model.entity.Pessoa;
import adopet.model.entity.Usuario;
import adopet.model.service.PessoaService;
import adopet.model.service.UsuarioService;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView mv = new ModelAndView("/home/home");

        return mv;
    }
}
