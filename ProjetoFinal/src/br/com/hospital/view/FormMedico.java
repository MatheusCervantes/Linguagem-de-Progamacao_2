package br.com.hospital.view;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Label;
import java.awt.Font;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import br.com.hospital.ctr.GerarRelatorio;
import br.com.hospital.dao.MedicoDAO;
import br.com.hospital.model.MedicoDTO;
import br.com.hospital.model.ModeloTabelaMedico;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;

public class FormMedico extends JInternalFrame {
	private ArrayList<MedicoDTO> medicos = new ArrayList<MedicoDTO>();
	private int controle_cadastro;
	private JButton btnCadastrar;
	private JButton btnAlterar;
	private JButton btnRemover;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JButton btnRelatorio;
	private JButton btnSair;
	private TextField formnome;
	private TextField formcpf;
	private TextField formendereco;
	private TextField formcidade;
	private TextField formtelefone;
	private TextField formnumero;
	private TextField formcrm;
	private JComboBox formestado;
	private ModeloTabelaMedico modeloTabelaMedico;
	private MedicoDAO medicoDAO = new MedicoDAO();
	private static final long serialVersionUID = 1L;
	private JTable tableMedicos;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormMedico frame = new FormMedico();
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

	public FormMedico() {
		setBounds(100, 100, 631, 378);
		getContentPane().setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(10, 11, 602, 324);
		getContentPane().add(panel_1);

		JPanel panel = new JPanel();
		panel.setBounds(28, 250, 266, 63);
		panel_1.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setEnabled(true);
		panel.add(btnCadastrar);

		btnAlterar = new JButton("Alterar");
		btnAlterar.setEnabled(false);
		panel.add(btnAlterar);

		btnRemover = new JButton("Remover");
		btnRemover.setEnabled(false);
		panel.add(btnRemover);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setEnabled(false);
		panel.add(btnSalvar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setEnabled(false);
		panel.add(btnCancelar);

		btnSair = new JButton("Sair");
		panel.add(btnSair);

		btnRelatorio = new JButton("Gerar relatório");
		btnRelatorio.setEnabled(true);
		btnRelatorio.setBounds(380, 290, 126, 23);
		panel_1.add(btnRelatorio);

		Label label = new Label("Médico");
		label.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 36));
		label.setBounds(238, 10, 132, 39);
		panel_1.add(label);

		Label label_1 = new Label("Nome:");
		label_1.setBounds(46, 55, 36, 22);
		panel_1.add(label_1);

		Label label_2 = new Label("   CPF:");
		label_2.setBounds(46, 83, 36, 22);
		panel_1.add(label_2);

		Label label_3 = new Label("Endereço:");
		label_3.setBounds(28, 138, 54, 22);
		panel_1.add(label_3);

		Label label_4 = new Label(" Cidade:");
		label_4.setBounds(38, 166, 44, 22);
		panel_1.add(label_4);

		Label label_5 = new Label(" Estado:");
		label_5.setBounds(38, 194, 44, 22);
		panel_1.add(label_5);

		Label label_6 = new Label("Número:");
		label_6.setBounds(159, 194, 54, 22);
		panel_1.add(label_6);

		Label label_7 = new Label(" Telefone:");
		label_7.setBounds(28, 222, 54, 22);
		panel_1.add(label_7);

		Label label_8 = new Label("Médicos Cadastradas");
		label_8.setFont(new Font("Dialog", Font.BOLD, 16));
		label_8.setBounds(364, 55, 172, 20);
		panel_1.add(label_8);

		Label lblNewLabel = new Label("              CRM:");
		lblNewLabel.setBounds(10, 114, 72, 14);
		panel_1.add(lblNewLabel);

		formnome = new TextField();
		formnome.setBounds(88, 55, 207, 22);
		panel_1.add(formnome);

		formcpf = new TextField();
		formcpf.setBounds(88, 83, 207, 22);
		panel_1.add(formcpf);

		formendereco = new TextField();
		formendereco.setBounds(87, 138, 207, 22);
		panel_1.add(formendereco);

		formcidade = new TextField();
		formcidade.setBounds(87, 166, 207, 22);
		panel_1.add(formcidade);

		formtelefone = new TextField();
		formtelefone.setBounds(88, 222, 207, 22);
		panel_1.add(formtelefone);

		formnumero = new TextField();
		formnumero.setBounds(210, 194, 84, 22);
		panel_1.add(formnumero);

		formestado = new JComboBox();
		formestado.setModel(new DefaultComboBoxModel(
				new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB",
						"PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		formestado.setBounds(88, 194, 65, 22);
		panel_1.add(formestado);

		formcrm = new TextField();
		formcrm.setColumns(10);
		formcrm.setBounds(88, 111, 206, 20);
		panel_1.add(formcrm);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(301, 83, 291, 196);
		panel_1.add(scrollPane);

		modeloTabelaMedico = new ModeloTabelaMedico(medicos);
		attTabela();
		tableMedicos = new JTable();
		scrollPane.setViewportView(tableMedicos);
		tableMedicos.setModel(modeloTabelaMedico);
		tableMedicos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MedicoDTO aux = new MedicoDTO();
				aux = MedicoDAO.pequisarMedico(
						Integer.parseInt(String.valueOf(tableMedicos.getValueAt(tableMedicos.getSelectedRow(), 0))));

				preenchecampos(aux);
				botoes(false, true, true, false, true, false);
			}
		});

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
							medicoDAO.cadastrarMedico(preencheObjeto());
							limpacampos();
							attTabela();
						}
						if (controle_cadastro == 2) {
							medicoDAO.alterarMedico(Integer.parseInt(
									(String.valueOf(tableMedicos.getValueAt(tableMedicos.getSelectedRow(), 0)))),
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

					int response = JOptionPane.showConfirmDialog(null, "Deseja Realmente Remover o Registro?",
							"Confirmar", JOptionPane.YES_NO_OPTION);

					if (response == JOptionPane.YES_OPTION) {
						medicoDAO.removerMedico(Integer
								.parseInt(String.valueOf(tableMedicos.getValueAt(tableMedicos.getSelectedRow(), 0))));
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
					relatorio.gerarRelatorioGeral("Medicos");
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
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
		formcrm.setText("");
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
			formcrm.setEnabled(true);
		} else if (fc == 2) {
			formnome.setEnabled(false);
			formcpf.setEnabled(false);
			formendereco.setEnabled(false);
			formcidade.setEnabled(false);
			formestado.setEnabled(false);
			formnumero.setEnabled(false);
			formtelefone.setEnabled(false);
			formcrm.setEnabled(false);
		}
	}

	private void preenchecampos(MedicoDTO medicoDTO) {
		formnome.setText(medicoDTO.getNomeMedico());
		formcpf.setText(medicoDTO.getCpfMedico());
		formendereco.setText(medicoDTO.getEnderecoMedico());
		formcidade.setText(medicoDTO.getCidadeMedico());
		formestado.setSelectedItem(medicoDTO.getEstadoMedico());
		formnumero.setText(medicoDTO.getNumeroMedico());
		formtelefone.setText(medicoDTO.getTelefoneMedico());
		formcrm.setText(medicoDTO.getCrmMedico());
	}

	private MedicoDTO preencheObjeto() {
		MedicoDTO medicoDTO = new MedicoDTO();
		medicoDTO.setNomeMedico(formnome.getText());
		medicoDTO.setCpfMedico(formcpf.getText());
		medicoDTO.setEnderecoMedico(formendereco.getText());
		medicoDTO.setCidadeMedico(formcidade.getText());
		medicoDTO.setEstadoMedico((String) formestado.getSelectedItem());
		medicoDTO.setNumeroMedico(formnumero.getText());
		medicoDTO.setTelefoneMedico(formtelefone.getText());
		medicoDTO.setCrmMedico(formcrm.getText());
		return medicoDTO;
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
		medicos = medicoDAO.pequisarMedicoTabela();
		modeloTabelaMedico.atualizarDados(medicos);
	}
}
