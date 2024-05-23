package org.notelog.entidades.logs.cpu;

import com.github.britooo.looca.api.core.Looca;

public class LogCpu {
    private Integer id;
    private Double porcentagemUso;
    private Integer fkCPU;

    public LogCpu(Integer fkCPU)
    {
        Looca looca = new Looca();
        this.porcentagemUso = looca.getProcessador().getUso();
        this.fkCPU = fkCPU;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPorcentagemUso() {
        return porcentagemUso;
    }

    public void setPorcentagemUso(Double porcentagemUso) {
        this.porcentagemUso = porcentagemUso;
    }

    public Integer getFkCPU() {
        return fkCPU;
    }

    public void setFkCPU(Integer fkCPU) {
        this.fkCPU = fkCPU;
    }

    @Override
    public String toString() {
        return "org.notelog.entidades.log.cpu.LogCPU{" +
                "idLogCPU=" + id +
                ", porcentagemUso='" + porcentagemUso + '\'' +
                ", fkCPU=" + fkCPU +
                '}';
    }
}
