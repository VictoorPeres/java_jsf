package br.com.avancard.bean;

import br.com.avancard.model.dao.DaoGeneric;
import br.com.avancard.model.entity.Lancamento;
import br.com.avancard.model.entity.Pessoa;
import br.com.avancard.repository.IDaoLancamento;
import br.com.avancard.repository.IDaoLancamentoImpl;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@ManagedBean(name = "lancamentoBean")
public class LancamentoBean {
    private Lancamento lancamento = new Lancamento();
    private DaoGeneric<Lancamento> dao = new DaoGeneric<Lancamento>();
    private List<Lancamento> lancamentos = new ArrayList<Lancamento>();
    private IDaoLancamento daoLancamento = new IDaoLancamentoImpl();



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
        lancamentos = daoLancamento.consultarLancamentoUsuario(pessoa.getId());
    }

    public void novo(){
        lancamento = new Lancamento();
    }

    public void excluir(){
        dao.delete(lancamento);
        lancamento = new Lancamento();
        carregarLancamentos();
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
