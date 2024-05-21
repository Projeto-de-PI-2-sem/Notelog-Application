package org.notelog.dao;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import org.notelog.model.LogDisco;
import org.notelog.util.database.Conexao;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class
LogDiscoDAO {
    public void adicionarLogDisco(LogDisco logDisco) {
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();
        int fkDiscoRigido = con.queryForObject("SELECT id from DiscoRigido ORDER BY id DESC LIMIT 1", Integer.class);

        String sql = "INSERT INTO LogDisco (fkDiscoRigido, usoDisco, dataLog) VALUES (%d, '%s', '%s')"
                .formatted(fkDiscoRigido, (Long.parseLong(logDisco.getLeituras()) + Long.parseLong(logDisco.getBytesLeitura()) + Long.parseLong(logDisco.getEscritas()) + Long.parseLong(logDisco.getBytesEscritas())), logDisco.dataHoraAtual());
        con.update(sql);
    }

    private boolean logDiscoExiste (LogDisco logDisco){
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();

        //Pega o ultimo Id inserido do DiscoRigido, porem é necessário uma melhor alternativa futuramente
        int fkDiscoRigido = con.queryForObject("SELECT id from DiscoRigido ORDER BY id DESC LIMIT 1", Integer.class);

        // Dar um jeito de identificar se o registro existe ou não
        Integer quantidade = con.queryForObject(("select count(*) from DiscoRigido join LogDisco on fkDiscoRigido = DiscoRigido.id " +
                "where DiscoRigido.id = %d and usoDisco = '%s'")
                .formatted(fkDiscoRigido, (Long.parseLong(logDisco.getLeituras()) + Long.parseLong(logDisco.getBytesLeitura()) + Long.parseLong(logDisco.getEscritas()) + Long.parseLong(logDisco.getBytesEscritas()))), Integer.class);

        if (quantidade != null) {
            return quantidade > 0;
        } else {
            return false;
        }
    }

    public void adiconarNovoLogDisco() {

        Looca looca = new Looca();
        DiscoGrupo grupoDeDiscos = looca.getGrupoDeDiscos();

        List<Disco> discos = grupoDeDiscos.getDiscos();

        for (Disco disco : discos) {
            LogDisco novoLogDiscoRigido = new LogDisco( null, disco.getLeituras().toString(), disco.getBytesDeLeitura().toString(), disco.getEscritas().toString(), disco.getBytesDeEscritas().toString());


            if (!logDiscoExiste(novoLogDiscoRigido)) {
                adicionarLogDisco(novoLogDiscoRigido);
            }
        }


    }
}
