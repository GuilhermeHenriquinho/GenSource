package gensource.view.anotations;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class TelaColumn extends JFrame{
	private JLabel lblNewLabel;
	private JTextField textField;
	private JLabel lblLength;
	private JTextField textField_1;
	private JCheckBox chckbxNewCheckBox;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JTextField textField_2;
	private JLabel lblA;
	private JCheckBox chckbxUnique;

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
		setBounds(100, 100, 236, 193);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().add(getLblNewLabel());
		getContentPane().add(getTextField());
		getContentPane().add(getLblLength());
		getContentPane().add(getTextField_1());
		getContentPane().add(getChckbxNewCheckBox());
		getContentPane().add(getBtnSalvar());
		getContentPane().add(getBtnCancelar());
		getContentPane().add(getTextField_2());
		getContentPane().add(getLblA());
		getContentPane().add(getChckbxUnique());
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
			textField_1.setBounds(72, 57, 55, 22);
		}
		return textField_1;
	}
	private JCheckBox getChckbxNewCheckBox() {
		if (chckbxNewCheckBox == null) {
			chckbxNewCheckBox = new JCheckBox("Nullable");
			chckbxNewCheckBox.setBounds(135, 86, 66, 23);
		}
		return chckbxNewCheckBox;
	}
	private JButton getBtnSalvar() {
		if (btnSalvar == null) {
			btnSalvar = new JButton("Salvar");
			btnSalvar.setBounds(112, 116, 89, 30);
		}
		return btnSalvar;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setBounds(13, 116, 89, 30);
		}
		return btnCancelar;
	}
	private JTextField getTextField_2() {
		if (textField_2 == null) {
			textField_2 = new JTextField();
			textField_2.setColumns(10);
			textField_2.setBounds(145, 57, 56, 22);
		}
		return textField_2;
	}
	private JLabel getLblA() {
		if (lblA == null) {
			lblA = new JLabel("a");
			lblA.setHorizontalAlignment(SwingConstants.CENTER);
			lblA.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblA.setBounds(125, 57, 22, 22);
		}
		return lblA;
	}
	private JCheckBox getChckbxUnique() {
		if (chckbxUnique == null) {
			chckbxUnique = new JCheckBox("Unique");
			chckbxUnique.setBounds(55, 86, 72, 23);
		}
		return chckbxUnique;
	}
}
