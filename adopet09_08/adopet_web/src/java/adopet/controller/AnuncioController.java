package adopet.controller;

import adopet.model.criteria.PessoaTelefoneCriteria;
import adopet.model.criteria.TimelineCriteria;
import adopet.utils.ConfiguracaoSistema;
import adopet.model.entity.Anuncio;
import adopet.model.entity.Especie;
import adopet.model.entity.Foto;
import adopet.model.entity.Pessoa;
import adopet.model.entity.PessoaTelefone;
import adopet.model.entity.Timeline;
import adopet.model.entity.Usuario;
import adopet.model.service.AnuncioService;
import adopet.model.service.EspecieService;
import adopet.model.service.FotoService;
import adopet.model.service.PessoaService;
import adopet.model.service.PessoaTelefoneService;
import adopet.model.service.TimelineService;
import adopet.model.service.UsuarioService;
import adopet.utils.IOUtils;
import adopet.utils.TipoAnuncioEnum;
import adopet.utils.TipoSexoEnum;
import adopet.utils.TipoStatusAnuncioEnum;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AnuncioController {

    @RequestMapping(value = "/anuncio", method = RequestMethod.GET)
    public ModelAndView readByGerenciador() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        ModelAndView mv = new ModelAndView("/anuncio/list");
        //Anuncios do gestor
        AnuncioService service = new AnuncioService();
        try {
            List<Anuncio> anuncioList = service.readByCriteria(new HashMap<Long, Object>(), null, null);
            List<Anuncio> anuncioUsuarioList = new ArrayList<Anuncio>();
            HttpSession session = request.getSession();
            String cpf = (String) session.getAttribute("pessoaCpfLogado");
            for (Anuncio anuncio : anuncioList) {
                if (anuncio.getPessoaAnuncianteCpf().equals(cpf)) {
                    anuncioUsuarioList.add(anuncio);
                }
            }
            mv.addObject("anuncioList", anuncioUsuarioList);
        } catch (Exception ex) {
            ex.printStackTrace();
            //TODO resolver depois...
        }

        //Anuncios com solicitacao
        try {
            //Pessoa
            List<Pessoa> pessoaSolicitanteList = new ArrayList<>();
            PessoaService pessoaService = new PessoaService();

            //Anuncio
            Map<Long, Object> criteriaSolicitacao = new HashMap<>();
            List<Anuncio> anuncioListSolicitacao = service.readByCriteria(criteriaSolicitacao, null, null);
            List<Anuncio> anuncioSolicitacaoUsuarioList = new ArrayList<Anuncio>();
            HttpSession session = request.getSession();
            String cpf = (String) session.getAttribute("pessoaCpfLogado");
            for (Anuncio anuncio : anuncioListSolicitacao) {
                if (anuncio.getPessoaAnuncianteCpf().equals(cpf) && anuncio.getPessoaAdotanteCpf() != null
                        && !anuncio.getPessoaAdotanteCpf().isEmpty()
                        && (anuncio.getStatus().equals(TipoStatusAnuncioEnum.pendente.name()))) {
                    anuncioSolicitacaoUsuarioList.add(anuncio);
                    pessoaSolicitanteList.add(pessoaService.readByCpf(anuncio.getPessoaAdotanteCpf()));
                }
            }
            mv.addObject("anuncioSolicitacaoList", anuncioSolicitacaoUsuarioList);
            mv.addObject("solicitanteList", pessoaSolicitanteList);
        } catch (Exception ex) {
            ex.printStackTrace();
            //TODO resolver depois...
        }

        return mv;
    }

    @RequestMapping(value = "/adocao", method = RequestMethod.GET)
    public ModelAndView createAdocao() {
        ModelAndView mv = new ModelAndView("/anuncio/listAdocao");
        AnuncioService service = new AnuncioService();

        try {
            //Recupera a foto da base de dados
            FotoService fotoService = new FotoService();
            List<Anuncio> listAnuncios = service.readByCriteria(new HashMap<Long, Object>(), null, null);
            List<String> listFotoAnuncios = new ArrayList<>();
            if (!listAnuncios.isEmpty()) {
                List<Anuncio> listAdocao = new ArrayList<>();
                for (Anuncio anuncio : listAnuncios) {
                    if (anuncio.getTipo().equals("adocao") && anuncio.getStatus().equals(TipoStatusAnuncioEnum.pendente.name())) {
                        listAdocao.add(anuncio);
                        //Recupera a entidade Foto
                        Foto foto = fotoService.readById(anuncio.getFoto_id());
                        //Pega o arquivo no diretório
                        byte[] byteArrayFoto = IOUtils.readFile(foto.getNome());
                        //Converte para o padrão Base64 de imagens
                        byte[] byteArrayFotoBase64 = Base64.getEncoder().encode(byteArrayFoto);
                        //Insere na lista
                        if (byteArrayFotoBase64 != null) {
                            listFotoAnuncios.add(new String(byteArrayFotoBase64));
                        }
                    }
                }
                mv.addObject("anuncioList", listAdocao);
                mv.addObject("anuncioImageList", listFotoAnuncios);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    @RequestMapping(value = "/perdido", method = RequestMethod.GET)
    public ModelAndView createPerdidos() {
        ModelAndView mv = new ModelAndView("/anuncio/listPerdidos");
        AnuncioService service = new AnuncioService();

        try {
            //Recupera a foto da base de dados
            FotoService fotoService = new FotoService();
            List<Anuncio> listAnuncios = service.readByCriteria(new HashMap<Long, Object>(), null, null);
            List<String> listFotoAnuncios = new ArrayList<>();
            if (!listAnuncios.isEmpty()) {
                List<Anuncio> listAdocao = new ArrayList<>();
                for (Anuncio anuncio : listAnuncios) {
                    if (anuncio.getTipo().equals("perdido") && anuncio.getStatus().equals(TipoStatusAnuncioEnum.pendente.name())) {
                        listAdocao.add(anuncio);
                        //Recupera a entidade Foto
                        Foto foto = fotoService.readById(anuncio.getFoto_id());
                        //Pega o arquivo no diretório
                        byte[] byteArrayFoto = IOUtils.readFile(foto.getNome());
                        //Converte para o padrão Base64 de imagens
                        byte[] byteArrayFotoBase64 = Base64.getEncoder().encode(byteArrayFoto);
                        //Insere na lista
                        if (byteArrayFotoBase64 != null) {
                            listFotoAnuncios.add(new String(byteArrayFotoBase64));
                        }
                    }
                }
                mv.addObject("anuncioList", listAdocao);
                mv.addObject("anuncioImageList", listFotoAnuncios);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    @RequestMapping(value = "/posAdocao", method = RequestMethod.GET)
    public ModelAndView readPosAdocao() {
        ModelAndView mv = new ModelAndView("/posAdocao/posAdocao");
        AnuncioService service = new AnuncioService();

        try {
            //Recupera a foto da base de dados
            FotoService fotoService = new FotoService();
            List<Anuncio> listAnuncios = service.readByCriteria(new HashMap<Long, Object>(), null, null);
            List<String> listFotoAnuncios = new ArrayList<>();
            if (!listAnuncios.isEmpty()) {
                List<Anuncio> listAdocao = new ArrayList<>();
                for (Anuncio anuncio : listAnuncios) {
                    if (anuncio.getTipo().equals("adocao") && !anuncio.getStatus().equals(TipoStatusAnuncioEnum.pendente.name())) {
                        listAdocao.add(anuncio);
                        //Recupera a entidade Foto
                        Foto foto = fotoService.readById(anuncio.getFoto_id());
                        //Pega o arquivo no diretório
                        byte[] byteArrayFoto = IOUtils.readFile(foto.getNome());
                        //Converte para o padrão Base64 de imagens
                        byte[] byteArrayFotoBase64 = Base64.getEncoder().encode(byteArrayFoto);
                        //Insere na lista
                        if (byteArrayFotoBase64 != null) {
                            listFotoAnuncios.add(new String(byteArrayFotoBase64));
                        }
                    }
                }
                //usuario
                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                HttpSession session = request.getSession();
                String email = (String) session.getAttribute("usuarioLogado");
                mv.addObject("usuarioLogado", email);
                mv.addObject("anuncioList", listAdocao);
                mv.addObject("anuncioImageList", listFotoAnuncios);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    @RequestMapping(value = "/anuncio/{id}/ver}", method = RequestMethod.GET)
    public ModelAndView showFormForVer() {
        ModelAndView mv = new ModelAndView("/anuncio/new");
        return mv;
    }

    @RequestMapping(value = "/anuncio/{id}/ver", method = RequestMethod.GET) // /categorias = requisição do usuário na URL
    public ModelAndView Ver() {
        ModelAndView mv = new ModelAndView("/anuncio/new");
        AnuncioService service = new AnuncioService();
        try {
            List<Anuncio> anuncioList = service.readByCriteria(new HashMap<Long, Object>(), null, null);
            mv.addObject("anuncioList", anuncioList);
        } catch (Exception ex) {
            //TODO resolver depois...
        }
        return mv;
    }

    @RequestMapping(value = "/anuncio/novo", method = RequestMethod.GET)
    public ModelAndView showFormForCreate() {
        ModelAndView mv = new ModelAndView("/anuncio/form");
        return mv;

    }

    @RequestMapping(value = "/anuncio/novo", method = RequestMethod.POST)
    public ModelAndView create(String tipo, String especie, String sexo, String porte, String idade, String caracteristica, String raca, MultipartFile foto, String recompensa, String local) {
        ModelAndView mv = new ModelAndView("redirect:/anuncio");

        //Tipos
        if (tipo.equals("Adoção")) {
            tipo = TipoAnuncioEnum.adocao.name();
        } else if (tipo.equals("Perdido")) {
            tipo = TipoAnuncioEnum.perdido.name();
        }

        if (sexo.equals("Macho")) {
            sexo = TipoSexoEnum.m.name();
        } else if (tipo.equals("Fêmea")) {
            sexo = TipoSexoEnum.f.name();
        }
        //Nome do arquivo
        String nomeArquivo = especie + System.currentTimeMillis() + ConfiguracaoSistema.extensaoImagem;
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

        if (fotoEntity != null) {
            Anuncio anuncio = new Anuncio();
            anuncio.setTipo(tipo);
            anuncio.setCaracteristicas(caracteristica);
            anuncio.setIdade(Integer.parseInt(idade));
            anuncio.setPorte(porte);
            anuncio.setRaca(raca);
            anuncio.setSexo(sexo);
            anuncio.setStatus(TipoStatusAnuncioEnum.pendente.name());
            anuncio.setFoto_id(fotoEntity.getId());
            if (tipo != null && tipo.equals(TipoAnuncioEnum.perdido.name())) {
                anuncio.setRecompensa(recompensa);
                anuncio.setLocal(local);
            }

            //Aqui agente vai precisar pegar o file em bytes
            //salvar arquivo em um diretorio
            //Criar entidade imagem entidade
            //salvar no banco Mock 
            //inserir id da imagem
            //anuncio.setFoto_id(Long.parseLong("foto"));
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpSession session = request.getSession();
            String cpf = (String) session.getAttribute("pessoaCpfLogado");

            anuncio.setPessoaAnuncianteCpf(cpf);

            EspecieService especieService = new EspecieService();
            try {
                List<Especie> especieList = especieService.readByCriteria(new HashMap<Long, Object>(), null, null);
                for (Especie entity : especieList) {
                    if (entity.getNome().equals(especie)) {
                        anuncio.setEspecie_id(entity.getId());
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

//        anuncio.setFoto_id();
            AnuncioService service = new AnuncioService();
            try {
                service.create(anuncio);
            } catch (Exception ex) {
                //TODO resolver isso aqui...
                ex.printStackTrace();

            }
        }

        return mv;

    }

    @RequestMapping(value = "/anuncio/{id}/read", method = RequestMethod.GET)
    public ModelAndView read(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("/anuncio/read");

        AnuncioService service = new AnuncioService();
        EspecieService especieService = new EspecieService();

        try {
            Anuncio anuncio = service.readById(id);
            Especie especie = especieService.readById(anuncio.getEspecie_id());
            if (anuncio.getTipo().equals(TipoAnuncioEnum.adocao.name())) {
                mv = new ModelAndView("/anuncio/adocao/read");
            } else {
                mv = new ModelAndView("/anuncio/perdido/read");
            }

            FotoService fotoService = new FotoService();
            Foto foto = fotoService.readById(anuncio.getFoto_id());
            //Pega o arquivo no diretório
            byte[] byteArrayFoto = IOUtils.readFile(foto.getNome());
            //Converte para o padrão Base64 de imagens
            byte[] byteArrayFotoBase64 = Base64.getEncoder().encode(byteArrayFoto);
            //Insere na lista
            if (byteArrayFotoBase64 != null) {
                mv.addObject("imageBase64", new String(byteArrayFotoBase64));
            }

            TimelineService timelineService = new TimelineService();
            Map<Long, Object> criteriaTimeline = new HashMap<Long, Object>();

            criteriaTimeline.put(TimelineCriteria.ANUNCIO_ID_EQ, id);
            List<Timeline> timelineList = timelineService.readByCriteria(criteriaTimeline, null, null);
            PessoaService pessoaService = new PessoaService();
            List<Pessoa> pessoaList = pessoaService.readByCriteria(new HashMap<Long, Object>(), null, null);
            List<Pessoa> pessoaTimilene = new ArrayList<>();
            for (Timeline timeline1 : timelineList) {
                for (Pessoa pessoa : pessoaList) {
                    if (pessoa.getCpf().equals(timeline1.getPessoa_cpf())) {
                        pessoaTimilene.add(pessoa);
                        break;
                    }
                }
            }
            mv.addObject("pessoaTimeline", pessoaTimilene);
            mv.addObject("timelineList", timelineList);
            mv.addObject("anuncio", anuncio);
            if (especie != null) {
                mv.addObject("especie", especie);
            }
        } catch (Exception ex) {
            //TODO resolver isso aqui...
        }

        return mv;

    }

    @RequestMapping(value = "/anuncio/{id}/read", method = RequestMethod.POST)
    public ModelAndView showFormRead(@PathVariable Long id, String texto) throws Exception {
        ModelAndView mv = new ModelAndView("redirect:/anuncio/" + id + "/read");
        Timeline timeline = new Timeline();
        timeline.setAnuncio_id(id);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        String cpf = (String) session.getAttribute("pessoaCpfLogado");
        timeline.setPessoa_cpf(cpf);
        String email = (String) session.getAttribute("usuarioLogado");
        mv.addObject("pessoaCpfLogado", cpf);
        mv.addObject("usuarioLogado", email);
        timeline.setTexto(texto);
        timeline.setAnuncio_id(id);
        timeline.setData_hora(new Timestamp(System.currentTimeMillis()));
        try {
            TimelineService timelineService = new TimelineService();
            timelineService.create(timeline);
        } catch (Exception ex) {
            Logger.getLogger(AnuncioController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            TimelineService timelineService = new TimelineService();
            Map<Long, Object> criteriaTimeline = new HashMap<Long, Object>();
            criteriaTimeline.put(TimelineCriteria.ANUNCIO_ID_EQ, id);
            List<Timeline> timelineList = timelineService.readByCriteria(criteriaTimeline, null, null);
            PessoaService pessoaService = new PessoaService();
            List<Pessoa> pessoaList = pessoaService.readByCriteria(new HashMap<Long, Object>(), null, null);
            List<Pessoa> pessoaTimilene = new ArrayList<>();
            for (Timeline timeline1 : timelineList) {
                for (Pessoa pessoa : pessoaList) {
                    if (pessoa.getCpf().equals(timeline1.getPessoa_cpf())) {
                        pessoaTimilene.add(pessoa);
                        break;
                    }
                }
            }
            mv.addObject("pessoaTimeline", pessoaTimilene);
            mv.addObject("timelineList", timelineList);
        } catch (Exception e) {
        }

        return mv;

    }

    /**
     * Método responsável por tratar e carregar os recursos da solicitacao detalhadas
     * @param id
     * @return 
     */
    @RequestMapping(value = "/anuncio/{id}/readSolicitacao", method = RequestMethod.GET)
    public ModelAndView readSolicitacao(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("/anuncio/readSolicitacao");

        AnuncioService service = new AnuncioService();
        EspecieService especieService = new EspecieService();

        try {
            Anuncio anuncio = service.readById(id);
            Especie especie = especieService.readById(anuncio.getEspecie_id());
            if (anuncio.getTipo().equals(TipoAnuncioEnum.adocao.name())) {
                mv = new ModelAndView("/anuncio/adocao/readSolicitacao");
            } else {
                mv = new ModelAndView("/anuncio/perdido/readSolicitacao");
            }

            FotoService fotoService = new FotoService();
            Foto foto = fotoService.readById(anuncio.getFoto_id());
            //Pega o arquivo no diretório
            byte[] byteArrayFoto = IOUtils.readFile(foto.getNome());
            //Converte para o padrão Base64 de imagens
            byte[] byteArrayFotoBase64 = Base64.getEncoder().encode(byteArrayFoto);
            //Insere na lista
            if (byteArrayFotoBase64 != null) {
                mv.addObject("imageBase64", new String(byteArrayFotoBase64));
            }

            TimelineService timelineService = new TimelineService();
            Map<Long, Object> criteriaTimeline = new HashMap<Long, Object>();

            criteriaTimeline.put(TimelineCriteria.ANUNCIO_ID_EQ, id);
            List<Timeline> timelineList = timelineService.readByCriteria(criteriaTimeline, null, null);
            PessoaService pessoaService = new PessoaService();
            List<Pessoa> pessoaList = pessoaService.readByCriteria(new HashMap<Long, Object>(), null, null);
            List<Pessoa> pessoaTimilene = new ArrayList<>();
            for (Timeline timeline1 : timelineList) {
                for (Pessoa pessoa : pessoaList) {
                    if (pessoa.getCpf().equals(timeline1.getPessoa_cpf())) {
                        pessoaTimilene.add(pessoa);
                        break;
                    }
                }
            }
            mv.addObject("pessoaTimeline", pessoaTimilene);
            mv.addObject("timelineList", timelineList);
            mv.addObject("anuncio", anuncio);
            if (especie != null) {
                mv.addObject("especie", especie);
            }

            //Pessoa solicitante
            Pessoa solicitante = null;
            solicitante = pessoaService.readByCpf(anuncio.getPessoaAdotanteCpf());
            //Pessoa telefone 
            PessoaTelefone telefoneSolicitante= null;
            PessoaTelefoneService serviceTelefone = new PessoaTelefoneService();
            telefoneSolicitante=serviceTelefone.readById(solicitante.getPessoaTelefone_id());
            //Usuario 
            Usuario usuarioSolicitante= null;
            UsuarioService serviceUsuario= new UsuarioService();
            usuarioSolicitante=serviceUsuario.readById(solicitante.getUsuario_id());
            
            
            
            if (solicitante != null && telefoneSolicitante != null && usuarioSolicitante != null  ) {
                mv.addObject("usuario", usuarioSolicitante);
                mv.addObject("telefone", telefoneSolicitante);
                mv.addObject("solicitante", solicitante);
            }

        } catch (Exception ex) {
            //TODO resolver isso aqui...
            ex.printStackTrace();
        }

        return mv;

    }

    /**
     * Método que confirma a solicitacao pelo POST
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/anuncio/{id}/readSolicitacao/confirm", method = RequestMethod.POST)
    public ModelAndView confirmSolicitacao(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("redirect:/anuncio/");
        Anuncio anuncio = null;
        AnuncioService anuncioService = new AnuncioService();
        try {
            anuncio = anuncioService.readById(id);
            
             //Teste se o usuario que alterou é o dono do anuncio
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpSession session = request.getSession();
            String cpf = (String) session.getAttribute("pessoaCpfLogado");
            
            if (anuncio != null && anuncio.getPessoaAnuncianteCpf().equals(cpf)) {
                if (anuncio.getTipo().equals(TipoAnuncioEnum.adocao.name())) {
                    anuncio.setStatus(TipoStatusAnuncioEnum.adotado.name());
                } else {
                    anuncio.setStatus(TipoStatusAnuncioEnum.encontrado.name());
                }
                anuncioService.update(anuncio);

            }
        } catch (Exception ex) {
            Logger.getLogger(AnuncioController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return mv;

    }

    /**
     * Método que deleta a solicitacao pelo POST
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/anuncio/{id}/readSolicitacao/delete", method = RequestMethod.POST)
    public ModelAndView deleteSolicitacao(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("redirect:/anuncio/");
        Anuncio anuncio = null;
        AnuncioService anuncioService = new AnuncioService();
        try {
            anuncio = anuncioService.readById(id);
            
            //Teste se o usuario que alterou é o dono do anuncio
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpSession session = request.getSession();
            String cpf = (String) session.getAttribute("pessoaCpfLogado");
            
            if (anuncio != null && anuncio.getPessoaAnuncianteCpf().equals(cpf)) {
                anuncio.setPessoaAdotanteCpf(null);

            }
            anuncioService.update(anuncio);
        } catch (Exception ex) {
            Logger.getLogger(AnuncioController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return mv;

    }

    @RequestMapping(value = "/anuncio/{id}/update", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("/anuncio/form");

        AnuncioService service = new AnuncioService();
        EspecieService especieService = new EspecieService();

        try {
            Anuncio anuncio = service.readById(id);
            Especie especie = especieService.readById(anuncio.getEspecie_id());

            mv.addObject("anuncio", anuncio);
            if (especie != null) {

                mv.addObject("especie", especie);
            }
        } catch (Exception ex) {
            //TODO resolver isso aqui...
        }

        return mv;

    }

    @RequestMapping(value = "/anuncio/{anuncioId}/update", method = RequestMethod.POST)
    public ModelAndView update(@PathVariable String anuncioId, String tipo, String nome, String raca, String especie, String sexo, String porte, String idade, String caracteristica, MultipartFile foto, String recompensa, String local) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        ModelAndView mv = new ModelAndView("redirect:/anuncio");
        AnuncioService service = new AnuncioService();

        //Tipos
        if (tipo.equals("Adoção")) {
            tipo = TipoAnuncioEnum.adocao.name();
        } else if (tipo.equals("Perdido")) {
            tipo = TipoAnuncioEnum.perdido.name();
        }

        if (sexo.equals("Macho")) {
            sexo = TipoSexoEnum.m.name();
        } else if (tipo.equals("Fêmea")) {
            sexo = TipoSexoEnum.f.name();
        }
        Foto fotoEntity = null;
        if (foto != null && !foto.isEmpty() && foto.getSize() > 0) {
            String nomeArquivo = nome + System.currentTimeMillis() + ConfiguracaoSistema.extensaoImagem;
            //Entidade foto

            //Criar arquivo no caminho
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
        AnuncioService anuncioService = new AnuncioService();

        Anuncio anuncio = new Anuncio();
        try {
            anuncio = anuncioService.readById(Long.parseLong(anuncioId));
            anuncio.setTipo(tipo);
            anuncio.setCaracteristicas(caracteristica);
            anuncio.setIdade(Integer.parseInt(idade));
            anuncio.setPorte(porte);
            anuncio.setRaca(raca);
            anuncio.setSexo(sexo);
            if (tipo != null && tipo.equals(TipoAnuncioEnum.perdido.name())) {
                anuncio.setRecompensa(recompensa);
                anuncio.setLocal(local);
            }
            if (fotoEntity != null) {
                anuncio.setFoto_id(fotoEntity.getId());
            }

            anuncioService.update(anuncio);

        } catch (Exception e) {
            e.printStackTrace();
            mv = new ModelAndView("/anuncio/home");
        }

        return mv;
    }

    @RequestMapping(value = "/anuncio/{id}/status", method = RequestMethod.GET)
    public ModelAndView updateStatus(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("redirect:/anuncio/");

        AnuncioService service = new AnuncioService();
        EspecieService especieService = new EspecieService();

        try {
            Anuncio anuncio = service.readById(id);
            Especie especie = especieService.readById(anuncio.getEspecie_id());
            if (anuncio.getStatus().equals(TipoStatusAnuncioEnum.pendente.name()) && anuncio.getTipo().equals(TipoAnuncioEnum.adocao.name())) {
                anuncio.setStatus(TipoStatusAnuncioEnum.adotado.name());
            } else if (anuncio.getStatus().equals(TipoStatusAnuncioEnum.pendente.name()) && anuncio.getTipo().equals(TipoAnuncioEnum.perdido.name())) {
                anuncio.setStatus(TipoStatusAnuncioEnum.encontrado.name());
            } else {
                anuncio.setStatus(TipoStatusAnuncioEnum.pendente.name());
            }
            AnuncioService anuncioService = new AnuncioService();
            anuncioService.update(anuncio);
            mv.addObject("anuncio", anuncio);

            if (especie != null) {

                mv.addObject("especie", especie);
            }
        } catch (Exception ex) {
            //TODO resolver isso aqui...
        }

        return mv;

    }

    @RequestMapping(value = "/anuncio/{id}/read/status", method = RequestMethod.GET)
    public ModelAndView updateStatusDetalhe(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("redirect:/anuncio/");

        AnuncioService service = new AnuncioService();
        EspecieService especieService = new EspecieService();

        try {
            Anuncio anuncio = service.readById(id);
            Especie especie = especieService.readById(anuncio.getEspecie_id());
            //Comentado para teste, ao clicar em adotar o status n deve mudar
//            if (anuncio.getStatus().equals(TipoStatusAnuncioEnum.pendente.name()) && anuncio.getTipo().equals(TipoAnuncioEnum.adocao.name())) {
//                anuncio.setStatus(TipoStatusAnuncioEnum.adotado.name());
//                mv = new ModelAndView("redirect:/adocao/");
//            } else if (anuncio.getStatus().equals(TipoStatusAnuncioEnum.pendente.name()) && anuncio.getTipo().equals(TipoAnuncioEnum.perdido.name())) {
//                anuncio.setStatus(TipoStatusAnuncioEnum.encontrado.name());
//                mv = new ModelAndView("redirect:/perdido/");
//            } else {
//                anuncio.setStatus(TipoStatusAnuncioEnum.pendente.name());
//            }
            AnuncioService anuncioService = new AnuncioService();

            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpSession session = request.getSession();
            String cpf = (String) session.getAttribute("pessoaCpfLogado");
            anuncio.setPessoaAdotanteCpf(cpf);
            anuncioService.update(anuncio);
            mv.addObject("anuncio", anuncio);

            if (especie != null) {

                mv.addObject("especie", especie);
            }
        } catch (Exception ex) {
            //TODO resolver isso aqui...
        }

        return mv;

    }

    @RequestMapping(value = "/anuncio/{id}/delete", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable Long id) {     //@PathVariable injeta o valor da url e converte o valor. OBS: o nome na url tem de ser igual ao do parametro
        ModelAndView mv = new ModelAndView("redirect:/anuncio");

        AnuncioService service = new AnuncioService();
        try {
            service.delete(id);
        } catch (Exception ex) {
            //TODO resolver isso aqui...
        }
        return mv;
    }

}
