package br.com.avancard.bean;

import br.com.avancard.model.dao.DaoGeneric;
import br.com.avancard.model.entity.Pessoa;

import javax.faces.bean.*;

@ViewScoped
@ManagedBean (name = "pessoaBean")
public class PessoaBean {

    private Pessoa pessoa = new Pessoa();
    DaoGeneric<Pessoa> dao = new DaoGeneric<Pessoa>();


    public void salvar(){
        dao.salvar(pessoa);
        pessoa = new Pessoa();
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
}
