package adopet.model.base.service;

import adopet.model.base.BaseCRUDService;
import adopet.model.entity.Usuario;

public interface BaseUsuarioService extends BaseCRUDService<Usuario>{
      public Usuario login(String email, String password) throws Exception;
       public void updateBySenha(Usuario entity) throws Exception;
}
