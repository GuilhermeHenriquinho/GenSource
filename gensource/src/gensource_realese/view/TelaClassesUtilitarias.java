package gensource_realese.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

public class TelaClassesUtilitarias {

	private JFrame frmGensourceTela;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); // Configurar o LookAndFeel para o estilo do Windows
                } catch (Exception e) {
                    e.printStackTrace();
                }
				try {
					TelaClassesUtilitarias window = new TelaClassesUtilitarias();
					window.frmGensourceTela.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaClassesUtilitarias() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGensourceTela = new JFrame();
		frmGensourceTela.getContentPane().setBackground(new Color(255, 255, 255));
		frmGensourceTela.setTitle("GENSOURCE");
		frmGensourceTela.setBounds(100, 100, 225, 238);
		frmGensourceTela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGensourceTela.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Finalizar");
		btnNewButton.setBounds(50, 161, 112, 28);
		frmGensourceTela.getContentPane().add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Classes Utilit\u00E1rias", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 255)));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 11, 186, 143);
		frmGensourceTela.getContentPane().add(panel);
		panel.setLayout(null);
		
		JCheckBox chckbxOpesescolhas = new JCheckBox("Op\u00E7\u00F5es/Escolhas");
		chckbxOpesescolhas.setBounds(18, 99, 151, 23);
		panel.add(chckbxOpesescolhas);
		
		JCheckBox chckbxGerarClasseResponsavel = new JCheckBox("Mudar Foco");
		chckbxGerarClasseResponsavel.setBounds(18, 73, 151, 23);
		panel.add(chckbxGerarClasseResponsavel);
		
		JCheckBox chckbxGerarClasseDe = new JCheckBox("Valores/Convers\u00F5es");
		chckbxGerarClasseDe.setBounds(18, 47, 151, 23);
		panel.add(chckbxGerarClasseDe);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Datas/Horas");
		chckbxNewCheckBox.setBounds(18, 21, 151, 23);
		panel.add(chckbxNewCheckBox);
	}
}
