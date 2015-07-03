package gui;

import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;


import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import dados.RepPessoas;
import dados.exceptionsDados.FuncionarioNaoEncontradoException;
import negócio.EpontoFachada;
import negócio.entity_beans.Admin;
import negócio.entity_beans.Pessoa;

public class FrameLogin extends JFrame implements ActionListener, KeyListener {
	
	private JPanel contentPane;
	private JButton btnOk;
	private JTextField txtLogin;
	private JPasswordField passSenha;
	
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
		
		setResizable(false);
		setTitle("Login Eponto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 250, 134);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("CPF:");
		lblLogin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLogin.setBounds(20, 11, 33, 14);
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
		passSenha.addKeyListener(this);
		contentPane.add(passSenha);
		
		this.txtLogin.setText("");
		this.passSenha.setText("");
		
		btnOk = new JButton("OK");
		btnOk.setBounds(76, 71, 89, 23);
		contentPane.add(btnOk);
		btnOk.addActionListener(this);
		btnOk.addKeyListener(this);

	}
	
	public void validarLogin(){
		try {
			if (EpontoFachada.getInstance().validarLogin(txtLogin.getText(), passSenha.getPassword() ) ){
				JOptionPane.showMessageDialog(null, "Login efetuado com sucesso.");
				this.setVisible(false);
				ControladorDeTelas.getInstance().loginProximaTela(EpontoFachada.getInstance().getPessoaCpf(txtLogin.getText()));
			}
			else {
				passSenha.requestFocusInWindow();
				JOptionPane.showMessageDialog(null, "Senha incorreta.");
				passSenha.setText("");
			}
		} catch (HeadlessException e1) {
			System.out.println("Exception");
			e1.printStackTrace();
		} catch (FuncionarioNaoEncontradoException e1) {
			JOptionPane.showMessageDialog(null, "Usuário não encontrado.");
			txtLogin.requestFocus();
			txtLogin.setText("");
			passSenha.setText("");
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnOk)){
			this.validarLogin();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER){
			this.validarLogin();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
	
}
