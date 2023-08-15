package gensource.view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;

import gensource.model.AnotacaoString;
import gensource.model.Atributo;
import gensource.model.enumAndModel.Anotacao;
import gensource.model.enumAndModel.AnotacaoModel;
import gensource.view.anotations.TelaColumn;
import gensource.view.anotations.TelaGeneratedValue;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;

public class TelaAnotacao extends JDialog {

	private ObjectTableModel<AnotacaoModel> model = null;
	private ObjectTableModel<AnotacaoString> modelString = null;
    private List<AnotacaoModel> anotacoes = new ArrayList<>();
    private List<AnotacaoString> anotacoesString = new ArrayList<>();
	private JTable table;
	private JTable table2;
    private Atributo atributo;
    private Atributo retorno;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAnotacao window = new TelaAnotacao();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaAnotacao() {
		initialize();
	}

	private void initialize() {
		setModal(true);
		this.setTitle("GENSOURCE - Tela de Anota\u00E7\u00F5es");
		this.getContentPane().setBackground(new Color(255, 255, 255));
		this.setBounds(100, 100, 532, 509);
		this.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 88, 496, 118);
		this.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Concluir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				List<AnotacaoString> anotacoes = modelString.getData();
				if(Objects.nonNull(anotacoes) && !anotacoes.isEmpty()) {
					List<String> anotacoesString = new ArrayList<>();
					for(AnotacaoString an : anotacoes) {
						anotacoesString.add(an.getAnotacao());
					}
					getAtributo().setAnotacao(anotacoesString);
					setRetorno(getAtributo());
					JOptionPane.showMessageDialog(null, "Anotações para o Atributo \""+getAtributo().getNomeAtributo()+"\" salvas com Sucesso!");
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Por favor adicione anotações para o atributo.");
				}
			}
		});
		btnNewButton.setBounds(398, 424, 108, 35);
		this.getContentPane().add(btnNewButton);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(280, 424, 108, 35);
		this.getContentPane().add(btnCancelar);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AnotacaoModel am = model.getValue(table.getSelectedRow());
				
				if(Objects.nonNull(am)) {
					//Column
					if(am.getTitulo().contains("Column")) {
						TelaColumn screen = new TelaColumn();
						screen.setVisible(true);
						if(Objects.nonNull(screen.getAnotacao()) && screen.getAnotacao().length() > 0) {
							AnotacaoString anotacao = new AnotacaoString();
							anotacao.setAnotacao(screen.getAnotacao());
							anotacoesString.add(anotacao);
							carregaTableString(anotacoesString);
						}
					}
					
					//Relacionamentos
//					if() {
//						
//					}
					
					//GeneratedValue
					if(getAtributo().getNomeAtributo().equals("id") && am.getTitulo().contains("GeneratedValue")) {
						TelaGeneratedValue screen = new TelaGeneratedValue();
						screen.setVisible(true);
						if(Objects.nonNull(screen.getAnotacao()) && screen.getAnotacao().length() > 0) {
							AnotacaoString anotacao = new AnotacaoString();
							anotacao.setAnotacao(screen.getAnotacao());
							anotacoesString.add(anotacao);
							
							for (int i = 0; i < anotacoesString.size(); i++) {
								if (anotacoesString.get(i).getAnotacao().contains("GeneratedValue")) {
									anotacoesString.remove(i);

									for (int j = 0; j < getAtributo().getAnotacao().size(); j++) {
										if (getAtributo().getAnotacao().get(j).contains("GeneratedValue")) {
											getAtributo().getAnotacao().remove(j);
											break;
										}
									}
									break;
								}
							}
							
							carregaTableString(anotacoesString);
						}
					}
					
					
					
					
				}
			}
		});
		btnAdicionar.setBounds(417, 217, 89, 23);
		this.getContentPane().add(btnAdicionar);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 268, 496, 118);
		this.getContentPane().add(scrollPane_1);
		
		JLabel lblNewLabel = new JLabel("Anota\u00E7\u00F5es disponiveis");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBackground(new Color(119, 136, 153));
		lblNewLabel.setBounds(10, 72, 496, 14);
		this.getContentPane().add(lblNewLabel);
		
		JLabel lblAnotaesUtilizadas = new JLabel("Anota\u00E7\u00F5es Utilizadas");
		lblAnotaesUtilizadas.setBounds(10, 251, 496, 14);
		this.getContentPane().add(lblAnotaesUtilizadas);
		
		table2 = new JTable();
		scrollPane_1.setViewportView(table2);
		
		JLabel lblNewLabel_1 = new JLabel("2 Cliques em cima da Anota\u00E7\u00E3o para editar - Apertar DEL com a anota\u00E7\u00E3o selecionada para deletar");
		lblNewLabel_1.setForeground(new Color(255, 0, 0));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 388, 496, 14);
		this.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Anota\u00E7\u00F5es para o Campo:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(10, 26, 312, 23);
		this.getContentPane().add(lblNewLabel_2);
		
		JLabel lbNomeCampo = new JLabel("");
		lbNomeCampo.setForeground(new Color(255, 165, 0));
		lbNomeCampo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbNomeCampo.setBounds(332, 26, 174, 23);
		this.getContentPane().add(lbNomeCampo);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				lbNomeCampo.setText(atributo.getNomeAtributo());
				carregaTableString(anotacoesString);
			}
		});
		enumToObject();
		carregaTable();
		carregaTableString(anotacoesString);
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
	
	public void carregaTableString(List<AnotacaoString> anotacoes) {
		AnnotationResolver resolver = new AnnotationResolver(AnotacaoString.class);
		modelString = new ObjectTableModel<AnotacaoString>(resolver, "anotacao:Anotação");
		modelString.setData(anotacoes);
		table2.setModel(modelString);
	}

	public Atributo getRetorno() {
		return retorno;
	}

	public void setRetorno(Atributo retorno) {
		this.retorno = retorno;
	}

	public List<AnotacaoString> getAnotacoesString() {
		return anotacoesString;
	}

	public void setAnotacoesString(List<AnotacaoString> anotacoesString) {
		this.anotacoesString = anotacoesString;
	}
	
}
