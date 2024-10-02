import javax.persistence.Persistence;

public class TesteJPA {
    public static void main(String[] args) {
        Persistence.createEntityManagerFactory("jpa_jsf"); /*Esse código inicializa a infraestrutura de persistência JPA, permitindo que a aplicação interaja com o banco de dados por meio das entidades definidas.*/
    }
}
