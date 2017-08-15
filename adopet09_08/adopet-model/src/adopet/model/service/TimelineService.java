
package adopet.model.service;

import adopet.model.base.service.BaseTimelineService;
import adopet.model.entity.Timeline;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class TimelineService implements BaseTimelineService{
    private static Long sequence = 0L;
    private static List<Timeline> entityList = new ArrayList<>();

    @Override
    public void create(Timeline entity) throws Exception {
        entity.setId(++sequence);
        entityList.add(entity);
    }

    @Override
    public Timeline readById(Long id) throws Exception {
        Timeline entity = null;
        for (Timeline aux : entityList) {
            if (aux.getId().equals(id)) {
                entity = aux;
                break;
            }
        }
        return entity;
    }

    @Override
    public List<Timeline> readByCriteria(Map<Long, Object> criteria) throws Exception {
        return entityList;
    }

    @Override
    public void update(Timeline entity) throws Exception {
        for (Timeline aux : entityList) {
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
        for (Timeline aux : entityList) {
            if (aux.getId().equals(id)) {
                entityList.remove(aux);
                break;
            }
        }
    }
}
