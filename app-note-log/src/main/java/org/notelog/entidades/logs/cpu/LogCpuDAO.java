package org.notelog.entidades.logs.cpu;

import com.github.britooo.looca.api.core.Looca;
import org.notelog.config.Conexao;
import org.notelog.entidades.cpu.Cpu;
import org.springframework.jdbc.core.JdbcTemplate;

public class LogCpuDAO {
    public void adicionarLogCpu(LogCpu logCpu) {
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();

        String sql = "INSERT INTO LogCpu (porcentagemUso, fkCPU) VALUES ('%s', '%d')"
                .formatted(logCpu.getPorcentagemUso(), logCpu.getFkCPU());
        con.update(sql);
    }
}
