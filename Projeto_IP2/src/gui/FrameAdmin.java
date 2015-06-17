package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.time.LocalTime;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import negócio.ControladorPessoas;
import negócio.entity_beans.Empresa;
import negócio.entity_beans.Funcionario;
import negócio.entity_beans.exceptionsBeans.CNPJInvalidoException;
import negócio.entity_beans.exceptionsBeans.NomeInvalidoException;

public class FrameAdmin extends JFrame {

	private JPanel contentPane;
	private JTextField txtBusca;
	private ControladorPessoas controladorPessoas;
	private DefaultTableModel modeloTable;
	private JTable tableFuncionarios; 
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameAdmin frame = new FrameAdmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public FrameAdmin() throws Exception {
		
		controladorPessoas = new ControladorPessoas();
		char[] senha = {'1','2','3','4'};
		Empresa empresa = new Empresa("UFRPE","2414","25","235");
		Funcionario funcionario3 = new Funcionario("Lima","123","lima@gmail",senha,"telefone", empresa,"Estudante","8h/dia",LocalTime.of(8,0),LocalTime.of(12,0),LocalTime.of(10,0),LocalTime.of(10,15));
		controladorPessoas.adicionarPessoa(funcionario3);
		Funcionario funcionario = new Funcionario("Ikaro","12345","ikaroalef@gmail.com",senha,"telefone",empresa,"Estudante","8h/dia",LocalTime.of(8,0),LocalTime.of(12,0),LocalTime.of(10,0),LocalTime.of(10,15));
		Funcionario funcionario1 = new Funcionario("Alef","1234","alef@gmail.com",senha,"telefone",empresa,"Estudante","8h/dia",LocalTime.of(8,0),LocalTime.of(12,0),LocalTime.of(10,0),LocalTime.of(10,15));
		controladorPessoas.adicionarPessoa(funcionario);
		controladorPessoas.adicionarPessoa(funcionario1);
		
		modeloTable = new DefaultTableModel(new Object[][]{}, new Object[]{"Nome","CPF","Email","Telefone","Cargo"});
		for (int i=0; i < controladorPessoas.tamanhoLista() ; i++){
			modeloTable.addRow(controladorPessoas.linhaTabela(i));
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1366, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtBusca = new JTextField();
		txtBusca.setBounds(115, 41, 600, 20);
		contentPane.add(txtBusca);
		txtBusca.setColumns(50);
		
		JLabel lblProcurar = new JLabel("Procurar:");
		lblProcurar.setBounds(30, 44, 75, 14);
		contentPane.add(lblProcurar);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 97, 21);
		contentPane.add(menuBar);
		
		    tableFuncionarios = new JTable(modeloTable);

		    JScrollPane scrllPnFuncionarios = new JScrollPane(tableFuncionarios);
		    scrllPnFuncionarios.setLocation(30, 104);
		    scrllPnFuncionarios.setSize(1290, 360);
//		    scrollPane.setVisible(true);
		    contentPane.add(scrllPnFuncionarios);
		    
	}
}
