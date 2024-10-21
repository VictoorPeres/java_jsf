package br.com.avancard.model.dao;

import br.com.avancard.jpautil.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class DaoGeneric<E> {
    private EntityManager entityManager = JPAUtil.getEntityManager();
    public void salvar(E entidade){
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(entidade);
            transaction.commit();
            System.out.println("Cadastro realizado");
    }
    public E merge(E entidade){

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        E retorno = entityManager.merge(entidade);
        transaction.commit();
        System.out.println("Cadastro realizado");
        return retorno;
    }

    public void delete(E entidade){

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(entidade);
        transaction.commit();
        System.out.println("Exclusao realizado");
    }

    public List<E> getEntityList(Class<E> entidade){

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        List<E> retorno = entityManager.createQuery("from "+entidade.getName()).getResultList();
        transaction.commit();
        return retorno;
    }

    public E consultar(Class<E> entidade, String codigo){
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        E objeto = (E) entityManager.find(entidade, Long.parseLong(codigo));
        return objeto;
    }

}
