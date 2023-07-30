package gensource_realese.view;

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

import gensource_realese.model.Atributo;
import gensource_realese.model.Classe;
import gensource_realese.model.Conexao;
import gensource_realese.model.Projeto;

public class Menu {

    private JFrame frmGensourceMenu;
    private JTextField txtNomeClasse;
	private ObjectTableModel<Atributo> model = null;
	private List<Classe> listaClasses = new ArrayList<Classe>();
	private List<Atributo> listaAtributo = new ArrayList<Atributo>();
	private JTable table;
	private JTextField textField;
	private JTextField txtNomeConexao;
	private JTextField txtUrl;
	private JTextField txtDialect;
	private JTextField txtUsuario;
	private JTextField txtSenha;
	private JTextField txtNomeProjeto;
	private JTextField txtCaminho;
	private JTextField txtCaminhoClasse;
	private JTextField txtDriver;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); // Configurar o LookAndFeel para o estilo do Windows
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Menu window = new Menu();
                window.frmGensourceMenu.setVisible(true);
            }
        });
    }

    public Menu() {
        initialize();
    }

    private void initialize() {
        frmGensourceMenu = new JFrame();
        frmGensourceMenu.getContentPane().setBackground(new Color(255, 255, 255));
        frmGensourceMenu.addWindowListener(new WindowAdapter() {
        	@Override
        	public void windowOpened(WindowEvent e) {
        		ajusta();
        	}
        });
        frmGensourceMenu.getContentPane().setForeground(new Color(255, 255, 255));
        frmGensourceMenu.setTitle("GENSOURCE - Gerador de CRUD");
        frmGensourceMenu.setBounds(100, 100, 716, 648);
        frmGensourceMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmGensourceMenu.getContentPane().setLayout(null);

        JPanel panelCabecalho = new JPanel();
        panelCabecalho.setBackground(new Color(255, 255, 255));
        panelCabecalho.setBounds(0, 0, 700, 97);
        frmGensourceMenu.getContentPane().add(panelCabecalho);
        panelCabecalho.setLayout(null);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setIcon(new ImageIcon("C:\\workspace\\gensource\\src\\gensource_realese\\icon\\LogoPrincipal2.png"));
        lblNewLabel.setBounds(0, 0, 701, 97);
        panelCabecalho.add(lblNewLabel);

        JPanel panelGuias = new JPanel();
        panelGuias.setBounds(0, 97, 700, 446);
        frmGensourceMenu.getContentPane().add(panelGuias);
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
        
        JLabel lblNewLabel_1_3_1 = new JLabel("Caminho do Projeto:");
        lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_1_3_1.setBounds(250, 30, 127, 19);
        panelProjeto.add(lblNewLabel_1_3_1);
        
        txtCaminho = new JTextField();
        txtCaminho.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtCaminho.setColumns(10);
        txtCaminho.setBounds(375, 28, 179, 23);
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
        
        JButton btnAvancar_1 = new JButton("Avancar");
        btnAvancar_1.setBounds(543, 284, 122, 34);
        panelProjeto.add(btnAvancar_1);
        
        JButton btnAvancar_1_1 = new JButton("Selecionar Projeto");
        btnAvancar_1_1.setBounds(411, 284, 122, 34);
        panelProjeto.add(btnAvancar_1_1);
        
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
        btnSelecionarCaminhoProjeto.setBounds(564, 26, 101, 28);
        panelProjeto.add(btnSelecionarCaminhoProjeto);
        
        JLabel lblNewLabel_4 = new JLabel("Para avan\u00E7ar para as outras guias \u00E9 necess\u00E1rio criar ou selecionar um projeto!");
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4.setForeground(new Color(255, 0, 0));
        lblNewLabel_4.setBounds(20, 370, 675, 14);
        abaProjeto.add(lblNewLabel_4);
        tabbedPane.addTab("Classe", abaClasse);
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
        txtNomeClasse.setBounds(58, 26, 121, 23);
        panelClass.add(txtNomeClasse);
        txtNomeClasse.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtNomeClasse.setColumns(10);
        
        JButton btnGerarClasse = new JButton("Gerar Classe");
        btnGerarClasse.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		verificaGerarClasse();
        	}
        });
        btnGerarClasse.setBounds(385, 351, 111, 34);
        panelClass.add(btnGerarClasse);
        
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
        
        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textField.setColumns(10);
        textField.setBounds(67, 24, 153, 23);
        panel.add(textField);
        
        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblTipo.setBounds(230, 26, 38, 21);
        panel.add(lblTipo);
        
        JComboBox cbTipo = new JComboBox();
        cbTipo.setModel(new DefaultComboBoxModel(new String[] {"", "Int", "Long", "Boolean", "Char", "Float", "Double", "String"}));
        cbTipo.setBounds(267, 24, 127, 23);
        panel.add(cbTipo);
        
        JCheckBox checkObrigatorio = new JCheckBox("Obrigat\u00F3rio");
        checkObrigatorio.setBounds(277, 59, 102, 23);
        panel.add(checkObrigatorio);
        
        JCheckBox checkRelacionamento = new JCheckBox("Relacionamento");
        checkRelacionamento.setBounds(393, 59, 112, 23);
        panel.add(checkRelacionamento);
        
        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				Classe c = new Classe();
				c.setNomeClasse("Cliente");
				
				Atributo at = new Atributo();
				at.setNomeAtributo("id");
				at.setTipoAtributo("Long");
				
				Atributo at2 = new Atributo();
				at2.setNomeAtributo("nome");
				at2.setTipoAtributo("String");
				
				Atributo at3 = new Atributo();
				at3.setNomeAtributo("idade");
				at3.setTipoAtributo("int");
				
				List<Atributo> listaAtributos = new ArrayList<>();
				listaAtributos.add(at);
				listaAtributos.add(at2);
				listaAtributos.add(at3);
				
				c.setAtributos(listaAtributos);
				
				listaClasses.add(c);
				
				for(int i=0; i<listaAtributos.size(); i++) {
					listaAtributos.get(i).setIsObrigatorio(true);
					listaAtributos.get(i).setIsRelacionamento(false);
				}
				listaAtributo = listaAtributos;
				carregaTable();
        	}
        });
        btnAdicionar.setBounds(511, 55, 112, 31);
        panel.add(btnAdicionar);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 97, 613, 118);
        panel.add(scrollPane);
        
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Atributo atributo = model.getValue(table.getSelectedColumn());
				TelaAnotacao tela = new TelaAnotacao();
				tela.setAtributo(atributo);
				tela.getFrame().setVisible(true);
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.setBounds(119, 247, 122, 23);
		panel.add(btnRemover);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(383, 247, 122, 23);
		panel.add(btnEditar);
		
		JLabel lblNewLabel_2 = new JLabel("Clique duas vezes em cima do campo que deseja adicionar/editar anota\u00E7\u00F5es");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(10, 222, 613, 14);
		panel.add(lblNewLabel_2);
		
		JCheckBox chckbxConsulta = new JCheckBox("Consulta Por");
		chckbxConsulta.setBounds(154, 59, 110, 23);
		panel.add(chckbxConsulta);
		
		JCheckBox chckbxApareceNaConsulta = new JCheckBox("Aparece na Consulta");
		chckbxApareceNaConsulta.setBounds(10, 59, 132, 23);
		panel.add(chckbxApareceNaConsulta);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(251, 247, 122, 23);
		panel.add(btnLimpar);
		
		JButton btnRemoverClasse = new JButton("Excluir Classe");
		btnRemoverClasse.setBounds(34, 351, 105, 34);
		panelClass.add(btnRemoverClasse);
		
		JButton btnEditarClasse = new JButton("Editar Classe");
		btnEditarClasse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Abrir tela que seleciona classe e tras para essa e preenche campos com a classe
			}
		});
		btnEditarClasse.setBounds(149, 351, 105, 34);
		panelClass.add(btnEditarClasse);
		
		JLabel lblCaminhoDaClasse = new JLabel("Diret\u00F3rio:");
		lblCaminhoDaClasse.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCaminhoDaClasse.setBounds(189, 26, 65, 21);
		panelClass.add(lblCaminhoDaClasse);
		
		txtCaminhoClasse = new JTextField();
		txtCaminhoClasse.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCaminhoClasse.setColumns(10);
		txtCaminhoClasse.setBounds(248, 26, 208, 23);
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
		btnSelecionarCaminhoClasse.setBounds(466, 24, 81, 28);
		panelClass.add(btnSelecionarCaminhoClasse);
		
		JButton btnSalvarClasse = new JButton("Salvar Classe");
		btnSalvarClasse.setBounds(264, 351, 111, 34);
		panelClass.add(btnSalvarClasse);
		
		JButton btnAvanar = new JButton("Gerar Telas");
		btnAvanar.setBounds(506, 351, 111, 34);
		panelClass.add(btnAvanar);
		
		JCheckBox chckbxGerarCrud = new JCheckBox("Gerar CRUD");
		chckbxGerarCrud.setBounds(553, 26, 90, 23);
		panelClass.add(chckbxGerarCrud);
		
		JButton btnGerar = new JButton("Gerar Projeto");
        btnGerar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                        	Projeto projeto = montaProjeto();
                            gerarProjetoMaven(projeto);
                        } catch (Exception ex) {
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    JOptionPane.showMessageDialog(null, "Ocorreu um erro ao gerar o projeto:\n" + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                                }
                            });
                        }
                    }
                });
                thread.start();
            }
        });
		btnGerar.setBounds(553, 554, 122, 44);
		frmGensourceMenu.getContentPane().add(btnGerar);
		
		JButton btnGerarClasse_1_1 = new JButton("Limpar");
		btnGerarClasse_1_1.setBounds(421, 554, 122, 44);
		frmGensourceMenu.getContentPane().add(btnGerarClasse_1_1);
		
		JButton btnGerarClasse_1_1_1 = new JButton("Fechar");
		btnGerarClasse_1_1_1.setBounds(289, 554, 122, 44);
		frmGensourceMenu.getContentPane().add(btnGerarClasse_1_1_1);
    }
    
	public void carregaTable() {
		AnnotationResolver resolver = new AnnotationResolver(Atributo.class);
		model = new ObjectTableModel<Atributo>(resolver, "nomeAtributo:Nome,tipoAtributo:Tipo,isObrigatorio:Obrigatório,isRelacionamento:Relacionamento");
		model.setData(listaAtributo);
		table.setModel(model);
	    table.getColumnModel().getColumn(0).setPreferredWidth(100);
	}
	
	public void ajusta() {
		carregaTable();
	}
	
	public void verificaGerarClasse() {
		
	}
	
	private Projeto montaProjeto() {
		montaClassesTeste();
		
		Conexao conexao = new Conexao();
        conexao.setNomeConexao(txtNomeConexao.getText());
        conexao.setUrl(txtUrl.getText());
        conexao.setDialect(txtDialect.getText());
        conexao.setUsuario(txtUsuario.getText());
        conexao.setSenha(txtSenha.getText());
        conexao.setDriver(txtDriver.getText());
		
		Projeto projeto = new Projeto();
		projeto.setConexao(conexao);
		projeto.setClasses(getListaClasses());	
		projeto.setDiretorioProjeto(txtCaminho.getText() + "\\" + txtNomeProjeto.getText());
		
		return projeto;
	}
	
	private void montaClassesTeste() {
		Classe classe1 = new Classe();
		classe1.setDiretorioClasse("com.classes");
		classe1.setNomeClasse("Produto");
		Atributo at1 = new Atributo();
		at1.setNomeAtributo("codigo");
		at1.setTipoAtributo("Integer");
		String anotacao1 = "@Id";
		String anotacao2 = "@GeneratedValue(strategy = GenerationType.IDENTITY)";
		List<String> anotacoes = new ArrayList<>();
		anotacoes.add(anotacao1);
		anotacoes.add(anotacao2);
		at1.setAnotacao(anotacoes);
		List<Atributo> atributos = new ArrayList<>();
		atributos.add(at1);
		Atributo at2 = new Atributo();
		at2.setNomeAtributo("nome");
		at2.setTipoAtributo("String");
		atributos.add(at2);
		classe1.setAtributos(atributos);
		listaClasses.add(classe1);

		// Classe 2
		Classe classe2 = new Classe();
		classe2.setDiretorioClasse("com.classes.cliente");
		classe2.setNomeClasse("Cliente");
		Atributo at3 = new Atributo();
		at3.setNomeAtributo("id");
		at3.setTipoAtributo("Long");
		String anotacao3 = "@Id";
		String anotacao4 = "@GeneratedValue(strategy = GenerationType.IDENTITY)";
		List<String> anotacoes2 = new ArrayList<>();
		anotacoes2.add(anotacao3);
		anotacoes2.add(anotacao4);
		at3.setAnotacao(anotacoes2);
		List<Atributo> atributos2 = new ArrayList<>();
		atributos2.add(at3);
		Atributo at4 = new Atributo();
		at4.setNomeAtributo("nome");
		at4.setTipoAtributo("String");
		atributos2.add(at4);
		classe2.setAtributos(atributos2);
		listaClasses.add(classe2);

		// Classe 3
		Classe classe3 = new Classe();
		classe3.setDiretorioClasse("com.classes.cliente");
		classe3.setNomeClasse("Pedido");
		Atributo at5 = new Atributo();
		at5.setNomeAtributo("id");
		at5.setTipoAtributo("Long");
		String anotacao5 = "@Id";
		String anotacao6 = "@GeneratedValue(strategy = GenerationType.IDENTITY)";
		List<String> anotacoes3 = new ArrayList<>();
		anotacoes3.add(anotacao5);
		anotacoes3.add(anotacao6);
		at5.setAnotacao(anotacoes3);
		List<Atributo> atributos3 = new ArrayList<>();
		atributos3.add(at5);
		Atributo at6 = new Atributo();
		at6.setNomeAtributo("valorTotal");
		at6.setTipoAtributo("BigDecimal");
		atributos3.add(at6);
		classe3.setAtributos(atributos3);
		listaClasses.add(classe3);
	}
	
    private void gerarProjetoMaven(Projeto projeto) throws IOException, InterruptedException {
        File diretorio = new File(projeto.getDiretorioProjeto());
        if (!diretorio.exists()) {
            if (!diretorio.mkdirs()) {
                throw new IOException("Não foi possível criar o diretório: " + projeto.getDiretorioProjeto());
            }
        }
        
        montaPomXml(projeto);
        criarClasses(projeto);
        montaPersistenceXml(projeto);
        JOptionPane.showMessageDialog(null, "Projeto gerado com sucesso!");
        
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "mvn clean install");
        builder.directory(diretorio);
        builder.redirectErrorStream(true);
        Process process = builder.start();
        process.waitFor();
    }
    
    private void montaPomXml(Projeto projeto) {
        // Criar o arquivo pom.xml com as configurações necessárias
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

    
    private void criarClasses(Projeto projeto) throws IOException {
    	daoGenerico(projeto.getDiretorioProjeto(), projeto.getConexao().getNomeConexao());
    	
        // Mapeamento entre tipos primitivos e seus imports correspondentes
        Map<String, String> tipoImportMap = new HashMap<>();
        tipoImportMap.put("BigDecimal", "java.math.BigDecimal");
        // Adicione outros mapeamentos conforme necessário
    	
        for (Classe classe : projeto.getClasses()) {
            String pacoteBase = classe.getDiretorioClasse();
            String nomeClasse = classe.getNomeClasse();
            List<Atributo> atributos = classe.getAtributos();

            StringBuilder codigoClasse = new StringBuilder();
            codigoClasse.append("package ").append(pacoteBase).append(";\n\n");
            
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
            String diretorioSrc = projeto.getDiretorioProjeto() + "\\src\\main\\java\\" + pacoteBase.replace('.', '\\');
            Files.createDirectories(Paths.get(diretorioSrc));
            Files.write(Paths.get(diretorioSrc + "\\" + nomeClasse + ".java"), codigoClasse.toString().getBytes());
            
            // Código para criar a classe DAO correspondente utilizando a GenericDAO
            StringBuilder codigoDAO = new StringBuilder();
            codigoDAO.append("package ").append("dao").append(";\n\n");
            codigoDAO.append("import ").append(classe.getDiretorioClasse()).append("."+nomeClasse).append(";\n\n");
            codigoDAO.append("public class ").append(nomeClasse).append("DAO extends GenericDAO<").append(nomeClasse).append("> {\n\n");
            codigoDAO.append("    public ").append(nomeClasse).append("DAO() {\n");
            codigoDAO.append("        super(").append(nomeClasse).append(".class);\n");
            codigoDAO.append("    }\n\n");
            codigoDAO.append("}");

            // Criar a pasta do pacote base se ainda não existe
            String diretorioSrcDAO = projeto.getDiretorioProjeto() + "\\src\\main\\java\\dao";
            Files.createDirectories(Paths.get(diretorioSrcDAO));

            // Escrever o código da classe DAO no arquivo .java
            Files.write(Paths.get(diretorioSrcDAO + "\\" + nomeClasse + "DAO.java"), codigoDAO.toString().getBytes());
        }
    }
    
    private void daoGenerico(String diretorioProjeto, String nomeConexao) throws IOException {
    	StringBuilder codigoGenericDAO = new StringBuilder();
    	codigoGenericDAO.append("package dao;\n");
    	codigoGenericDAO.append("import javax.persistence.EntityManager;\n");
    	codigoGenericDAO.append("import javax.persistence.EntityManagerFactory;\n");
    	codigoGenericDAO.append("import javax.persistence.Persistence;\n");
    	codigoGenericDAO.append("import java.util.List;\n\n");
    	codigoGenericDAO.append("public class GenericDAO<T> {\n\n");
    	codigoGenericDAO.append("    private static final String PERSISTENCE_UNIT_NAME = \""+nomeConexao+"\"; // Nome da unidade de persistência no persistence.xml\n\n");
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
    	codigoGenericDAO.append("    }\n");
    	codigoGenericDAO.append("}\n");
    	
        // Criar a pasta do pacote base se ainda não existe
        String diretorioSrcDAO = diretorioProjeto + "\\src\\main\\java\\dao";
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

	public List<Atributo> getListaAtributo() {
		return listaAtributo;
	}

	public void setListaAtributo(List<Atributo> listaAtributo) {
		this.listaAtributo = listaAtributo;
	}
    
    
}
