package br.com.hospital.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JTable;
import java.awt.TextField;
import java.util.ArrayList;
import java.awt.Label;
import java.awt.Font;
import java.awt.Choice;
import java.awt.Panel;
import javax.swing.table.DefaultTableModel;

import br.com.hospital.dao.PessoaDAO;
import br.com.hospital.model.ModeloTabelaPessoa;
import br.com.hospital.model.PessoaDTO;

import javax.swing.JScrollPane;
import java.awt.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class FormPessoa extends JFrame {

	private JFrame frame;
	private JTable tablePessoas;
	private ArrayList<PessoaDTO> pessoas = new ArrayList<PessoaDTO>();
	private int controle_cadastro;
	private JButton btnCadastrar;
	private JButton btnAlterar;
	private JButton btnRemover;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JButton btnRelatorio;
	private TextField formnome;
	private TextField formcpf;
	private TextField formendereco;
	private TextField formcidade;
	private TextField formtelefone;
	private TextField formnumero;
	private JComboBox formestado;
	private ModeloTabelaPessoa modeloTabelaPessoa;

	PessoaDAO pessoaDAO = new PessoaDAO();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormPessoa window = new FormPessoa();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FormPessoa() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 694, 385);
		frame.setLocationRelativeTo(null); // Adicione esta linha
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(40, 11, 602, 311);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(28, 223, 266, 63);
		panel_1.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnCadastrar = new JButton("Cadastrar");
		panel.add(btnCadastrar);

		btnAlterar = new JButton("Alterar");
		panel.add(btnAlterar);

		btnRemover = new JButton("Remover");
		panel.add(btnRemover);

		btnSalvar = new JButton("Salvar");
		panel.add(btnSalvar);

		btnCancelar = new JButton("Cancelar");
		panel.add(btnCancelar);

		btnRelatorio = new JButton("Gerar relatório");
		btnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(formcidade.getText());
			}
		});
		btnRelatorio.setBounds(380, 270, 126, 23);
		panel_1.add(btnRelatorio);

		Label label = new Label("Pessoa");
		label.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 36));
		label.setBounds(230, 10, 132, 39);
		panel_1.add(label);

		Label label_1 = new Label("Nome:");
		label_1.setBounds(46, 55, 36, 22);
		panel_1.add(label_1);

		Label label_2 = new Label("   CPF:");
		label_2.setBounds(46, 83, 36, 22);
		panel_1.add(label_2);

		Label label_3 = new Label("Endereço:");
		label_3.setBounds(28, 111, 54, 22);
		panel_1.add(label_3);

		Label label_4 = new Label(" Cidade:");
		label_4.setBounds(38, 139, 44, 22);
		panel_1.add(label_4);

		Label label_5 = new Label(" Estado:");
		label_5.setBounds(38, 167, 44, 22);
		panel_1.add(label_5);

		Label label_6 = new Label("Número:");
		label_6.setBounds(159, 167, 54, 22);
		panel_1.add(label_6);

		Label label_7 = new Label(" Telefone:");
		label_7.setBounds(29, 195, 54, 22);
		panel_1.add(label_7);

		Label label_8 = new Label("Pessoas Cadastradas");
		label_8.setFont(new Font("Dialog", Font.BOLD, 16));
		label_8.setBounds(364, 55, 172, 20);
		panel_1.add(label_8);

		formnome = new TextField();
		formnome.setBounds(88, 55, 207, 22);
		panel_1.add(formnome);

		formcpf = new TextField();
		formcpf.setBounds(88, 83, 207, 22);
		panel_1.add(formcpf);

		formendereco = new TextField();
		formendereco.setBounds(88, 111, 207, 22);
		panel_1.add(formendereco);

		formcidade = new TextField();
		formcidade.setBounds(88, 139, 207, 22);
		panel_1.add(formcidade);

		formtelefone = new TextField();
		formtelefone.setBounds(88, 195, 207, 22);
		panel_1.add(formtelefone);

		formnumero = new TextField();
		formnumero.setBounds(210, 167, 84, 22);
		panel_1.add(formnumero);

		formestado = new JComboBox();
		formestado.setModel(new DefaultComboBoxModel(
				new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB",
						"PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		formestado.setBounds(88, 167, 65, 22);
		panel_1.add(formestado);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(315, 75, 266, 184);
		panel_1.add(scrollPane);

		modeloTabelaPessoa = new ModeloTabelaPessoa(pessoas);
		attTabela();
		tablePessoas = new JTable();
		tablePessoas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PessoaDTO aux = new PessoaDTO();
				aux = PessoaDAO.pequisarPessoa(
						Integer.parseInt(String.valueOf(tablePessoas.getValueAt(tablePessoas.getSelectedRow(), 0))));

				preenchecampos(aux);
				botoes(false, true, true, false, true, false);
			}
		});
		scrollPane.setViewportView(tablePessoas);
		tablePessoas.setModel(modeloTabelaPessoa);

		tablePessoas.getColumnModel().getColumn(0).setResizable(false);
		tablePessoas.getColumnModel().getColumn(0).setPreferredWidth(36);
		tablePessoas.getColumnModel().getColumn(1).setPreferredWidth(140);
		tablePessoas.getColumnModel().getColumn(2).setPreferredWidth(130);

		// Sistema iniciando
		habilitarcampos(2);
		botoes(true, false, false, false, false, true);

		// Eventos
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controle_cadastro = 1;
				botoes(false, false, false, true, true, false);
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
						botoes(true, false, false, false, false, true);
						if (controle_cadastro == 1) {
							pessoaDAO.cadastrarPessoa(preencheObjeto());
							limpacampos();
							attTabela();
						}
						if (controle_cadastro == 2) {
							pessoaDAO.alterarPessoa(Integer.parseInt(
									(String.valueOf(tablePessoas.getValueAt(tablePessoas.getSelectedRow(), 0)))),
									preencheObjeto());
							limpacampos();
							attTabela();
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
				botoes(true, false, false, false, false, true);
			}
		});

		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controle_cadastro = 2;
				botoes(false, false, false, true, true, false);
				habilitarcampos(1);
			}
		});

		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					botoes(false, false, false, false, false, false);

					int response = JOptionPane.showConfirmDialog(null, "Deseja Realmente Remover o Registro?", "Confirmar",
							JOptionPane.YES_NO_OPTION);

					if (response == JOptionPane.YES_OPTION) {
						pessoaDAO.removerPessoa(Integer.parseInt(String.valueOf(tablePessoas.getValueAt(tablePessoas.getSelectedRow(),0))));
						attTabela();
					}
					habilitarcampos(2);
					botoes(true, false, false, false, false, true);
					limpacampos();
				} catch (Exception er) {
					System.out.println(er.getMessage());
				}
			}
		});
	}

	private void limpacampos() {
		formnome.setText("");
		formcpf.setText("");
		formendereco.setText("");
		formcidade.setText("");
		formestado.setSelectedItem("AC");
		formnumero.setText("");
		formtelefone.setText("");
	}

	private void habilitarcampos(int fc) {
		if (fc == 1) {
			formnome.setEnabled(true);
			formcpf.setEnabled(true);
			formendereco.setEnabled(true);
			formcidade.setEnabled(true);
			formestado.setEnabled(true);
			formnumero.setEnabled(true);
			formtelefone.setEnabled(true);
		} else if (fc == 2) {
			formnome.setEnabled(false);
			formcpf.setEnabled(false);
			formendereco.setEnabled(false);
			formcidade.setEnabled(false);
			formestado.setEnabled(false);
			formnumero.setEnabled(false);
			formtelefone.setEnabled(false);
		}
	}

	private void preenchecampos(PessoaDTO pessoaDTO) {
		formnome.setText(pessoaDTO.getNomePessoa());
		formcpf.setText(pessoaDTO.getCpfPessoa());
		formendereco.setText(pessoaDTO.getEnderecoPessoa());
		formcidade.setText(pessoaDTO.getCidadePessoa());
		formestado.setSelectedItem(pessoaDTO.getEstadoPessoa());
		formnumero.setText(pessoaDTO.getNumeroPessoa());
		formtelefone.setText(pessoaDTO.getTelefonePessoa());
	}

	private PessoaDTO preencheObjeto() {
		PessoaDTO pessoaDTO = new PessoaDTO();
		pessoaDTO.setNomePessoa(formnome.getText());
		pessoaDTO.setCpfPessoa(formcpf.getText());
		pessoaDTO.setEnderecoPessoa(formendereco.getText());
		pessoaDTO.setCidadePessoa(formcidade.getText());
		pessoaDTO.setEstadoPessoa((String) formestado.getSelectedItem());
		pessoaDTO.setNumeroPessoa(formnumero.getText());
		pessoaDTO.setTelefonePessoa(formtelefone.getText());
		return pessoaDTO;
	}

	private void botoes(boolean a, boolean b, boolean c, boolean d, boolean e, boolean f) {
		btnCadastrar.setEnabled(a);
		btnAlterar.setEnabled(b);
		btnRemover.setEnabled(c);
		btnSalvar.setEnabled(d);
		btnCancelar.setEnabled(e);
		btnRelatorio.setEnabled(f);
	}

	private void attTabela() {
		pessoas = pessoaDAO.pequisarPessoaTabela();
		modeloTabelaPessoa.atualizarDados(pessoas);
	}
}