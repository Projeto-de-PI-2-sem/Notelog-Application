package org.notelog.dao;

import com.github.britooo.looca.api.core.Looca;

import com.github.britooo.looca.api.group.janelas.Janela;
import com.github.britooo.looca.api.group.janelas.JanelaGrupo;
import org.notelog.model.LogJanelas;
import org.notelog.util.database.Conexao;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class LogJanelasDAO {

    private boolean logJanelasExiste(LogJanelas janela) {
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();

        Integer quantidade = con.queryForObject("select count(*) from LogJanelas where idJanela = '%s' and fkNotebook = '%s'".formatted(janela.getIdJanela(), janela.getFkNotebook()), Integer.class);

        if (quantidade != null) {
            return quantidade > 0;
        } else {
            return false;
        }
    }

    public void adicionarNovoLogJanelas(Integer fkNotebook) {
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();
        Looca looca = new Looca();
        JanelaGrupo grupoDeJanelas = looca.getGrupoDeJanelas();

        List<Janela> janelas = grupoDeJanelas.getJanelas();
        for (Janela janela : janelas) {
            LogJanelas novaLogJanela = new LogJanelas(null, janela.getJanelaId().toString(), janela.getTitulo(), fkNotebook);

            if (!logJanelasExiste(novaLogJanela)) {
                adicionarLogJanelas(novaLogJanela);
            }
        }
    }

    public void adicionarLogJanelas(LogJanelas logJanelas) {
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();
        int fkNotebook = logJanelas.getFkNotebook();

        String sql = String.format(
                """
                   INSERT INTO LogJanelas (idJanela, nomeJanela, bloqueado, fkNotebook) VALUES ('%s', "%s", %b, %d);
                """,
                logJanelas.getIdJanela(),
                logJanelas.getNomeJanela(),
                logJanelas.getBloqueado(),
                logJanelas.getFkNotebook()
        );
        con.update(sql);

    }
}

