import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Looca looca = new Looca();

        System.out.println("Informações do sistema: ");
        System.out.println(looca.getSistema().getArquitetura());


        //Criação do gerenciador
        DiscoGrupo grupoDeDiscos = looca.getGrupoDeDiscos();

        //Obtendo lista de discos a partir do getter
        List<Disco> discos = grupoDeDiscos.getDiscos();

        System.out.println("Exibindo as informações de cada disco: ");
        for (Disco disco : discos) {
            System.out.println(disco);
        }
    }
}
