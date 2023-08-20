package gensource.view.anotations;

import java.awt.EventQueue;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaJoinColumn extends JDialog{

	private JLabel lblNewLabel;
	private JTextField txtNome;
	private JButton btnCancelar;
	private JButton btnSalvar;
	private String anotacao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaJoinColumn window = new TelaJoinColumn();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaJoinColumn() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setModal(true);
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		getContentPane().add(getLblNewLabel());
		getContentPane().add(getTxtNome());
		getContentPane().add(getBtnCancelar());
		getContentPane().add(getBtnSalvar());
		setTitle("JoinColumn");
		setBounds(100, 100, 259, 167);
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Name:");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblNewLabel.setBounds(20, 28, 55, 22);
		}
		return lblNewLabel;
	}
	private JTextField getTxtNome() {
		if (txtNome == null) {
			txtNome = new JTextField();
			txtNome.setColumns(10);
			txtNome.setBounds(82, 29, 129, 22);
		}
		return txtNome;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnCancelar.setBounds(20, 74, 89, 30);
		}
		return btnCancelar;
	}
	private JButton getBtnSalvar() {
		if (btnSalvar == null) {
			btnSalvar = new JButton("Salvar");
			btnSalvar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if(txtNome.getText().length() > 0) {
						String anot = "@JoinColumn(name = \""+txtNome.getText()+"\")";
						setAnotacao(anot);
						dispose();
					}else {
						JOptionPane.showMessageDialog(null, "Para salvar é necessário informar Name!");
					}
					
				}
			});
			btnSalvar.setBounds(119, 74, 89, 30);
		}
		return btnSalvar;
	}

	public String getAnotacao() {
		return anotacao;
	}

	public void setAnotacao(String retorno) {
		this.anotacao = retorno;
	}
}
