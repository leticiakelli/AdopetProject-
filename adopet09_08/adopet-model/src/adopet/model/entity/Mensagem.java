package adopet.model.entity;

import adopet.model.base.BaseEntity;
import java.sql.Timestamp;


public class Mensagem extends BaseEntity{
    private Timestamp data_hora;
    private String texto;
    private Long anuncio_id;
    private Long pessoa_cpf;

    public Timestamp getData_hora() {
        return data_hora;
    }

    public void setData_hora(Timestamp data_hora) {
        this.data_hora = data_hora;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Long getAnuncio_id() {
        return anuncio_id;
    }

    public void setAnuncio_id(Long anuncio_id) {
        this.anuncio_id = anuncio_id;
    }

    public Long getPessoa_cpf() {
        return pessoa_cpf;
    }

    public void setPessoa_cpf(Long pessoa_cpf) {
        this.pessoa_cpf = pessoa_cpf;
    }

   
    

}
