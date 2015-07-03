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
		setBounds(100, 100, 527, 220);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.admin=admin;
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 11, 135, 14);
		contentPane.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(10, 25, 369, 20);
		txtNome.setColumns(10);
		txtNome.setText(admin.getNome());
		contentPane.add(txtNome);
		
		JLabel lblSenha = new JLabel("Senha: ");
		lblSenha.setBounds(190, 104, 63, 14);
		contentPane.add(lblSenha);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(10, 73, 369, 20);
		txtEmail.setText(admin.getEmail());
		contentPane.add(txtEmail);
		
		txtCpf = new JTextField();
		txtCpf.setColumns(10);
		txtCpf.setBounds(10, 117, 170, 20);
		txtCpf.setText(admin.getCpf());
		contentPane.add(txtCpf);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(10, 60, 424, 14);
		contentPane.add(lblEmail);
		
		JLabel lblCpf = new JLabel("CPF: ");
		lblCpf.setBounds(10, 104, 75, 14);
		contentPane.add(lblCpf);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(320, 157, 89, 23);
		btnSalvar.addActionListener(this);
		contentPane.add(btnSalvar);
				
		btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(419, 157, 89, 23);
		btnLimpar.addActionListener(this);
		contentPane.add(btnLimpar);
		
		passSenha = new JPasswordField();
		passSenha.setBounds(190, 117, 189, 20);
		contentPane.add(passSenha);
		
		listModel = new DefaultListModel<>();
		scrllPnLista = new JScrollPane();
		scrllPnLista.setToolTipText("N\u00E3o \u00E9 necess\u00E1rio selecionar uma empresa caso n\u00E3o queria alterar.");
		ArrayList <Empresa> empresas = EpontoFachada.getInstance().getEmpresas(); 
		for (int i = 0; i < empresas.size(); i++){
			listModel.addElement(empresas.get(i));
		}
		jListaEmpresas = new JList<Empresa>(listModel);
		jListaEmpresas.setToolTipText("N\u00E3o \u00E9 necess\u00E1rio selecionar uma empresa caso n\u00E3o queria alterar.");
		jListaEmpresas.setBounds(10, 163, 98, 98);
		
		scrllPnLista.setViewportView(jListaEmpresas);
		scrllPnLista.setBounds(410, 30, 98, 105);
		contentPane.add(scrllPnLista);		
		
		JLabel lblEmpresas = new JLabel("Empresas:");
		lblEmpresas.setBounds(410, 11, 75, 14);
		contentPane.add(lblEmpresas);
		
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

