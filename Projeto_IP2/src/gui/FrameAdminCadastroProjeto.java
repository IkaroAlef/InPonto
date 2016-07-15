package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.xml.crypto.Data;

import conexaoBD.FabricaDeConexao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import negócio.ControladorProjetos;
import negócio.EpontoFachada;
import negócio.entity_beans.Coordenador;
import negócio.entity_beans.Departamento;
import negócio.entity_beans.Projeto;
import negócio.entity_beans.exceptionsBeans.CNPJInvalidoException;
import negócio.entity_beans.exceptionsBeans.NomeInvalidoException;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

public class FrameAdminCadastroProjeto extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtDescricaoProjeto;
	private JComboBox<String> cmbBxCoordenador;
	private JComboBox<String> cmbBxDepartamento;
	private JFormattedTextField txtDataInicio;
	private JTextField txtHoras;
	private JButton btnLimpar;
	private JButton btnSalvar;
	private JLabel lblCadastrarProjeto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameAdminCadastroProjeto frame = new FrameAdminCadastroProjeto();
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
	public FrameAdminCadastroProjeto() {
		setTitle("Cadastrar Projeto");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 646, 398);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(169, 169, 169));
		contentPane.setForeground(new Color(169, 169, 169));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDescricao = new JLabel("Descri\u00E7\u00E3o:");
		lblDescricao.setForeground(new Color(255, 255, 255));
		lblDescricao.setBounds(86, 115, 67, 14);
		contentPane.add(lblDescricao);

		txtDescricaoProjeto = new JTextField();
		txtDescricaoProjeto.setBounds(151, 112, 351, 20);
		txtDescricaoProjeto.setColumns(10);
		contentPane.add(txtDescricaoProjeto);

		JLabel lblHoras = new JLabel("Horas:");
		lblHoras.setForeground(new Color(255, 255, 255));
		lblHoras.setBounds(104, 171, 49, 14);
		contentPane.add(lblHoras);

		cmbBxCoordenador = new JComboBox<String>();
		// cmbBxCoordenador = new JTextField();
		cmbBxCoordenador.setBounds(151, 196, 262, 20);
		contentPane.add(cmbBxCoordenador);

		FabricaDeConexao bd = new FabricaDeConexao();
		Connection con = null;
		try {
			con = bd.getConexao("admin", "bancodedados");
			con.setAutoCommit(false);
			ResultSet rsCoord = con.createStatement().executeQuery(
					"SELECT pessoa.nome, coordenador.cpf, departamento.CNPJ FROM coordenador JOIN PESSOA JOIN PROJETO JOIN DEPARTAMENTO WHERE coordenador.cpf = pessoa.cpf;");
			String coord;
			while (rsCoord.next()) {
				coord = rsCoord.getString("nome") + "-" + rsCoord.getString("cpf");
				cmbBxCoordenador.addItem(coord);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		txtDataInicio = new JFormattedTextField(createFormatter("##/##/####"));
		txtDataInicio.setColumns(10);
		txtDataInicio.setBounds(151, 140, 128, 20);
		contentPane.add(txtDataInicio);

		NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
		DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
		decimalFormat.setGroupingUsed(false);

		txtHoras = new JTextField() {
			public void processKeyEvent(KeyEvent ev) {
				char c = ev.getKeyChar();
				try {
					// Ignore all non-printable characters. Just check the
					// printable ones.
					if (c > 31 && c < 127) {
						Integer.parseInt(c + "");
					}
					super.processKeyEvent(ev);
				} catch (NumberFormatException nfe) {
					// Do nothing. Character inputted is not a number, so ignore
					// it.
				}
			}
		};
		txtHoras.setColumns(10);
		txtHoras.setBounds(151, 168, 100, 20);
		contentPane.add(txtHoras);

		JLabel lblDataInicio = new JLabel("Data Inicio:");
		lblDataInicio.setForeground(new Color(255, 255, 255));
		lblDataInicio.setBounds(81, 140, 72, 14);
		contentPane.add(lblDataInicio);

		JLabel lblCoordenador = new JLabel("Coordenador:");
		lblCoordenador.setForeground(new Color(255, 255, 255));
		lblCoordenador.setBounds(68, 199, 85, 14);
		contentPane.add(lblCoordenador);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setForeground(new Color(0, 0, 0));
		btnSalvar.setBounds(175, 273, 100, 23);
		btnSalvar.addActionListener(this);
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(btnSalvar);

		btnLimpar = new JButton("Limpar");
		btnLimpar.setForeground(new Color(0, 0, 0));
		btnLimpar.setBounds(285, 273, 100, 23);
		btnLimpar.addActionListener(this);
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(btnLimpar);

		lblCadastrarProjeto = new JLabel("CADASTRO DE PROJETO");
		lblCadastrarProjeto.setForeground(new Color(255, 255, 255));
		lblCadastrarProjeto.setBackground(new Color(128, 128, 128));
		lblCadastrarProjeto.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCadastrarProjeto.setBounds(212, 54, 223, 14);
		contentPane.add(lblCadastrarProjeto);

		JLabel lblDepartamento = new JLabel("Departamento:");
		lblDepartamento.setForeground(Color.WHITE);
		lblDepartamento.setBounds(63, 230, 90, 14);
		contentPane.add(lblDepartamento);

		cmbBxDepartamento = new JComboBox<String>();
		cmbBxDepartamento.setBounds(151, 227, 262, 20);
		contentPane.add(cmbBxDepartamento);

		try {
			con = bd.getConexao("admin", "bancodedados");
			con.setAutoCommit(false);
			ResultSet rsDept = con.createStatement().executeQuery("SELECT nome, codigo FROM departamento;");
			String dept;
			while (rsDept.next()) {
				dept = rsDept.getString("codigo") + "-" + rsDept.getString("nome");
				cmbBxDepartamento.addItem(dept);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	private void limparCampos() {
		txtDescricaoProjeto.setText("");
		txtDataInicio.setText("");
		txtHoras.setText("");
		// cmbBxCoordenador.setText("");;
		// cmbBxDepartamento.setText("");
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnLimpar)) {
			this.limparCampos();
		} else if (e.getSource().equals(btnSalvar)) {
			Projeto projeto = null;
			String descricao = txtDescricaoProjeto.getText();
			String coordenador = cmbBxCoordenador.getSelectedItem().toString();
			String[] coord = coordenador.split("-");
			Date dataInicio = null;
			String cnpj = null;

			String departamento = cmbBxDepartamento.getSelectedItem().toString();
			String[] dept = departamento.split("-");
			FabricaDeConexao bd = new FabricaDeConexao();
			Connection con;
			try {
				con = bd.getConexao("admin", "bancodedados");
			
			con.setAutoCommit(false);
			PreparedStatement ps =con.prepareStatement("SELECT cnpj from departamento where departamento.codigo=?;");
			ps.setInt(1, Integer.parseInt(dept[0]));
			ResultSet rsDept = ps.executeQuery();
			rsDept.next();
			cnpj = rsDept.getString("cnpj");
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			
			if (txtDataInicio.getText() != null) {
				String dt = txtDataInicio.getText();
				String[] dts = dt.split("/");
				dataInicio = Date.valueOf(dts[2] + "-" + dts[1] + "-" + dts[0]);
			}
			int horas = Integer.parseInt(txtHoras.getText());

			projeto = new Projeto(horas, descricao, dataInicio, null, coord[1], Integer.parseInt(dept[0]),cnpj);

			ControladorProjetos controlProjeto = new ControladorProjetos();
			try {
				controlProjeto.adicionarProjeto(projeto);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			JOptionPane.showMessageDialog(null, "Projeto cadastrado com suscesso");
			this.limparCampos();
		}
	}
}
