package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.table.DefaultTableModel;

import negócio.ControladorEmpresas;
import negócio.ControladorPessoas;
import negócio.entity_beans.Empresa;
import negócio.entity_beans.Funcionario;
import negócio.entity_beans.Pessoa;
import negócio.entity_beans.exceptionsBeans.CNPJInvalidoException;
import negócio.entity_beans.exceptionsBeans.NomeInvalidoException;

import javax.swing.JButton;

import dados.RepPessoas;

public class FrameAdmin1 extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtBusca;
	private ControladorPessoas controladorPessoas;
	private ControladorEmpresas controladorEmpresas;
	private DefaultTableModel modeloTable;
	private JTable tableFuncionarios; 
	private JButton btnPesquisar; 
	private JScrollPane scrllPnFuncionarios;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameAdmin1 frame = new FrameAdmin1();
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
	public FrameAdmin1() throws Exception {
		
		this.controladorPessoas = new ControladorPessoas(new RepPessoas());
		char[] senha = {'1','2','3','4'};
		
		
		// TESTE ...
		
		Empresa empresa = new Empresa("UFRPE","2414","25","235");
		Funcionario funcionario3 = new Funcionario("Lima","123","lima@gmail",senha,"telefone", empresa,"Estudante","8h/dia",LocalTime.of(8,0),LocalTime.of(12,0),LocalTime.of(10,0),LocalTime.of(10,15));
		controladorPessoas.adicionarPessoa(funcionario3);
		Funcionario funcionario = new Funcionario("Ikaro","12345","ikaroalef@gmail.com",senha,"telefone",empresa,"Estudante","8h/dia",LocalTime.of(8,0),LocalTime.of(12,0),LocalTime.of(10,0),LocalTime.of(10,15));
		Funcionario funcionario1 = new Funcionario("Alef","1234","alef@gmail.com",senha,"telefone",empresa,"Estudante","8h/dia",LocalTime.of(8,0),LocalTime.of(12,0),LocalTime.of(10,0),LocalTime.of(10,15));
		controladorPessoas.adicionarPessoa(funcionario);
		controladorPessoas.adicionarPessoa(funcionario1);
		
		//...TESTE
		
		//Modelo de Tabela.
		this.modeloTable = new DefaultTableModel(new Object[][]{}, new Object[]{"Nome","CPF","Telefone","Cargo"});
		this.preencherTableFuncionarios(null);
			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(3, 100, 1360, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.txtBusca = new JTextField();
		txtBusca.setBounds(115, 41, 600, 20);
		contentPane.add(txtBusca);
		txtBusca.setColumns(50);
		
		JLabel lblProcurar = new JLabel("Pesquisar:");
		lblProcurar.setBounds(30, 44, 75, 14);
		contentPane.add(lblProcurar);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 97, 21);
		contentPane.add(menuBar);
		
		this.tableFuncionarios = new JTable(modeloTable);

	    this.scrllPnFuncionarios = new JScrollPane(tableFuncionarios);
	    scrllPnFuncionarios.setLocation(30, 104);
		scrllPnFuncionarios.setSize(1290, 360);
//      scrollPane.setVisible(true);
	    contentPane.add(scrllPnFuncionarios);
	    
	    btnPesquisar = new JButton("Pesquisar");
	    btnPesquisar.setBounds(747, 40, 102, 23);
	    btnPesquisar.addActionListener(this);
	    contentPane.add(btnPesquisar);
		    
		}

	//preenche a table de acordo com o parametro: mostra todos os Funcionarios cujo nome contém a String passado como parâmetro.
	//caso o parametro seja NULL, preenche com todos os Funcionarios
	public void preencherTableFuncionarios(String nome){ 
		this.limparTableFuncionarios();
		ArrayList<Pessoa>pessoas = controladorPessoas.getPessoas(nome);
		String[] linha= new String[5];
		for (int i=0; i < pessoas.size() ; i++){
			if (pessoas.get(i) instanceof Funcionario){
				linha[0] = pessoas.get(i).getNome();
				linha[1] = pessoas.get(i).getCpf();
				linha[2] = pessoas.get(i).getEmail();
				linha[3] = ((Funcionario) pessoas.get(i)).getTelefone();
				linha[4] = ((Funcionario) pessoas.get(i)).getCargo();
				modeloTable.addRow(linha);
			}
		}
	}
	
	//limpa a tableFuncionario
	private void limparTableFuncionarios() { 
		while (modeloTable.getRowCount() > 0) {
		modeloTable.removeRow(0);
		}
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnPesquisar)){
			this.preencherTableFuncionarios(txtBusca.getText());
		}
	}
	
}


