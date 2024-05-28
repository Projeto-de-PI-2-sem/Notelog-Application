package org.notelog.dao;

import org.notelog.model.Empresa;
import org.notelog.model.Notebook;
import org.notelog.model.Ram;
import org.notelog.util.database.Conexao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class EmpresaDAO {

    public Empresa consultaEmpresaCPU(Integer fkCPU){
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();

        String sql = """
                Select * from Empresa
                join Notebook on Empresa.id = Notebook.fkEmpresa
                Join `Cpu` on `Cpu`.fkNotebook = Notebook.id
                Join LogCpu on LogCpu.fkCpu = `Cpu`.id WHERE LogCpu.fkCpu = %d LIMIT 1;
                """.formatted(fkCPU);

        // Utilizando queryForObject para retornar um único objeto Empresa
        return con.queryForObject(sql, new BeanPropertyRowMapper<>(Empresa.class));

    };

    public Empresa consultaEmpresaRAM(Integer fkRAM){
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();

        String sql = """
                Select * from Empresa
                join Notebook on Empresa.id = Notebook.fkEmpresa
                Join Ram on Ram .fkNotebook = Notebook.id
                Join LogRam on LogRam.fkRam = Ram.id WHERE LogRam.fkRam = 1 LIMIT 1;
                """.formatted(fkRAM);

        // Utilizando queryForObject para retornar um único objeto Empresa
        return con.queryForObject(sql, new BeanPropertyRowMapper<>(Empresa.class));

    };

    public Empresa consultaEmpresaDisco(Integer fkDisco){
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();

        String sql = """
                Select * from Empresa
                join Notebook on Empresa.id = Notebook.fkEmpresa
                Join `Cpu` on `Cpu`.fkNotebook = Notebook.id
                Join LogCpu on LogCpu.fkCpu = `Cpu`.id WHERE LogCpu.fkCpu = %d LIMIT 1;
                """.formatted(fkDisco);

        // Utilizando queryForObject para retornar um único objeto Empresa
        return con.queryForObject(sql, new BeanPropertyRowMapper<>(Empresa.class));

    };


}
