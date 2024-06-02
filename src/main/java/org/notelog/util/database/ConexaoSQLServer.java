package org.notelog.util.database;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class ConexaoSQLServer {
    private JdbcTemplate conexaoDoBanco;

    public ConexaoSQLServer() {
        try {
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            dataSource.setUrl("jdbc:sqlserver://54.158.158.60:1433;database=notelog");
            dataSource.setUsername("notelogUser");
            dataSource.setPassword("notelikeag0d*");

            conexaoDoBanco = new JdbcTemplate(dataSource);
        } catch (DataAccessException e) {
            // Logar ou tratar a exceção
            System.err.println("Erro ao configurar conexão SQLServer: " + e.getMessage());
            conexaoDoBanco = null; // Definir como null se a configuração falhar
        }
    }


    public JdbcTemplate getConexaoDoBanco() {
        return conexaoDoBanco;
    }
}