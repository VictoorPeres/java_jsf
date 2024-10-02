package br.com.avancard.model.dao;

import br.com.avancard.jpautil.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class DaoGeneric<E> {
    private EntityManager entityManager = JPAUtil.getEntityManager();
    public void salvar(E entidade){
        try{
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(entidade);
            transaction.commit();
            System.out.println("Cadastro realizado");
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
