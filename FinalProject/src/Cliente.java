import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Hashtable;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.*;

public class Cliente extends JFrame {
	private JTextField text;
	private JTextArea chat;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private String msj;
	private String serverIP;
	private Socket sckt;
	private JButton b1, b2, b3;
	private String archivo = "src/txtproyecto.txt";
	BigInteger p, q, n, pvk, pbk, e, d;
	BigInteger oe, on;
	String palabra = "";
	String palabraAnterior = "";
	PalabrasRelacionadas tablaPali = new PalabrasRelacionadas();

	Hashtable<String, PalabrasRelacionadas> tabla = new Hashtable<>(1000);
	
	public Cliente(String host, String username) throws IOException {
		super("Cliente");
		
		
		b1 = new JButton("");
		b2 = new JButton("");
		b3 = new JButton("");
		
		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int i = text.getText().length()-1;
				int start = 0;
				for(int p = i ; p>=0; p--) {
					if(text.getText().charAt(p) == ' ') {
						start = p+1;
						break;
					}
				}
				
				String tmp = "";
				for(int j = 0; j<start;j++) {
					tmp += text.getText().charAt(j);
				}
				
				tmp += b1.getText() + " ";
				text.setText(tmp);

				if (!tabla.containsKey(b1.getText())) {
					b1.setText("");
					b2.setText("");
					b3.setText("");
				} else {
					try {
					b1.setText(tabla.get(b1.getText()).getP1());
					b2.setText(tabla.get(b1.getText()).getP2());
					b3.setText(tabla.get(b1.getText()).getP3());
					}catch (Exception ex) {
						//System.err.print("No se encontraron palabras relacionadas");
						b1.setText("");
						b2.setText("");
						b3.setText("");
					}
				}

			}
		});

		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int i = text.getText().length()-1;
				int start = 0;
				for(int p = i ; p>=0; p--) {
					if(text.getText().charAt(p) == ' ') {
						start = p+1;
						break;
					}
				}
				
				String tmp = "";
				for(int j = 0; j<start;j++) {
					tmp += text.getText().charAt(j);
				}
				
				tmp += b2.getText() + " ";
				
				text.setText(tmp);

				if (!tabla.containsKey(b2.getText())) {
					b1.setText("");
					b2.setText("");
					b3.setText("");
				} else {
					try {
					b1.setText(tabla.get(b2.getText()).getP1());
					b2.setText(tabla.get(b2.getText()).getP2());
					b3.setText(tabla.get(b2.getText()).getP3());
					}catch (Exception ex) {
						System.err.print("No se encontraron palabras relacionadas");
					}
				}

			}
		});

		b3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int i = text.getText().length()-1;
				int start = 0;
				for(int p = i ; p>=0; p--) {
					if(text.getText().charAt(p) == ' ') {
						start = p+1;
						break;
					}
				}
				
				String tmp = "";
				for(int j = 0; j<start;j++) {
					tmp += text.getText().charAt(j);
				}
				
				tmp += b3.getText() + " ";
				
				text.setText(tmp);

				if (!tabla.containsKey(b3.getText())) {
					b1.setText("");
					b2.setText("");
					b3.setText("");
				} else {
					try {
					b1.setText(tabla.get(b3.getText()).getP1());
					b2.setText(tabla.get(b3.getText()).getP2());
					b3.setText(tabla.get(b3.getText()).getP3());
					}catch (Exception ex) {
						System.err.print("No se encontraron palabras relacionadas");
					}
				}

			}
		});
		
		JPanel panelbotones = new JPanel();
		panelbotones.add(b1);
		panelbotones.add(b2);
		panelbotones.add(b3);
		
		serverIP = host;
		text = new JTextField();
		text.setEditable(false);
		text.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ev) {
				// TODO Auto-generated method stub
				palabra = "";
				try {
					mandarMensaje(ev.getActionCommand(),username);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidKeyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchPaddingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalBlockSizeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BadPaddingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidKeySpecException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				almacenarPalabras(text.getText());
				escribirArchivo(archivo,text.getText());
				text.setText("");
				b1.setText("");
				b2.setText("");
				b3.setText("");
			}
		});
		text.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == ' ' || e.getKeyChar() == ',' || e.getKeyChar() == '.' || e.getKeyChar() == ';') {
					
					int i = text.getText().length()-1;
					int start = 0;
					for(int p = i ; p>=0; p--) {
						if(text.getText().charAt(p) == ' ') {
							start = p+1;
							break;
						}
					}
					String temp = "";
					for(int x = start; x<text.getText().length(); x++) {
						temp += text.getText().charAt(x);
					}
					palabra = temp;
					
					//System.out.println(palabra);
					
					if (!tabla.containsKey(palabra)) {
										b1.setText("");
										b2.setText("");
										b3.setText("");
					} else {
					
										b1.setText(tabla.get(palabra).getP1());
										b2.setText(tabla.get(palabra).getP2());
										b3.setText(tabla.get(palabra).getP3());
					}

					palabra = "";
				} else if(e.getKeyChar() == 8) {
					int i = text.getText().length()-1;
					int start = 0;
					for(int p = i ; p>=0; p--) {
						if(text.getText().charAt(p) == ' ') {
							start = p+1;
							break;
						}
					}
					String temp = "";
					for(int x = start; x<text.getText().length(); x++) {
						temp += text.getText().charAt(x);
					}
					palabra = temp;
					//System.out.println(palabra);
				}else {
					palabra = palabra + e.getKeyChar();
				}
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		JPanel msjs = new JPanel();
		msjs.setLayout(new GridLayout(2,1));
		
		msjs.add(text);
		
		add(msjs,BorderLayout.SOUTH);
		chat = new JTextArea();
		chat.setEditable(false);
		chat.setLineWrap(true);
		chat.setWrapStyleWord(true);
		add(new JScrollPane(chat));
		this.setSize(450,400);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Thread hilo=new Thread() {
			public void run() {
				try {
					init();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		generarLlaves();
		hilo.start();
	}
	public void init() throws IOException {
		leerArchivo(archivo);
		try {
			conectar();
			iniciarStreams();
			chatear();
			
		}catch(EOFException e) {
			mostrar("\n Se ha terminado la conexion");
		}catch(IOException io) {
			io.printStackTrace();
		}finally {
			cerrar();
		}
	}
	private void conectar() throws IOException{
		mostrar("\n Conectando....... \n" + serverIP);
		//SolicitarInfo();
		sckt = new Socket(InetAddress.getByName(serverIP),8888);
		mostrar("\n Conectado a: " + sckt.getInetAddress().getHostName());
		puedeEscribir(true);
	}
	private void iniciarStreams() throws IOException{
		out = new ObjectOutputStream(sckt.getOutputStream());
		out.flush();
		in = new ObjectInputStream(sckt.getInputStream());
		mostrar("\n Conectado!");
	}
	private void chatear()throws IOException {
		int x = 0;
		String msj = "Conectados! Pueden comenzar a chatear...";
		out.writeObject("%&%" + "," +  e + "," + n);
		out.flush();
		mostrar(msj);
		puedeEscribir(true);
		do {
			try {
				if(x == 0) {
				msj = (String) in.readObject();
				String [] dta = msj.split(",");
				oe = new BigInteger(dta[1]);
				on = new BigInteger(dta[2]);
				System.out.println("Datos Srv: " + oe + "," + on);
				x++;
				}else {
					BigInteger[] msg = (BigInteger[]) in.readObject();
					String[] decryptmsjarr = decrypt(msg);
					String decryptmsj = "";
					for(String s : decryptmsjarr) {
						decryptmsj += s;
					}
					mostrar(decryptmsj);
				}
			}catch(ClassNotFoundException e) {
				mostrar("\n Mensaje no aceptado");
			}
		}while(!msj.contentEquals("SALIR"));
	}
	private void puedeEscribir(boolean state) throws IOException {
		SwingUtilities.invokeLater(
				new Runnable() {
					public void run() {
						text.setEditable(state);
					}
		});
	}
	private void cerrar() throws IOException {
		mostrar("\n Saliendo...");
		puedeEscribir(false);
		try {
			out.close();
			in.close();
			sckt.close();
			dispose();
		}catch(IOException io) {
			io.printStackTrace();
		}
	}
	private void mandarMensaje(String msj, String username) throws ClassNotFoundException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException {
		try {
			out.flush();
			out.writeObject(encrypt("\n" + username + ": " + msj));
			out.flush();
			mostrar("\n" + username + ": " + msj);
		}catch(IOException io) {
			chat.append("\nAlgo falló al mandar el mensaje");
		}
	}
	private void mostrar(final String msj) {
		SwingUtilities.invokeLater(
				new Runnable() {
					public void run() {
						chat.append(msj);
					}
		});
	}
	public void escribirArchivo(String archivo, String text) {
		try {
			FileWriter bw = new FileWriter(archivo, true);
			bw.append("\n" + text);
			bw.close();

		} catch (Exception ex) {
			System.err.print("No se puede escribir el archivo");
		}
	}

	public void leerArchivo(String archivo) {


		try {
			BufferedReader br = new BufferedReader(new FileReader(archivo));
			String bfRead;

			while ((bfRead = br.readLine()) != null) {
				almacenarPalabras(bfRead);
			}
			br.close();
		} catch (Exception ex) {
			
			System.err.print("No se encontro el archivo");
		}
	}
	
	

	public void almacenarPalabras(String word) {

		String[] splitted = word.split("\\s+");
		String[] noPunct = word.split("\\s+");
		for(int i = 0; i < splitted.length; i++) {
			String tmp = removePunct(splitted[i]);
			noPunct[i] = tmp;	
		}
		
		for(int i = 0; i<noPunct.length-1; i++) {
			if(!tabla.containsKey(noPunct[i])) {
				tabla.put(noPunct[i], new PalabrasRelacionadas());
			}
			tabla.get(noPunct[i]).acomodarPalabra(noPunct[i+1]);
			//System.out.println("Palabra : " + noPunct[i] + " Relacionada: " + noPunct[i+1]);
		}	
	}
	
	public String removePunct(String s) {
		String word = "";
		for(int i = 0; i<s.length();i++) {
			if(s.charAt(i) != '!' && s.charAt(i) != '?' && s.charAt(i) != ',' && s.charAt(i) != '.' 
					&& s.charAt(i) != '¿' && s.charAt(i) != '¡' && s.charAt(i) != ';' && s.charAt(i) != '-'
					&& s.charAt(i) != '/' && s.charAt(i) != '(' && s.charAt(i) != ')') {
				word += s.charAt(i);
			}
		}
		return word;
	}
	public void generarLlaves() {
		Random rand = new SecureRandom();
		int BIT_LENGTH = 16;
		
		p = BigInteger.probablePrime(BIT_LENGTH/2, rand);
		q = BigInteger.probablePrime(BIT_LENGTH/2, rand);
		
		System.out.println("P1:" + p);
		System.out.println("P2:" + q);
		
		n = p.multiply(q);
		
		BigInteger p_1 = p.subtract(BigInteger.ONE);
		BigInteger q_1 = q.subtract(BigInteger.ONE);
		BigInteger phi = p_1.multiply(q_1);
		
		e = BigInteger.probablePrime(BIT_LENGTH/2, rand);
		while(phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi)<0) {
			e.add(BigInteger.ONE);
		}
		System.out.println("E: "+ e);
		d = e.modInverse(phi);
		System.out.println("D: " + d);
	}

	public BigInteger[] encrypt(String msj) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException, InvalidKeyException{
		BigInteger[] msjencrypt = new BigInteger[msj.length()];
		for(int i = 0; i < msj.length(); i++) {
			int v = msj.charAt(i);
			BigInteger bv = BigInteger.valueOf(v);
			System.out.println(bv);
			//System.out.println(n);
			BigInteger enc = bv.modPow(oe,on);
			System.out.print(enc);
			msjencrypt[i] = enc;
			System.out.println("\n----------------");
		}
		return msjencrypt;
	}
	public String[] decrypt(BigInteger[] criptmsj) {
		System.out.println("\n");
		String[] msjdecrypt = new String[criptmsj.length];
		for(int i = 0; i < criptmsj.length; i++) {
			BigInteger v = criptmsj[i];
			//System.out.println(criptmsj[i]);
			BigInteger dec = v.modPow(d,n);
		
			int dc = dec.intValue();
			System.out.println(dc);
			char c = (char)dc;
			//System.out.println(c);
			msjdecrypt[i] = String.valueOf(c);
		}
		return msjdecrypt;
	}
}

