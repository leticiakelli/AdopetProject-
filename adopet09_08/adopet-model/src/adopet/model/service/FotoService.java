package adopet.model.service;

import adopet.model.base.service.BaseFotoService;
import adopet.model.entity.Foto;
import adopet.model.entity.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FotoService implements BaseFotoService {

    private static Long sequence = 0L;
    private static List<Foto> entityList = new ArrayList<>();

    @Override
    public void create(Foto entity) throws Exception {
        entity.setId(++sequence);
        entityList.add(entity);
    }

    @Override
    public Foto readById(Long id) throws Exception {
        Foto entity = null;
        for (Foto aux : entityList) {
            if (aux.getId().equals(id)) {
                entity = aux;
                break;
            }
        }
        return entity;
    }

    @Override
    public List<Foto> readByCriteria(Map<Long, Object> criteria) throws Exception {
        return entityList;
    }

    @Override
    public void update(Foto entity) throws Exception {
        for (Foto aux : entityList) {
            if (aux.getId().equals(entity.getId())) {
                aux.setNome(entity.getNome());
                break;
            }
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        for (Foto aux : entityList) {
            if (aux.getId().equals(id)) {
                entityList.remove(aux);
                break;
            }
        }
    }

}
