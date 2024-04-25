package org.notelog.entidades.logs.disco;

import com.github.britooo.looca.api.core.Looca;
import org.notelog.config.Conexao;
import org.notelog.entidades.disco.rigido.DiscoRigido;
import org.springframework.jdbc.core.JdbcTemplate;

public class LogDiscoDAO {
    public void adicionarDisco(DiscoRigido discoRigido) {
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();
        Looca looca = new Looca();
        LogDisco logDisco = new LogDisco();

//        String sql = "INSERT INTO LogDiscoDAO (leituras, bytesLeitura, escritas, bytesEscritas) VALUES ('%s', '%s', '%s', '%s')"
//                .formatted(looca.getGrupoDeDiscos(), logDisco.getLeituras(),logDisco.getBytesLeitura(), logDisco.getEscritas(), logDisco.getBytesEscritas());
//        con.update(sql);
    }
}
