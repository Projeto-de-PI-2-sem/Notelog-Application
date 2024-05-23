package org.notelog.entidades.logs.janelas;

public class LogJanelas {
    private Integer id;
    private String idJanela;
    private Integer fkNotebook;

    public LogJanelas(Integer id, String idJanela, Integer fkNotebook) {
        this.id = id;
        this.idJanela = idJanela;
        this.fkNotebook = fkNotebook;
    }

    public LogJanelas() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdJanela() {
        return idJanela;
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
                ", idJanela='" + idJanela + '\'' +
                ", fkNotebook=" + fkNotebook +
                '}';
    }
}
