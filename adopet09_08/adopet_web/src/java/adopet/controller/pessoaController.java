package adopet.controller;

import adopet.model.entity.Pessoa;
import adopet.model.service.PessoaService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class pessoaController {
 @RequestMapping(value = "/pessoa", method = RequestMethod.GET) 
    public ModelAndView read() {
        ModelAndView mv = new ModelAndView("/pessoa/formCadastre");
        
       
        
        PessoaService service = new PessoaService();
        try {
            List<Pessoa> pessoaList = service.readByCriteria(null);
            mv.addObject("pessoaList", pessoaList);
        } catch (Exception ex) {
            //TODO resolver depois...
        }       
        
        

        return mv;
    }    
}
