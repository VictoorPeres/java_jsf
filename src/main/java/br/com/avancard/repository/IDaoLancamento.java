package br.com.avancard.repository;

import br.com.avancard.model.entity.Lancamento;

import java.util.ArrayList;
import java.util.List;

public interface IDaoLancamento {
    List<Lancamento> consultarLancamentoUsuario(Long idUsuario);
}
