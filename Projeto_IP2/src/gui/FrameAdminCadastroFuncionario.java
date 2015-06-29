package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.JButton;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;

import dados.exceptionsDados.EmpresaNaoEncontradaException;
import negócio.EpontoFachada;
import negócio.entity_beans.Empresa;
import negócio.entity_beans.Funcionario;
import negócio.entity_beans.exceptionsBeans.CNPJInvalidoException;
import negócio.entity_beans.exceptionsBeans.NomeInvalidoException;

public class FrameAdminCadastroFuncionario extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtCPF;
	private JTextField txtSenha;
	private JTextField txtEmail;
	private JTextField txtTelefone;
	private JTextField txtCargo;
	private JTextField txtHoraChegada;
	private JTextField txtMinutosChegada;
	private JTextField txtHoraSaida;
	private JTextField txtMinutosSaida;
	private JTextField txtHoraSaidaIntervalo;
	private JTextField txtMinutosSaidaIntervalo;
	private JTextField txtHoraChegadaIntervalo;
	private JTextField txtMinutosChegadaIntervalo;
	private JComboBox<Empresa> cmbBxEmpresa;
	private JComboBox <String> cmbBxEscala;
	private JButton btnLimpar;
	private JButton btnSalvar;
	
	//webcam
	private Dimension ds = new Dimension(240,240);
	private Webcam wCam = Webcam.getDefault();
	private WebcamPanel wCamPanel = new WebcamPanel(wCam);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameAdminCadastroFuncionario frame = new FrameAdminCadastroFuncionario();
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
	public FrameAdminCadastroFuncionario() {
		setTitle("Cadastrar Funcion\u00E1rio");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 814, 366);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 11, 46, 14);
		contentPane.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(10, 25, 490, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("CPF:");
		lblNewLabel.setBounds(10, 61, 46, 14);
		contentPane.add(lblNewLabel);
		
		txtCPF = new JTextField();
		txtCPF.setColumns(10);
		txtCPF.setBounds(10, 76, 220, 20);
		contentPane.add(txtCPF);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(257, 61, 46, 14);
		contentPane.add(lblSenha);
		
		txtSenha = new JTextField();
		txtSenha.setColumns(10);
		txtSenha.setBounds(256, 76, 244, 20);
		contentPane.add(txtSenha);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(10, 107, 46, 14);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(10, 122, 490, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblEmpresa = new JLabel("Empresa:");
		lblEmpresa.setBounds(10, 153, 72, 14);
		contentPane.add(lblEmpresa);
		
		cmbBxEmpresa = new JComboBox<Empresa>();
		cmbBxEmpresa.setBounds(10, 167, 116, 20);
		contentPane.add(cmbBxEmpresa);
		for(int i=0;i<EpontoFachada.getInstance().getSizeEmpresas() ; i++){
			cmbBxEmpresa.addItem(EpontoFachada.getInstance().getEmpresas(null).get(i));
		}
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(152, 153, 78, 14);
		contentPane.add(lblTelefone);
		
		txtTelefone = new JTextField();
		txtTelefone.setBounds(152, 167, 151, 20);
		contentPane.add(txtTelefone);
		txtTelefone.setColumns(10);
		
		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setBounds(322, 153, 58, 14);
		contentPane.add(lblCargo);
		
		txtCargo = new JTextField();
		txtCargo.setBounds(321, 167, 155, 20);
		contentPane.add(txtCargo);
		txtCargo.setColumns(10);
		
		JLabel lblEscala = new JLabel("Escala");
		lblEscala.setBounds(10, 198, 46, 14);
		contentPane.add(lblEscala);
		
		cmbBxEscala = new JComboBox <String>();
		cmbBxEscala.setBounds(10, 215, 116, 20);
		contentPane.add(cmbBxEscala);
		cmbBxEscala.addItem("Seg. à Sex");
		cmbBxEscala.addItem("Dia sim Dia não");
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 246, 470, 2);
		contentPane.add(separator);
		
		JLabel lblHorrioChegada = new JLabel("Hor\u00E1rio Chegada:");
		lblHorrioChegada.setBounds(10, 259, 104, 14);
		contentPane.add(lblHorrioChegada);
		
		txtHoraChegada = new JTextField();
		txtHoraChegada.setBounds(10, 279, 30, 20);
		contentPane.add(txtHoraChegada);
		txtHoraChegada.setColumns(10);
		
		txtMinutosChegada = new JTextField();
		txtMinutosChegada.setColumns(10);
		txtMinutosChegada.setBounds(52, 279, 30, 20);
		contentPane.add(txtMinutosChegada);
		
		JLabel lbl2ptChegada = new JLabel(":");
		lbl2ptChegada.setBounds(45, 283, 10, 10);
		contentPane.add(lbl2ptChegada);
		
		JLabel lblHorarioSaida = new JLabel("Hor\u00E1rio Sa\u00EDda:");
		lblHorarioSaida.setBounds(120, 259, 88, 14);
		contentPane.add(lblHorarioSaida);
		
		txtHoraSaida = new JTextField();
		txtHoraSaida.setColumns(10);
		txtHoraSaida.setBounds(120, 279, 30, 20);
		contentPane.add(txtHoraSaida);
		
		JLabel lbl2ptSaida = new JLabel(":");
		lbl2ptSaida.setBounds(155, 283, 10, 10);
		contentPane.add(lbl2ptSaida);
		
		txtMinutosSaida = new JTextField();
		txtMinutosSaida.setColumns(10);
		txtMinutosSaida.setBounds(162, 279, 30, 20);
		contentPane.add(txtMinutosSaida);
		
		JLabel lblHorarioSaidaIntervalo = new JLabel("Hor\u00E1rio Sa\u00EDda Intervalo:");
		lblHorarioSaidaIntervalo.setBounds(218, 259, 137, 14);
		contentPane.add(lblHorarioSaidaIntervalo);
		
		txtHoraSaidaIntervalo = new JTextField();
		txtHoraSaidaIntervalo.setColumns(10);
		txtHoraSaidaIntervalo.setBounds(247, 279, 30, 20);
		contentPane.add(txtHoraSaidaIntervalo);
		
		JLabel lbl2ptSaidaIntervalo = new JLabel(":");
		lbl2ptSaidaIntervalo.setBounds(282, 283, 10, 10);
		contentPane.add(lbl2ptSaidaIntervalo);
		
		txtMinutosSaidaIntervalo = new JTextField();
		txtMinutosSaidaIntervalo.setColumns(10);
		txtMinutosSaidaIntervalo.setBounds(289, 279, 30, 20);
		contentPane.add(txtMinutosSaidaIntervalo);
		
		JLabel lblHorrioChegadaIntervalo = new JLabel("Hor\u00E1rio Chegada Intervalo:");
		lblHorrioChegadaIntervalo.setBounds(355, 259, 150, 14);
		contentPane.add(lblHorrioChegadaIntervalo);
		
		txtHoraChegadaIntervalo = new JTextField();
		txtHoraChegadaIntervalo.setColumns(10);
		txtHoraChegadaIntervalo.setBounds(384, 279, 30, 20);
		contentPane.add(txtHoraChegadaIntervalo);
		
		JLabel lbl2ptChegadaIntervalo = new JLabel(":");
		lbl2ptChegadaIntervalo.setBounds(419, 283, 10, 10);
		contentPane.add(lbl2ptChegadaIntervalo);
		
		txtMinutosChegadaIntervalo = new JTextField();
		txtMinutosChegadaIntervalo.setColumns(10);
		txtMinutosChegadaIntervalo.setBounds(426, 279, 30, 20);
		contentPane.add(txtMinutosChegadaIntervalo);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(552, 283, 89, 23);
		btnSalvar.addActionListener(this);
		contentPane.add(btnSalvar);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(681, 283, 89, 23);
		btnLimpar.addActionListener(this);
		contentPane.add(btnLimpar);
		
		//webcam
		wCamPanel.setBounds(548, 8, 176, 144);
		wCamPanel.setSize(ds);
		contentPane.add(wCamPanel);
		wCamPanel.setFPSDisplayed(true);
	}
	
	private void limparCampos(){
		txtNome.setText("");
		txtCPF.setText("");
		txtEmail.setText("");
		txtTelefone.setText("");
		txtSenha.setText("");
		txtCargo.setText("");
		txtHoraChegada.setText("");
		txtMinutosChegada.setText("");
		txtHoraSaida.setText("");
		txtMinutosSaida.setText("");
		txtHoraSaidaIntervalo.setText("");
		txtMinutosSaidaIntervalo.setText("");
		txtHoraChegadaIntervalo.setText("");
		txtMinutosChegadaIntervalo.setText("");			
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnLimpar)){
			this.limparCampos();
		}
		else if(e.getSource().equals(btnSalvar)){
			/*
			try{
				Empresa empresa = new Empresa ("UFRPE","123","123","1312");
			}catch(NomeInvalidoException excp1){
				JOptionPane.showMessageDialog(null, excp1.getMessage() );
			}catch(CNPJInvalidoException excp2){
				JOptionPane.showMessageDialog(null, excp2.getMessage() );
			}
			*/
			Funcionario funcionario = null;
			String nome = txtNome.getText();
			String cpf = txtCPF.getText();
			String email = txtEmail.getText();
			char[] senha = txtSenha.getText().toCharArray();
			String telefone = txtTelefone.getText();
			String cargo = txtCargo.getText();
			Empresa empresa = null;
			try {
				empresa = (Empresa) EpontoFachada.getInstance().buscaEmpresaNome(cmbBxEmpresa.getSelectedItem().toString());
			} catch (EmpresaNaoEncontradaException e3) {
				JOptionPane.showMessageDialog(null, e3.getMessage());
			}
			LocalTime horaChegada = LocalTime.of( Integer.parseInt(txtHoraChegada.getText()) , Integer.parseInt( txtMinutosChegada.getText()) );
			LocalTime horaSaida = LocalTime.of( Integer.parseInt(txtHoraSaida.getText()) , Integer.parseInt( txtMinutosSaida.getText()) );
			LocalTime horaChegadaIntervalo = LocalTime.of( Integer.parseInt(txtHoraChegadaIntervalo.getText()) , Integer.parseInt( txtMinutosChegadaIntervalo.getText()) );
			LocalTime horaSaidaIntervalo = LocalTime.of( Integer.parseInt(txtHoraSaidaIntervalo.getText()) , Integer.parseInt( txtMinutosSaidaIntervalo.getText()) );
			try{
				funcionario = new Funcionario(nome, cpf, email, senha, telefone, empresa, cargo, "Seg. à Sex", horaChegada, horaSaida, horaChegadaIntervalo, horaSaidaIntervalo);
				File file= new File (String.format("Imagem %s.jpg", funcionario.getNome()));
				ImageIO.write(wCam.getImage(), "JPG", file);
			}catch(NomeInvalidoException e1){
				JOptionPane.showMessageDialog(null, e1.getMessage() );
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Foto não salva, usuario nao cadastrado");
				JOptionPane.showMessageDialog(null, e2.getMessage());
			}
			EpontoFachada.getInstance().adicionarPessoa(funcionario);
			JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso" );
			this.limparCampos();
		}
	}
			
}
