package com.difusos.cadastros;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import com.difusos.bc.BC;

public class Main {
	
	private JFrame mainFrame;
	Path pathPrincipal;
	Path pathVariaveis;
	Path pathTermos;
	Path pathBC;
	
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
		
		JMenuItem mntmBc = new JMenuItem("BC");
		mntmBc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BC bc = new BC();
				mainFrame.dispose();
				bc.getFrameBC().setVisible(true);
			}
		});
		mnCadastros.add(mntmBc);
		
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
		try {
			pathPrincipal = Paths.get(System.getenv("APPDATA")+"/DifusosEvandro");
			pathVariaveis = Paths.get(pathPrincipal.toFile().getAbsolutePath()+"/variaveis.txt");
			pathTermos = Paths.get(pathPrincipal.toFile().getAbsolutePath()+"/termos.txt");
			pathBC = Paths.get(pathPrincipal.toFile().getAbsolutePath()+"/bc.txt");
			if (!pathPrincipal.toFile().exists()) {
				pathPrincipal.toFile().mkdirs();
			}
			if (!pathVariaveis.toFile().exists()) {
				FileWriter arq = new FileWriter(pathVariaveis.toFile().getAbsolutePath());
				arq.close();
			}
			if (!pathTermos.toFile().exists()) {
				FileWriter arq = new FileWriter(pathTermos.toFile().getAbsolutePath());
				arq.close();
			}
			if (!pathBC.toFile().exists()) {
				FileWriter arq = new FileWriter(pathBC.toFile().getAbsolutePath());
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
