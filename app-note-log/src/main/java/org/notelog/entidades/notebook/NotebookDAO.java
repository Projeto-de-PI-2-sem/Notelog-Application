package org.notelog.entidades.notebook;

import com.github.britooo.looca.api.core.Looca;
import org.notelog.config.Conexao;
import org.notelog.entidades.cpu.Cpu;
import org.springframework.jdbc.core.JdbcTemplate;

public class NotebookDAO {
    public Boolean adicionarNotebook(Notebook notebook) {
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();
        int funcionarioExistente = con.queryForObject("select count(*) from Funcionario where fkEmpresa = ? and id = ?", Integer.class, notebook.getFkEmpresa(), notebook.getFkFuncionario());



        if (funcionarioExistente >= 1) {
            String sql = "INSERT INTO Notebook (sistemaOperacional, fabricante, arquitetura, fkFuncionario, fkEmpresa) VALUES ('%s', '%s', '%s', %d, %d)"
                    .formatted(notebook.getSistemaOperacional(), notebook.getFabricante(), notebook.getArquitetura(), notebook.getFkFuncionario(), notebook.getFkEmpresa());
            con.update(sql);
            return true;
        } else return false;

    }
}