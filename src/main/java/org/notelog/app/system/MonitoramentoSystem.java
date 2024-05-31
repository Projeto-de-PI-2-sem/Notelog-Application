package org.notelog.app.system;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.janelas.Janela;
import org.notelog.SimpleLogger;
import org.notelog.dao.*;
import org.notelog.model.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.notelog.model.LogJanelas.verificaProcessoEmExecucao;
import static org.notelog.service.SlackService.sendMensagemSlackCPU;
import static org.notelog.service.SlackService.sendMensagemSlackRAM;


public class MonitoramentoSystem {

    public static void escolherMonitoramento(Funcionario usuario, Notebook notebook) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        int opcao;

        do {
            System.out.println("Selecione uma 'feature' abaixo:");
            System.out.println("1 - Exibir informações, CPU");
            System.out.println("2 - Exibir informações, Disco(s)");
            System.out.println("3 - Exibir informações, Janela(s)");
            System.out.println("4 - Exibir informações, memória RAM");
            System.out.println("5 - Exibir informações, Geolocalização do dispositivo");
            System.out.println("6 - Ocultar ação do Notelog...");
            System.out.println("7 - Sair do programa.");

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
                    System.out.println("Iniciando execução em segundo plano...");
                    inserirDadosNoBanco(usuario, notebook, false);
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 7);

        System.out.println("Programa encerrado.");
    }

    // Funções de monitoramento
    public static void monitorarCPU(Funcionario usuario, Notebook notebook) {
        Scanner scanner = new Scanner(System.in);
        Looca looca = new Looca();
        do {
            System.out.println("Buscando informações sobre CPU...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Dados encontrados:");
            try {
                System.out.println(looca.getProcessador().toString());
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Enviando dados para o servidor...");
            try {
                inserirDadosNoBanco(usuario, notebook, true);
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Concluído. Digite '/exit' para sair.");
        } while (!scanner.nextLine().equalsIgnoreCase("/exit"));
        System.out.println("Saindo...");

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
            System.out.println("Buscando informações sobre Disco(s)...");
            try {
                Thread.sleep(1000); // Espera 1 segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Dados encontrados:");
            try {
                Thread.sleep(5000);
                System.out.println(looca.getGrupoDeDiscos().getDiscos().toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Enviando dados para o servidor...");
            try {
                inserirDadosNoBanco(usuario, notebook, true);
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Concluído. Digite '/exit' para sair.");
        } while (!scanner.nextLine().equalsIgnoreCase("/exit"));
        System.out.println("Saindo...");

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
            System.out.println("Buscando informações sobre Janela(s)...");
            try {
                Thread.sleep(1000); // Espera 1 segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Dados encontrados:");
            try {
                Thread.sleep(5000); // Espera 5 segundos
                System.out.println(looca.getGrupoDeJanelas().getJanelas().toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Enviando dados para o servidor...");
            try {
                inserirDadosNoBanco(usuario, notebook, true);
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Concluído. Digite '/exit' para sair.");
        } while (!scanner.nextLine().equalsIgnoreCase("/exit"));
        System.out.println("Saindo...");

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

            System.out.println("Buscando informações sobre memória RAM...");
            try {
                Thread.sleep(1000); // Espera 1 segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Dados encontrados:");
            try {
                Thread.sleep(5000); // Espera 5 segundos
                System.out.println(looca.getMemoria().toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Enviando dados para o servidor...");
            try {
                inserirDadosNoBanco(usuario, notebook, true);
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Concluído. Digite '/exit' para sair.");
        } while (!scanner.nextLine().equalsIgnoreCase("/exit"));
        System.out.println("Saindo...");

        try {
            escolherMonitoramento(usuario, notebook);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void monitorarGeolocalizacao(Funcionario usuario, Notebook notebook) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        do {

            System.out.println("Buscando informações sobre geolocalização do dispositivo...");
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
            System.out.println("Dados encontrados:");
            Thread.sleep(1000); // Espera 1 segundo
            System.out.println(dadosFormatados);
            Thread.sleep(5000); // Espera 5 segundos

            System.out.println("Enviando dados para o servidor...");
            try {
                inserirDadosNoBanco(usuario, notebook, true);
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Concluído. Digite '/exit' para sair.");
        } while (!scanner.nextLine().equalsIgnoreCase("/exit"));
        System.out.println("Saindo...");

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
            Geolocalizacao geolocalizacao = new Geolocalizacao();

            //Metodos - Objetos DAO
            discoRigidoDAO.adiconarNovoDisco(notebook.getId());
            geoDAO.adicionaGeolocalizacao(notebook.getId(),geolocalizacao);
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
                    System.out.println("\nMonitoramento em andamento...");
                    Thread.sleep(3500);

                    // Criando Objeto CPU pelo id do notebook
                    Cpu cpu = new Cpu(notebook.getId());
                    System.out.println("Dados - CPU: \n" + cpu);
                    System.out.println(cpu.getIdFisicoProcessador());
                    Thread.sleep(4000);

                    Thread.sleep(2000);

                    // Criando Objeto RAM pelo id do notebook
                    Ram ram = new Ram(notebook.getId());
                    System.out.println("Dados - Memória Ram: \n" + ram);

                    Thread.sleep(2000);

                    // Criando Objeto tempo de atividade pelo id do notebook
                    TempoDeAtividade tempoDeAtividade = new TempoDeAtividade(notebook.getId());
                    System.out.println("Dados - Tempo de atividade: \n" + tempoDeAtividade);

                    Thread.sleep(2000);

                    //Geolocalização
                    if (contador % 5 == 0 && contador > 0 ){
                        System.out.println("Dados - Geolocalização do dispositivo: \n");
                        Geolocalizacao geolocalizacao = new Geolocalizacao();
                        String publicIPAddress = geolocalizacao.ObterIP();
                        String jsonString = geolocalizacao.ObterGeoPorIP(publicIPAddress);
                        geolocalizacao.preencherDados(jsonString);
                        String dadosFormatados = geolocalizacao.formatarDados();
                        System.out.println(dadosFormatados);
                        GeolocalizacaoDAO geoDAO = new GeolocalizacaoDAO();
                        geoDAO.adicionaGeolocalizacao(notebook.getId(), geolocalizacao);
                        Thread.sleep(2000);
                    }

                    //Objetos DAO de cada componente
                    CpuDAO cpuDAO = new CpuDAO();
                    RamDAO ramDAO = new RamDAO();
                    DiscoRigidoDAO discoRigidoDAO = new DiscoRigidoDAO();
                    TempoDeAtividadeDAO tempoDeAtividadeDAO = new TempoDeAtividadeDAO();

                    //Metodos - Objetos DAO
                    discoRigidoDAO.adiconarNovoDisco(notebook.getId());
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

                    System.out.println("Verificando Janelas indevidas... ");
                    Thread.sleep(2000);
                    List<LogJanelas> listaJanelas = logJanelasDAO.selecionarJanelas(notebook.getId());

                    for (LogJanelas process : listaJanelas) {
                        if (verificaProcessoEmExecucao(Integer.parseInt(process.getIdJanela()))) {
                            if (process.getBloqueado() > 0) {
                                try {
                                    int idJanela = Integer.parseInt(process.getIdJanela());
                                    process.encerraProcesso(idJanela);
                                    System.out.println("Janela " + process.getNomeJanela() + " foi encerrada por ação de um técnico especializado, por violar as políticas da empresa.");
                                    Thread.sleep(1500); // Pausa de 1,5 segundos
                                } catch (NumberFormatException e) {
                                    System.out.println("ID da janela inválido: " + process.getIdJanela());
                                } catch (InterruptedException e) {
                                    Thread.currentThread().interrupt();
                                    System.out.println("Thread interrompida.");
                                }
                            }
                        }

                    }

                    Thread.sleep(3500);

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
                            System.out.println("Alerta - Alto Uso de CPU detectado!");
                            Thread.sleep(1000);
                            System.out.println("Notificando técnicos especializados...");
                            Thread.sleep(8000);
                        }
                    }

                    ramEmUso = Long.parseLong(logram.getUsoMemoria());
                    ramDisponivel = Long.parseLong(logram.getMemoriaDisponivel());

                    porcentagemUsoRAM.add(((double) ramEmUso / (ramEmUso + ramDisponivel) * 100));

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
                            System.out.println("Alerta - Alto Uso de RAM detectado!");
                            Thread.sleep(1000);
                            System.out.println("Notificando técnicos especializados...");
                            Thread.sleep(8000);
                        }
                    }

                    contador++;

                    System.out.println("Enviando dados para o servidor...");
                    Thread.sleep(2000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

}


