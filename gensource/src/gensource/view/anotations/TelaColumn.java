package gensource.view.anotations;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class TelaColumn extends JFrame{
	private JLabel lblNewLabel;
	private JTextField textField;
	private JLabel lblLength;
	private JTextField textField_1;
	private JCheckBox chckbxNewCheckBox;
	private JButton btnSalvar;
	private JButton btnCancelar;

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
		setTitle("Column");
		getContentPane().setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 236, 202);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().add(getLblNewLabel());
		getContentPane().add(getTextField());
		getContentPane().add(getLblLength());
		getContentPane().add(getTextField_1());
		getContentPane().add(getChckbxNewCheckBox());
		getContentPane().add(getBtnSalvar());
		getContentPane().add(getBtnCancelar());
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Name:");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblNewLabel.setBounds(10, 23, 55, 22);
		}
		return lblNewLabel;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setBounds(72, 24, 129, 22);
			textField.setColumns(10);
		}
		return textField;
	}
	private JLabel getLblLength() {
		if (lblLength == null) {
			lblLength = new JLabel("Length:");
			lblLength.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblLength.setBounds(10, 57, 55, 22);
		}
		return lblLength;
	}
	private JTextField getTextField_1() {
		if (textField_1 == null) {
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(72, 57, 129, 22);
		}
		return textField_1;
	}
	private JCheckBox getChckbxNewCheckBox() {
		if (chckbxNewCheckBox == null) {
			chckbxNewCheckBox = new JCheckBox("Nullable");
			chckbxNewCheckBox.setBounds(121, 86, 80, 23);
		}
		return chckbxNewCheckBox;
	}
	private JButton getBtnSalvar() {
		if (btnSalvar == null) {
			btnSalvar = new JButton("Salvar");
			btnSalvar.setBounds(112, 122, 89, 30);
		}
		return btnSalvar;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setBounds(13, 122, 89, 30);
		}
		return btnCancelar;
	}
}
