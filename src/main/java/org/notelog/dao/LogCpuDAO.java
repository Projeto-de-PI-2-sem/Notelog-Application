package org.notelog.dao;

import org.notelog.model.LogCpu;
import org.notelog.util.database.ConexaoMySQL;
import org.springframework.jdbc.core.JdbcTemplate;

public class LogCpuDAO {
    public void adicionarLogCpu(LogCpu logCpu) {
        ConexaoMySQL conexaoMySQL = new ConexaoMySQL();
        JdbcTemplate con = conexaoMySQL.getConexaoDoBanco();
        String sql = "INSERT INTO LogCpu (porcentagemUso, dataLog, fkCPU) VALUES ('%s', '%s', %d)"
                .formatted(logCpu.getPorcentagemUso(), logCpu.dataHoraAtual(), logCpu.getFkCPU());
        con.update(sql);
    }
}
