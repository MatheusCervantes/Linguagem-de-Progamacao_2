package br.com.hospital.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import br.com.hospital.dao.CadastrarLoginDAO;
import br.com.hospital.dao.PessoaDAO;
import br.com.hospital.model.CadastrarLoginDTO;
import br.com.hospital.model.MedicoDTO;
import br.com.hospital.model.ModeloTabelaCadastrarLogin;
import br.com.hospital.model.ModeloTabelaMedico;
import br.com.hospital.model.PessoaDTO;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class FormCadastrarLogin extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField formusuario;
	private JPasswordField formsenha;
	private JTextField formnome;
	private JTextField formcpf;
	private JComboBox formtipousuario;
	private JTable tabelaUsuario;
	private JFormattedTextField formdata;
	private JCheckBox btnconfirma;
	private JButton btnCadastrar;
	private JButton btnAlterar;
	private JButton btnRemover;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JButton btnSair;
	private ArrayList<CadastrarLoginDTO> usuarios = new ArrayList<CadastrarLoginDTO>();
	private int controle_cadastro;
	private ModeloTabelaCadastrarLogin modeloTabelaCadastrarLogin;
	private CadastrarLoginDAO cadastrarLoginDAO = new CadastrarLoginDAO();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormCadastrarLogin frame = new FormCadastrarLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormCadastrarLogin() {
		setBounds(100, 100, 738, 342);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(10, 11, 702, 294);
		getContentPane().add(panel);

		JLabel lblNewLabel = new JLabel("Usuário");
		lblNewLabel.setFont(new Font("Sylfaen", Font.BOLD, 36));
		lblNewLabel.setBounds(288, 11, 124, 48);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Usuário:");
		lblNewLabel_1.setBounds(54, 59, 51, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Senha:");
		lblNewLabel_2.setBounds(59, 87, 46, 14);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("  Nome:");
		lblNewLabel_3.setBounds(54, 145, 51, 14);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("CPF:");
		lblNewLabel_4.setBounds(70, 172, 35, 14);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Data Nascimento:");
		lblNewLabel_5.setBounds(0, 200, 105, 14);
		panel.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("   Tipo:");
		lblNewLabel_6.setBounds(59, 120, 46, 14);
		panel.add(lblNewLabel_6);

		formusuario = new JTextField();
		formusuario.setColumns(10);
		formusuario.setBounds(104, 56, 241, 20);
		panel.add(formusuario);

		formsenha = new JPasswordField();
		formsenha.setBounds(104, 84, 241, 20);
		panel.add(formsenha);

		formnome = new JTextField();
		formnome.setColumns(10);
		formnome.setBounds(104, 142, 241, 20);
		panel.add(formnome);

		formtipousuario = new JComboBox();
		formtipousuario.setModel(new DefaultComboBoxModel(new String[] { "COMUM", "ADMIN" }));
		formtipousuario.setBounds(104, 112, 105, 22);
		panel.add(formtipousuario);

		btnconfirma = new JCheckBox("Alterar Senha");
		btnconfirma.setBounds(229, 112, 116, 23);
		panel.add(btnconfirma);

		formcpf = new JTextField();
		formcpf.setColumns(10);
		formcpf.setBounds(104, 169, 241, 20);
		panel.add(formcpf);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(70, 222, 296, 65);
		panel.add(panel_1);

		btnCadastrar = new JButton("Cadastrar");
		panel_1.add(btnCadastrar);

		btnAlterar = new JButton("Alterar");
		panel_1.add(btnAlterar);

		btnRemover = new JButton("Remover");
		panel_1.add(btnRemover);

		btnSalvar = new JButton("Salvar");
		panel_1.add(btnSalvar);

		btnCancelar = new JButton("Cancelar");
		panel_1.add(btnCancelar);

		btnSair = new JButton("Sair");
		panel_1.add(btnSair);

		// Formatar Data
		formdata = createFormattedTextField();
		formdata.setBounds(104, 197, 241, 20);
		panel.add(formdata);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(379, 58, 313, 229);
		panel.add(scrollPane);

		modeloTabelaCadastrarLogin = new ModeloTabelaCadastrarLogin(usuarios);
		tabelaUsuario = new JTable();
		tabelaUsuario.setModel(modeloTabelaCadastrarLogin);
		tabelaUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CadastrarLoginDTO aux = new CadastrarLoginDTO();
				aux = cadastrarLoginDAO.pequisarLogin(
						Integer.parseInt(String.valueOf(tabelaUsuario.getValueAt(tabelaUsuario.getSelectedRow(), 0))));
				habilitarcampos(2);
				preenchecampos(aux);
				botoes(false, true, true, false, true, false, false);
			}
		});
		scrollPane.setViewportView(tabelaUsuario);

		// InicioTela
		habilitarcampos(2);
		botoes(true, false, false, false, false, true, false);
		attTabela();

		// Eventos
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controle_cadastro = 1;
				botoes(false, false, false, true, true, false, false);
				limpacampos();
				habilitarcampos(1);
			}
		});

		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int response = JOptionPane.showConfirmDialog(null, "Deseja Realmente Salvar o Registro?",
							"Confirmar", JOptionPane.YES_NO_OPTION);
					if (response == JOptionPane.YES_OPTION) {
						JOptionPane.getDefaultLocale();
						habilitarcampos(2);
						botoes(true, false, false, false, false, true, false);

						boolean isChecked = btnconfirma.isSelected();
						if (isChecked) {
							botoes(true, false, false, false, false, true, false);
							cadastrarLoginDAO.alterarLoginSenha(Integer.parseInt(
									(String.valueOf(tabelaUsuario.getValueAt(tabelaUsuario.getSelectedRow(), 0)))),
									preencheObjeto());
							limpacampos();
							attTabela();
						} else {
							if (controle_cadastro == 1) {
								cadastrarLoginDAO.cadastrarLogin(preencheObjeto());
								limpacampos();
								attTabela();
							}
							if (controle_cadastro == 2) {
								botoes(true, false, false, false, false, true, false);
								cadastrarLoginDAO.alterarLogin(Integer.parseInt(
										(String.valueOf(tabelaUsuario.getValueAt(tabelaUsuario.getSelectedRow(), 0)))),
										preencheObjeto());
								limpacampos();
								attTabela();
							}
						}
					} else if (response == JOptionPane.NO_OPTION) {

					}
				} catch (SQLException el) {
					System.out.println(el.getMessage());
				}
			}
		});

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpacampos();
				habilitarcampos(2);
				botoes(true, false, false, false, false, true, false);
			}
		});

		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controle_cadastro = 2;
				botoes(false, false, false, true, true, false, true);
				habilitarcampos(1);
			}
		});

		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					botoes(false, false, false, false, false, false, false);

					int response = JOptionPane.showConfirmDialog(null, "Deseja Realmente Remover o Registro?",
							"Confirmar", JOptionPane.YES_NO_OPTION);

					if (response == JOptionPane.YES_OPTION) {
						cadastrarLoginDAO.removerLogin(Integer
								.parseInt(String.valueOf(tabelaUsuario.getValueAt(tabelaUsuario.getSelectedRow(), 0))));
						attTabela();
					}
					habilitarcampos(2);
					botoes(true, false, false, false, false, true, false);
					limpacampos();
				} catch (Exception er) {
					System.out.println(er.getMessage());
				}
			}
		});

		btnSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "Confirmação",
						JOptionPane.YES_NO_OPTION);
				if (resposta == JOptionPane.YES_OPTION)
					dispose();
			}
		});

	}

	private JFormattedTextField createFormattedTextField() {
		JFormattedTextField formdata = new JFormattedTextField();
		formdata.setHorizontalAlignment(SwingConstants.LEFT);

		try {
			MaskFormatter mask = new MaskFormatter("##/##/####");
			mask.install(formdata);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return formdata;
	}

	private void limpacampos() {
		formnome.setText("");
		formcpf.setText("");
		formsenha.setText("");
		formusuario.setText("");
		formtipousuario.setSelectedItem("COMUM");
		formdata.setText("");
	}

	private void habilitarcampos(int fc) {
		if (fc == 1) {
			formnome.setEnabled(true);
			formcpf.setEnabled(true);
			formsenha.setEnabled(true);
			formusuario.setEnabled(true);
			formtipousuario.setEnabled(true);
			formdata.setEnabled(true);
		} else if (fc == 2) {
			formnome.setEnabled(false);
			formcpf.setEnabled(false);
			formsenha.setEnabled(false);
			formusuario.setEnabled(false);
			formtipousuario.setEnabled(false);
			formdata.setEnabled(false);
		}
	}

	private void preenchecampos(CadastrarLoginDTO cadastrarLoginDTO) {
		formnome.setText(cadastrarLoginDTO.getNomeLogin());
		formcpf.setText(cadastrarLoginDTO.getCpfLogin());
		formusuario.setText(cadastrarLoginDTO.getUsuarioLogin());
		formdata.setText(cadastrarLoginDTO.getDtanascLogin());
		formtipousuario.setSelectedItem(cadastrarLoginDTO.getTipousuarioLogin());
	}

	private CadastrarLoginDTO preencheObjeto() {
		CadastrarLoginDTO cadastrarLoginDTO = new CadastrarLoginDTO();
		cadastrarLoginDTO.setNomeLogin(formnome.getText());
		cadastrarLoginDTO.setCpfLogin(formcpf.getText());
		cadastrarLoginDTO.setSenhaLogin(String.valueOf(formsenha.getPassword()));
		cadastrarLoginDTO.setUsuarioLogin(formusuario.getText());
		cadastrarLoginDTO.setTipousuarioLogin((String) formtipousuario.getSelectedItem());
		cadastrarLoginDTO.setDtanascLogin(formdata.getText());
		return cadastrarLoginDTO;
	}

	private void botoes(boolean a, boolean b, boolean c, boolean d, boolean e, boolean f, boolean g) {
		btnCadastrar.setEnabled(a);
		btnAlterar.setEnabled(b);
		btnRemover.setEnabled(c);
		btnSalvar.setEnabled(d);
		btnCancelar.setEnabled(e);
		btnconfirma.setVisible(g);
	}

	private void attTabela() {
		usuarios = cadastrarLoginDAO.pequisarLoginTabela();
		modeloTabelaCadastrarLogin.atualizarDados(usuarios);
	}

}
