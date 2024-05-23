package org.notelog.dao;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import org.notelog.model.LogDisco;
import org.notelog.util.database.Conexao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.logging.Logger;

public class LogDiscoDAO {
    private final JdbcTemplate jdbcTemplate;
    private static final Logger logger = Logger.getLogger(LogDiscoDAO.class.getName());

    public LogDiscoDAO() {
        Conexao conexao = new Conexao();
        this.jdbcTemplate = conexao.getConexaoDoBanco();
    }

    public void adicionarLogDisco(LogDisco logDisco, Integer fkDiscoRigido) {
        String sql = "INSERT INTO LogDisco (fkDiscoRigido, leitura, bytesLeitura, escrita, bytesEscrita) " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, fkDiscoRigido, logDisco.getLeituras(), logDisco.getBytesLeitura(),
                logDisco.getEscritas(), logDisco.getBytesEscritas());
        logger.info("LogDisco adicionado com sucesso: " + logDisco);
    }

    private Boolean logDiscoExiste(LogDisco logDisco, Integer fkDiscoRigido) {
        String sql = "SELECT COUNT(*) FROM LogDisco WHERE fkDiscoRigido = ? AND leitura = ? AND bytesLeitura = ? " +
                "AND escrita = ? AND bytesEscrita = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, fkDiscoRigido,
                logDisco.getLeituras(), logDisco.getBytesLeitura(),
                logDisco.getEscritas(), logDisco.getBytesEscritas());
        return count != null && count > 0;
    }

    public void adicionarNovoLogDisco() {
        Looca looca = new Looca();
        DiscoGrupo grupoDeDiscos = looca.getGrupoDeDiscos();
        List<Disco> discos = grupoDeDiscos.getDiscos();

        Integer fkDiscoRigido = jdbcTemplate.queryForObject(
                "SELECT id FROM DiscoRigido ORDER BY id DESC LIMIT 1", Integer.class);

        for (Disco disco : discos) {
            LogDisco novoLogDiscoRigido = new LogDisco(null, disco.getLeituras().toString(),
                    disco.getBytesDeLeitura().toString(),
                    disco.getEscritas().toString(),
                    disco.getBytesDeEscritas().toString());

            if (!logDiscoExiste(novoLogDiscoRigido, fkDiscoRigido)) {
                adicionarLogDisco(novoLogDiscoRigido, fkDiscoRigido);
            } else {
                logger.info("LogDisco já existe: " + novoLogDiscoRigido);
            }
        }
    }

    public void adicionarLogDisco() {
    }
}
