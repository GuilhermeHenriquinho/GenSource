package gensource.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

import gensource.model.Atributo;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dialog.ModalityType;
import java.awt.Window.Type;
import java.awt.BorderLayout;

public class TelaEditarAtributo extends JDialog{
	private JLabel lblNewLabel;
	private JLabel lblTipo;
	private JCheckBox ckApareceNaConsulta;
	private JCheckBox ckConsultaPor;
	private JCheckBox ckObrigatorio;
	private JCheckBox ckRelacionamento;
	private JTextField txtNomeAtributo;
	private JComboBox cbTipo;
	private JButton btnCancelar;
	private Atributo atributo;
	private Atributo retorno;
	private JButton btnSalvar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEditarAtributo window = new TelaEditarAtributo();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaEditarAtributo() {
		initialize();
	}

	private void initialize() {
		setModal(true);
		this.getContentPane().setLayout(null);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				txtNomeAtributo.setText(getAtributo().getNomeAtributo());
				
				DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) cbTipo.getModel();
				for (int i = 0; i < model.getSize(); i++) {
				    String item = model.getElementAt(i);
				    if (item.equals(getAtributo().getTipoAtributo())) {
				        cbTipo.setSelectedIndex(i);
				        break;
				    }
				}
				
				ckApareceNaConsulta.setSelected(getAtributo().getApareceNaConsulta());;
				ckConsultaPor.setSelected(getAtributo().getConsultaPor());;
				ckObrigatorio.setSelected(getAtributo().getIsObrigatorio());;
				ckRelacionamento.setSelected(getAtributo().getIsRelacionamento());;
			}
		});
		setTitle("Tela de Editar Atributo");
		getContentPane().setBackground(new Color(255, 255, 255));
		this.setBounds(100, 100, 264, 281);
		getContentPane().add(getLblNewLabel());
		getContentPane().add(getLblTipo());
		getContentPane().add(getCkApareceNaConsulta());
		getContentPane().add(getCkConsultaPor());
		getContentPane().add(getCkObrigatorio());
		getContentPane().add(getCkRelacionamento());
		getContentPane().add(getTxtNomeAtributo());
		getContentPane().add(getCbTipo());
		getContentPane().add(getBtnCancelar());
		getContentPane().add(getBtnSalvar());
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Atributo:");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblNewLabel.setBounds(27, 22, 58, 14);
		}
		return lblNewLabel;
	}
	private JLabel getLblTipo() {
		if (lblTipo == null) {
			lblTipo = new JLabel("Tipo:");
			lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblTipo.setBounds(27, 53, 58, 14);
		}
		return lblTipo;
	}
	private JCheckBox getCkApareceNaConsulta() {
		if (ckApareceNaConsulta == null) {
			ckApareceNaConsulta = new JCheckBox("Aparece na Consulta");
			ckApareceNaConsulta.setFont(new Font("Tahoma", Font.PLAIN, 13));
			ckApareceNaConsulta.setBounds(27, 79, 195, 23);
		}
		return ckApareceNaConsulta;
	}
	private JCheckBox getCkConsultaPor() {
		if (ckConsultaPor == null) {
			ckConsultaPor = new JCheckBox("Consulta Por");
			ckConsultaPor.setFont(new Font("Tahoma", Font.PLAIN, 13));
			ckConsultaPor.setBounds(27, 105, 195, 23);
		}
		return ckConsultaPor;
	}
	private JCheckBox getCkObrigatorio() {
		if (ckObrigatorio == null) {
			ckObrigatorio = new JCheckBox("Obrigat\u00F3rio");
			ckObrigatorio.setFont(new Font("Tahoma", Font.PLAIN, 13));
			ckObrigatorio.setBounds(27, 131, 195, 23);
		}
		return ckObrigatorio;
	}
	private JCheckBox getCkRelacionamento() {
		if (ckRelacionamento == null) {
			ckRelacionamento = new JCheckBox("Relacionamento");
			ckRelacionamento.setFont(new Font("Tahoma", Font.PLAIN, 13));
			ckRelacionamento.setBounds(27, 157, 195, 23);
		}
		return ckRelacionamento;
	}
	private JTextField getTxtNomeAtributo() {
		if (txtNomeAtributo == null) {
			txtNomeAtributo = new JTextField();
			txtNomeAtributo.setBounds(95, 22, 127, 20);
			txtNomeAtributo.setColumns(10);
		}
		return txtNomeAtributo;
	}
	private JComboBox getCbTipo() {
		if (cbTipo == null) {
			cbTipo = new JComboBox();
			cbTipo.setModel(new DefaultComboBoxModel(new String[] {"", "Int", "Long", "Boolean", "Char", "Float", "Double", "String"}));
			cbTipo.setBounds(95, 50, 127, 22);
		}
		return cbTipo;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setRetorno(null);
					dispose();
				}
			});
			btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnCancelar.setBounds(27, 193, 89, 32);
		}
		return btnCancelar;
	}

	public Atributo getAtributo() {
		return atributo;
	}

	public void setAtributo(Atributo atributo) {
		this.atributo = atributo;
	}

	public Atributo getRetorno() {
		return retorno;
	}

	public void setRetorno(Atributo retorno) {
		this.retorno = retorno;
	}
	private JButton getBtnSalvar() {
		if (btnSalvar == null) {
			btnSalvar = new JButton("Salvar");
			btnSalvar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Atributo atr = new Atributo();
					atr.setNomeAtributo(txtNomeAtributo.getText());
	        		atr.setTipoAtributo(cbTipo.getSelectedItem().toString());
	        		atr.setApareceNaConsulta(ckApareceNaConsulta.isSelected());
	        		atr.setConsultaPor(ckConsultaPor.isSelected());
	        		atr.setIsObrigatorio(ckObrigatorio.isSelected());
	        		atr.setIsRelacionamento(ckRelacionamento.isSelected());
	        		setRetorno(atr);
					
					dispose();
				}
			});
			btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnSalvar.setBounds(133, 193, 89, 32);
		}
		return btnSalvar;
	}
}
