import com.github.britooo.looca.api.core.Looca;
import org.springframework.jdbc.core.JdbcTemplate;

public class TesteNotebook {
    public static void main(String[] args) {
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();
        Looca looca = new Looca();


        con.update("INSERT INTO Notebook (sistemaOperacional, fabricante, arquitetura) VALUES ('" + looca.getSistema().getSistemaOperacional() +
                "', '" + looca.getSistema().getFabricante() + "', " + looca.getSistema().getArquitetura() + ")");
    }
}
