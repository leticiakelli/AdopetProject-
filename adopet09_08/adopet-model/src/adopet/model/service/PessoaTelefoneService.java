package adopet.model.service;

import adopet.model.base.service.BasePessoaTelefone;
import adopet.model.entity.Pessoa;
import adopet.model.entity.PessoaTelefone;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class PessoaTelefoneService implements BasePessoaTelefone {
  private static Long sequence = 0L;
    private static List<PessoaTelefone> entityList = new ArrayList<>();

    @Override
    public void create(PessoaTelefone entity) throws Exception {
        entity.setId(++sequence);
        entityList.add(entity);
    }

    @Override
    public PessoaTelefone readById(Long id) throws Exception {
        PessoaTelefone entity = null;
        for (PessoaTelefone aux : entityList) {
            if (aux.getId().equals(id)) {
                entity = aux;
                break;
            }
        }
        return entity;
    }

    @Override
    public List<PessoaTelefone> readByCriteria(Map<Long, Object> criteria) throws Exception {
        return entityList;
    }

    @Override
    public void update(PessoaTelefone entity) throws Exception {
        for (PessoaTelefone aux : entityList) {
             if (aux.getId().equals(entity.getId())) {
               aux.setCelular(entity.getCelular());
               aux.setTelefone(entity.getTelefone());


                break;
            }
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        for (PessoaTelefone aux : entityList) {
            if (aux.getId().equals(id)) {
                entityList.remove(aux);
                break;
            }
        }
    }
}
