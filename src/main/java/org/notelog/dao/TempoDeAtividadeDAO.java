package org.notelog.dao;

import org.notelog.util.database.Conexao;
import org.notelog.model.TempoDeAtividade;
import org.springframework.jdbc.core.JdbcTemplate;

public class TempoDeAtividadeDAO {
    public void adicionarTempoDeAtividade(TempoDeAtividade tempoDeAtividade) {
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();

        String sql;

        if (!tempoDeAtividadeExiste(tempoDeAtividade)) {
            sql = "INSERT INTO TempoDeAtividade (fkNotebook, tempoDeAtividade, tempoInicializado) VALUES ('%d', '%s', '%s')"
                    .formatted(tempoDeAtividade.getFkNotebook(), tempoDeAtividade.getTempoDeAtividade(), tempoDeAtividade.getTempoInicializado());

        } else {
            sql = "UPDATE TempoDeAtividade SET tempoDeAtividade = '%s' WHERE tempoInicializado = '%s' AND fkNotebook = %d;"
                    .formatted(tempoDeAtividade.getTempoDeAtividade(), tempoDeAtividade.getTempoInicializado(), tempoDeAtividade.getFkNotebook());
        }
         con.update(sql);
    }

    private boolean tempoDeAtividadeExiste(TempoDeAtividade tempoDeAtividade) {
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();

        Integer quantidade = con.queryForObject("SELECT count(*) FROM TempoDeAtividade WHERE fkNotebook = ? AND tempoInicializado = ?", Integer.class, tempoDeAtividade.getFkNotebook(), tempoDeAtividade.getTempoInicializado());
        if (quantidade != null && quantidade> 0) {
            return true;
        } else {
            return false;
        }
    }
}
