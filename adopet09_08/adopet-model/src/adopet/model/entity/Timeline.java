package adopet.model.entity;

import adopet.model.base.BaseEntity;
import java.sql.Timestamp;


public class Timeline extends BaseEntity{
    private Timestamp data_hora;
    private String texto;
    private Long anuncio_id;
    private String pessoa_cpf;
    private Long foto_id;
    

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

    
    public String getPessoa_cpf() {
        return pessoa_cpf;
    }

    public void setPessoa_cpf(String pessoa_cpf) {
        this.pessoa_cpf = pessoa_cpf;
    }

    public Long getAnuncio_id() {
        return anuncio_id;
    }

    public void setAnuncio_id(Long anuncio_id) {
        this.anuncio_id = anuncio_id;
    }

     public Long getFoto_id() {
        return foto_id;
    }

    public void setFoto_id(Long foto_id) {
        this.foto_id = foto_id;
    }
}
