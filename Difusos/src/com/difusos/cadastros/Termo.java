package com.difusos.cadastros;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class Termo {

	private JFrame termoFrame;
	private JTextField txtTermoNome;
	private JTextField txtTermoNucleoIni;
	private JTextField txtTermoNucleoFim;

	

	private JTextField txtTermoSuporteIni;
	private JLabel label_1;
	private JTextField txtTermoSupoteFim;
	private JButton btnTermoCad;
	private JLabel lblVarivel;
	private JComboBox<String> comboBox;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					Termo window = new Termo();
					window.termoFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Termo() {
		initialize();
	}

	private void initialize() {
		termoFrame = new JFrame();
		termoFrame.setTitle("Cadastro de Termos");
		termoFrame.setBounds(100, 100, 246, 174);
		termoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(26, 39, 31, 14);
		
		txtTermoNome = new JTextField();
		txtTermoNome.setBounds(67, 34, 125, 20);
		txtTermoNome.setColumns(10);
		
		JLabel lblNcleo = new JLabel("N\u00FAcleo:");
		lblNcleo.setBounds(26, 64, 36, 14);
		
		txtTermoNucleoIni = new JTextField();
		txtTermoNucleoIni.setBounds(67, 58, 50, 20);
		txtTermoNucleoIni.setColumns(10);
		
		txtTermoNucleoFim = new JTextField();
		txtTermoNucleoFim.setBounds(142, 58, 50, 20);
		txtTermoNucleoFim.setColumns(10);
		
		JLabel label = new JLabel("\u00E0");
		label.setBounds(127, 64, 12, 14);
		
		JLabel lblSuporte = new JLabel("Suporte:");
		lblSuporte.setBounds(21, 85, 42, 14);
		termoFrame.getContentPane().setLayout(null);
		termoFrame.getContentPane().add(lblNome);
		termoFrame.getContentPane().add(txtTermoNome);
		termoFrame.getContentPane().add(lblNcleo);
		termoFrame.getContentPane().add(txtTermoNucleoIni);
		termoFrame.getContentPane().add(txtTermoNucleoFim);
		termoFrame.getContentPane().add(label);
		termoFrame.getContentPane().add(lblSuporte);
		
		txtTermoSuporteIni = new JTextField();
		txtTermoSuporteIni.setColumns(10);
		txtTermoSuporteIni.setBounds(67, 82, 50, 20);
		termoFrame.getContentPane().add(txtTermoSuporteIni);
		
		label_1 = new JLabel("\u00E0");
		label_1.setBounds(127, 88, 12, 14);
		termoFrame.getContentPane().add(label_1);
		
		txtTermoSupoteFim = new JTextField();
		txtTermoSupoteFim.setColumns(10);
		txtTermoSupoteFim.setBounds(142, 82, 50, 20);
		termoFrame.getContentPane().add(txtTermoSupoteFim);
		
		btnTermoCad = new JButton("Cadastrar");
		btnTermoCad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Path pathTermos = Paths.get(System.getenv("APPDATA")+"/DifusosEvandro/termos.txt");
					FileWriter fw = new FileWriter(pathTermos.toFile().getAbsolutePath(), true);
					fw.write(comboBox.getItemAt(comboBox.getSelectedIndex())+";"
							+txtTermoNome.getText()+";"
							+txtTermoNucleoIni.getText()+";"+txtTermoNucleoFim.getText()+";"
							+txtTermoSuporteIni.getText()+";"+txtTermoSupoteFim.getText()+";");
					fw.write(System.getProperty("line.separator"));
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				Main m = new Main();
				termoFrame.dispose();
				m.getMainFrame().setVisible(true);
				m.preencheCombos();
			}
		});
		btnTermoCad.setBounds(67, 113, 89, 23);
		termoFrame.getContentPane().add(btnTermoCad);
		
		lblVarivel = new JLabel("Vari\u00E1vel:");
		lblVarivel.setBounds(10, 11, 46, 14);
		termoFrame.getContentPane().add(lblVarivel);
		
		
		comboBox = buscaVariaveis();
		comboBox.setBounds(67, 8, 125, 20);
		termoFrame.getContentPane().add(comboBox);
		
	}
	
	private JComboBox<String> buscaVariaveis() {
		JComboBox<String> combobox = new JComboBox<>();
		Variavel v = new Variavel();
		ArrayList<String> listaDeVariaveis = v.getListaDeVariaveis();
		for (String string : listaDeVariaveis) {
			combobox.addItem(string);
		}
		return combobox;
	}

	public JFrame getTermoFrame() {
		return termoFrame;
	}	
}
