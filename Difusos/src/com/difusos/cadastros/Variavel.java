package com.difusos.cadastros;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Variavel {
	
	private String nome;
	private JFrame variavelFrame;
	private JTextField txtNomeVariavel;
	private JTextField txtConjIni;
	private JTextField txtConjFim;

	public JFrame getVariavelFrame() {
		return variavelFrame;
	}
	
	public ArrayList<String> getListaDeVariaveis(){
		ArrayList<String> listaDeVariaveis = null;
		Path pathVariaveis = null;
		BufferedReader br = null;
		try {
			listaDeVariaveis = new ArrayList<>();
			pathVariaveis = Paths.get(System.getenv("APPDATA")+"/DifusosEvandro/variaveis.txt");
			br = new BufferedReader(new FileReader(pathVariaveis.toFile().getAbsolutePath()));
			String linha="";
			String[] nome = new String[4];
			while((linha = br.readLine()) != null){
				nome = linha.split(";");
				listaDeVariaveis.add(nome[0]);
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException io){
			io.printStackTrace();
		}
		
		return listaDeVariaveis;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					Variavel window = new Variavel();
					window.variavelFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Variavel() {
		initialize();
	}

	private void initialize() {
		variavelFrame = new JFrame();
		variavelFrame.setTitle("Cadastro de Vari\u00E1veis");
		variavelFrame.setBounds(100, 100, 330, 145);
		variavelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		variavelFrame.getContentPane().setLayout(null);
		
		JLabel lblNomeDaVarivel = new JLabel("Nome da vari\u00E1vel:");
		lblNomeDaVarivel.setBounds(10, 11, 87, 14);
		variavelFrame.getContentPane().add(lblNomeDaVarivel);
		
		txtNomeVariavel = new JTextField();
		txtNomeVariavel.setBounds(107, 8, 180, 20);
		variavelFrame.getContentPane().add(txtNomeVariavel);
		txtNomeVariavel.setColumns(10);
		
		JLabel lblConjuntoUniverso = new JLabel("Conjunto Universo:");
		lblConjuntoUniverso.setBounds(10, 39, 93, 14);
		variavelFrame.getContentPane().add(lblConjuntoUniverso);
		
		txtConjIni = new JTextField();
		txtConjIni.setBounds(107, 36, 86, 20);
		variavelFrame.getContentPane().add(txtConjIni);
		txtConjIni.setColumns(10);
		
		JLabel label = new JLabel("\u00E0");
		label.setBounds(203, 39, 6, 14);
		variavelFrame.getContentPane().add(label);
		
		txtConjFim = new JTextField();
		txtConjFim.setColumns(10);
		txtConjFim.setBounds(219, 36, 86, 20);
		variavelFrame.getContentPane().add(txtConjFim);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Path pathVariaveis = Paths.get(System.getenv("APPDATA")+"/DifusosEvandro/variaveis.txt");
					FileWriter fw = new FileWriter(pathVariaveis.toFile().getAbsolutePath(), true);
					fw.write(txtNomeVariavel.getText()+";"+txtConjIni.getText()+";"+txtConjFim.getText()+";");
					fw.write(System.getProperty("line.separator"));
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				Main m = new Main();
				variavelFrame.dispose();
				m.getMainFrame().setVisible(true);
				m.preencheCombos();
			}
		});
		btnCadastrar.setBounds(107, 67, 89, 23);
		variavelFrame.getContentPane().add(btnCadastrar);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
