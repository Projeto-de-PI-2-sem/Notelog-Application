package org.notelog.dao;

import org.notelog.model.LogCpu;
import org.notelog.util.database.ConexaoMySQL;
import org.notelog.util.database.ConexaoSQLServer;
import org.springframework.jdbc.core.JdbcTemplate;

public class LogCpuDAO {
    public void adicionarLogCpu(LogCpu logCpu) {
        ConexaoMySQL conexaoMySQL = new ConexaoMySQL();
        JdbcTemplate conmysql = conexaoMySQL.getConexaoDoBanco();

//        ConexaoSQLServer conSQLServer = new ConexaoSQLServer();
//        JdbcTemplate consqlserver = conSQLServer.getConexaoDoBanco();

        String sql = """
        INSERT INTO LogCpu (porcentagemUso, dataLog, fkCPU)
        VALUES (?, ?, ?)
        """;

        Object[] params = {logCpu.getPorcentagemUso(), logCpu.dataHoraAtual(), logCpu.getFkCPU()};


            conmysql.update(sql, params);

//            consqlserver.update(sql, params);

    }

}
