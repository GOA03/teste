package model;

import java.io.*;
import java.net.*;

public class ServidorModel {
    private ServerSocket serverSocket;

    public void iniciarServidor(int porta) throws IOException {
        serverSocket = new ServerSocket(porta, 50, InetAddress.getByName("0.0.0.0"));
    }

    public Socket esperarConexao() throws IOException {
        return serverSocket.accept();
    }

    public void fecharServidor() throws IOException {
        if (serverSocket != null) {
            serverSocket.close();
        }
    }

    public void lidarComCliente(Socket socketCliente, ServidorListener listener) {
        new Thread(() -> {
            try (
                PrintWriter saida = new PrintWriter(socketCliente.getOutputStream(), true);
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()))
            ) {
                listener.onConexao(socketCliente.getInetAddress().toString());
                
                String mensagemRecebida;
                while ((mensagemRecebida = entrada.readLine()) != null) {
                    listener.onMensagemRecebida(mensagemRecebida);
                    if (mensagemRecebida.equalsIgnoreCase("bye")) {
                        saida.println("bye");
                        listener.onDesconexao();
                        break;
                    }
                    // Resposta em maiúsculas
                    saida.println(mensagemRecebida.toUpperCase());
                }
            } catch (IOException e) {
                listener.onErro("Erro na comunicação com o cliente: " + e.getMessage());
            } finally {
                try {
                    socketCliente.close();
                } catch (IOException e) {
                    listener.onErro("Erro ao fechar a conexão com o cliente: " + e.getMessage());
                }
            }
        }).start();
    }

    public interface ServidorListener {
        void onConexao(String clienteInfo);
        void onMensagemRecebida(String mensagem);
        void onDesconexao();
        void onErro(String erro);
    }
}
