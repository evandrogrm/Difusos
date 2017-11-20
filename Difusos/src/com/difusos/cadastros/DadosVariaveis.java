package com.difusos.cadastros;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.difusos.entity.DadosVar;
import com.difusos.entity.Variaveis;
import com.difusos.util.BuscarDadosTxt;
import com.difusos.util.PreencherDadosTxt;

public class DadosVariaveis {

	private JFrame dadosFrame;
	private BuscarDadosTxt txtBusca = new BuscarDadosTxt();
	private PreencherDadosTxt txtPreenche = new PreencherDadosTxt();
	private JTextField txtValor;
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					DadosVariaveis window = new DadosVariaveis();
					window.dadosFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DadosVariaveis() {
		initialize();
	}

	private void initialize() {
		dadosFrame = new JFrame();
		dadosFrame.setTitle("Dados das Variáveis");
		dadosFrame.setBounds(100, 100, 400, 346);
		dadosFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dadosFrame.getContentPane().setLayout(null);
		
		JLabel lblVariavel = new JLabel("Variavel:");
		lblVariavel.setBounds(10, 11, 55, 14);
		dadosFrame.getContentPane().add(lblVariavel);
		
		final JComboBox<String> comboBox = buscaVariaveis();
		comboBox.setBounds(10, 26, 135, 20);
		dadosFrame.getContentPane().add(comboBox);
		
		JLabel lblValor = new JLabel("Valor (float):");
		lblValor.setBounds(10, 57, 86, 14);
		dadosFrame.getContentPane().add(lblValor);
		
		txtValor = new JTextField();
		txtValor.setBounds(10, 75, 86, 20);
		dadosFrame.getContentPane().add(txtValor);
		txtValor.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 120, 360, 149);
		dadosFrame.getContentPane().add(scrollPane);
		
		final DefaultTableModel model = new DefaultTableModel(); 
		model.addColumn("Nome Variável");
		model.addColumn("Valor");
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(model);
		
		JButton btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					List<DadosVar> listadeDados = txtBusca.listaDeDados();
					boolean naoCadastrado = true;
					for (DadosVar d : listadeDados) {
						if(d.getV().getNome().equals(comboBox.getSelectedItem().toString())){
							JOptionPane.showMessageDialog(null, "Variável já cadastrada");
							naoCadastrado = false;
							break;
						}
					}
					if(!txtValor.getText().equals("") && naoCadastrado){
						String nomeVariavel = comboBox.getSelectedItem().toString();    
						float valor         = Float.parseFloat(txtValor.getText());                       
						txtPreenche.preencheDadosVariaveis(nomeVariavel,valor);
						model.addRow(new Object[]{nomeVariavel, valor});
						txtValor.setText("");
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnIncluir.setBounds(200, 7, 89, 23);
		dadosFrame.getContentPane().add(btnIncluir);
		
		JButton btnApagar = new JButton("Apagar");
		btnApagar.addActionListener(new ActionListener() {
			private Path arquivoOriginal;
			private FileReader frOriginal;
			private Path arquivoSubstituto;
			private FileWriter fwRascunho;

			public void actionPerformed(ActionEvent evt) {
				try {
					int linhaSelecionada = table.getSelectedRow();
					if (linhaSelecionada >= 0) {
						arquivoOriginal = Paths.get(System.getenv("APPDATA")+"/DifusosEvandro/dadosVariaveis.txt");
						frOriginal = new FileReader(arquivoOriginal.toFile().getAbsolutePath());
						arquivoSubstituto = Paths.get(System.getenv("APPDATA")+"/DifusosEvandro/dadosVariaveis2.txt");
						fwRascunho =  new FileWriter(arquivoSubstituto.toFile().getAbsolutePath());
						BufferedReader br = new BufferedReader(frOriginal);
						String linha="";
						Object nomeVariavel = table.getValueAt(linhaSelecionada, 0);
						String[] strv = new String[2];
						while((linha = br.readLine()) != null){
							strv = linha.split(";");
							if(!(strv[0].equals(nomeVariavel))) { 
								fwRascunho.write(linha);
								fwRascunho.write(System.getProperty("line.separator"));
							}  
						}
						br.close();
						fwRascunho.close();
						arquivoOriginal.toFile().delete();
						arquivoSubstituto.toFile().renameTo(arquivoOriginal.toFile().getAbsoluteFile());
						model.removeRow(table.getSelectedRow());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnApagar.setBounds(200, 74, 89, 23);
		dadosFrame.getContentPane().add(btnApagar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Main m = new Main();
				dadosFrame.dispose();
				m.getMainFrame().setVisible(true);
			}
		});
		btnVoltar.setBounds(299, 74, 89, 23);
		dadosFrame.getContentPane().add(btnVoltar);
		
		try {
			List<DadosVar> listadeDados = txtBusca.listaDeDados();
			for (DadosVar d : listadeDados) {
				model.addRow(new Object[]{d.getV().getNome(),d.getValor()});
				table.setModel(model);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private JComboBox<String> buscaVariaveis() {
		JComboBox<String> combobox = new JComboBox<>();
		List<Variaveis> listadeVariaveis = txtBusca.listaDeVariaveis();
		for (Variaveis v : listadeVariaveis) {
			if(v.getIsObjetivo().equals("0"))
				combobox.addItem(v.getNome());
		}
		return combobox;
	}

	public JFrame getDadosFrame() {
		return dadosFrame;
	}	
}
