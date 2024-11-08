package br.com.avancard.repository;

import br.com.avancard.model.entity.Lancamento;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface IDaoLancamento {
    List<Lancamento> consultarLancamentoUsuario(Long idUsuario);
    List<Lancamento> consultarTopDez(Long idUsuario);
    List<Lancamento> relatorioLancamento(String numNota, Date dataIni, Date dataFim);
}
