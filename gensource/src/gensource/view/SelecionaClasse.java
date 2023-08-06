package gensource.view;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;

import gensource.model.Classe;
import gensource.model.enumAndModel.AnotacaoModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;

public class SelecionaClasse extends JDialog{

	private List<Classe> classes = new ArrayList<>();
	private Classe retorno;
	private ObjectTableModel<Classe> model = null;
	private JScrollPane scrollPane;
	private JTable table;
	private JLabel lblNewLabel;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelecionaClasse window = new SelecionaClasse();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SelecionaClasse() {
		initialize();
	}

	private void initialize() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				carregaTable();
			}
		});
		setTitle("GENSOURCE - Selecionar Classe");
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		getContentPane().add(getScrollPane());
		getContentPane().add(getLblNewLabel());
	}

	public List<Classe> getClasses() {
		return classes;
	}

	public void setClasses(List<Classe> classes) {
		this.classes = classes;
	}

	public Classe getRetorno() {
		return retorno;
	}

	public void setRetorno(Classe retorno) {
		this.retorno = retorno;
	}
	
	public void carregaTable() {
		AnnotationResolver resolver = new AnnotationResolver(Classe.class);
		model = new ObjectTableModel<Classe>(resolver, "nomeClasse:Classe,diretorioClasse:Diretorio");
		model.setData(classes);
		table.setModel(model);
	    table.getColumnModel().getColumn(0).setPreferredWidth(50);
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 11, 414, 226);
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}
	private JTable getTable() {
		if (table == null) {
			table = new JTable();
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(e.getClickCount() == 2) {
						setRetorno(model.getValue(table.getSelectedRow()));
						dispose();
					}
				}
			});
		}
		return table;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Clique duas vezes para selecionar a Classe");
			lblNewLabel.setForeground(new Color(255, 0, 0));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(10, 240, 414, 14);
		}
		return lblNewLabel;
	}
}
