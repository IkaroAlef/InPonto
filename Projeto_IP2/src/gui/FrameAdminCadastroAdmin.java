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
import java.awt.Color;
import java.awt.Font;

public class FrameAdminCadastroAdmin extends JFrame implements ActionListener {

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
	private JLabel lblCadastroAdministrador;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameAdminCadastroAdmin frame = new FrameAdminCadastroAdmin();
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
	public FrameAdminCadastroAdmin() {
		setResizable(false);
		setTitle("Cadastrar Administrador");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 646, 398);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(105, 105, 105));
		contentPane.setBackground(new Color(169, 169, 169));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setForeground(new Color(255, 255, 255));
		lblNome.setBackground(new Color(211, 211, 211));
		lblNome.setBounds(62, 129, 135, 14);
		contentPane.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(100, 126, 340, 20);
		txtNome.setColumns(10);
		contentPane.add(txtNome);
		
		JLabel lblSenha = new JLabel("Senha: ");
		lblSenha.setForeground(new Color(255, 255, 255));
		lblSenha.setBounds(219, 188, 63, 14);
		contentPane.add(lblSenha);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(96, 157, 340, 20);
		contentPane.add(txtEmail);
		
		txtCpf = new JTextField();
		txtCpf.setColumns(10);
		txtCpf.setBounds(96, 185, 113, 20);
		contentPane.add(txtCpf);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setForeground(new Color(255, 255, 255));
		lblEmail.setBounds(62, 160, 424, 14);
		contentPane.add(lblEmail);
		
		JLabel lblCpf = new JLabel("CPF: ");
		lblCpf.setForeground(new Color(255, 255, 255));
		lblCpf.setBounds(62, 188, 75, 14);
		contentPane.add(lblCpf);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setForeground(new Color(0,0,0));
		btnSalvar.setBounds(96, 249, 89, 23);
		btnSalvar.addActionListener(this);
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(btnSalvar);
				
		btnLimpar = new JButton("Limpar");
		btnLimpar.setForeground(new Color(0,0,0));
		btnLimpar.setBounds(195, 249, 89, 23);
		btnLimpar.addActionListener(this);
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(btnLimpar);
		
		passSenha = new JPasswordField();
		passSenha.setBounds(260, 185, 176, 20);
		contentPane.add(passSenha);
		
		listModel = new DefaultListModel<>();
		scrllPnLista = new JScrollPane();
		ArrayList <Empresa> empresas = EpontoFachada.getInstance().getEmpresas(); 
		for (int i = 0; i < empresas.size(); i++){
			listModel.addElement(empresas.get(i));
		}
		scrllPnLista.setBounds(462, 96, 156, 202);
		contentPane.add(scrllPnLista);		
		
		JLabel lblEmpresas = new JLabel("Empresas:");
		lblEmpresas.setForeground(new Color(255, 255, 255));
		lblEmpresas.setBounds(513, 76, 75, 14);
		contentPane.add(lblEmpresas);
		jListaEmpresas = new JList<Empresa>(listModel);
		contentPane.add(jListaEmpresas);
		jListaEmpresas.setBounds(462, 103, 156, 195);
		
		lblCadastroAdministrador = new JLabel("CADASTRO ADMINISTRADOR");
		lblCadastroAdministrador.setForeground(new Color(255, 255, 255));
		lblCadastroAdministrador.setBackground(new Color(128, 128, 128));
		lblCadastroAdministrador.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCadastroAdministrador.setBounds(142, 60, 242, 14);
		contentPane.add(lblCadastroAdministrador);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(new Color(0,0,0));
		btnCancelar.setBounds(295, 249, 89, 23);
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(btnCancelar);
		
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
				Admin admin = null;
				String nome = txtNome.getText();
				String cpf = txtCpf.getText();
				String email = txtEmail.getText();
				char[] senha = passSenha.getPassword();
				
				admin =new Admin (nome , cpf , email, senha);
				List<Empresa>empresas = jListaEmpresas.getSelectedValuesList();
				admin.adicionarEmpresas(empresas);
				
				EpontoFachada.getInstance().adicionarPessoa(admin);
				JOptionPane.showMessageDialog(null, "Cadastro realizado com suscesso!");
				this.limparCampos();
				}
			}
		}
}

