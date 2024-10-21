package br.com.avancard.model.entity;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_pessoa", unique = true, nullable = false)
    private long id;
    @Column(name = "nm_pessoa", nullable = false)
    @NotEmpty(message = "O nome deve ser informado")
    @NotNull(message = "O nome deve ser informado")
    @Size(min = 5, max = 20, message = "Nome deve ter entre 5 e 20 caracteres")
    private String nome;
    @NotEmpty(message = "O sobrenome deve ser informado")
    @NotNull(message = "O sobrenome deve ser informado")
    @Column(nullable = false)
    private String sobrenome;
    @NotEmpty(message = "Preencha o campo idade")
    @NotNull(message = "Preencha o campo idade")
    private int idade;
    @Temporal(TemporalType.DATE)
    @Future(message = "A data de nascimento não pode ser maior que a data atual")
    private Date dataNascimento;
    @NotEmpty(message = "Preencha o campo sexo")
    @NotNull(message = "Preencha o campo sexo")
    private String sexo;
    private String[] frameworks;
    private Boolean ativo;
    private String login;
    private String senha;
    private String perfilUsuario;
    private String nivelProgramador;
    private String[] linguagens;
    private String cep;
    private String logradouro;
    private String complemento;
    private String numero;
    private String bairro;
    @Column(name = "nm_cidade")
    private String localidade;
    private String uf;
    @Transient /*Faz com que o atributo não fique persistente ou não grarve no banco*/
    private Estados estados;
    @OneToOne
    @JoinColumn(name = "cidades_id")
    private Cidades cidades;

    @Column(columnDefinition = "text") /* tipo de text grava arquivos em base 64 */
    private String fotoIconBase64;
    private String extensao; /* extensão JPG, PNG,  JPEG */
    @Lob/* Gravar arquivos no banco de dados */
    @Basic(fetch = FetchType.LAZY)
    private byte[] fotoIconBase64Original;

    public Pessoa() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String[] getFrameworks() {
        return frameworks;
    }

    public void setFrameworks(String[] frameworks) {
        this.frameworks = frameworks;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPerfilUsuario() {
        return perfilUsuario;
    }

    public void setPerfilUsuario(String perfilUsuario) {
        this.perfilUsuario = perfilUsuario;
    }

    public String getNivelProgramador() {
        return nivelProgramador;
    }

    public void setNivelProgramador(String nivelProgramador) {
        this.nivelProgramador = nivelProgramador;
    }

    public String[] getLinguagens() {
        return linguagens;
    }

    public void setLinguagens(String[] linguagens) {
        this.linguagens = linguagens;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Cidades getCidades() {
        return cidades;
    }

    public void setCidades(Cidades cidades) {
        this.cidades = cidades;
    }

    public Estados getEstados() {
        return estados;
    }

    public void setEstados(Estados estados) {
        this.estados = estados;
    }

    public String getFotoIconBase64() {
        return fotoIconBase64;
    }

    public void setFotoIconBase64(String fotoIconBase64) {
        this.fotoIconBase64 = fotoIconBase64;
    }

    public String getExtensao() {
        return extensao;
    }

    public void setExtensao(String extensao) {
        this.extensao = extensao;
    }

    public byte[] getFotoIconBase64Original() {
        return fotoIconBase64Original;
    }

    public void setFotoIconBase64Original(byte[] fotoIconBase64Original) {
        this.fotoIconBase64Original = fotoIconBase64Original;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return id == pessoa.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
