package br.com.avancard.jpautil;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class JPAUtil {
    private EntityManagerFactory factory = null;

    public JPAUtil() {
        if(factory == null){
            factory = Persistence.createEntityManagerFactory("jpa_jsf");
            System.out.println("BD conectado");
        }
    }
    @Produces //é usada no contexto de CDI (Contexts and Dependency Injection) em Java para indicar que um método ou campo específico deve fornecer (ou "produzir") um objeto que pode ser injetado em outros pontos da aplicação. Com @Produces, é possível customizar a criação de objetos e gerenciar dependências que não podem ser diretamente instanciadas ou que exigem uma configuração específica.
    @RequestScoped
    public EntityManager getEntityManager(){
        return factory.createEntityManager();
    }
}
