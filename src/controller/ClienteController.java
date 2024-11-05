package controller;

import model.ClienteModel;
import view.ClienteView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ClienteController {
    private ClienteView view;
    private ClienteModel model;

    public ClienteController(ClienteView view, ClienteModel model) {
        this.view = view;
        this.model = model;

        // Adiciona ActionListeners para conectar ao servidor
        this.view.getBtnConectar().addActionListener(new ConectarServidorListener());
    }

    // Listener para conectar ao servidor
    class ConectarServidorListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String ip = view.getEnderecoServidor().getText();
            int porta = Integer.parseInt(view.getPortaField().getText());

            // Iniciar a conexão em uma nova thread
            new Thread(() -> {
                try {
                    model.conectar(ip, porta);  // Método sincronizado no modelo
                    view.getRetornoCliente().append("Conectado ao servidor!\n");
                } catch (IOException ex) {
                    view.getRetornoCliente().append("Erro ao conectar ao servidor: " + ex.getMessage() + "\n");
                }
            }).start();
        }
    }
}
