package org.notelog.entidades.ram;

import com.github.britooo.looca.api.core.Looca;

public class Ram {
    Looca looca = new Looca();
    private Integer id;
    private String totalMemoria;

    public Ram() {
        this.totalMemoria = totalMemoria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTotalMemoria() {
        return totalMemoria;
    }

    public void setTotalMemoria(String totalMemoria) {
        this.totalMemoria = totalMemoria;
    }

    @Override
    public String toString() {
        return "org.notelog.entidades.ram.RAM{" +
                "idRAM=" + id +
                ", totalMemoria='" + totalMemoria + '\'' +
                '}';
    }
}
