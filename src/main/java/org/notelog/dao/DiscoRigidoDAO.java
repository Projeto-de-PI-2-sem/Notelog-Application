package org.notelog.dao;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import org.notelog.SimpleLogger;
import org.notelog.util.database.Conexao;
import com.github.britooo.looca.api.group.discos.Disco;
import org.notelog.model.DiscoRigido;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DiscoRigidoDAO {

    SimpleLogger logger;

    {
        try {
            logger = new SimpleLogger("application.log");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void adicionarDisco(DiscoRigido discoRigido) {
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();

        String sql = "INSERT INTO DiscoRigido(modelo, serial, tamanho, fkNotebook) VALUES ('%s', '%s', '%s', %d)"
                .formatted(discoRigido.getModelo(), discoRigido.getSerial(), discoRigido.getTamanho(), discoRigido.getFkNotebook());
        logger.info("Inserindo informações de Disco no Banco de dados");
        con.update(sql);
        discoRigido.setId(con.queryForObject("SELECT id FROM DiscoRigido WHERE fkNotebook = ? ORDER BY id DESC LIMIT 1;", Integer.class, discoRigido.getFkNotebook()));
    }

    private boolean discoExiste(DiscoRigido disco) {
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();

        Integer quantidade = con.queryForObject("select count(*) from DiscoRigido where modelo = '%s' and serial = '%s'".formatted(disco.getModelo(), disco.getSerial()), Integer.class);

        if (quantidade != null) {
            return quantidade > 0;
        } else {
            return false;
        }
    }

    public static List<DiscoRigido> buscarTodosOsDiscos() {
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();

        List<DiscoRigido> discos = con.query("select * from DiscoRigido;", new BeanPropertyRowMapper<>(DiscoRigido.class));

        return new ArrayList<>(discos);
    }



    private DiscoRigido buscarDiscoPeloSerial(String serial) {
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();

        DiscoRigido discos = con.queryForObject("select * from DiscoRigido where serial = '%s'".formatted(serial), new BeanPropertyRowMapper<>(DiscoRigido.class));

        return discos;
    }

    public void exibirTodosOsDiscos() {
        for (DiscoRigido discoRigido : buscarTodosOsDiscos()) {
            System.out.println(discoRigido);
        }
    }

    public void adiconarNovoDisco(Integer fkNotebook) {

        Looca looca = new Looca();
        DiscoGrupo grupoDeDiscos = looca.getGrupoDeDiscos();

        List<Disco> discos = grupoDeDiscos.getDiscos();

        for (Disco disco : discos) {
            DiscoRigido novoDiscoRigido = new DiscoRigido(null, disco.getModelo(), disco.getSerial(), grupoDeDiscos.getTamanhoTotal().toString(), fkNotebook);


            if (!discoExiste(novoDiscoRigido)) {
                adicionarDisco(novoDiscoRigido);
            }
        }
    }
}
