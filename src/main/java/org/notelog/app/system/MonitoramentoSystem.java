package org.notelog.app.system;

import com.github.britooo.looca.api.core.Looca;
import org.notelog.SimpleLogger;
import org.notelog.dao.*;
import org.notelog.model.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.notelog.service.SlackService.sendMensagemSlackCPU;
import static org.notelog.service.SlackService.sendMensagemSlackRAM;


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
        // Variáveis CPU
        Integer contador = 0;
        List<Double> porcentagemUsoCPU = new ArrayList<>();
        Double mediaPorcentagemUsoCPU = 0.0;

        // Variáveis RAM
        Long ramEmUso = 0L; // em bytes
        Long ramDisponivel = 0L; // em bytes
        List<Double> porcentagemUsoRAM = new ArrayList<>();
        Double mediaPorcentagemUsoRAM = 0.0;

        if (acaoParcial) {

            // Criando Objeto CPU pelo id do notebook
            Cpu cpu = new Cpu(notebook.getId());

            // Criando Objeto RAM pelo id do notebook
            Ram ram = new Ram(notebook.getId());

            // Criando Objeto tempo de atividade pelo id do notebook
            TempoDeAtividade tempoDeAtividade = new TempoDeAtividade(notebook.getId());

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

            LogCpu logcpu = new LogCpu(cpuDAO.adicionarCpu(cpu));

            //LogDAO - Metodos
            logCpuDAO.adicionarLogCpu(logcpu);
            logRamDAO.adicionarLogRam(new LogRam(ramDAO.adicionarRam(ram)));
            logDiscoDAO.adicionarNovoLogDisco(notebook.getId());
            logJanelasDAO.adicionarNovoLogJanelas(notebook.getId());

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




                    // Logs - Instâncias
                    LogCpu logcpu = new LogCpu(cpuDAO.adicionarCpu(cpu));
                    LogRam logram = new LogRam(ramDAO.adicionarRam(ram));

                    //LogDAO - Metodos
                    logCpuDAO.adicionarLogCpu(logcpu);
                    logRamDAO.adicionarLogRam(logram);
                    logDiscoDAO.adicionarNovoLogDisco(notebook.getId());
                    logJanelasDAO.adicionarNovoLogJanelas(notebook.getId());

                    porcentagemUsoCPU.add(logcpu.getPorcentagemUso());
                    if (contador % 2 == 0 && contador > 0){
                        mediaPorcentagemUsoCPU = (porcentagemUsoCPU.get(porcentagemUsoCPU.size() - 1) + porcentagemUsoCPU.get(porcentagemUsoCPU.size() - 2)) / 2;
                        if (mediaPorcentagemUsoCPU > 5.5){

                            String usuarioNome = usuario.getNome();
                            String notebookModelo = notebook.getSistemaOperacional();
                            String notebookNumeroSerial = notebook.getNumeroSerial();
                            String dataHoraAtual = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                            // Mensagem detalhada
                            String mensagemDetalhada = String.format(
                                    "\n" +
                                            "🚨 *ALERTA DE ALTO USO DE CPU*\n\n" +
                                            "🔹Detalhes do Alerta:\n" +
                                            "  • Data e Hora: %s\n" +
                                            "  • Porcentagem de Uso da CPU: %.2f%%\n" +
                                            "___________________________________________________\n" +
                                            "🔹Informações do Usuário:\n" +
                                            "  • Nome: %s\n" +
                                            "___________________________________________________\n" +
                                            "🔹Informações do Notebook:\n" +
                                            "  • Sistema Operacional: %s\n" +
                                            "  • Número de Série: %s\n\n" +
                                            "*Atenção:* O uso da CPU está acima do limite recomendado. Por favor, verifique e tome as ações necessárias.\n" +
                                            "*Acesse o link:* http://54.158.158.60:80 - para obter detalhes da dashboard Notelog",
                                    dataHoraAtual, porcentagemUsoCPU.get(porcentagemUsoCPU.size() - 1), usuarioNome, notebookModelo, notebookNumeroSerial
                            );
                            sendMensagemSlackCPU(mensagemDetalhada, logcpu.getFkCPU(), usuario, notebook);
                            System.out.println("Alto Uso de CPU detectado!");
                            Thread.sleep(1000);
                            System.out.println("Notificando monitores...");
                            Thread.sleep(8000);
                        }
                    }

                    ramEmUso = Long.parseLong(logram.getUsoMemoria());
                    ramDisponivel = Long.parseLong(logram.getMemoriaDisponivel());

                    porcentagemUsoRAM.add(((double) ramEmUso / ramDisponivel) / 100);

                    if (contador % 2 == 0 && contador > 0){
                        mediaPorcentagemUsoRAM = (porcentagemUsoRAM.get(porcentagemUsoRAM.size() - 1) + porcentagemUsoRAM.get(porcentagemUsoRAM.size() - 2)) / 2;
                        if (mediaPorcentagemUsoCPU > 10.5){
                            String usuarioNome = usuario.getNome();
                            String notebookModelo = notebook.getSistemaOperacional();
                            String notebookNumeroSerial = notebook.getNumeroSerial();
                            String dataHoraAtual = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                            // Mensagem detalhada
                            String mensagemDetalhada = String.format(
                                    "\n" +
                                            "🚨 *ALERTA DE ALTO USO DE RAM*\n\n" +
                                            "🔹 Detalhes do Alerta:\n" +
                                            "  • Data e Hora: %s\n" +
                                            "  • Porcentagem de Uso da RAM: %.2f%%\n" +
                                            "___________________________________________________\n" +
                                            "🔹 Informações do Usuário:\n" +
                                            "  • Nome: %s\n" +
                                            "___________________________________________________\n" +
                                            "🔹 Informações do Notebook:\n" +
                                            "  • Sistema Operacional: %s\n" +
                                            "  • Número de Série: %s\n\n" +
                                            "*Atenção:* O uso da RAM está acima do limite recomendado. Por favor, verifique e tome as ações necessárias.\n" +
                                            "*Acesse o link:* http://54.158.158.60:80 - para obter detalhes da dashboard Notelog",
                                    dataHoraAtual, porcentagemUsoRAM.get(porcentagemUsoRAM.size() - 1), usuarioNome, notebookModelo, notebookNumeroSerial
                            );
                            sendMensagemSlackRAM(mensagemDetalhada, logram.getFkRAM(), usuario, notebook);
                            System.out.println("Alto Uso de RAM detectado!");
                            Thread.sleep(1000);
                            System.out.println("Notificando monitores...");
                            Thread.sleep(8000);
                        }
                    }

                    contador++;

                    System.out.println("Inserindo dados no Banco...");
                    Thread.sleep(2000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

}


