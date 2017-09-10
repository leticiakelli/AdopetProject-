package adopet.model.entity;

import adopet.model.base.BaseEntity;
import java.sql.Timestamp;

public class Anuncio extends BaseEntity{
    private Timestamp data_hora;
    private  String sexo;
    private  String porte;
    private  String raca;
    private  String caracteristicas;
    private  String tipo;
    private  String status;
    private  Integer idade;
    private  Long foto_id;
    private  Long especie_id;
    private  String pessoaAdotanteCpf;
    private  String pessoaAnuncianteCpf;
    private  String recompensa;
    private  String local;
    

    public Timestamp getData_hora() {
        return data_hora;
    }

    public void setData_hora(Timestamp data_hora) {
        this.data_hora = data_hora;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getRecompensa() {
        return recompensa;
    }

    public void setRecompensa(String recompensa) {
        this.recompensa = recompensa;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Long getFoto_id() {
        return foto_id;
    }

    public void setFoto_id(Long foto_id) {
        this.foto_id = foto_id;
    }

    public Long getEspecie_id() {
        return especie_id;
    }

    public void setEspecie_id(Long especie_id) {
        this.especie_id = especie_id;
    }

    public String getPessoaAdotanteCpf() {
        return pessoaAdotanteCpf;
    }

    public void setPessoaAdotanteCpf(String pessoaAdotanteCpf) {
        this.pessoaAdotanteCpf = pessoaAdotanteCpf;
    }

    public String getPessoaAnuncianteCpf() {
        return pessoaAnuncianteCpf;
    }

    public void setPessoaAnuncianteCpf(String pessoaAnuncianteCpf) {
        this.pessoaAnuncianteCpf = pessoaAnuncianteCpf;
    }

      
    
}
