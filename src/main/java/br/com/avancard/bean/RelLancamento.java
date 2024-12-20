package br.com.avancard.bean;

import br.com.avancard.model.dao.DaoGeneric;
import br.com.avancard.model.entity.Lancamento;
import br.com.avancard.repository.IDaoLancamento;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ViewScoped
@Named(value = "relLancamento")
public class RelLancamento implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Lancamento> lancamentos = new ArrayList<Lancamento>();

    private Date dataIni;
    private Date dataFin;

    private String numNota;

    @Inject
    private IDaoLancamento daoLancamento;

    @Inject
    private DaoGeneric<Lancamento> daoGeneric;

    public void buscarLancamento(){
        if(dataIni == null && dataFin == null && numNota.isEmpty()){
           lancamentos = daoGeneric.getEntityList(Lancamento.class);
        }else{
            lancamentos = daoLancamento.relatorioLancamento(numNota, dataIni, dataFin);
        }
    }

    public List<Lancamento> getLancamentos() {
        return lancamentos;
    }

    public void setLancamentos(List<Lancamento> lancamentos) {
        this.lancamentos = lancamentos;
    }

    public Date getDataIni() {
        return dataIni;
    }

    public void setDataIni(Date dataIni) {
        this.dataIni = dataIni;
    }

    public Date getDataFin() {
        return dataFin;
    }

    public void setDataFin(Date dataFin) {
        this.dataFin = dataFin;
    }

    public String getNumNota() {
        return numNota;
    }

    public void setNumNota(String numNota) {
        this.numNota = numNota;
    }
}
