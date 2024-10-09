package br.com.avancard.repository;

import br.com.avancard.jpautil.JPAUtil;
import br.com.avancard.model.entity.Lancamento;
import br.com.avancard.model.entity.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class IDaoLancamentoImpl implements IDaoLancamento {
    @Override
    public List<Lancamento> consultarLancamentoUsuario(Long idUsuario) {
        List<Lancamento> lancamentos = null;
        EntityManager entityManager = JPAUtil.getEntityManager();
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
