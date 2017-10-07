package adopet.controller;

import adopet.model.criteria.PessoaCriteria;
import adopet.model.criteria.UsuarioCriteria;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PessoaController {

    @RequestMapping(value = "/pessoa/cadastro", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView mv = new ModelAndView("/pessoa/formCadastre");

        PessoaService service = new PessoaService();
        try {
            List<Pessoa> pessoaList = service.readByCriteria(new HashMap<Long, Object>(), null, null);
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
        List<String> errors = new ArrayList<>();

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
                List<Foto> fotoList = fotoService.readByCriteria(new HashMap<Long, Object>(), null, null);
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

        Pessoa pessoa = null;
        if (usuario.getEmail() != null
                && !usuario.getEmail().isEmpty() && usuario.getSenha() != null
                && !usuario.getSenha().isEmpty()) {
            List<Usuario> listUsuario = new ArrayList<Usuario>();
            try {
                usuarioService.create(usuario);
                listUsuario = usuarioService.readByCriteria(new HashMap<Long, Object>(), null, null);
                usuario = listUsuario.get(listUsuario.size() - 1);
            } catch (Exception e) {
                e.printStackTrace();
                usuario = null;
            }

            if (usuario != null) {

                pessoa = new Pessoa();
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
                    List<PessoaTelefone> list = pessoaTelefoneService.readByCriteria(new HashMap<Long, Object>(), null, null);

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
                List<Usuario> usuarioList = serviceUsuario.readByCriteria(new HashMap<Long, Object>(), null, null);
                if (usuarioList != null && !usuarioList.isEmpty()) {
                    for (Usuario usuario : usuarioList) {
                        if (usuario.getEmail().equals(email)) {

                            //pessoa
                            PessoaService service = new PessoaService();
                            try {
                                List<Pessoa> pessoaList = service.readByCriteria(new HashMap<Long, Object>(), null, null);
                                for (Pessoa pessoa : pessoaList) {
                                    if (usuario.getId() == pessoa.getUsuario_id()) {

                                        //Pessoa telefone
                                        PessoaTelefoneService pessoaTelefoneService = new PessoaTelefoneService();
                                        try {
                                            List<PessoaTelefone> pessoaTelefoneList = pessoaTelefoneService.readByCriteria(new HashMap<Long, Object>(), null, null);
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
        if (foto != null && !foto.isEmpty() && foto.getSize() > 0) {
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
                    List<Foto> fotoList = fotoService.readByCriteria(new HashMap<Long, Object>(), null, null);
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
                    Map<Long, Object> criteria = new HashMap<>();
                    criteria.put(PessoaCriteria.USUARIO_ID_EQ, Long.parseLong(usuarioId));
                    List<Pessoa> pessoaDbList = pessoaService.readByCriteria(criteria, null, null);
                    if (pessoaDbList != null && !pessoaDbList.isEmpty()) {
                        pessoa = pessoaDbList.get(0);
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
                            List<PessoaTelefone> list = pessoaTelefoneService.readByCriteria(new HashMap<Long, Object>(), null, null);

                            pessoa.setPessoaTelefone_id(list.get(list.size() - 1).getId());
                            //Se foto estiver vazio nao cadastra
                            if (fotoEntity != null) {
                                pessoa.setFoto_id(fotoEntity.getId());
                            }

                        } catch (Exception e) {
                            e.printStackTrace();

                        }
                        pessoaService.update(pessoa);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    usuario = null;
                }

            }

        }

        return mv;

    }
}
