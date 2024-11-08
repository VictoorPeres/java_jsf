package br.com.avancard.repository;

import br.com.avancard.jpautil.JPAUtil;
import br.com.avancard.model.entity.Cidades;
import br.com.avancard.model.entity.Estados;
import br.com.avancard.model.entity.Pessoa;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named
public class IDaoPessoaImpl implements IDaoPessoa, Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager entityManager;

    @Override
    public Pessoa consultarPessoa(String login, String senha) {
        Pessoa pessoa = null;

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
/*
    @Override
    public List<SelectItem> listaEstados() {

        // Cria uma nova lista de SelectItem, que será preenchida com os estados.
        List<SelectItem> selectItems = new ArrayList<SelectItem>();

        // Obtém uma instância do EntityManager, que é usada para interagir com o banco de dados.
        EntityManager entityManager = JPAUtil.getEntityManager();

        // Inicia uma transação para garantir que as operações no banco de dados sejam consistentes.
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        // Executa uma consulta JPQL para buscar todos os registros da tabela 'Estados'.
        // "from Estados" é uma consulta simples que retorna todas as instâncias da entidade Estados.
        List<Estados> estados = entityManager.createQuery("from Estados").getResultList();

        // Itera sobre a lista de estados retornados da consulta.
        for (Estados estado : estados) {
            // Para cada estado, cria um novo SelectItem.
            // SelectItem é um item de seleção usado em componentes como selectOneMenu em JSF.
            // O primeiro parâmetro (estado.getId()) é o valor do item e o segundo (estado.getNome()) é o rótulo exibido ao usuário.
            selectItems.add(new SelectItem(estado.getId(), estado.getNome()));
        }

        // Retorna a lista de SelectItem, que pode ser usada para preencher um componente de seleção (ex: dropdown menu) na interface do usuário.
        return selectItems;
    }*/

    @Override
    public List<Estados> listaEstados() {


        List<Estados> estados = new ArrayList<Estados>();

        // Inicia uma transação para garantir que as operações no banco de dados sejam consistentes.
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        // Executa uma consulta JPQL para buscar todos os registros da tabela 'Estados'.
        // "from Estados" é uma consulta simples que retorna todas as instâncias da entidade Estados.
        List<Estados> getEstados = entityManager.createQuery("from Estados").getResultList();
        entityTransaction.commit();
        // Itera sobre a lista de estados retornados da consulta.
        for (Estados estado : getEstados) {
            // Para cada estado, cria um novo SelectItem.
            // SelectItem é um item de seleção usado em componentes como selectOneMenu em JSF.
            // O primeiro parâmetro (estado.getId()) é o valor do item e o segundo (estado.getNome()) é o rótulo exibido ao usuário.
            estados.add(estado);
        }

        // Retorna a lista de SelectItem, que pode ser usada para preencher um componente de seleção (ex: dropdown menu) na interface do usuário.
        return estados;
    }

    @Override
    public List<Cidades> listaCidadesPorId(Long id) {
        List<Cidades> cidades = new ArrayList<Cidades>();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        List<Cidades> getCidades = entityManager.createQuery("SELECT c FROM Cidades c WHERE id_estado = " + id).getResultList();
        // Itera sobre a lista de estados retornados da consulta.
        for (Cidades cidade : getCidades) {
            // Para cada estado, cria um novo SelectItem.
            // SelectItem é um item de seleção usado em componentes como selectOneMenu em JSF.
            // O primeiro parâmetro (estado.getId()) é o valor do item e o segundo (estado.getNome()) é o rótulo exibido ao usuário.
            cidades.add(cidade);
        }
        entityTransaction.commit();
        // Retorna a lista de SelectItem, que pode ser usada para preencher um componente de seleção (ex: dropdown menu) na interface do usuário.
        return cidades;
    }

    @Override
    public List<Pessoa> relatorioUsuario(String nome, String cpf) {
        List<Pessoa> pessoas = null;
        if((nome != "" || !nome.isEmpty()) && (cpf == "" || cpf.isEmpty())){
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            String sql = "select p from Pessoa p where p.nome like :nome";
            pessoas = entityManager.createQuery(sql, Pessoa.class) .setParameter("nome", "%" + nome + "%").getResultList();
            entityTransaction.commit();
            
        } else if ((nome == "" || nome.isEmpty()) && (cpf != "" || !cpf.isEmpty())) {
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            String sql = "select p from Pessoa p where p.cpf = :cpf";
            pessoas = entityManager.createQuery(sql, Pessoa.class).setParameter("cpf", cpf).getResultList();
        }else if ((nome != "" || !nome.isEmpty()) && (cpf != "" || !cpf.isEmpty())) {
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            String sql = "select p from Pessoa p where p.cpf = :cpf and p.nome like :nome";
            pessoas = entityManager.createQuery(sql, Pessoa.class).setParameter("cpf", cpf).setParameter("nome", "%" + nome + "%").getResultList();
        }
        return pessoas;
    }
}
