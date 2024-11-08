package br.com.avancard.model.dao;

import br.com.avancard.jpautil.JPAUtil;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.util.List;


@Named
public class DaoGeneric<E> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager entityManager;

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

    public void delete(E entidade) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            // Verifica se a entidade está "detached" e, se necessário, reanexa
            if (!entityManager.contains(entidade)) {
                entidade = entityManager.merge(entidade);
            }
            entityManager.remove(entidade);
            transaction.commit();
            System.out.println("Exclusão realizada com sucesso");
        } catch (Exception e) {
            transaction.rollback();
            System.err.println("Erro ao excluir: " + e.getMessage());
            e.printStackTrace(); // Exibe a pilha de erro para depuração
        }
    }

    public List<E> getEntityList(Class<E> entidade){

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        List<E> retorno = entityManager.createQuery("from "+entidade.getName()).getResultList();
        transaction.commit();
        return retorno;
    }

    public List<E> getEntityListTopDez(Class<E> entidade){

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        List<E> retorno = entityManager.createQuery("from " + entidade.getName() + " order by 1 desc").setMaxResults(10).getResultList();
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
