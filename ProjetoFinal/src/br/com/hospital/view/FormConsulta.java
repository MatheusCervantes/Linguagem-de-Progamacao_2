package br.com.hospital.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.hospital.dao.ConsultaDAO;
import br.com.hospital.dao.MedicoDAO;
import br.com.hospital.dao.PessoaDAO;
import br.com.hospital.model.ConsultaDTO;
import br.com.hospital.model.MedicoDTO;
import br.com.hospital.model.ModeloTabelaConsulta;
import br.com.hospital.model.ModeloTabelaMedico;
import br.com.hospital.model.ModeloTabelaPessoa;
import br.com.hospital.model.PessoaDTO;
import br.com.hospital.ctr.GerarRelatorio;

public class FormConsulta extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JTable tablePessoas;
	private JTable tableMedicos;
	private JTable tableConsultas;
	private JTextField formdata;
	private JTextField formhora;
	JTextPane formdescricao;
	private JButton btnCadastrar;
	private JButton btnAlterar;
	private JButton btnRemover;
	private JButton btnSalvar;
	private JButton btnRelatorio;
	private JButton btnCancelar;
	private JButton btnSair;
	private ArrayList<MedicoDTO> medicos = new ArrayList<MedicoDTO>();
	private ArrayList<PessoaDTO> pessoas = new ArrayList<PessoaDTO>();
	private ArrayList<ConsultaDTO> consultas = new ArrayList<ConsultaDTO>();
	private int controle_cadastro = 1;
	private ModeloTabelaPessoa modeloTabelaPessoa;
	private ModeloTabelaMedico modeloTabelaMedico;
	private ModeloTabelaConsulta modeloTabelaConsulta;
	private ConsultaDAO consultaDAO = new ConsultaDAO();
	private MedicoDAO medicoDAO = new MedicoDAO();
	private PessoaDAO pessoaDAO = new PessoaDAO();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormConsulta frame = new FormConsulta();
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
	public FormConsulta() {
		setBounds(100, 100, 798, 648);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(10, 11, 765, 594);
		getContentPane().add(panel);

		JLabel lblNewLabel = new JLabel("Consulta");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblNewLabel.setBounds(303, 5, 159, 44);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Pessoas");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(140, 61, 67, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Médicos");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(140, 333, 67, 14);
		panel.add(lblNewLabel_2);

		formdata = new JTextField();
		formdata.setText("");
		formdata.setColumns(10);
		formdata.setBounds(462, 162, 86, 20);
		panel.add(formdata);

		formdescricao = new JTextPane();
		formdescricao.setBounds(462, 80, 273, 71);
		panel.add(formdescricao);

		JLabel lblNewLabel_3 = new JLabel("Data:");
		lblNewLabel_3.setBounds(427, 165, 46, 14);
		panel.add(lblNewLabel_3);

		formhora = new JTextField();
		formhora.setText("");
		formhora.setColumns(10);
		formhora.setBounds(649, 162, 86, 20);
		panel.add(formhora);

		JLabel lblNewLabel_4 = new JLabel("Hora:");
		lblNewLabel_4.setBounds(616, 165, 33, 14);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Descrição:");
		lblNewLabel_5.setBounds(395, 105, 67, 14);
		panel.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Consultas");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_6.setBounds(538, 236, 79, 14);
		panel.add(lblNewLabel_6);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(462, 483, 257, 100);
		panel.add(panel_1);

		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setEnabled(true);
		panel_1.add(btnCadastrar);

		btnAlterar = new JButton("Alterar");
		btnAlterar.setEnabled(false);
		panel_1.add(btnAlterar);

		btnRemover = new JButton("Remover");
		btnRemover.setEnabled(false);
		panel_1.add(btnRemover);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setEnabled(false);
		panel_1.add(btnSalvar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setEnabled(false);
		panel_1.add(btnCancelar);

		btnSair = new JButton("Sair");
		panel_1.add(btnSair);

		btnRelatorio = new JButton("Gerar Relatório");
		btnRelatorio.setEnabled(true);
		panel_1.add(btnRelatorio);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 75, 322, 234);
		panel.add(scrollPane);

		tablePessoas = new JTable();
		scrollPane.setViewportView(tablePessoas);
		tablePessoas.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "NOME", "CPF" }));

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 352, 322, 231);
		panel.add(scrollPane_1);

		tableMedicos = new JTable();
		scrollPane_1.setViewportView(tableMedicos);
		tableMedicos.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "NOME", "CRM", "CPF" }));

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(439, 253, 296, 212);
		panel.add(scrollPane_2);

		tableConsultas = new JTable();
		scrollPane_2.setViewportView(tableConsultas);
		tableConsultas.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "ID", "PESSOA", "MEDICO", "DATA", "HORA" }));
		tableConsultas.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableMedicos.getColumnModel().getColumn(0).setPreferredWidth(28);
		tablePessoas.getColumnModel().getColumn(0).setPreferredWidth(31);

		modeloTabelaConsulta = new ModeloTabelaConsulta(consultas);
		tableConsultas.setModel(modeloTabelaConsulta);

		tableConsultas.getColumnModel().getColumn(0).setPreferredWidth(31);
		tableConsultas.getColumnModel().getColumn(3).setPreferredWidth(64);

		// Inicio sistema
		botoes(true, false, false, false, false, true);
		limpacampos();
		limpatabelas();
		attTabelaConsulta();
		habilitarcampos(2);

		// Eventos
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controle_cadastro = 1;
				attTabelaMedico();
				attTabelaPessoa();
				botoes(false, false, false, true, true, false);
				limpacampos();
				attdatahora();
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
							consultaDAO.cadastrarConsulta(preencheObjeto());
							limpacampos();
							limpatabelas();
							attTabelaConsulta();
						}
						if (controle_cadastro == 2) {
							consultaDAO.alterarConsulta(
									Integer.parseInt(String
											.valueOf(tableConsultas.getValueAt(tableConsultas.getSelectedRow(), 0))),
									preencheObjeto());
							limpacampos();
							limpatabelas();
							attTabelaConsulta();
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
				limpatabelas();
			}
		});

		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controle_cadastro = 2;
				botoes(false, false, false, true, true, false);
				habilitarcampos(1);
				alterar();
			}
		});

		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					botoes(false, false, false, false, false, false);

					int response = JOptionPane.showConfirmDialog(null, "Deseja Realmente Remover o Registro?",
							"Confirmar", JOptionPane.YES_NO_OPTION);

					if (response == JOptionPane.YES_OPTION) {
						consultaDAO.removerConsulta(Integer.parseInt(
								String.valueOf(tableConsultas.getValueAt(tableConsultas.getSelectedRow(), 0))));
						attTabelaConsulta();
					}
					habilitarcampos(2);
					botoes(true, false, false, false, false, true);
					limpacampos();
					limpatabelas();
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

		btnRelatorio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GerarRelatorio relatorio = new GerarRelatorio();
				try {
					relatorio.gerarRelatorioGeral("Consultas");
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				}
			}
		});

		tableConsultas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ConsultaDTO aux = new ConsultaDTO();
				aux = consultaDAO.pequisaConsulta(Integer
						.parseInt(String.valueOf(tableConsultas.getValueAt(tableConsultas.getSelectedRow(), 0))));
				preenchecampos(aux);
				botoes(false, true, true, false, true, false);
			}
		});

	}

	private void limpacampos() {
		formdata.setText("");
		formhora.setText("");
	}

	private void limpatabelas() {
		tablePessoas.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "NOME", "CPF" }));
		tableMedicos.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "NOME", "CRM", "CPF" }));
	}

	private void habilitarcampos(int fc) {
		if (fc == 1) {
			tableMedicos.setEnabled(true);
			tablePessoas.setEnabled(true);
			formdata.setEnabled(true);
			formhora.setEnabled(true);
			formdescricao.setEnabled(true);
		} else if (fc == 2) {
			tableMedicos.setEnabled(false);
			tablePessoas.setEnabled(false);
			formdata.setEnabled(false);
			formhora.setEnabled(false);
			formdescricao.setEnabled(false);
		}
	}

	private void preenchecampos(ConsultaDTO consultaDTO) {
		formdescricao.setText(consultaDTO.getDescricaoConsulta());
		formdata.setText(consultaDTO.getDataConsulta());
		formhora.setText(consultaDTO.getHoraConsulta());
		attTabelaMedicoPesquisa(consultaDTO.getIdMedico());
		attTabelaPessoaPesquisa(consultaDTO.getIdPessoa());
	}

	private ConsultaDTO preencheObjeto() {
		ConsultaDTO consultaDTO = new ConsultaDTO();
		consultaDTO.setDescricaoConsulta(formdescricao.getText());
		consultaDTO.setDataConsulta(formdata.getText());
		consultaDTO.setHoraConsulta(formhora.getText());
		consultaDTO.setIdMedico(
				Integer.parseInt(String.valueOf(tableMedicos.getValueAt(tableMedicos.getSelectedRow(), 0))));
		consultaDTO.setIdPessoa(
				Integer.parseInt(String.valueOf(tablePessoas.getValueAt(tablePessoas.getSelectedRow(), 0))));
		return consultaDTO;
	}

	private void botoes(boolean a, boolean b, boolean c, boolean d, boolean e, boolean f) {
		btnCadastrar.setEnabled(a);
		btnAlterar.setEnabled(b);
		btnRemover.setEnabled(c);
		btnSalvar.setEnabled(d);
		btnCancelar.setEnabled(e);
		btnRelatorio.setEnabled(f);
	}

	private void attTabelaMedico() {
		MedicoDAO medicoDAO = new MedicoDAO();
		medicos = medicoDAO.pequisarMedicoTabela();
		modeloTabelaMedico = new ModeloTabelaMedico(medicos);
		modeloTabelaMedico.atualizarDados(medicos);
		tableMedicos.setModel(modeloTabelaMedico);
	}

	private void attTabelaPessoa() {
		PessoaDAO pessoaDAO = new PessoaDAO();
		pessoas = pessoaDAO.pequisarPessoaTabela();
		modeloTabelaPessoa = new ModeloTabelaPessoa(pessoas);
		modeloTabelaPessoa.atualizarDados(pessoas);
		tablePessoas.setModel(modeloTabelaPessoa);
	}

	private void attTabelaConsulta() {
		consultas = consultaDAO.pequisarConsultaTabela();
		modeloTabelaConsulta.atualizarDados(consultas);
		tableConsultas.setModel(modeloTabelaConsulta);
	}

	private void attTabelaMedicoPesquisa(int i) {
		ArrayList<MedicoDTO> aux = new ArrayList<MedicoDTO>();
		aux.add(medicoDAO.pequisarMedico(i));
		modeloTabelaMedico = new ModeloTabelaMedico(aux);
		modeloTabelaMedico.atualizarDados(aux);
		tableMedicos.setModel(modeloTabelaMedico);
	}

	private void attTabelaPessoaPesquisa(int i) {
		ArrayList<PessoaDTO> aux = new ArrayList<PessoaDTO>();
		aux.add(pessoaDAO.pequisarPessoa(i));
		modeloTabelaPessoa = new ModeloTabelaPessoa(aux);
		modeloTabelaPessoa.atualizarDados(aux);
		tablePessoas.setModel(modeloTabelaPessoa);
	}

	private void attdatahora() {
		LocalTime horaAtual = LocalTime.now();
		DateTimeFormatter formatohora = DateTimeFormatter.ofPattern("HH:mm:ss");
		formhora.setText(horaAtual.format(formatohora));

		LocalDate dataAtual = LocalDate.now();
		DateTimeFormatter formatodata = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		formdata.setText(dataAtual.format(formatodata));
	}

	private void selecionarlinha(int idMedico, int idPessoa) {
		int columnIndex = 0;

		int rowCountMedicos = tableMedicos.getRowCount();

		for (int rowIndex = 0; rowIndex < rowCountMedicos; rowIndex++) {
			int valorCelula = Integer.parseInt(String.valueOf(tableMedicos.getValueAt(rowIndex, columnIndex)));

			if (valorCelula == idMedico) {
				tableMedicos.setRowSelectionInterval(rowIndex, rowIndex);
			}
		}

		int rowCountPessoas = tablePessoas.getRowCount();

		for (int rowIndex = 0; rowIndex < rowCountPessoas; rowIndex++) {
			int valorCelula = Integer.parseInt(String.valueOf(tablePessoas.getValueAt(rowIndex, columnIndex)));

			if (valorCelula == idPessoa) {
				tablePessoas.setRowSelectionInterval(rowIndex, rowIndex);
			}
		}
	}

	private void alterar() {
		ConsultaDTO aux = new ConsultaDTO();
		aux = consultaDAO.pequisaConsulta(
				Integer.parseInt(String.valueOf(tableConsultas.getValueAt(tableConsultas.getSelectedRow(), 0))));
		preenchecampos(aux);
		attTabelaMedico();
		attTabelaPessoa();
		selecionarlinha(aux.getIdMedico(), aux.getIdPessoa());
	}
}
