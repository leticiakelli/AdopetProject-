package adopet.model.service;

import adopet.model.base.service.BaseChatService;
import adopet.model.entity.Chat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ChatService implements BaseChatService{
    
    private static Long sequence = 0L;
    private static List<Chat> entityList = new ArrayList<>();

    @Override
    public void create(Chat entity) throws Exception {
        entity.setId(++sequence);
        entityList.add(entity);
    }

    @Override
    public Chat readById(Long id) throws Exception {
        Chat entity = null;
        for (Chat aux : entityList) {
            if (aux.getId().equals(id)) {
                entity = aux;
                break;
            }
        }
        return entity;
    }

    @Override
    public List<Chat> readByCriteria(Map<Long, Object> criteria) throws Exception {
        return entityList;
    }

    @Override
    public void update(Chat entity) throws Exception {
        for (Chat aux : entityList) {
            if (aux.getId().equals(entity.getId())) {
               aux.setPessoa_cpf(entity.getPessoa_cpf());
               aux.setAnuncio_id(entity.getAnuncio_id());
               aux.setData_hora(entity.getData_hora());
                break;
            }
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        for (Chat aux : entityList) {
            if (aux.getId().equals(id)) {
                entityList.remove(aux);
                break;
            }
        }
    }

}
