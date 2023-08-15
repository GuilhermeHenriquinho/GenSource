package gensource.view.anotations;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaColumn extends JDialog {
	private JLabel lblNewLabel;
	private JTextField txtName;
	private JLabel lblLength;
	private JTextField txtLength;
	private JCheckBox cbNullable;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JCheckBox cbUnique;
	private String anotacao;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaColumn window = new TelaColumn();
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
	public TelaColumn() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setModal(true);
		setTitle("Column");
		getContentPane().setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 236, 193);
		getContentPane().setLayout(null);
		getContentPane().add(getLblNewLabel());
		getContentPane().add(getTxtName());
		getContentPane().add(getLblLength());
		getContentPane().add(getTxtLength());
		getContentPane().add(getCbNullable());
		getContentPane().add(getBtnSalvar());
		getContentPane().add(getBtnCancelar());
		getContentPane().add(getCbUnique());
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Name:");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblNewLabel.setBounds(10, 23, 55, 22);
		}
		return lblNewLabel;
	}
	private JTextField getTxtName() {
		if (txtName == null) {
			txtName = new JTextField();
			txtName.setBounds(72, 24, 129, 22);
			txtName.setColumns(10);
		}
		return txtName;
	}
	private JLabel getLblLength() {
		if (lblLength == null) {
			lblLength = new JLabel("Length:");
			lblLength.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblLength.setBounds(10, 57, 55, 22);
		}
		return lblLength;
	}
	private JTextField getTxtLength() {
		if (txtLength == null) {
			txtLength = new JTextField();
			txtLength.setColumns(10);
			txtLength.setBounds(72, 57, 129, 22);
		}
		return txtLength;
	}
	private JCheckBox getCbNullable() {
		if (cbNullable == null) {
			cbNullable = new JCheckBox("Nullable");
			cbNullable.setBounds(135, 86, 66, 23);
		}
		return cbNullable;
	}
	private JButton getBtnSalvar() {
		if (btnSalvar == null) {
			btnSalvar = new JButton("Salvar");
			btnSalvar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String anot = "@Column(";
					if (txtName.getText().length() > 0) {
					    anot += "name = \"" + txtName.getText() + "\"";
					}
					
					if (txtLength.getText().length() > 0) {
					    if (anot.endsWith("\"") ) {
					        anot += ", ";
					    }
					    anot += "length = " + txtLength.getText();
					}

					if (cbUnique.isSelected()) {
					    if (anot.endsWith("\"") || anot.endsWith(", ") || !anot.endsWith("(")) {
					        anot += ", ";
					    }
					    anot += "unique = true";
					}

					if (cbNullable.isSelected()) {
					    if (anot.endsWith("\"") || anot.endsWith(", ") || !anot.endsWith("(")) {
					        anot += ", ";
					    }
					    anot += "nullable = true";
					}

					anot += ")";

					
					setAnotacao(anot);
					dispose();
					
				}
			});
			btnSalvar.setBounds(112, 116, 89, 30);
		}
		return btnSalvar;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnCancelar.setBounds(13, 116, 89, 30);
		}
		return btnCancelar;
	}
	private JCheckBox getCbUnique() {
		if (cbUnique == null) {
			cbUnique = new JCheckBox("Unique");
			cbUnique.setBounds(55, 86, 72, 23);
		}
		return cbUnique;
	}

	public String getAnotacao() {
		return anotacao;
	}

	public void setAnotacao(String anotacao) {
		this.anotacao = anotacao;
	}
}
