package org.notelog.entidades.ram;

import com.github.britooo.looca.api.core.Looca;

public class Ram {
    private String totalMemoria;
    private Integer fkNotebook;

    public Ram()
    {
        Looca looca = new Looca();
        this.totalMemoria = looca.getMemoria().getTotal().toString();
    }

    public String getTotalMemoria() {
        return totalMemoria;
    }

    public void setTotalMemoria(String totalMemoria) {
        this.totalMemoria = totalMemoria;
    }

    public Integer getFkNotebook() {
        return fkNotebook;
    }

    public void setFkNotebook(Integer fkNotebook) {
        this.fkNotebook = fkNotebook;
    }

    @Override
    public String toString() {
        return "Ram{" +
                "totalMemoria='" + totalMemoria + '\'' +
                '}';
    }
}
