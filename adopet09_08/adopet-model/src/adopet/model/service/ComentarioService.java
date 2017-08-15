package adopet.model.service;

import adopet.model.base.service.BaseComentarioService;
import adopet.model.entity.Comentario;
import adopet.model.entity.Comentario;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ComentarioService implements BaseComentarioService {

    private static Long sequence = 0L;
    private static List<Comentario> entityList = new ArrayList<>();

    @Override
    public void create(Comentario entity) throws Exception {
        entity.setId(++sequence);
        entityList.add(entity);
    }

    @Override
    public Comentario readById(Long id) throws Exception {
        Comentario entity = null;
        for (Comentario aux : entityList) {
            if (aux.getId().equals(id)) {
                entity = aux;
                break;
            }
        }
        return entity;
    }

    @Override
    public List<Comentario> readByCriteria(Map<Long, Object> criteria) throws Exception {
        return entityList;
    }

    @Override
    public void update(Comentario entity) throws Exception {
        for (Comentario aux : entityList) {
            if (aux.getId().equals(entity.getId())) {
                aux.setTexto(entity.getTexto());
                aux.setData_hora(entity.getData_hora());
                aux.setPessoa_cpf(entity.getPessoa_cpf());
                aux.setFoto_id(entity.getFoto_id());
                aux.setPost_id(entity.getPost_id());
                break;
            }
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        for (Comentario aux : entityList) {
            if (aux.getId().equals(id)) {
                entityList.remove(aux);
                break;
            }
        }
    }
}
