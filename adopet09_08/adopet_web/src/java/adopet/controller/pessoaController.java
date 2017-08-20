package adopet.controller;

import adopet.model.entity.Pessoa;
import adopet.model.entity.PessoaTelefone;
import adopet.model.entity.Usuario;
import adopet.model.service.PessoaService;
import adopet.model.service.PessoaTelefoneService;
import adopet.model.service.UsuarioService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class pessoaController {

    @RequestMapping(value = "/cadastro", method = RequestMethod.GET)
    public ModelAndView create() {
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

    @RequestMapping(value = "/cadastro", method = RequestMethod.POST)
    public ModelAndView create(String nome, String logradouro,
            String complemento, String bairro, String telefone, String cpf,
            String numero, String cidade, String uf, String celular, String email, String senha) {
        ModelAndView mv = new ModelAndView("redirect:/login");
        PessoaService service = new PessoaService();
        try {
            PessoaTelefone pessoaTelefone = new PessoaTelefone();
            pessoaTelefone.setCelular(celular);
            pessoaTelefone.setTelefone(telefone);
            PessoaTelefoneService pessoaTelefoneService = new PessoaTelefoneService();
            pessoaTelefoneService.create(pessoaTelefone);
            List<PessoaTelefone> pessoaTelefoneList = pessoaTelefoneService.readByCriteria(null);
            pessoaTelefone = pessoaTelefoneList.get(pessoaTelefoneList.size() - 1);

            Usuario usuario = new Usuario();
            usuario.setEmail(email);
            usuario.setSenha(senha);
            UsuarioService usuarioService = new UsuarioService();
            usuarioService.create(usuario);
            List<Usuario> usuarioList = usuarioService.readByCriteria(null);
            usuario = usuarioList.get(usuarioList.size() - 1);

            Pessoa pessoa = new Pessoa();
            pessoa.setNome(nome);
            pessoa.setLogradouro(logradouro);
            pessoa.setComplemento(complemento);
            pessoa.setBairro(bairro);
            pessoa.setCidade(cidade);
            pessoa.setEstado(uf);
            pessoa.setNumero(Integer.parseInt(numero));
            pessoa.setCpf(cpf);
            pessoa.setPessoaTelefone_id(pessoaTelefone.getId());
            pessoa.setUsuario_id(usuario.getId());
            PessoaService pessoaService = new PessoaService();
            pessoaService.create(pessoa);

        } catch (Exception ex) {
            //TODO resolver depois...
            ex.printStackTrace();
        }
        return mv;
    }
}
