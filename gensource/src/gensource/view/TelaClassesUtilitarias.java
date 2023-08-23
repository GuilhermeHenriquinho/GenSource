package gensource.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class TelaClassesUtilitarias extends JDialog{
	private JButton btnCancelar;
	private JCheckBox ckstringconversoes;
	private Boolean dataHora;
	private Boolean valoresConversoes;
	private Boolean stringConversoes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                } catch (Exception e) {
                    e.printStackTrace();
                }
				try {
					TelaClassesUtilitarias window = new TelaClassesUtilitarias();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaClassesUtilitarias() {
		initialize();
	}
	
	private void initialize() {
		setModal(true);
		getContentPane().setBackground(new Color(255, 255, 255));
		setTitle("GENSOURCE");
		setBounds(100, 100, 225, 215);
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Finalizar");
		btnNewButton.setBounds(106, 132, 90, 28);
		getContentPane().add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Classes Utilit\u00E1rias", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 255)));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 11, 186, 113);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JCheckBox ckvaloresconversoes = new JCheckBox("Valores/Convers\u00F5es");
		ckvaloresconversoes.setBounds(18, 47, 151, 23);
		panel.add(ckvaloresconversoes);
		
		JCheckBox ckdatashoras = new JCheckBox("Datas/Horas");
		ckdatashoras.setBounds(18, 21, 151, 23);
		panel.add(ckdatashoras);
		panel.add(getCkstringconversoes());
		getContentPane().add(getBtnCancelar());
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(ckdatashoras.isSelected()) {
					setDataHora(true);
				}
				if(ckvaloresconversoes.isSelected()) {
					setValoresConversoes(true);
				}
				if(ckstringconversoes.isSelected()) {
					setStringConversoes(true);
				}
				
				dispose();
			}
		});
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnCancelar.setBounds(10, 132, 86, 28);
		}
		return btnCancelar;
	}
	private JCheckBox getCkstringconversoes() {
		if (ckstringconversoes == null) {
			ckstringconversoes = new JCheckBox("String/Convers\u00F5es");
			ckstringconversoes.setBounds(18, 73, 151, 23);
		}
		return ckstringconversoes;
	}

	public Boolean getDataHora() {
		return dataHora;
	}

	public void setDataHora(Boolean dataHora) {
		this.dataHora = dataHora;
	}

	public Boolean getValoresConversoes() {
		return valoresConversoes;
	}

	public void setValoresConversoes(Boolean valoresConversoes) {
		this.valoresConversoes = valoresConversoes;
	}

	public Boolean getStringConversoes() {
		return stringConversoes;
	}

	public void setStringConversoes(Boolean stringConversoes) {
		this.stringConversoes = stringConversoes;
	}

	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public void setCkstringconversoes(JCheckBox ckstringconversoes) {
		this.ckstringconversoes = ckstringconversoes;
	}
}
