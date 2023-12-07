package br.com.hospital.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import br.com.hospital.dao.*;
import br.com.hospital.model.*;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FormConsulta {
	private JFrame frame;
	private JTable tablePessoas;
	private JTable tableMedicos;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JTextField formdata;
	private JTextField formhora;
	private JTextPane formdescricao;
	private JTable tableConsultas;
	private JButton btnCadastrar;
	private JButton btnAlterar;
	private JButton btnRemover;
	private JButton btnSalvar;
	private JButton btnRelatorio;
	private JButton btnCancelar;
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

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormConsulta window = new FormConsulta();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormConsulta() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 804, 657);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 765, 594);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Consulta");
		lblNewLabel.setBounds(303, 5, 159, 44);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
		panel.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Pessoas");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(140, 61, 67, 14);
		panel.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("Médicos");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(140, 333, 67, 14);
		panel.add(lblNewLabel_2);

		formdescricao = new JTextPane();
		formdescricao.setBounds(462, 75, 273, 76);
		panel.add(formdescricao);

		formdata = new JTextField();
		formdata.setBounds(462, 162, 86, 20);
		panel.add(formdata);
		formdata.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Data:");
		lblNewLabel_3.setBounds(427, 165, 46, 14);
		panel.add(lblNewLabel_3);

		formhora = new JTextField();
		formhora.setBounds(649, 162, 86, 20);
		panel.add(formhora);
		formhora.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Hora:");
		lblNewLabel_4.setBounds(616, 165, 33, 14);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Descrição:");
		lblNewLabel_5.setBounds(395, 105, 67, 14);
		panel.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Consultas");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_6.setBounds(543, 236, 79, 14);
		panel.add(lblNewLabel_6);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(463, 483, 257, 100);
		panel.add(panel_1);

		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_1.add(btnCadastrar);

		btnAlterar = new JButton("Alterar");
		panel_1.add(btnAlterar);

		btnRemover = new JButton("Remover");
		panel_1.add(btnRemover);

		btnSalvar = new JButton("Salvar");
		panel_1.add(btnSalvar);

		btnCancelar = new JButton("Cancelar");
		panel_1.add(btnCancelar);

		btnRelatorio = new JButton("Gerar Relatório");
		panel_1.add(btnRelatorio);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 75, 289, 230);
		panel.add(scrollPane);

		tablePessoas = new JTable();
		tablePessoas.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "NOME", "CPF" }));
		tablePessoas.getColumnModel().getColumn(0).setPreferredWidth(30);
		scrollPane.setViewportView(tablePessoas);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(36, 349, 289, 234);
		panel.add(scrollPane_1);

		tableMedicos = new JTable();
		tableMedicos.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "NOME", "CRM", "CPF" }));
		tableMedicos.getColumnModel().getColumn(0).setPreferredWidth(33);
		scrollPane_1.setViewportView(tableMedicos);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(446, 254, 289, 218);
		panel.add(scrollPane_2);

		tableConsultas = new JTable();

		scrollPane_2.setViewportView(tableConsultas);

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
		formdescricao.setText("");
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
		aux = consultaDAO.pequisaConsulta(Integer
				.parseInt(String.valueOf(tableConsultas.getValueAt(tableConsultas.getSelectedRow(), 0))));
		preenchecampos(aux);
		attTabelaMedico();
		attTabelaPessoa();
		selecionarlinha(aux.getIdMedico(), aux.getIdPessoa());
	}
}
