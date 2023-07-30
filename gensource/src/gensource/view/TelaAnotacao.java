package gensource.view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;

import gensource.model.AnotacaoString;
import gensource.model.Atributo;
import gensource.model.enumAndModel.Anotacao;
import gensource.model.enumAndModel.AnotacaoModel;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;

public class TelaAnotacao {

	private JFrame frmGensourceTela;
	private ObjectTableModel<AnotacaoModel> model = null;
	private ObjectTableModel<AnotacaoString> modelString = null;
    private List<AnotacaoModel> anotacoes = new ArrayList<>();
    private List<AnotacaoString> anotacoesString = new ArrayList<>();
	private JTable table;
	private JTable table2;
    private Atributo atributo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAnotacao window = new TelaAnotacao();
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
	public TelaAnotacao() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGensourceTela = new JFrame();

		frmGensourceTela.setTitle("GENSOURCE - Tela de Anota\u00E7\u00F5es");
		frmGensourceTela.getContentPane().setBackground(new Color(255, 255, 255));
		frmGensourceTela.setBounds(100, 100, 532, 509);
		frmGensourceTela.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 88, 496, 118);
		frmGensourceTela.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Concluir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(398, 424, 108, 35);
		frmGensourceTela.getContentPane().add(btnNewButton);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(280, 424, 108, 35);
		frmGensourceTela.getContentPane().add(btnCancelar);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnotacaoString anotacao = new AnotacaoString();
				anotacao.setAnotacao("@Column(name = \"product_name\", nullable = false, length = 100)");
				anotacoesString.add(anotacao);
				carregaTableString();
			}
		});
		btnAdicionar.setBounds(417, 217, 89, 23);
		frmGensourceTela.getContentPane().add(btnAdicionar);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 268, 496, 118);
		frmGensourceTela.getContentPane().add(scrollPane_1);
		
		JLabel lblNewLabel = new JLabel("Anota\u00E7\u00F5es disponiveis");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBackground(new Color(119, 136, 153));
		lblNewLabel.setBounds(10, 72, 496, 14);
		frmGensourceTela.getContentPane().add(lblNewLabel);
		
		JLabel lblAnotaesUtilizadas = new JLabel("Anota\u00E7\u00F5es Utilizadas");
		lblAnotaesUtilizadas.setBounds(10, 251, 496, 14);
		frmGensourceTela.getContentPane().add(lblAnotaesUtilizadas);
		
		table2 = new JTable();
		scrollPane_1.setViewportView(table2);
		
		JLabel lblNewLabel_1 = new JLabel("2 Cliques em cima da Anota\u00E7\u00E3o para editar - Apertar DEL com a anota\u00E7\u00E3o selecionada para deletar");
		lblNewLabel_1.setForeground(new Color(255, 0, 0));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 388, 496, 14);
		frmGensourceTela.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Anota\u00E7\u00F5es para o Campo:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(10, 26, 312, 23);
		frmGensourceTela.getContentPane().add(lblNewLabel_2);
		
		JLabel lbNomeCampo = new JLabel("");
		lbNomeCampo.setForeground(new Color(255, 165, 0));
		lbNomeCampo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbNomeCampo.setBounds(332, 26, 174, 23);
		frmGensourceTela.getContentPane().add(lbNomeCampo);
		frmGensourceTela.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				lbNomeCampo.setText(atributo.getNomeAtributo());
			}
		});
		enumToObject();
		carregaTable();
		carregaTableString();
	}
	
	public void enumToObject() {
        Anotacao[] anotacoes = Anotacao.values();
        for (int i = 0; i < anotacoes.length; i++) {
        	AnotacaoModel anotacaoModel = new AnotacaoModel();
        	anotacaoModel.setId(i+1);
        	anotacaoModel.setTitulo(anotacoes[i].getTitulo());
        	anotacaoModel.setExplicacao(anotacoes[i].getExplicacao());
        	this.anotacoes.add(anotacaoModel);
        }
	}

	public JFrame getFrame() {
		return frmGensourceTela;
	}

	public void setFrame(JFrame frame) {
		this.frmGensourceTela = frame;
	}

	public List<AnotacaoModel> getAnotacoes() {
		return anotacoes;
	}

	public void setAnotacoes(List<AnotacaoModel> anotacoes) {
		this.anotacoes = anotacoes;
	}

	public Atributo getAtributo() {
		return atributo;
	}

	public void setAtributo(Atributo atributo) {
		this.atributo = atributo;
	}
	
	public void carregaTable() {
		AnnotationResolver resolver = new AnnotationResolver(AnotacaoModel.class);
		model = new ObjectTableModel<AnotacaoModel>(resolver, "titulo:Titulo,explicacao:Explicação");
		model.setData(anotacoes);
		table.setModel(model);
	    table.getColumnModel().getColumn(0).setPreferredWidth(50);
	}
	
	public void carregaTableString() {
		AnnotationResolver resolver = new AnnotationResolver(AnotacaoString.class);
		modelString = new ObjectTableModel<AnotacaoString>(resolver, "anotacao:Anotação");
		modelString.setData(anotacoesString);
		table2.setModel(modelString);
	}
}
