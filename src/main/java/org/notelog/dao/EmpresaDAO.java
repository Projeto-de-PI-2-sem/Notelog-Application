package org.notelog.dao;

import org.notelog.model.Empresa;
import org.notelog.model.Notebook;
import org.notelog.util.database.Conexao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class EmpresaDAO {

    public Empresa consultaEmpresa(Integer fkEmpresa){
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();

        String sql = "select * from Empresa where id = %d;".formatted(fkEmpresa);

        // Utilizando queryForObject para retornar um único objeto Empresa
        return con.queryForObject(sql, new BeanPropertyRowMapper<>(Empresa.class));

    };
}
