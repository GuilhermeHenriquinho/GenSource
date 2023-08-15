package gensource.view.anotations;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TelaGeneratedValue extends JDialog {
	private JLabel lblNewLabel;
	private JComboBox cbGenerationType;
	private JLabel lblGenerator;
	private JTextField txtGenerator;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private String anotacao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaGeneratedValue window = new TelaGeneratedValue();
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
	public TelaGeneratedValue() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setModal(true);
		getContentPane().setBackground(new Color(255, 255, 255));
		setTitle("GeneratedValue");
		setBounds(100, 100, 286, 170);
		getContentPane().setLayout(null);
		getContentPane().add(getLblNewLabel());
		getContentPane().add(getCbGenerationType());
		getContentPane().add(getLblGenerator());
		getContentPane().add(getTxtGenerator());
		getContentPane().add(getBtnSalvar());
		getContentPane().add(getBtnCancelar());
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("GenerationType:");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblNewLabel.setBounds(10, 23, 103, 19);
		}
		return lblNewLabel;
	}
	private JComboBox getCbGenerationType() {
		if (cbGenerationType == null) {
			cbGenerationType = new JComboBox();
			cbGenerationType.setModel(new DefaultComboBoxModel(new String[] {"", "AUTO", "IDENTITY", "TABLE", "SEQUENCE"}));
			cbGenerationType.setFont(new Font("Tahoma", Font.PLAIN, 13));
			cbGenerationType.setBounds(111, 23, 138, 24);
		}
		return cbGenerationType;
	}
	private JLabel getLblGenerator() {
		if (lblGenerator == null) {
			lblGenerator = new JLabel("Generator:");
			lblGenerator.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblGenerator.setBounds(10, 53, 103, 18);
		}
		return lblGenerator;
	}
	private JTextField getTxtGenerator() {
		if (txtGenerator == null) {
			txtGenerator = new JTextField();
			txtGenerator.setBounds(111, 53, 138, 23);
			txtGenerator.setColumns(10);
		}
		return txtGenerator;
	}
	private JButton getBtnSalvar() {
		if (btnSalvar == null) {
			btnSalvar = new JButton("Salvar");
			btnSalvar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String anot = "@GeneratedValue(strategy = GenerationType.";
					String generatedValue = cbGenerationType.getSelectedItem().toString();
					
					if(generatedValue.length() > 0) {
						anot += generatedValue;
					}
					
					if(txtGenerator.getText().length() > 0) {
						if(generatedValue.length() > 0) {
							anot += ", ";
						}
						anot += "generator = \"" + txtGenerator.getText() + "\"";
					}
					
					anot += ")";
					setAnotacao(anot);
					dispose();
				}
			});
			btnSalvar.setBounds(160, 87, 89, 30);
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
			btnCancelar.setBounds(61, 87, 89, 30);
		}
		return btnCancelar;
	}

	public String getAnotacao() {
		return anotacao;
	}

	public void setAnotacao(String anotacao) {
		this.anotacao = anotacao;
	}
	
}
