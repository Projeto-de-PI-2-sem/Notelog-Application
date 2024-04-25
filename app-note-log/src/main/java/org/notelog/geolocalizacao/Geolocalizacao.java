package org.notelog.geolocalizacao;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Geolocalizacao {
    private Integer idGeolocalizacao;
    @JsonAlias({"ip"})
    private String enderecoIp;

    @JsonAlias({"country_name"})
    private String pais;

    @JsonAlias({"city_name"})
    private String cidade;

    @JsonAlias({"region_name"})
    private String nomeRegiao;


    @JsonAlias({"latitude"})
    private double latitude;

    @JsonAlias({"longitude"})
    private double longitude;

    @JsonAlias({"time_zone"})
    private String timeZone;

    @JsonAlias({"asn"})
    private String asn;

    @JsonAlias({"as"})
    private String as;

    @JsonAlias({"is_proxy"})
    private boolean isProxy;

    public Integer getIdGeolocalizacao() {
        return idGeolocalizacao;
    }

    public void setIdGeolocalizacao(Integer idGeolocalizacao) {
        this.idGeolocalizacao = idGeolocalizacao;
    }

    public String getEnderecoIp() {
        return enderecoIp;
    }

    public void setEnderecoIp(String enderecoIp) {
        this.enderecoIp = enderecoIp;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getNomeRegiao() {
        return nomeRegiao;
    }

    public void setNomeRegiao(String nomeRegiao) {
        this.nomeRegiao = nomeRegiao;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getAsn() {
        return asn;
    }

    public void setAsn(String asn) {
        this.asn = asn;
    }

    public String getAs() {
        return as;
    }

    public void setAs(String as) {
        this.as = as;
    }

    public boolean isProxy() {
        return isProxy;
    }

    public void setProxy(boolean proxy) {
        isProxy = proxy;
    }

    @Override
    public String toString() {
        return "Geolocalizacao{" +
                "idGeolocalizacao=" + idGeolocalizacao +
                ", enderecoIp='" + enderecoIp + '\'' +
                ", pais='" + pais + '\'' +
                ", cidade='" + cidade + '\'' +
                ", nomeRegiao='" + nomeRegiao + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", timeZone='" + timeZone + '\'' +
                ", asn='" + asn + '\'' +
                ", as='" + as + '\'' +
                ", isProxy=" + isProxy +
                '}';
    }
    public void preencherDados(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Geolocalizacao geoData = objectMapper.readValue(jsonString, Geolocalizacao.class);

            this.enderecoIp = geoData.getEnderecoIp();
            this.pais = geoData.getPais();
            this.nomeRegiao = geoData.getNomeRegiao();
            this.cidade = geoData.getCidade();
            this.latitude = geoData.getLatitude();
            this.longitude = geoData.getLongitude();
            this.timeZone = geoData.getTimeZone();
            this.asn = geoData.getAsn();
            this.as = geoData.getAs();
            this.isProxy = geoData.isProxy();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
