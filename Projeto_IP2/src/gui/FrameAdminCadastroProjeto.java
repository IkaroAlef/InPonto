package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.crypto.Data;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

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
	private JComboBox<Coordenador> cmbBxCoordenador;
	private JComboBox<Departamento> cmbBxDepartamento;
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

		cmbBxCoordenador = new JComboBox<Coordenador>();
		cmbBxCoordenador.setBounds(151, 196, 262, 20);
		contentPane.add(cmbBxCoordenador);

		txtDataInicio = new JFormattedTextField();
		txtDataInicio.setColumns(10);
		txtDataInicio.setBounds(151, 140, 128, 20);
		contentPane.add(txtDataInicio);

		txtHoras = new JTextField();
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

		cmbBxDepartamento = new JComboBox<Departamento>();
		cmbBxDepartamento.setBounds(151, 227, 262, 20);
		contentPane.add(cmbBxDepartamento);
		// for(int i=0;i<EpontoFachada.getInstance().getSizeEmpresas() ; i++){
		// cmbBxEmpresa.addItem(EpontoFachada.getInstance().getEmpresas(null).get(i));
		// }
	}

	private void limparCampos() {
		txtDescricaoProjeto.setText("");
		txtDataInicio.setText("");
		txtHoras.setText("");

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnLimpar)) {
			this.limparCampos();
		} else if (e.getSource().equals(btnSalvar)) {
			Projeto projeto = null;
			int codigo = 1;
			String descricao = txtDescricaoProjeto.getText();
			String coordenador = cmbBxCoordenador.getName();
			String dataInicio = txtDataInicio.getText();
			int horas = Integer.parseInt(txtHoras.getText());
			String departamento = cmbBxDepartamento.getName();

			projeto = new Projeto(codigo, horas, descricao, dataInicio, null, coordenador, departamento);

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
