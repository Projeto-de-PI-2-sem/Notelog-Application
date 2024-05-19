package org.notelog.model;

import com.github.britooo.looca.api.core.Looca;

public class Notebook {

    private Integer id;
    private String sistemaOperacional;
    private String fabricante;
    private Integer arquitetura;
    private Integer fkFuncionario;
    private Integer fkEmpresa;

    public Notebook(Integer fkFuncionario, Integer fkEmpresa) {
        Looca looca = new Looca();
        this.sistemaOperacional = looca.getSistema().getSistemaOperacional();
        this.fabricante = looca.getSistema().getFabricante();
        this.arquitetura = looca.getSistema().getArquitetura();
        this.fkFuncionario = fkFuncionario;
        this.fkEmpresa = fkEmpresa;
    }

    public Notebook(){
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public void setSistemaOperacional(String sistemaOperacional) {
        this.sistemaOperacional = sistemaOperacional;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public Integer getArquitetura() {
        return arquitetura;
    }

    public void setArquitetura(Integer arquitetura) {
        this.arquitetura = arquitetura;
    }

    public Integer getFkFuncionario() {
        return fkFuncionario;
    }

    public void setFkFuncionario(Integer fkFuncionario) {
        this.fkFuncionario = fkFuncionario;
    }

    public Integer getFkEmpresa() {
        return fkEmpresa;
    }

    public void setFkEmpresa(Integer fkEmpresa) {
        this.fkEmpresa = fkEmpresa;
    }

    @Override
    public String toString() {
        return """
                Notebook %d
                Sistema operacional: %s
                Fabricante: %s
                Arquiteta: %s
                fkFuncionario: %d
                fkEmpresa: %d
                """.formatted(id, sistemaOperacional, fabricante, arquitetura, fkFuncionario, fkEmpresa);
    }
}
