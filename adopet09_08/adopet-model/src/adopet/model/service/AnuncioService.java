
package adopet.model.service;

import adopet.model.base.service.BaseAnuncioService;
import adopet.model.entity.Anuncio;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class AnuncioService implements BaseAnuncioService{
  private static Long sequence = 0L;
    private static List<Anuncio> entityList = new ArrayList<>();

    @Override
    public void create(Anuncio entity) throws Exception {
        entity.setId(++sequence);
        entityList.add(entity);
    }

    @Override
    public Anuncio readById(Long id) throws Exception {
        Anuncio entity = null;
        for (Anuncio aux : entityList) {
            if (aux.getId().equals(id)) {
                entity = aux;
                break;
            }
        }
        return entity;
    }

    @Override
    public List<Anuncio> readByCriteria(Map<Long, Object> criteria) throws Exception {
        return entityList;
    }

    @Override
    public void update(Anuncio entity) throws Exception {
        for (Anuncio aux : entityList) {
            if (aux.getId().equals(entity.getId())) {
               aux.setData_hora(entity.getData_hora());
               aux.setSexo(entity.getSexo());
               aux.setPorte(entity.getPorte());
               aux.setRaca(entity.getRaca());
               aux.setRecompensa(entity.getRecompensa());
               aux.setCaracteristicas(entity.getCaracteristicas());
               aux.setTipo(entity.getTipo());
               aux.setStatus(entity.getStatus());
               aux.setIdade(entity.getIdade());
               aux.setLocal(entity.getLocal());
               aux.setFoto_id(entity.getFoto_id());
               aux.setEspecie_id(entity.getEspecie_id());
               aux.setPessoaAnuncianteCpf(entity.getPessoaAnuncianteCpf());
               aux.setPessoaAdotanteCpf(entity.getPessoaAdotanteCpf());
                break;
            }
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        for (Anuncio aux : entityList) {
            if (aux.getId().equals(id)) {
                entityList.remove(aux);
                break;
            }
        }
    }  
}
