package br.com.hospital.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.com.hospital.ctr.Backup;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FormBackup extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ArrayList<String> arquivosBackup;
	private Backup backup;
	private String[] nomesBackup;
	private String itemSelecionado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormBackup frame = new FormBackup();
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
	public FormBackup() {
		setBounds(100, 100, 809, 328);
		getContentPane().setLayout(null);

		// Insirir backups existentes
		backup = new Backup();
		arquivosBackup = new ArrayList<>();
		arquivosBackup = backup.listarArquivos();
		nomesBackup = arquivosBackup.toArray(new String[arquivosBackup.size()]);

		JPanel contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBounds(10, 11, 787, 284);
		getContentPane().add(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 741, 226);
		contentPane.add(scrollPane);

		JList list = new JList();
		// Listar Backups
		list.setListData(nomesBackup);

		scrollPane.setViewportView(list);

		JButton btnGerarBackup = new JButton("Gerar Backup");
		btnGerarBackup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Deseja gerar o backup? ", "Confirmação",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					backup.gerarBackup();
					arquivosBackup = backup.listarArquivos();
					nomesBackup = arquivosBackup.toArray(new String[arquivosBackup.size()]);
					list.setListData(nomesBackup);
					revalidate();
					repaint();
				}
			}
		});
		btnGerarBackup.setBounds(10, 250, 117, 23);
		contentPane.add(btnGerarBackup);

		JButton btnRestaurarBackup = new JButton("Restaurar Backup");
		btnRestaurarBackup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "Confirmação",
						JOptionPane.YES_NO_OPTION);
				if (resposta == JOptionPane.YES_OPTION) {
					try {
						backup.restaurarBackup(itemSelecionado);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnRestaurarBackup.setBounds(137, 250, 154, 23);
		contentPane.add(btnRestaurarBackup);

		JButton btnSair = new JButton("Sair");
		btnSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "Confirmação",
						JOptionPane.YES_NO_OPTION);
				if (resposta == JOptionPane.YES_OPTION)
					dispose();
			}
		});
		btnSair.setBounds(301, 250, 89, 23);
		contentPane.add(btnSair);

		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					if (list.getSelectedIndex() == -1) {
						list.setSelectedIndex(e.getFirstIndex());
					}
					itemSelecionado = ((JList<String>) e.getSource()).getSelectedValue();
					if (itemSelecionado != null) {
						btnRestaurarBackup.setEnabled(true);
					}
				}
			}
		});

	}
}
