package br.com.hospital.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.hospital.ctr.Conexao;
import br.com.hospital.ctr.Criptografia;
import br.com.hospital.dao.UsuarioDAO;
import br.com.hospital.model.UsuarioDTO;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.Window;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormLogin extends JFrame {

	// Sistema de Login Criptografado com SHA2-256
	static JFrame frame1;
	private static final long serialVersionUID = 1L;
	static JPanel contentPane;
	private static JTextField txtuser;
	private static JPasswordField passwordField;
	static String senhaout;
	static String senha;
	static String usuario;
	static MessageDigest algorithm = null;
	static byte messageDigest[] = null;
	static StringBuilder hexString;
	private static UsuarioDTO usuarioBanco;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormLogin frame = new FormLogin();
					frame1 = frame;
					frame1.setVisible(true);
					frame1.setTitle("Clínica Versão Beta");
					frame1.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Criando frame Login
	public FormLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(-6, -37, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(79, 45, 284, 169);
		panel.setBackground(new Color(240, 240, 240));
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Bem Vindo");
		lblNewLabel.setBounds(99, 27, 104, 17);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));

		JButton btnLogin = new JButton("Entrar");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtuser.getText() != null && !txtuser.getText().isEmpty() && passwordField.getText() != null
						&& !passwordField.getText().isEmpty()) {
					ChecarLogin();
				} else {
					JOptionPane.showMessageDialog(btnLogin, "Verifique as informações!", "Aviso",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setBackground(new Color(0, 0, 0));

		btnLogin.setBounds(111, 135, 80, 23);
		panel.add(btnLogin);
		txtuser = new JTextField();
		txtuser.setBounds(111, 66, 115, 20);
		panel.add(txtuser);
		txtuser.setColumns(10);

		JLabel lblUsurio = new JLabel("Usuário:");
		lblUsurio.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUsurio.setBounds(54, 69, 47, 15);
		panel.add(lblUsurio);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSenha.setBounds(60, 95, 41, 15);
		panel.add(lblSenha);

		passwordField = new JPasswordField();
		passwordField.setBounds(111, 92, 115, 20);
		panel.add(passwordField);
		passwordField.setBackground(new Color(255, 255, 255));
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER) {
					ChecarLogin();
				}
			}
		});
	}

	public static void ChecarLogin() {
		senha = new String(passwordField.getPassword());
		usuario = new String(txtuser.getText());

		Criptografia criptografia = new Criptografia(senha, "SHA-256");
		senhaout = criptografia.criptografar();

		try {
			usuarioBanco = UsuarioDAO.consultarUsuario(usuario, senhaout);
			if (usuarioBanco != null) {
				if (usuarioBanco.getTipoUsuario().equals("ADMIN")) {
					FormPrincipal principal = new FormPrincipal();
					principal.menuUsarios.setVisible(true);
					principal.frame.setVisible(true);
					frame1.setVisible(false);
				} else {
					FormPrincipal principal = new FormPrincipal();
					principal.menuUsarios.setVisible(false);
					principal.frame.setVisible(true);
					frame1.setVisible(false);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Usuário ou Senha Incorretos!");
				txtuser.setText("");
				passwordField.setText("");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao verificar login.");
		}
	}
}