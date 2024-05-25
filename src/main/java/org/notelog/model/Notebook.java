package org.notelog.model;

import com.github.britooo.looca.api.core.Looca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Notebook {

    private Integer id;
    private String sistemaOperacional;
    private String fabricante;
    private Integer arquitetura;
    private String numeroSerial;
    private Integer fkFuncionario;
    private Integer fkEmpresa;

    public Notebook(Integer fkFuncionario, Integer fkEmpresa) {
        Looca looca = new Looca();
        this.sistemaOperacional = looca.getSistema().getSistemaOperacional();
        this.fabricante = looca.getSistema().getFabricante();
        this.arquitetura = looca.getSistema().getArquitetura();
        this.numeroSerial = pegarNumeroSerial();
        this.fkFuncionario = fkFuncionario;
        this.fkEmpresa = fkEmpresa;
    }

    public Notebook(){
    }

    public static String pegarNumeroSerial() {
        String osName = System.getProperty("os.name").toLowerCase();
        StringBuilder numeroSerial = new StringBuilder();

        try {
            if (osName.contains("windows")) {
                // Comando para Windows
                String command = "wmic bios get serialnumber";
                Process process = Runtime.getRuntime().exec(command);
                process.waitFor();

                // Ler a saída do comando
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    numeroSerial.append(line.trim()); // Adiciona a linha ao número de série
                }
                reader.close();
            } else if (osName.contains("linux")) {
                // Comando para Linux
                String command = "sudo dmidecode -s system-serial-number";
                Process process = Runtime.getRuntime().exec(new String[]{"bash", "-c", command});
                process.waitFor();

                // Ler a saída do comando
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    numeroSerial.append(line.trim()); // Adiciona a linha ao número de série
                }
                reader.close();
            } else {
                System.out.println("Sistema operacional não suportado.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return numeroSerial.toString();
    }


    public String getNumeroSerial() {
        return numeroSerial;
    }

    public void setNumeroSerial(String numeroSerial) {
        this.numeroSerial = numeroSerial;
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
