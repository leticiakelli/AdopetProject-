package adopet.model.entity;

import adopet.model.base.BaseEntity;



public class PessoaTelefone extends BaseEntity{
   
    private String telefone;
    private String celular; 

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
    
}
