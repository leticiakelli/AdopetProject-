package adopet.controller;

import adopet.model.entity.Usuario;
import adopet.model.service.UsuarioService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

//        Usuario usuario = new Usuario();
//        usuario.setEmail(email);
//        usuario.setSenha(senha);
//        if (usuario.getEmail() != null
//                && !usuario.getEmail().isEmpty() && usuario.getSenha() != null
//                && !usuario.getSenha().isEmpty()) {
//            HttpSession session = request.getSession();
//            session.setAttribute("usuarioLogado", email);
//        }
        UsuarioService usuarioService = new UsuarioService();
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(senha);
        if (usuario.getEmail() != null
                && !usuario.getEmail().isEmpty() && usuario.getSenha() != null
                && !usuario.getSenha().isEmpty()) {
            List<Usuario> listUsuario = null;
            //verifica se usuarios existentes
            try {
                listUsuario = usuarioService.readByCriteria(new HashMap<>());
            } catch (Exception e) {
                e.printStackTrace();

            }
            //seta usuario na sessao
            if (listUsuario != null && !listUsuario.isEmpty()) {
                for (Usuario usuarioSalvo : listUsuario) {
                    if (usuarioSalvo.getEmail().equals(usuario.getEmail())
                            && usuarioSalvo.getSenha().equals(usuario.getSenha())) {
                        //seta ususario na sessao
                        HttpSession session = request.getSession();
                        session.setAttribute("usuarioLogado", email);
                        break;
                    }
                }

            }

        }

        return mv;

    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        ModelAndView mv = new ModelAndView("redirect:/home");
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
