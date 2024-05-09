package org.notelog.entidades.tempo.atividade;

import com.github.britooo.looca.api.core.Looca;

import java.sql.Date;
import java.sql.Time;
import java.time.DateTimeException;

public class TempoDeAtividade {
    private Integer id;
    private Integer fkNotebook;
    private String tempoDeAtividade;
    private String tempoInicializado;

    public TempoDeAtividade() {
        Looca looca = new Looca();
        this.tempoDeAtividade = looca.getSistema().getTempoDeAtividade().toString();
        this.tempoInicializado = looca.getSistema().getInicializado().toString();
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

    public String getTempoDeAtividade() {
        return tempoDeAtividade;
    }

    public void setTempoDeAtividade(String tempoDeAtividade) {
        this.tempoDeAtividade = tempoDeAtividade;
    }

    public String getTempoInicializado() {
        return tempoInicializado;
    }

    public void setTempoInicializado(String tempoInicializado) {
        this.tempoInicializado = tempoInicializado;
    }

    @Override
    public String toString() {
        return "org.notelog.entidades.tempo.atividade.TempoDeAtividade{" +
                "idTempoDeAtividade=" + id +
                ", fkNotebook=" + fkNotebook +
                ", tempoDeAtividade=" + tempoDeAtividade +
                ", tempoInicializado=" + tempoInicializado +
                '}';
    }
}
