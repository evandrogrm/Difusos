package com.difusos.cadastros;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.difusos.entity.Termos;
import com.difusos.entity.Variaveis;
import com.difusos.util.BuscarDadosTxt;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;

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
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnApagar;
	private BuscarDadosTxt txt = new BuscarDadosTxt();
	private Path arquivoOriginal;
	private Path arquivoSubstituto;
	private FileReader frOriginal;
	private FileWriter fwRascunho;
	

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
		termoFrame.setBounds(100, 100, 725, 346);
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
		
		btnTermoCad = new JButton("Incluir");
		btnTermoCad.setBounds(222, 11, 89, 23);
		termoFrame.getContentPane().add(btnTermoCad);
		
		lblVarivel = new JLabel("Vari\u00E1vel:");
		lblVarivel.setBounds(10, 11, 46, 14);
		termoFrame.getContentPane().add(lblVarivel);
		
		
		comboBox = buscaVariaveis();
		comboBox.setBounds(67, 8, 125, 20);
		termoFrame.getContentPane().add(comboBox);
		
		JButton btnCancelar = new JButton("Voltar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Main m = new Main();
				termoFrame.dispose();
				m.getMainFrame().setVisible(true);
			}
		});
		btnCancelar.setBounds(367, 81, 89, 23);
		termoFrame.getContentPane().add(btnCancelar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 110, 689, 184);
		
		final DefaultTableModel model = new DefaultTableModel(); 
		model.addColumn("Nome Variável");
		model.addColumn("Nome Termo");
		model.addColumn("Termo Ini");
		model.addColumn("Termo Fim");
		model.addColumn("Suporte Ini");
		model.addColumn("Suporte Fim");
		
		termoFrame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(model);
		
		btnApagar = new JButton("Apagar");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				table.setModel(model);
				try {
					arquivoOriginal = Paths.get(System.getenv("APPDATA")+"/DifusosEvandro/termos.txt");
					frOriginal = new FileReader(arquivoOriginal.toFile().getAbsolutePath());
					arquivoSubstituto = Paths.get(System.getenv("APPDATA")+"/DifusosEvandro/termos2.txt");
					fwRascunho =  new FileWriter(arquivoSubstituto.toFile().getAbsolutePath());
					BufferedReader br = new BufferedReader(frOriginal);
					String linha="";
					int linhaSelecionada = table.getSelectedRow();
					Object nomeTermo = table.getValueAt(linhaSelecionada, 1);
					String[] strv = new String[4];
					while((linha = br.readLine()) != null){
						strv = linha.split(";");
						if(!strv[1].equals(nomeTermo)) { 
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
		btnApagar.setBounds(367, 11, 89, 23);
		termoFrame.getContentPane().add(btnApagar);
		
		try {
			List<Termos> listadeTermos = txt.listaDeTermos();
			for (Termos t : listadeTermos) {
				model.addRow(new Object[]{t.getVariavel().getNome(),t.getNome(),t.getNucleoIni(),t.getNucleoFim(),
						t.getSuporteIni(),t.getSuporteFim()});
				table.setModel(model);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		btnTermoCad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(!txtTermoNome.getText().equals("")){
						Path pathTermos = Paths.get(System.getenv("APPDATA")+"/DifusosEvandro/termos.txt");
						FileWriter fw = new FileWriter(pathTermos.toFile().getAbsolutePath(), true);
						fw.write(comboBox.getItemAt(comboBox.getSelectedIndex())+";"
								+txtTermoNome.getText()+";"
								+txtTermoNucleoIni.getText()+";"+txtTermoNucleoFim.getText()+";"
								+txtTermoSuporteIni.getText()+";"+txtTermoSupoteFim.getText()+";");
						fw.write(System.getProperty("line.separator"));
						fw.close();
						String nomeVariavel = comboBox.getSelectedItem().toString();
						String nomeTermo    = txtTermoNome.getText();                   txtTermoNome.setText("");
						String nucleoIni    = txtTermoNucleoIni.getText();              txtTermoNucleoIni.setText("");
						String nucleoFim    = txtTermoNucleoFim.getText();              txtTermoNucleoFim.setText("");
						String suporteIni   = txtTermoSuporteIni.getText();             txtTermoSuporteIni.setText("");
						String suporteFim   = txtTermoSupoteFim.getText();              txtTermoSupoteFim.setText("");
						model.addRow(new Object[]{nomeVariavel,nomeTermo,nucleoIni,nucleoFim,suporteIni,suporteFim});
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		});
		
	}
	
	private JComboBox<String> buscaVariaveis() {
		JComboBox<String> combobox = new JComboBox<>();
		List<Variaveis> listadeVariaveis = txt.listaDeVariaveis();
		for (Variaveis v : listadeVariaveis) {
			combobox.addItem(v.getNome());
		}
		return combobox;
	}

	public JFrame getTermoFrame() {
		return termoFrame;
	}	
}
