package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

import conexaoBD.FabricaDeConexao;
import dados.exceptionsDados.EmpresaNaoEncontradaException;
import negócio.EpontoFachada;
import negócio.entity_beans.Empresa;
import negócio.entity_beans.Funcionario;
import negócio.entity_beans.exceptionsBeans.NomeInvalidoException;
import qrCode.GerarQRCode;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class FrameAdminCadastroFuncionario extends JFrame implements ActionListener, WindowListener {

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
	private Webcam wCam;
	private WebcamPanel wCamPanel;
	private JTextField txtMatricula;
	private JTextField txtRg;
	private JTextField txtEndereco;
	private JTextField txtNumero;
	private JTextField txtComplemento;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtEstado;
	private JTextField txtCep;
	private JTextField txtCtps;
	private JTextField txtPis;
	private JTextField txtCodEqp;
	private JComboBox<String> txtCpf_coord;
	private JTextField txtDt_admissao;
	private JSeparator separator_2;
	private JTextField txtCodDept;

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
		setBounds(100, 100, 810, 596);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(169, 169, 169));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		addWindowListener(this);
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setForeground(new Color(255, 255, 255));
		lblNome.setBackground(new Color(255, 255, 255));
		lblNome.setBounds(10, 11, 46, 14);
		contentPane.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(10, 25, 466, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("CPF:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(10, 61, 46, 14);
		contentPane.add(lblNewLabel);
		
		txtCPF = new JTextField();
		txtCPF.setColumns(10);
		txtCPF.setBounds(10, 76, 220, 20);
		contentPane.add(txtCPF);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setForeground(new Color(255, 255, 255));
		lblSenha.setBounds(257, 61, 46, 14);
		contentPane.add(lblSenha);
		
		txtSenha = new JTextField();
		txtSenha.setColumns(10);
		txtSenha.setBounds(256, 76, 220, 20);
		contentPane.add(txtSenha);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setForeground(new Color(255, 255, 255));
		lblEmail.setBounds(10, 107, 46, 14);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(10, 122, 466, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblEmpresa = new JLabel("Empresa:");
		lblEmpresa.setForeground(new Color(255, 255, 255));
		lblEmpresa.setBounds(10, 153, 72, 14);
		contentPane.add(lblEmpresa);
		
		cmbBxEmpresa = new JComboBox<Empresa>();
		cmbBxEmpresa.setBounds(10, 167, 132, 20);
		contentPane.add(cmbBxEmpresa);
		for(int i=0;i<EpontoFachada.getInstance().getSizeEmpresas() ; i++){
			cmbBxEmpresa.addItem(EpontoFachada.getInstance().getEmpresas(null).get(i));
		}
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setForeground(new Color(255, 255, 255));
		lblTelefone.setBounds(152, 153, 78, 14);
		contentPane.add(lblTelefone);
		
		txtTelefone = new JTextField();
		txtTelefone.setBounds(152, 167, 151, 20);
		contentPane.add(txtTelefone);
		txtTelefone.setColumns(10);
		
		JLabel lblCargo = new JLabel("Cargo:");
		lblCargo.setForeground(new Color(255, 255, 255));
		lblCargo.setBounds(322, 153, 58, 14);
		contentPane.add(lblCargo);
		
		txtCargo = new JTextField();
		txtCargo.setBounds(321, 167, 155, 20);
		contentPane.add(txtCargo);
		txtCargo.setColumns(10);
		
		JLabel lblEscala = new JLabel("Escala");
		lblEscala.setForeground(new Color(255, 255, 255));
		lblEscala.setBounds(10, 198, 46, 14);
		contentPane.add(lblEscala);
		
		cmbBxEscala = new JComboBox <String>();
		cmbBxEscala.setBounds(10, 215, 132, 20);
		contentPane.add(cmbBxEscala);
		cmbBxEscala.addItem("Seg. à Sex");
		cmbBxEscala.addItem("Dia sim Dia não");
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 475, 486, 10);
		contentPane.add(separator);
		
		JLabel lblHorrioChegada = new JLabel("Hor\u00E1rio Chegada:");
		lblHorrioChegada.setForeground(new Color(255, 255, 255));
		lblHorrioChegada.setBounds(10, 482, 104, 14);
		contentPane.add(lblHorrioChegada);
		
		txtHoraChegada = new JFormattedTextField(createFormatter("##"));
		txtHoraChegada.setBounds(10, 502, 30, 20);
		contentPane.add(txtHoraChegada);
		txtHoraChegada.setColumns(10);
		
		txtMinutosChegada = new JFormattedTextField(createFormatter("##"));
		txtMinutosChegada.setColumns(10);
		txtMinutosChegada.setBounds(52, 502, 30, 20);
		contentPane.add(txtMinutosChegada);
		
		JLabel lbl2ptChegada = new JLabel(":");
		lbl2ptChegada.setBounds(45, 506, 10, 10);
		contentPane.add(lbl2ptChegada);
		
		JLabel lblHorarioSaida = new JLabel("Hor\u00E1rio Sa\u00EDda:");
		lblHorarioSaida.setForeground(new Color(255, 255, 255));
		lblHorarioSaida.setBounds(122, 482, 88, 14);
		contentPane.add(lblHorarioSaida);
		
		txtHoraSaida = new JFormattedTextField(createFormatter("##"));
		txtHoraSaida.setColumns(10);
		txtHoraSaida.setBounds(120, 502, 30, 20);
		contentPane.add(txtHoraSaida);
		
		JLabel lbl2ptSaida = new JLabel(":");
		lbl2ptSaida.setBounds(155, 506, 10, 10);
		contentPane.add(lbl2ptSaida);
		
		txtMinutosSaida = new JFormattedTextField(createFormatter("##"));
		txtMinutosSaida.setColumns(10);
		txtMinutosSaida.setBounds(162, 502, 30, 20);
		contentPane.add(txtMinutosSaida);
		
		JLabel lblHorarioSaidaIntervalo = new JLabel("Hor\u00E1rio Sa\u00EDda Intervalo:");
		lblHorarioSaidaIntervalo.setForeground(new Color(255, 255, 255));
		lblHorarioSaidaIntervalo.setBounds(218, 482, 137, 14);
		contentPane.add(lblHorarioSaidaIntervalo);
		
		txtHoraSaidaIntervalo = new JFormattedTextField(createFormatter("##"));
		txtHoraSaidaIntervalo.setColumns(10);
		txtHoraSaidaIntervalo.setBounds(243, 502, 30, 20);
		contentPane.add(txtHoraSaidaIntervalo);
		
		JLabel lbl2ptSaidaIntervalo = new JLabel(":");
		lbl2ptSaidaIntervalo.setBounds(278, 506, 10, 10);
		contentPane.add(lbl2ptSaidaIntervalo);
		
		txtMinutosSaidaIntervalo = new JFormattedTextField(createFormatter("##"));
		txtMinutosSaidaIntervalo.setColumns(10);
		txtMinutosSaidaIntervalo.setBounds(285, 502, 30, 20);
		contentPane.add(txtMinutosSaidaIntervalo);
		
		JLabel lblHorrioChegadaIntervalo = new JLabel("Hor\u00E1rio Chegada Intervalo:");
		lblHorrioChegadaIntervalo.setForeground(new Color(255, 255, 255));
		lblHorrioChegadaIntervalo.setBounds(355, 482, 150, 14);
		contentPane.add(lblHorrioChegadaIntervalo);
		
		txtHoraChegadaIntervalo = new JFormattedTextField(createFormatter("##"));
		txtHoraChegadaIntervalo.setColumns(10);
		txtHoraChegadaIntervalo.setBounds(376, 502, 30, 20);
		contentPane.add(txtHoraChegadaIntervalo);
		
		JLabel lbl2ptChegadaIntervalo = new JLabel(":");
		lbl2ptChegadaIntervalo.setBounds(411, 506, 10, 10);
		contentPane.add(lbl2ptChegadaIntervalo);
		
		txtMinutosChegadaIntervalo = new JFormattedTextField(createFormatter("##"));
		txtMinutosChegadaIntervalo.setColumns(10);
		txtMinutosChegadaIntervalo.setBounds(418, 502, 30, 20);
		contentPane.add(txtMinutosChegadaIntervalo);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setForeground(new Color(0,0,0));
		btnSalvar.setBounds(510, 289, 116, 23);
		btnSalvar.addActionListener(this);
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(btnSalvar);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.setForeground(new Color(0, 0, 0));
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnLimpar.setBounds(664, 289, 116, 23);
		btnLimpar.addActionListener(this);
		contentPane.add(btnLimpar);
		
		//webcam
		wCam = Webcam.getDefault();
		wCam.setViewSize(WebcamResolution.VGA.getSize());
		wCamPanel = new WebcamPanel(wCam);
		wCamPanel.setDisplayDebugInfo(false);
		wCamPanel.setBounds(510, 25, 270, 240);
		contentPane.add(wCamPanel);
		
		JLabel lblMatricula = new JLabel("Matr\u00EDcula:");
		lblMatricula.setForeground(Color.WHITE);
		lblMatricula.setBounds(152, 201, 78, 14);
		contentPane.add(lblMatricula);
		
		txtMatricula = new JTextField();
		txtMatricula.setColumns(10);
		txtMatricula.setBounds(152, 215, 151, 20);
		contentPane.add(txtMatricula);
		
		JLabel lblRg = new JLabel("RG:");
		lblRg.setForeground(Color.WHITE);
		lblRg.setBounds(325, 201, 78, 14);
		contentPane.add(lblRg);
		
		txtRg = new JTextField();
		txtRg.setColumns(10);
		txtRg.setBounds(322, 215, 154, 20);
		contentPane.add(txtRg);
		
		JLabel lblEndereco = new JLabel("Rua:");
		lblEndereco.setForeground(Color.WHITE);
		lblEndereco.setBounds(10, 248, 116, 14);
		contentPane.add(lblEndereco);
		
		txtEndereco = new JTextField();
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(10, 262, 396, 20);
		contentPane.add(txtEndereco);
		
		JLabel lblNumero = new JLabel("Numero:");
		lblNumero.setForeground(Color.WHITE);
		lblNumero.setBounds(411, 246, 78, 14);
		contentPane.add(lblNumero);
		
		txtNumero = new JTextField();
		txtNumero.setColumns(10);
		txtNumero.setBounds(411, 262, 65, 20);
		contentPane.add(txtNumero);
		
		JLabel lblComplemento = new JLabel("Complemento:");
		lblComplemento.setForeground(Color.WHITE);
		lblComplemento.setBounds(10, 293, 85, 14);
		contentPane.add(lblComplemento);
		
		txtComplemento = new JTextField();
		txtComplemento.setColumns(10);
		txtComplemento.setBounds(10, 307, 151, 20);
		contentPane.add(txtComplemento);
		
		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setForeground(Color.WHITE);
		lblBairro.setBounds(171, 293, 78, 14);
		contentPane.add(lblBairro);
		
		txtBairro = new JTextField();
		txtBairro.setColumns(10);
		txtBairro.setBounds(171, 307, 154, 20);
		contentPane.add(txtBairro);
		
		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setForeground(Color.WHITE);
		lblCidade.setBounds(335, 293, 78, 14);
		contentPane.add(lblCidade);
		
		txtCidade = new JTextField();
		txtCidade.setColumns(10);
		txtCidade.setBounds(335, 307, 141, 20);
		contentPane.add(txtCidade);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setForeground(Color.WHITE);
		lblEstado.setBounds(10, 334, 78, 14);
		contentPane.add(lblEstado);
		
		txtEstado = new JTextField();
		txtEstado.setColumns(10);
		txtEstado.setBounds(10, 348, 151, 20);
		contentPane.add(txtEstado);
		
		JLabel lblCep = new JLabel("CEP:");
		lblCep.setForeground(Color.WHITE);
		lblCep.setBounds(171, 334, 78, 14);
		contentPane.add(lblCep);
		
		txtCep = new JTextField();
		txtCep.setColumns(10);
		txtCep.setBounds(171, 348, 154, 20);
		contentPane.add(txtCep);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(495, 24, 10, 453);
		contentPane.add(separator_1);
		
		JLabel lblCtps = new JLabel("N\u00BA Carteira de Trabalho:");
		lblCtps.setForeground(Color.WHITE);
		lblCtps.setBounds(335, 334, 134, 14);
		contentPane.add(lblCtps);
		
		txtCtps = new JTextField();
		txtCtps.setColumns(10);
		txtCtps.setBounds(335, 348, 141, 20);
		contentPane.add(txtCtps);
		
		JLabel lblPis = new JLabel("N\u00BA PIS:");
		lblPis.setForeground(Color.WHITE);
		lblPis.setBounds(10, 379, 78, 14);
		contentPane.add(lblPis);
		
		txtPis = new JTextField();
		txtPis.setColumns(10);
		txtPis.setBounds(10, 393, 151, 20);
		contentPane.add(txtPis);
		
		JLabel lblCodEqp = new JLabel("C\u00F3digo Equipe:");
		lblCodEqp.setForeground(Color.WHITE);
		lblCodEqp.setBounds(335, 379, 92, 14);
		contentPane.add(lblCodEqp);
		
		txtCodEqp = new JTextField();
		txtCodEqp.setColumns(10);
		txtCodEqp.setBounds(335, 393, 141, 20);
		contentPane.add(txtCodEqp);
		
		JLabel lblCpf_coord = new JLabel("Coordenador:");
		lblCpf_coord.setForeground(Color.WHITE);
		lblCpf_coord.setBounds(10, 424, 132, 14);
		contentPane.add(lblCpf_coord);
		
		txtCpf_coord = new JComboBox<String>();
		txtCpf_coord.setBounds(10, 438, 315, 20);
		contentPane.add(txtCpf_coord);
		
		FabricaDeConexao bd = new FabricaDeConexao();
		Connection con = null;
		try {
			con = bd.getConexao("admin", "bancodedados");
			con.setAutoCommit(false);
			ResultSet rsCoord = con.createStatement().executeQuery("SELECT nome, coordenador.cpf FROM coordenador JOIN PESSOA WHERE coordenador.cpf=pessoa.cpf;");
			String coord;
			while (rsCoord.next()){
				coord = rsCoord.getString("nome") + "-" +rsCoord.getString("cpf");
				txtCpf_coord.addItem(coord);
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JLabel lblDt_admissao = new JLabel("Data Admiss\u00E3o:");
		lblDt_admissao.setForeground(Color.WHITE);
		lblDt_admissao.setBounds(171, 379, 116, 14);
		contentPane.add(lblDt_admissao);
		
		txtDt_admissao = new JFormattedTextField(createFormatter("##/##/####"));
		txtDt_admissao.setColumns(10);
		txtDt_admissao.setBounds(171, 393, 154, 20);
		contentPane.add(txtDt_admissao);
		
		separator_2 = new JSeparator();
		separator_2.setBounds(10, 536, 486, 10);
		contentPane.add(separator_2);
		
		txtCodDept = new JTextField();
		txtCodDept.setBounds(335, 438, 141, 20);
		contentPane.add(txtCodDept);
		
		JLabel lblCodDept = new JLabel("C\u00F3digo Departamento:");
		lblCodDept.setForeground(Color.WHITE);
		lblCodDept.setBounds(335, 424, 126, 14);
		contentPane.add(lblCodDept);
	}
	
	protected MaskFormatter createFormatter(String s) {
		MaskFormatter formatter = null;
	    try {
	        formatter = new MaskFormatter(s);
	    } catch (java.text.ParseException exc) {
	        System.err.println("formatter is bad: " + exc.getMessage());
	        System.exit(-1);
	    }
	    return formatter;
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
		txtBairro.setText("");
		txtMatricula.setText("");
		txtRg.setText("");
		txtEndereco.setText("");
		txtNumero.setText("");
		txtComplemento.setText("");
		txtBairro.setText("");
		txtCidade.setText("");
		txtEstado.setText("");
		txtCep.setText("");
		txtCtps.setText("");
		txtPis.setText("");
		txtCodEqp.setText("");
//		txtCpf_coord.setText("");
		txtDt_admissao.setText("");
		txtCodDept.setText("");	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnLimpar)){
			this.limparCampos();
		}
		else if(e.getSource().equals(btnSalvar)){
			Funcionario funcionario = null;
			String nome = txtNome.getText();
			String cpf = txtCPF.getText();
			String email = txtEmail.getText();
			char[] senha = txtSenha.getText().toCharArray();
			String telefone = txtTelefone.getText();
			String cargo = txtCargo.getText();
			
			Empresa empresa = null;
			LocalTime horaChegada = null; 
			LocalTime horaSaida = null ;
			LocalTime horaChegadaIntervalo = null;
			LocalTime horaSaidaIntervalo = null ;
			
			int matricula = Integer.parseInt(txtMatricula.getText());
			String rg = txtRg.getText();
			String endereco =  txtEndereco.getText();
			String numero =  txtNumero.getText();
			String complemento = txtComplemento.getText();
			String bairro =  txtBairro.getText();
			String cidade = txtCidade.getText();
			String estado = txtEstado.getText();
			String cep = txtCep.getText();
			String ctps = txtCtps.getText();
			String pis = txtPis.getText();
			int codDept = Integer.parseInt(txtCodDept.getText());
			int cod_eqp = Integer.parseInt(txtCodEqp.getText());
			String cpf_coord = txtCpf_coord.getSelectedItem().toString();
			Date dt_admissao = null;
			Date dt_demissao = null;
			if (txtDt_admissao.getText() != null){
				String dt = txtDt_admissao.getText();
				String[] dts = dt.split("/");
				dt_admissao = Date.valueOf(dts[2]+"-"+dts[1]+"-"+dts[0]);
			}
			try {
				empresa = (Empresa) EpontoFachada.getInstance().buscaEmpresaNome(cmbBxEmpresa.getSelectedItem().toString());
				horaChegada = LocalTime.of( Integer.parseInt(txtHoraChegada.getText()) , Integer.parseInt( txtMinutosChegada.getText()) );
				horaSaida = LocalTime.of( Integer.parseInt(txtHoraSaida.getText()) , Integer.parseInt( txtMinutosSaida.getText()) );
				horaChegadaIntervalo = LocalTime.of( Integer.parseInt(txtHoraChegadaIntervalo.getText()) , Integer.parseInt( txtMinutosChegadaIntervalo.getText()) );
				horaSaidaIntervalo = LocalTime.of( Integer.parseInt(txtHoraSaidaIntervalo.getText()) , Integer.parseInt( txtMinutosSaidaIntervalo.getText()) );
				funcionario = new Funcionario(nome,
						cpf,
						email,
						senha,
						matricula,
						rg,
						telefone,
						cargo,
						codDept,
						endereco,
						numero,
						complemento,
						bairro,
						cidade,
						estado,
						cep,
						cpf_coord,
						ctps,
						pis,
						dt_admissao,
						dt_demissao,
						cod_eqp,
						empresa,
						"Seg. a Sexta",
						horaChegada, horaSaida, horaChegadaIntervalo, horaSaidaIntervalo);
				funcionario.setFotoPadrao(new ImageIcon(wCam.getImage()));

				EpontoFachada.getInstance().adicionarPessoa(funcionario);
				
				GerarQRCode.gerar(funcionario);
				JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso" );
				this.limparCampos();
				
			} catch (EmpresaNaoEncontradaException e3) {
				JOptionPane.showMessageDialog(null, e3.getMessage());
			}catch(NumberFormatException e2){
				JOptionPane.showMessageDialog(null, "Por favor, digite apenas números.");
				txtHoraChegada.requestFocus();
			}catch(NomeInvalidoException e1){
				JOptionPane.showMessageDialog(null, e1.getMessage() );
			}catch (SQLException e1){
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowClosed(WindowEvent e) {
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
}
