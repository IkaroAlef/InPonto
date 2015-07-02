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

public class FrameAdminCadastroAdmin extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtNomeEmpresa;
	private JTextField txtEmail;
	private JTextField txtCpf;
	private JButton btnLimpar;
	private JButton btnSalvar;
	private JPasswordField passSenha;
	private JScrollPane scrllPnLista = new JScrollPane();
	private JList<Empresa> jListaEmpresas;
	private DefaultListModel<Empresa> listModel;
	
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
		setBounds(100, 100, 527, 220);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Nome:");
		label.setBounds(10, 11, 424, 14);
		contentPane.add(label);
		
		txtNomeEmpresa = new JTextField();
		txtNomeEmpresa.setBounds(10, 25, 369, 20);
		txtNomeEmpresa.setColumns(10);
		contentPane.add(txtNomeEmpresa);
		
		JLabel lblSenha = new JLabel("Senha: ");
		lblSenha.setBounds(190, 104, 63, 14);
		contentPane.add(lblSenha);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(10, 73, 369, 20);
		contentPane.add(txtEmail);
		
		txtCpf = new JTextField();
		txtCpf.setColumns(10);
		txtCpf.setBounds(10, 117, 170, 20);
		contentPane.add(txtCpf);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(10, 60, 424, 14);
		contentPane.add(lblEmail);
		
		JLabel lblContato = new JLabel("CPF: ");
		lblContato.setBounds(10, 104, 75, 14);
		contentPane.add(lblContato);
		
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
		ArrayList <Empresa> empresas = EpontoFachada.getInstance().getEmpresas(); 
		for (int i = 0; i < empresas.size(); i++){
			listModel.addElement(empresas.get(i));
		}
		jListaEmpresas = new JList<Empresa>(listModel);
		jListaEmpresas.setBounds(10, 163, 98, 98);
		
		scrllPnLista.setViewportView(jListaEmpresas);
		scrllPnLista.setBounds(410, 30, 98, 105);
		contentPane.add(scrllPnLista);		
		
		JLabel lblEmpresas = new JLabel("Empresas:");
		lblEmpresas.setBounds(410, 11, 75, 14);
		contentPane.add(lblEmpresas);
		
	}
	
	private void limparCampos(){
		txtNomeEmpresa.setText("");
		passSenha.setText("");
		txtEmail.setText("");
		txtCpf.setText("");		
	
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnLimpar)){
			this.limparCampos();
		}
		else if(e.getSource().equals(btnSalvar)){
			Admin admin = null;
			String nome = txtNomeEmpresa.getText();
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

