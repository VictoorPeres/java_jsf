package br.com.avancard.repository;

import br.com.avancard.model.entity.Cidades;
import br.com.avancard.model.entity.Estados;
import br.com.avancard.model.entity.Pessoa;

import javax.faces.model.SelectItem;
import java.util.List;

public interface IDaoPessoa {

    Pessoa consultarPessoa(String login, String senha);
    //List<SelectItem> listaEstados();
    public List<Estados> listaEstados();
    public List<Cidades> listaCidadesPorId(Long id);

    }
