 package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.JButton;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

import dados.exceptionsDados.EmpresaNaoEncontradaException;
import dados.exceptionsDados.FuncionarioNaoEncontradoException;
import negócio.EpontoFachada;
import negócio.entity_beans.Empresa;
import negócio.entity_beans.Funcionario;
import negócio.entity_beans.exceptionsBeans.CNPJInvalidoException;
import negócio.entity_beans.exceptionsBeans.NomeInvalidoException;

import java.awt.Color;

public class FrameAdminEditarFuncionario extends JFrame implements ActionListener, WindowListener, ItemListener {

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
	private JCheckBox chkbxAlterarFoto;
	private Funcionario funcionario;
	
	//webcam
	private Webcam wCam;
	private WebcamPanel wCamPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameAdminEditarFuncionario frame = new FrameAdminEditarFuncionario((Funcionario) EpontoFachada.getInstance().getPessoaNome("Ikaro Alef"));
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
	public FrameAdminEditarFuncionario(Funcionario funcionario) {
		super("Editar Funcionario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 814, 366);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(169, 169, 169));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		addWindowListener(this);
		
		this.funcionario=funcionario;
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setForeground(new Color(255, 255, 255));
		lblNome.setBounds(10, 11, 46, 14);
		contentPane.add(lblNome);
		
		txtNome = new JTextField(funcionario.getNome());
		txtNome.setBounds(10, 25, 490, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("CPF:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(10, 61, 46, 14);
		contentPane.add(lblNewLabel);
		
		txtCPF = new JTextField(funcionario.getCpf());
		txtCPF.setColumns(10);
		txtCPF.setBounds(10, 76, 220, 20);
		contentPane.add(txtCPF);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(257, 61, 46, 14);
		lblSenha.setForeground(new Color(255, 255, 255));
		contentPane.add(lblSenha);
		
		txtSenha = new JTextField();
		txtSenha.setColumns(10);
		txtSenha.setBounds(256, 76, 244, 20);
		txtSenha.addActionListener(this);
		contentPane.add(txtSenha);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setForeground(new Color(255, 255, 255));
		lblEmail.setBounds(10, 107, 46, 14);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField(funcionario.getEmail());
		txtEmail.setBounds(10, 122, 490, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblEmpresa = new JLabel("Empresa:");
		lblEmpresa.setForeground(new Color(255, 255, 255));
		lblEmpresa.setBounds(10, 153, 72, 14);
		contentPane.add(lblEmpresa);
		
		cmbBxEmpresa = new JComboBox<Empresa>();
		cmbBxEmpresa.setBounds(10, 167, 116, 20);
		contentPane.add(cmbBxEmpresa);
		for(int i=0;i<EpontoFachada.getInstance().getSizeEmpresas() ; i++){
			cmbBxEmpresa.addItem(EpontoFachada.getInstance().getEmpresas(null).get(i));
		}
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setForeground(new Color(255, 255, 255));
		lblTelefone.setBounds(152, 153, 78, 14);
		contentPane.add(lblTelefone);
		
		txtTelefone = new JTextField(funcionario.getTelefone());
		txtTelefone.setBounds(152, 167, 151, 20);
		contentPane.add(txtTelefone);
		txtTelefone.setColumns(10);
		
		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setForeground(new Color(255, 255, 255));
		lblCargo.setBounds(322, 153, 58, 14);
		contentPane.add(lblCargo);
		
		txtCargo = new JTextField(funcionario.getCargo());
		txtCargo.setBounds(321, 167, 179, 20);
		contentPane.add(txtCargo);
		txtCargo.setColumns(10);
		
		JLabel lblEscala = new JLabel("Escala");
		lblEscala.setForeground(new Color(255, 255, 255));
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
		lblHorrioChegada.setForeground(new Color(255, 255, 255));
		lblHorrioChegada.setBounds(10, 259, 104, 14);
		contentPane.add(lblHorrioChegada);
		
		txtHoraChegada = new JTextField(String.valueOf(funcionario.getChegada().getHour()));
		txtHoraChegada.setBounds(10, 279, 30, 20);
		contentPane.add(txtHoraChegada);
		txtHoraChegada.setColumns(10);
		
		txtMinutosChegada = new JTextField(String.valueOf(funcionario.getChegada().getMinute()));
		txtMinutosChegada.setColumns(10);
		txtMinutosChegada.setBounds(52, 279, 30, 20);
		contentPane.add(txtMinutosChegada);
		
		JLabel lbl2ptChegada = new JLabel(":");
		lbl2ptChegada.setBounds(45, 283, 10, 10);
		contentPane.add(lbl2ptChegada);
		
		JLabel lblHorarioSaida = new JLabel("Hor\u00E1rio Sa\u00EDda:");
		lblHorarioSaida.setForeground(new Color(255, 255, 255));
		lblHorarioSaida.setBounds(120, 259, 88, 14);
		contentPane.add(lblHorarioSaida);
		
		txtHoraSaida = new JTextField(String.valueOf(funcionario.getSaida().getHour()));
		txtHoraSaida.setColumns(10);
		txtHoraSaida.setBounds(120, 279, 30, 20);
		contentPane.add(txtHoraSaida);
		
		JLabel lbl2ptSaida = new JLabel(":");
		lbl2ptSaida.setBounds(155, 283, 10, 10);
		contentPane.add(lbl2ptSaida);
		
		txtMinutosSaida = new JTextField(String.valueOf(funcionario.getSaida().getMinute()));
		txtMinutosSaida.setColumns(10);
		txtMinutosSaida.setBounds(162, 279, 30, 20);
		contentPane.add(txtMinutosSaida);
		
		JLabel lblHorarioSaidaIntervalo = new JLabel("Hor\u00E1rio Sa\u00EDda Intervalo:");
		lblHorarioSaidaIntervalo.setForeground(new Color(255, 255, 255));
		lblHorarioSaidaIntervalo.setBounds(218, 259, 137, 14);
		contentPane.add(lblHorarioSaidaIntervalo);
		
		txtHoraSaidaIntervalo = new JTextField(String.valueOf(funcionario.getIntervalo_out().getHour()));
		txtHoraSaidaIntervalo.setColumns(10);
		txtHoraSaidaIntervalo.setBounds(247, 279, 30, 20);
		contentPane.add(txtHoraSaidaIntervalo);
		
		JLabel lbl2ptSaidaIntervalo = new JLabel(":");
		lbl2ptSaidaIntervalo.setBounds(282, 283, 10, 10);
		contentPane.add(lbl2ptSaidaIntervalo);
		
		txtMinutosSaidaIntervalo = new JTextField(String.valueOf(funcionario.getIntervalo_out().getMinute()));
		txtMinutosSaidaIntervalo.setColumns(10);
		txtMinutosSaidaIntervalo.setBounds(289, 279, 30, 20);
		contentPane.add(txtMinutosSaidaIntervalo);
		
		JLabel lblHorrioChegadaIntervalo = new JLabel("Hor\u00E1rio Chegada Intervalo:");
		lblHorrioChegadaIntervalo.setForeground(new Color(255, 255, 255));
		lblHorrioChegadaIntervalo.setBounds(355, 259, 150, 14);
		contentPane.add(lblHorrioChegadaIntervalo);
		
		txtHoraChegadaIntervalo = new JTextField(String.valueOf(funcionario.getIntervalo_in().getHour()));
		txtHoraChegadaIntervalo.setColumns(10);
		txtHoraChegadaIntervalo.setBounds(384, 279, 30, 20);
		contentPane.add(txtHoraChegadaIntervalo);
		
		JLabel lbl2ptChegadaIntervalo = new JLabel(":");
		lbl2ptChegadaIntervalo.setBounds(419, 283, 10, 10);
		contentPane.add(lbl2ptChegadaIntervalo);
		
		txtMinutosChegadaIntervalo = new JTextField(String.valueOf(funcionario.getIntervalo_in().getMinute()));
		txtMinutosChegadaIntervalo.setColumns(10);
		txtMinutosChegadaIntervalo.setBounds(426, 279, 30, 20);
		contentPane.add(txtMinutosChegadaIntervalo);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setForeground(new Color(128, 128, 128));
		btnSalvar.setBounds(581, 283, 72, 23);
		btnSalvar.setForeground(new Color(0, 0, 0));
		btnSalvar.addActionListener(this);
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(btnSalvar);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.setForeground(new Color(128, 128, 128));
		btnLimpar.setBounds(663, 283, 75, 23);
		btnLimpar.setForeground(new Color(0, 0, 0));
		btnLimpar.addActionListener(this);
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(btnLimpar);
		
		chkbxAlterarFoto = new JCheckBox("Alterar Foto", false);
		chkbxAlterarFoto.setBackground(new Color(169, 169, 169));
		chkbxAlterarFoto.setSize(100, 20);
		chkbxAlterarFoto.setLocation(620, 246);
		chkbxAlterarFoto.addItemListener(this);
		chkbxAlterarFoto.setForeground(new Color(255, 255, 255));
		contentPane.add(chkbxAlterarFoto);
		
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
			if(txtSenha.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Por favor, digite a senha novamente ou insira uma nova.");
				txtSenha.requestFocus();
			}else{
			Funcionario funcionarioNew = null;
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
				if(chkbxAlterarFoto.isSelected()){
				funcionarioNew = new Funcionario(nome, cpf, email, senha, telefone, empresa, cargo, "Seg. à Sex", horaChegada, horaSaida, horaChegadaIntervalo, horaSaidaIntervalo);
				funcionarioNew.setFotoPadrao(wCam.getImage());
				}
				else{
					funcionarioNew = new Funcionario(nome, cpf, email, senha, telefone, empresa, cargo, "Seg. à Sex", horaChegada, horaSaida, horaChegadaIntervalo, horaSaidaIntervalo);
					funcionarioNew.setFotoPadrao(funcionario.getFotoPadrao());
			}
			}catch(NomeInvalidoException e1){
				JOptionPane.showMessageDialog(null, e1.getMessage() );
			}
			try {
				EpontoFachada.getInstance().editarPessoa(EpontoFachada.getInstance().getIndiceCpf(funcionario.getCpf()),funcionarioNew);
			} catch (FuncionarioNaoEncontradoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Dados atualizados com sucesso!" );
			this.limparCampos();
		}
		}
	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowClosed(WindowEvent e) {
		if(chkbxAlterarFoto.isSelected())
			wCam.close();
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
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource().equals(chkbxAlterarFoto)){
			if(chkbxAlterarFoto.isSelected()){
				wCam = Webcam.getDefault();
				wCam.setViewSize(WebcamResolution.VGA.getSize());
				wCamPanel = new WebcamPanel(wCam);
				wCamPanel.setBounds(520, 8, 260, 240);
				contentPane.add(wCamPanel);
			}else{
				wCam.close();
				wCamPanel.setVisible(false);
			}
		}
	}
			
}
