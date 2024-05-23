package org.notelog.app.system;

import com.github.britooo.looca.api.core.Looca;
import org.notelog.dao.*;
import org.notelog.model.*;

import java.util.Scanner;


public class MonitoramentoSystem {

    public static void escolherMonitoramento(Funcionario usuario, Notebook notebook) throws InterruptedException {
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
                    System.out.println("Todos os inserts serão executados em segundo plano.");
                    inserirDadosNoBanco(usuario, notebook, false);
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
    public static void monitorarCPU(Funcionario usuario, Notebook notebook) {
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
                System.out.println(looca.getProcessador().toString());
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Inserindo processos no Banco...");
            try {
                inserirDadosNoBanco(usuario, notebook, true);
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Concluido. Digite '/exit' para sair.");
        } while (!scanner.nextLine().equalsIgnoreCase("/exit"));
        System.out.println("Saindo do monitoramento de CPU...");

        try {
            escolherMonitoramento(usuario, notebook);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void monitorarDisco(Funcionario usuario, Notebook notebook) {
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
                inserirDadosNoBanco(usuario, notebook, true);
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Concluido. Digite '/exit' para sair.");
        } while (!scanner.nextLine().equalsIgnoreCase("/exit"));
        System.out.println("Saindo do monitoramento de Disco...");

        try {
            escolherMonitoramento(usuario, notebook);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void monitorarJanelas(Funcionario usuario, Notebook notebook) {
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
                inserirDadosNoBanco(usuario, notebook, true);
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Concluido. Digite '/exit' para sair.");
        } while (!scanner.nextLine().equalsIgnoreCase("/exit"));
        System.out.println("Saindo do monitoramento de Janelas...");

        try {
            escolherMonitoramento(usuario, notebook);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void monitorarRAM(Funcionario usuario, Notebook notebook) {
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
                inserirDadosNoBanco(usuario, notebook, true);
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Concluido. Digite '/exit' para sair.");
        } while (!scanner.nextLine().equalsIgnoreCase("/exit"));
        System.out.println("Saindo do monitoramento de RAM...");

        try {
            escolherMonitoramento(usuario, notebook);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void monitorarGeolocalizacao(Funcionario usuario, Notebook notebook) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        do {

            System.out.println("Monitorando Geolocalização...");
            try {
                Thread.sleep(2000); // Espera 2 segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

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

            System.out.println("Inserindo processos no Banco...");
            try {
                inserirDadosNoBanco(usuario, notebook, true);
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Concluido. Digite '/exit' para sair.");
        } while (!scanner.nextLine().equalsIgnoreCase("/exit"));
        System.out.println("Saindo do monitoramento de RAM...");

        try {
            escolherMonitoramento(usuario, notebook);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void inserirDadosNoBanco(Funcionario usuario, Notebook notebook, Boolean acaoParcial) throws InterruptedException {

        if (acaoParcial) {
            // Criando Objeto CPU pelo id do notebook
            Cpu cpu = new Cpu(notebook.getId());

            // Criando Objeto RAM pelo id do notebook
            Ram ram = new Ram(notebook.getId());

            // Criando Objeto tempo de atividade pelo id do notebook
            TempoDeAtividade tempoDeAtividade = new TempoDeAtividade(notebook.getId());

//            // Criando Objeto Geolocalização pelo IP
//            Geolocalizacao geolocalizacao = new Geolocalizacao();
//            String publicIPAddress = geolocalizacao.ObterIP();
//            String jsonString = geolocalizacao.ObterGeoPorIP(publicIPAddress);
//            geolocalizacao.preencherDados(jsonString);

            //Objetos DAO de cada componente
            CpuDAO cpuDAO = new CpuDAO();
            RamDAO ramDAO = new RamDAO();
            DiscoRigidoDAO discoRigidoDAO = new DiscoRigidoDAO();
            TempoDeAtividadeDAO tempoDeAtividadeDAO = new TempoDeAtividadeDAO();
            GeolocalizacaoDAO geoDAO = new GeolocalizacaoDAO();

            //Metodos - Objetos DAO
            discoRigidoDAO.adiconarNovoDisco(notebook.getId());
            geoDAO.adicionaGeolocalizacao(notebook.getId());
            tempoDeAtividadeDAO.adicionarTempoDeAtividade(tempoDeAtividade);


            //LogDAO - Instancias
            LogCpuDAO logCpuDAO = new LogCpuDAO();
            LogRamDAO logRamDAO = new LogRamDAO();
            LogDiscoDAO logDiscoDAO = new LogDiscoDAO();
            LogJanelasDAO logJanelasDAO = new LogJanelasDAO();


            //LogDAO - Metodos
            logCpuDAO.adicionarLogCpu(new LogCpu(cpuDAO.adicionarCpu(cpu)));
            logRamDAO.adicionarLogRam(new LogRam(ramDAO.adicionarRam(ram)));
            logDiscoDAO.adicionarNovoLogDisco();
            logJanelasDAO.adicionarNovoLogJanelas();


        } else {
            while (true) {
                try {
                    System.out.print("Monitoramento em andamento");
                    for (int i = 0; i < 10; i++) {
                        Thread.sleep(500); // Espera meio segundo
                        System.out.print(".");
                        System.out.print("\b"); // Apaga o último ponto
                    }
                    System.out.println("\nMonitoramento em andamento...");

                    // Criando Objeto CPU pelo id do notebook
                    Cpu cpu = new Cpu(notebook.getId());
                    System.out.println("Dados CPU: \n" + cpu);

                    Thread.sleep(2000);

                    // Criando Objeto RAM pelo id do notebook
                    Ram ram = new Ram(notebook.getId());
                    System.out.println("Dados Memória Ram: \n" + ram);

                    Thread.sleep(2000);

                    // Criando Objeto tempo de atividade pelo id do notebook
                    TempoDeAtividade tempoDeAtividade = new TempoDeAtividade(notebook.getId());
                    System.out.println("Dados Tempo de atividade: \n" + tempoDeAtividade);

                    Thread.sleep(2000);

                    //Geolocalização
                    System.out.println("Dados Geolocalização: \n");
                    Geolocalizacao geolocalizacao = new Geolocalizacao();
                    String publicIPAddress = geolocalizacao.ObterIP();
                    String jsonString = geolocalizacao.ObterGeoPorIP(publicIPAddress);
                    geolocalizacao.preencherDados(jsonString);
                    String dadosFormatados = geolocalizacao.formatarDados();
                    System.out.println(dadosFormatados);

                    Thread.sleep(2000);

                    //Objetos DAO de cada componente
                    CpuDAO cpuDAO = new CpuDAO();
                    RamDAO ramDAO = new RamDAO();
                    DiscoRigidoDAO discoRigidoDAO = new DiscoRigidoDAO();
                    TempoDeAtividadeDAO tempoDeAtividadeDAO = new TempoDeAtividadeDAO();
                    GeolocalizacaoDAO geoDAO = new GeolocalizacaoDAO();

                    //Metodos - Objetos DAO
                    discoRigidoDAO.adiconarNovoDisco(notebook.getId());
                    geoDAO.adicionaGeolocalizacao(notebook.getId());
                    tempoDeAtividadeDAO.adicionarTempoDeAtividade(tempoDeAtividade);


                    //LogDAO - Instancias
                    LogCpuDAO logCpuDAO = new LogCpuDAO();
                    LogRamDAO logRamDAO = new LogRamDAO();
                    LogDiscoDAO logDiscoDAO = new LogDiscoDAO();
                    LogJanelasDAO logJanelasDAO = new LogJanelasDAO();


                    //LogDAO - Metodos
                    logCpuDAO.adicionarLogCpu(new LogCpu(cpuDAO.adicionarCpu(cpu)));
                    logRamDAO.adicionarLogRam(new LogRam(ramDAO.adicionarRam(ram)));
                    logDiscoDAO.adicionarLogDisco();
                    logJanelasDAO.adicionarNovoLogJanelas();

                    System.out.println("Inserindo dados no Banco...");
                    Thread.sleep(2000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

}


