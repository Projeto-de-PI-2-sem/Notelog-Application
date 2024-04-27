package org.notelog.execute;

import org.notelog.entidades.cpu.Cpu;
import org.notelog.entidades.cpu.CpuDAO;
import org.notelog.entidades.disco.rigido.DiscoRigidoDAO;
import org.notelog.entidades.logs.cpu.LogCpu;
import org.notelog.entidades.logs.cpu.LogCpuDAO;
import org.notelog.entidades.logs.disco.LogDiscoDAO;
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

public class Main {
    public static void main(String[] args) {
        //Instâncias
        //BD
        CpuDAO cpuDAO = new CpuDAO();
        DiscoRigidoDAO discoRigidoDAO = new DiscoRigidoDAO();
        LogCpuDAO logCpuDAO = new LogCpuDAO();
        LogDiscoDAO logDiscoDAO = new LogDiscoDAO();
        //LogDisco
        LogJanelasDAO logJanelasDAO = new LogJanelasDAO();
        LogRamDAO logRamDAO = new LogRamDAO();

        NotebookDAO notebookDAO = new NotebookDAO();
        RamDAO ramDAO = new RamDAO();
        TempoDeAtividadeDAO tempoDeAtividadeDAO = new TempoDeAtividadeDAO();

        // Hardware
        cpuDAO.adicionarCpu(new Cpu());
        discoRigidoDAO.adiconarNovoDisco();
        notebookDAO.adicionarNotebook(new Notebook());
        ramDAO.adicionarRam(new Ram());

        // Logs
        logCpuDAO.adicionarLogCpu(new LogCpu());
        logJanelasDAO.adicionarNovoLogJanelas();
        logRamDAO.adicionarLogRam(new LogRam());
        logDiscoDAO.adiconarNovoLogDisco();

        tempoDeAtividadeDAO.adicionarTempoDeAtividade(new TempoDeAtividade());

    }
}
