package org.notelog.entidades.notebook;

import org.notelog.config.Conexao;
import org.notelog.entidades.usuario.Funcionario;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class NotebookDAO {
    public Boolean adicionarNotebook(Notebook notebook) {
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();
        int funcionarioExistente = con.queryForObject("select count(*) from Funcionario where fkEmpresa = ? and id = ?", Integer.class, notebook.getFkEmpresa(), notebook.getFkFuncionario());
        int funcionarioJaAtrelado = con.queryForObject("select count(*) from Notebook join Funcionario on Funcionario.id = fkFuncionario where fkFuncionario = ?;", Integer.class, notebook.getFkFuncionario());

        if (funcionarioExistente == 1 && funcionarioJaAtrelado == 0) {
            String sql = "INSERT INTO Notebook (sistemaOperacional, fabricante, arquitetura, fkFuncionario, fkEmpresa) VALUES ('%s', '%s', '%s', %d, %d)"
                    .formatted(notebook.getSistemaOperacional(), notebook.getFabricante(), notebook.getArquitetura(), notebook.getFkFuncionario(), notebook.getFkEmpresa());
            con.update(sql);
            notebook.setId(con.queryForObject("SELECT id FROM Notebook ORDER BY id DESC LIMIT 1", Integer.class));
            return true;
        } else return false;

    }

    public Notebook consultaNotebook(Integer idUsuario){
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();

        String sql = "select * from notebook where fkFuncionario = %d;".formatted(idUsuario);

        // Utilizando queryForObject para retornar um único objeto Funcionario
        return con.queryForObject(sql, new BeanPropertyRowMapper<>(Notebook.class));

    };

}