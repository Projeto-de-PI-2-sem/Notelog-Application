package org.notelog.dao;

import org.notelog.util.database.ConexaoMySQL;
import org.notelog.model.Cpu;
import org.notelog.util.database.ConexaoSQLServer;
import org.springframework.jdbc.core.JdbcTemplate;

public class CpuDAO {
    public Integer adicionarCpu(Cpu cpu) {
        ConexaoMySQL conexaoMySQL = new ConexaoMySQL();
        JdbcTemplate conmysql = conexaoMySQL.getConexaoDoBanco();

        ConexaoSQLServer conSQLServer = new ConexaoSQLServer();
        JdbcTemplate consqlserver = conSQLServer.getConexaoDoBanco();

        Integer id = null;

        if (!cpuExiste(cpu)) {
            // SQL SERVER
            String sql = "INSERT INTO Cpu (nome, numeroFisico, numeroLogico, frequencia, idFisicoProcessador, fkNotebook) VALUES (?, ?, ?, ?, ?, ?)";

            consqlserver.update(sql, cpu.getNome(), cpu.getNumeroFisico(), cpu.getNumerologico(), cpu.getFrequencia(), cpu.getIdFisicoProcessador(), cpu.getFkNotebook());

            String selectSQLServer = "SELECT TOP 1 id FROM Cpu WHERE fkNotebook = ? ORDER BY id DESC";

            id = consqlserver.queryForObject(selectSQLServer, Integer.class, cpu.getFkNotebook());

            // MY SQL

            String mysql = "INSERT INTO Cpu (id,nome, numeroFisico, numeroLogico, frequencia, idFisicoProcessador, fkNotebook) VALUES (?, ?, ?, ?, ?, ?, ?)";

            conmysql.update(mysql, id, cpu.getNome(), cpu.getNumeroFisico(), cpu.getNumerologico(), cpu.getFrequencia(), cpu.getIdFisicoProcessador(), cpu.getFkNotebook());

        } else {
            String selectSQLServer = "SELECT TOP 1 id FROM Cpu WHERE fkNotebook = ? ORDER BY id DESC";

            id = consqlserver.queryForObject(selectSQLServer, Integer.class, cpu.getFkNotebook());

        }

        return id;
    }

    private boolean cpuExiste(Cpu cpu) {
        ConexaoSQLServer conSQLServer = new ConexaoSQLServer();
        JdbcTemplate consqlserver = conSQLServer.getConexaoDoBanco();


        String sql = "SELECT count(*) FROM Cpu WHERE fkNotebook = ? AND idFisicoProcessador = ?";
        Integer quantidade = 0;

        quantidade = consqlserver.queryForObject(sql, Integer.class, cpu.getFkNotebook(), cpu.getIdFisicoProcessador());

        return quantidade != null && quantidade > 0;
    }

}

