package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import conexaoBD.FabricaDeConexao;
import negócio.EpontoFachada;
import negócio.entity_beans.Admin;
import negócio.entity_beans.Empresa;
import negócio.entity_beans.Funcionario;
import negócio.entity_beans.Pessoa;
import negócio.entity_beans.exceptionsBeans.NomeInvalidoException;

import javax.swing.JButton;

import dados.exceptionsDados.FuncionarioNaoEncontradoException;

import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

public class FrameAdmin1 extends JFrame implements ActionListener, MouseListener, WindowListener, FocusListener {

	private Admin admin;
	private JPanel contentPane;
	private JTextField txtBusca;
	private DefaultTableModel modeloTable;
	private JTable tableFuncionarios; 
	private JButton btnPesquisar; 
	private JScrollPane scrllPnFuncionarios;
	private JButton btnCadastrarFuncionario;
	private JButton btnMostrarTodos;
	private JButton btnCadastrarEmpresa;
	private JButton btnExcluirFuncionario;
	private JButton btnCadastrarAdministrador;
	private JButton btnCadastrarProjeto;
	private JComboBox<Empresa> cmbBxEmpresa;
	private JLabel lblEmpresa;
	private static final String AdminSuper= EpontoFachada.getInstance().getPessoas(null).get(0).getCpf();
	private JLabel lblModoAdmin;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameAdmin1 frame = new FrameAdmin1((Admin) EpontoFachada.getInstance().getPessoaCpf(AdminSuper));
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
	public FrameAdmin1(Admin admin) {
		setTitle("Administrador " + admin.getNome());		

		//Modelo de Tabela.
		this.modeloTable = new DefaultTableModel(new Object[][]{}, new Object[]{"Nome","CPF","E-Mail","Telefone","Cargo","Empresa"}){
			 @Override
			    public boolean isCellEditable(int row, int column) { //sobreescreve pra tornar todas as células NÃO editáveis
			        //all cells false
			        return false;
			    }
		};
		
		this.admin = admin;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(3, 100, 1360, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(211, 211, 211));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		addWindowListener(this);
		
		cmbBxEmpresa = new JComboBox<Empresa>();
	    cmbBxEmpresa.setBounds(515, 144, 145, 20);
	    for (int i = 0; i < admin.getEmpresas().size(); i++){
	    	cmbBxEmpresa.addItem(admin.getEmpresas().get(i));
	    }
	    cmbBxEmpresa.addActionListener(this);
	    contentPane.add(cmbBxEmpresa);

	    if(admin.getCpf().equals(AdminSuper))
	    		this.preencherTableFuncionariosSuper(null);
	    else
	    	this.preencherTableFuncionarios(null);
		
		this.txtBusca = new JTextField();
		txtBusca.setBounds(515, 113, 241, 20);
		contentPane.add(txtBusca);
		txtBusca.setColumns(50);
		
		JLabel lblProcurar = new JLabel("Pesquisar:");
		lblProcurar.setForeground(new Color(0, 0, 0));
		lblProcurar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProcurar.setBounds(435, 116, 70, 14);
		contentPane.add(lblProcurar);
		
		this.tableFuncionarios = new JTable(modeloTable);
		tableFuncionarios.addMouseListener(this);

	    this.scrllPnFuncionarios = new JScrollPane(tableFuncionarios);
	    scrllPnFuncionarios.setLocation(376, 190);
		scrllPnFuncionarios.setSize(811, 360);
	    contentPane.add(scrllPnFuncionarios);
	    
	    btnPesquisar = new JButton("Pesquisar");
	    btnPesquisar.setForeground(new Color(0, 0, 0));
	    btnPesquisar.setBounds(781, 112, 102, 23);
	    btnPesquisar.addActionListener(this);
	    contentPane.add(btnPesquisar);
	    
	    btnMostrarTodos = new JButton("Mostrar Todos");
	    btnMostrarTodos.setForeground(new Color(0, 0, 0));
	    btnMostrarTodos.setBounds(895, 112, 118, 23);
	    btnMostrarTodos.addActionListener(this);
	    contentPane.add(btnMostrarTodos);
	    
	    lblEmpresa = new JLabel("Empresa:");
	    lblEmpresa.setForeground(new Color(0, 0, 0));
	    lblEmpresa.setHorizontalAlignment(SwingConstants.RIGHT);
	    lblEmpresa.setBounds(434, 147, 65, 14);
	    contentPane.add(lblEmpresa);
	    
	    JPanel panel = new JPanel();
	    panel.setBackground(new Color(105, 105, 105));
	    panel.setBounds(0, 0, 241, 561);
	    contentPane.add(panel);
	    panel.setLayout(null);
	    
	    btnCadastrarFuncionario = new JButton("Cadastrar Funcionario");
	    btnCadastrarFuncionario.setForeground(new Color(0, 0, 0));
	    btnCadastrarFuncionario.setBounds(28, 256, 180, 39);
	    panel.add(btnCadastrarFuncionario);
	    
	    btnCadastrarAdministrador = new JButton("Cadastrar Administrador");
	    btnCadastrarAdministrador.setForeground(new Color(0, 0, 0));
	    btnCadastrarAdministrador.setBounds(28, 206, 180, 39);
	    panel.add(btnCadastrarAdministrador);
	    
	    JLabel lblInponto = new JLabel("InPonto");
	    lblInponto.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 29));
	    lblInponto.setForeground(new Color(143, 188, 143));
	    lblInponto.setBounds(74, 133, 98, 22);
	    panel.add(lblInponto);
	    
	    btnCadastrarProjeto = new JButton("Cadastrar Projeto");
	    btnCadastrarProjeto.setForeground(new Color(0, 0, 0));
	    btnCadastrarProjeto.setBounds(28, 356, 180, 39);
	    panel.add(btnCadastrarProjeto);
	    btnCadastrarProjeto.addActionListener(this);
	    
	    btnExcluirFuncionario = new JButton("Excluir Funcion\u00E1rio");
	    btnExcluirFuncionario.setBounds(28, 406, 180, 39);
	    panel.add(btnExcluirFuncionario);
	    btnExcluirFuncionario.setForeground(new Color(0, 0, 0));
	    
	    btnCadastrarEmpresa = new JButton("Cadastrar Empresa");
	    btnCadastrarEmpresa.setBounds(28, 306, 180, 39);
	    panel.add(btnCadastrarEmpresa);
	    btnCadastrarEmpresa.setForeground(new Color(0, 0, 0));
	    btnCadastrarEmpresa.addActionListener(this);
	    btnExcluirFuncionario.addActionListener(this);
	    
	    lblModoAdmin = new JLabel("MODO ADMINISTRADOR");
	    lblModoAdmin.setForeground(new Color(0,0,0));
	    lblModoAdmin.setFont(new Font("Tahoma", Font.BOLD, 16));
	    lblModoAdmin.setBounds(645, 44, 200, 20);
	    contentPane.add(lblModoAdmin);
	    btnCadastrarAdministrador.addActionListener(this);
	    btnCadastrarFuncionario.addActionListener(this);
		    
		}

	//preenche a table de acordo com o parametro: mostra todos os Funcionarios cujo nome contém a String passado como parâmetro.
	//caso o parametro seja NULL, preenche com todos os Funcionarios
	public void preencherTableFuncionarios(String nome){ 
		this.limparTableFuncionarios();
		
		ArrayList<Pessoa>pessoas = EpontoFachada.getInstance().getPessoas(nome);
		
		FabricaDeConexao bd = new FabricaDeConexao();
		Connection con = null;
		try {
			con = bd.getConexao("admin", "bancodedados");
			con.setAutoCommit(false);
			ResultSet rsFunc = con.createStatement().executeQuery(
					"SELECT pessoa.nome as Nome, pessoa.cpf as CPF, pessoa.email as Email, pessoa.telefone as Telefone, cargo.nome as Cargo FROM funcionario JOIN pessoa JOIN cargo WHERE funcionario.cpf = pessoa.cpf and cargo.codigo = pessoa.cargo;");
			
			String[] linha= new String[5];
			
			while(rsFunc.next()){
				linha[0] = rsFunc.getString("Nome");
				linha[1] = rsFunc.getString("CPF");
				linha[2] = rsFunc.getString("Email");
				linha[3] = rsFunc.getString("Telefone");
				linha[4] = rsFunc.getString("Cargo");
				modeloTable.addRow(linha);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[] linha= new String[6];
		
		Empresa empresa = (Empresa) cmbBxEmpresa.getSelectedItem();
//		for (int i=0; i < pessoas.size() ; i++){
//			if (pessoas.get(i) instanceof Funcionario && 
//					((Funcionario)pessoas.get(i)).getEmpresa().igualNome(empresa.getNomeEmpresa())){
//				linha[0] = pessoas.get(i).getNome();
//				linha[1] = pessoas.get(i).getCpf();
//				linha[2] = pessoas.get(i).getEmail();
//				linha[3] = ((Funcionario) pessoas.get(i)).getTelefone();
//				linha[4] = ((Funcionario) pessoas.get(i)).getCargo();
//				linha[5] = ((Funcionario) pessoas.get(i)).getEmpresa().getNomeEmpresa();
//				modeloTable.addRow(linha);
//			}
//			if (pessoas.get(i) instanceof Admin && 
//					((Admin)pessoas.get(i)).getEmpresas().contains(cmbBxEmpresa.getSelectedItem())){
//				linha[0] = pessoas.get(i).getNome();
//				linha[1] = pessoas.get(i).getCpf();
//				linha[2] = pessoas.get(i).getEmail();
//				linha[3] = "";
//				linha[4] = "";
//				linha[5] = ((Admin) pessoas.get(i)).getStringEmpresas();
//				modeloTable.addRow(linha);
//			}
//		}
	}
	
	public void preencherTableFuncionariosSuper(String nome){ 
		this.limparTableFuncionarios();
		
		ArrayList<Pessoa>pessoas = EpontoFachada.getInstance().getPessoas(nome);
		String[] linha= new String[6];
		for (int i=0; i < pessoas.size() ; i++){
			if (pessoas.get(i) instanceof Funcionario) {
				linha[0] = pessoas.get(i).getNome();
				linha[1] = pessoas.get(i).getCpf();
				linha[2] = pessoas.get(i).getEmail();
				linha[3] = ((Funcionario) pessoas.get(i)).getTelefone();
				linha[4] = ((Funcionario) pessoas.get(i)).getCargo();
				linha[5] = ((Funcionario) pessoas.get(i)).getEmpresa().getNomeEmpresa();
				modeloTable.addRow(linha);
			}
			else if(pessoas.get(i) instanceof Admin){
				linha[0] = pessoas.get(i).getNome();
				linha[1] = pessoas.get(i).getCpf();
				linha[2] = pessoas.get(i).getEmail();
				linha[3] = "";
				linha[4] = "";
				linha[5] = ((Admin) pessoas.get(i)).getStringEmpresas();
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
			if (admin.getCpf().equals(AdminSuper))
				this.preencherTableFuncionariosSuper(txtBusca.getText());
			else
				this.preencherTableFuncionarios(txtBusca.getText());
		}
		
		else if(e.getSource().equals(btnCadastrarFuncionario)){
			if(EpontoFachada.getInstance().getSizeEmpresas()<1)
				JOptionPane.showMessageDialog(null, "É necessário cadastrar uma empresa antes de cadastrar um funcionário.");
			else 
				ControladorDeTelas.getInstance().frameCadastrarFuncionario();
		}
		
		else if(e.getSource().equals(btnMostrarTodos)){
			if(admin.getCpf().equals(AdminSuper))
				this.preencherTableFuncionariosSuper(null);
			else 
				this.preencherTableFuncionarios(null);
		} 	
		
		else if(e.getSource().equals(btnExcluirFuncionario)){
			if(tableFuncionarios.getSelectedRowCount()==0)
				JOptionPane.showMessageDialog(null, "Por favor, selecione pelo menos um funcionário.");
			else{
				String nomes[] = new String[tableFuncionarios.getSelectedRowCount()];
				int[] linhasSelecionadas = tableFuncionarios.getSelectedRows();
				for (int i = 0; i < tableFuncionarios.getSelectedRowCount(); i++){
					nomes[i] = (String) tableFuncionarios.getValueAt(linhasSelecionadas[i], 0);
				}
				try {
					EpontoFachada.getInstance().deletarPessoas(nomes);
					JOptionPane.showMessageDialog(null, "Funcionário (s) excluído (s) com sucesso.");
				} catch (FuncionarioNaoEncontradoException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		}
		
		else if(e.getSource().equals(btnCadastrarEmpresa)){
			ControladorDeTelas.getInstance().frameCadastrarEmpresa();
		}
		
		else if(e.getSource().equals(btnCadastrarProjeto)){
			ControladorDeTelas.getInstance().frameCadastrarProjeto();
		}
		
		else if(e.getSource().equals(btnCadastrarAdministrador))
			if(EpontoFachada.getInstance().getSizeEmpresas()<1)
				JOptionPane.showMessageDialog(null, "É necessário cadastrar uma empresa antes de cadastrar um Administrador.");
			else 
			ControladorDeTelas.getInstance().frameCadastrarAdmin();
		else if(e.getSource().equals(cmbBxEmpresa)){
			this.preencherTableFuncionarios(null);
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) { //evento para double click nna tableFuncionarios;
		if (e.getClickCount() == 2) {
		      JTable target = (JTable)e.getSource();
		      int row = target.getSelectedRow();
		      Pessoa pessoaSelecionada = null;
			try {
				pessoaSelecionada = EpontoFachada.getInstance().getPessoaCpf((String) target.getValueAt(row, 1));
			} catch (FuncionarioNaoEncontradoException | NomeInvalidoException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		      if(pessoaSelecionada instanceof Funcionario)
				  ControladorDeTelas.getInstance().frameAdmin2((Funcionario) pessoaSelecionada);
			  else if(pessoaSelecionada instanceof Admin)
				  ControladorDeTelas.getInstance().frameEditarAdmin((Admin) pessoaSelecionada);
		      
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

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		ControladorDeTelas.getInstance().frameLogin();
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		this.preencherTableFuncionarios(null);
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}


