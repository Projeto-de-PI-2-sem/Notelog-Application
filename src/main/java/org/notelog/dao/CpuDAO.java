package org.notelog.dao;

import org.notelog.util.database.ConexaoMySQL;
import org.notelog.model.Cpu;
import org.notelog.util.database.ConexaoSQLServer;
import org.springframework.jdbc.core.JdbcTemplate;

public class CpuDAO {
    public Integer adicionarCpu(Cpu cpu) {
        ConexaoMySQL conexaoMySQL = new ConexaoMySQL();
        JdbcTemplate conmysql = conexaoMySQL.getConexaoDoBanco();

//        ConexaoSQLServer conSQLServer = new ConexaoSQLServer();
//        JdbcTemplate consqlserver = conSQLServer.getConexaoDoBanco();


        if (!cpuExiste(cpu)) {
            String sql = "INSERT INTO Cpu (nome, numeroFisico, numeroLogico, frequencia, idFisicoProcessador, fkNotebook) VALUES (?, ?, ?, ?, ?, ?)";


                // Inserir no SQL Server
//                consqlserver.update(sql, cpu.getNome(), cpu.getNumeroFisico(), cpu.getNumerologico(), cpu.getFrequencia(), cpu.getIdFisicoProcessador(), cpu.getFkNotebook());



                // Inserir no MySQL
                conmysql.update(sql, cpu.getNome(), cpu.getNumeroFisico(), cpu.getNumerologico(), cpu.getFrequencia(), cpu.getIdFisicoProcessador(), cpu.getFkNotebook());

        }

        Integer id = null;


            // Obter ID do SQL Server
            id = conmysql.queryForObject("SELECT id FROM Cpu WHERE fkNotebook = ? ORDER BY id DESC LIMIT 1", Integer.class, cpu.getFkNotebook());



            // Obter ID do MySQL
//            id = consqlserver.queryForObject("SELECT TOP 1 id FROM Cpu WHERE fkNotebook = ? ORDER BY id DESC", Integer.class, cpu.getFkNotebook());


        cpu.setId(id);
        return id;
    }

    private boolean cpuExiste(Cpu cpu) {
        ConexaoMySQL conexaoMySQL = new ConexaoMySQL();
        JdbcTemplate conmysql = conexaoMySQL.getConexaoDoBanco();

//        ConexaoSQLServer conSQLServer = new ConexaoSQLServer();
//        JdbcTemplate consqlserver = conSQLServer.getConexaoDoBanco();


        String sql = "SELECT count(*) FROM Cpu WHERE fkNotebook = ? AND idFisicoProcessador = ?";
        Integer quantidade = 0;

            quantidade = conmysql.queryForObject(sql, Integer.class, cpu.getFkNotebook(), cpu.getIdFisicoProcessador());

//            quantidade = consqlserver.queryForObject(sql, Integer.class, cpu.getFkNotebook(), cpu.getIdFisicoProcessador());


        return quantidade != null && quantidade > 0;
    }

}

