package br.com.avancard.bean;

import br.com.avancard.model.dao.DaoGeneric;
import br.com.avancard.model.entity.Pessoa;
import br.com.avancard.repository.IDaoPessoa;
import br.com.avancard.repository.IDaoPessoaImpl;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@ManagedBean (name = "pessoaBean")
public class PessoaBean {

    private Pessoa pessoa = new Pessoa();
    DaoGeneric<Pessoa> dao = new DaoGeneric<Pessoa>();
    private List<Pessoa> pessoas = new ArrayList<Pessoa>();

    IDaoPessoa iDaoPessoa = new IDaoPessoaImpl();


    public String login(){

        Pessoa pessoaUser = iDaoPessoa.consultarPessoa(pessoa.getLogin(), pessoa.getSenha());

        if(pessoaUser != null){/*Achou o usuário*/

            /**/
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();
            externalContext.getSessionMap().put("usuarioLogado", pessoaUser);

            return "primeirapagina.jsf";
        }
        return "index.jsf";
    }

    public boolean permiteAcesso(String acesso){
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        Pessoa pessoaUser = (Pessoa) externalContext.getSessionMap().get("usuarioLogado");
        return pessoaUser.getPerfilUsuario().equals(acesso);
    }

    public String salvar(){
        pessoa = dao.merge(pessoa);
        carregarPessoas();
        mostrarMsg("Cadastrado com sucesso!");
        return "";
    }

    private void mostrarMsg(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(msg);
        context.addMessage(null, message);
    }

    public void novo(){
        pessoa = new Pessoa();
    }

    public void excluir(){
        dao.delete(pessoa);
        pessoa = new Pessoa();
        carregarPessoas();
        mostrarMsg("Removido com sucesso!");

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
