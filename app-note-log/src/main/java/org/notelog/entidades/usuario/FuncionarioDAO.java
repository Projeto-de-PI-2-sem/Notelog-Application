package org.notelog.entidades.usuario;

import org.notelog.config.Conexao;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {
    public Funcionario verificaUsuario(String email, String senha) {
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();
        String sql = "SELECT F.id, F.nome, F.email, F.fkEmpresa FROM Funcionario AS F WHERE F.email = ? AND F.senha = ?";
        try {
            Funcionario usuario = con.queryForObject(sql, new BeanPropertyRowMapper<>(Funcionario.class), email, senha);
            return usuario;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Boolean temVinculo(Integer idUsuario){
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();
        Integer funcionarioJaAtrelado = con.queryForObject("select count(*) from funcionario AS f JOIN Notebook AS N ON f.id = N.fkFuncionario WHERE N.fkFUncionario = ?", Integer.class, idUsuario);
        if (funcionarioJaAtrelado == null || funcionarioJaAtrelado == 0) {
            return false;
        } else{
            return true;
        }
    };

    public List<Funcionario> buscarFuncionarios(Integer fkEmpresa) {
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();

        String sql = "SELECT F.id, F.nome FROM Funcionario as F LEFT JOIN Notebook ON F.id = Notebook.fkFuncionario WHERE Notebook.fkFuncionario IS NULL AND F.fkEmpresa = ?;";

        List<Funcionario> funcionarios = con.query(sql, new BeanPropertyRowMapper<>(Funcionario.class), fkEmpresa);
        return new ArrayList<>(funcionarios);
    }

}
