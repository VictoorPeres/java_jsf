package br.com.avancard;

import javax.faces.bean.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@ManagedBean (name = "pessoaBean")
public class PessoaBean {
    private String nome;
    private String sobrenome;
    private String nomeCompleto;

    private List<String> nomes = new ArrayList<String>();


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public List<String> getNomes() {
        return nomes;
    }

    public void setNomes(List<String> nomes) {
        this.nomes = nomes;
    }

    public void mostrarNome() {
        this.setNomeCompleto(this.getNome() + " " + this.getSobrenome());
    }

    public String addNome() {
        this.setNomeCompleto(this.getNome() + " " + this.getSobrenome());
        this.nomes.add(this.getNomeCompleto());
        if (nomes.size() > 3) {
            return "index?faces-redirect=true";
        }

        return "";
    }

}
