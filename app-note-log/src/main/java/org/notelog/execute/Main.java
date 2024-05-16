package org.notelog.execute;

import com.github.britooo.looca.api.core.Looca;
import org.notelog.entidades.cpu.Cpu;
import org.notelog.entidades.cpu.CpuDAO;
import org.notelog.entidades.disco.rigido.DiscoRigidoDAO;
import org.notelog.entidades.logs.cpu.LogCpu;
import org.notelog.entidades.logs.cpu.LogCpuDAO;
import org.notelog.entidades.logs.disco.LogDiscoDAO;
import org.notelog.entidades.logs.janelas.LogJanelasDAO;
import org.notelog.entidades.logs.ram.LogRam;
import org.notelog.entidades.logs.ram.LogRamDAO;
import org.notelog.entidades.notebook.Notebook;
import org.notelog.entidades.notebook.NotebookDAO;
import org.notelog.entidades.ram.Ram;
import org.notelog.entidades.ram.RamDAO;
import org.notelog.entidades.tempo.atividade.TempoDeAtividade;
import org.notelog.entidades.tempo.atividade.TempoDeAtividadeDAO;
import org.notelog.entidades.usuario.Funcionario;
import org.notelog.entidades.usuario.FuncionarioDAO;
import org.notelog.geolocalizacao.Geolocalizacao;
import org.notelog.geolocalizacao.GeolocalizacaoDAO;

import java.io.Console;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        // Título em ASCII
        System.out.println("""
                            _____                   _______           _____                    _____                    _____     _______                   _____         \s
                           /\\    \\                 /::\\    \\         /\\    \\                  /\\    \\                  /\\    \\   /::\\    \\                 /\\    \\        \s
                          /::\\____\\               /::::\\    \\       /::\\    \\                /::\\    \\                /::\\____\\ /::::\\    \\               /::\\    \\       \s
                         /::::|   |              /::::::\\    \\      \\:::\\    \\              /::::\\    \\              /:::/    //::::::\\    \\             /::::\\    \\      \s
                        /:::::|   |             /::::::::\\    \\      \\:::\\    \\            /::::::\\    \\            /:::/    //::::::::\\    \\           /::::::\\    \\     \s
                       /::::::|   |            /:::/~~\\:::\\    \\      \\:::\\    \\          /:::/\\:::\\    \\          /:::/    //:::/~~\\:::\\    \\         /:::/\\:::\\    \\    \s
                      /:::/|::|   |           /:::/    \\:::\\    \\      \\:::\\    \\        /:::/__\\:::\\    \\        /:::/    //:::/    \\:::\\    \\       /:::/  \\:::\\    \\   \s
                     /:::/ |::|   |          /:::/    / \\:::\\    \\     /::::\\    \\      /::::\\   \\:::\\    \\      /:::/    //:::/    / \\:::\\    \\     /:::/    \\:::\\    \\  \s
                    /:::/  |::|   | _____   /:::/____/   \\:::\\____\\   /::::::\\    \\    /::::::\\   \\:::\\    \\    /:::/    //:::/____/   \\:::\\____\\   /:::/    / \\:::\\    \\ \s
                   /:::/   |::|   |/\\    \\ |:::|    |     |:::|    | /:::/\\:::\\    \\  /:::/\\:::\\   \\:::\\    \\  /:::/    /|:::|    |     |:::|    | /:::/    /   \\:::\\ ___\\\s
                  /:: /    |::|   /::\\____\\|:::|____|     |:::|    |/:::/  \\:::\\____\\/:::/__\\:::\\   \\:::\\____\\/:::/____/ |:::|____|     |:::|    |/:::/____/  ___\\:::|    |
                  \\::/    /|::|  /:::/    / \\:::\\    \\   /:::/    //:::/    \\::/    /\\:::\\   \\:::\\   \\::/    /\\:::\\    \\  \\:::\\    \\   /:::/    / \\:::\\    \\ /\\  /:::|____|
                   \\/____/ |::| /:::/    /   \\:::\\    \\ /:::/    //:::/    / \\/____/  \\:::\\   \\:::\\   \\/____/  \\:::\\    \\  \\:::\\    \\ /:::/    /   \\:::\\    /::\\ \\::/    /\s
                           |::|/:::/    /     \\:::\\    /:::/    //:::/    /            \\:::\\   \\:::\\    \\       \\:::\\    \\  \\:::\\    /:::/    /     \\:::\\   \\:::\\ \\/____/ \s
                           |::::::/    /       \\:::\\__/:::/    //:::/    /              \\:::\\   \\:::\\____\\       \\:::\\    \\  \\:::\\__/:::/    /       \\:::\\   \\:::\\____\\   \s
                           |:::::/    /         \\::::::::/    / \\::/    /                \\:::\\   \\::/    /        \\:::\\    \\  \\::::::::/    /         \\:::\\  /:::/    /   \s
                           |::::/    /           \\::::::/    /   \\/____/                  \\:::\\   \\/____/          \\:::\\    \\  \\::::::/    /           \\:::\\/:::/    /    \s
                           /:::/    /             \\::::/    /                              \\:::\\    \\               \\:::\\    \\  \\::::/    /             \\::::::/    /     \s
                          /:::/    /               \\::/____/                                \\:::\\____\\               \\:::\\____\\  \\::/____/               \\::::/    /      \s
                          \\::/    /                 ~~                                       \\::/    /                \\::/    /   ~~                      \\::/____/       \s
                           \\/____/                                                            \\/____/                  \\/____/                                            \s
                                                                                                                                                                          \s
                """);
        System.out.println("Solução de monitoramento para notebooks - Notelog 0.18.2 (Versão Alpha)");

        Thread.sleep(3000);
        Scanner scanner = new Scanner(System.in);

        // Loop de login
        boolean loginValido = false;
        FuncionarioDAO userDAO = new FuncionarioDAO();
        Funcionario usuario = null;

        while (!loginValido) {
            Console console = System.console();
            String senha = "";
            System.out.println("Por favor, faça login para continuar...");
            System.out.print("Email: ");
            String email = scanner.nextLine();
            if (console == null){
                System.out.print("Senha: ");
                senha = scanner.nextLine();
            }else{
                char[] senhaArray = console.readPassword("Insira sua senha");

                senha = new String(senhaArray);

                java.util.Arrays.fill(senhaArray,' ');
            }


            // Verifica se o usuário existe no banco de dados
            usuario = userDAO.verificaUsuario(email, senha);
            if (usuario != null) {
                loginValido = true;
                System.out.println("Login bem-sucedido! Bem-vindo, " + usuario.getNome() + "!");
                Thread.sleep(3000);
                vincularFuncionario(usuario);
            } else {
                System.out.println("Email ou senha incorretos. Tente novamente.");
            }
        }
    }

    private static void vincularFuncionario(Funcionario usuario) {
        FuncionarioDAO userDAO = new FuncionarioDAO();
        NotebookDAO notebookDAO = new NotebookDAO();

        if (userDAO.temVinculo(usuario.getId())){
            Notebook notebookJaCadastrado = notebookDAO.consultaNotebook(usuario.getId());
            try {
                escolherMonitoramento(usuario, notebookJaCadastrado);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else{

            Scanner scanner = new Scanner(System.in);
            System.out.println("""
                =================================================================================================
                | Para seguir para o monitoramento, digite o ID do funcionário no qual a maquina será designada |
                =================================================================================================
                """);
            for (Funcionario funcionario : userDAO.buscarFuncionarios(usuario.getFkEmpresa())) {
                System.out.println(funcionario);
            }
            Notebook notebook = new Notebook(scanner.nextInt(), usuario.getFkEmpresa());

            if (notebookDAO.adicionarNotebook(notebook)){
                try {
                    escolherMonitoramento(usuario, notebook);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else System.out.println("ERRO!!! Funcionário inexistente vinculado a empresa");
        };
    }

    private static void inserirDadosNoBanco(Funcionario usuario, Notebook notebook) throws InterruptedException {
        System.out.println("Monitorando dispositivo, que tal um café enquanto isso?...");

        while (true) {
            try {
                Cpu cpu = new Cpu(notebook.getId());
                Ram ram = new Ram(notebook.getId());
//              DiscoRigido
                TempoDeAtividade tempoDeAtividade= new TempoDeAtividade(notebook.getId());


                //Geolocalização
                Geolocalizacao geolocalizacao = new Geolocalizacao();
                String publicIPAddress = geolocalizacao.ObterIP();
                String jsonString = geolocalizacao.ObterGeoPorIP(publicIPAddress);
                geolocalizacao.preencherDados(jsonString);
                String dadosFormatados = geolocalizacao.formatarDados();
                Thread.sleep(5000);

                //funcionalidade de Processos bloqueados
//                  Looca janelaGroup = new Looca();
//                  ucionalidadeConsole func = new FucionalidadeConsole();
//                  List<String> processosBloqueados = new ArrayList<>();

                //DAO - Instancias
                CpuDAO cpuDAO = new CpuDAO();
                RamDAO ramDAO = new RamDAO();
                DiscoRigidoDAO discoRigidoDAO = new DiscoRigidoDAO();
                TempoDeAtividadeDAO tempoDeAtividadeDAO = new TempoDeAtividadeDAO();
//                GeolocalizacaoDAO geoDAO = new GeolocalizacaoDAO();


                //DAO - Metodos
                discoRigidoDAO.adiconarNovoDisco(notebook.getId());
                tempoDeAtividadeDAO.adicionarTempoDeAtividade(tempoDeAtividade);


                //LogDAO - Instancias
                LogCpuDAO logCpuDAO = new LogCpuDAO();
                LogRamDAO logRamDAO = new LogRamDAO();
                LogDiscoDAO logDiscoDAO = new LogDiscoDAO();
                LogJanelasDAO logJanelasDAO = new LogJanelasDAO();


                //LogDAO - Metodos
                logCpuDAO.adicionarLogCpu(new LogCpu(cpuDAO.adicionarCpu(cpu)));
                logRamDAO.adicionarLogRam(new LogRam(ramDAO.adicionarRam(ram)));
                logDiscoDAO.adiconarNovoLogDisco();
                logJanelasDAO.adicionarNovoLogJanelas();
//                geoDAO.adicionaGeolocalizacao();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }



    private static void escolherMonitoramento(Funcionario usuario, Notebook notebook) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        int opcao;

        do {
            System.out.println("Selecione o tipo de monitoramento:");
            System.out.println("1 - Monitorar CPU");
            System.out.println("2 - Monitorar Disco");
            System.out.println("3 - Monitorar Janelas");
            System.out.println("4 - Monitorar RAM");
            System.out.println("5 - Capturar Geolocalização do dispositivo");
            System.out.println("6 - Ocultar ação do Notelog");
            System.out.println("7 - Sair do programa");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    monitorarCPU(usuario, notebook);
                    break;
                case 2:
                    monitorarDisco(usuario, notebook);
                    break;
                case 3:
                    monitorarJanelas(usuario, notebook);
                    break;
                case 4:
                    monitorarRAM(usuario, notebook);
                    break;
                case 5:
                    monitorarGeolocalizacao(usuario, notebook);
                    break;
                case 6:
                    System.out.println("Os inserts continuaram sendo executados, porém de forma invisivel");
                    inserirDadosNoBanco(usuario, notebook);
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 7);
        System.out.println("Programa Finalizado.");
    }

    // Funções de monitoramento
    private static void monitorarCPU(Funcionario usuario, Notebook notebook) {
        Scanner scanner = new Scanner(System.in);
        Looca looca = new Looca();
        do {
            System.out.println("Monitorando CPU...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Dados de CPU:");
            try {
                Thread.sleep(5000);
                System.out.println(looca.getProcessador().toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Inserindo processos no Banco...");
            try {
                Thread.sleep(9000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Concluido!!");
        } while (!scanner.nextLine().equalsIgnoreCase("exit"));
        System.out.println("Saindo do monitoramento de CPU...");

        try {
            escolherMonitoramento(usuario, notebook);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void monitorarDisco(Funcionario usuario, Notebook notebook) {
        Scanner scanner = new Scanner(System.in);
        Looca looca = new Looca();
        do {
            System.out.println("Monitorando Disco...");
            try {
                Thread.sleep(1000); // Espera 1 segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Dados de Discos:");
            try {
                Thread.sleep(5000);
                System.out.println(looca.getGrupoDeDiscos().getDiscos().toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Inserindo processos no Banco...");
            try {
                Thread.sleep(9000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Concluido!!");
        } while (!scanner.nextLine().equalsIgnoreCase("exit"));
        System.out.println("Saindo do monitoramento de Disco...");

        try {
            escolherMonitoramento(usuario, notebook);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private static void monitorarJanelas(Funcionario usuario, Notebook notebook) {
        Scanner scanner = new Scanner(System.in);
        Looca looca = new Looca();
        do {
            System.out.println("Monitorando Janelas...");
            try {
                Thread.sleep(1000); // Espera 1 segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Dados de Janelas:");
            try {
                Thread.sleep(5000); // Espera 5 segundos
                System.out.println(looca.getGrupoDeJanelas().getJanelas().toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Inserindo processos no Banco...");
            try {
                Thread.sleep(9000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Concluido!!");
        } while (!scanner.nextLine().equalsIgnoreCase("exit"));
        System.out.println("Saindo do monitoramento de Janelas...");

        try {
            escolherMonitoramento(usuario, notebook);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private static void monitorarRAM(Funcionario usuario, Notebook notebook) {
        Scanner scanner = new Scanner(System.in);
        Looca looca = new Looca();
        do {

            System.out.println("Monitorando RAM...");
            try {
                Thread.sleep(1000); // Espera 1 segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Dados de memória RAM:");
            try {
                Thread.sleep(5000); // Espera 5 segundos
                looca.getMemoria().toString();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Inserindo processos no Banco...");
            try {
                Thread.sleep(9000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Concluido!!");
        } while (!scanner.nextLine().equalsIgnoreCase("exit"));
        System.out.println("Saindo do monitoramento de RAM...");

        try {
            escolherMonitoramento(usuario, notebook);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void monitorarGeolocalizacao(Funcionario usuario, Notebook notebook) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                Geolocalizacao geolocalizacao = new Geolocalizacao();
                GeolocalizacaoDAO geoDAO = new GeolocalizacaoDAO();

                String publicIPAddress = geolocalizacao.ObterIP();
                String jsonString = geolocalizacao.ObterGeoPorIP(publicIPAddress);

                geolocalizacao.preencherDados(jsonString);
                String dadosFormatados = geolocalizacao.formatarDados();
                System.out.println("Geolocalização do dispositivo:");
                Thread.sleep(1000); // Espera 1 segundo
                System.out.println(dadosFormatados);
                Thread.sleep(5000); // Espera 5 segundos
                System.out.println("Dados capturados com sucesso.");
//                geoDAO.adicionaGeolocalizacao();
                return;

            } catch (InterruptedException e) {}
        }
    }

}