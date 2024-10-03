package br.com.avancard.bean;

import br.com.avancard.model.dao.DaoGeneric;
import br.com.avancard.model.entity.Pessoa;

import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@ManagedBean (name = "pessoaBean")
public class PessoaBean {

    private Pessoa pessoa = new Pessoa();
    DaoGeneric<Pessoa> dao = new DaoGeneric<Pessoa>();
    private List<Pessoa> pessoas = new ArrayList<Pessoa>();


    public String salvar(){
        pessoa = dao.merge(pessoa);
        carregarPessoas();
        return "";
    }

    public void novo(){
        pessoa = new Pessoa();
    }

    public void excluir(){
        dao.delete(pessoa);
        pessoa = new Pessoa();
        carregarPessoas();
    }
    @PostConstruct
    public void carregarPessoas(){
        pessoas = dao.getEntityList(Pessoa.class);
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public DaoGeneric<Pessoa> getDao() {
        return dao;
    }

    public void setDao(DaoGeneric<Pessoa> dao) {
        this.dao = dao;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }
}
