package org.notelog.dao;

import org.notelog.util.database.ConexaoMySQL;
import org.notelog.model.Ram;
import org.notelog.util.database.ConexaoSQLServer;
import org.springframework.jdbc.core.JdbcTemplate;

public class  RamDAO {
    public Integer adicionarRam(Ram ram) {
        ConexaoMySQL conexaoMySQL = new ConexaoMySQL();
        JdbcTemplate conMySQL = conexaoMySQL.getConexaoDoBanco();

        ConexaoSQLServer conexaoSQLServer = new ConexaoSQLServer();
        JdbcTemplate conSQLServer = conexaoSQLServer.getConexaoDoBanco();

        String sqlInsert = "INSERT INTO Ram (totalMemoria, fkNotebook) VALUES (?, ?)";
        String sqlUpdate = "UPDATE Ram SET totalMemoria = ? WHERE fkNotebook = ?";
        String sqlSelectMySQL = "SELECT id FROM Ram WHERE fkNotebook = ? ORDER BY id DESC LIMIT 1";
        String sqlSelectSQLServer = "SELECT TOP 1 id FROM Ram WHERE fkNotebook = ? ORDER BY id DESC";

        try {
            if (!ramExiste(ram)) {
                    conSQLServer.update(sqlInsert, ram.getTotalMemoria(), ram.getFkNotebook());
                    ram.setId(conSQLServer.queryForObject(sqlSelectSQLServer, Integer.class, ram.getFkNotebook()));

                    conMySQL.update(sqlInsert, ram.getTotalMemoria(), ram.getFkNotebook());
                    ram.setId(conMySQL.queryForObject(sqlSelectMySQL, Integer.class, ram.getFkNotebook()));

            } else {

                    conSQLServer.update(sqlUpdate, ram.getTotalMemoria(), ram.getFkNotebook());
                    ram.setId(conSQLServer.queryForObject(sqlSelectSQLServer, Integer.class, ram.getFkNotebook()));

                    conMySQL.update(sqlUpdate, ram.getTotalMemoria(), ram.getFkNotebook());
                    ram.setId(conMySQL.queryForObject(sqlSelectMySQL, Integer.class, ram.getFkNotebook()));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ram.getFkNotebook();
    }


    private boolean ramExiste(Ram ram) {
        ConexaoMySQL conexaoMySQL = new ConexaoMySQL();
        JdbcTemplate conMySQL = conexaoMySQL.getConexaoDoBanco();

        ConexaoSQLServer conexaoSQLServer = new ConexaoSQLServer();
        JdbcTemplate conSQLServer = conexaoSQLServer.getConexaoDoBanco();

        String sql = "SELECT count(*) FROM Ram WHERE fkNotebook = ? AND totalMemoria = ?";
        Integer quantidade = null;

        try {

                quantidade = conMySQL.queryForObject(sql, Integer.class, ram.getFkNotebook(), ram.getTotalMemoria());

                quantidade = conSQLServer.queryForObject(sql, Integer.class, ram.getFkNotebook(), ram.getTotalMemoria());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return quantidade != null && quantidade > 0;
    }

}

