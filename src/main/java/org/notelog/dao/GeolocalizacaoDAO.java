package org.notelog.dao;

import org.notelog.model.Geolocalizacao;
import org.notelog.util.database.ConexaoMySQL;
import org.springframework.jdbc.core.JdbcTemplate;

public class GeolocalizacaoDAO {
    public void adicionaGeolocalizacao(Integer fkNotebook, Geolocalizacao geolocalizacao) {
        ConexaoMySQL conexaoMySQL = new ConexaoMySQL();
        JdbcTemplate con = conexaoMySQL.getConexaoDoBanco();

        String publicIPAddress = geolocalizacao.ObterIP();
        String jsonString = geolocalizacao.ObterGeoPorIP(publicIPAddress);
        geolocalizacao.preencherDados(jsonString);

        String sql = "INSERT INTO Geolocalizacao (enderecoIp, pais, cidade, nomeRegiao, latitude, longitude, timezone, companiaInternet, fkNotebook) VALUES ('%s','%s', '%s', '%s', '%s','%s','%s','%s', '%d');"
                .formatted(geolocalizacao.getIp(),geolocalizacao.getCountryName(), geolocalizacao.getCityName(),
                        geolocalizacao.getRegionName(), geolocalizacao.getLatitude(), geolocalizacao.getLongitude(),
                        geolocalizacao.getTimeZone(), geolocalizacao.getAs(), fkNotebook);
        con.update(sql);

    }
}

