package org.notelog.model;

public class LogJanelas extends LogAbstract {
    private String idJanela;
    private String nomeJanela;
    private Boolean bloqueado = false;
    private Integer fkNotebook;

    public LogJanelas(Integer id, String idJanela, String nomeJanela, Integer fkNotebook) {
        super.setId(id);
        this.idJanela = idJanela;
        this.nomeJanela = nomeJanela;
        this.bloqueado = bloqueado;
        this.fkNotebook = fkNotebook;
    }

    public LogJanelas() {
    }

    public Boolean getBloqueado() {
        return bloqueado;
    }

    public String getIdJanela() {
        return idJanela;
    }

    public String getNomeJanela() {
        return nomeJanela;
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
