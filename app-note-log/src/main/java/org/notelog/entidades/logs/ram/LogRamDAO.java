package org.notelog.entidades.logs.ram;

import org.notelog.config.Conexao;
import org.springframework.jdbc.core.JdbcTemplate;

public class LogRamDAO {
    public void adicionarLogRam(LogRam logRam) {
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();

        int fkRam = con.queryForObject("SELECT id from Ram ORDER BY id DESC LIMIT 1", Integer.class);

        String sql = "INSERT INTO LogRam (fkRAM, usoMemoria, memoriaDisponivel) VALUES ('%d', '%s', '%s')"
                .formatted(fkRam, logRam.getUsoMemoria(), logRam.getMemoriaDisponivel());
        con.update(sql);
    }
}
