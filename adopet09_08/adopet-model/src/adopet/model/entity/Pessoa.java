package adopet.model.entity;

import adopet.model.base.BaseEntity;

public class Pessoa extends BaseEntity {

private String cpf;
private String nome;
private String logradouro;
private Integer numero;
private String complemento;
private String bairro;
private String cidade;
private String estado;
private Long foto_id;
private Long usuario_id;
private Long pessoaTelefone_id;

    public Long getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(Long usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getFoto_id() {
        return foto_id;
    }

    public void setFoto_id(Long foto_id) {
        this.foto_id = foto_id;
    }



    public Long getPessoaTelefone_id() {
        return pessoaTelefone_id;
    }

    public void setPessoaTelefone_id(Long pessoaTelefone_id) {
        this.pessoaTelefone_id = pessoaTelefone_id;
    }
   

}
