package org.notelog.service;

import org.notelog.dao.FuncionarioDAO;
import org.notelog.dao.NotebookDAO;
import org.notelog.model.Funcionario;
import org.notelog.model.Notebook;

import java.util.Scanner;

import static org.notelog.app.system.MonitoramentoSystem.escolherMonitoramento;

public class LoginService {
    public static void vincularFuncionario(Funcionario usuario) {
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

            ASCIIService ascii = new ASCIIService();
            ascii.VinculoFuncionarioASCII();

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
}
