package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import controller.ClienteController;
import model.ClienteModel;

public class InterfaceClienteView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField ipServidor, portaServidor, entradaMsgCliente;
	private JTextArea retornoCliente;
	private JButton conectarServidor, enviarMensagem;

	public InterfaceClienteView() {
		
		setTitle("Cliente");
		setSize(500, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());

		JPanel painelSuperior = new JPanel();
		painelSuperior.setLayout(new FlowLayout());

		JLabel labelIp = new JLabel("IP do Servidor:");
		ipServidor = new JTextField(10);
		JLabel labelPorta = new JLabel("Porta do Servidor:");
		portaServidor = new JTextField(5);
		conectarServidor = new JButton("Conectar");

		painelSuperior.add(labelIp);
		painelSuperior.add(ipServidor);
		painelSuperior.add(labelPorta);
		painelSuperior.add(portaServidor);
		painelSuperior.add(conectarServidor);

		JPanel painelCentral = new JPanel();
		painelCentral.setLayout(new BorderLayout());

		retornoCliente = new JTextArea();
		retornoCliente.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(retornoCliente);

		painelCentral.add(scrollPane, BorderLayout.CENTER);

		JPanel painelInferior = new JPanel();
		painelInferior.setLayout(new FlowLayout());

		entradaMsgCliente = new JTextField(38);
		enviarMensagem = new JButton("Enviar");

		painelInferior.add(entradaMsgCliente);
		painelInferior.add(enviarMensagem);

		getContentPane().add(painelSuperior, BorderLayout.NORTH);
		getContentPane().add(painelCentral, BorderLayout.CENTER);
		getContentPane().add(painelInferior, BorderLayout.SOUTH);
	}

	public JTextField getIpServidor() {
		return ipServidor;
	}

	public JTextField getPortaServidor() {
		return portaServidor;
	}

	public JTextField getEntradaMsgCliente() {
		return entradaMsgCliente;
	}

	public JTextArea getRetornoCliente() {
		return retornoCliente;
	}

	public void adicionarActionListenerConectar(ActionListener listener) {
		conectarServidor.addActionListener(listener);
	}

	public void adicionarActionListenerEnviar(ActionListener listener) {
		enviarMensagem.addActionListener(listener);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceClienteView view = new InterfaceClienteView();
					ClienteModel model = new ClienteModel();
					new ClienteController(view, model);
					view.setLocationRelativeTo(null); // Centraliza a janela
					view.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
