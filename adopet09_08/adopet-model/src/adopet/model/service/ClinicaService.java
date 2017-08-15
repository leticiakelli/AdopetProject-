package adopet.model.service;

import adopet.model.base.BaseCRUDService;
import adopet.model.base.service.BaseClinicaService;
import adopet.model.entity.Clinica;
import adopet.model.entity.Clinica;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ClinicaService implements BaseClinicaService{
    
    private static Long sequence = 0L;
    private static List<Clinica> entityList = new ArrayList<>();

    @Override
    public void create(Clinica entity) throws Exception {
        entity.setId(++sequence);
        entityList.add(entity);
    }

    @Override
    public Clinica readById(Long id) throws Exception {
        Clinica entity = null;
        for (Clinica aux : entityList) {
            if (aux.getId().equals(id)) {
                entity = aux;
                break;
            }
        }
        return entity;
    }

    @Override
    public List<Clinica> readByCriteria(Map<Long, Object> criteria) throws Exception {
        return entityList;
    }

    @Override
    public void update(Clinica entity) throws Exception {
        for (Clinica aux : entityList) {
            if (aux.getId().equals(entity.getId())) {
                 aux.setCnpj(entity.getCnpj());
                aux.setNome(entity.getNome());
                aux.setTelefone(entity.getTelefone());
                aux.setPessoa_cpf(entity.getPessoa_cpf());
                aux.setFoto_id(entity.getFoto_id());
                aux.setLogradouro(entity.getLogradouro());
                aux.setNumero(entity.getNumero());
                aux.setComplemento(entity.getComplemento());
                aux.setBairro(entity.getBairro());
                aux.setCidade(entity.getCidade());
                aux.setEstado(entity.getEstado());
                break;
            }
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        for (Clinica aux : entityList) {
            if (aux.getId().equals(id)) {
                entityList.remove(aux);
                break;
            }
        }
    }

}
