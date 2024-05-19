package org.notelog.dao;

import org.notelog.util.database.Conexao;
import org.notelog.model.Ram;
import org.springframework.jdbc.core.JdbcTemplate;

public class  RamDAO {
    public Integer adicionarRam(Ram ram) {
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();
        String sql;
        if (!ramExiste(ram)) {
            sql = "INSERT INTO Ram (totalMemoria, fkNotebook) VALUES ('%s', '%d')"
                    .formatted(ram.getTotalMemoria(), ram.getFkNotebook());
        }else {
            sql = "UPDATE Ram SET totalMemoria = '%s' WHERE fkNotebook = %d;"
                    .formatted(ram.getTotalMemoria(), ram.getFkNotebook());
        }
        con.update(sql);
        ram.setId(con.queryForObject("SELECT id from `Cpu` WHERE fkNotebook = ? ORDER BY id DESC LIMIT 1", Integer.class, ram.getFkNotebook()));
        return ram.getFkNotebook();


    }

    private boolean ramExiste(Ram ram) {
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();

        Integer quantidade = con.queryForObject("SELECT count(*) FROM Ram WHERE fkNotebook = ? AND totalMemoria = ?", Integer.class, ram.getFkNotebook(), ram.getTotalMemoria());
        if (quantidade != null) {
            return quantidade > 0;
        } else {
            return false;
        }
    }
}

