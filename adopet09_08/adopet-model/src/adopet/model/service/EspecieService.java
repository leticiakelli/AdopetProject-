package adopet.model.service;

import adopet.model.base.service.BaseEspecieService;
import adopet.model.entity.Especie;
import adopet.model.entity.Especie;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EspecieService implements BaseEspecieService {

    private static Long sequence = 0L;
    private static List<Especie> entityList = new ArrayList<>();

    @Override
    public void create(Especie entity) throws Exception {
        entity.setId(++sequence);
        entityList.add(entity);
    }

    @Override
    public Especie readById(Long id) throws Exception {
        if (entityList.isEmpty()) {
            entityList.clear();
            Especie especie = new Especie();
            especie.setId(1L);
            especie.setNome("Cão");
            entityList.add(especie);
            
            especie = new Especie();
            especie.setId(2L);
            especie.setNome("Gato");
            entityList.add(especie);
        }
        Especie entity = null;
        for (Especie aux : entityList) {
            if (aux.getId().equals(id)) {
                entity = aux;
                break;
            }
        }
        return entity;
    }

    @Override
    public List<Especie> readByCriteria(Map<Long, Object> criteria) throws Exception {
        if (entityList.isEmpty()) {
            entityList.clear();
            Especie especie = new Especie();
            especie.setId(1L);
            especie.setNome("Cão");
            entityList.add(especie);
            especie = new Especie();
            especie.setId(2L);
            especie.setNome("Gato");
            entityList.add(especie);
        }
        return entityList;
    }

    @Override
    public void update(Especie entity) throws Exception {
        for (Especie aux : entityList) {
            if (aux.getId().equals(entity.getId())) {
                aux.setNome(entity.getNome());
                break;
            }
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        for (Especie aux : entityList) {
            if (aux.getId().equals(id)) {
                entityList.remove(aux);
                break;
            }
        }
    }
}
