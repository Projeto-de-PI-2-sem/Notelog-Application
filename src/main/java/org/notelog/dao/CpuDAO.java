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

        if (!cpuExiste(cpu)) {
            String sql = "INSERT INTO `Cpu` (nome, numeroFisico, numeroLogico, frequencia, idFisicoProcessador, fkNotebook) VALUES ('%s', '%s', '%s', '%s','%s', '%d')"
                    .formatted(cpu.getNome(), cpu.getNumeroFisico(), cpu.getNumerologico(), cpu.getFrequencia(), cpu.getIdFisicoProcessador(), cpu.getFkNotebook());

            conmysql.update(sql);
            consqlserver.update(sql);
        }
        cpu.setId(conmysql.queryForObject("SELECT id from `Cpu` WHERE fkNotebook = ? ORDER BY id DESC LIMIT 1", Integer.class, cpu.getFkNotebook()));
        cpu.setId(conmysql.queryForObject("SELECT id from `Cpu` WHERE fkNotebook = ? ORDER BY id DESC LIMIT 1", Integer.class, cpu.getFkNotebook()));

        return cpu.getId();
    }

    private boolean cpuExiste(Cpu cpu) {
        ConexaoMySQL conexaoMySQL = new ConexaoMySQL();
        JdbcTemplate con = conexaoMySQL.getConexaoDoBanco();

        Integer quantidade = con.queryForObject("SELECT count(*) FROM `Cpu` WHERE fkNotebook = ? AND idFisicoProcessador = ?;", Integer.class, cpu.getFkNotebook(), cpu.getIdFisicoProcessador());
        if (quantidade != null) {
            return quantidade > 0;
        } else {
            return false;
        }
    }
}

