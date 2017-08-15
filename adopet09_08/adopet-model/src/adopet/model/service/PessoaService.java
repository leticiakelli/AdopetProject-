package adopet.model.service;

import adopet.model.base.service.BasePessoaService;
import adopet.model.entity.Pessoa;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PessoaService implements BasePessoaService {

    private static Long sequence = 0L;
    private static List<Pessoa> entityList = new ArrayList<>();

    @Override
    public void create(Pessoa entity) throws Exception {
        entity.setId(++sequence);
        entityList.add(entity);
    }

    @Override
    public Pessoa readById(Long id) throws Exception {
        Pessoa entity = null;
        for (Pessoa aux : entityList) {
            if (aux.getId().equals(id)) {
                entity = aux;
                break;
            }
        }
        return entity;
    }

    @Override
    public List<Pessoa> readByCriteria(Map<Long, Object> criteria) throws Exception {
        return entityList;
    }

    @Override
    public void update(Pessoa entity) throws Exception {
        for (Pessoa aux : entityList) {
             if (aux.getId().equals(entity.getId())) {
               aux.setNome(entity.getNome());
               aux.setCpf(entity.getCpf());
               aux.setLogradouro(entity.getLogradouro());
               aux.setNumero(entity.getNumero());
               aux.setComplemento(entity.getComplemento());
               aux.setBairro(entity.getBairro());
               aux.setCidade(entity.getCidade());
               aux.setEstado(entity.getEstado());
               aux.setPessoaTelefone_id(entity.getPessoaTelefone_id());
               aux.setFoto_id(entity.getFoto_id());
               aux.setUsuario_id(entity.getUsuario_id());

                break;
            }
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        for (Pessoa aux : entityList) {
            if (aux.getId().equals(id)) {
                entityList.remove(aux);
                break;
            }
        }
    }

}
