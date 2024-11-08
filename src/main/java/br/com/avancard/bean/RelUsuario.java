package br.com.avancard.bean;

import br.com.avancard.model.dao.DaoGeneric;
import br.com.avancard.model.entity.Lancamento;
import br.com.avancard.model.entity.Pessoa;
import br.com.avancard.repository.IDaoLancamento;
import br.com.avancard.repository.IDaoPessoa;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ViewScoped
@Named(value = "relUsuario")
public class RelUsuario implements Serializable {
    private static final long serialVersionUID = 1L;


    private List<Pessoa> pessoas = new ArrayList<Pessoa>();

    private String cpf;
    private String nome;

    @Inject
    private IDaoPessoa idaoPessoa;

    @Inject
    private DaoGeneric<Pessoa> daoPessoa;


    public void buscarUsuario(){
        if((cpf == "" || cpf.isEmpty()) && (nome == "" || nome.isEmpty())){
            pessoas = daoPessoa.getEntityList(Pessoa.class);
        }else{
            pessoas = idaoPessoa.relatorioUsuario(nome, cpf);
        }
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
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
}
