package adopet.model.entity;

import adopet.model.base.BaseEntity;


public class Usuario extends BaseEntity{
  private String email;
  private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
  
}
