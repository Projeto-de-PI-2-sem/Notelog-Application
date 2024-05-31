package org.notelog.dao;

import org.notelog.util.database.ConexaoMySQL;
import org.notelog.model.Funcionario;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.notelog.model.Notebook.pegarNumeroSerial;

public class FuncionarioDAO {
    public Funcionario verificaUsuario(String email, String senha) {
        ConexaoMySQL conexaoMySQL = new ConexaoMySQL();
        JdbcTemplate con = conexaoMySQL.getConexaoDoBanco();
        String sql = "SELECT F.id, F.nome, F.email, F.fkEmpresa FROM Funcionario AS F WHERE F.email = ? AND F.senha = ?";
        try {
            Funcionario usuario = con.queryForObject(sql, new BeanPropertyRowMapper<>(Funcionario.class), email, senha);
            return usuario;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Boolean temVinculo(String numeroSerial){
        ConexaoMySQL conexaoMySQL = new ConexaoMySQL();
        JdbcTemplate con = conexaoMySQL.getConexaoDoBanco();
        Integer funcionarioJaAtrelado = con.queryForObject("select count(*) from Funcionario AS f JOIN Notebook AS N ON f.id = N.fkFuncionario WHERE N.numeroSerial = ?", Integer.class, numeroSerial);
        if (funcionarioJaAtrelado == null || funcionarioJaAtrelado == 0) {
            return false;
        } else{
            return true;
        }
    };

    public Funcionario pegaFuncionarioPeloNumeroSerial(){
        ConexaoMySQL conexaoMySQL = new ConexaoMySQL();
        JdbcTemplate con = conexaoMySQL.getConexaoDoBanco();
        String numeroSerial = pegarNumeroSerial(); // Número de série fixo para o exemplo

        String sql = "SELECT Funcionario.* FROM Funcionario " +
                "JOIN Notebook ON Funcionario.id = Notebook.fkFuncionario " +
                "WHERE Notebook.numeroSerial = ?";

        try {
            return con.queryForObject(sql, new BeanPropertyRowMapper<>(Funcionario.class), numeroSerial);
        } catch (EmptyResultDataAccessException e) {
            // Retorna null se nenhum funcionário for encontrado
            return null;
        } catch (Exception e) {
            // Imprime a pilha de erros e retorna null para outras exceções
            e.printStackTrace();
            return null;
        }




    }

    public List<Funcionario> buscarFuncionarios(Integer fkEmpresa) {
        ConexaoMySQL conexaoMySQL = new ConexaoMySQL();
        JdbcTemplate con = conexaoMySQL.getConexaoDoBanco();

        String sql = "SELECT F.id, F.nome FROM Funcionario as F LEFT JOIN Notebook ON F.id = Notebook.fkFuncionario WHERE Notebook.fkFuncionario IS NULL AND F.fkEmpresa = ?;";

        List<Funcionario> funcionarios = con.query(sql, new BeanPropertyRowMapper<>(Funcionario.class), fkEmpresa);
        return new ArrayList<>(funcionarios);
    }

}
