package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.table.DefaultTableModel;

import negócio.ControladorEmpresas;
import negócio.ControladorPessoas;
import negócio.EpontoFachada;
import negócio.entity_beans.Empresa;
import negócio.entity_beans.Funcionario;
import negócio.entity_beans.Pessoa;
import negócio.entity_beans.exceptionsBeans.CNPJInvalidoException;
import negócio.entity_beans.exceptionsBeans.NomeInvalidoException;

import javax.swing.JButton;

import dados.RepPessoas;
import dados.exceptionsDados.FuncionarioNaoEncontradoException;

public class FrameAdmin1 extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private JTextField txtBusca;
	private DefaultTableModel modeloTable;
	private JTable tableFuncionarios; 
	private JButton btnPesquisar; 
	private JScrollPane scrllPnFuncionarios;
	private JButton btnCadastrarFuncionario;
	private JButton btnMostrarTodos;
	private JButton btnCadastrarEmpresa;
	private JButton btnExcluirFuncionrio;
	
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
		setTitle("Administrador");		
		
		// TESTE ...
		//...TESTE
		
		//Modelo de Tabela.
		this.modeloTable = new DefaultTableModel(new Object[][]{}, new Object[]{"Nome","CPF","E-Mail","Telefone","Cargo","Empresa"}){
			 @Override
			    public boolean isCellEditable(int row, int column) { //sobreescreve pra tornar todas as células NÃO editáveis
			        //all cells false
			        return false;
			    }
		};
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
		tableFuncionarios.addMouseListener(this);

	    this.scrllPnFuncionarios = new JScrollPane(tableFuncionarios);
	    scrllPnFuncionarios.setLocation(30, 104);
		scrllPnFuncionarios.setSize(1290, 360);
//      scrollPane.setVisible(true);
	    contentPane.add(scrllPnFuncionarios);
	    
	    btnPesquisar = new JButton("Pesquisar");
	    btnPesquisar.setBounds(747, 40, 102, 23);
	    btnPesquisar.addActionListener(this);
	    contentPane.add(btnPesquisar);
	    
	    btnCadastrarFuncionario = new JButton("Cadastrar Funcionario");
	    btnCadastrarFuncionario.setBounds(30, 527, 170, 23);
	    btnCadastrarFuncionario.addActionListener(this);
	    contentPane.add(btnCadastrarFuncionario);
	    
	    btnMostrarTodos = new JButton("Mostrar Todos");
	    btnMostrarTodos.setBounds(874, 40, 118, 23);
	    btnMostrarTodos.addActionListener(this);
	    contentPane.add(btnMostrarTodos);
	    
	    btnCadastrarEmpresa = new JButton("Cadastrar Empresa");
	    btnCadastrarEmpresa.setBounds(210, 527, 170, 23);
	    contentPane.add(btnCadastrarEmpresa);
	    btnCadastrarEmpresa.addActionListener(this );
	    
	    btnExcluirFuncionrio = new JButton("Excluir Funcion\u00E1rio");
	    btnExcluirFuncionrio.setBounds(390, 527, 151, 23);
	    contentPane.add(btnExcluirFuncionrio);
	    btnExcluirFuncionrio.addActionListener(this);
		    
		}

	//preenche a table de acordo com o parametro: mostra todos os Funcionarios cujo nome contém a String passado como parâmetro.
	//caso o parametro seja NULL, preenche com todos os Funcionarios
	public void preencherTableFuncionarios(String nome){ 
		this.limparTableFuncionarios();
		ArrayList<Pessoa>pessoas = EpontoFachada.getInstance().getPessoas(nome);
		String[] linha= new String[6];
		for (int i=0; i < pessoas.size() ; i++){
			if (pessoas.get(i) instanceof Funcionario){
				linha[0] = pessoas.get(i).getNome();
				linha[1] = pessoas.get(i).getCpf();
				linha[2] = pessoas.get(i).getEmail();
				linha[3] = ((Funcionario) pessoas.get(i)).getTelefone();
				linha[4] = ((Funcionario) pessoas.get(i)).getCargo();
				linha[5] = ((Funcionario) pessoas.get(i)).getEmpresa().getNomeEmpresa();
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
		else if(e.getSource().equals(btnCadastrarFuncionario)){
			ControladorDeTelas.Admin1ToAdminCadastrarFuncionario();
		}
		else if(e.getSource().equals(btnMostrarTodos)){
			this.preencherTableFuncionarios(null);
		}
		else if(e.getSource().equals(btnExcluirFuncionrio)){
			int row = tableFuncionarios.getSelectedRow();
			try {
				EpontoFachada.getInstance().deletarPessoa((String)tableFuncionarios.getValueAt(row, 0));
			} catch (FuncionarioNaoEncontradoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Funcionário excluído com sucesso.");
		}
		else if(e.getSource().equals(btnCadastrarEmpresa)){
			ControladorDeTelas.CadastrarEmpresa();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) { //evento para double click nna tableFuncionarios;
		if (e.getClickCount() == 2) {
		      JTable target = (JTable)e.getSource();
		      int row = target.getSelectedRow();
		      //DAQUI PRA BAIXO, O CÓDIGO
		      try {
				System.out.println(EpontoFachada.getInstance().buscaPessoaNome((String) target.getValueAt(row, 0)).getNome());
			} catch (FuncionarioNaoEncontradoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}


