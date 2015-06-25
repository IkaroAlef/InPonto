package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JButton;


import dados.RepPessoas;
import dados.exceptionsDados.FuncionarioNaoEncontradoException;
import negócio.EpontoFachada;
import negócio.entity_beans.Funcionario;
import negócio.entity_beans.RegPonto;

public class FrameFuncionario extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnBaterPonto;
	private Funcionario funcionario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameFuncionario frame = new FrameFuncionario((Funcionario) RepPessoas.getInstance().buscarPessoaNome("Ikaro"));
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
	public FrameFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
		setTitle("Funcionario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblImagemDaCamera = new JLabel("IMAGEM DA CAMERA");
		lblImagemDaCamera.setBounds(699, 243, 117, 14);
		contentPane.add(lblImagemDaCamera);
		
		btnBaterPonto = new JButton("Bater Ponto");
		btnBaterPonto.setBounds(699, 415, 117, 23);
		contentPane.add(btnBaterPonto);
		btnBaterPonto.addActionListener(this);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(49, 269, 63, 14);
		contentPane.add(lblNome);
		
		JLabel lblNomeFuncionario = new JLabel(funcionario.getNome());
		lblNomeFuncionario.setBounds(122, 269, 165, 14);
		contentPane.add(lblNomeFuncionario);
		
		JLabel lblCpf = new JLabel("Cpf:");
		lblCpf.setBounds(49, 294, 63, 14);
		contentPane.add(lblCpf);
		
		JLabel lblCpfFuncionario = new JLabel(funcionario.getCpf());
		lblCpfFuncionario.setBounds(122, 294, 165, 14);
		contentPane.add(lblCpfFuncionario);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(49, 319, 63, 14);
		contentPane.add(lblEmail);
		
		JLabel lblEmailFuncionario = new JLabel(funcionario.getEmail());
		lblEmailFuncionario.setBounds(122, 319, 165, 14);
		contentPane.add(lblEmailFuncionario);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(49, 344, 63, 14);
		contentPane.add(lblTelefone);
		
		JLabel lblTelefoneFuncionario = new JLabel(funcionario.getTelefone());
		lblTelefoneFuncionario.setBounds(122, 344, 165, 14);
		contentPane.add(lblTelefoneFuncionario);
		
		JLabel lblEmpresa = new JLabel("Empresa:");
		lblEmpresa.setBounds(49, 369, 63, 14);
		contentPane.add(lblEmpresa);
		
		JLabel lblEmpresaFuncionario = new JLabel(funcionario.getEmpresa().getNomeEmpresa());
		lblEmpresaFuncionario.setBounds(122, 369, 165, 14);
		contentPane.add(lblEmpresaFuncionario);
		
		JLabel lblCargo = new JLabel("Cargo:");
		lblCargo.setBounds(49, 394, 63, 14);
		contentPane.add(lblCargo);
		
		JLabel lblCargoFuncionario = new JLabel(funcionario.getCargo());
		lblCargoFuncionario.setBounds(122, 394, 165, 14);
		contentPane.add(lblCargoFuncionario);
		
		JLabel lblEscala = new JLabel("Escala:");
		lblEscala.setBounds(49, 419, 63, 14);
		contentPane.add(lblEscala);
		
		JLabel lblEscalaFuncionario = new JLabel(funcionario.getEscala());
		lblEscalaFuncionario.setBounds(122, 419, 165, 14);
		contentPane.add(lblEscalaFuncionario);
		
		JLabel lblHorarioChegada = new JLabel("Hor\u00E1rio Chegada:");
		lblHorarioChegada.setBounds(10, 444, 102, 14);
		contentPane.add(lblHorarioChegada);
		
		JLabel lblHorarioChegadaFuncionario = new JLabel(funcionario.getChegada().toString());
		lblHorarioChegadaFuncionario.setBounds(122, 444, 165, 14);
		contentPane.add(lblHorarioChegadaFuncionario);
		
		JLabel lblHorarioSaida = new JLabel("Hor\u00E1rio Sa\u00EDda:");
		lblHorarioSaida.setBounds(10, 469, 102, 14);
		contentPane.add(lblHorarioSaida);
		
		JLabel lblHorarioSaidaFuncionario = new JLabel(funcionario.getSaida().toString());
		lblHorarioSaidaFuncionario.setBounds(122, 469, 165, 14);
		contentPane.add(lblHorarioSaidaFuncionario);
		
		JLabel lblIntervaloSaida = new JLabel("Intervalo Sa\u00EDda:");
		lblIntervaloSaida.setBounds(10, 494, 102, 14);
		contentPane.add(lblIntervaloSaida);
		
		JLabel lblIntervaloSaidaFuncionario = new JLabel(funcionario.getIntervalo_out().toString());
		lblIntervaloSaidaFuncionario.setBounds(122, 494, 165, 14);
		contentPane.add(lblIntervaloSaidaFuncionario);
		
		JLabel lblIntervaloVolta = new JLabel("Intervalo Volta:");
		lblIntervaloVolta.setBounds(10, 519, 102, 14);
		contentPane.add(lblIntervaloVolta);
		
		JLabel lblIntervaloVoltaFuncionario = new JLabel(funcionario.getIntervalo_in().toString());
		lblIntervaloVoltaFuncionario.setBounds(122, 519, 165, 14);
		contentPane.add(lblIntervaloVoltaFuncionario);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnBaterPonto)){
			RegPonto ponto = new RegPonto();
			ponto.registrarPonto(funcionario);
			try {
				ponto.registrarPonto((Funcionario)EpontoFachada.getInstance().buscarPessoaCpf(funcionario.getCpf()));
			} catch (FuncionarioNaoEncontradoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			EpontoFachada.getInstance().adicionarRegistro(ponto);
			JOptionPane.showMessageDialog(null, "Ponto registrado com sucesso às " + ponto.getAgoraFormatada() + ("!"));
			}
		
	}
}
