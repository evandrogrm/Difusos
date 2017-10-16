package com.difusos.bc;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableColumn;

import com.difusos.entity.Termos;
import com.difusos.entity.Variaveis;
import com.difusos.util.BuscarDadosTxt;

public class BC {

	private JFrame frameBC;
	private JTable table;
	private JTable table2;
	private TableModelBCSE tableModelBCSE;
	private TableModelBCENTAO tableModelBCENTAO;
	private BuscarDadosTxt txt;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					BC window = new BC();
					window.frameBC.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BC() {
		initialize();
		construtorTableModel();
	}

	private void initialize() {
		frameBC = new JFrame();
		frameBC.setTitle("Base de Conhecimento");
		frameBC.setBounds(100, 100, 691, 468);
		frameBC.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameBC.getContentPane().setLayout(null);
		
		tableModelBCSE = new TableModelBCSE();
		tableModelBCENTAO = new TableModelBCENTAO();
		txt = new BuscarDadosTxt();
		
		JLabel lblSe = new JLabel("SE:");
		lblSe.setBounds(10, 10, 46, 14);
		frameBC.getContentPane().add(lblSe);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 35, 663, 134);
		frameBC.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(tableModelBCSE);
		table.setFillsViewportHeight(true);
		table.setShowGrid(true);
		scrollPane.setViewportView(table);
		
		JButton btnAdicionarLinhaNova = new JButton("^Adicionar Linha Nova^");
		btnAdicionarLinhaNova.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<Variaveis> variaveis = txt.listaDeVariaveis();
				JComboBox<Object> comboVariaveis = new JComboBox<>();
				for (Variaveis v : variaveis) {
					comboVariaveis.addItem(v.getNome().toString());
				}
				List<Termos> termos = txt.listaDeTermos();
				JComboBox<Object> comboTermos = new JComboBox<>();
				for (Termos t : termos) {
					comboTermos.addItem(t);
				}
				Condicao c = new Condicao(comboVariaveis,"",comboTermos,"");
				tableModelBCSE.addRow(c);
			}
		});
		btnAdicionarLinhaNova.setBounds(10, 205, 159, 23);
		frameBC.getContentPane().add(btnAdicionarLinhaNova);
		
		JButton btnRemoverLinhaSelecionada = new JButton("^Remover Linha Selecionada^");
		btnRemoverLinhaSelecionada.setBounds(179, 205, 179, 23);
		frameBC.getContentPane().add(btnRemoverLinhaSelecionada);

		//TODO ENTÃO:
		JLabel lblEnto = new JLabel("ENT\u00C3O:");
		lblEnto.setBounds(10, 180, 46, 14);
		frameBC.getContentPane().add(lblEnto);
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(10, 239, 663, 134);
		frameBC.getContentPane().add(scrollPane2);
		
		table2 = new JTable();
		table2.setModel(tableModelBCENTAO);
		table2.setFillsViewportHeight(true);
		table2.setShowGrid(true);
		scrollPane2.setViewportView(table2);
		
	}
	
	private void construtorTableModel() {
		TableColumn coluna1 = table.getColumnModel().getColumn(0);
	    TableColumn coluna2 = table.getColumnModel().getColumn(1);
	    TableColumn coluna3 = table.getColumnModel().getColumn(2);
	    TableColumn coluna4 = table.getColumnModel().getColumn(3);
		JComboBox<Object> comboVariaveis = new JComboBox<Object>();
		JComboBox<Object> comboTermos = new JComboBox<Object>();
		JComboBox<Object> comboConcentracoes = new JComboBox<Object>();
		comboConcentracoes.addItem("");
		comboConcentracoes.addItem("MUITO");
		comboConcentracoes.addItem("ALGO");
		comboConcentracoes.addItem("DE FATO");
		comboConcentracoes.addItem("MUITO MUITO");
		JComboBox<Object> comboTPCondicoes = new JComboBox<Object>();
		comboTPCondicoes.addItem("E");
		comboTPCondicoes.addItem("OU");
		List<Variaveis> variaveis = txt.listaDeVariaveis();
		comboVariaveis.removeAllItems();
		for (Variaveis v : variaveis) {
			comboVariaveis.addItem(v.getNome());
		}
		coluna1.setCellEditor(new DefaultCellEditor(comboVariaveis));
		coluna2.setCellEditor(new DefaultCellEditor(comboConcentracoes));
		coluna3.setCellEditor(new DefaultCellEditor(comboTermos));
		coluna4.setCellEditor(new DefaultCellEditor(comboTPCondicoes));
		table.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				
			}
		});
	}
	
	public void atualizaTermos(JComboBox<Object> comboVariaveis, JComboBox<Object> comboTermos){
//		System.out.println("Entrou no metodo atualizaTermos()");
//		List<Termos> termos = txt.listaDeTermos();
//		String nomeComboVariaveis = comboVariaveis.getItemAt(comboVariaveis.getSelectedIndex()).toString();
//		comboTermos.removeAllItems();
//		for (Termos t : termos) {
//			if(t.getVariavel().getNome().equals(nomeComboVariaveis)){
//				comboTermos.addItem(t.getNome());
//			}
//		}
	}

	public JFrame getFrameBC() {
		return frameBC;
	}
}
