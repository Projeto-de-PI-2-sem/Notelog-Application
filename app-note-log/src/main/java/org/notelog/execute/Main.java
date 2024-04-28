package org.notelog.execute;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.janelas.Janela;
import org.notelog.entidades.cpu.Cpu;
import org.notelog.entidades.cpu.CpuDAO;
import org.notelog.entidades.disco.rigido.DiscoRigidoDAO;
import org.notelog.entidades.logs.cpu.LogCpu;
import org.notelog.entidades.logs.cpu.LogCpuDAO;
import org.notelog.entidades.logs.disco.LogDiscoDAO;
import org.notelog.entidades.logs.janelas.FucionalidadeConsole;
import org.notelog.entidades.logs.janelas.LogJanelas;
import org.notelog.entidades.logs.janelas.LogJanelasDAO;
import org.notelog.entidades.logs.ram.LogRam;
import org.notelog.entidades.logs.ram.LogRamDAO;
import org.notelog.entidades.notebook.Notebook;
import org.notelog.entidades.notebook.NotebookDAO;
import org.notelog.entidades.ram.Ram;
import org.notelog.entidades.ram.RamDAO;
import org.notelog.entidades.tempo.atividade.TempoDeAtividade;
import org.notelog.entidades.tempo.atividade.TempoDeAtividadeDAO;
import org.notelog.entidades.usuario.Usuario;
import org.notelog.entidades.usuario.UsuarioDAO;
import org.notelog.geolocalizacao.Geolocalizacao;
import org.notelog.geolocalizacao.GeolocalizacaoBD;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        // Título em ASCII
        System.out.println(" _   _           _              _");
        System.out.println("| \\ | |         | |            | |");
        System.out.println("|  \\| |   ___   | |_    ___    | |    ___     __ _  ");
        System.out.println("| . ` |  / _ \\  | __|  / _ \\   | |   / _ \\  /  _  |");
        System.out.println("| |\\  | | (_) | | |_  |  ___/  | |  | (_) | | (_| |");
        System.out.println("|_| \\_|  \\___/  \\__|   \\___|   |_|   \\___/   \\__, |");
        System.out.println("                                            ____/ |");
        System.out.println("                                            |___ /");
        System.out.println();
        System.out.println("Solução de monitoramento para notebooks - Notelog 0.18.2 (Versão Alpha)");

        Thread.sleep(3000);
        Scanner scanner = new Scanner(System.in);

        // Loop de login
        boolean loginValido = false;
        UsuarioDAO userDAO = new UsuarioDAO();
        Usuario usuario = null;

        while (!loginValido) {
            System.out.println("Por favor, faça login para continuar...");
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Senha: ");
            String senha = scanner.nextLine();

            // Verifica se o usuário existe no banco de dados
            usuario = userDAO.verificaUsuario(email, senha);
            if (usuario != null) {
                loginValido = true;
                System.out.println("Login bem-sucedido! Bem-vindo, " + usuario.getNome() + "!");
                Thread.sleep(3000);
                escolherMonitoramento(usuario);
            } else {
                System.out.println("Email ou senha incorretos. Tente novamente.");
            }
        }



//        //Instâncias BD
//        CpuDAO cpuDAO = new CpuDAO();
//        DiscoRigidoDAO discoRigidoDAO = new DiscoRigidoDAO();
//        LogCpuDAO logCpuDAO = new LogCpuDAO();
//        LogDiscoDAO logDiscoDAO = new LogDiscoDAO();
//
//        //LogDisco
//        LogJanelasDAO logJanelasDAO = new LogJanelasDAO();
//        LogRamDAO logRamDAO = new LogRamDAO();
//
//        //LogDisco
//        NotebookDAO notebookDAO = new NotebookDAO();
//        RamDAO ramDAO = new RamDAO();
//        TempoDeAtividadeDAO tempoDeAtividadeDAO = new TempoDeAtividadeDAO();
//
//        // Hardware
//        notebookDAO.adicionarNotebook(new Notebook());
//        cpuDAO.adicionarCpu(new Cpu());
//        discoRigidoDAO.adiconarNovoDisco();
//        ramDAO.adicionarRam(new Ram());
//
//        // Logs
//        logCpuDAO.adicionarLogCpu(new LogCpu());
//        logJanelasDAO.adicionarNovoLogJanelas();
//        logRamDAO.adicionarLogRam(new LogRam());
//        logDiscoDAO.adiconarNovoLogDisco();
//        tempoDeAtividadeDAO.adicionarTempoDeAtividade(new TempoDeAtividade());
//
//        Looca janelaGroup = new Looca();
//        FucionalidadeConsole func = new FucionalidadeConsole();
//        List<String> processosBloqueados = new ArrayList<>();
//
//
//        Geolocalizacao geolocalizacao = new Geolocalizacao();
//        GeolocalizacaoBD geoBD = new GeolocalizacaoBD();
//
//        String publicIPAddress = geolocalizacao.ObterIP();
//        String jsonString = geolocalizacao.ObterGeoPorIP(publicIPAddress);
//
//        geolocalizacao.preencherDados(jsonString);
//        String dadosFormatados = geolocalizacao.formatarDados();
//        geoBD.adicionaGeolocalizacao();



        //ELIZEU COLOCA ESSAS insTÃNCIAS NAS SUAS RESPECTIVAS FUNÇÕES DE MONITORAMENTO.
        // eSSAS AÍ DE CIMA, AS COMENTADAS


    }

    private static void escolherMonitoramento(Usuario usuario) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        int opcao;

        do {
            System.out.println("Selecione o tipo de monitoramento:");
            System.out.println("1 - Monitorar CPU");
            System.out.println("2 - Monitorar Disco");
            System.out.println("3 - Monitorar Janelas");
            System.out.println("4 - Monitorar RAM");
            System.out.println("5 - Capturar Geolocalização do dispositivo");
            System.out.println("6 - Sair");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    monitorarCPU(usuario);
                    break;
                case 2:
                    monitorarDisco(usuario);
                    break;
                case 3:
                    monitorarJanelas(usuario);
                    break;
                case 4:
                    monitorarRAM(usuario);
                    break;
                case 5:
                    monitorarGeolocalizacao(usuario);
                    break;
                case 6:
                    System.out.println("Encerrando o programa...");
                    Thread.sleep(5000);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 5);




    }

    // Funções de monitoramento
    private static void monitorarCPU(Usuario usuario) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Monitorando CPU...");
                Thread.sleep(1000); // Espera 1 segundo
                System.out.println("Dados da CPU:");
                Thread.sleep(5000); // Espera 5 segundos
                System.out.println("Inserindo processos no Banco...");

                //ELIZEU tenta arrumar essa interrupçâo aqui
                // a idei É PARAR ESSA FUNÇÃO QUANDO O USUÁRIO APERTAR ALGUMA TECLA, PIQUE CTRL + c.

            } catch (InterruptedException e) {
                // A exceção InterruptedException é lançada quando o usuário pressiona Ctrl + C
                System.out.println("Monitoramento interrompido pelo usuário.");
                System.out.println("Deseja parar o monitoramento? (S/N)");
                String resposta = scanner.nextLine();
                if (resposta.equalsIgnoreCase("S")) {
                    return; // Retorna para a função escolherMonitoramento
                }
            }
        }
    }



    private static void monitorarDisco(Usuario usuario) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Monitorando CPU...");
                Thread.sleep(1000); // Espera 1 segundo
                System.out.println("Dados da CPU:");
                Thread.sleep(5000); // Espera 5 segundos
                System.out.println("Inserindo processos no Banco...");

            } catch (InterruptedException e) {
                System.out.println("Monitoramento interrompido pelo usuário.");
                System.out.println("Deseja parar o monitoramento? (S/N)");
                String resposta = scanner.nextLine();
                if (resposta.equalsIgnoreCase("S")) {
                    return; // Retorna para a função escolherMonitoramento
                }
            }
        }
    }


    private static void monitorarJanelas(Usuario usuario) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Monitorando CPU...");
                Thread.sleep(1000); // Espera 1 segundo
                System.out.println("Dados da CPU:");
                Thread.sleep(5000); // Espera 5 segundos
                System.out.println("Inserindo processos no Banco...");

            } catch (InterruptedException e) {
                System.out.println("Monitoramento interrompido pelo usuário.");
                System.out.println("Deseja parar o monitoramento? (S/N)");
                String resposta = scanner.nextLine();
                if (resposta.equalsIgnoreCase("S")) {
                    return; // Retorna para a função escolherMonitoramento
                }
            }
        }
    }


    private static void monitorarRAM(Usuario usuario) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Monitorando CPU...");
                Thread.sleep(1000); // Espera 1 segundo
                System.out.println("Dados da CPU:");
                Thread.sleep(5000); // Espera 5 segundos
                System.out.println("Inserindo processos no Banco...");

            } catch (InterruptedException e) {
                System.out.println("Monitoramento interrompido pelo usuário.");
                System.out.println("Deseja parar o monitoramento? (S/N)");
                String resposta = scanner.nextLine();
                if (resposta.equalsIgnoreCase("S")) {
                    return; // Retorna para a função escolherMonitoramento
                }
            }
        }
    }

    private static void monitorarGeolocalizacao(Usuario usuario) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                Geolocalizacao geolocalizacao = new Geolocalizacao();
                GeolocalizacaoBD geoBD = new GeolocalizacaoBD();

                String publicIPAddress = geolocalizacao.ObterIP();
                String jsonString = geolocalizacao.ObterGeoPorIP(publicIPAddress);

                geolocalizacao.preencherDados(jsonString);
                String dadosFormatados = geolocalizacao.formatarDados();
                System.out.println("Geolocalização do dispositivo:");
                Thread.sleep(1000); // Espera 1 segundo
                System.out.println(dadosFormatados);
                Thread.sleep(5000); // Espera 5 segundos
                System.out.println("Dados capturados e enviados ao Banco...");
                geoBD.adicionaGeolocalizacao();
                return;

            } catch (InterruptedException e) {}
        }
    }


}
