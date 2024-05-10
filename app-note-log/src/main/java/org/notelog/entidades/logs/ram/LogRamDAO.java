package org.notelog.entidades.logs.ram;

import org.notelog.config.Conexao;
import org.springframework.jdbc.core.JdbcTemplate;

public class LogRamDAO {
    public void adicionarLogRam(LogRam logRam) {
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();
        String sql = "INSERT INTO LogRam (fkRAM, usoMemoria, memoriaDisponivel) VALUES ('%d', '%s', '%s')"
                .formatted(logRam.getFkRAM(), logRam.getUsoMemoria(), logRam.getMemoriaDisponivel());
        con.update(sql);
    }
}
