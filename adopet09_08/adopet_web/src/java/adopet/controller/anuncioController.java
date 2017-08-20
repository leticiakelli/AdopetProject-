package adopet.controller;


import adopet.model.entity.Anuncio;
import adopet.model.entity.Especie;
import adopet.model.service.AnuncioService;
import adopet.model.service.EspecieService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class anuncioController {

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
        AnuncioService service= new AnuncioService();
        
          try {
            List<Anuncio> listAnuncios= service.readByCriteria(null);  
             if(!listAnuncios.isEmpty()) {
                 List<Anuncio> listAdocao= new ArrayList<>();
                 for (Anuncio anuncio : listAnuncios) {
                     if(anuncio.getTipo().equals("Adoção")){
                         listAdocao.add(anuncio);
                     }
                 }
                 mv.addObject("anuncioList",listAdocao);
             }
 
             
          } catch (Exception e) {
              e.printStackTrace();
          }
        return mv;
    }   
    
      @RequestMapping(value = "/perdido", method = RequestMethod.GET) 
    public ModelAndView createPerdidos() {
        ModelAndView mv = new ModelAndView("/anuncio/listPerdidos");
         AnuncioService service= new AnuncioService();
        
          try {
            List<Anuncio> listAnuncios= service.readByCriteria(null);  
             if(!listAnuncios.isEmpty()) {
                 List<Anuncio> listPerdidos= new ArrayList<>();
                 for (Anuncio anuncio : listAnuncios) {
                     if(anuncio.getTipo().equals("Perdido")){
                         listPerdidos.add(anuncio);
                     }
                 }
                 mv.addObject("anuncioList",listPerdidos);
             }
 
             
          } catch (Exception e) {
              e.printStackTrace();
          }
        return mv;
    }   
    
    @RequestMapping(value = "/anuncio/{id}/ver}", method = RequestMethod.GET)
    public ModelAndView showFormForVer(){
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
    public ModelAndView showFormForCreate(){
        ModelAndView mv = new ModelAndView("/anuncio/form");
        return mv;
        
    }
    
    @RequestMapping(value = "/anuncio/novo", method = RequestMethod.POST)
    public ModelAndView create(String tipo,String especie, String sexo, String porte, String idade, String caracteristica,String raca){
        ModelAndView mv = new ModelAndView("redirect:/anuncio");
        
        Anuncio anuncio = new Anuncio();
        anuncio.setTipo(tipo);
        anuncio.setCaracteristicas(caracteristica);
        anuncio.setIdade(Integer.parseInt(idade));
        anuncio.setPorte(porte);
        anuncio.setRaca(raca);
        anuncio.setSexo(sexo);
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
                if(entity.getNome().equals(especie)){
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
        } catch (Exception ex){
            //TODO resolver isso aqui...
        }
        return mv;
        
    }
    
    @RequestMapping(value = "/anuncio/{id}/alterar", method = RequestMethod.GET)
    public ModelAndView showFormForUpdate(@PathVariable Long id){
        ModelAndView mv = new ModelAndView("/anuncio/form");
        
        AnuncioService service = new AnuncioService();
        EspecieService especieService = new EspecieService();
        
        try {
           Anuncio anuncio = service.readById(id);
           Especie especie = especieService.readById(anuncio.getId());
           
           mv.addObject("anuncio", anuncio);
           if(especie!=null){
               
            mv.addObject("especie",especie);
           }
        } catch (Exception ex) {
            //TODO resolver isso aqui...
        }
        
        return mv;
        
    }
    
    @RequestMapping(value = "/anuncio/{id}/alterar", method = RequestMethod.POST)
    public ModelAndView update(@PathVariable Long id, String tipo, String nome,String raca, String especie, String sexo, String porte, String idade, String caracteristica){
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
                if(entity.getNome().equals(especie)){
                    anuncio.setEspecie_id(entity.getId());
                }
            }
        
        } catch (Exception e) {
            e.printStackTrace();
        } 
        AnuncioService service = new AnuncioService();
        try {
        service.update(anuncio);
        } catch (Exception ex){
            //TODO resolver isso aqui...
        }
        return mv;
        
    }
    
    
    @RequestMapping(value="/anuncio/{id}/excluir", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable Long id){     //@PathVariable injeta o valor da url e converte o valor. OBS: o nome na url tem de ser igual ao do parametro
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
