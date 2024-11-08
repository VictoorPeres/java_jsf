package br.com.avancard.bean;

import br.com.avancard.model.dao.DaoGeneric;
import br.com.avancard.model.entity.Lancamento;
import br.com.avancard.model.entity.Pessoa;
import br.com.avancard.repository.IDaoLancamento;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@Named(value = "lancamentoBean")
public class LancamentoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Lancamento lancamento = new Lancamento();
    private List<Lancamento> lancamentos = new ArrayList<Lancamento>();

    @Inject
    private DaoGeneric<Lancamento> dao;
    @Inject
    private IDaoLancamento daoLancamento;



    public String salvar(){
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        Pessoa pessoa = (Pessoa) externalContext.getSessionMap().get("usuarioLogado");
        lancamento.setUsuario(pessoa);
        lancamento = dao.merge(lancamento);
        carregarLancamentos();
        return "";
    }

    @PostConstruct
    public void carregarLancamentos(){
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        Pessoa pessoa = (Pessoa) externalContext.getSessionMap().get("usuarioLogado");
        lancamentos = daoLancamento.consultarTopDez(pessoa.getId());
    }

    public String novo(){
        lancamento = new Lancamento();
        return "";
    }

    public String limpar(){

        lancamento = new Lancamento();
        return "";
    }

    public void excluir(){
        dao.delete(lancamento);
        lancamento = new Lancamento();
        carregarLancamentos();
        mostrarMsg("Exclusão realizada com sucesso!");
    }

    private void mostrarMsg(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(msg);
        context.addMessage(null, message);
    }

    public Lancamento getLancamento() {
        return lancamento;
    }

    public void setLancamento(Lancamento lancamento) {
        this.lancamento = lancamento;
    }

    public DaoGeneric<Lancamento> getDao() {
        return dao;
    }

    public void setDao(DaoGeneric<Lancamento> dao) {
        this.dao = dao;
    }

    public List<Lancamento> getLancamentos() {
        return lancamentos;
    }

    public void setLancamentos(List<Lancamento> lancamentos) {
        this.lancamentos = lancamentos;
    }
}
