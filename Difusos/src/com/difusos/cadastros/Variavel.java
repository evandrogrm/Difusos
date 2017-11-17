package com.difusos.cadastros;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.difusos.entity.Variaveis;
import com.difusos.util.BuscarDadosTxt;

public class Variavel {
	
	private String nome;
	private JFrame variavelFrame;
	private JTextField txtNomeVariavel;
	private JTextField txtConjIni;
	private JTextField txtConjFim;
	JCheckBox chckbxVarivelObjetivo;
	private JTable table;
	private BuscarDadosTxt txt = new BuscarDadosTxt();
	private Path arquivoOriginal;
	private Path arquivoSubstituto;
	private FileWriter fwRascunho;
	private FileReader frOriginal;

	public JFrame getVariavelFrame() {
		return variavelFrame;
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
		variavelFrame.setBounds(100, 100, 633, 295);
		variavelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		variavelFrame.getContentPane().setLayout(null);
		
		JLabel lblNomeDaVarivel = new JLabel("Nome da variável:");
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
		
		JButton btnCadastrar = new JButton("Incluir");
		btnCadastrar.setBounds(517, 11, 89, 23);
		variavelFrame.getContentPane().add(btnCadastrar);
		
		JButton btnCancelar = new JButton("Voltar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main m = new Main();
				variavelFrame.dispose();
				m.getMainFrame().setVisible(true);
			}
		});
		btnCancelar.setBounds(517, 224, 89, 23);
		variavelFrame.getContentPane().add(btnCancelar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 115, 485, 132);
		variavelFrame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		final DefaultTableModel model = new DefaultTableModel(); 
		model.addColumn("Nome Variável");
		model.addColumn("Universo Inicial");
		model.addColumn("Universo Final");
		model.addColumn("É objetivo");
		table.setModel(model);
		chckbxVarivelObjetivo = new JCheckBox("Variável Objetivo");
		chckbxVarivelObjetivo.setBounds(10, 60, 107, 23);
		variavelFrame.getContentPane().add(chckbxVarivelObjetivo);
		
		JButton btnApagar = new JButton("Apagar");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				table.setModel(model);
				try {
					arquivoOriginal = Paths.get(System.getenv("APPDATA")+"/DifusosEvandro/variaveis.txt");
					frOriginal = new FileReader(arquivoOriginal.toFile().getAbsolutePath());
					arquivoSubstituto = Paths.get(System.getenv("APPDATA")+"/DifusosEvandro/variaveis2.txt");
					fwRascunho =  new FileWriter(arquivoSubstituto.toFile().getAbsolutePath());
					BufferedReader br = new BufferedReader(frOriginal);
					String linha="";
					int linhaSelecionada = table.getSelectedRow();
					Object nomeBusca = table.getValueAt(linhaSelecionada, 0);
					String[] strv = new String[4];
					while((linha = br.readLine()) != null){
						strv = linha.split(";");
						if(!strv[0].equals(nomeBusca)) { 
                            fwRascunho.write(linha);
                            fwRascunho.write(System.getProperty("line.separator"));
                        }  
                    }
					br.close();
					fwRascunho.close();
					arquivoOriginal.toFile().delete();
					arquivoSubstituto.toFile().renameTo(arquivoOriginal.toFile().getAbsoluteFile());
				} catch (Exception e) {
					e.printStackTrace();
				}
				model.removeRow(table.getSelectedRow());
			}
		});
		btnApagar.setBounds(517, 60, 89, 23);
		variavelFrame.getContentPane().add(btnApagar);
		
		try {
			List<Variaveis> listaDeVariaveiss = new ArrayList<>();
			listaDeVariaveiss = txt.listaDeVariaveis();
			if (listaDeVariaveiss == null) {
				model.addRow(new Object[] { "", "", "", "" });
				table.setModel(model);
			}else{
				String temp="";
				for (Variaveis variaveis : listaDeVariaveiss) {
					if(variaveis.getIsObjetivo().equals("0")){
						temp="Não";
					}else{
						temp="Sim";
					}
					model.addRow(new Object[]{variaveis.getNome(),variaveis.getUniversalIni(),
							variaveis.getUniversalFim(),temp});
					table.setModel(model);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(!txtNomeVariavel.getText().equals("")){
						Path pathVariaveis = Paths.get(System.getenv("APPDATA")+"/DifusosEvandro/variaveis.txt");
						FileWriter fw = new FileWriter(pathVariaveis.toFile().getAbsolutePath(), true);
						String nome,conjIni,conjFim;
						nome = txtNomeVariavel.getText().trim();
						conjIni = txtConjIni.getText().trim();
						conjFim = txtConjFim.getText().trim();
						if(chckbxVarivelObjetivo.isSelected()){
							fw.write(nome+";"+conjIni+";"+conjFim+";1");
							model.addRow(new Object[]{nome,conjIni,conjFim,"Sim"});
							table.setModel(model);
							chckbxVarivelObjetivo.setSelected(false);
						}else{
							fw.write(nome+";"+conjIni+";"+conjFim+";0");
							model.addRow(new Object[]{nome,conjIni,conjFim,"Não"});
							table.setModel(model);
						}
						txtNomeVariavel.setText("");
						txtConjIni.setText("");
						txtConjFim.setText("");
						
						fw.write(System.getProperty("line.separator"));
						fw.close();
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
