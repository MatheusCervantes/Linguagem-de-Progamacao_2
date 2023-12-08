package br.com.hospital.view;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import br.com.hospital.ctr.Conexao;
import br.com.hospital.ctr.Criptografia;
import br.com.hospital.view.*;

public class FormPrincipal {
	JMenuItem menuUsarios;
	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormPrincipal window = new FormPrincipal();
					window.frame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setBounds(100, 100, 843, 570);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);

		JMenuItem menuPessoas = new JMenuItem("Pessoa");
		menuPessoas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FormPessoa pessoa = new FormPessoa();
				int centerX = frame.getX() + (frame.getWidth() - pessoa.getWidth()) / 2;
				int centerY = frame.getY() + (frame.getHeight() - pessoa.getHeight()) / 2;
				pessoa.setLocation(centerX, centerY);
				frame.getContentPane().add(pessoa);
				pessoa.setVisible(true);
			}
		});
		mnNewMenu.add(menuPessoas);

		JMenuItem menuMedicos = new JMenuItem("Médico");
		menuMedicos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FormMedico medico = new FormMedico();
				int centerX = frame.getX() + (frame.getWidth() - medico.getWidth()) / 2;
				int centerY = frame.getY() + (frame.getHeight() - medico.getHeight()) / 2;
				medico.setLocation(centerX, centerY);
				frame.getContentPane().add(medico);
				medico.setVisible(true);
			}
		});

		mnNewMenu.add(menuMedicos);

		JMenuItem menuConsultas = new JMenuItem("Consulta");
		menuConsultas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormConsulta consulta = new FormConsulta();
				int centerX = frame.getX() + (frame.getWidth() - consulta.getWidth()) / 2;
				int centerY = frame.getY() + (frame.getHeight() - consulta.getHeight()) / 2;
				consulta.setLocation(centerX, centerY);
				frame.getContentPane().add(consulta);
				consulta.setVisible(true);
			}
		});
		mnNewMenu.add(menuConsultas);

		menuUsarios = new JMenuItem("Usuário");
		menuUsarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormCadastrarLogin cadastrarLogin = new FormCadastrarLogin();
				int centerX = frame.getX() + (frame.getWidth() - cadastrarLogin.getWidth()) / 2;
				int centerY = frame.getY() + (frame.getHeight() - cadastrarLogin.getHeight()) / 2;
				cadastrarLogin.setLocation(centerX, centerY);
				frame.getContentPane().add(cadastrarLogin);
				cadastrarLogin.setVisible(true);
			}
		});
		mnNewMenu.add(menuUsarios);

		JMenu menuBackup = new JMenu("Backup");
		menuBackup.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				FormBackup backup = new FormBackup();
				int centerX = frame.getX() + (frame.getWidth() - backup.getWidth()) / 2;
				int centerY = frame.getY() + (frame.getHeight() - backup.getHeight()) / 2;
				backup.setLocation(centerX, centerY);
				frame.getContentPane().add(backup);
				backup.setVisible(true);
			}
		});
		menuBackup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormBackup backup = new FormBackup();
				int centerX = frame.getX() + (frame.getWidth() - backup.getWidth()) / 2;
				int centerY = frame.getY() + (frame.getHeight() - backup.getHeight()) / 2;
				backup.setLocation(centerX, centerY);
				frame.getContentPane().add(backup);
				backup.setVisible(true);
			}
		});
		menuBar.add(menuBackup);

		JMenu mnNewMenu_2 = new JMenu("Sair");
		mnNewMenu_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "Confirmação",
						JOptionPane.YES_NO_OPTION);
				if (resposta == JOptionPane.YES_OPTION)
					System.exit(0);
			}
		});
		menuBar.add(mnNewMenu_2);
		frame.getContentPane().setLayout(null);

	}

}
