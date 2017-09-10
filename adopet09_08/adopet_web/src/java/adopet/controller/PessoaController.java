package adopet.controller;

import adopet.utils.ConfiguracaoSistema;
import adopet.model.entity.Foto;
import adopet.model.entity.Pessoa;
import adopet.model.entity.PessoaTelefone;
import adopet.model.entity.Usuario;
import adopet.model.service.FotoService;
import adopet.model.service.PessoaService;
import adopet.model.service.PessoaTelefoneService;
import adopet.model.service.UsuarioService;
import adopet.utils.IOUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PessoaController {

    @RequestMapping(value = "/pessoa/cadastro", method = RequestMethod.GET)
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

    @RequestMapping(value = "/pessoa/cadastro", method = RequestMethod.POST)
    public ModelAndView create(String email, String senha, String nome, String cpf,
            String logradouro, String numero, String complemento, String cidade,
            String bairro, String uf, String telefone, String celular, MultipartFile foto) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        ModelAndView mv = new ModelAndView("redirect:/adocao");
        //Criar novo usuario
        //Nome do arquivo
        String nomeArquivo = nome + System.currentTimeMillis() + ConfiguracaoSistema.extensaoImagem;
        //Entidade foto
        Foto fotoEntity = null;
        //Criar arquivo no caminho
        if (foto != null && nomeArquivo != null) {
            //Cria arquivo
            IOUtils.createFile(ConfiguracaoSistema.caminhoImagem, nomeArquivo, foto);
            //Seta nome do arquivo
            fotoEntity = new Foto();
            fotoEntity.setNome(nomeArquivo);
            //Salva no banco
            FotoService fotoService = new FotoService();
            try {
                //cria no sgbd
                fotoService.create(fotoEntity);
                //recuperar para pegar o id
                List<Foto> fotoList = fotoService.readByCriteria(new HashMap<Long, Object>());
                fotoEntity = fotoList.get(fotoList.size() - 1);
            } catch (Exception e) {
                e.printStackTrace();
                fotoEntity = null;
            }

        } else {
            System.err.println("Não salvou o arquivo");
        }
        UsuarioService usuarioService = new UsuarioService();
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(senha);

        if (usuario.getEmail() != null
                && !usuario.getEmail().isEmpty() && usuario.getSenha() != null
                && !usuario.getSenha().isEmpty() && usuarioService.validateForCreate(usuario)) {
            List<Usuario> listUsuario = new ArrayList<Usuario>();
            try {
                usuarioService.create(usuario);
                listUsuario = usuarioService.readByCriteria(new HashMap<Long, Object>());
            } catch (Exception e) {
                e.printStackTrace();
                usuario = null;
            }

            if (usuario != null) {
                usuario.setId(listUsuario.get(listUsuario.size() - 1).getId());
                Pessoa pessoa = new Pessoa();
                pessoa.setUsuario_id(usuario.getId());
                pessoa.setNome(nome);
                pessoa.setCpf(cpf);
                pessoa.setLogradouro(logradouro);
                pessoa.setNumero(Integer.parseInt(numero));
                pessoa.setComplemento(complemento);
                pessoa.setCidade(cidade);
                pessoa.setBairro(bairro);
                pessoa.setEstado(uf);

                //telefone service
                PessoaTelefoneService pessoaTelefoneService = new PessoaTelefoneService();
                //cria no sgbd
                PessoaTelefone pessoaTelefone = new PessoaTelefone();
                pessoaTelefone.setTelefone(telefone);
                pessoaTelefone.setCelular(celular);
                try {
                    pessoaTelefoneService.create(pessoaTelefone);
                    //recuperar para pegar o id
                    List<PessoaTelefone> list = pessoaTelefoneService.readByCriteria(new HashMap<Long, Object>());

                    pessoa.setPessoaTelefone_id(list.get(list.size() - 1).getId());
                    //Se foto estiver vazio nao cadastra
                    if (fotoEntity != null) {
                        pessoa.setFoto_id(fotoEntity.getId());

                        //Cria pessoa no banco
                        PessoaService pessoaService = new PessoaService();
                        pessoaService.create(pessoa);

                    }

                } catch (Exception e) {
                    e.printStackTrace();

                }
            }

        }
        usuario = new Usuario();
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

    @RequestMapping(value = "/pessoa/cadastro/update", method = RequestMethod.GET)
    public ModelAndView update() {
        ModelAndView mv = new ModelAndView("/pessoa/formCadastre");

        //usuario
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("usuarioLogado");
        if (email != null && !email.isEmpty()) {
            UsuarioService serviceUsuario = new UsuarioService();
            try {
                //TODO: inserir criteria
                List<Usuario> usuarioList = serviceUsuario.readByCriteria(new HashMap<Long, Object>());
                if (usuarioList != null && !usuarioList.isEmpty()) {
                    for (Usuario usuario : usuarioList) {
                        if (usuario.getEmail().equals(email)) {

                            //pessoa
                            PessoaService service = new PessoaService();
                            try {
                                List<Pessoa> pessoaList = service.readByCriteria(null);
                                for (Pessoa pessoa : pessoaList) {
                                    if (usuario.getId() == pessoa.getUsuario_id()) {

                                        //Pessoa telefone
                                        PessoaTelefoneService pessoaTelefoneService = new PessoaTelefoneService();
                                        try {
                                            List<PessoaTelefone> pessoaTelefoneList = pessoaTelefoneService.readByCriteria(null);
                                            for (PessoaTelefone pessoaTelefoneEntity : pessoaTelefoneList) {
                                                if (pessoa.getPessoaTelefone_id() == pessoaTelefoneEntity.getId()) {
                                                    mv.addObject("usuario", usuario);
                                                    mv.addObject("pessoa", pessoa);
                                                    mv.addObject("telefone", pessoaTelefoneEntity);
                                                    break;
                                                }
                                            }
                                        } catch (Exception ex) {
                                            //TODO resolver depois...
                                        }
                                        break;
                                    }
                                }
                            } catch (Exception ex) {
                                //TODO resolver depois...
                            }

                            break;
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            //TODO: tratar usuario não logado
            mv = new ModelAndView("redirect:/login");
        }

        return mv;
    }

    @RequestMapping(value = "/pessoa/cadastro/update", method = RequestMethod.POST)
    public ModelAndView update(String email, String senha, String nome, String cpf,
            String logradouro, String numero, String complemento, String cidade,
            String bairro, String uf, String telefone, String celular, MultipartFile foto, String pessoaId, String usuarioId) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        ModelAndView mv = new ModelAndView("redirect:/adocao");

        //Atualiza usuario
        //Nome do arquivo
        Foto fotoEntity = null;
        if (foto != null || !foto.isEmpty()) {
            String nomeArquivo = nome + System.currentTimeMillis() + ConfiguracaoSistema.extensaoImagem;
            //Entidade foto

            //Criar arquivo no caminho
            if (foto != null && nomeArquivo != null) {
                //Cria arquivo
                IOUtils.createFile(ConfiguracaoSistema.caminhoImagem, nomeArquivo, foto);
                //Seta nome do arquivo
                fotoEntity = new Foto();
                fotoEntity.setNome(nomeArquivo);
                //Salva no banco
                FotoService fotoService = new FotoService();
                try {
                    //cria no sgbd
                    fotoService.create(fotoEntity);
                    //recuperar para pegar o id
                    List<Foto> fotoList = fotoService.readByCriteria(new HashMap<Long, Object>());
                    fotoEntity = fotoList.get(fotoList.size() - 1);
                } catch (Exception e) {
                    e.printStackTrace();
                    fotoEntity = null;
                }

            } else {
                System.err.println("Não salvou o arquivo");
            }
        }
        UsuarioService usuarioService = new UsuarioService();
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(senha);

        if (usuario.getEmail() != null
                && !usuario.getEmail().isEmpty() && usuario.getSenha() != null
                && !usuario.getSenha().isEmpty()) {
            Usuario usuarioLogado = null;
            List<Usuario> listUsuario = new ArrayList<Usuario>();
            try {
                usuarioLogado = usuarioService.readById(Long.parseLong(usuarioId));
                usuarioLogado.setEmail(email);
                usuarioService.update(usuarioLogado);

            } catch (Exception e) {
                e.printStackTrace();
                usuario = null;
            }

            if (usuarioLogado != null) {
                Pessoa pessoa = null;
                try {
                    PessoaService pessoaService = new PessoaService();
                    pessoa = pessoaService.readById(Long.parseLong(pessoaId));
                    pessoa.setNome(nome);
                    pessoa.setCpf(cpf);
                    pessoa.setLogradouro(logradouro);
                    pessoa.setNumero(Integer.parseInt(numero));
                    pessoa.setComplemento(complemento);
                    pessoa.setCidade(cidade);
                    pessoa.setBairro(bairro);
                    pessoa.setEstado(uf);

                    //telefone service
                    PessoaTelefoneService pessoaTelefoneService = new PessoaTelefoneService();
                    //TODO: limpar todos os telefones desta pessoa no sgbd

                    //cria no sgbd
                    PessoaTelefone pessoaTelefone = new PessoaTelefone();
                    pessoaTelefone.setTelefone(telefone);
                    pessoaTelefone.setCelular(celular);
                    try {
                        pessoaTelefoneService.create(pessoaTelefone);
                        //recuperar para pegar o id
                        List<PessoaTelefone> list = pessoaTelefoneService.readByCriteria(new HashMap<Long, Object>());

                        pessoa.setPessoaTelefone_id(list.get(list.size() - 1).getId());
                        //Se foto estiver vazio nao cadastra
                        if (fotoEntity != null) {
                            pessoa.setFoto_id(fotoEntity.getId());
                        }

                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                    pessoaService.update(pessoa);

                } catch (Exception e) {
                    e.printStackTrace();
                    usuario = null;
                }

            }

        }

        return mv;

    }
}
