package com.difusos.bc;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.table.TableColumn;

import com.difusos.cadastros.BuscarDadosTxt;

public class BC {

	private JFrame frameBC;
	private JTable table;
	private JComboBox<Object> comboVariaveis;
	private JComboBox<Object> comboTermos;
	private JComboBox<Object> comboConcentracoes;
	private JComboBox<Object> comboTPCondicoes;

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
	}

	private void initialize() {
		frameBC = new JFrame();
		frameBC.setTitle("Base de Conhecimento");
		frameBC.setBounds(100, 100, 691, 333);
		frameBC.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameBC.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 663, 134);
		frameBC.getContentPane().add(scrollPane);
		
		table = new JTable(new TableModelBC());
		table.setFillsViewportHeight(true);
		table.setShowGrid(true);
	    TableColumn coluna1 = table.getColumnModel().getColumn(0);
	    TableColumn coluna2 = table.getColumnModel().getColumn(1);
	    TableColumn coluna3 = table.getColumnModel().getColumn(2);
	    TableColumn coluna4 = table.getColumnModel().getColumn(3);
	    BuscarDadosTxt b = new BuscarDadosTxt();
		String[] variaveis = b.listaDeNomesVariaveis();
		comboVariaveis = new JComboBox<Object>();
		comboTermos = new JComboBox<Object>();
		comboConcentracoes = new JComboBox<Object>();
		comboConcentracoes.addItem("");
		comboConcentracoes.addItem("MUITO");
		comboConcentracoes.addItem("ALGO");
		comboConcentracoes.addItem("DE FATO");
		comboConcentracoes.addItem("MUITO MUITO");
		comboTPCondicoes = new JComboBox<Object>();
		comboTPCondicoes.addItem("E");
		comboTPCondicoes.addItem("OU");
		for (int i = 0; i < variaveis.length; i++) {
			comboVariaveis.addItem(variaveis[i]);
		}
		coluna1.setCellEditor(new DefaultCellEditor(comboVariaveis));
		coluna2.setCellEditor(new DefaultCellEditor(comboConcentracoes));
		coluna3.setCellEditor(new DefaultCellEditor(comboTermos));
		coluna4.setCellEditor(new DefaultCellEditor(comboTPCondicoes));
		
		
		//TODO ADICIONAR ACIMA
		scrollPane.setViewportView(table);
	}

	public JFrame getFrameBC() {
		return frameBC;
	}
	
	public void inserirTermos(String variavel) {
		System.out.println("entrou");
		BuscarDadosTxt b = new BuscarDadosTxt();
		String[] termos = b.listaDeNomesTermos(variavel);
		comboTermos = new JComboBox<Object>();
		for (int i = 0; i < termos.length; i++) {
			comboTermos.addItem(termos[i]);
		}
		new ChecarComSwingWorker();
	}
	
	class ChecarComSwingWorker implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            final int totalRows = table.getModel().getRowCount();
            SwingWorker<Object, Integer> worker = new SwingWorker<Object, Integer>() {
                @Override
                protected Object doInBackground() {
                    for (int i = 0; i < totalRows; i++) {
                        try {
                        	inserirTermos("Temperatura");
                            publish(i);
                            Thread.sleep(400);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    return null;
                }

            };
            worker.execute();
        }

    }
	
}
