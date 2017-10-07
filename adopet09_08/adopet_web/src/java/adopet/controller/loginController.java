package adopet.controller;

import adopet.model.criteria.PessoaCriteria;
import adopet.model.criteria.UsuarioCriteria;
import adopet.model.entity.Pessoa;
import adopet.model.entity.Usuario;
import adopet.model.service.PessoaService;
import adopet.model.service.UsuarioService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
        UsuarioService usuarioService = new UsuarioService();
        boolean vazio = false;
        try {
            if (usuarioService.readByCriteria(new HashMap<Long, Object>(), null, null).isEmpty()) {
                vazio = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (vazio) {

            Usuario usuario = new Usuario();
            usuario.setEmail("admin@admin.com");
            usuario.setSenha("admin");

            try {
                usuarioService.create(usuario);
                usuario = usuarioService.readByCriteria(new HashMap<Long, Object>(), null, null).get(0);
            } catch (Exception ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        try {
            List<Usuario> usuarioList = service.readByCriteria(new HashMap<Long, Object>(), null, null);
            mv.addObject("usuarioList", usuarioList);
        } catch (Exception ex) {
            //TODO resolver depois...
        }
        return mv;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView create(String email, String senha) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        ModelAndView mv = new ModelAndView("redirect:/login");

        //Mapa de erros
        List<String> errors = new ArrayList<>();

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
            //Mapa de criterios de busca
            Map<Long, Object> criteria = new HashMap<Long, Object>();
            criteria.put(UsuarioCriteria.EMAIL_EQ, usuario.getEmail());
            criteria.put(UsuarioCriteria.SENHA_EQ, usuario.getSenha());
            //Busca usuario com email e senha iguaios no banco
            try {
                listUsuario = usuarioService.readByCriteria(criteria, null, null);
            } catch (Exception e) {
                e.printStackTrace();

            }
            //se encontrou procuta qual  pessoa com o mesmo id
            if (listUsuario != null && !listUsuario.isEmpty()) {
                Usuario usuarioDb = listUsuario.get(0);

                PessoaService pessoaService = new PessoaService();
                //Criterio de busca
                Map<Long, Object> criteriaPessoa = new HashMap<Long, Object>();
                criteriaPessoa.put(PessoaCriteria.USUARIO_ID_EQ, usuarioDb.getId());
                List<Pessoa> pessoaListDb = new ArrayList<>();
                try {
                    pessoaListDb = pessoaService.readByCriteria(criteriaPessoa, null, null);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //se encontrou adiciona atributo
                if (pessoaListDb != null && !pessoaListDb.isEmpty()) {
                    HttpSession session = request.getSession();
                    session.setAttribute("usuarioLogado", email);
                    session.setAttribute("pessoaCpfLogado", pessoaListDb.get(0).getCpf());
                    mv = new ModelAndView("redirect:/adocao");
                } else {
                    errors.add("Pessoa não cadastrada");
                }

            } else {
                errors.add("Usuário Incorreto");
            }

        } else {
            errors.add("Verifique os campos");
        }
        mv.addObject("errors", errors);
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
            List<Usuario> usuarioList = service.readByCriteria(new HashMap<Long, Object>(), null, null);
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
