package org.notelog.entidades.cpu;

import com.github.britooo.looca.api.core.Looca;
import org.notelog.config.Conexao;
import org.springframework.jdbc.core.JdbcTemplate;

public class CpuDAO {
    public void adicionarCpu(Cpu cpu) {
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();
        Looca looca = new Looca();

        String sql = "INSERT INTO Cpu (nome, numeroFisico, numeroLogico, frequencia, idFisicoProcessador) VALUES ('%s', '%s', '%s', '%s','%s')"
                .formatted(looca.getProcessador().getNome(), looca.getProcessador().getNumeroCpusFisicas(),
                        looca.getProcessador().getNumeroCpusLogicas(), looca.getProcessador().getFrequencia(), looca.getProcessador().getIdentificador());
        con.update(sql);
    }
}
