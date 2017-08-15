package adopet.controller;

import adopet.model.entity.Clinica;
import adopet.model.service.ClinicaService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class clinicaController {
    @RequestMapping(value = "/clinica", method = RequestMethod.GET) 
    public ModelAndView read() {
        ModelAndView mv = new ModelAndView("/anuncio/list");
        
       
        
        ClinicaService service = new ClinicaService();
        try {
            List<Clinica> clinicaList = service.readByCriteria(null);
            mv.addObject("clinicaList", clinicaList);
        } catch (Exception ex) {
            //TODO resolver depois...
        }       
        
        

        return mv;
    } 
}
