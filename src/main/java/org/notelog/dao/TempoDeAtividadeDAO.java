package org.notelog.dao;

import org.notelog.util.database.ConexaoMySQL;
import org.notelog.model.TempoDeAtividade;
import org.notelog.util.database.ConexaoSQLServer;
import org.springframework.jdbc.core.JdbcTemplate;

public class TempoDeAtividadeDAO {
    public void adicionarTempoDeAtividade(TempoDeAtividade tempoDeAtividade) {
        ConexaoMySQL conexaoMySQL = new ConexaoMySQL();
        JdbcTemplate conMySQL = conexaoMySQL.getConexaoDoBanco();

//        ConexaoSQLServer conexaoSQLServer = new ConexaoSQLServer();
//        JdbcTemplate conSQLServer = conexaoSQLServer.getConexaoDoBanco();

        String sqlInsert = "INSERT INTO TempoDeAtividade (fkNotebook, tempoDeAtividade, tempoInicializado) VALUES (?, ?, ?)";
        String sqlUpdate = "UPDATE TempoDeAtividade SET tempoDeAtividade = ? WHERE tempoInicializado = ? AND fkNotebook = ?";

        try {


//                if (!tempoDeAtividadeExiste(tempoDeAtividade)) {
//                    conSQLServer.update(sqlInsert, tempoDeAtividade.getFkNotebook(), tempoDeAtividade.getTempoDeAtividade(), tempoDeAtividade.getTempoInicializado());
//                } else {
//                    conSQLServer.update(sqlUpdate, tempoDeAtividade.getTempoDeAtividade(), tempoDeAtividade.getTempoInicializado(), tempoDeAtividade.getFkNotebook());
//                }



                if (!tempoDeAtividadeExiste(tempoDeAtividade)) {
                    conMySQL.update(sqlInsert, tempoDeAtividade.getFkNotebook(), tempoDeAtividade.getTempoDeAtividade(), tempoDeAtividade.getTempoInicializado());
                } else {
                    conMySQL.update(sqlUpdate, tempoDeAtividade.getTempoDeAtividade(), tempoDeAtividade.getTempoInicializado(), tempoDeAtividade.getFkNotebook());
                }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean tempoDeAtividadeExiste(TempoDeAtividade tempoDeAtividade) {
        ConexaoMySQL conexaoMySQL = new ConexaoMySQL();
        JdbcTemplate conMySQL = conexaoMySQL.getConexaoDoBanco();

//        ConexaoSQLServer conexaoSQLServer = new ConexaoSQLServer();
//        JdbcTemplate conSQLServer = conexaoSQLServer.getConexaoDoBanco();

        String sql = "SELECT count(*) FROM TempoDeAtividade WHERE fkNotebook = ? AND tempoInicializado = ?";
        Integer quantidade = 0;

        try {

                quantidade = conMySQL.queryForObject(sql, Integer.class, tempoDeAtividade.getFkNotebook(), tempoDeAtividade.getTempoInicializado());

//                quantidade = conSQLServer.queryForObject(sql, Integer.class, tempoDeAtividade.getFkNotebook(), tempoDeAtividade.getTempoInicializado());

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return quantidade != null && quantidade > 0;
    }

}
