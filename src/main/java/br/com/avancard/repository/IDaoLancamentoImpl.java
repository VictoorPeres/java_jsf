package br.com.avancard.repository;

import br.com.avancard.bean.LancamentoBean;
import br.com.avancard.model.entity.Lancamento;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Named
public class IDaoLancamentoImpl implements IDaoLancamento, Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager entityManager;
    @Named("lancamentoBean")
    @Inject
    private LancamentoBean lancamentoBean;


    @Override
    public List<Lancamento> relatorioLancamento(String numNota, Date dataIni, Date dataFim) {
        List<Lancamento> lancamentos = null;
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try{
            if(dataIni == null && dataFim == null && numNota != "" && !numNota.isEmpty()){
                entityTransaction.begin();
                lancamentos = entityManager.createQuery("select l from Lancamento l where l.numeroNotaFiscal = " + numNota ).getResultList();
                entityTransaction.commit();
            }else if(dataIni != null && dataFim == null && numNota == "" && numNota.isEmpty()){
                String dataFormatada = new SimpleDateFormat("yyyy-MM-dd").format(dataIni);
                Date dataIniDate = new SimpleDateFormat("yyyy-MM-dd").parse(dataFormatada);
                entityTransaction.begin();
                String sql = "select l from Lancamento l where l.dataIni = :dataIniDate";
                lancamentos = entityManager.createQuery(sql, Lancamento.class)
                                           .setParameter("dataIniDate", dataIniDate)
                                           .getResultList();
                entityTransaction.commit();
            }else if(dataIni == null && dataFim != null && numNota == "" && numNota.isEmpty()){
                String dataFormatada = new SimpleDateFormat("yyyy-MM-dd").format(dataFim);
                Date dataFimDate = new SimpleDateFormat("yyyy-MM-dd").parse(dataFormatada);
                entityTransaction.begin();
                String sql = "select l from Lancamento l where l.dataFim = :dataFimDate";
                lancamentos = entityManager.createQuery(sql, Lancamento.class)
                                           .setParameter("dataFimDate", dataFimDate)
                                           .getResultList();
                entityTransaction.commit();
            } else if (dataIni != null && dataFim != null && numNota == "" && numNota.isEmpty()) {
                    entityTransaction.begin();
                    String sql = "select l from Lancamento l where l.dataIni >= :dataIni and l.dataFim <= :dataFim";
                    lancamentos = entityManager.createQuery(sql, Lancamento.class)
                                               .setParameter("dataIni", dataIni)
                                               .setParameter("dataFim", dataFim)
                                               .getResultList();
            } else if (dataIni != null && dataFim == null && numNota != "" && !numNota.isEmpty()) {
                String dataFormatada = new SimpleDateFormat("yyyy-MM-dd").format(dataIni);
                Date dataIniDate = new SimpleDateFormat("yyyy-MM-dd").parse(dataFormatada);
                entityTransaction.begin();
                String sql = "select l from Lancamento l where l.dataIni = :dataIniDate and l.numeroNotaFiscal = :numNota";
                lancamentos = entityManager.createQuery(sql, Lancamento.class)
                                           .setParameter("dataIniDate", dataIniDate)
                                           .setParameter("numNota", numNota)
                                           .getResultList();
                
            }else if (dataIni == null && dataFim != null && numNota != "" && !numNota.isEmpty()) {
                String dataFormatada = new SimpleDateFormat("yyyy-MM-dd").format(dataFim);
                Date dataFimDate = new SimpleDateFormat("yyyy-MM-dd").parse(dataFormatada);
                entityTransaction.begin();
                String sql = "select l from Lancamento l where l.dataFim = :dataFimDate and l.numeroNotaFiscal = :numNota";
                lancamentos = entityManager.createQuery(sql, Lancamento.class)
                        .setParameter("dataFimDate", dataFimDate)
                        .setParameter("numNota", numNota)
                        .getResultList();

            }else if (dataIni != null && dataFim != null && numNota != "" && !numNota.isEmpty()) {
                entityTransaction.begin();
                String sql = "select l from Lancamento l where l.dataIni >= :dataIni and l.dataFim <= :dataFim and l.numeroNotaFiscal = :numNota";
                lancamentos = entityManager.createQuery(sql, Lancamento.class)
                        .setParameter("dataIni", dataIni)
                        .setParameter("dataFim", dataFim)
                        .setParameter("numNota", numNota)
                        .getResultList();

            }
        }catch(Exception e){
           System.out.println("Erro: " + e.getMessage());
           entityTransaction.rollback();
       }
        return lancamentos;
    }

    @Override
    public List<Lancamento> consultarTopDez(Long idUsuario) {
        List<Lancamento> lancamentos = null;
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try{
            entityTransaction.begin();
            lancamentos =  entityManager.createQuery(" from Lancamento where usuario.id = " + idUsuario + " order by 1 desc").setMaxResults(10).getResultList();
            entityTransaction.commit();
        }catch(Exception e){
            System.out.println("Erro: " + e.getMessage());
            entityTransaction.rollback();
        }

        System.out.println(lancamentos.toString());
        return lancamentos;
    }

    @Override
    public List<Lancamento> consultarLancamentoUsuario(Long idUsuario) {
        List<Lancamento> lancamentos = null;
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try{
            entityTransaction.begin();
            lancamentos =  entityManager.createQuery(" from Lancamento where usuario.id = " + idUsuario).getResultList();
            entityTransaction.commit();
        }catch(Exception e){
            System.out.println("Erro: " + e.getMessage());
            entityTransaction.rollback();
        }

        return lancamentos;
    }
}
