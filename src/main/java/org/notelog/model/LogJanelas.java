package org.notelog.model;

public class LogJanelas extends LogAbstract {
    private String idJanela;
    private Integer fkNotebook;

    public LogJanelas(Integer id, String idJanela, Integer fkNotebook) {
        super.setId(id);
        this.idJanela = idJanela;
        this.fkNotebook = fkNotebook;
    }

    public LogJanelas() {
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
                "idLogJanelas=" + super.getId() +
                ", idJanela='" + idJanela + '\'' +
                ", fkNotebook=" + fkNotebook +
                '}';
    }
}
