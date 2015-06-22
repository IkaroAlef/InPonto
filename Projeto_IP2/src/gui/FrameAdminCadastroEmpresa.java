package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import negócio.EpontoFachada;
import negócio.entity_beans.Empresa;
import negócio.entity_beans.exceptionsBeans.CNPJInvalidoException;
import negócio.entity_beans.exceptionsBeans.NomeInvalidoException;

public class FrameAdminCadastroEmpresa extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtNomeEmpresa;
	private JTextField txtCnpj;
	private JTextField txtEndereco;
	private JTextField txtContato;
	private JButton btnLimpar;
	private JButton btnSalvar;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameAdminCadastroEmpresa frame = new FrameAdminCadastroEmpresa();
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
	public FrameAdminCadastroEmpresa() {
		setTitle("Cadastrar Empresa");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 405, 222);
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
		
		JLabel lblCnpj = new JLabel("CNPJ:");
		lblCnpj.setBounds(117, 104, 63, 14);
		contentPane.add(lblCnpj);
		
		txtCnpj = new JTextField();
		txtCnpj.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent j) {
				String caracteres="0987654321";
				if(!caracteres.contains(j.getKeyChar()+"")){
				j.consume();
				}
			}
		});
		txtCnpj.setColumns(10);
		txtCnpj.setBounds(117, 117, 262, 20);
		contentPane.add(txtCnpj);
		
		txtEndereco = new JTextField();
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(10, 73, 369, 20);
		contentPane.add(txtEndereco);
		
		txtContato = new JTextField();
		txtContato.setColumns(10);
		txtContato.setBounds(10, 117, 97, 20);
		contentPane.add(txtContato);
		
		JLabel lblEndereco = new JLabel("Endere\u00E7o:");
		lblEndereco.setBounds(10, 60, 424, 14);
		contentPane.add(lblEndereco);
		
		JLabel lblContato = new JLabel("Contato:");
		lblContato.setBounds(10, 104, 75, 14);
		contentPane.add(lblContato);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(117, 148, 89, 23);
		btnSalvar.addActionListener(this);
		contentPane.add(btnSalvar);
				
		btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(216, 148, 89, 23);
		btnLimpar.addActionListener(this);
		contentPane.add(btnLimpar);
	}
	
	private void limparCampos(){
		txtNomeEmpresa.setText("");
		txtCnpj.setText("");
		txtEndereco.setText("");
		txtContato.setText("");		
	
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnLimpar)){
			this.limparCampos();
		}
		else if(e.getSource().equals(btnSalvar)){
			Empresa empresa = null;
			String nomeEmpresa = txtNomeEmpresa.getText();
			String cnpj = txtCnpj.getText();
			String endereco = txtEndereco.getText();
			String contato = txtContato.getText();
			
			try{
				empresa =new Empresa (nomeEmpresa , cnpj , endereco, contato);
				}catch(NomeInvalidoException e1){
					JOptionPane.showMessageDialog(null, e1.getMessage() );
				}catch(CNPJInvalidoException e2){
					JOptionPane.showMessageDialog(null, e2.getMessage() );
				}
			
			EpontoFachada.getInstance().adicionarEmpresa(empresa);
			JOptionPane.showMessageDialog(null, "Empresa cadastrada com suscesso" );
			this.limparCampos();
				}
			}
		}

