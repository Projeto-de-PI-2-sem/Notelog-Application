package org.notelog.entidades.notebook;

import com.github.britooo.looca.api.core.Looca;
import org.notelog.config.Conexao;
import org.notelog.entidades.cpu.Cpu;
import org.springframework.jdbc.core.JdbcTemplate;

public class NotebookDAO {
    public void adicionarNotebook(Notebook notebook) {
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();
        Looca looca = new Looca();

        String sql = "INSERT INTO Notebook (sistemaOperacional, fabricante, arquitetura) VALUES ('%s', '%s', '%s')"
                .formatted(looca.getSistema().getSistemaOperacional() , looca.getSistema().getFabricante(),
                        looca.getSistema().getArquitetura());
        con.update(sql);
    }
}
