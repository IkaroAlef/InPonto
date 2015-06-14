package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.FlowLayout;

import javax.swing.JTextField;
import javax.swing.BoxLayout;

import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import dados.exceptionsDados.FuncionarioNaoEncontradoException;
import negócio.ControladorPessoas;

public class FrameLogin extends JFrame implements ActionListener {
	
	private JPanel contentPane;
	private JButton btnOk;
	private JTextField txtLogin;
	private JPasswordField passSenha;
	
	private ControladorPessoas controladorPessoas ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameLogin frame = new FrameLogin();
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
	public FrameLogin() {
		
		this.controladorPessoas = new ControladorPessoas();
		
		
		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 250, 134);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setHorizontalAlignment(SwingConstants.LEFT);
		lblLogin.setBounds(20, 11, 56, 14);
		contentPane.add(lblLogin);
		
		txtLogin = new JTextField();
		txtLogin.setHorizontalAlignment(SwingConstants.LEFT);
		txtLogin.setBounds(76, 8, 150, 20);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setHorizontalAlignment(SwingConstants.LEFT);
		lblSenha.setBounds(20, 42, 56, 14);
		contentPane.add(lblSenha);
		
		passSenha = new JPasswordField();
		passSenha.setHorizontalAlignment(SwingConstants.LEFT);
		passSenha.setBounds(76, 39, 150, 20);
		contentPane.add(passSenha);
		
		btnOk = new JButton("OK");
		btnOk.setBounds(76, 71, 89, 23);
		contentPane.add(btnOk);
		btnOk.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnOk)){
			try {
				if ( controladorPessoas.validarLogin( txtLogin.getText(), passSenha.getPassword() ) ){
					JOptionPane.showMessageDialog(null, "Login Efetuado com Sucesso.");
				}
				else 
					JOptionPane.showMessageDialog(null, "Senha incorreta.");
			} catch (HeadlessException e1) {
				System.out.println("Exception");
				e1.printStackTrace();
			} catch (FuncionarioNaoEncontradoException e1) {
				JOptionPane.showMessageDialog(null, "Usuário Não Encontrado.");
			}
			txtLogin.setText("");
			passSenha.setText("");
		}
	}
}
