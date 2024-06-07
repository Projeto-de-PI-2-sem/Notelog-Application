package org.notelog.dao;

import org.notelog.util.database.ConexaoMySQL;
import org.notelog.model.Notebook;
import org.notelog.util.database.ConexaoSQLServer;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class NotebookDAO {
    public boolean adicionarNotebook(Notebook notebook) {
        ConexaoMySQL conexaoMySQL = new ConexaoMySQL();
        JdbcTemplate conMySQL = conexaoMySQL.getConexaoDoBanco();

        ConexaoSQLServer conexaoSQLServer = new ConexaoSQLServer();
        JdbcTemplate conSQLServer = conexaoSQLServer.getConexaoDoBanco();

        int funcionarioExistente = 0;
        int funcionarioJaAtrelado = 0;

            funcionarioExistente = conMySQL.queryForObject("SELECT COUNT(*) FROM Funcionario WHERE fkEmpresa = ? AND id = ?", Integer.class, notebook.getFkEmpresa(), notebook.getFkFuncionario());
            funcionarioJaAtrelado = conMySQL.queryForObject("SELECT COUNT(*) FROM Notebook JOIN Funcionario ON Funcionario.id = fkFuncionario WHERE fkFuncionario = ?", Integer.class, notebook.getFkFuncionario());

            funcionarioExistente = conSQLServer.queryForObject("SELECT COUNT(*) FROM Funcionario WHERE fkEmpresa = ? AND id = ?", Integer.class, notebook.getFkEmpresa(), notebook.getFkFuncionario());
            funcionarioJaAtrelado = conSQLServer.queryForObject("SELECT COUNT(*) FROM Notebook JOIN Funcionario ON Funcionario.id = fkFuncionario WHERE fkFuncionario = ?", Integer.class, notebook.getFkFuncionario());


        if (funcionarioExistente == 1 && funcionarioJaAtrelado == 0) {
            String sql = "INSERT INTO Notebook (sistemaOperacional, fabricante, arquitetura, numeroSerial, fkFuncionario, fkEmpresa) VALUES (?, ?, ?, ?, ?, ?)";
            Object[] params = {notebook.getSistemaOperacional(), notebook.getFabricante(), notebook.getArquitetura(), notebook.getNumeroSerial(), notebook.getFkFuncionario(), notebook.getFkEmpresa()};

                conSQLServer.update(sql, params);
                notebook.setId(conSQLServer.queryForObject("SELECT TOP 1 id FROM Notebook ORDER BY id DESC", Integer.class));

                conMySQL.update(sql, params);
                notebook.setId(conMySQL.queryForObject("SELECT id FROM Notebook ORDER BY id DESC LIMIT 1", Integer.class));


            return true;

        } else {
            return false;
        }
    }

    public Notebook consultaNotebook(Integer idUsuario) {
        ConexaoMySQL conexaoMySQL = new ConexaoMySQL();
        JdbcTemplate conMySQL = conexaoMySQL.getConexaoDoBanco();

        ConexaoSQLServer conexaoSQLServer = new ConexaoSQLServer();
        JdbcTemplate conSQLServer = conexaoSQLServer.getConexaoDoBanco();

        String sql = "SELECT * FROM Notebook WHERE fkFuncionario = ?";

        Notebook notebook = null;

        try {
                notebook = conMySQL.queryForObject(sql, new BeanPropertyRowMapper<>(Notebook.class), idUsuario);

                notebook = conSQLServer.queryForObject(sql, new BeanPropertyRowMapper<>(Notebook.class), idUsuario);

        } catch (EmptyResultDataAccessException e) {
            // Nenhum notebook encontrado para o usuário fornecido
            System.out.println("Nenhum notebook encontrado para o usuário com ID: " + idUsuario);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return notebook;
    }


}