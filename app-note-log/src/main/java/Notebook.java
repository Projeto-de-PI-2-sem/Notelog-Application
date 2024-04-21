import com.github.britooo.looca.api.core.Looca;

public class Notebook {
    Looca looca = new Looca();

    private Integer idNotebook;
    private String sistemaOperacional;
    private String fabricante;
    private Integer arquitetura;
    private Integer fkFuncionario;

    public Notebook() {
        this.sistemaOperacional = looca.getSistema().getSistemaOperacional();
        this.fabricante = looca.getSistema().getFabricante();
        this.arquitetura = looca.getSistema().getArquitetura();
    }

    public Integer getIdNotebook() {
        return idNotebook;
    }

    public void setIdNotebook(Integer idNotebook) {
        this.idNotebook = idNotebook;
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

    @Override
    public String toString() {
        return """
                Notebook %d
                Sistema operacional: %s
                Fabricante: %s
                Arquiteta: %s
                fkFuncionario: %d
                """.formatted(idNotebook, sistemaOperacional, fabricante, arquitetura, fkFuncionario);
    }
}
