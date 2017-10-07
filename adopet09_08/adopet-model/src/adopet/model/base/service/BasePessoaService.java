
package adopet.model.base.service;

import adopet.model.base.BaseCRUDService;
import adopet.model.entity.Pessoa;


public interface BasePessoaService extends BaseCRUDService<Pessoa>{
    public Pessoa readByCpf (String cpf) throws  Exception;
    public void deleteByCpf(String cpf) throws Exception;
}
