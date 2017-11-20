package com.difusos.cadastros;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import com.difusos.core.Execucao;

public class Main {
	
	private JFrame mainFrame;
	private Path pathPrincipal;
	private Path pathVariaveis;
	private Path pathTermos;
	private Path pathPertinencia;
	private Path pathDadosVariaveis;
	private Path pathAtivacao;
	private Path pathConfig;
	private Path pathAgregacao;
	Execucao exec = new Execucao();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					Main window = new Main();
					window.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Main() {
		initialize();
	}
	
	private void initialize() {
		mainFrame = new JFrame();
		mainFrame.setTitle("Difusos");
		mainFrame.setBounds(100, 100, 450, 395);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 442, 20);
		mainFrame.getContentPane().add(menuBar);
		
		JMenu mnCadastros = new JMenu("Cadastros");
		menuBar.add(mnCadastros);
		
		JMenuItem mntmVarivel = new JMenuItem("Variável");
		mntmVarivel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Variavel v = new Variavel();
				mainFrame.dispose();
				v.getVariavelFrame().setVisible(true);
			}
		});
		mnCadastros.add(mntmVarivel);
		
		JMenuItem mntmTermo = new JMenuItem("Termo");
		mntmTermo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Termo t = new Termo();
				mainFrame.dispose();
				t.getTermoFrame().setVisible(true);
			}
		});
		mnCadastros.add(mntmTermo);
		
		JMenu mnConfigurao = new JMenu("Configuração");
		menuBar.add(mnConfigurao);
		
		JMenuItem mntmConfigurar = new JMenuItem("Configurar");
		mntmConfigurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Configurar c = new Configurar();
				mainFrame.dispose();
				c.getConfiugurarFrame().setVisible(true);
			}
		});
		mnConfigurao.add(mntmConfigurar);
		
		JButton btnCalcularPertinencia = new JButton("Calcular Pertinência");
		btnCalcularPertinencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				exec.pertinencias();
			}
		});
		btnCalcularPertinencia.setBounds(10, 90, 134, 23);
		mainFrame.getContentPane().add(btnCalcularPertinencia);
		
		JButton btnDadosVariaveis = new JButton("Dados das Variáveis");
		btnDadosVariaveis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DadosVariaveis d = new DadosVariaveis();
				mainFrame.dispose();
				d.getDadosFrame().setVisible(true);
			}
		});
		btnDadosVariaveis.setBounds(10, 45, 134, 23);
		mainFrame.getContentPane().add(btnDadosVariaveis);
		
		JButton btnCalcularAtivao = new JButton("Calcular Ativação");
		btnCalcularAtivao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exec.pertinencias();
				exec.ativar();
			}
		});
		btnCalcularAtivao.setBounds(10, 135, 134, 23);
		mainFrame.getContentPane().add(btnCalcularAtivao);
		
		JButton btnCalcularAgregao = new JButton("Calcular Agregação");
		btnCalcularAgregao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exec.pertinencias();
				exec.ativar();
				exec.agregar();
			}
		});
		btnCalcularAgregao.setBounds(10, 180, 134, 23);
		mainFrame.getContentPane().add(btnCalcularAgregao);
		
		JButton btnVisualizarArquivo = new JButton("Visualizar Arquivo");
		btnVisualizarArquivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					Path arquivo = Paths.get(System.getenv("APPDATA")+"/DifusosEvandro"+"/pertinencias.txt");
					Process pro = Runtime.getRuntime().exec("notepad "+arquivo.toFile().getAbsolutePath());
					pro.waitFor();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnVisualizarArquivo.setBounds(154, 90, 134, 23);
		mainFrame.getContentPane().add(btnVisualizarArquivo);
		
		JButton btnNewButton = new JButton("Visualizar Arquivo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					Path arquivo = Paths.get(System.getenv("APPDATA")+"/DifusosEvandro"+"/ativacao.txt");
					Process pro = Runtime.getRuntime().exec("notepad "+arquivo.toFile().getAbsolutePath());
					pro.waitFor();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(154, 135, 134, 23);
		mainFrame.getContentPane().add(btnNewButton);
		
		JButton btnVisualizarArquivo_1 = new JButton("Visualizar Arquivo");
		btnVisualizarArquivo_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					Path arquivo = Paths.get(System.getenv("APPDATA")+"/DifusosEvandro"+"/agregacao.txt");
					Process pro = Runtime.getRuntime().exec("notepad "+arquivo.toFile().getAbsolutePath());
					pro.waitFor();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnVisualizarArquivo_1.setBounds(154, 180, 134, 23);
		mainFrame.getContentPane().add(btnVisualizarArquivo_1);
		
		JButton btnCalcularCentride = new JButton("Calcular Centróide");
		btnCalcularCentride.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//TODO A
				exec.pertinencias();
				exec.ativar();
				exec.agregar();
				float resultado = exec.calcularCentroide();
				JOptionPane.showMessageDialog(null, resultado);
			}
		});
		btnCalcularCentride.setBounds(10, 225, 134, 23);
		mainFrame.getContentPane().add(btnCalcularCentride);
		
		JLabel lblInformarDadosDas = new JLabel("Informar dados das Vari\u00E1veis:");
		lblInformarDadosDas.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInformarDadosDas.setBounds(10, 30, 221, 14);
		mainFrame.getContentPane().add(lblInformarDadosDas);
		
		JLabel lblPertinncias = new JLabel("Pertin\u00EAncias:");
		lblPertinncias.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPertinncias.setBounds(10, 75, 221, 14);
		mainFrame.getContentPane().add(lblPertinncias);
		
		JLabel lblAtivao = new JLabel("Ativa\u00E7\u00E3o:");
		lblAtivao.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAtivao.setBounds(10, 120, 221, 14);
		mainFrame.getContentPane().add(lblAtivao);
		
		JLabel lblAgregao = new JLabel("Agrega\u00E7\u00E3o:");
		lblAgregao.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAgregao.setBounds(10, 165, 221, 14);
		mainFrame.getContentPane().add(lblAgregao);
		
		JLabel lblCentride = new JLabel("Centr\u00F3ide:");
		lblCentride.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCentride.setBounds(10, 210, 221, 14);
		mainFrame.getContentPane().add(lblCentride);
		
		try {
			pathPrincipal = Paths.get(System.getenv("APPDATA")+"/DifusosEvandro");
			pathVariaveis = Paths.get(pathPrincipal.toFile().getAbsolutePath()+"/variaveis.txt");
			pathTermos = Paths.get(pathPrincipal.toFile().getAbsolutePath()+"/termos.txt");
			pathPertinencia = Paths.get(pathPrincipal.toFile().getAbsolutePath()+"/pertinencias.txt");
			pathDadosVariaveis = Paths.get(pathPrincipal.toFile().getAbsolutePath()+"/dadosVariaveis.txt");
			pathAtivacao = Paths.get(pathPrincipal.toFile().getAbsolutePath()+"/ativacao.txt");
			pathAgregacao = Paths.get(pathPrincipal.toFile().getAbsolutePath()+"/agregacao.txt");
			pathConfig = Paths.get(pathPrincipal.toFile().getAbsolutePath()+"/config.txt");
			
			if (!pathPrincipal.toFile().exists()) {
				pathPrincipal.toFile().mkdirs();
			}
			if (!pathVariaveis.toFile().exists()) {
				FileWriter arq = new FileWriter(pathVariaveis.toString());
				arq.close();
			}
			if (!pathTermos.toFile().exists()) {
				FileWriter arq = new FileWriter(pathTermos.toString());
				arq.close();
			}
			if (!pathPertinencia.toFile().exists()) {
				FileWriter arq = new FileWriter(pathPertinencia.toString());
				arq.close();
			}
			if (!pathDadosVariaveis.toFile().exists()) {
				FileWriter arq = new FileWriter(pathDadosVariaveis.toString());
				arq.close();
			}
			if (!pathAtivacao.toFile().exists()) {
				FileWriter arq = new FileWriter(pathAtivacao.toString());
				arq.close();
			}
			if (!pathAgregacao.toFile().exists()) {
				FileWriter arq = new FileWriter(pathAgregacao.toString());
				arq.close();
			}
			if (!pathConfig.toFile().exists()) {
				FileWriter arq = new FileWriter(pathConfig.toString());
				arq.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao carregar dados");
		}
		
	}
	
	public JFrame getMainFrame() {
		return mainFrame;
	}
}
