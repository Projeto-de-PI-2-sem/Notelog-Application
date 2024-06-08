package org.notelog.dao;

import org.notelog.util.database.ConexaoMySQL;
import org.notelog.model.TempoDeAtividade;
import org.notelog.util.database.ConexaoSQLServer;
import org.springframework.jdbc.core.JdbcTemplate;

public class TempoDeAtividadeDAO {
    public void adicionarTempoDeAtividade(TempoDeAtividade tempoDeAtividade) {
        ConexaoMySQL conexaoMySQL = new ConexaoMySQL();
        JdbcTemplate conMySQL = conexaoMySQL.getConexaoDoBanco();

        ConexaoSQLServer conexaoSQLServer = new ConexaoSQLServer();
        JdbcTemplate conSQLServer = conexaoSQLServer.getConexaoDoBanco();

        String sqlInsert = "INSERT INTO TempoDeAtividade (fkNotebook, tempoDeAtividade, tempoInicializado) VALUES (?, ?, ?)";
        String sqlUpdate = "UPDATE TempoDeAtividade SET tempoDeAtividade = ? WHERE tempoInicializado = ? AND fkNotebook = ?";

        try {

                if (tempoDeAtividadeExiste(tempoDeAtividade) == false) {

                    // SQL SERVER

                    // insert
                    conSQLServer.update(sqlInsert, tempoDeAtividade.getFkNotebook(), tempoDeAtividade.getTempoDeAtividade(), tempoDeAtividade.getTempoInicializado());

                    String selectSQLServer = "SELECT TOP 1 id FROM TempoDeAtividade WHERE fkNotebook = ? ORDER BY id DESC";

                    Integer id = null;

                    id = conSQLServer.queryForObject(selectSQLServer, Integer.class, tempoDeAtividade.getFkNotebook());

                    // MY SQL

                    String mysqlInsert = "INSERT INTO TempoDeAtividade (id, fkNotebook, tempoDeAtividade, tempoInicializado) VALUES (?, ?, ?, ?)";

                    conMySQL.update(mysqlInsert, id, tempoDeAtividade.getFkNotebook(), tempoDeAtividade.getTempoDeAtividade(), tempoDeAtividade.getTempoInicializado());


                } else {

                    // update
                    conSQLServer.update(sqlUpdate, tempoDeAtividade.getTempoDeAtividade(), tempoDeAtividade.getTempoInicializado(), tempoDeAtividade.getFkNotebook());
                    conMySQL.update(sqlUpdate, tempoDeAtividade.getTempoDeAtividade(), tempoDeAtividade.getTempoInicializado(), tempoDeAtividade.getFkNotebook());

                }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean tempoDeAtividadeExiste(TempoDeAtividade tempoDeAtividade) {
        ConexaoSQLServer conexaoSQLServer = new ConexaoSQLServer();
        JdbcTemplate conSQLServer = conexaoSQLServer.getConexaoDoBanco();

        String sql = "SELECT count(*) FROM TempoDeAtividade WHERE fkNotebook = ? AND tempoInicializado = ?";
        Integer quantidade = null;

        quantidade = conSQLServer.queryForObject(sql, Integer.class, tempoDeAtividade.getFkNotebook(), tempoDeAtividade.getTempoInicializado());

        if (quantidade != null && quantidade > 0){
            return true;
        }else{
            return false;
        }

    }

}
