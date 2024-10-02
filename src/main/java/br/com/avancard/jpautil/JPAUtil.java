package br.com.avancard.jpautil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
    private static EntityManagerFactory factory = null;

    static{
        init();
    }

    public static void init(){
        if(factory == null){
            factory = Persistence.createEntityManagerFactory("jpa_jsf");
            System.out.println("BD conectado");
        }
    }

    public static EntityManager getEntityManager(){
        return factory.createEntityManager();
    }
}
