package adopet.model.entity;

import adopet.model.base.BaseEntity;
import java.sql.Timestamp;


public class Comentario extends BaseEntity{
  private String texto;
  private Timestamp data_hora;
  private String pessoa_cpf;
  private Long foto_id;
  private Long post_id;

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Timestamp getData_hora() {
        return data_hora;
    }

    public void setData_hora(Timestamp data_hora) {
        this.data_hora = data_hora;
    }

    public String getPessoa_cpf() {
        return pessoa_cpf;
    }

    public void setPessoa_cpf(String pessoa_cpf) {
        this.pessoa_cpf = pessoa_cpf;
    }

    public Long getFoto_id() {
        return foto_id;
    }

    public void setFoto_id(Long foto_id) {
        this.foto_id = foto_id;
    }

    public Long getPost_id() {
        return post_id;
    }

    public void setPost_id(Long post_id) {
        this.post_id = post_id;
    }

  
}
