package adopet.controller;

import adopet.model.entity.Usuario;
import adopet.model.service.UsuarioService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class loginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView mv = new ModelAndView("/login/form");
        UsuarioService service = new UsuarioService();
        try {
            List<Usuario> usuarioList = service.readByCriteria(null);
            mv.addObject("usuarioList", usuarioList);
        } catch (Exception ex) {
            //TODO resolver depois...
        }
        return mv;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView create(String email, String senha) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        ModelAndView mv = new ModelAndView("redirect:/adocao");

        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(senha);

        if (usuario.getEmail() != null
                && !usuario.getEmail().isEmpty() && usuario.getSenha() != null
                && !usuario.getSenha().isEmpty()) {
            UsuarioService usuarioService = new UsuarioService();
            Map<Long, Object> criteria = new HashMap<>();
            criteria.put(1L, email);
            criteria.put(2L, senha);
            try {
                List<Usuario> usuarioList = usuarioService.readByCriteria(criteria);

                for (Usuario usuario1 : usuarioList) {
                    if (usuario1.getEmail().equals(email) && usuario1.getSenha().equals(senha)) {
                        HttpSession session = request.getSession();
                        session.setAttribute("usuarioLogado", email);
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return mv;

    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        ModelAndView mv = new ModelAndView("redirect:/blog");
        HttpSession session = request.getSession();
        session.removeAttribute("usuarioLogado");

        return mv;

    }

    @RequestMapping(value = "/login/{id}/ver}", method = RequestMethod.GET)
    public ModelAndView showFormForVer() {
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
    public ModelAndView showFormForCreate() {
        ModelAndView mv = new ModelAndView("/login/form");
        return mv;

    }

}
