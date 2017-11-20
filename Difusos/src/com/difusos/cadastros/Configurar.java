package com.difusos.cadastros;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.UIManager;

import com.difusos.util.BuscarDadosTxt;
import com.difusos.util.PreencherDadosTxt;

import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Configurar {
	
	private JFrame confiugurarFrame;
	private BuscarDadosTxt txtBusca = new BuscarDadosTxt();
	private PreencherDadosTxt txtPreenche = new PreencherDadosTxt();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					Configurar window = new Configurar();
					window.confiugurarFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Configurar() {
		initialize();
	}

	private void initialize() {
		confiugurarFrame = new JFrame();
		confiugurarFrame.setTitle("Configurações");
		confiugurarFrame.setBounds(100, 100, 420, 295);
		confiugurarFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		confiugurarFrame.getContentPane().setLayout(null);
		
		JLabel lblTipoDeAgregao = new JLabel("Tipo de Agrega\u00E7\u00E3o:");
		lblTipoDeAgregao.setBounds(10, 11, 107, 14);
		confiugurarFrame.getContentPane().add(lblTipoDeAgregao);
		
		
		final JRadioButton rbMinimo = new JRadioButton("M\u00EDnima");
		rbMinimo.setBounds(6, 32, 109, 23);
		confiugurarFrame.getContentPane().add(rbMinimo);
		
		JRadioButton rbMaximo = new JRadioButton("M\u00E1xima");
		rbMaximo.setBounds(117, 32, 109, 23);
		confiugurarFrame.getContentPane().add(rbMaximo);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rbMinimo);
		bg.add(rbMaximo);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtPreenche.preencheConfiguracao(rbMinimo.isSelected());				
				Main m = new Main();
				confiugurarFrame.dispose();
				m.getMainFrame().setVisible(true);
			}
		});
		btnVoltar.setBounds(232, 32, 89, 23);
		confiugurarFrame.getContentPane().add(btnVoltar);
		
		try {
			if(txtBusca.getAgregacaoConfigMinimo()){
				rbMinimo.setSelected(true);
			}else{
				rbMaximo.setSelected(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public JFrame getConfiugurarFrame() {
		return confiugurarFrame;
	}
}
