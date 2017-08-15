package adopet.model.service;

import adopet.model.base.service.BasePostService;
import adopet.model.entity.Post;
import adopet.model.entity.Post;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PostService implements BasePostService {

    private static Long sequence = 0L;
    private static List<Post> entityList = new ArrayList<>();

    @Override
    public void create(Post entity) throws Exception {
        entity.setId(++sequence);
        entityList.add(entity);
    }

    @Override
    public Post readById(Long id) throws Exception {
        Post entity = null;
        for (Post aux : entityList) {
            if (aux.getId().equals(id)) {
                entity = aux;
                break;
            }
        }
        return entity;
    }

    @Override
    public List<Post> readByCriteria(Map<Long, Object> criteria) throws Exception {
        return entityList;
    }

    @Override
    public void update(Post entity) throws Exception {
        for (Post aux : entityList) {
            if (aux.getId().equals(entity.getId())) {
                aux.setTexto(entity.getTexto());
                aux.setData_hora(entity.getData_hora());
                aux.setFoto_id(entity.getFoto_id());
                aux.setFoto_id(entity.getFoto_id());
                break;
            }
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        for (Post aux : entityList) {
            if (aux.getId().equals(id)) {
                entityList.remove(aux);
                break;
            }
        }
    }
}
