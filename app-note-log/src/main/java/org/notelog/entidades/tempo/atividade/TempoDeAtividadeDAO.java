package org.notelog.entidades.tempo.atividade;

import com.github.britooo.looca.api.core.Looca;
import org.notelog.config.Conexao;
import org.notelog.entidades.cpu.Cpu;
import org.springframework.jdbc.core.JdbcTemplate;

public class TempoDeAtividadeDAO {
    public void adicionarTempoDeAtividade(TempoDeAtividade tempoDeAtividade) {
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();
        Looca looca = new Looca();

        String sql = "INSERT INTO TempoDeAtividade (tempoDeAtividade, tempoInicializado) VALUES ('%s', '%s')"
                .formatted(looca.getSistema().getTempoDeAtividade(),looca.getSistema().getInicializado());
        con.update(sql);
    }
}
