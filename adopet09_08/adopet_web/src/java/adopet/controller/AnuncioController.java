package adopet.controller;

import adopet.utils.ConfiguracaoSistema;
import adopet.model.entity.Anuncio;
import adopet.model.entity.Especie;
import adopet.model.entity.Foto;
import adopet.model.service.AnuncioService;
import adopet.model.service.EspecieService;
import adopet.model.service.FotoService;
import adopet.utils.IOUtils;
import adopet.utils.TipoAnuncioEnum;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AnuncioController {
    
    @RequestMapping(value = "/anuncio", method = RequestMethod.GET)
    public ModelAndView read() {
        ModelAndView mv = new ModelAndView("/anuncio/list");
        AnuncioService service = new AnuncioService();
        try {
            List<Anuncio> anuncioList = service.readByCriteria(null);
            mv.addObject("anuncioList", anuncioList);
        } catch (Exception ex) {
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
            List<Anuncio> listAnuncios = service.readByCriteria(null);
            List<String> listFotoAnuncios = new ArrayList<>();
            if (!listAnuncios.isEmpty()) {
                List<Anuncio> listAdocao = new ArrayList<>();
                for (Anuncio anuncio : listAnuncios) {
                    if (anuncio.getTipo().equals("Adoção")) {
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
            List<Anuncio> listAnuncios = service.readByCriteria(null);
            List<String> listFotoAnuncios = new ArrayList<>();
            if (!listAnuncios.isEmpty()) {
                List<Anuncio> listAdocao = new ArrayList<>();
                for (Anuncio anuncio : listAnuncios) {
                    if (anuncio.getTipo().equals("Perdido")) {
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
            List<Anuncio> anuncioList = service.readByCriteria(null);
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
                List<Foto> fotoList = fotoService.readByCriteria(new HashMap<Long, Object>());
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
            anuncio.setFoto_id(fotoEntity.getId());
            if (tipo != null && tipo.equals(TipoAnuncioEnum.Perdido.name())) {
                anuncio.setRecompensa(recompensa);
                anuncio.setLocal(local);
            }

            //Aqui agente vai precisar pegar o file em bytes
            //salvar arquivo em um diretorio
            //Criar entidade imagem entidade
            //salvar no banco Mock 
            //inserir id da imagem
            //anuncio.setFoto_id(Long.parseLong("foto"));
            anuncio.setPessoaAnuncianteCpf(especie);
            anuncio.getPessoaAdotanteCpf();
            
            EspecieService especieService = new EspecieService();
            try {
                List<Especie> especieList = especieService.readByCriteria(null);
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
            }
        }
        
        return mv;
        
    }
    
    @RequestMapping(value = "/anuncio/{id}/alterar", method = RequestMethod.GET)
    public ModelAndView showFormForUpdate(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("/anuncio/form");
        
        AnuncioService service = new AnuncioService();
        EspecieService especieService = new EspecieService();
        
        try {
            Anuncio anuncio = service.readById(id);
            Especie especie = especieService.readById(anuncio.getId());
            
            mv.addObject("anuncio", anuncio);
            if (especie != null) {
                
                mv.addObject("especie", especie);
            }
        } catch (Exception ex) {
            //TODO resolver isso aqui...
        }
        
        return mv;
        
    }
    
    @RequestMapping(value = "/anuncio/{id}/alterar", method = RequestMethod.POST)
    public ModelAndView update(@PathVariable Long id, String tipo, String nome, String raca, String especie, String sexo, String porte, String idade, String caracteristica) {
        ModelAndView mv = new ModelAndView("redirect:/anuncio");
        
        Anuncio anuncio = new Anuncio();
        anuncio.setId(id);
        anuncio.setTipo(tipo);
        anuncio.setCaracteristicas(caracteristica);
        anuncio.setIdade(Integer.parseInt(idade));
        anuncio.setPorte(porte);
        anuncio.setRaca(raca);
        anuncio.setSexo(sexo);
        anuncio.setFoto_id(id);
        
        EspecieService especieService = new EspecieService();
        try {
            List<Especie> especieList = especieService.readByCriteria(null);
            for (Especie entity : especieList) {
                if (entity.getNome().equals(especie)) {
                    anuncio.setEspecie_id(entity.getId());
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        AnuncioService service = new AnuncioService();
        try {
            service.update(anuncio);
        } catch (Exception ex) {
            //TODO resolver isso aqui...
        }
        return mv;
        
    }
    
    @RequestMapping(value = "/anuncio/{id}/excluir", method = RequestMethod.GET)
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