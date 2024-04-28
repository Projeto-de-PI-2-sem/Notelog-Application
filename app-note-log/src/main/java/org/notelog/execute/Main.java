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
import org.notelog.geolocalizacao.Geolocalizacao;
import org.notelog.geolocalizacao.GeolocalizacaoBD;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
//        Instâncias BD

        System.out.println("Realizando Monitoramento de Processos do dispositivo...");
        Thread.sleep(5000);
//
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
        Looca janelaGroup = new Looca();
        FucionalidadeConsole func = new FucionalidadeConsole();
        List<String> processosBloqueados = new ArrayList<>();



        System.out.println("Encerrando processos indevídos...");

        Thread.sleep(5000);

        processosBloqueados.add("WhatsApp");
        for (Janela janela : janelaGroup.getGrupoDeJanelas().getJanelas()) {
            for (int i = 0; i < processosBloqueados.size(); i++) {
                if (janela.getTitulo().contains(processosBloqueados.get(i))) {
                    func.encerraProcesso(Math.toIntExact(janela.getPid()));
                    System.out.println("Processo '" + janela.getTitulo() + "' foi encerrado por violar as políticas de segurança da empresa!");
                    Thread.sleep(5000);

                }
            }
        }

        Geolocalizacao geolocalizacao = new Geolocalizacao();
        GeolocalizacaoBD geoBD = new GeolocalizacaoBD();

        String publicIPAddress = geolocalizacao.ObterIP();
        String jsonString = geolocalizacao.ObterGeoPorIP(publicIPAddress);

        geolocalizacao.preencherDados(jsonString);
        String dadosFormatados = geolocalizacao.formatarDados();
        System.out.println("Geolocalização do dispositivo:");
        System.out.println(dadosFormatados);
        System.out.println("Dados capturados e enviados ao Banco...");
        geoBD.adicionaGeolocalizacao();

    }
}