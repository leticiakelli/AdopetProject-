
package adopet.model.entity;

import adopet.model.base.BaseEntity;
import java.sql.Timestamp;


public class Post extends BaseEntity{
   private String texto;
   private Timestamp data_hora;
   private String pesso_cpf;
   private Long foto_id;

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

    public String getPesso_cpf() {
        return pesso_cpf;
    }

    public void setPesso_cpf(String pesso_cpf) {
        this.pesso_cpf = pesso_cpf;
    }

    public Long getFoto_id() {
        return foto_id;
    }

    public void setFoto_id(Long foto_id) {
        this.foto_id = foto_id;
    }

   
}
