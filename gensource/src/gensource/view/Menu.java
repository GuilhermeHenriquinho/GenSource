package gensource.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;

import gensource.model.AnotacaoString;
import gensource.model.Atributo;
import gensource.model.Classe;
import gensource.model.Conexao;
import gensource.model.Projeto;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Menu extends JFrame{
    private JTextField txtNomeClasse;
	private ObjectTableModel<Atributo> model = null;
	private List<Classe> listaClasses = new ArrayList<Classe>();
//	private List<Atributo> listaAtributo = new ArrayList<Atributo>();
	private JTable table;
	private JTextField txtNomeAtributo;
	private JTextField txtNomeConexao;
	private JTextField txtUrl;
	private JTextField txtDialect;
	private JTextField txtUsuario;
	private JTextField txtSenha;
	private JTextField txtNomeProjeto;
	private JTextField txtCaminho;
	private JTextField txtCaminhoClasse;
	private JTextField txtDriver;
	private JComboBox cbTipoAtributo;
	private JCheckBox chckbxApareceNaConsulta;
	private JCheckBox chckbxConsultaPor;
	private JCheckBox checkObrigatorio;
	private JCheckBox checkRelacionamento;
	private Boolean nova = true;
	private JButton btnLimparClasse;
	private JCheckBox cbIsWeb;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); // Configurar o LookAndFeel para o estilo do Windows
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Menu window = new Menu();
                window.setVisible(true);
            }
        });
    }

    public Menu() {
        initialize();
    }

    private void initialize() {
        this.getContentPane().setBackground(new Color(255, 255, 255));
        this.addWindowListener(new WindowAdapter() {
        	@Override
        	public void windowOpened(WindowEvent e) {
        		ajusta();
        	}
        });
        this.getContentPane().setForeground(new Color(255, 255, 255));
        this.setTitle("GENSOURCE - Gerador de CRUD");
        this.setBounds(100, 100, 716, 648);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JPanel panelCabecalho = new JPanel();
        panelCabecalho.setBackground(new Color(255, 255, 255));
        panelCabecalho.setBounds(0, 0, 700, 97);
        this.getContentPane().add(panelCabecalho);
        panelCabecalho.setLayout(null);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\guilh\\git\\gensource\\gensource\\src\\gensource\\icon\\LogoPrincipal2.png"));
        lblNewLabel.setBounds(0, 0, 701, 97);
        panelCabecalho.add(lblNewLabel);

        JPanel panelGuias = new JPanel();
        panelGuias.setBounds(0, 97, 700, 446);
        this.getContentPane().add(panelGuias);
        panelGuias.setLayout(new BoxLayout(panelGuias, BoxLayout.X_AXIS)); // Definindo o layout como BoxLayout horizontal

        // Criação do JTabbedPane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setAlignmentY(5.0f);
        tabbedPane.setAlignmentX(5.0f);
        panelGuias.add(tabbedPane);

        // Criação dos painéis para as abas
        JPanel abaProjeto = new JPanel();
        abaProjeto.setBackground(new Color(255, 255, 255));
        JPanel abaClasse = new JPanel();
        abaClasse.setBackground(new Color(255, 255, 255));

        // Adicionar os painéis às abas do JTabbedPane
        tabbedPane.addTab("Projeto", abaProjeto);
        abaProjeto.setLayout(null);
        
        JPanel panelProjeto = new JPanel();
        panelProjeto.setBackground(new Color(255, 255, 255));
        panelProjeto.setBorder(new TitledBorder(null, "Projeto", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
        panelProjeto.setBounds(10, 41, 675, 329);
        abaProjeto.add(panelProjeto);
        panelProjeto.setLayout(null);
        
        JLabel lblNewLabel_1_3 = new JLabel("Nome do Projeto:");
        lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_1_3.setBounds(10, 30, 120, 19);
        panelProjeto.add(lblNewLabel_1_3);
        
        txtNomeProjeto = new JTextField();
        txtNomeProjeto.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtNomeProjeto.setColumns(10);
        txtNomeProjeto.setBounds(120, 28, 120, 23);
        panelProjeto.add(txtNomeProjeto);
        
        JLabel lblNewLabel_1_3_1 = new JLabel("Caminho:");
        lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_1_3_1.setBounds(250, 30, 72, 19);
        panelProjeto.add(lblNewLabel_1_3_1);
        
        txtCaminho = new JTextField();
        txtCaminho.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtCaminho.setColumns(10);
        txtCaminho.setBounds(319, 28, 136, 23);
        panelProjeto.add(txtCaminho);
        
        JPanel panelConexao = new JPanel();
        panelConexao.setBounds(10, 69, 655, 204);
        panelProjeto.add(panelConexao);
        panelConexao.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Conex\u00E3o", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
        panelConexao.setBackground(Color.WHITE);
        panelConexao.setLayout(null);
        
        JLabel lblNewLabel_1 = new JLabel("Nome da Conex\u00E3o:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_1.setBounds(10, 36, 120, 14);
        panelConexao.add(lblNewLabel_1);
        
        txtNomeConexao = new JTextField();
        txtNomeConexao.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtNomeConexao.setBounds(132, 32, 235, 23);
        panelConexao.add(txtNomeConexao);
        txtNomeConexao.setColumns(10);
        
        txtUrl = new JTextField();
        txtUrl.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtUrl.setColumns(10);
        txtUrl.setBounds(132, 61, 235, 23);
        panelConexao.add(txtUrl);
        
        JLabel lblNewLabel_1_1 = new JLabel("URL:");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_1_1.setBounds(10, 65, 120, 14);
        panelConexao.add(lblNewLabel_1_1);
        
        JLabel lblNewLabel_1_1_1 = new JLabel("Dialect:");
        lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_1_1_1.setBounds(10, 94, 120, 14);
        panelConexao.add(lblNewLabel_1_1_1);
        
        txtDialect = new JTextField();
        txtDialect.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtDialect.setColumns(10);
        txtDialect.setBounds(132, 90, 235, 23);
        panelConexao.add(txtDialect);
        
        JPanel panelLogin = new JPanel();
        panelLogin.setBorder(new TitledBorder(null, "Informa\u00E7\u00F5es de Login", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
        panelLogin.setBackground(new Color(255, 255, 255));
        panelLogin.setBounds(377, 21, 268, 127);
        panelConexao.add(panelLogin);
        panelLogin.setLayout(null);
        
        JLabel lblNewLabel_1_2 = new JLabel("Usu\u00E1rio:");
        lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_1_2.setBounds(10, 40, 48, 14);
        panelLogin.add(lblNewLabel_1_2);
        
        JLabel lblNewLabel_1_2_1 = new JLabel("Senha:");
        lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_1_2_1.setBounds(10, 76, 55, 14);
        panelLogin.add(lblNewLabel_1_2_1);
        
        txtUsuario = new JTextField();
        txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtUsuario.setColumns(10);
        txtUsuario.setBounds(69, 38, 189, 23);
        panelLogin.add(txtUsuario);
        
        txtSenha = new JTextField();
        txtSenha.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtSenha.setColumns(10);
        txtSenha.setBounds(69, 72, 189, 23);
        panelLogin.add(txtSenha);
        
        JLabel lblNewLabel_3 = new JLabel("Deve colocar as informa\u00E7\u00F5es de conex\u00E3o do Banco de Dados que o projeto gerado ir\u00E1 utilizar");
        lblNewLabel_3.setForeground(new Color(255, 0, 0));
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_3.setBounds(10, 179, 644, 14);
        panelConexao.add(lblNewLabel_3);
        
        JLabel lblNewLabel_1_1_1_1 = new JLabel("Driver:");
        lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_1_1_1_1.setBounds(10, 123, 120, 14);
        panelConexao.add(lblNewLabel_1_1_1_1);
        
        txtDriver = new JTextField();
        txtDriver.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtDriver.setColumns(10);
        txtDriver.setBounds(132, 119, 235, 23);
        panelConexao.add(txtDriver);
        
        JButton btnAvancar = new JButton("Avancar");
        btnAvancar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
//        		if(verificaCamposProjeto()) {
	        		tabbedPane.setEnabledAt(1, true);
	        		tabbedPane.setSelectedIndex(1);
//        		} else {
//        			JOptionPane.showMessageDialog(null, "Por favor crie ou adicione um projeto para avançar!");
//        		}
        	}
        });
        btnAvancar.setBounds(543, 284, 122, 34);
        panelProjeto.add(btnAvancar);
        
        JButton btnSelecionarCaminhoProjeto = new JButton("Selecionar");
        btnSelecionarCaminhoProjeto.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    txtCaminho.setText(selectedFile.getAbsolutePath());
                }
        	}
        });
        btnSelecionarCaminhoProjeto.setBounds(465, 26, 101, 28);
        panelProjeto.add(btnSelecionarCaminhoProjeto);
        panelProjeto.add(getCbIsWeb());
        
        JLabel lblNewLabel_4 = new JLabel("Para avan\u00E7ar para as outras guias \u00E9 necess\u00E1rio criar um projeto!");
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4.setForeground(new Color(255, 0, 0));
        lblNewLabel_4.setBounds(10, 370, 675, 14);
        abaProjeto.add(lblNewLabel_4);
        tabbedPane.addTab("Classe", abaClasse);
        tabbedPane.setEnabledAt(1, false);
        abaClasse.setLayout(null);
        
        JPanel panelClass = new JPanel();
        panelClass.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Classe", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 140, 0)));
        panelClass.setBackground(new Color(255, 255, 255));
        panelClass.setBounds(20, 11, 653, 396);
        abaClasse.add(panelClass);
        panelClass.setLayout(null);
        
        JLabel lbNomeClasse = new JLabel("Classe:");
        lbNomeClasse.setBounds(10, 27, 52, 21);
        panelClass.add(lbNomeClasse);
        lbNomeClasse.setFont(new Font("Tahoma", Font.PLAIN, 13));
        
        txtNomeClasse = new JTextField();
        txtNomeClasse.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyTyped(KeyEvent e) {
                String text = txtNomeClasse.getText();
                if (text.length() == 0) {
                    e.setKeyChar(Character.toUpperCase(e.getKeyChar()));
                } else if (text.length() == 1 && Character.isLowerCase(text.charAt(0))) {
                    txtNomeClasse.setText(text.toUpperCase());
                }
        	}
        });
        txtNomeClasse.setBounds(58, 26, 155, 23);
        panelClass.add(txtNomeClasse);
        txtNomeClasse.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtNomeClasse.setColumns(10);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Atributo(s)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 140, 0)));
        panel.setBounds(10, 59, 633, 281);
        panelClass.add(panel);
        panel.setLayout(null);
        
        JLabel lblNomeDoAtributo = new JLabel("Atributo:");
        lblNomeDoAtributo.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNomeDoAtributo.setBounds(10, 26, 61, 21);
        panel.add(lblNomeDoAtributo);
        
        txtNomeAtributo = new JTextField();
        txtNomeAtributo.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtNomeAtributo.setColumns(10);
        txtNomeAtributo.setBounds(67, 24, 153, 23);
        panel.add(txtNomeAtributo);
        
        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblTipo.setBounds(230, 26, 38, 21);
        panel.add(lblTipo);
        
        cbTipoAtributo = new JComboBox();
        //Adicionar nome das Classes já adicionadas
        cbTipoAtributo.setModel(new DefaultComboBoxModel(new String[] {"", "Integer", "Long", "Boolean", "Char", "Float", "Double", "String"}));
        cbTipoAtributo.setBounds(267, 24, 127, 23);
        panel.add(cbTipoAtributo);
        
        checkObrigatorio = new JCheckBox("Obrigat\u00F3rio");
        checkObrigatorio.setBounds(277, 59, 102, 23);
        panel.add(checkObrigatorio);
        
        checkRelacionamento = new JCheckBox("Relacionamento");
        checkRelacionamento.setBounds(393, 59, 112, 23);
        panel.add(checkRelacionamento);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 97, 613, 118);
        panel.add(scrollPane);
        
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(e.getClickCount() == 2) {
					Atributo atributo = model.getValue(table.getSelectedRow());
					TelaAnotacao tela = new TelaAnotacao();
					
					List<String> anotacoes = atributo.getAnotacao();
					List<AnotacaoString> anotacoesObject = new ArrayList<>();
					
					if(Objects.nonNull(anotacoes) && !anotacoes.isEmpty()) {
						for(String anot : anotacoes) {
							anotacoesObject.add(new AnotacaoString(anot));
						}
						tela.setAnotacoesString(anotacoesObject);
					}
					tela.setAtributo(atributo);
					tela.setVisible(true);
					
					if(Objects.nonNull(tela.getRetorno())) {
						Classe classe = selecionaClasse();
	        			
	        			for(int i=0; i<classe.getAtributos().size(); i++) {
	        				if(classe.getAtributos().get(i).getNomeAtributo().equals(atributo.getNomeAtributo())) {
	        					classe.getAtributos().get(i).setAnotacao(tela.getRetorno().getAnotacao());
	        				}
	        			}
	        			carregaTable(classe.getAtributos());
					}
					
				}
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Classe classe = selecionaClasse();
				
				String nomeAtributo = model.getValue(table.getSelectedRow()).getNomeAtributo();
				List<Atributo> listaAtr = classe.getAtributos();
				for(int i=0; i<listaAtr.size(); i++) {
					if(listaAtr.get(i).getNomeAtributo().equals(nomeAtributo)) {
						listaAtr.remove(i);
						break;
					}
				}
				classe.setAtributos(listaAtr);
				
				carregaTable(classe.getAtributos());
			}
		});
		btnRemover.setBounds(119, 247, 122, 23);
		panel.add(btnRemover);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//abre uma tela com os campos de atributo para preencher e dps salva e recarrega a lista
				String nomeAtributo = model.getValue(table.getSelectedRow()).getNomeAtributo();
				
				Classe classe = selecionaClasse();
				
				int index = 0;
				for(int i=0; i<classe.getAtributos().size(); i++) {
					if(classe.getAtributos().get(i).getNomeAtributo().equals(nomeAtributo)) {
						index = i;
					}
				}
				
				TelaEditarAtributo screen = new TelaEditarAtributo();
				screen.setAtributo(classe.getAtributos().get(index));
				screen.setVisible(true);
				
				if(Objects.nonNull(screen.getRetorno())) {
					classe.getAtributos().set(index, screen.getRetorno());
					JOptionPane.showMessageDialog(null, "Atributo editado com Sucesso!");
					carregaTable(classe.getAtributos());
				}
				
				
				
			}
		});
		btnEditar.setBounds(383, 247, 122, 23);
		panel.add(btnEditar);
		
		JLabel lblNewLabel_2 = new JLabel("Clique duas vezes em cima do campo que deseja adicionar/editar anota\u00E7\u00F5es");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(10, 222, 613, 14);
		panel.add(lblNewLabel_2);
		
		chckbxConsultaPor = new JCheckBox("Consulta Por");
		chckbxConsultaPor.setBounds(154, 59, 110, 23);
		panel.add(chckbxConsultaPor);
		
		chckbxApareceNaConsulta = new JCheckBox("Aparece na Consulta");
		chckbxApareceNaConsulta.setBounds(10, 59, 132, 23);
		panel.add(chckbxApareceNaConsulta);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpa(false);
			}
		});
		btnLimpar.setBounds(251, 247, 122, 23);
		panel.add(btnLimpar);
		
		JButton btnRemoverClasse = new JButton("Excluir Classe");
		btnRemoverClasse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//exclui classe atual que esta na tela e limpa os campos
				int i=0;
				for(; i<listaClasses.size(); i++) {
					if(txtNomeClasse.getText().equals(listaClasses.get(i).getNomeClasse())) {
						listaClasses.remove(i);
						break;
					}
				}
				
				limpa(true);
			}
		});
		btnRemoverClasse.setBounds(58, 351, 121, 34);
		panelClass.add(btnRemoverClasse);
		
		JButton btnEditarClasse = new JButton("Editar Classe");
		btnEditarClasse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Abrir tela que seleciona classe (que tem em execucao do projeto atual) e tras para essa e preenche campos com a classe
				SelecionaClasse screen = new SelecionaClasse();
				screen.setClasses(listaClasses);
				screen.setVisible(true);
				
				if(Objects.nonNull(screen.getRetorno())) {
					mostraClasse(screen.getRetorno());
					nova = false;
				}
			}
		});
		btnEditarClasse.setBounds(329, 351, 130, 34);
		panelClass.add(btnEditarClasse);
		
		JLabel lblCaminhoDaClasse = new JLabel("Diret\u00F3rio:");
		lblCaminhoDaClasse.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCaminhoDaClasse.setBounds(223, 27, 65, 21);
		panelClass.add(lblCaminhoDaClasse);
		
		txtCaminhoClasse = new JTextField();
		txtCaminhoClasse.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCaminhoClasse.setColumns(10);
		txtCaminhoClasse.setBounds(282, 27, 238, 23);
		panelClass.add(txtCaminhoClasse);
		
		JButton btnSelecionarCaminhoClasse = new JButton("Selecionar");
		btnSelecionarCaminhoClasse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    txtCaminhoClasse.setText(selectedFile.getAbsolutePath());
                }
			}
		});
		btnSelecionarCaminhoClasse.setBounds(530, 24, 113, 28);
		panelClass.add(btnSelecionarCaminhoClasse);
		
		JButton btnSalvarClasse = new JButton("Salvar Classe");
		btnSalvarClasse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(nova) {
					cbTipoAtributo.addItem(txtNomeClasse.getText());
				} else {
					for(int i=0; i<listaClasses.size(); i++) {
						if(txtNomeClasse.getText().equals(listaClasses.get(i).getNomeClasse())) {
							listaClasses.get(i).setDiretorioClasse(txtCaminhoClasse.getText());
							listaClasses.get(i).setAtributos(model.getData());
						}
					}
				}
				
				carregaTable(new ArrayList<Atributo>());
				
				txtNomeClasse.setText("");
				txtCaminhoClasse.setText("");
				txtNomeAtributo.setText("");
				cbTipoAtributo.setSelectedIndex(0);
				chckbxApareceNaConsulta.setSelected(false);
				chckbxConsultaPor.setSelected(false);
				checkObrigatorio.setSelected(false);
				checkRelacionamento.setSelected(false);
				
				nova = true;
				JOptionPane.showMessageDialog(null, "Classe Salva com Sucesso!");
			}
		});
		btnSalvarClasse.setBounds(469, 351, 121, 34);
		panelClass.add(btnSalvarClasse);
		
		JButton btnGerar = new JButton("Gerar Projeto");
        btnGerar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                        	Projeto projeto = montaProjeto();
                        	if(Objects.nonNull(projeto) && Objects.nonNull(projeto.getClasses()) && !projeto.getClasses().isEmpty()) {
                        		gerarProjetoMaven(projeto);
                        	}
                        } catch (Exception ex) {
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    JOptionPane.showMessageDialog(null, "Ocorreu um erro ao gerar o projeto:\n" + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                                    ex.printStackTrace();
                                }
                            });
                        }
                    }
                });
                thread.start();
            }
        });
		btnGerar.setBounds(553, 554, 122, 44);
		this.getContentPane().add(btnGerar);
		
		JButton btnLimparProjeto = new JButton("Limpar");
		btnLimparProjeto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnLimparProjeto.setBounds(421, 554, 122, 44);
		this.getContentPane().add(btnLimparProjeto);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnFechar.setBounds(289, 554, 122, 44);
		this.getContentPane().add(btnFechar);
		
        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(verificaClasse_Atributo()) {
        			Classe classe = selecionaClasse();
        			
	        		Atributo atr = new Atributo();
	        		atr.setNomeAtributo(txtNomeAtributo.getText());
	        		atr.setTipoAtributo(cbTipoAtributo.getSelectedItem().toString());
	        		
	        		atr.setApareceNaConsulta(chckbxApareceNaConsulta.isSelected());
	        		atr.setConsultaPor(chckbxConsultaPor.isSelected());
	        		atr.setIsObrigatorio(checkObrigatorio.isSelected());
	        		atr.setIsRelacionamento(checkRelacionamento.isSelected());
	        		
	        		if(Objects.nonNull(classe.getAtributos()) && !classe.getAtributos().isEmpty()) {
	        			classe.getAtributos().add(atr);
	        		}else {
	        			List<Atributo> listaAtr = new ArrayList<>();
	        			listaAtr.add(atr);
	        			classe.setAtributos(listaAtr);
	        		}
	        		
					carregaTable(classe.getAtributos());
					
					limpa(false);
        		}
        	}
        });
        btnAdicionar.setBounds(511, 55, 112, 31);
        panel.add(btnAdicionar);
        panelClass.add(getBtnLimparClasse());
    }
    
	public void limpaProjeto() {
		txtNomeProjeto.setText("");
		txtCaminho.setText("");
		txtNomeConexao.setText("");
		txtUrl.setText("");
		txtDialect.setText("");
		txtDriver.setText("");
		txtUsuario.setText("");
		txtSenha.setText("");
		limpa(true);
	}
    
    public Classe selecionaClasse() {
		String nomeClasse = txtNomeClasse.getText();
		Classe classe = new Classe();
		
		for(Classe classr : listaClasses) {
			if(classr.getNomeClasse().equals(nomeClasse)) {
				classe = classr;
			}
		}
		return classe;
    }
    
    public Boolean verificaClasse_Atributo() {
    	String nomeClasse = txtNomeClasse.getText();
    	String caminhoClasse = txtCaminhoClasse.getText();
    	
    	if(Objects.nonNull(nomeClasse) && Objects.nonNull(caminhoClasse) && nomeClasse.isEmpty() && caminhoClasse.isEmpty()) {
    		JOptionPane.showMessageDialog(null, "Por favor informe o Nome e o Caminho da Classe!");
    		return false;
    	}
    	
    	boolean teste = true;
    	for(Classe classr : listaClasses) {
    		if(classr.getNomeClasse().equals(nomeClasse)) {
    			teste = false;
    		}
    	}
    	
    	if(teste) {
			Classe classe = new Classe();
			classe.setNomeClasse(nomeClasse);
			classe.setDiretorioClasse(txtCaminhoClasse.getText());
			Atributo attr = new Atributo();
			attr.setNomeAtributo("id");
			attr.setApareceNaConsulta(true);
			attr.setConsultaPor(false);
			attr.setIsObrigatorio(false);
			attr.setIsRelacionamento(false);
			attr.setTipoAtributo(cbIsWeb.isSelected() ? "Integer" : "Long");
			List<String> anotacao = new ArrayList<String>();
			anotacao.add(new String("@Id"));
		    anotacao.add(new String("@GeneratedValue(strategy = GenerationType.IDENTITY)"));
			attr.setAnotacao(anotacao);
			List<Atributo> atributo = new ArrayList<>();
			atributo.add(attr);
			classe.setAtributos(atributo);
			listaClasses.add(classe);
    	}
		
    	String nome = txtNomeAtributo.getText();
    	
    	List<Atributo> listaAtributo = model.getData();
    	for(Atributo atr : listaAtributo) {
    		if(atr.getNomeAtributo().equals(nome)) {
    			JOptionPane.showMessageDialog(null, "O Atributo já foi adicionado!");
    			return false;
    		}
    	}
    	
    	String tipo = cbTipoAtributo.getSelectedItem().toString();
    	if(Objects.nonNull(nome) && Objects.nonNull(tipo) && !nome.isEmpty() && !tipo.isEmpty()) {
    		return true;
    	}else {
    		JOptionPane.showMessageDialog(null, "Por favor informe o Nome e o Tipo do Atributo!");
    		return false;
    	}
    }
    
    public Boolean verificaCamposProjeto() {
        List<String> campos = new ArrayList<>();
        campos.add(txtNomeConexao.getText());
        campos.add(txtUrl.getText());
        campos.add(txtDialect.getText());
        campos.add(txtUsuario.getText());
        campos.add(txtSenha.getText());
        campos.add(txtDriver.getText());
        campos.add(txtCaminho.getText());
        campos.add(txtNomeProjeto.getText());

        for (String campo : campos) {
            if (campo.isEmpty()) {
                return false;
            }
        }
        return true;
    }
	
	public void carregaTable(List<Atributo> atributos) {
		AnnotationResolver resolver = new AnnotationResolver(Atributo.class);
		model = new ObjectTableModel<Atributo>(resolver, "nomeAtributo:Nome,tipoAtributo:Tipo,isObrigatorio:Obrigatório,isRelacionamento:Relacionamento,apareceNaConsulta:ApareceConsulta,consultaPor:ConsultaPor");
		model.setData(atributos);
		table.setModel(model);
	    table.getColumnModel().getColumn(0).setPreferredWidth(100);
	}
	
	public void ajusta() {
		List<Atributo> atributos = new ArrayList();
		carregaTable(atributos);
	}
	
	private Projeto montaProjeto() {
		
        txtCaminho.setText("C:\\workspace");
        txtNomeProjeto.setText("projetoWebGerado");
        txtNomeConexao.setText("teste");
        txtUrl.setText("jdbc:mysql://localhost/teste");
        txtDialect.setText("org.hibernate.dialect.MySQL57Dialect");
        txtDriver.setText("com.mysql.jdbc.Driver");
        txtUsuario.setText("root");
        txtSenha.setText("1234");
        

//		montaClassesTeste();
		
		//Conexão
		Conexao conexao = new Conexao();
        conexao.setNomeConexao(txtNomeConexao.getText());
        conexao.setUrl(txtUrl.getText());
        conexao.setDialect(txtDialect.getText());
        conexao.setUsuario(txtUsuario.getText());
        conexao.setSenha(txtSenha.getText());
        conexao.setDriver(txtDriver.getText());
		
        
        //Projeto
		Projeto projeto = new Projeto();
		projeto.setNomeProjeto(txtNomeProjeto.getText());
		projeto.setProjetoWeb(cbIsWeb.isSelected());
		projeto.setConexao(conexao);
		
		
        for(int i=0; i<listaClasses.size(); i++) {
        	for(int j=0; j<listaClasses.get(i).getAtributos().size(); j++) {
        		if("id".equals(listaClasses.get(i).getAtributos().get(j).getNomeAtributo())) {
        			if(projeto.getProjetoWeb()) {
        				listaClasses.get(i).getAtributos().get(j).setTipoAtributo("Integer");
        			}else {
        				listaClasses.get(i).getAtributos().get(j).setTipoAtributo("Long");
        			}
        		}
        	}
        }
		
		//Classes
		projeto.setClasses(getListaClasses());
		
		projeto.setDiretorioProjeto(txtCaminho.getText() + "\\" + txtNomeProjeto.getText());
		
		return projeto;
	}
	
	private void montaClassesTeste() {
		Classe classe1 = new Classe();
		classe1.setDiretorioClasse("com.classes");
		classe1.setNomeClasse("Pessoa");
		Atributo at1 = new Atributo();
		at1.setNomeAtributo("id");
//		at1.setTipoAtributo("Long"); //desktop
		at1.setTipoAtributo("Integer"); //web
		
		at1.setConsultaPor(false);
		at1.setApareceNaConsulta(true);
		String anotacao1 = "@Id";
		String anotacao2 = "@GeneratedValue(strategy = GenerationType.IDENTITY)";
		List<String> anotacoes = new ArrayList<>();
		anotacoes.add(anotacao1);
		anotacoes.add(anotacao2);
		at1.setAnotacao(anotacoes);
		List<Atributo> atributos = new ArrayList<>();
		atributos.add(at1);
		Atributo at2 = new Atributo();
		at2.setConsultaPor(true);
		at2.setApareceNaConsulta(true);
//		at2.setIsObrigatorio(true);
		List<String> anotacoes21 = new ArrayList<>();
		anotacoes21.add(new String("@Column(name = \"nome\", length = 11, nullable = true)"));
		at2.setAnotacao(anotacoes21);
		at2.setIsObrigatorio(true);
		
		at2.setNomeAtributo("nome");
		at2.setTipoAtributo("String");
		atributos.add(at2);
		Atributo at3 = new Atributo();
		at3.setConsultaPor(false);
		at3.setApareceNaConsulta(true);
		at3.setConsultaPor(true);
		at3.setNomeAtributo("sobrenome");
		at3.setTipoAtributo("String");
		atributos.add(at3);
		Atributo at4 = new Atributo();
		at4.setConsultaPor(false);
		at4.setApareceNaConsulta(true);
		at4.setIsRelacionamento(true);
		at4.setNomeAtributo("animal");
		at4.setTipoAtributo("Animal");
		
		String anotacao3 = "@ManyToOne";
		String anotacao4 = "@JoinColumn(name = \"animal\")";
		List<String> anotacoes1 = new ArrayList<>();
		anotacoes1.add(anotacao3);
		anotacoes1.add(anotacao4);
		at4.setAnotacao(anotacoes1);
//		
		atributos.add(at4);
		classe1.setAtributos(atributos);
		listaClasses.add(classe1);

		//Classe 2
		Classe classe2 = new Classe();
		classe2.setDiretorioClasse("com.classes.cliente");
		classe2.setNomeClasse("Animal");
		Atributo at5 = new Atributo();
		at5.setConsultaPor(false);
		at5.setApareceNaConsulta(true);
		at5.setNomeAtributo("id");
		at5.setTipoAtributo("Integer");
		String anotacao5 = "@Id";
		String anotacao6 = "@GeneratedValue(strategy = GenerationType.IDENTITY)";
		List<String> anotacoes2 = new ArrayList<>();
		anotacoes2.add(anotacao5);
		anotacoes2.add(anotacao6);
		at5.setAnotacao(anotacoes2);
		List<Atributo> atributos2 = new ArrayList<>();
		atributos2.add(at5);
		Atributo at6 = new Atributo();
		at6.setConsultaPor(true);
		at6.setApareceNaConsulta(true);
		at6.setNomeAtributo("nome");
		at6.setTipoAtributo("String");
		atributos2.add(at6);
		classe2.setAtributos(atributos2);
		listaClasses.add(classe2);

		System.out.println("aa");
		// Classe 3
//		Classe classe3 = new Classe();
//		classe3.setDiretorioClasse("com.classes.cliente");
//		classe3.setNomeClasse("Pedido");
//		Atributo at5 = new Atributo();
//		at5.setNomeAtributo("id");
//		at5.setTipoAtributo("Long");
//		String anotacao5 = "@Id";
//		String anotacao6 = "@GeneratedValue(strategy = GenerationType.IDENTITY)";
//		List<String> anotacoes3 = new ArrayList<>();
//		anotacoes3.add(anotacao5);
//		anotacoes3.add(anotacao6);
//		at5.setAnotacao(anotacoes3);
//		List<Atributo> atributos3 = new ArrayList<>();
//		atributos3.add(at5);
//		Atributo at6 = new Atributo();
//		at6.setNomeAtributo("valorTotal");
//		at6.setTipoAtributo("BigDecimal");
//		atributos3.add(at6);
//		classe3.setAtributos(atributos3);
//		listaClasses.add(classe3);
	}
	
    private void gerarProjetoMaven(Projeto projeto) throws IOException, InterruptedException {
        File diretorio = new File(projeto.getDiretorioProjeto());
        if (!diretorio.exists()) {
            if (!diretorio.mkdirs()) {
                throw new IOException("Não foi possível criar o diretório: " + projeto.getDiretorioProjeto());
            }
        }
        
		if (projeto.getProjetoWeb()) {
			// web
			montaPomXmlWeb(projeto);
			montaApplicationProperties(projeto);
			montaNotFoundException(projeto);
			montaMainController(projeto);
			criarClasses(projeto, true);
			montaRepositoryForClass(projeto);
			montaServiceForClass(projeto);
			montaControllerForClass(projeto);
			criarIndex(projeto);
			criarListar(projeto);
			criarFormulario(projeto);
		} else {
			// desktop
			montaPomXml(projeto);
			criarClasses(projeto, false);
			montaPersistenceXml(projeto);
			gerarTelasCadastro(projeto);
		}
        
        JOptionPane.showMessageDialog(null, "Projeto gerado com sucesso!");
        
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "mvn clean install");
        builder.directory(diretorio);
        builder.redirectErrorStream(true);
        Process process = builder.start();
        process.waitFor();
    }
    
    private void gerarMenu(Projeto projeto) throws IOException {
        String diretorioTelas = projeto.getDiretorioProjeto() + "\\src\\main\\java\\com\\"+projeto.getNomeProjeto()+"\\view";
        Files.createDirectories(Paths.get(diretorioTelas));
    	
    	StringBuilder stringBuilder = new StringBuilder();
    	stringBuilder.append("package com."+projeto.getNomeProjeto()+".view;\n\n");
    	stringBuilder.append("import java.awt.EventQueue;\n\n");
    	stringBuilder.append("import javax.swing.JFrame;\n");
    	stringBuilder.append("import java.awt.Color;\n");
    	stringBuilder.append("import javax.swing.JMenuBar;\n");
    	stringBuilder.append("import javax.swing.JMenu;\n");
    	stringBuilder.append("import javax.swing.JMenuItem;\n");
    	stringBuilder.append("import javax.swing.UIManager;\n");
    	stringBuilder.append("import java.awt.event.ActionListener;\n");
    	stringBuilder.append("import java.awt.event.ActionEvent;\n\n");
    	stringBuilder.append("public class TelaMenu extends JFrame {\n");
    	stringBuilder.append("    private JMenuBar menuBar;\n");
    	stringBuilder.append("    private JMenu mnCadastros;\n");
    	
    	for(Classe classe : projeto.getClasses()) {
        	stringBuilder.append("    private JMenuItem menuItem"+classe.getNomeClasse()+";\n");
    	}
    	
    	stringBuilder.append("    public static void main(String[] args) {\n");
    	stringBuilder.append("        EventQueue.invokeLater(new Runnable() {\n");
    	stringBuilder.append("            public void run() {\n");
    	stringBuilder.append("                try {\n");
    	stringBuilder.append("                    UIManager.setLookAndFeel(\"com.sun.java.swing.plaf.windows.WindowsLookAndFeel\");\n");
    	stringBuilder.append("                    TelaMenu window = new TelaMenu();\n");
    	stringBuilder.append("                    window.setVisible(true);\n");
    	stringBuilder.append("                } catch (Exception e) {\n");
    	stringBuilder.append("                    e.printStackTrace();\n");
    	stringBuilder.append("                }\n");
    	stringBuilder.append("            }\n");
    	stringBuilder.append("        });\n");
    	stringBuilder.append("    }\n\n");
    	stringBuilder.append("    public TelaMenu() {\n");
    	stringBuilder.append("        initialize();\n");
    	stringBuilder.append("    }\n\n");
    	stringBuilder.append("    private void initialize() {\n");
    	stringBuilder.append("        getContentPane().setBackground(new Color(255, 255, 255));\n");
    	stringBuilder.append("        setTitle(\"Menu\");\n");
    	stringBuilder.append("        setBounds(100, 100, 450, 300);\n");
    	stringBuilder.append("        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);\n");
    	stringBuilder.append("        getContentPane().setLayout(null);\n");
    	stringBuilder.append("        getContentPane().add(getMenuBar_1());\n");
    	stringBuilder.append("    }\n\n");
    	stringBuilder.append("    private JMenuBar getMenuBar_1() {\n");
    	stringBuilder.append("        if (menuBar == null) {\n");
    	stringBuilder.append("            menuBar = new JMenuBar();\n");
    	stringBuilder.append("            menuBar.setBounds(0, 0, 434, 51);\n");
    	stringBuilder.append("            menuBar.add(getMnCadastros());\n");
    	stringBuilder.append("        }\n");
    	stringBuilder.append("        return menuBar;\n");
    	stringBuilder.append("    }\n\n");
    	stringBuilder.append("    private JMenu getMnCadastros() {\n");
    	stringBuilder.append("        if (mnCadastros == null) {\n");
    	stringBuilder.append("            mnCadastros = new JMenu(\"Cadastros\");\n");
    	
    	
    	for(Classe classe : projeto.getClasses()) {
    		stringBuilder.append("            mnCadastros.add(getMenuItem"+classe.getNomeClasse()+"());\n");
    	}
    	
    	stringBuilder.append("        }\n");
    	stringBuilder.append("        return mnCadastros;\n");
    	stringBuilder.append("    }\n\n");
    	
    	
    	for(Classe classe : projeto.getClasses()) {
        	stringBuilder.append("    private JMenuItem getMenuItem"+classe.getNomeClasse()+"() {\n");
        	stringBuilder.append("        if (menuItem"+classe.getNomeClasse()+" == null) {\n");
        	stringBuilder.append("            menuItem"+classe.getNomeClasse()+" = new JMenuItem(\""+classe.getNomeClasse()+"\");\n");
        	stringBuilder.append("            menuItem"+classe.getNomeClasse()+".addActionListener(new ActionListener() {\n");
        	stringBuilder.append("                public void actionPerformed(ActionEvent e) {\n");
        	stringBuilder.append("                    Tela"+classe.getNomeClasse()+" screen = new Tela"+classe.getNomeClasse()+"();\n");
        	stringBuilder.append("                    screen.setVisible(true);\n");
        	stringBuilder.append("                }\n");
        	stringBuilder.append("            });\n");
        	stringBuilder.append("        }\n");
        	stringBuilder.append("        return menuItem"+classe.getNomeClasse()+";\n");
        	stringBuilder.append("    }\n\n");
    	}    	
    	
    	stringBuilder.append("}\n");
    	
    	Files.write(Paths.get(diretorioTelas + "\\" + "TelaMenu.java"), stringBuilder.toString().getBytes());
    }
    
    private void gerarTelasCadastro(Projeto projeto) throws IOException {
        String diretorioTelas = projeto.getDiretorioProjeto() + "\\src\\main\\java\\com\\"+projeto.getNomeProjeto()+"\\view";
        Files.createDirectories(Paths.get(diretorioTelas));

        for (Classe classe : projeto.getClasses()) {
            
            StringBuilder codigoTela = new StringBuilder();
            codigoTela.append("package com."+projeto.getNomeProjeto()+".view;\n\n");
            codigoTela.append("import java.awt.EventQueue;\n");
            codigoTela.append("import javax.swing.JDialog;\n");
            codigoTela.append("import java.awt.Color;\n");
            codigoTela.append("import javax.swing.JPanel;\n");
            codigoTela.append("import javax.swing.JLabel;\n");
            codigoTela.append("import javax.swing.JOptionPane;\n");
            codigoTela.append("import javax.swing.SwingConstants;\n");
            codigoTela.append("import javax.swing.UIManager;\n");
            codigoTela.append("import javax.swing.table.DefaultTableModel;\n");
            codigoTela.append("import java.util.Objects;\n");
            
            for(int i=0; i<classe.getAtributos().size(); i++) {
            	if(Objects.nonNull(classe.getAtributos().get(i).getIsRelacionamento()) && classe.getAtributos().get(i).getIsRelacionamento()) {
            		codigoTela.append("import com."+projeto.getNomeProjeto()+".dao."+classe.getAtributos().get(i).getTipoAtributo()+"DAO;\n");
            		codigoTela.append("import com."+projeto.getNomeProjeto()+".model."+classe.getAtributos().get(i).getTipoAtributo()+";\n");
            		
            	}
            }
            
            
            
            
            codigoTela.append("import ").append("com."+projeto.getNomeProjeto()).append(".model.").append(classe.getNomeClasse()).append(";\n\n");
            codigoTela.append("import ").append("com.").append(projeto.getNomeProjeto()).append(".dao.").append(classe.getNomeClasse()).append("DAO;\n\n");

            codigoTela.append("import java.awt.Font;\n");
            codigoTela.append("import javax.swing.JTabbedPane;\n");
            codigoTela.append("import javax.swing.JTable;\n");
            codigoTela.append("import javax.swing.JTextField;\n");
            codigoTela.append("import javax.swing.JButton;\n");
            codigoTela.append("import javax.swing.JScrollPane;\n");
            codigoTela.append("import java.awt.event.WindowAdapter;\n");
            codigoTela.append("import java.awt.event.WindowEvent;\n");
            codigoTela.append("import java.util.List;\n");
            codigoTela.append("import java.awt.event.MouseAdapter;\n");
            codigoTela.append("import java.awt.event.MouseEvent;\n");
            codigoTela.append("import java.awt.event.ActionListener;\n");
            codigoTela.append("import java.awt.event.ActionEvent;\n\n");
            for(Atributo atr : classe.getAtributos()) {
            	if("Boolean".equals(atr.getTipoAtributo())) {
            		codigoTela.append("import javax.swing.JCheckBox;\n\n");
            		break;
            	}
            }
            
            
            
            codigoTela.append("public class Tela").append(classe.getNomeClasse()).append(" extends JDialog {\n\n");
            codigoTela.append("	private JPanel panelCabecalho;\n");
            codigoTela.append("	private JLabel lbCabecalho;\n");
            codigoTela.append("	private JTabbedPane tabbedPane;\n");
            codigoTela.append("	private JPanel panelCampos;\n");
            codigoTela.append("	private JPanel panelTabela;\n");
            codigoTela.append("	private JLabel lbBuscar;\n");
            codigoTela.append("	private JTextField txtBuscar;\n");
            codigoTela.append("	private JButton btnBuscar;\n");
            codigoTela.append("	private JButton btnNovo;\n");
            codigoTela.append("	private JButton btnSalvar;\n");
            codigoTela.append("	private JButton btnEditar;\n");
            codigoTela.append("	private JButton btnExcluir;\n");
            codigoTela.append("	private JScrollPane scrollPane;\n");
            codigoTela.append("	private JTable table;\n");
            
            for(Atributo atr : classe.getAtributos()){
            	
				if ("Boolean".equals(atr.getTipoAtributo())) {
					codigoTela.append("	private JCheckBox cb").append(atr.getNomeAtributo()).append(";\n");
				} else {
					codigoTela.append("	private JLabel lb").append(atr.getNomeAtributo()).append(";\n");
					codigoTela.append("	private JTextField txt").append(atr.getNomeAtributo()).append(";\n");
				}

        		
        		if(Objects.nonNull(atr.getIsRelacionamento()) && atr.getIsRelacionamento()) {
        			codigoTela.append("	private JButton btnBuscar"+atr.getTipoAtributo()+";\n");
        			
        			codigoTela.append("	private "+atr.getTipoAtributo()+" retorno;\n");
        		}
        	}
            codigoTela.append("	private "+classe.getNomeClasse()+" retorno"+classe.getNomeClasse()+";\n");
        	codigoTela.append("	private Boolean subtela;\n");
        	
        	codigoTela.append("	public static void main(String[] args) {\n");
            codigoTela.append("    EventQueue.invokeLater(new Runnable() {\n");
            codigoTela.append("        public void run() {\n");
            codigoTela.append("            try {\n");
            codigoTela.append("                UIManager.setLookAndFeel(\"com.sun.java.swing.plaf.windows.WindowsLookAndFeel\");\n");
        	codigoTela.append("                Tela").append(classe.getNomeClasse()).append(" window = new Tela").append(classe.getNomeClasse()).append("();\n");
            codigoTela.append("                window.setVisible(true);\n");
            codigoTela.append("            } catch (Exception e) {\n");
            codigoTela.append("                e.printStackTrace();\n");
            codigoTela.append("            }\n");
            codigoTela.append("        }\n");
            codigoTela.append("    });\n");
            codigoTela.append("	}\n"); 
        	
        	codigoTela.append("	public Tela").append(classe.getNomeClasse()).append("(){\n");
        	codigoTela.append("  initialize();\n");
        	codigoTela.append(" }\n");
        	
        	codigoTela.append("	private void initialize() {\n");
            codigoTela.append("    addWindowListener(new WindowAdapter() {\n");
            codigoTela.append("        @Override\n");
            codigoTela.append("        public void windowOpened(WindowEvent e) {\n");
            codigoTela.append("            listar();\n");
            codigoTela.append("				if(Objects.nonNull(getSubtela()) && getSubtela()) {\n");
            codigoTela.append("					tabbedPane.setSelectedIndex(1);\n");
            codigoTela.append("					setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);\n");
            codigoTela.append("				}\n");
            codigoTela.append("        }\n");
            codigoTela.append("    });\n");
            codigoTela.append("    setModal(true);;\n");
            codigoTela.append("    setTitle(\"Tela de ").append(classe.getNomeClasse()).append("\");\n");
            codigoTela.append("    getContentPane().setBackground(new Color(255, 255, 255));\n");
            codigoTela.append("    setBackground(new Color(255, 255, 255));\n");
            codigoTela.append("    this.setBounds(100, 100, 605, 512);\n");
            codigoTela.append("    this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);\n");
            codigoTela.append("    getContentPane().setLayout(null);\n");
            codigoTela.append("    getContentPane().add(getPanelCabecalho());\n");
            codigoTela.append("    getContentPane().add(getTabbedPane());\n");
            codigoTela.append("    getContentPane().add(getBtnNovo());\n");
            codigoTela.append("    getContentPane().add(getBtnSalvar());\n");
            codigoTela.append("    getContentPane().add(getBtnEditar());\n");
            codigoTela.append("    getContentPane().add(getBtnExcluir());\n");
            codigoTela.append("	}\n");
            
        	codigoTela.append("	private JPanel getPanelCabecalho() {\n");
            codigoTela.append("    if (panelCabecalho == null) {\n");
            codigoTela.append("        panelCabecalho = new JPanel();\n");
            codigoTela.append("        panelCabecalho.setBackground(new Color(30, 144, 255));\n");
            codigoTela.append("        panelCabecalho.setBounds(0, 0, 589, 57);\n");
            codigoTela.append("        panelCabecalho.setLayout(null);\n");
            codigoTela.append("        panelCabecalho.add(getLbCabecalho());\n");
            codigoTela.append("    }\n");
            codigoTela.append("    return panelCabecalho;\n");
            codigoTela.append("	}\n");
            
            codigoTela.append("	private JLabel getLbCabecalho() {\n");
            codigoTela.append("    if (lbCabecalho == null) {\n");
        	codigoTela.append("		   lbCabecalho = new JLabel(\"Tela de ").append(classe.getNomeClasse()).append("\");\n");
        	codigoTela.append("        lbCabecalho.setForeground(new Color(255, 255, 255));\n");
            codigoTela.append("        lbCabecalho.setFont(new Font(\"Tahoma\", Font.PLAIN, 28));\n");
            codigoTela.append("        lbCabecalho.setHorizontalAlignment(SwingConstants.CENTER);\n");
            codigoTela.append("        lbCabecalho.setBounds(0, 0, 589, 57);\n");
            codigoTela.append("    }\n");
            codigoTela.append("    return lbCabecalho;\n");
            codigoTela.append("	}\n");
            
        	codigoTela.append("	private JTabbedPane getTabbedPane() {\n");
            codigoTela.append("    if (tabbedPane == null) {\n");
            codigoTela.append("        tabbedPane = new JTabbedPane();\n");
            codigoTela.append("        tabbedPane.setAlignmentY(5.0f);\n");
            codigoTela.append("        tabbedPane.setAlignmentX(5.0f);\n");
            codigoTela.append("        tabbedPane.setBounds(10, 81, 569, 329);\n");
            codigoTela.append("        tabbedPane.addTab(\"Campos\", null, getPanelCampos(), null);\n");
            codigoTela.append("        tabbedPane.addTab(\"Consulta\", null, getPanelTabela(), null);\n");
            codigoTela.append("    }\n");
            codigoTela.append("    return tabbedPane;\n");
            codigoTela.append("	}\n");
            
            codigoTela.append("	private JPanel getPanelCampos() {\n");
            codigoTela.append("    if (panelCampos == null) {\n");
            codigoTela.append("        panelCampos = new JPanel();\n");
            codigoTela.append("        panelCampos.setBackground(new Color(255, 255, 255));\n");
            codigoTela.append("        panelCampos.setLayout(null);\n");
            
            for(Atributo atr : classe.getAtributos()){
            	if ("Boolean".equals(atr.getTipoAtributo())) {
            		codigoTela.append("        panelCampos.add(getCb").append(atr.getNomeAtributo()).append("());\n");
            	}else {
            		codigoTela.append("        panelCampos.add(getTxt").append(atr.getNomeAtributo()).append("());\n");
            		codigoTela.append("        panelCampos.add(getLb").append(atr.getNomeAtributo()).append("());\n");
            	}

        		
        		if(Objects.nonNull(atr.getIsRelacionamento()) && atr.getIsRelacionamento()) {
        			codigoTela.append("		   panelCampos.add(getBtnBuscar"+atr.getTipoAtributo()+"());");
        		}
        	}
            
            codigoTela.append("    }\n");
            codigoTela.append("    return panelCampos;\n");
            codigoTela.append("	}\n");
            
        	codigoTela.append("	private JPanel getPanelTabela() {\n");
            codigoTela.append("    if (panelTabela == null) {\n");
            codigoTela.append("        panelTabela = new JPanel();\n");
            codigoTela.append("        panelTabela.setBackground(new Color(255, 255, 255));\n");
            codigoTela.append("        panelTabela.setLayout(null);\n");
            codigoTela.append("        panelTabela.add(getLbBuscar());\n");
            codigoTela.append("        panelTabela.add(getTxtBuscar());\n");
            codigoTela.append("        panelTabela.add(getBtnBuscar());\n");
            codigoTela.append("        panelTabela.add(getScrollPane());\n");
            codigoTela.append("    }\n");
            codigoTela.append("    return panelTabela;\n");
            codigoTela.append("	}\n");
            
        	int y = 23;
        	int x = 10;
        	int z = 114;
        	
        	//Ao criar modificar para identificar chave primaria e deixar o campo não editavel
        	for(Atributo atr : classe.getAtributos()){
        		String nomeAtributo = atr.getNomeAtributo();

        		if("Boolean".equals(atr.getTipoAtributo())) {
        			
        			codigoTela.append("private JCheckBox getCb"+nomeAtributo+"() {\n");
        			codigoTela.append("    if (cb"+nomeAtributo+" == null) {\n");
        			codigoTela.append("        cb"+nomeAtributo+" = new JCheckBox(\""+nomeAtributo+"\");\n");
        			codigoTela.append("        cb"+nomeAtributo+".setBounds("+z+", "+(y-2)+", 97, 23);\n");
        			codigoTela.append("    }\n");
        			codigoTela.append("    return cb"+nomeAtributo+";\n");
        			codigoTela.append("}\n");
        			
        		} else {
            	    codigoTela.append("private JLabel getLb"+nomeAtributo+"() {\n");
            	    codigoTela.append("    if (lb"+nomeAtributo+"== null) {\n");
            	    codigoTela.append("        lb"+nomeAtributo+" = new JLabel(\""+nomeAtributo+":\");\n");
            	    codigoTela.append("        lb"+nomeAtributo+".setFont(new Font(\"Tahoma\", Font.PLAIN, 13));\n");
            	    codigoTela.append("        lb"+nomeAtributo+".setBounds("+x+", "+y+", 102, 14);\n");
            	    codigoTela.append("    }\n");
            	    codigoTela.append("    return lb"+nomeAtributo+";\n");
            	    codigoTela.append("}\n");
            	    
            	    codigoTela.append("private JTextField getTxt"+nomeAtributo+"() {\n");
            		codigoTela.append("    if (txt"+nomeAtributo+" == null) {\n");
            		codigoTela.append("        txt"+nomeAtributo+" = new JTextField();\n");
            		
            		if("id".equals(atr.getNomeAtributo())) {
            			codigoTela.append("        txt"+nomeAtributo+".setEditable(false);\n");
            		}
            		
            		codigoTela.append("        txt"+nomeAtributo+".setFont(new Font(\"Tahoma\", Font.PLAIN, 13));\n");
            		codigoTela.append("        txt"+nomeAtributo+".setBounds("+z+", "+(y-2)+", 144, 20);\n");
            		codigoTela.append("        txt"+nomeAtributo+".setColumns(10);\n");
            		codigoTela.append("    }\n");
            		codigoTela.append("    return txt"+nomeAtributo+";\n");
            		codigoTela.append("}\n");
        		}
        	    
        		if(Objects.nonNull(atr.getIsRelacionamento()) && atr.getIsRelacionamento()) {
        			codigoTela.append("    private JButton getBtnBuscar"+atr.getTipoAtributo()+"() {\n");
        			codigoTela.append("        if (btnBuscar"+atr.getTipoAtributo()+" == null) {\n");
        			codigoTela.append("            btnBuscar"+atr.getTipoAtributo()+" = new JButton(\"Buscar\");\n");
        			codigoTela.append("            btnBuscar"+atr.getTipoAtributo()+".addActionListener(new ActionListener() {\n");
        			codigoTela.append("                public void actionPerformed(ActionEvent e) {\n");
        			codigoTela.append("                    Tela"+atr.getTipoAtributo()+" screen = new Tela"+atr.getTipoAtributo()+"();\n");
        			codigoTela.append("                    screen.setSubtela(true);\n");
        			codigoTela.append("                    screen.setVisible(true);\n");
        			codigoTela.append("                    if (Objects.nonNull(screen.getRetorno"+atr.getTipoAtributo()+"())) {\n");
        			
        			String searchByField = "";
        			for(int i=0; i<listaClasses.size(); i++) {
        				if(listaClasses.get(i).getNomeClasse().equals(atr.getTipoAtributo())) {
        					for(int j=0; j<listaClasses.get(i).getAtributos().size(); j++) {
        						if(listaClasses.get(i).getAtributos().get(j).getConsultaPor()) {
        							searchByField = listaClasses.get(i).getAtributos().get(j).getNomeAtributo();
        							searchByField = searchByField.substring(0, 1).toUpperCase() + searchByField.substring(1);

        						}
        					}
        				}
        			}
        			codigoTela.append("                        txt"+atr.getNomeAtributo()+".setText(screen.getRetorno"+atr.getTipoAtributo()+"().get"+searchByField+"());\n");
        			
        			
        			codigoTela.append("                    }\n");
        			codigoTela.append("                }\n");
        			codigoTela.append("            });\n");
        			codigoTela.append("            btnBuscar"+atr.getTipoAtributo()+".setBounds(270, "+(y-2)+", 89, 23);\n");
        			codigoTela.append("        }\n");
        			codigoTela.append("        return btnBuscar"+atr.getTipoAtributo()+";\n");
        			codigoTela.append("    }\n\n");
        		}
        		
        	    y+=27;
        	    if(y==266){
        	    y=23;
        	    x=280;
        	    z = 384;
        	    }
            }
        	
        	codigoTela.append("	private JLabel getLbBuscar() {\n");
            codigoTela.append("    if (lbBuscar == null) {\n");
            codigoTela.append("        lbBuscar = new JLabel(\"Buscar:\");\n");
            codigoTela.append("        lbBuscar.setFont(new Font(\"Tahoma\", Font.PLAIN, 13));\n");
            codigoTela.append("        lbBuscar.setBounds(10, 13, 56, 14);\n");
            codigoTela.append("    }\n");
            codigoTela.append("    return lbBuscar;\n");
            codigoTela.append("	}\n");
            
            codigoTela.append("	private JTextField getTxtBuscar() {\n");
            codigoTela.append("    if (txtBuscar == null) {\n");
            codigoTela.append("        txtBuscar = new JTextField();\n");
            codigoTela.append("        txtBuscar.setFont(new Font(\"Tahoma\", Font.PLAIN, 13));\n");
            codigoTela.append("        txtBuscar.setColumns(10);\n");
            codigoTela.append("        txtBuscar.setBounds(64, 10, 354, 20);\n");
            codigoTela.append("    }\n");
            codigoTela.append("    return txtBuscar;\n");
            codigoTela.append("	}\n");
            
        	codigoTela.append("	private JButton getBtnBuscar() {\n");
            codigoTela.append("    if (btnBuscar == null) {\n");
            codigoTela.append("        btnBuscar = new JButton(\"Buscar\");\n");
            codigoTela.append("        btnBuscar.addActionListener(new ActionListener() {\n");
            codigoTela.append("            public void actionPerformed(ActionEvent e) {\n");
        	codigoTela.append("                ").append(classe.getNomeClasse()).append("DAO dao = new ").append(classe.getNomeClasse()).append("DAO();\n");
            codigoTela.append("                List<").append(classe.getNomeClasse()).append("> lista = dao.findBy");
            
            for(Atributo atr : classe.getAtributos()){
        		if(atr.getConsultaPor()){
        			String nomeAtributo = atr.getNomeAtributo();
        			String nomeAtrMaiusculo = nomeAtributo.substring(0, 1).toUpperCase() + nomeAtributo.substring(1);

        			codigoTela.append(nomeAtrMaiusculo).append("(txtBuscar.getText());\n");
        			break;
        		}
        	}
            
            codigoTela.append("			if(lista.isEmpty()) {\n");
            codigoTela.append("				lista = dao.findAll();\n");
            codigoTela.append("			}\n\n");

            
            
            codigoTela.append("                DefaultTableModel dados = (DefaultTableModel) table.getModel();\n");
            codigoTela.append("                dados.setNumRows(0);\n");
            codigoTela.append("                for(").append(classe.getNomeClasse()).append(" obj : lista)").append("{\n");
            codigoTela.append("                    dados.addRow(new Object[]{\n");
            
        	for(Atributo atr : classe.getAtributos()){
    			String nomeAtributo = atr.getNomeAtributo();
    			String nomeAtrMaiusculo = nomeAtributo.substring(0, 1).toUpperCase() + nomeAtributo.substring(1);
        		if(atr.getApareceNaConsulta() && Objects.isNull(atr.getIsRelacionamento()) || !atr.getIsRelacionamento()){
        			codigoTela.append("                        obj.get").append(nomeAtrMaiusculo).append("(),\n");
        		}
        		if(Objects.nonNull(atr.getIsRelacionamento()) && atr.getIsRelacionamento()) {
        			String searchByField = "";
        			for(int i=0; i<listaClasses.size(); i++) {
        				if(listaClasses.get(i).getNomeClasse().equals(atr.getTipoAtributo())) {
        					for(int j=0; j<listaClasses.get(i).getAtributos().size(); j++) {
        						if(listaClasses.get(i).getAtributos().get(j).getConsultaPor()) {
        							searchByField = listaClasses.get(i).getAtributos().get(j).getNomeAtributo();
        							searchByField = searchByField.substring(0, 1).toUpperCase() + searchByField.substring(1);

        						}
        					}
        				}
        			}
        			
        			codigoTela.append("                        obj.get").append(atr.getTipoAtributo()).append("().get").append(searchByField+"(),\n");
        		}
        	}
        	
            codigoTela.append("                    });\n");
            codigoTela.append("                }\n");
            codigoTela.append("            }\n");
            codigoTela.append("        });\n");
            codigoTela.append("        btnBuscar.setBounds(431, 10, 89, 23);\n");
            codigoTela.append("    }\n");
            codigoTela.append("    return btnBuscar;\n");
            codigoTela.append("	}\n");
            
        	codigoTela.append("	private JButton getBtnNovo() {\n");
            codigoTela.append("    if (btnNovo == null) {\n");
            codigoTela.append("        btnNovo = new JButton(\"Novo\");\n");
            codigoTela.append("        btnNovo.addActionListener(new ActionListener() {\n");
            codigoTela.append("            public void actionPerformed(ActionEvent e) {\n");
            codigoTela.append("                limpaTela();\n");
            codigoTela.append("            }\n");
            codigoTela.append("        });\n");
            codigoTela.append("        btnNovo.setBounds(85, 421, 89, 30);\n");
            codigoTela.append("    }\n");
            codigoTela.append("    return btnNovo;\n");
            codigoTela.append("	}\n");
            
        	codigoTela.append("	private JButton getBtnSalvar() {\n");
            codigoTela.append("    if (btnSalvar == null) {\n");
            codigoTela.append("        btnSalvar = new JButton(\"Salvar\");\n");
            codigoTela.append("        btnSalvar.addActionListener(new ActionListener() {\n");
            codigoTela.append("            public void actionPerformed(ActionEvent e) {\n");
            codigoTela.append("                ").append(classe.getNomeClasse()).append("DAO dao = new ").append(classe.getNomeClasse()).append("DAO();\n");
            codigoTela.append("                ").append(classe.getNomeClasse()).append(" obj = buildObject()").append(";\n");
            
            codigoTela.append("                if (Objects.nonNull(obj)) {\n");
            codigoTela.append("                		dao.save(obj);\n");
            codigoTela.append("                		JOptionPane.showMessageDialog(null, \"").append(classe.getNomeClasse()).append(" salvo(a) com Sucesso!\");\n");
            codigoTela.append("                		limpaTela();\n");
            codigoTela.append("        		   }\n");
            
            codigoTela.append("            }\n");
            codigoTela.append("        });\n");
            codigoTela.append("        btnSalvar.setBounds(193, 421, 89, 30);\n");
            codigoTela.append("    }\n");
            codigoTela.append("    return btnSalvar;\n");
            codigoTela.append("	}\n");
            
        	codigoTela.append("            private ").append(classe.getNomeClasse()).append(" buildObject(){\n");
        	codigoTela.append("                  ").append(classe.getNomeClasse()).append(" obj = new ").append(classe.getNomeClasse()).append("();\n");
        	
        	for(Atributo atr : classe.getAtributos()){
    			String nomeAtributo = atr.getNomeAtributo();
    			String nomeAtrMaiusculo = nomeAtributo.substring(0, 1).toUpperCase() + nomeAtributo.substring(1);
        		Boolean notid = true;
        		List<String> anotacoes = atr.getAnotacao();
        		if(Objects.nonNull(anotacoes)) {
	        		for(String anot : anotacoes){
	        			if(Objects.nonNull(anot) && anot.contains("@Id")){
	        				notid = false;
	        				codigoTela.append("                  ").append("if(!txt").append(atr.getNomeAtributo()).append(".getText().isEmpty()){\n");
	        				codigoTela.append("                            ").append("obj.setId(Long.parseLong(txt").append(atr.getNomeAtributo()).append(".getText()));\n");
	        				codigoTela.append("}\n");
	        			}
	        		}
        		}
        		if(notid){
        			Boolean isrelacionamento = false;
        			Boolean isboolean = false;
        			Boolean isNumber = false;
        			
					if (atr.getTipoAtributo().equals("Integer") || atr.getTipoAtributo().equals("Long")
							|| atr.getTipoAtributo().equals("Float")) {
						isNumber = true;

						if (atr.getTipoAtributo().equals("Integer")) {
							codigoTela.append("obj.set").append(nomeAtrMaiusculo).append("(Integer.parseInt(txt")
									.append(atr.getNomeAtributo()).append(".getText()));\n");
						}

						if (atr.getTipoAtributo().equals("Long")) {
							codigoTela.append("obj.set").append(nomeAtrMaiusculo).append("(Long.parseLong(txt")
									.append(atr.getNomeAtributo()).append(".getText()));\n");
						}

						if (atr.getTipoAtributo().equals("Float")) {
							codigoTela.append("obj.set").append(nomeAtrMaiusculo).append("(Float.parseFloat(txt")
									.append(atr.getNomeAtributo()).append(".getText()));\n");
						}

					} 
        			else if(Objects.nonNull(atr.getIsRelacionamento()) && atr.getIsRelacionamento()) {
        				isrelacionamento = true;
            			String searchByField = "";
            			for(int i=0; i<listaClasses.size(); i++) {
            				if(listaClasses.get(i).getNomeClasse().equals(atr.getTipoAtributo())) {
            					for(int j=0; j<listaClasses.get(i).getAtributos().size(); j++) {
            						if(listaClasses.get(i).getAtributos().get(j).getConsultaPor()) {
            							searchByField = listaClasses.get(i).getAtributos().get(j).getNomeAtributo();
            							searchByField = searchByField.substring(0, 1).toUpperCase() + searchByField.substring(1);
            						}
            					}
            				}
            			}
        				
        				codigoTela.append("obj.set").append(nomeAtrMaiusculo).append("(new ")
        				.append(atr.getTipoAtributo()+"DAO().findBy"+searchByField+"(txt")
        				.append(atr.getNomeAtributo()+".getText()).get(0));\n");
        			}
        			
        			
        			else if ("Boolean".equals(atr.getTipoAtributo())) {
        				isboolean = true;
        				codigoTela.append("obj.set").append(nomeAtrMaiusculo).append("(cb").append(atr.getNomeAtributo()).append(".isSelected());\n");
        			}
        			
        			if(Objects.nonNull(atr.getIsObrigatorio()) && atr.getIsObrigatorio() && !isboolean && !isNumber) {
        				codigoTela.append("if (txt"+atr.getNomeAtributo()+".getText().isEmpty()) {\n");
        				codigoTela.append("    JOptionPane.showMessageDialog(null, \"O campo "+atr.getNomeAtributo()+" é obrigatório!\");\n");
        				codigoTela.append("    txt"+atr.getNomeAtributo()+".selectAll();\n");
        				codigoTela.append("    txt"+atr.getNomeAtributo()+".requestFocus();\n");
        				codigoTela.append("    return null;\n");
        				codigoTela.append("}\n");
        			}
        			
        			if(Objects.nonNull(atr.getAnotacao()) && !isrelacionamento && !isboolean) {
        				String length = null;
        				for(String anot : atr.getAnotacao()) {
        					
        					String[] parts = anot.split(",");

        	                for (String part : parts) {
        	                    if (part.contains("length")) {
        	                        length = part.split("=")[1].trim();
        	                    }
        	                }
            				break;
        				}
        				
        				codigoTela.append("if (txt"+atr.getNomeAtributo()+".getText().length() > "+length+") {\n");
        				codigoTela.append("    obj.set"+nomeAtrMaiusculo+"(txt"+atr.getNomeAtributo()+".getText());\n");
        				codigoTela.append("} else {\n");
        				codigoTela.append("    JOptionPane.showMessageDialog(null, \"Quantidade mínima de caracteres para o campo "+atr.getNomeAtributo()+" é 11!\");\n");
        				codigoTela.append("    txt"+atr.getNomeAtributo()+".selectAll();\n");
        				codigoTela.append("    txt"+atr.getNomeAtributo()+".requestFocus();\n");
        				codigoTela.append("    return null;\n");
        				codigoTela.append("}\n");
        			}
        			
        			else {
        				if(!isrelacionamento && !isboolean && !isNumber) {
        					codigoTela.append("obj.set").append(nomeAtrMaiusculo).append("(txt").append(atr.getNomeAtributo()).append(".getText());\n");
        				}
        			}
        		}
        	}
        	codigoTela.append("return obj;\n");
        	codigoTela.append("	}\n\n");
        	
        	codigoTela.append("	private JButton getBtnEditar() {\n");
            codigoTela.append("    if (btnEditar == null) {\n");
            codigoTela.append("        btnEditar = new JButton(\"Editar\");\n");
            codigoTela.append("        btnEditar.addActionListener(new ActionListener() {\n");
            codigoTela.append("            public void actionPerformed(ActionEvent e) {\n");
            codigoTela.append("                ").append(classe.getNomeClasse()).append("DAO dao = new ").append(classe.getNomeClasse()).append("DAO();\n");
            codigoTela.append("                ").append(classe.getNomeClasse()).append(" obj = buildObject()").append(";\n");
            
            codigoTela.append("                if (Objects.nonNull(obj)) {\n");
            codigoTela.append("                		dao.save(obj);\n");
            codigoTela.append("                		JOptionPane.showMessageDialog(null, \"").append(classe.getNomeClasse()).append(" editado(a) com Sucesso!\");\n");
            codigoTela.append("                		btnSalvar.setEnabled(true);\n");
            codigoTela.append("                		limpaTela();\n");
            codigoTela.append("       		   }\n");
            
            
            codigoTela.append("            }\n");
            codigoTela.append("        });\n");
            codigoTela.append("        btnEditar.setBounds(301, 421, 89, 30);\n");
            codigoTela.append("    }\n");
            codigoTela.append("    return btnEditar;\n");
            codigoTela.append("	}\n"); 
            
        	codigoTela.append("	private JButton getBtnExcluir() {\n");
            codigoTela.append("    if (btnExcluir == null) {\n");
            codigoTela.append("        btnExcluir = new JButton(\"Excluir\");\n");
            codigoTela.append("        btnExcluir.addActionListener(new ActionListener() {\n");
            codigoTela.append("            public void actionPerformed(ActionEvent e) {\n");
            codigoTela.append("                if (!txtid.getText().isEmpty()) {\n"); //modificar para identificar chave primaria
            codigoTela.append("                    ").append(classe.getNomeClasse()).append("DAO dao = new ").append(classe.getNomeClasse()).append("DAO();\n");
            codigoTela.append("                    ").append(classe.getNomeClasse()).append(" obj = buildObject()").append(";\n");
            codigoTela.append("                    dao.delete(obj);\n");
            codigoTela.append("                    JOptionPane.showMessageDialog(null, \"").append(classe.getNomeClasse()).append(" deletado(a) com Sucesso!\");\n");
            codigoTela.append("                    limpaTela();\n");
            codigoTela.append("                }\n");
            codigoTela.append("            }\n");
            codigoTela.append("        });\n");
            codigoTela.append("        btnExcluir.setBounds(405, 421, 89, 30);\n");
            codigoTela.append("    }\n");
            codigoTela.append("    return btnExcluir;\n");
            codigoTela.append("	}\n");
            
        	codigoTela.append("	private JScrollPane getScrollPane() {\n");
            codigoTela.append("    if (scrollPane == null) {\n");
            codigoTela.append("        scrollPane = new JScrollPane();\n");
            codigoTela.append("        scrollPane.setBounds(10, 38, 544, 220);\n");
            codigoTela.append("        scrollPane.setViewportView(getTable());\n");
            codigoTela.append("    }\n");
            codigoTela.append("    return scrollPane;\n");
            codigoTela.append("	}\n");
            
            codigoTela.append("	private JTable getTable() {\n");
            codigoTela.append("    if (table == null) {\n");
            codigoTela.append("        table = new JTable();\n");
            codigoTela.append("        table.addMouseListener(new MouseAdapter() {\n");
            codigoTela.append("            @Override\n");
            codigoTela.append("            public void mouseClicked(MouseEvent e) {\n");
            
            
            codigoTela.append("					if(Objects.isNull(getSubtela()) || !getSubtela()) {\n");
            
            codigoTela.append("                tabbedPane.setSelectedIndex(0);\n");
            for(int i=0; i<classe.getAtributos().size(); i++){
        		Atributo atr = classe.getAtributos().get(i);
        		if("Boolean".equals(atr.getTipoAtributo())) {
        			codigoTela.append("                cb").append(atr.getNomeAtributo()).append(".setSelected(Boolean.parseBoolean(table.getValueAt(table.getSelectedRow(), ").append(i).append(").toString()));\n");
        		}else {
        			codigoTela.append("                txt").append(atr.getNomeAtributo()).append(".setText(table.getValueAt(table.getSelectedRow(),").append(i).append(").toString());\n");
        		}
        	}
            codigoTela.append("                btnSalvar.setEnabled(false);\n");
            codigoTela.append("					} else {\n");
            codigoTela.append("						setRetorno(new "+classe.getNomeClasse()+"DAO().findById((Long) table.getValueAt(table.getSelectedRow(), 0)));\n");
            codigoTela.append("						dispose();\n");
            codigoTela.append("					}\n");
            
            
            codigoTela.append("            }\n");
            codigoTela.append("        });\n");
            codigoTela.append("        table.setModel(new DefaultTableModel(\n");
            codigoTela.append("            new Object [][] {\n");
            codigoTela.append("            },\n");
            codigoTela.append("            new String [] {\n");
            
        	String fields = "";
        	for (Atributo atr : classe.getAtributos()) {
            	if (atr.getApareceNaConsulta()) {
                	fields += "\"" + atr.getNomeAtributo() + "\",";
            	}
        	}
        	
        	if (fields.length() > 0) {
        	    fields = fields.substring(0, fields.length() - 1);
        	}
            
            codigoTela.append("                ").append(fields).append("\n");
            
            codigoTela.append("            }\n");
            codigoTela.append("        ));\n");
            codigoTela.append("    }\n");
            codigoTela.append("    return table;\n");
            codigoTela.append("	}\n");
            
        	codigoTela.append("	public void listar() {\n");
            codigoTela.append("    ").append(classe.getNomeClasse()).append("DAO dao = new ").append(classe.getNomeClasse()).append("DAO();\n");
            codigoTela.append("    List<").append(classe.getNomeClasse()).append("> lista = dao.findAll();\n");
            codigoTela.append("    DefaultTableModel dados = (DefaultTableModel) table.getModel();\n");
            codigoTela.append("    dados.setNumRows(0);\n");
            codigoTela.append("    for(").append(classe.getNomeClasse()).append(" obj : lista)").append("{\n");
            codigoTela.append("        dados.addRow(new Object[]{\n");
            
			for (Atributo atr : classe.getAtributos()) {
				String nomeAtributo = atr.getNomeAtributo();
				String nomeAtrMaiusculo = nomeAtributo.substring(0, 1).toUpperCase() + nomeAtributo.substring(1);
				if (atr.getApareceNaConsulta()) {
	        		if(Objects.nonNull(atr.getIsRelacionamento()) && atr.getIsRelacionamento()) {
            			String searchByField = "";
            			for(int i=0; i<listaClasses.size(); i++) {
            				if(listaClasses.get(i).getNomeClasse().equals(atr.getTipoAtributo())) {
            					for(int j=0; j<listaClasses.get(i).getAtributos().size(); j++) {
            						if(listaClasses.get(i).getAtributos().get(j).getConsultaPor()) {
            							searchByField = listaClasses.get(i).getAtributos().get(j).getNomeAtributo();
            							searchByField = searchByField.substring(0, 1).toUpperCase() + searchByField.substring(1);

            						}
            					}
            				}
            			}
						
            			codigoTela.append("            obj.get").append(nomeAtrMaiusculo).append("().get"+searchByField+"(),\n");
						
					} else {
						codigoTela.append("            obj.get").append(nomeAtrMaiusculo).append("(),\n");
					}
				}
			}
            
            codigoTela.append("        });\n");
            codigoTela.append("    }\n");
            codigoTela.append("	}\n"); 
            
            codigoTela.append("	public void limpaTela() {\n");
            
            for(Atributo atr : classe.getAtributos()){
            	if("Boolean".equals(atr.getTipoAtributo())) {
            		codigoTela.append("cb"+atr.getNomeAtributo()+".setSelected(false);\n");
            	}else {
            		codigoTela.append("    txt").append(atr.getNomeAtributo()).append(".setText(\"\");\n");
            	}
            	
            }
            
            codigoTela.append("}\n");
            
            
            
            for(int i=0; i<classe.getAtributos().size(); i++) {
            	if(Objects.nonNull(classe.getAtributos().get(i).getIsRelacionamento()) && classe.getAtributos().get(i).getIsRelacionamento()) {
                    codigoTela.append("        	public void setRetorno("+classe.getAtributos().get(i).getTipoAtributo()+" retorno) {\n");
                    codigoTela.append("        		this.retorno = retorno;\n");
                    codigoTela.append("        	}\n\n");

                    codigoTela.append("        	public "+classe.getAtributos().get(i).getTipoAtributo()+" getRetorno() {\n");
                    codigoTela.append("        		return this.retorno;\n");
                    codigoTela.append("        	}\n\n");
            	}
            }
            
            codigoTela.append("        	public void setRetorno("+classe.getNomeClasse()+" retorno) {\n");
            codigoTela.append("        		this.retorno"+classe.getNomeClasse()+" = retorno;\n");
            codigoTela.append("        	}\n\n");
            
            codigoTela.append("        	public "+classe.getNomeClasse()+" getRetorno"+classe.getNomeClasse()+"() {\n");
            codigoTela.append("        		return this.retorno"+classe.getNomeClasse()+";\n");
            codigoTela.append("        	}\n\n");

            codigoTela.append("        	public void setSubtela(Boolean subtela) {\n");
            codigoTela.append("        		this.subtela = subtela;\n");
            codigoTela.append("        	}\n\n");

            codigoTela.append("        	public Boolean getSubtela() {\n");
            codigoTela.append("        		return subtela;\n");
            codigoTela.append("        	}\n\n");

            
            codigoTela.append("	}");
            
            Files.write(Paths.get(diretorioTelas + "\\" + "Tela" +classe.getNomeClasse() + ".java"), codigoTela.toString().getBytes());
            
            if(!projeto.getProjetoWeb()) {
            	gerarMenu(projeto);
            }
        }
    }

    
    private void montaPomXml(Projeto projeto) {
        String conteudoPomXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n" +
                "         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
                "    <modelVersion>4.0.0</modelVersion>\n" +
                "\n" +
                "    <groupId>com.example</groupId>\n" +
                "    <artifactId>projeto-gerado</artifactId>\n" +
                "    <version>1.0-SNAPSHOT</version>\n" +
                "\n" +
                "    <properties>\n" +
                "        <maven.compiler.source>1.8</maven.compiler.source>\n" +
                "        <maven.compiler.target>1.8</maven.compiler.target>\n" +
                "    </properties>\n" +
                "\n" +
                "    <dependencies>\n" +
                "        <dependency>\n" +
                "            <groupId>org.hibernate</groupId>\n" +
                "            <artifactId>hibernate-core</artifactId>\n" +
                "            <version>5.5.7.Final</version>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>javax.persistence</groupId>\n" +
                "            <artifactId>javax.persistence-api</artifactId>\n" +
                "            <version>2.2</version>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>org.hibernate</groupId>\n" +
                "            <artifactId>hibernate-envers</artifactId>\n" +
                "            <version>5.5.7.Final</version>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>com.h2database</groupId>\n" +
                "            <artifactId>h2</artifactId>\n" +
                "            <version>1.4.200</version>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>mysql</groupId>\n" +
                "            <artifactId>mysql-connector-java</artifactId>\n" +
                "            <version>8.0.26</version>\n" +
                "        </dependency>\n" +
                "    </dependencies>\n" +
                "</project>";

        String pomDestino = projeto.getDiretorioProjeto() + "\\pom.xml";
        try {
            Files.write(Paths.get(pomDestino), conteudoPomXml.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void montaPersistenceXml(Projeto projeto) {
        String persistenceXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<persistence version=\"2.1\"\n" +
                "    xmlns=\"http://xmlns.jcp.org/xml/ns/persistence\"\n" +
                "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "    xsi:schemaLocation=\"http://xmlns.jcp.org/xml/ns/persistence http://xmlns.jcp.org/xml/ns/persistence/persistence_2_1.xsd\">\n" +
                "    <persistence-unit name=\""+projeto.getConexao().getNomeConexao()+"\">\n" +
                "        <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>\n" +
                "        <properties>\n" +
                "            <property name=\"javax.persistence.jdbc.driver\" value=\"" + projeto.getConexao().getDriver() + "\"/>\n" +
                "            <property name=\"javax.persistence.jdbc.url\" value=\"" + projeto.getConexao().getUrl() + "\"/>\n" +
                "            <property name=\"javax.persistence.jdbc.user\" value=\"" + projeto.getConexao().getUsuario() + "\"/>\n" +
                "            <property name=\"javax.persistence.jdbc.password\" value=\"" + projeto.getConexao().getSenha() + "\"/>\n" +
                "            <property name=\"hibernate.dialect\" value=\"" + projeto.getConexao().getDialect() + "\"/>\n" +
                "            <property name=\"hibernate.show_sql\" value=\"true\"/>\n" +
                "            <property name=\"hibernate.format_sql\" value=\"true\"/>\n" +
                "            <property name=\"hibernate.hbm2ddl.auto\" value=\"create\"/>\n" +
                "        </properties>\n" +
                "    </persistence-unit>\n" +
                "</persistence>";

        String persistenceDestino = projeto.getDiretorioProjeto() + "\\src\\main\\resources\\META-INF\\persistence.xml";
        try {
            Files.createDirectories(Paths.get(projeto.getDiretorioProjeto() + "\\src\\main\\resources\\META-INF"));
            Files.write(Paths.get(persistenceDestino), persistenceXml.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void criarClasses(Projeto projeto, boolean web) throws IOException {
    	if(!web) {
    		daoGenerico(projeto);
    	}
    	
        // Mapeamento entre tipos primitivos e seus imports correspondentes
        Map<String, String> tipoImportMap = new HashMap<>();
        tipoImportMap.put("BigDecimal", "java.math.BigDecimal");
        // Adicione outros mapeamentos conforme necessário
    	
        String pacoteBase = "com." + projeto.getNomeProjeto() + ".model";
        
        for (Classe classe : projeto.getClasses()) {
            String nomeClasse = classe.getNomeClasse();
            List<Atributo> atributos = classe.getAtributos();

            StringBuilder codigoClasse = new StringBuilder();
            
			if (!web) {
				codigoClasse.append("package ").append(pacoteBase).append(";\n\n");
			} else {
				codigoClasse.append("package com.").append(projeto.getNomeProjeto()).append(".").append(classe.getNomeClasse())
						.append(";\n\n");
				for(int i=0; i<classe.getAtributos().size(); i++) {
					if(Objects.nonNull(classe.getAtributos().get(i).getIsRelacionamento()) && classe.getAtributos().get(i).getIsRelacionamento()) {
						codigoClasse.append("import com."+projeto.getNomeProjeto()+"."+classe.getAtributos().get(i).getTipoAtributo()+"."+classe.getAtributos().get(i).getTipoAtributo()+";\n");
					}
				}
			}
            
            codigoClasse.append("import javax.persistence.*;\n\n");
            
            // Importar as classes necessárias
            Set<String> imports = new HashSet<>();
            imports.add("import javax.persistence.*;\n");
            for (Atributo atributo : atributos) {
                String tipoAtributo = atributo.getTipoAtributo();
                if (tipoImportMap.containsKey(tipoAtributo)) {
                    imports.add("import " + tipoImportMap.get(tipoAtributo) + ";\n");
                }
            }
            for (String importLine : imports) {
                codigoClasse.append(importLine);
            }
            
            codigoClasse.append("@Entity\n");
            codigoClasse.append("public class ").append(nomeClasse).append(" {\n\n");

            for (Atributo atributo : atributos) {
                if (Objects.nonNull(atributo.getAnotacao())) {
                    for (String anotacao : atributo.getAnotacao()) {
                        codigoClasse.append("    ").append(anotacao).append("\n");
                    }
                }
                codigoClasse.append("    private ").append(atributo.getTipoAtributo()).append(" ").append(atributo.getNomeAtributo()).append(";\n");
            }

            codigoClasse.append("\n");

            for (Atributo atributo : atributos) {
                // Gerar getters e setters
                codigoClasse.append("    public ").append(atributo.getTipoAtributo()).append(" get").append(capitalize(atributo.getNomeAtributo())).append("() {\n");
                codigoClasse.append("        return ").append(atributo.getNomeAtributo()).append(";\n");
                codigoClasse.append("    }\n\n");
                codigoClasse.append("    public void set").append(capitalize(atributo.getNomeAtributo())).append("(").append(atributo.getTipoAtributo()).append(" ").append(atributo.getNomeAtributo()).append(") {\n");
                codigoClasse.append("        this.").append(atributo.getNomeAtributo()).append(" = ").append(atributo.getNomeAtributo()).append(";\n");
                codigoClasse.append("    }\n\n");
            }

            codigoClasse.append("}");
            
            if(!web) {
	            String diretorioSrc = projeto.getDiretorioProjeto() + "\\src\\main\\java\\" + pacoteBase.replace('.', '\\');
	            Files.createDirectories(Paths.get(diretorioSrc));
	            Files.write(Paths.get(diretorioSrc + "\\" + nomeClasse + ".java"), codigoClasse.toString().getBytes());
            } else {
    	        String diretorioClasse = projeto.getDiretorioProjeto() + "\\src\\main\\java\\com\\" + projeto.getNomeProjeto() + "\\" + classe.getNomeClasse();
    	        Files.createDirectories(Paths.get(diretorioClasse));
    	        Files.write(Paths.get(diretorioClasse + "\\" + classe.getNomeClasse() + ".java"), codigoClasse.toString().getBytes());
            }
            
            if(!web) {
	            // Código para criar a classe DAO correspondente utilizando a GenericDAO
	            StringBuilder codigoDAO = new StringBuilder();
	            codigoDAO.append("package ").append("com."+projeto.getNomeProjeto()+".dao").append(";\n\n");
	            codigoDAO.append("import java.util.List").append(";\n\n");
	            codigoDAO.append("import ").append("com."+projeto.getNomeProjeto()).append(".model."+nomeClasse).append(";\n\n");
	            codigoDAO.append("public class ").append(nomeClasse).append("DAO extends GenericDAO<").append(nomeClasse).append("> {\n\n");
	            codigoDAO.append("    public ").append(nomeClasse).append("DAO() {\n");
	            codigoDAO.append("        super(").append(nomeClasse).append(".class);\n");
	            codigoDAO.append("    }\n\n");
	            
	            for (Atributo atributo : atributos) {
	                if (atributo.getConsultaPor() != null && atributo.getConsultaPor()) {
	                    codigoDAO.append("    public List<").append(nomeClasse).append("> findBy").append(capitalize(atributo.getNomeAtributo())).append("(").append(atributo.getTipoAtributo()).append(" ").append(atributo.getNomeAtributo()).append(") {\n");
	                    codigoDAO.append("        return findByAttribute(\"").append(atributo.getNomeAtributo()).append("\", ").append(atributo.getNomeAtributo()).append(");\n");
	                    codigoDAO.append("    }\n\n");
	                }
	            }
	            
	            codigoDAO.append("}");
	
	            // Criar a pasta do pacote base se ainda não existe
	            String diretorioSrcDAO = projeto.getDiretorioProjeto() + "\\src\\main\\java\\com\\"+projeto.getNomeProjeto()+"\\dao";
//	            Files.createDirectories(Paths.get(diretorioSrcDAO));
	
	            // Escrever o código da classe DAO no arquivo .java
	            Files.write(Paths.get(diretorioSrcDAO + "\\" + nomeClasse + "DAO.java"), codigoDAO.toString().getBytes());
            }
        }
    }
    
    public void montaRepositoryForClass(Projeto projeto) throws IOException {
    	for(Classe classe : projeto.getClasses()) {
	        StringBuilder conteudoRepository = new StringBuilder();
	        conteudoRepository.append("package com."+projeto.getNomeProjeto()+"."+classe.getNomeClasse()+";\n\n");
	        conteudoRepository.append("import org.springframework.data.repository.CrudRepository;\n\n");
	        conteudoRepository.append("public interface "+classe.getNomeClasse()+"Repository extends CrudRepository<"+classe.getNomeClasse()+", Integer> {\n");
	        //Verificar isso, chave primaria
	        conteudoRepository.append("    public Long countById(Integer id);\n");
	        conteudoRepository.append("}\n");
	        
	        String diretorioRepository = projeto.getDiretorioProjeto() + "\\src\\main\\java\\com\\" + projeto.getNomeProjeto() + "\\" + classe.getNomeClasse();
            Files.createDirectories(Paths.get(diretorioRepository));

            Files.write(Paths.get(diretorioRepository + "\\" + classe.getNomeClasse() + "Repository.java"), conteudoRepository.toString().getBytes());
    	}
    }
    
    private void daoGenerico(Projeto projeto) throws IOException {
        StringBuilder codigoGenericDAO = new StringBuilder();
        codigoGenericDAO.append("package com."+projeto.getNomeProjeto()+".dao;\n");
        codigoGenericDAO.append("import javax.persistence.EntityManager;\n");
        codigoGenericDAO.append("import javax.persistence.EntityManagerFactory;\n");
        codigoGenericDAO.append("import javax.persistence.Persistence;\n");
        codigoGenericDAO.append("import javax.persistence.criteria.CriteriaBuilder;\n");
        codigoGenericDAO.append("import javax.persistence.criteria.CriteriaQuery;\n");
        codigoGenericDAO.append("import javax.persistence.criteria.Root;\n");
        codigoGenericDAO.append("import java.util.List;\n\n");
        codigoGenericDAO.append("public class GenericDAO<T> {\n\n");
        codigoGenericDAO.append("    private static final String PERSISTENCE_UNIT_NAME = \""+projeto.getConexao().getNomeConexao()+"\"; // Nome da unidade de persistência no persistence.xml\n\n");
        codigoGenericDAO.append("    private Class<T> entityType;\n");
        codigoGenericDAO.append("    private EntityManagerFactory entityManagerFactory;\n\n");
        codigoGenericDAO.append("    public GenericDAO(Class<T> entityType) {\n");
        codigoGenericDAO.append("        this.entityType = entityType;\n");
        codigoGenericDAO.append("        this.entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);\n");
        codigoGenericDAO.append("    }\n\n");
        codigoGenericDAO.append("    public T save(T entity) {\n");
        codigoGenericDAO.append("        EntityManager entityManager = entityManagerFactory.createEntityManager();\n");
        codigoGenericDAO.append("        try {\n");
        codigoGenericDAO.append("            entityManager.getTransaction().begin();\n");
        codigoGenericDAO.append("            entity = entityManager.merge(entity);\n");
        codigoGenericDAO.append("            entityManager.getTransaction().commit();\n");
        codigoGenericDAO.append("            return entity;\n");
        codigoGenericDAO.append("        } catch (Exception e) {\n");
        codigoGenericDAO.append("            entityManager.getTransaction().rollback();\n");
        codigoGenericDAO.append("            throw e;\n");
        codigoGenericDAO.append("        } finally {\n");
        codigoGenericDAO.append("            entityManager.close();\n");
        codigoGenericDAO.append("        }\n");
        codigoGenericDAO.append("    }\n\n");
        codigoGenericDAO.append("    public T findById(Long id) {\n");
        codigoGenericDAO.append("        EntityManager entityManager = entityManagerFactory.createEntityManager();\n");
        codigoGenericDAO.append("        try {\n");
        codigoGenericDAO.append("            return entityManager.find(entityType, id);\n");
        codigoGenericDAO.append("        } finally {\n");
        codigoGenericDAO.append("            entityManager.close();\n");
        codigoGenericDAO.append("        }\n");
        codigoGenericDAO.append("    }\n\n");
        codigoGenericDAO.append("    public List<T> findAll() {\n");
        codigoGenericDAO.append("        EntityManager entityManager = entityManagerFactory.createEntityManager();\n");
        codigoGenericDAO.append("        try {\n");
        codigoGenericDAO.append("            String query = \"SELECT e FROM \" + entityType.getSimpleName() + \" e\";\n");
        codigoGenericDAO.append("            return entityManager.createQuery(query, entityType).getResultList();\n");
        codigoGenericDAO.append("        } finally {\n");
        codigoGenericDAO.append("            entityManager.close();\n");
        codigoGenericDAO.append("        }\n");
        codigoGenericDAO.append("    }\n\n");
        codigoGenericDAO.append("    public void delete(T entity) {\n");
        codigoGenericDAO.append("        EntityManager entityManager = entityManagerFactory.createEntityManager();\n");
        codigoGenericDAO.append("        try {\n");
        codigoGenericDAO.append("            entityManager.getTransaction().begin();\n");
        codigoGenericDAO.append("            entity = entityManager.merge(entity);\n");
        codigoGenericDAO.append("            entityManager.remove(entity);\n");
        codigoGenericDAO.append("            entityManager.getTransaction().commit();\n");
        codigoGenericDAO.append("        } catch (Exception e) {\n");
        codigoGenericDAO.append("            entityManager.getTransaction().rollback();\n");
        codigoGenericDAO.append("            throw e;\n");
        codigoGenericDAO.append("        } finally {\n");
        codigoGenericDAO.append("            entityManager.close();\n");
        codigoGenericDAO.append("        }\n");
        codigoGenericDAO.append("    }\n\n");
        // Parte adicionada - findByAttribute
        codigoGenericDAO.append("    public List<T> findByAttribute(String attributeName, Object attributeValue) {\n");
        codigoGenericDAO.append("        EntityManager entityManager = entityManagerFactory.createEntityManager();\n");
        codigoGenericDAO.append("        try {\n");
        codigoGenericDAO.append("            CriteriaBuilder cb = entityManager.getCriteriaBuilder();\n");
        codigoGenericDAO.append("            CriteriaQuery<T> query = cb.createQuery(entityType);\n");
        codigoGenericDAO.append("            Root<T> root = query.from(entityType);\n");
        codigoGenericDAO.append("            query.select(root).where(cb.equal(root.get(attributeName), attributeValue));\n");
        codigoGenericDAO.append("            return entityManager.createQuery(query).getResultList();\n");
        codigoGenericDAO.append("        } finally {\n");
        codigoGenericDAO.append("            entityManager.close();\n");
        codigoGenericDAO.append("        }\n");
        codigoGenericDAO.append("    }\n");

        codigoGenericDAO.append("}\n");

        // Criar a pasta do pacote base se ainda não existe
        String diretorioSrcDAO = projeto.getDiretorioProjeto() + "\\src\\main\\java\\com\\"+projeto.getNomeProjeto()+"\\dao";
        Files.createDirectories(Paths.get(diretorioSrcDAO));

        // Escrever o código da classe DAO no arquivo .java
        Files.write(Paths.get(diretorioSrcDAO + "\\GenericDAO.java"), codigoGenericDAO.toString().getBytes());
    }
    
    private String capitalize(String str) {
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

	public List<Classe> getListaClasses() {
		return listaClasses;
	}

	public void setListaClasses(List<Classe> listaClasses) {
		this.listaClasses = listaClasses;
	}
    
	public void mostraClasse(Classe classe) {
		txtNomeClasse.setText(classe.getNomeClasse());
		txtCaminhoClasse.setText(classe.getDiretorioClasse());
		txtNomeAtributo.setText("");
		cbTipoAtributo.setSelectedIndex(0);
		chckbxApareceNaConsulta.setSelected(false);
		chckbxConsultaPor.setSelected(false);
		checkObrigatorio.setSelected(false);
		checkRelacionamento.setSelected(false);
		carregaTable(classe.getAtributos());
	}
	
	private void limpa(Boolean classe) {
		if(classe) {
			txtNomeClasse.setText("");
			txtCaminhoClasse.setText("");
		}
		txtNomeAtributo.setText("");
		cbTipoAtributo.setSelectedIndex(0);
		chckbxApareceNaConsulta.setSelected(false);
		chckbxConsultaPor.setSelected(false);
		checkObrigatorio.setSelected(false);
		checkRelacionamento.setSelected(false);
	}
	private JButton getBtnLimparClasse() {
		if (btnLimparClasse == null) {
			btnLimparClasse = new JButton("Limpar Classe");
			btnLimparClasse.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					limpa(true);
				}
			});
			btnLimparClasse.setBounds(189, 351, 130, 34);
		}
		return btnLimparClasse;
	}
    
    //WEB
    private void montaPomXmlWeb(Projeto projeto) {
        String conteudoPomXmlWeb = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
                "    <modelVersion>4.0.0</modelVersion>\n" +
                "    <parent>\n" +
                "        <groupId>org.springframework.boot</groupId>\n" +
                "        <artifactId>spring-boot-starter-parent</artifactId>\n" +
                "        <version>2.5.1</version>\n" +
                "        <relativePath/> <!-- lookup parent from repository -->\n" +
                "    </parent>\n" +
                "    <groupId>com.mycompany</groupId>\n" +
                "    <artifactId>MyWebApp</artifactId>\n" +
                "    <version>0.0.1-SNAPSHOT</version>\n" +
                "    <name>MyWebApp</name>\n" +
                "    <description>Demo project for Spring Boot</description>\n" +
                "    <properties>\n" +
                "        <java.version>11</java.version>\n" +
                "    </properties>\n" +
                "    <dependencies>\n" +
                "        <dependency>\n" +
                "            <groupId>org.springframework.boot</groupId>\n" +
                "            <artifactId>spring-boot-starter-data-jpa</artifactId>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>org.springframework.boot</groupId>\n" +
                "            <artifactId>spring-boot-starter-thymeleaf</artifactId>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>org.springframework.boot</groupId>\n" +
                "            <artifactId>spring-boot-starter-web</artifactId>\n" +
                "        </dependency>\n" +
                "\n" +
                "        <dependency>\n" +
                "            <groupId>org.springframework.boot</groupId>\n" +
                "            <artifactId>spring-boot-devtools</artifactId>\n" +
                "            <scope>runtime</scope>\n" +
                "            <optional>true</optional>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>mysql</groupId>\n" +
                "            <artifactId>mysql-connector-java</artifactId>\n" +
                "            <scope>runtime</scope>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>org.springframework.boot</groupId>\n" +
                "            <artifactId>spring-boot-starter-test</artifactId>\n" +
                "            <scope>test</scope>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>org.webjars</groupId>\n" +
                "            <artifactId>bootstrap</artifactId>\n" +
                "            <version>4.3.1</version>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>org.webjars</groupId>\n" +
                "            <artifactId>webjars-locator-core</artifactId>\n" +
                "        </dependency>\n" +
                "    </dependencies>\n" +
                "\n" +
                "    <build>\n" +
                "        <plugins>\n" +
                "            <plugin>\n" +
                "                <groupId>org.springframework.boot</groupId>\n" +
                "                <artifactId>spring-boot-maven-plugin</artifactId>\n" +
                "            </plugin>\n" +
                "        </plugins>\n" +
                "    </build>\n" +
                "\n" +
                "</project>";

        String pomDestino = projeto.getDiretorioProjeto() + "\\pom.xml";
        
        try {
            Files.write(Paths.get(pomDestino), conteudoPomXmlWeb.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void montaServiceForClass(Projeto projeto) throws IOException {
    	for(Classe classe : projeto.getClasses()) {
	        StringBuilder conteudoService = new StringBuilder();
	        conteudoService.append("package com."+projeto.getNomeProjeto()+"."+classe.getNomeClasse()+";\n\n");
	        conteudoService.append("import org.springframework.beans.factory.annotation.Autowired;\n");
	        conteudoService.append("import org.springframework.stereotype.Service;\n");
	        conteudoService.append("import java.util.List;\n");
	        conteudoService.append("import java.util.Optional;\n\n");
	        conteudoService.append("@Service\n");
	        conteudoService.append("public class "+classe.getNomeClasse()+"Service {\n\n");
	        conteudoService.append("    @Autowired private "+classe.getNomeClasse()+"Repository repo;\n\n");
	        conteudoService.append("    public List<"+classe.getNomeClasse()+"> listAll() {\n");
	        conteudoService.append("        return (List<"+classe.getNomeClasse()+">) repo.findAll();\n");
	        conteudoService.append("    }\n\n");
	        conteudoService.append("    public void save("+classe.getNomeClasse()+" "+classe.getNomeClasse()+") {\n");
	        conteudoService.append("        repo.save("+classe.getNomeClasse()+");\n");
	        conteudoService.append("    }\n\n");
	        conteudoService.append("    public "+classe.getNomeClasse()+" get(Integer id) throws "+classe.getNomeClasse()+"NotFoundException {\n");
	        conteudoService.append("        Optional<"+classe.getNomeClasse()+"> result = repo.findById(id);\n");
	        conteudoService.append("        if (result.isPresent()) {\n");
	        conteudoService.append("            return result.get();\n");
	        conteudoService.append("        }\n");
	        conteudoService.append("        throw new "+classe.getNomeClasse()+"NotFoundException(\"Não foi possível encontrar nenhum usuário com ID \" + id);\n");
	        conteudoService.append("    }\n\n");
	        conteudoService.append("    public void delete(Integer id) throws "+classe.getNomeClasse()+"NotFoundException {\n");
	        conteudoService.append("        Long count = repo.countById(id);\n");
	        conteudoService.append("        if (count == null || count == 0) {\n");
	        conteudoService.append("            throw new "+classe.getNomeClasse()+"NotFoundException(\"Não foi possível encontrar nenhum usuário com ID \" + id);\n");
	        conteudoService.append("        }\n");
	        conteudoService.append("        repo.deleteById(id);\n");
	        conteudoService.append("    }\n");
	        conteudoService.append("}\n");
	        
	        String diretorioService = projeto.getDiretorioProjeto() + "\\src\\main\\java\\com\\" + projeto.getNomeProjeto() + "\\" + classe.getNomeClasse();
	        
	        Files.write(Paths.get(diretorioService + "\\" + classe.getNomeClasse() + "Service.java"), conteudoService.toString().getBytes());
    	}
    }
    
    private void montaNotFoundException(Projeto projeto) throws IOException {
    	for(Classe classe : projeto.getClasses()) {
	        StringBuilder conteudoNotFoundException = new StringBuilder();
	
	        conteudoNotFoundException.append("package com."+projeto.getNomeProjeto()+"."+classe.getNomeClasse()+";\n\n");
	        conteudoNotFoundException.append("public class "+classe.getNomeClasse()+"NotFoundException extends Throwable {\n\n");
	        conteudoNotFoundException.append("    public "+classe.getNomeClasse()+"NotFoundException(String message) {\n");
	        conteudoNotFoundException.append("        super(message);\n");
	        conteudoNotFoundException.append("    }\n");
	        conteudoNotFoundException.append("}\n");
	        
	        String diretorioRepository = projeto.getDiretorioProjeto() + "\\src\\main\\java\\com\\" + projeto.getNomeProjeto() + "\\" + classe.getNomeClasse();
	        Files.createDirectories(Paths.get(diretorioRepository));
	        Files.write(Paths.get(diretorioRepository + "\\" + classe.getNomeClasse() + "NotFoundException.java"), conteudoNotFoundException.toString().getBytes());
    	}
    }
    
    public void montaApplicationProperties(Projeto projeto) throws IOException {
        StringBuilder conteudoApplicationProperties = new StringBuilder();
        conteudoApplicationProperties.append("spring.datasource.url="+projeto.getConexao().getUrl()+"\n");
        conteudoApplicationProperties.append("spring.datasource.username="+projeto.getConexao().getUsuario()+"\n");
        conteudoApplicationProperties.append("spring.datasource.password="+projeto.getConexao().getSenha()+"\n");
        conteudoApplicationProperties.append("spring.jpa.hibernate.ddl-auto=create\n");
        conteudoApplicationProperties.append("spring.jpa.properties.hibernate.show_sql=true\n");
        
        String applicationPropertiesDestino = projeto.getDiretorioProjeto() + "\\src\\main\\resources";
        Files.createDirectories(Paths.get(applicationPropertiesDestino));
        
        String txt = applicationPropertiesDestino + "\\\\application.properties";
        Files.write(Paths.get(txt), conteudoApplicationProperties.toString().getBytes());
    }
    
    private void criarIndex(Projeto projeto) throws IOException {
        StringBuilder htmlBuilder = new StringBuilder();
        
        htmlBuilder.append("<!DOCTYPE html>\n");
        htmlBuilder.append("<html lang=\"en\" xmlns:th=\"http://www.thymeleaf.org\">\n");
        htmlBuilder.append("<head>\n");
        htmlBuilder.append("    <meta charset=\"UTF-8\">\n");
        htmlBuilder.append("    <title>"+projeto.getNomeProjeto()+"</title>\n");
        htmlBuilder.append("    <link rel=\"stylesheet\" type=\"text/css\" th:href=\"@{/webjars/bootstrap/css/bootstrap.min.css}\" />\n");
        htmlBuilder.append("    <style>\n");
        htmlBuilder.append("        body {\n");
        htmlBuilder.append("            background-color: #f5f5f5;\n");
        htmlBuilder.append("            font-family: Arial, sans-serif;\n");
        htmlBuilder.append("        }\n");
        htmlBuilder.append("\n");
        htmlBuilder.append("        .container {\n");
        htmlBuilder.append("            max-width: 800px;\n");
        htmlBuilder.append("            margin: 0 auto;\n");
        htmlBuilder.append("            padding: 20px;\n");
        htmlBuilder.append("            text-align: center;\n");
        htmlBuilder.append("        }\n");
        htmlBuilder.append("\n");
        htmlBuilder.append("        h1 {\n");
        htmlBuilder.append("            color: #333;\n");
        htmlBuilder.append("            margin-bottom: 20px;\n");
        htmlBuilder.append("        }\n");
        htmlBuilder.append("\n");
        htmlBuilder.append("        .menu {\n");
        htmlBuilder.append("            list-style: none;\n");
        htmlBuilder.append("            padding: 0;\n");
        htmlBuilder.append("        }\n");
        htmlBuilder.append("\n");
        htmlBuilder.append("        .menu-item {\n");
        htmlBuilder.append("            display: inline-block;\n");
        htmlBuilder.append("            margin-right: 20px;\n");
        htmlBuilder.append("        }\n");
        htmlBuilder.append("\n");
        htmlBuilder.append("        .menu-item a {\n");
        htmlBuilder.append("            text-decoration: none;\n");
        htmlBuilder.append("            color: #007bff;\n");
        htmlBuilder.append("            font-size: 18px;\n");
        htmlBuilder.append("            transition: color 0.3s;\n");
        htmlBuilder.append("        }\n");
        htmlBuilder.append("\n");
        htmlBuilder.append("        .menu-item a:hover {\n");
        htmlBuilder.append("            color: #0056b3;\n");
        htmlBuilder.append("        }\n");
        htmlBuilder.append("    </style>\n");
        htmlBuilder.append("</head>\n");
        htmlBuilder.append("<body>\n");
        htmlBuilder.append("<div class=\"container\">\n");
        htmlBuilder.append("    <h1>"+projeto.getNomeProjeto()+"</h1>\n");
        htmlBuilder.append("    <ul class=\"menu\">\n");
    	
    	for(Classe classe : projeto.getClasses()) {
	        htmlBuilder.append("        <li class=\"menu-item\"><a th:href=\"@{/"+classe.getNomeClasse()+"s}\">Gerenciar "+classe.getNomeClasse()+"s</a></li>\n");
    	}
    	
        htmlBuilder.append("    </ul>\n");
        htmlBuilder.append("</div>\n");
        htmlBuilder.append("</body>\n");
        htmlBuilder.append("</html>\n");
        
        String indexHtmlDestino = projeto.getDiretorioProjeto() + "\\src\\main\\resources\\templates";
        Files.createDirectories(Paths.get(indexHtmlDestino));
        
        String file = indexHtmlDestino + "\\\\index.html";
        Files.write(Paths.get(file), htmlBuilder.toString().getBytes());
    }
    
    //ajustar genericamente
    private void criarListar(Projeto projeto) throws IOException {
    	for(Classe classe : projeto.getClasses()) {
    	    StringBuilder conteudoHtml = new StringBuilder();

    	    conteudoHtml.append("<!DOCTYPE html>\n");
    	    conteudoHtml.append("<html lang=\"en\" xmlns:th=\"http://www.thymeleaf.org\">\n");
    	    conteudoHtml.append("<head>\n");
    	    conteudoHtml.append("  <meta charset=\"UTF-8\">\n");
    	    conteudoHtml.append("  <title>Gerenciar "+classe.getNomeClasse()+"s</title>\n");
    	    conteudoHtml.append("  <link rel=\"stylesheet\" type=\"text/css\" th:href=\"@{/webjars/bootstrap/css/bootstrap.min.css}\"  />\n");
    	    conteudoHtml.append("</head>\n");
    	    conteudoHtml.append("<body>\n");
    	    conteudoHtml.append("<div class=\"container-fluid text-center\">\n");
    	    conteudoHtml.append("  <div><h2>Gerenciar "+classe.getNomeClasse()+"s</h2></div>\n");
    	    conteudoHtml.append("  <div class=\"m-2\">\n");
    	    conteudoHtml.append("    <a class=\"h3\" th:href=\"@{/"+classe.getNomeClasse()+"s/new}\">Adicionar "+classe.getNomeClasse()+"</a>\n");
    	    conteudoHtml.append("  </div>\n");
    	    conteudoHtml.append("  <div th:if=\"${message}\" class=\"alert alert-success text-center\">\n");
    	    conteudoHtml.append("    [[${message}]]\n");
    	    conteudoHtml.append("  </div>\n");
    	    conteudoHtml.append("  <div>\n");
    	    conteudoHtml.append("    <table class=\"table table-bordered\">\n");
    	    conteudoHtml.append("      <thead class=\"thead-dark\">\n");
    	    conteudoHtml.append("        <tr>\n");
    	    
    	    List<Atributo> atributos = classe.getAtributos();
			if (Objects.nonNull(atributos) && !atributos.isEmpty()) {
				for (Atributo attr : atributos) {
					if (attr.getApareceNaConsulta()) {
						conteudoHtml.append("          <th>"+attr.getNomeAtributo()+"</th>\n");
					}
				}
			}
    	    
    	    conteudoHtml.append("          <th></th>\n");
    	    conteudoHtml.append("        </tr>\n");
    	    conteudoHtml.append("      </thead>\n");
    	    conteudoHtml.append("      <tbody>\n");
    	    conteudoHtml.append("      <th:block th:each=\""+classe.getNomeClasse().toLowerCase()+" : ${list"+classe.getNomeClasse()+"s}\">\n");
    	    conteudoHtml.append("       <tr>\n");
    	    
    	    if(Objects.nonNull(atributos) && !atributos.isEmpty()) {
    	    	String nomeSearch = "";
    	    	for(Atributo attr : atributos) {
    	    		if(attr.getApareceNaConsulta()) {
    	    			if(Objects.nonNull(attr.getIsRelacionamento()) && attr.getIsRelacionamento()) {
    	    				for(int i=0; i<listaClasses.size(); i++) {
    	    					if(attr.getTipoAtributo().equals(listaClasses.get(i).getNomeClasse())) {
    	    						for(int j=0; j<listaClasses.get(i).getAtributos().size(); j++) {
    	    							if(Objects.nonNull(listaClasses.get(i).getAtributos().get(j).getConsultaPor()) && listaClasses.get(i).getAtributos().get(j).getConsultaPor()) {
    	    								nomeSearch = listaClasses.get(i).getAtributos().get(j).getNomeAtributo();
    	    							}
    	    						}
    	    					}
    	    				}
    	    				
    	    				conteudoHtml.append("         <td>[[${"+classe.getNomeClasse().toLowerCase()+"."+attr.getNomeAtributo()+"."+nomeSearch+"}]]</td>\n");
    	    			}else {
    	    				conteudoHtml.append("         <td>[[${"+classe.getNomeClasse().toLowerCase()+"."+attr.getNomeAtributo()+"}]]</td>\n");
    	    			}
    	    		}
    	    	}
    	    }
    	    
    	    conteudoHtml.append("         <td>\n");
    	    
    	    //Ver chave primaria
    	    conteudoHtml.append("           <a class=\"h4 mr-3\" th:href=\"@{'/"+classe.getNomeClasse()+"s/edit/' + ${"+classe.getNomeClasse().toLowerCase()+".id}}\">Editar</a>\n");
    	    conteudoHtml.append("           <a class=\"h4\" th:href=\"@{'/"+classe.getNomeClasse()+"s/delete/' + ${"+classe.getNomeClasse().toLowerCase()+".id}}\">Deletar</a>\n");
    	    //
    	    
    	    conteudoHtml.append("         </td>\n");
    	    conteudoHtml.append("       </tr>\n");
    	    conteudoHtml.append("      </th:block>\n");
    	    conteudoHtml.append("      </tbody>\n");
    	    conteudoHtml.append("    </table>\n");
    	    conteudoHtml.append("  </div>\n");
    	    
    	    conteudoHtml.append("  <div class=\"mt-4\">\n");
    	    conteudoHtml.append("    <a class=\"btn btn-secondary\" th:href=\"@{/}\">\n");
    	    conteudoHtml.append("      Voltar ao Menu\n");
    	    conteudoHtml.append("    </a>\n");
    	    conteudoHtml.append("  </div>\n");
    	    
    	    conteudoHtml.append("</div>\n");
    	    conteudoHtml.append("</body>\n");
    	    conteudoHtml.append("</html>\n");
    	    
    	    String htmlDestino = projeto.getDiretorioProjeto() + "\\src\\main\\resources\\templates";
    	    Files.createDirectories(Paths.get(htmlDestino));
    	    String file = htmlDestino + "\\\\"+classe.getNomeClasse()+"s.html";
    	    Files.write(Paths.get(file), conteudoHtml.toString().getBytes());
    	}
    }
    
    //ajustar genericamente
    private void criarFormulario(Projeto projeto) throws IOException {
    	
    	for(Classe classe : projeto.getClasses()) {
	        StringBuilder conteudoFormHtml = new StringBuilder();
	
	        conteudoFormHtml.append("<!DOCTYPE html>\n");
	        conteudoFormHtml.append("<html lang=\"en\" xmlns:th=\"http://www.thymeleaf.org\">\n");
	        conteudoFormHtml.append("<head>\n");
	        conteudoFormHtml.append("    <meta charset=\"UTF-8\">\n");
	        conteudoFormHtml.append("    <title>[[${pageTitle}]]</title>\n");
	        conteudoFormHtml.append("    <link rel=\"stylesheet\" type=\"text/css\" th:href=\"@{/webjars/bootstrap/css/bootstrap.min.css}\"  />\n");
	        conteudoFormHtml.append("</head>\n");
	        conteudoFormHtml.append("<body>\n");
	        conteudoFormHtml.append("<div class=\"container-fluid\">\n");
	        conteudoFormHtml.append("  <div class=\"text-center\"><h2>[[${pageTitle}]]</h2></div>\n");
	        conteudoFormHtml.append("  <form th:action=\"@{/"+classe.getNomeClasse()+"s/save}\" method=\"post\" th:object=\"${"+classe.getNomeClasse()+"}\"\n");
	        conteudoFormHtml.append("      style=\"max-width: 500px; margin: 0 auto;\">\n");
	        
	        //chave primaria
	        conteudoFormHtml.append("    <input type=\"hidden\" th:field=\"*{id}\">\n");
	        conteudoFormHtml.append("    <div class=\"border border-secondary rounded p-3\">\n");
	        
	        List<Atributo> atributos = classe.getAtributos();
	        if(Objects.nonNull(atributos) && !atributos.isEmpty()) {
	        	for(Atributo attr : atributos) {
	        		if(!("id".equals(attr.getNomeAtributo()))) {
		        		if(!attr.getTipoAtributo().equals("Boolean")) {
		        			if(Objects.nonNull(attr.getIsRelacionamento()) && attr.getIsRelacionamento()) {
		        				conteudoFormHtml.append("      <div class=\"form-group row\">\n");
		        				conteudoFormHtml.append("        <label class=\"col-sm-4 col-form-label\">"+attr.getNomeAtributo()+":</label>\n");
		        				conteudoFormHtml.append("        <div class=\"col-sm-8\">\n");
		        				conteudoFormHtml.append("          <select th:field=\"*{"+attr.getNomeAtributo()+"}\" class=\"form-control\">\n");
		        				conteudoFormHtml.append("            <option th:each=\""+attr.getNomeAtributo()+" : ${list"+attr.getTipoAtributo()+"s}\" th:value=\"${"+attr.getNomeAtributo()+".id}\" th:text=\"${"+attr.getNomeAtributo()+".nome}\"></option>\n");
		        				conteudoFormHtml.append("          </select>\n");
		        				conteudoFormHtml.append("        </div>\n");
		        				conteudoFormHtml.append("      </div>\n");
		        			}else {
			        	        conteudoFormHtml.append("      <div class=\"form-group row\">\n");
			        	        conteudoFormHtml.append("        <label class=\"col-sm-4 col-form-label\">"+attr.getNomeAtributo()+":</label>\n");
			        	        conteudoFormHtml.append("        <div class=\"col-sm-8\">\n");
			        	        
			        	        String restrictions = "";
			        	        if(Objects.nonNull(attr.getAnotacao())) {
				        	        for(String anot : attr.getAnotacao()) {
				        	        	if(anot.contains("@Column") && !anot.contains("@Join")) {
				        	        		
				        	                String[] parts = anot.split(",");

				        	                String length = null;
				        	                Boolean nullable = null;

				        	                if(Objects.nonNull(attr.getIsObrigatorio()) && attr.getIsObrigatorio()) {
				        	                	nullable = true;
				        	                }
				        	                
				        	                for (String part : parts) {
				        	                    if (part.contains("length")) {
				        	                        length = part.split("=")[1].trim();
				        	                    } else if (part.contains("nullable") && Objects.isNull(nullable)) {
				        	                        String nullableValue = part.split("=")[1].trim();
				        	                        nullableValue = nullableValue.substring(0, nullableValue.length() - 1); // Removendo o último caractere
				        	                        nullable = Boolean.valueOf(nullableValue);
				        	                    }
				        	                }
				        	                
				        	                String minlength = " minlength=\""+length+"\"";
				        	                String required = " ";
				        	                if(Objects.nonNull(nullable) && nullable) {
				        	                	required += "required";
				        	                }
				        	        		
				        	                if(Objects.nonNull(length)) {
				        	                	restrictions = minlength;
				        	                }
				        	                if(Objects.nonNull(nullable)) {
				        	                	restrictions += required; 
				        	                }
				        	                
				        	        	}
				        	        }
			        	        }else {
		        	                Boolean nullable = null;

		        	                if(Objects.nonNull(attr.getIsObrigatorio()) && attr.getIsObrigatorio()) {
		        	                	nullable = true;
		        	                }
		        	                
		        	                String required = " ";
		        	                if(Objects.nonNull(nullable) && nullable) {
		        	                	required += "required";
		        	                }
		        	                
		        	                if(Objects.nonNull(nullable)) {
		        	                	restrictions += required; 
		        	                }
			        	        }
			        	        
			        	        
			        	        conteudoFormHtml.append("          <input type=\"text\" th:field=\"*{"+attr.getNomeAtributo()+"}\" class=\"form-control\" "+restrictions+" />\n");
			        	        
			        	        
			        	        conteudoFormHtml.append("        </div>\n");
			        	        conteudoFormHtml.append("      </div>\n");
		        			}
		        		} else {
		        	        conteudoFormHtml.append("      <div class=\"form-group row\">\n");
		        	        conteudoFormHtml.append("        <label class=\"col-sm-4 col-form-label\">"+attr.getNomeAtributo()+":</label>\n");
		        	        conteudoFormHtml.append("        <div class=\"col-sm-8\">\n");
		        	        conteudoFormHtml.append("          <input type=\"checkbox\" th:field=\"*{"+attr.getNomeAtributo()+"}\" />\n");
		        	        conteudoFormHtml.append("        </div>\n");
		        	        conteudoFormHtml.append("      </div>\n");
		        		}
		        	}
	        		}
	        }
	        
	        conteudoFormHtml.append("      <div class=\"text-center\">\n");
	        conteudoFormHtml.append("        <button type=\"submit\" class=\"btn btn-primary m-2\">Salvar</button>\n");
	        conteudoFormHtml.append("        <button type=\"button\" class=\"btn btn-secondary m-2\" onclick=\"cancelForm()\">Cancelar</button>\n");
	        conteudoFormHtml.append("      </div>\n");
	        conteudoFormHtml.append("    </div>\n");
	        conteudoFormHtml.append("  </form>\n");
	        conteudoFormHtml.append("</div>\n");
	        conteudoFormHtml.append("<script type=\"text/javascript\">\n");
	        conteudoFormHtml.append("  function cancelForm() {\n");
	        conteudoFormHtml.append("    window.location = \"[[@{/"+classe.getNomeClasse()+"s}]]\";\n");
	        conteudoFormHtml.append("  }\n");
	        conteudoFormHtml.append("</script>\n");
	        conteudoFormHtml.append("</body>\n");
	        conteudoFormHtml.append("</html>\n");
	        
	        String formHtmlDestino = projeto.getDiretorioProjeto() + "\\src\\main\\resources\\templates";
	        Files.createDirectories(Paths.get(formHtmlDestino));
	        
	        String file = formHtmlDestino + "\\\\"+classe.getNomeClasse()+"_form.html";
	        Files.write(Paths.get(file), conteudoFormHtml.toString().getBytes());
    	}
    }
    
    //VERIFICAR EM TUDO PARA VER COMO VAI SER TRATADO A QUESTAO DA CHAVE PRIMARIA
    //(CAMINHO MAIS FACIL É PADRONIZAR PARA CÓDIGO OU ID) E AVISAR AO USUARIO QUE ISSO SERÁ PADRÃO PARA TODAS AS CLASSES
    //VERIFICAR SOBRE A CONVERSÃO DOS TIPOS
    //VERIFICAR QUESTÃO DE RELACIONAMENTOS
    //VERIFICAR QUESTAO DE ANOTAÇÕES
    
    //Ajustar e depois criar a Classe ("USER"), verificar se vai ser necessario criar outro metodo ou se dá para utilizar o criarClasses
    
    //editar maiusculos e minusculos
    private void montaControllerForClass(Projeto projeto) throws IOException {
    	for(Classe classe : projeto.getClasses()) {
    	    StringBuilder conteudoController = new StringBuilder();

    	    conteudoController.append("package com."+projeto.getNomeProjeto()+"."+classe.getNomeClasse()+";\n\n");
    	    conteudoController.append("import org.springframework.beans.factory.annotation.Autowired;\n");
    	    conteudoController.append("import org.springframework.stereotype.Controller;\n");
    	    conteudoController.append("import org.springframework.ui.Model;\n");
    	    conteudoController.append("import org.springframework.web.bind.annotation.GetMapping;\n");
    	    conteudoController.append("import org.springframework.web.bind.annotation.PathVariable;\n");
    	    conteudoController.append("import org.springframework.web.bind.annotation.PostMapping;\n");
    	    conteudoController.append("import org.springframework.web.servlet.mvc.support.RedirectAttributes;\n");
    	    conteudoController.append("import java.util.List;\n");
    	    
    	    for(int i=0; i<classe.getAtributos().size(); i++) {
    	    	if(Objects.nonNull(classe.getAtributos().get(i).getIsRelacionamento()) && classe.getAtributos().get(i).getIsRelacionamento()) {
    	    		conteudoController.append("import com."+projeto.getNomeProjeto()+"."+classe.getAtributos().get(i).getTipoAtributo()+"."+classe.getAtributos().get(i).getTipoAtributo()+"NotFoundException;\n");
    	    		conteudoController.append("import com."+projeto.getNomeProjeto()+"."+classe.getAtributos().get(i).getTipoAtributo()+"."+classe.getAtributos().get(i).getTipoAtributo()+"Service;;\n\n");
    	    	}
    	    }
    	    
    	    conteudoController.append("@Controller\n");
    	    conteudoController.append("public class "+classe.getNomeClasse()+"Controller {\n\n");
    	    conteudoController.append("    @Autowired private "+classe.getNomeClasse()+"Service service;\n\n");
    	    for(int i=0; i<classe.getAtributos().size(); i++) {
    	    	if(Objects.nonNull(classe.getAtributos().get(i).getIsRelacionamento()) && classe.getAtributos().get(i).getIsRelacionamento()) {
    	    		conteudoController.append("    @Autowired private "+classe.getAtributos().get(i).getTipoAtributo()+"Service "+classe.getAtributos().get(i).getTipoAtributo().toLowerCase()+"Service;\n\n");
    	    	}
    	    }
    	    
    	    conteudoController.append("    @GetMapping(\"/"+classe.getNomeClasse()+"s\")\n"); /////////
    	    
    	    
    	    conteudoController.append("    public String show"+classe.getNomeClasse()+"List(Model model) {\n");
    	    conteudoController.append("        List<"+classe.getNomeClasse()+"> list"+classe.getNomeClasse()+"s = service.listAll();\n");
    	    conteudoController.append("        model.addAttribute(\"list"+classe.getNomeClasse()+"s\", list"+classe.getNomeClasse()+"s);\n");
    	    conteudoController.append("        return \""+classe.getNomeClasse()+"s\";\n");
    	    conteudoController.append("    }\n\n");
    	    
    	    
    	    conteudoController.append("    @GetMapping(\"/"+classe.getNomeClasse()+"s/new\")\n");
    	    conteudoController.append("    public String showNewForm(Model model) {\n");
    	    conteudoController.append("        model.addAttribute(\""+classe.getNomeClasse()+"\", new "+classe.getNomeClasse()+"());\n");
    	    
    	    Boolean temRelacionamento = false;
    	    for(int i=0; i<classe.getAtributos().size(); i++) {
    	    	if(Objects.nonNull(classe.getAtributos().get(i).getIsRelacionamento()) && classe.getAtributos().get(i).getIsRelacionamento()) {
    	    		temRelacionamento = true;
    	    		conteudoController.append("        model.addAttribute(\"list"+classe.getAtributos().get(i).getTipoAtributo()+"s\", "+classe.getAtributos().get(i).getTipoAtributo().toLowerCase()+"Service.listAll());\n");
    	    	}
    	    }
    	    
    	    conteudoController.append("        model.addAttribute(\"pageTitle\", \"Adicionar "+classe.getNomeClasse()+"\");\n");
    	    conteudoController.append("        return \""+classe.getNomeClasse()+"_form\";\n");
    	    conteudoController.append("    }\n\n");
    	    
    	    
    	    conteudoController.append("    @PostMapping(\"/"+classe.getNomeClasse()+"s/save\")\n");
    	    conteudoController.append("    public String save"+classe.getNomeClasse()+"("+classe.getNomeClasse()+" "+classe.getNomeClasse()+", RedirectAttributes ra) {\n");
    	    
    	    String tipoAtr = "";
    	    if(temRelacionamento) {   	    	
    	    	conteudoController.append("    	try {\n");
    	    }
    	    for(int i=0; i<classe.getAtributos().size(); i++) {
    	    	if(Objects.nonNull(classe.getAtributos().get(i).getIsRelacionamento()) && classe.getAtributos().get(i).getIsRelacionamento()) {
    	    		String nomeAtributo = classe.getAtributos().get(i).getNomeAtributo();
    	    		String nomeAtributoComPrimeiraMaiuscula = nomeAtributo.substring(0, 1).toUpperCase() + nomeAtributo.substring(1);
    	    		tipoAtr = classe.getAtributos().get(i).getTipoAtributo();

    	    		conteudoController.append("        "+classe.getNomeClasse()+".set"+nomeAtributoComPrimeiraMaiuscula+"("+classe.getAtributos().get(i).getTipoAtributo().toLowerCase()+"Service.get("+classe.getNomeClasse()+".get"+nomeAtributoComPrimeiraMaiuscula+"().getId()));\n");
    	    	}
    	    }
    	    
    	    conteudoController.append("        service.save("+classe.getNomeClasse()+");\n");
    	    conteudoController.append("        ra.addFlashAttribute(\"message\", \""+classe.getNomeClasse()+" Salvo com Sucesso!.\");\n");
    	    conteudoController.append("        return \"redirect:/"+classe.getNomeClasse()+"s\";\n");
    	    
    	    if(temRelacionamento) {
	    	    conteudoController.append("    	}catch("+tipoAtr+"NotFoundException e) {\n");
	    	    conteudoController.append("    		e.printStackTrace();");
	    	    conteudoController.append("    	}\n");
	    	    conteudoController.append("    	return null;\n");
    	    }
    	    
    	    
    	    conteudoController.append("    }\n\n");
    	    
    	    
    	    conteudoController.append("    @GetMapping(\"/"+classe.getNomeClasse()+"s/edit/{id}\")\n");
    	    conteudoController.append("    public String showEditForm(@PathVariable(\"id\") Integer id, Model model, RedirectAttributes ra) {\n");
    	    conteudoController.append("        try {\n");
    	    conteudoController.append("            "+classe.getNomeClasse()+" "+classe.getNomeClasse()+" = service.get(id);\n");
    	    conteudoController.append("            model.addAttribute(\""+classe.getNomeClasse()+"\", "+classe.getNomeClasse()+");\n");
    	    
			for (int i = 0; i < classe.getAtributos().size(); i++) {
				if(Objects.nonNull(classe.getAtributos().get(i).getIsRelacionamento()) && classe.getAtributos().get(i).getIsRelacionamento()) {
					conteudoController.append("			   model.addAttribute(\"list"
							+ classe.getAtributos().get(i).getTipoAtributo() + "s\", "+classe.getAtributos().get(i).getTipoAtributo().toLowerCase()+"Service.listAll());\n");
				}
			}
    	    
    	    conteudoController.append("            model.addAttribute(\"pageTitle\", \"Edit "+classe.getNomeClasse()+" (ID: \" + id + \")\");\n");
    	    conteudoController.append("            return \""+classe.getNomeClasse()+"_form\";\n");
    	    conteudoController.append("        } catch ("+classe.getNomeClasse()+"NotFoundException e) {\n");
    	    conteudoController.append("            ra.addFlashAttribute(\"message\", e.getMessage());\n");
    	    conteudoController.append("            return \"redirect:/"+classe.getNomeClasse()+"s\";\n");
    	    conteudoController.append("        }\n");
    	    conteudoController.append("    }\n\n");
    	    
    	    
    	    conteudoController.append("    @GetMapping(\"/"+classe.getNomeClasse()+"s/delete/{id}\")\n");
    	    conteudoController.append("    public String delete"+classe.getNomeClasse()+"(@PathVariable(\"id\") Integer id, RedirectAttributes ra) {\n");
    	    conteudoController.append("        try {\n");
    	    conteudoController.append("            service.delete(id);\n");
    	    conteudoController.append("            ra.addFlashAttribute(\"message\", \""+classe.getNomeClasse()+" ID \" + id + \" foi Deletado!.\");\n");
    	    conteudoController.append("        } catch ("+classe.getNomeClasse()+"NotFoundException e) {\n");
    	    conteudoController.append("            ra.addFlashAttribute(\"message\", e.getMessage());\n");
    	    conteudoController.append("        }\n");
    	    conteudoController.append("        return \"redirect:/"+classe.getNomeClasse()+"s\";\n");
    	    conteudoController.append("    }\n");
    	    conteudoController.append("}\n");
    	    
	        String diretorioRepository = projeto.getDiretorioProjeto() + "\\src\\main\\java\\com\\" + projeto.getNomeProjeto() + "\\" + classe.getNomeClasse();
	        Files.createDirectories(Paths.get(diretorioRepository));
	        Files.write(Paths.get(diretorioRepository + "\\" + classe.getNomeClasse() + "Controller.java"), conteudoController.toString().getBytes());
    	}
    }
    /*
     * montaPomXmlWeb
montaServiceForClass
montaNotFoundException
montaApplicationProperties
criarIndex
criarListar
criarFormulario
criarClassesWeb
montaControllerForClass
montaMainController
     */
    public void montaMainController(Projeto projeto) throws IOException {
        StringBuilder conteudoMainController = new StringBuilder();
        conteudoMainController.append("package com."+projeto.getNomeProjeto()+";\n\n");
        conteudoMainController.append("import org.springframework.stereotype.Controller;\n");
        conteudoMainController.append("import org.springframework.web.bind.annotation.GetMapping;\n\n");
        conteudoMainController.append("@Controller\n");
        conteudoMainController.append("public class MainController {\n\n");
        conteudoMainController.append("    @GetMapping(\"\")\n");
        conteudoMainController.append("    public String showHomePage() {\n");
        conteudoMainController.append("        return \"index\";\n");
        conteudoMainController.append("    }\n");
        conteudoMainController.append("}\n");
        String diretorioSrcMain = projeto.getDiretorioProjeto() + "\\src\\main\\java\\com\\" + projeto.getNomeProjeto();
        Files.createDirectories(Paths.get(diretorioSrcMain));
        Files.write(Paths.get(diretorioSrcMain + "\\MainController.java"), conteudoMainController.toString().getBytes());
        
        StringBuilder conteudoMyWebAppApplication = new StringBuilder();
        conteudoMyWebAppApplication.append("package com."+projeto.getNomeProjeto()+";\n\n");
        conteudoMyWebAppApplication.append("import org.springframework.boot.SpringApplication;\n");
        conteudoMyWebAppApplication.append("import org.springframework.boot.autoconfigure.SpringBootApplication;\n\n");
        conteudoMyWebAppApplication.append("@SpringBootApplication\n");
        conteudoMyWebAppApplication.append("public class MyWebAppApplication {\n\n");
        conteudoMyWebAppApplication.append("    public static void main(String[] args) {\n");
        conteudoMyWebAppApplication.append("        SpringApplication.run(MyWebAppApplication.class, args);\n");
        conteudoMyWebAppApplication.append("    }\n");
        conteudoMyWebAppApplication.append("}\n");
        
        Files.write(Paths.get(diretorioSrcMain + "\\MyWebAppApplication.java"), conteudoMyWebAppApplication.toString().getBytes());
    }
	private JCheckBox getCbIsWeb() {
		if (cbIsWeb == null) {
			cbIsWeb = new JCheckBox("Projeto Web");
			cbIsWeb.setSelected(false);
			cbIsWeb.setBounds(572, 29, 93, 23);
		}
		return cbIsWeb;
	}
}
