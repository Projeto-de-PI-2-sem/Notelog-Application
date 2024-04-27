package org.notelog.entidades.logs.janelas;

import com.github.britooo.looca.api.core.Looca;

import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.janelas.Janela;
import com.github.britooo.looca.api.group.janelas.JanelaGrupo;
import org.notelog.config.Conexao;
import org.notelog.entidades.disco.rigido.DiscoRigido;
import org.notelog.entidades.logs.cpu.LogCpu;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class LogJanelasDAO {
    private List<LogJanelas> buscarTodosOsDiscos() {
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();

        List<LogJanelas> janelas = con.query("select * from disco;", new BeanPropertyRowMapper<>(LogJanelas.class));

        return new ArrayList<>(janelas);
    }

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

    public void adicionarNovoLogJanelas() {
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();
        Looca looca = new Looca();
        JanelaGrupo grupoDeJanelas = looca.getGrupoDeJanelas();

        List<Janela> janelas = grupoDeJanelas.getJanelas();
        for (Janela janela : janelas) {
            LogJanelas novaLogJanela = new LogJanelas(null, janela.getJanelaId().toString(), null);

            if (!logJanelasExiste(novaLogJanela)) {
                adicionarLogJanelas(novaLogJanela);
            }
        }
    }

    public void adicionarLogJanelas(LogJanelas logJanelas) {
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();
        Looca looca = new Looca();
            String sql = "INSERT INTO LogJanelas (idJanela) VALUES ('%s')"
                    .formatted(logJanelas.getIdJanela());
            con.update(sql);
    }
}

