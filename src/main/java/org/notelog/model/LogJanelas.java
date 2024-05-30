package org.notelog.model;

import java.io.IOException;

public class LogJanelas extends LogAbstract {
    private String idJanela;
    private String nomeJanela;
    private Integer bloqueado;
    private Integer fkNotebook;

    // Construtor que inicializa todos os campos
    public LogJanelas(Integer id, String idJanela, String nomeJanela, Integer bloqueado, Integer fkNotebook) {
        super.setId(id);
        this.idJanela = idJanela;
        this.nomeJanela = nomeJanela;
        this.bloqueado = bloqueado;
        this.fkNotebook = fkNotebook;
    }

    // Construtor padrão
    public LogJanelas() {
    }

    // Método para encerrar o processo com base no PID
    public void encerraProcesso(Integer pid) {
        try {
            String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                // Encerra o processo no Windows usando o comando taskkill
                Runtime.getRuntime().exec("taskkill /F /PID " + pid).waitFor();
            } else {
                // Encerra o processo no Linux usando o comando kill
                Runtime.getRuntime().exec("sudo kill -9 " + pid).waitFor();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Getters e Setters
    public Integer getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(Integer bloqueado) {
        this.bloqueado = bloqueado;
    }

    public String getIdJanela() {
        return idJanela;
    }

    public void setIdJanela(String idJanela) {
        this.idJanela = idJanela;
    }

    public String getNomeJanela() {
        return nomeJanela;
    }

    public void setNomeJanela(String nomeJanela) {
        this.nomeJanela = nomeJanela;
    }

    public Integer getFkNotebook() {
        return fkNotebook;
    }

    public void setFkNotebook(Integer fkNotebook) {
        this.fkNotebook = fkNotebook;
    }

    @Override
    public String toString() {
        return "LogJanelas{" +
                "idLogJanelas=" + super.getId() +
                ", idJanela='" + idJanela + '\'' +
                ", nomeJanela='" + nomeJanela + '\'' +
                ", bloqueado=" + bloqueado +
                ", fkNotebook=" + fkNotebook +
                '}';
    }
}