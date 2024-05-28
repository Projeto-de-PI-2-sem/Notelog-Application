package org.notelog.dao;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import org.notelog.model.LogDisco;
import org.notelog.util.database.Conexao;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.logging.Logger;

import static org.notelog.model.LogAbstract.dataHoraAtual;

public class LogDiscoDAO {
    private final JdbcTemplate jdbcTemplate;
    private static final Logger logger = Logger.getLogger(LogDiscoDAO.class.getName());

    public LogDiscoDAO() {
        Conexao conexao = new Conexao();
        this.jdbcTemplate = conexao.getConexaoDoBanco();
    }

    public void adicionarLogDisco(LogDisco logDisco, Integer fkDiscoRigido) {
        String sql = "INSERT INTO LogDisco (fkDiscoRigido, usoDisco, dataLog) " +
                "VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, fkDiscoRigido, (Long.parseLong(logDisco.getLeituras()) + Long.parseLong(logDisco.getBytesLeitura()) +
                Long.parseLong(logDisco.getEscritas()) + Long.parseLong(logDisco.getBytesEscritas())), dataHoraAtual());
    }

    private Boolean logDiscoExiste(LogDisco logDisco, Integer fkDiscoRigido) {
        String sql = "SELECT COUNT(*) FROM LogDisco WHERE fkDiscoRigido = ? AND datalog = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, fkDiscoRigido,
                logDisco.getDataLog());
        return count != null && count > 0;
    }

    public void adicionarNovoLogDisco(Integer idNotebook) {
        Looca looca = new Looca();
        DiscoGrupo grupoDeDiscos = looca.getGrupoDeDiscos();
        List<Disco> discos = grupoDeDiscos.getDiscos();

        for (Disco disco : discos) {
            Integer fkDiscoRigido = null;
            Integer fkNotebook = null;
            try {
                fkDiscoRigido = jdbcTemplate.queryForObject("SELECT DiscoRigido.id FROM DiscoRigido WHERE DiscoRigido.serial = ?;", Integer.class, disco.getSerial());
                fkNotebook = jdbcTemplate.queryForObject("SELECT DiscoRigido.fkNotebook FROM DiscoRigido WHERE DiscoRigido.serial = ?;", Integer.class, disco.getSerial());
            } catch (EmptyResultDataAccessException e) {
                logger.warning("Nenhum disco rígido encontrado para o serial " + disco.getSerial());
                continue;  // Pular para o próximo disco se não encontrar nenhum resultado
            }

            if (!idNotebook.equals(fkNotebook)) {
                jdbcTemplate.update("UPDATE DiscoRigido SET fkNotebook = ? WHERE serial = ?;", idNotebook, disco.getSerial());
            }

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
}