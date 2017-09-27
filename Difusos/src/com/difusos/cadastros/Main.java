package com.difusos.cadastros;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Main {
	
	private JFrame mainFrame;
	private JTextField textField;
	private JComboBox<String> cbVariaveis;
	private JComboBox<String> cbTermos;
	Path pathPrincipal;
	Path pathVariaveis;
	Path pathTermos;
	
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
		mainFrame.setBounds(100, 100, 450, 300);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 442, 20);
		mainFrame.getContentPane().add(menuBar);
		
		JMenu mnCadastros = new JMenu("Cadastros");
		menuBar.add(mnCadastros);
		
		JMenuItem mntmVarivel = new JMenuItem("Vari\u00E1vel");
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
		
		JMenu mnEditar = new JMenu("Editar");
		menuBar.add(mnEditar);
		
		JMenuItem mntmVarivel_1 = new JMenuItem("Vari\u00E1vel");
		mnEditar.add(mntmVarivel_1);
		
		JMenuItem mntmTermo_1 = new JMenuItem("Termo");
		mnEditar.add(mntmTermo_1);
		
		JMenu mnGerar = new JMenu("Gerar");
		menuBar.add(mnGerar);
		
		JMenuItem mntmGrfico = new JMenuItem("Gr\u00E1fico");
		mnGerar.add(mntmGrfico);
		
		textField = new JTextField();
		textField.setBounds(30, 80, 86, 20);
		mainFrame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnCalcularPertinncia = new JButton("Calcular Pertin\u00EAncia");
		btnCalcularPertinncia.setBounds(126, 79, 127, 23);
		mainFrame.getContentPane().add(btnCalcularPertinncia);
		
		JLabel lblX = new JLabel("X:");
		lblX.setBounds(10, 83, 10, 14);
		mainFrame.getContentPane().add(lblX);
		
		JButton btnCalcularE = new JButton("Calcular \"Muito\"");
		btnCalcularE.setBounds(126, 113, 127, 23);
		mainFrame.getContentPane().add(btnCalcularE);
		
		JButton btnCalcularalgo = new JButton("Calcular \"Algo\"");
		btnCalcularalgo.setBounds(126, 147, 127, 23);
		mainFrame.getContentPane().add(btnCalcularalgo);
		
		cbVariaveis = new JComboBox<>();
		cbVariaveis.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				preencheCombos();
			}
		});
		cbVariaveis.setBounds(30, 56, 110, 20);
		mainFrame.getContentPane().add(cbVariaveis);
		
		cbTermos = new JComboBox<>();
		cbTermos.setBounds(165, 56, 127, 20);
		mainFrame.getContentPane().add(cbTermos);
		
		JButton btnCalculardeFato = new JButton("Calcular \"De Fato\"");
		btnCalculardeFato.setBounds(126, 181, 127, 23);
		mainFrame.getContentPane().add(btnCalculardeFato);
		
		JLabel lblVarivel = new JLabel("Vari\u00E1vel");
		lblVarivel.setBounds(30, 41, 46, 14);
		mainFrame.getContentPane().add(lblVarivel);
		
		JLabel lblTermo = new JLabel("Termo");
		lblTermo.setBounds(165, 41, 46, 14);
		mainFrame.getContentPane().add(lblTermo);
		
		JButton btnCarregarDados = new JButton("Carregar Dados");
		btnCarregarDados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				preencheCombos();
			}
		});
		btnCarregarDados.setBounds(302, 55, 110, 23);
		mainFrame.getContentPane().add(btnCarregarDados);
		
		try {
			pathPrincipal = Paths.get(System.getenv("APPDATA")+"/DifusosEvandro");
			pathVariaveis = Paths.get(pathPrincipal.toFile().getAbsolutePath()+"/variaveis.txt");
			pathTermos = Paths.get(pathPrincipal.toFile().getAbsolutePath()+"/termos.txt");
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
			preencheCombos();
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao carregar dados");
		}
		
	}

	public void preencheCombos() {
		ArrayList<String> listaDeVariaveis = preencheLista(pathVariaveis, "VARIAVEL");
		ArrayList<String> listaDeTermos = preencheLista(pathTermos, "TERMOS");
		List<String> listaVarAdicionadas = new ArrayList<>();
		for (int i = 0; i < cbVariaveis.getItemCount(); i++) {
			listaVarAdicionadas.add(cbVariaveis.getItemAt(i).toString());
		}
		List<String> listaTerAdicionados = new ArrayList<>();
		for (int i = 0; i < cbTermos.getItemCount(); i++) {
			listaTerAdicionados.add(cbTermos.getItemAt(i).toString());
		}
		for (String str : listaDeVariaveis) {
			if(!listaVarAdicionadas.contains(str)){
				cbVariaveis.addItem(str);
			}
		}
		for (String str : listaDeTermos) {
			if(!listaTerAdicionados.contains(str)){
				cbTermos.addItem(str);
			}
		}
		for (int i = 0; i < cbTermos.getItemCount(); i++) {
			if(cbTermos.getItemAt(i).toString().equals("")){
				cbTermos.removeItem(i);
			}
		}
	}

	private ArrayList<String> preencheLista(Path path, String TIPO) {
		ArrayList<String> lista = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(path.toFile().getAbsolutePath()));
			String linha = "";
			String str[] = new String[10];
			while ((linha = br.readLine()) != null) {
				str = linha.split(";");
				if(TIPO.equals("VARIAVEL")){
					if (!lista.contains(str[0])) {
						lista.add(str[0]);
					}
				} else if(TIPO.equals("TERMOS")){
					if(cbVariaveis.isShowing() && cbVariaveis.getItemAt(cbVariaveis.getSelectedIndex()).equals(str[0])){
						lista.add(str[1]);
					}
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(lista.isEmpty()){
			lista.add("");
		}
		return lista;
	}

	public JFrame getMainFrame() {
		return mainFrame;
	}
}
