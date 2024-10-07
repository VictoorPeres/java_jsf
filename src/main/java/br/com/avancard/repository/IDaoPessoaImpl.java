package br.com.avancard.repository;

import br.com.avancard.jpautil.JPAUtil;
import br.com.avancard.model.entity.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class IDaoPessoaImpl implements IDaoPessoa{


    @Override
    public Pessoa consultarPessoa(String login, String senha) {
        Pessoa pessoa = null;
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try{
            entityTransaction.begin();
            pessoa = (Pessoa) entityManager.createQuery("select p from Pessoa p where p.login = '"+ login +"' and p.senha ='" + senha +"'").getSingleResult();
            entityTransaction.commit();
        }catch(Exception e){
            System.out.println("Erro: " + e.getMessage());
            entityTransaction.rollback();
        }

        return pessoa;
    }
}
