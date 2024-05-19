package org.notelog.dao;

import org.notelog.util.database.Conexao;
import org.notelog.model.Cpu;
import org.springframework.jdbc.core.JdbcTemplate;

public class CpuDAO {
    public Integer adicionarCpu(Cpu cpu) {
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();

        if (!cpuExiste(cpu)) {
            String sql = "INSERT INTO `Cpu` (nome, numeroFisico, numeroLogico, frequencia, idFisicoProcessador, fkNotebook) VALUES ('%s', '%s', '%s', '%s','%s', '%d')"
                    .formatted(cpu.getNome(), cpu.getNumeroFisico(), cpu.getNumerologico(), cpu.getFrequencia(), cpu.getIdFisicoProcessador(), cpu.getFkNotebook());
            con.update(sql);
        }
        cpu.setId(con.queryForObject("SELECT id from `Cpu` WHERE fkNotebook = ? ORDER BY id DESC LIMIT 1", Integer.class, cpu.getFkNotebook()));
        return cpu.getId();
    }

    private boolean cpuExiste(Cpu cpu) {
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();

        Integer quantidade = con.queryForObject("SELECT count(*) FROM `Cpu` WHERE fkNotebook = ? AND idFisicoProcessador = ?;", Integer.class, cpu.getFkNotebook(), cpu.getIdFisicoProcessador());
        if (quantidade != null) {
            return quantidade > 0;
        } else {
            return false;
        }
    }
}

