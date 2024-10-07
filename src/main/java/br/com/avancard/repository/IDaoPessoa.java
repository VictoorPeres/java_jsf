package br.com.avancard.repository;

import br.com.avancard.model.entity.Pessoa;

public interface IDaoPessoa {

    Pessoa consultarPessoa(String login, String senha);
}
