package org.notelog.entidades.logs.disco;

import com.github.britooo.looca.api.core.Looca;

public class LogDisco {
    Looca looca = new Looca();
    private Integer id;
    private Integer fkNotebook;
    private Integer fkDisco;
    private String leituras;
    private String bytesLeitura;
    private String escritas;
    private String bytesEscritas;

    public LogDisco() {
        this.fkNotebook = fkNotebook;
        this.fkDisco = fkDisco;
        this.leituras = leituras;
        this.bytesLeitura = bytesLeitura;
        this.escritas = escritas;
        this.bytesEscritas = bytesEscritas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFkNotebook() {
        return fkNotebook;
    }

    public void setFkNotebook(Integer fkNotebook) {
        this.fkNotebook = fkNotebook;
    }

    public Integer getFkDisco() {
        return fkDisco;
    }

    public void setFkDisco(Integer fkDisco) {
        this.fkDisco = fkDisco;
    }

    public String getLeituras() {
        return leituras;
    }

    public void setLeituras(String leituras) {
        this.leituras = leituras;
    }

    public String getBytesLeitura() {
        return bytesLeitura;
    }

    public void setBytesLeitura(String bytesLeitura) {
        this.bytesLeitura = bytesLeitura;
    }

    public String getEscritas() {
        return escritas;
    }

    public void setEscritas(String escritas) {
        this.escritas = escritas;
    }

    public String getBytesEscritas() {
        return bytesEscritas;
    }

    public void setBytesEscritas(String bytesEscritas) {
        this.bytesEscritas = bytesEscritas;
    }

    @Override
    public String toString() {
        return "org.notelog.entidades.logs.disco.LogDisco{" +
                "idLogDisco=" + id +
                ", fkNotebook=" + fkNotebook +
                ", fkDisco=" + fkDisco +
                ", leituras='" + leituras + '\'' +
                ", bytesLeitura='" + bytesLeitura + '\'' +
                ", escritas='" + escritas + '\'' +
                ", bytesEscritas='" + bytesEscritas + '\'' +
                '}';
    }
}
