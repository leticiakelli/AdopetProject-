package adopet.model.service;

import adopet.model.base.service.BaseMensagemService;
import adopet.model.entity.Mensagem;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MensagemService implements BaseMensagemService{
  private static Long sequence = 0L;
    private static List<Mensagem> entityList = new ArrayList<>();

    @Override
    public void create(Mensagem entity) throws Exception {
        entity.setId(++sequence);
        entityList.add(entity);
    }

    @Override
    public Mensagem readById(Long id) throws Exception {
        Mensagem entity = null;
        for (Mensagem aux : entityList) {
            if (aux.getId().equals(id)) {
                entity = aux;
                break;
            }
        }
        return entity;
    }

    @Override
    public List<Mensagem> readByCriteria(Map<Long, Object> criteria) throws Exception {
        return entityList;
    }

    @Override
    public void update(Mensagem entity) throws Exception {
        for (Mensagem aux : entityList) {
            if (aux.getId().equals(entity.getId())) {
               aux.setPessoa_cpf(entity.getPessoa_cpf());
               aux.setAnuncio_id(entity.getAnuncio_id());
               aux.setData_hora(entity.getData_hora());
               aux.setTexto(entity.getTexto());
                break;
            }
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        for (Mensagem aux : entityList) {
            if (aux.getId().equals(id)) {
                entityList.remove(aux);
                break;
            }
        }
    }
  
}
