import br.com.avancard.repository.IDaoPessoa;
import br.com.avancard.repository.IDaoPessoaImpl;
import org.junit.Test;

import javax.persistence.Persistence;

public class TesteJPA {
    public static void main(String[] args) {
       /*Esse código inicializa a infraestrutura de persistência JPA, permitindo que a aplicação interaja com o banco de dados por meio das entidades definidas.*/
    }
    @Test
    public void teste(){
        IDaoPessoa iDaoPessoa = new IDaoPessoaImpl();
        System.out.println(iDaoPessoa.listaCidadesPorId(1L));
        //System.out.println(iDaoPessoa.listaCidadesPorId(2L));

    }
}
