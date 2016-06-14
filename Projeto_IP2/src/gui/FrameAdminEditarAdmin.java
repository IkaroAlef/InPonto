package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import negócio.EpontoFachada;
import negócio.entity_beans.Admin;
import negócio.entity_beans.Empresa;

import javax.swing.JPasswordField;

import dados.exceptionsDados.FuncionarioNaoEncontradoException;
import java.awt.Font;
import java.awt.Color;

public class FrameAdminEditarAdmin extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JTextField txtCpf;
	private JButton btnLimpar;
	private JButton btnSalvar;
	private JPasswordField passSenha;
	private JScrollPane scrllPnLista = new JScrollPane();
	private JList<Empresa> jListaEmpresas;
	private DefaultListModel<Empresa> listModel;
	private Admin admin;
	private JButton btnNewButton;
	private JLabel lblEditarAdministrador;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		char[] senha = {'1','1','2'};
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameAdminEditarAdmin frame = new FrameAdminEditarAdmin(new Admin("ikaro","123456t","iajfaijf",senha));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameAdminEditarAdmin(Admin admin) {
		setResizable(false);
		setTitle("Editar Administrador");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 646, 398);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(169, 169, 169));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.admin=admin;
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setForeground(new Color(255, 255, 255));
		lblNome.setBounds(25, 82, 135, 14);
		contentPane.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(25, 107, 369, 20);
		txtNome.setColumns(10);
		txtNome.setText(admin.getNome());
		contentPane.add(txtNome);
		
		JLabel lblSenha = new JLabel("Senha: ");
		lblSenha.setForeground(new Color(255, 255, 255));
		lblSenha.setBounds(205, 193, 63, 14);
		contentPane.add(lblSenha);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(25, 162, 369, 20);
		txtEmail.setText(admin.getEmail());
		contentPane.add(txtEmail);
		
		txtCpf = new JTextField();
		txtCpf.setColumns(10);
		txtCpf.setBounds(25, 218, 170, 20);
		txtCpf.setText(admin.getCpf());
		contentPane.add(txtCpf);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setForeground(new Color(255, 255, 255));
		lblEmail.setBounds(25, 138, 424, 14);
		contentPane.add(lblEmail);
		
		JLabel lblCpf = new JLabel("CPF: ");
		lblCpf.setForeground(new Color(255, 255, 255));
		lblCpf.setBounds(25, 193, 75, 14);
		contentPane.add(lblCpf);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setForeground(new Color(0, 0, 0));
		btnSalvar.setBounds(31, 260, 111, 23);
		btnSalvar.addActionListener(this);
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(btnSalvar);
				
		btnLimpar = new JButton("Limpar");
		btnLimpar.setForeground(new Color(0, 0, 0));
		btnLimpar.setBounds(152, 260, 111, 23);
		btnLimpar.addActionListener(this);
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(btnLimpar);
		
		passSenha = new JPasswordField();
		passSenha.setBounds(205, 218, 189, 20);
		contentPane.add(passSenha);
		
		listModel = new DefaultListModel<>();
		scrllPnLista = new JScrollPane();
		scrllPnLista.setToolTipText("N\u00E3o \u00E9 necess\u00E1rio selecionar uma empresa caso n\u00E3o queria alterar.");
		ArrayList <Empresa> empresas = EpontoFachada.getInstance().getEmpresas(); 
		for (int i = 0; i < empresas.size(); i++){
			listModel.addElement(empresas.get(i));
		}
		scrllPnLista.setBounds(441, 88, 148, 195);
		contentPane.add(scrllPnLista);		
		
		JLabel lblEmpresas = new JLabel("Empresas:");
		lblEmpresas.setForeground(new Color(255, 255, 255));
		lblEmpresas.setBounds(484, 63, 75, 14);
		contentPane.add(lblEmpresas);
		jListaEmpresas = new JList<Empresa>(listModel);
		contentPane.add(jListaEmpresas);
		jListaEmpresas.setToolTipText("N\u00E3o \u00E9 necess\u00E1rio selecionar uma empresa caso n\u00E3o queria alterar.");
		jListaEmpresas.setBounds(441, 88, 148, 195);
		
		btnNewButton = new JButton("Cancelar");
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBounds(273, 260, 111, 23);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(btnNewButton);
		
		lblEditarAdministrador = new JLabel("EDITAR ADMINISTRADOR");
		lblEditarAdministrador.setForeground(new Color(255, 255, 255));
		lblEditarAdministrador.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEditarAdministrador.setBounds(100, 37, 215, 14);
		contentPane.add(lblEditarAdministrador);
		
	}
	
	private void limparCampos(){
		txtNome.setText("");
		passSenha.setText("");
		txtEmail.setText("");
		txtCpf.setText("");		
	
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnLimpar)){
			this.limparCampos();
		}
		else if(e.getSource().equals(btnSalvar)){
			if(jListaEmpresas.getSelectedIndices().length==0){
				JOptionPane.showMessageDialog(null, "Por favor, selecione pelo menos uma Empresa.");
			}else{
				Admin adminNew = null;
				String nome = txtNome.getText();
				String cpf = txtCpf.getText();
				String email = txtEmail.getText();
				char[] senha = passSenha.getPassword();
				
				adminNew =new Admin (nome , cpf , email, senha);
				
				if(jListaEmpresas.getSelectedIndices().length==0)
					adminNew.adicionarEmpresas(admin.getEmpresas());
				else{
					List<Empresa>empresas = jListaEmpresas.getSelectedValuesList();
					adminNew.adicionarEmpresas(empresas);
				}
				try {
					EpontoFachada.getInstance().editarPessoa(EpontoFachada.getInstance().getIndiceCpf(admin.getCpf()),(adminNew));
				} catch (FuncionarioNaoEncontradoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Dados atualizados com suscesso!");
				this.limparCampos();
				}
			}
		}
}

