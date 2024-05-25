package org.notelog.service;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;
import org.notelog.dao.EmpresaDAO;
import org.notelog.model.Empresa;

import java.util.Scanner;

public class SlackService {
    private static String webHookUrl;
    private static String oAuthToken;
    private static String slackChannel;

    public static void setWebHookUrl(String webHookUrl) {
        SlackService.webHookUrl = webHookUrl;
    }

    public static void setoAuthToken(String oAuthToken) {
        SlackService.oAuthToken = oAuthToken;
    }

    public static void setSlackChannel(String slackChannel) {
        SlackService.slackChannel = slackChannel;
    }

    public static void main(String[] args) {

        System.out.println("Digite a mensagem que deseja enviar para o slack: ");
        Scanner sc = new Scanner(System.in);
        String mensagem = sc.nextLine();
        Integer id = 1;
        sendMensagemSlack(mensagem, 2);
    }

    public static void sendMensagemSlack(String mensagem, Integer fkEmpresa) {
        EmpresaDAO empresaDAO = new EmpresaDAO();
        Empresa empresa = empresaDAO.consultaEmpresa(fkEmpresa);

        setWebHookUrl(empresa.getWebHookUrl());
        setoAuthToken(empresa.getoAuthToken());
        setSlackChannel(empresa.getSlackChannel());
        try {
            StringBuilder msgbuilder = new StringBuilder();
            msgbuilder.append(mensagem);

            Payload payload = Payload.builder().channel(slackChannel).text(msgbuilder.toString()).build();

            WebhookResponse wbResp = Slack.getInstance().send(webHookUrl, payload);
        } catch (Exception e){
            System.out.println("Erro ao enviar mensagem para o slack: " + e.getMessage());
        }
    }
}