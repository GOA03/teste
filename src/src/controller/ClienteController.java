package controller;

import model.ClienteModel;
import view.InterfaceClienteView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ClienteController {
    private InterfaceClienteView view;
    private ClienteModel model;

    public ClienteController(InterfaceClienteView view, ClienteModel model) {
        this.view = view;
        this.model = model;
        
        this.view.adicionarActionListenerConectar(new ConectarServidorListener());
        this.view.adicionarActionListenerEnviar(new EnviarMensagemListener());
    }

    class ConectarServidorListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String ip = view.getIpServidor().getText();
            int porta = Integer.parseInt(view.getPortaServidor().getText());

            // Iniciar conexão em uma nova thread
            new Thread(() -> {
                try {
                    model.conectar(ip, porta);  // Método sincronizado
                    view.getRetornoCliente().append("Conectado ao servidor!\n");
                } catch (IOException ex) {
                    view.getRetornoCliente().append("Erro ao conectar ao servidor: " + ex.getMessage() + "\n");
                }
            }).start();
        }
    }

    class EnviarMensagemListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String mensagem = view.getEntradaMsgCliente().getText();
            
            // Enviar mensagem em uma nova thread
            new Thread(() -> {
                try {
                    model.enviarMensagem(mensagem);  // Método sincronizado
                    view.getRetornoCliente().append("Você: " + mensagem + "\n");
                    
                    // Receber a resposta do servidor
                    String resposta = model.receberResposta();  // Método sincronizado
                    view.getRetornoCliente().append("Servidor: " + resposta + "\n");

                    // Limpa o campo de entrada
                    view.getEntradaMsgCliente().setText("");

                    if (mensagem.equalsIgnoreCase("Bye")) {
                        model.fecharConexao();  // Método sincronizado
                        view.getRetornoCliente().append("Conexão encerrada.\n");
                    }
                } catch (IOException ex) {
                    view.getRetornoCliente().append("Erro ao enviar mensagem: " + ex.getMessage() + "\n");
                }
            }).start();
        }
    }
}
