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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import dados.exceptionsDados.FuncionarioNaoEncontradoException;
import negócio.EpontoFachada;
import java.awt.Font;
import java.awt.Color;

public class FrameLogin extends JFrame implements ActionListener, KeyListener{
	
	private JPanel contentPane;
	private JButton btnOk;
	private JTextField txtLogin;
	private JPasswordField passSenha;
	private JLabel lblInponto;
	private JButton btnNewButton;
	
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
		setTitle("Login inPonto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 379, 213);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(105, 105, 105));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("CPF:");
		lblLogin.setForeground(new Color(255, 255, 255));
		lblLogin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLogin.setBounds(71, 64, 33, 14);
		contentPane.add(lblLogin);
		
		txtLogin = new JTextField();
		txtLogin.setHorizontalAlignment(SwingConstants.LEFT);
		txtLogin.setBounds(114, 61, 166, 20);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setForeground(new Color(255, 255, 255));
		lblSenha.setHorizontalAlignment(SwingConstants.LEFT);
		lblSenha.setBounds(72, 95, 64, 14);
		contentPane.add(lblSenha);
		
		passSenha = new JPasswordField();
		passSenha.setHorizontalAlignment(SwingConstants.LEFT);
		passSenha.setBounds(114, 92, 166, 20);
		passSenha.addKeyListener(this);
		contentPane.add(passSenha);
		
		this.txtLogin.setText("");
		this.passSenha.setText("");
		
		btnOk = new JButton("OK");
		btnOk.setForeground(new Color(0, 0, 0));
		btnOk.setBounds(114, 133, 78, 23);
		contentPane.add(btnOk);
		btnOk.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		lblInponto = new JLabel("inPonto");
		lblInponto.setForeground(new Color(143, 188, 143));
		lblInponto.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 28));
		lblInponto.setBounds(136, 11, 107, 39);
		contentPane.add(lblInponto);
		
		btnNewButton = new JButton("Cancela");
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton.setBounds(202, 133, 78, 23);
		contentPane.add(btnNewButton);
		btnOk.addActionListener(this);
		btnOk.addKeyListener(this);

	}
	
	public void validarLogin(){
		try {
			if (EpontoFachada.getInstance().validarLogin(txtLogin.getText(), passSenha.getPassword() ) ){
				JOptionPane.showMessageDialog(null, "Login efetuado com sucesso.");
				this.setVisible(false);
				ControladorDeTelas.getInstance().loginProximaTela(EpontoFachada.getInstance().getPessoaCpf(txtLogin.getText()));
				txtLogin.setText("");
				passSenha.setText("");
			}
			else {
				passSenha.requestFocusInWindow();
				JOptionPane.showMessageDialog(null, "Senha incorreta.");
				passSenha.setText("");
			}
		} catch (HeadlessException e1) {
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
