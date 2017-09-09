package adopet.model.service;

import adopet.model.base.service.BaseUsuarioService;
import adopet.model.entity.Usuario;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsuarioService implements BaseUsuarioService {

    private static Long sequence = 0L;
    private static List<Usuario> entityList = new ArrayList<>();

    @Override
    public Usuario login(String email, String password) throws Exception {
        Usuario entity = null;
        for (Usuario aux : entityList) {
            if (aux.getEmail().equals(email) && aux.getSenha().equals(password)) {
                entity = aux;
                break;
            }
        }
        return entity;
    }

    @Override
    public void create(Usuario entity) throws Exception {
        entity.setId(++sequence);
        entityList.add(entity);
    }

    @Override
    public Usuario readById(Long id) throws Exception {
        Usuario entity = null;
        for (Usuario aux : entityList) {
            if (aux.getId().equals(id)) {
                entity = aux;
                break;
            }
        }
        return entity;
    }

    @Override
    public List<Usuario> readByCriteria(Map<Long, Object> criteria) throws Exception {
        return entityList;
    }

    @Override
    public void update(Usuario entity) throws Exception {
        for (Usuario aux : entityList) {
            if (aux.getId().equals(entity.getId())) {
                aux.setEmail(entity.getEmail());
                aux.setSenha(entity.getSenha());
                break;
            }
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        for (Usuario aux : entityList) {
            if (aux.getId().equals(id)) {
                entityList.remove(aux);
                break;
            }
        }
    }

    public Boolean validateForCreate(Usuario usuario) {
        try {
            //varre e procura algum usuario com email e senha iguais
            List<Usuario> usuarioList = readByCriteria(new HashMap<Long, Object>());
            for (Usuario usuarioSalvo : usuarioList) {
                if (usuario.getEmail().equals(usuarioSalvo.getEmail())
                        && usuario.getSenha().equals(usuarioSalvo.getSenha())) {
                    //Se encontrar retorna falsp
                    return false;

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        //caso nao encontre nenhum erro ou suaurio com emaul e senha iguais ele retorna verdadeiro
        return true;

    }
}
