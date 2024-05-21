package org.notelog.dao;

import org.notelog.model.LogRam;
import org.notelog.util.database.Conexao;
import org.springframework.jdbc.core.JdbcTemplate;

public class LogRamDAO {
    public void adicionarLogRam(LogRam logRam) {
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();
        String sql = "INSERT INTO LogRam (fkRAM, usoMemoria, memoriaDisponivel, dataLog) VALUES ('%d', '%s', '%s', '%s')"
                .formatted(logRam.getFkRAM(), logRam.getUsoMemoria(), logRam.getMemoriaDisponivel(), logRam.dataHoraAtual());
        con.update(sql);
    }
}
