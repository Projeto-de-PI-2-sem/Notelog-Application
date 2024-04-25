package org.notelog.entidades.logs.janelas;

import com.github.britooo.looca.api.core.Looca;

public class LogJanelas {
    Looca looca = new Looca();
    private Integer id;
    private String enderecoJanela;
    private Integer fkNotebook;

    public LogJanelas() {
        this.id = id;
        this.enderecoJanela = enderecoJanela;
        this.fkNotebook = fkNotebook;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEnderecoJanela() {
        return enderecoJanela;
    }

    public void setEnderecoJanela(String enderecoJanela) {
        this.enderecoJanela = enderecoJanela;
    }

    public Integer getFkNotebook() {
        return fkNotebook;
    }

    public void setFkNotebook(Integer fkNotebook) {
        this.fkNotebook = fkNotebook;
    }

    @Override
    public String toString() {
        return "org.notelog.entidades.logs.janelas.LogJanelas{" +
                "idLogJanelas=" + id +
                ", enderecoJanela='" + enderecoJanela + '\'' +
                ", fkNotebook=" + fkNotebook +
                '}';
    }
}
