package adopet.controller;

import adopet.model.entity.Usuario;
import adopet.model.service.UsuarioService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class loginController {
    @RequestMapping(value = "/login", method = RequestMethod.GET) 
    public ModelAndView read() {
        ModelAndView mv = new ModelAndView("/login/list");
        UsuarioService service = new UsuarioService();
        try {
            List<Usuario> usuarioList = service.readByCriteria(null);
            mv.addObject("usuarioList", usuarioList);
        } catch (Exception ex) {
            //TODO resolver depois...
        }       
        return mv;
    }
         
    @RequestMapping(value = "/login/{id}/ver}", method = RequestMethod.GET)
    public ModelAndView showFormForVer(){
        ModelAndView mv = new ModelAndView("/login/new");
        return mv;
    }
    
      @RequestMapping(value = "/login/{id}/ver", method = RequestMethod.GET) // /categorias = requisição do usuário na URL
    public ModelAndView Ver() {
        ModelAndView mv = new ModelAndView("/login/new");
        UsuarioService service = new UsuarioService();
        try {
            List<Usuario> usuarioList = service.readByCriteria(null);
            mv.addObject("usuarioList", usuarioList);
        } catch (Exception ex) {
            //TODO resolver depois...
        }       
        return mv;
    }
    
       
    @RequestMapping(value = "/login/novo", method = RequestMethod.GET)
    public ModelAndView showFormForCreate(){
        ModelAndView mv = new ModelAndView("/login/form");
        return mv;
        
    }
    
      @RequestMapping(value = "/login/novo", method = RequestMethod.POST)
    public ModelAndView create(String tipo,String especie, String sexo, String porte, String idade, String caracteristica,String raca){
        ModelAndView mv = new ModelAndView("redirect:/login");
        
        Usuario usuario = new Usuario();
        usuario.setEmail("email");
        usuario.setSenha("senha");
       
 //não sei muito bem o que preciso fazer aqui 
//        UsuarioService service = new UsuarioService();
//        try {
//        List<Usuario> usuarioList = service.readByCriteria(null);
//            for (Usuario entity : usuarioList) {
//                if(entity.getId().equals(especie)){
//                    usuario.setEmail("email");
//                }
//            }
//        
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//       
        return mv;
        
    }
   
    
}
