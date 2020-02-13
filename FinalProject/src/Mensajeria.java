import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Mensajeria extends JFrame {
	JPanel host;
	JPanel join;
	JPanel usuario;
	JTextField ip, username;
	JButton hostear, unirse;

	public static void main(String[] args) throws IOException {
		new Mensajeria();
		/*
		 * String usuarioname =
		 * JOptionPane.showInputDialog("Ingrese su nombre de usuario:"); String opcion =
		 * JOptionPane.showInputDialog("Desea hostear o unirse a un chat?");
		 * 
		 * if(opcion.equals("hostear") || opcion.equals("Hostear")) { new
		 * Servidor(usuarioname); }else if(opcion.equals("unirse") ||
		 * opcion.equals("Unirse")) { String hostip =
		 * JOptionPane.showInputDialog("Ingrese su nombre de usuario:"); new
		 * Cliente(hostip,usuarioname); }
		 */
	}

	public Mensajeria() {
		super("Mensajeria");

		JLabel userlabel = new JLabel("Nombre de Usuario");
		userlabel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
		userlabel.setHorizontalTextPosition(0);
		username = new JTextField();
		username.setSize(200, 50);
		host = new JPanel();
		join = new JPanel();
		join.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		host.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		usuario = new JPanel();
		usuario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		hostear = new JButton("Hostear Sesion");
		JLabel iplabel = new JLabel("IP del host");
		iplabel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
		ip = new JTextField();

		unirse = new JButton("Unirse a Sesion");
		hostear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (username.getText().length() > 0) {

					dispose();
					// System.out.println(username.getText());
					new Servidor(username.getText());
				}
			}
		});

		unirse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (ip.getText().length() > 0 && username.getText().length() > 0) {
					dispose();
					try {
						new Cliente(ip.getText(), username.getText());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		host.add(hostear);
		join.setLayout(new GridLayout(1, 3));
		join.add(iplabel);
		join.add(ip);
		join.add(unirse);
		usuario.setLayout(new GridLayout(1, 2));
		usuario.add(userlabel);
		usuario.add(username);
		JPanel content = new JPanel();
		content.setLayout(new GridLayout(2, 1));
		content.add(host);
		content.add(join);
		this.add(usuario, BorderLayout.NORTH);
		this.add(content, BorderLayout.CENTER);
		this.setSize(450, 200);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
