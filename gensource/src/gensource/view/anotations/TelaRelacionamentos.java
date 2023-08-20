package gensource.view.anotations;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TelaRelacionamentos extends JDialog {
	private JLabel lblMappedby;
	private JComboBox cbCascade;
	private JLabel lblFetch;
	private JComboBox cbFetch;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JLabel lblField;
	private JTextField txtMappedBy;
	private String relation = ""; 
	private String anotacao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaRelacionamentos window = new TelaRelacionamentos();
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
	public TelaRelacionamentos() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				setTitle(getRelation());
			}
		});
		setModal(true);
		getContentPane().setBackground(new Color(255, 255, 255));
		setTitle(getRelation());
		setBounds(100, 100, 262, 251);
		getContentPane().setLayout(null);
		getContentPane().add(getBtnSalvar());
		getContentPane().add(getBtnCancelar());
		getContentPane().add(getCbCascade());
		getContentPane().add(getLblMappedby());
		getContentPane().add(getLblFetch());
		getContentPane().add(getCbFetch());
		getContentPane().add(getTxtMappedBy());
		getContentPane().add(getLblField());
	}
	private JLabel getLblMappedby() {
		if (lblMappedby == null) {
			lblMappedby = new JLabel("Cascade:");
			lblMappedby.setBounds(10, 98, 71, 14);
			lblMappedby.setFont(new Font("Tahoma", Font.PLAIN, 12));
		}
		return lblMappedby;
	}
	private JComboBox getCbCascade() {
		if (cbCascade == null) {
			cbCascade = new JComboBox();
			cbCascade.setBounds(90, 93, 130, 26);
			cbCascade.setModel(new DefaultComboBoxModel(new String[] {"", "ALL", "PERSIST", "MERGE", "REMOVE", "DETACH", "REFRESH"}));
		}
		return cbCascade;
	}
	private JLabel getLblFetch() {
		if (lblFetch == null) {
			lblFetch = new JLabel("Fetch:");
			lblFetch.setBounds(10, 65, 71, 14);
			lblFetch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		}
		return lblFetch;
	}
	private JComboBox getCbFetch() {
		if (cbFetch == null) {
			cbFetch = new JComboBox();
			cbFetch.setBounds(90, 60, 130, 26);
			cbFetch.setModel(new DefaultComboBoxModel(new String[] {"", "EAGER", "LAZY"}));
		}
		return cbFetch;
	}
	private JButton getBtnSalvar() {
		if (btnSalvar == null) {
			btnSalvar = new JButton("Salvar");
			btnSalvar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String anot = getRelation() + "(";
					String fetch = cbFetch.getSelectedItem().toString();
					String cascade = cbCascade.getSelectedItem().toString();
					String field = txtMappedBy.getText();
					
					if(field.length() > 0) {
						anot += "mappedBy = \"" + field + "\"";
					}
					
					if(cascade.length() > 0) {
						if (anot.endsWith("\"") ) {
							anot += ", ";
						}
						anot += "cascade = CascadeType." + cascade;
					}
					
					if(fetch.length() > 0) {
						if (anot.endsWith("\"") || !anot.endsWith("(")) {
							anot += ", ";
						}
						anot += "fetch = FetchType." + fetch;
					}
					
					
					if (anot.endsWith("\"") || !anot.endsWith("(")) {
						anot += ", ";
					}
					anot += ")";
					
					if (fetch == null || fetch.isEmpty() && cascade == null || cascade.isEmpty() && field == null || field.isEmpty()) {
				        anot = anot.substring(0, anot.length() - 2);
					}
					
					setAnotacao(anot);
					dispose();
				}
			});
			btnSalvar.setBounds(121, 156, 89, 39);
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
			btnCancelar.setBounds(22, 156, 89, 39);
		}
		return btnCancelar;
	}
	private JLabel getLblField() {
		if (lblField == null) {
			lblField = new JLabel("MappedBy:");
			lblField.setBounds(10, 34, 81, 14);
			lblField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		}
		return lblField;
	}
	private JTextField getTxtMappedBy() {
		if (txtMappedBy == null) {
			txtMappedBy = new JTextField();
			txtMappedBy.setBounds(90, 28, 130, 26);
			txtMappedBy.setColumns(10);
		}
		return txtMappedBy;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getAnotacao() {
		return anotacao;
	}

	public void setAnotacao(String anotacao) {
		this.anotacao = anotacao;
	}
	
}
