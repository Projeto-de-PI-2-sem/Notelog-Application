package org.notelog.dao;

import com.github.britooo.looca.api.core.Looca;

import com.github.britooo.looca.api.group.janelas.Janela;
import com.github.britooo.looca.api.group.janelas.JanelaGrupo;
import org.notelog.model.LogJanelas;
import org.notelog.util.database.ConexaoMySQL;
import org.notelog.util.database.ConexaoSQLServer;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;


public class LogJanelasDAO {

    private boolean logJanelasExiste(LogJanelas janela) {
        ConexaoMySQL conexaoMySQL = new ConexaoMySQL();
        JdbcTemplate conmysql = conexaoMySQL.getConexaoDoBanco();
//
//        ConexaoSQLServer conSQLServer = new ConexaoSQLServer();
//        JdbcTemplate consqlserver = conSQLServer.getConexaoDoBanco();

        Integer quantidade = null;

        try {

                quantidade = conmysql.queryForObject("SELECT COUNT(*) FROM LogJanelas WHERE idJanela = ? AND fkNotebook = ?", Integer.class, janela.getIdJanela(), janela.getFkNotebook());

//                quantidade = consqlserver.queryForObject("SELECT COUNT(*) FROM LogJanelas WHERE idJanela = ? AND fkNotebook = ?", Integer.class, janela.getIdJanela(), janela.getFkNotebook());


        } catch (EmptyResultDataAccessException e) {
            // Tratar exceção, se necessário
        }

        return quantidade != null && quantidade > 0;
    }


    public void adicionarNovoLogJanelas(Integer fkNotebook) {
        ConexaoMySQL conexaoMySQL = new ConexaoMySQL();
        JdbcTemplate conmysql = conexaoMySQL.getConexaoDoBanco();
//
//        ConexaoSQLServer conSQLServer = new ConexaoSQLServer();
//        JdbcTemplate consqlserver = conSQLServer.getConexaoDoBanco();

        Looca looca = new Looca();
        JanelaGrupo grupoDeJanelas = looca.getGrupoDeJanelas();
        List<Janela> janelas = grupoDeJanelas.getJanelasVisiveis();

        for (Janela janela : janelas) {
            LogJanelas novaLogJanela = new LogJanelas(null, janela.getPid().toString(), janela.getTitulo(), 0, fkNotebook);

            try {
                    if (!logJanelasExiste(novaLogJanela)) {
                        adicionarLogJanelas(novaLogJanela);
                    }
            } catch (Exception e) {
                // Tratar exceção, se necessário
            }
        }
    }


    public void adicionarLogJanelas(LogJanelas logJanelas) {
        ConexaoMySQL conexaoMySQL = new ConexaoMySQL();
        JdbcTemplate conmysql = conexaoMySQL.getConexaoDoBanco();

//        ConexaoSQLServer conSQLServer = new ConexaoSQLServer();
//        JdbcTemplate consqlserver = conSQLServer.getConexaoDoBanco();


        int fkNotebook = logJanelas.getFkNotebook();

        String sql = String.format(
                """
                   INSERT INTO LogJanelas (idJanela, nomeJanela, bloqueado, fkNotebook) VALUES ('%s', "%s", %b, %d);
                """,
                logJanelas.getIdJanela(),
                logJanelas.getNomeJanela(),
                false,
                logJanelas.getFkNotebook()
        );



//            consqlserver.update(sql);


            conmysql.update(sql);


    }

    public List<LogJanelas> selecionarJanelas(Integer idNotebook){
        ConexaoMySQL conexaoMySQL = new ConexaoMySQL();
        JdbcTemplate conmysql = conexaoMySQL.getConexaoDoBanco();

//        ConexaoSQLServer conSQLServer = new ConexaoSQLServer();
//        JdbcTemplate consqlserver = conSQLServer.getConexaoDoBanco();

        String sql = "SELECT * FROM LogJanelas WHERE fkNotebook = ?";

        List<LogJanelas> listaLogJanelas = new ArrayList<>();

            listaLogJanelas = conmysql.query(sql, new BeanPropertyRowMapper<>(LogJanelas.class), idNotebook);

//            listaLogJanelas = consqlserver.query(sql, new BeanPropertyRowMapper<>(LogJanelas.class), idNotebook);


        return listaLogJanelas;
    }


}

