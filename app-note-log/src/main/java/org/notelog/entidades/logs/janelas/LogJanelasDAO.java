package org.notelog.entidades.logs.janelas;

import com.github.britooo.looca.api.core.Looca;
import org.notelog.config.Conexao;
import org.notelog.entidades.logs.cpu.LogCpu;
import org.springframework.jdbc.core.JdbcTemplate;

public class LogJanelasDAO {
    public void adicionarLogJanelas(LogJanelas logJanelas) {
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();
        Looca looca = new Looca();

        String sql = "INSERT INTO LogJanelas (enderecoJanela) VALUES ('%s')"
                .formatted(looca.getGrupoDeJanelas().getTotalJanelas());
        con.update(sql);
    }
}
