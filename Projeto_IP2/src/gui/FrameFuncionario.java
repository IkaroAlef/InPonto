package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JButton;








import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

import dados.RepPessoas;
import dados.exceptionsDados.FuncionarioNaoEncontradoException;
import negócio.EpontoFachada;
import negócio.entity_beans.Funcionario;
import negócio.entity_beans.RegPonto;
import negócio.entity_beans.exceptionsBeans.NomeInvalidoException;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.Component;

public class FrameFuncionario extends JFrame implements ActionListener, WindowListener {

	private JPanel contentPane;
	private JButton btnBaterPonto;
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
					FrameFuncionario frame = new FrameFuncionario((Funcionario) RepPessoas.getInstance().buscarPessoaNome("Ikaro Alef"));
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
		setResizable(false);
		this.funcionario = funcionario;
		setTitle("Bem-Vindo "+funcionario.getNome());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 900, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		addWindowListener(this);
		
		btnBaterPonto = new JButton("Bater Ponto");
		btnBaterPonto.setBounds(600, 353, 117, 23);
		contentPane.add(btnBaterPonto);
		btnBaterPonto.addActionListener(this);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(87, 10, 63, 14);
		contentPane.add(lblNome);
		
		JLabel lblNomeFuncionario = new JLabel(funcionario.getNome());
		lblNomeFuncionario.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblNomeFuncionario.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNomeFuncionario.setBounds(160, 10, 250, 14);
		contentPane.add(lblNomeFuncionario);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblCpf.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCpf.setBounds(92, 45, 58, 14);
		contentPane.add(lblCpf);
		
		JLabel lblCpfFuncionario = new JLabel(funcionario.getCpf());
		lblCpfFuncionario.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblCpfFuncionario.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCpfFuncionario.setBounds(162, 45, 248, 14);
		contentPane.add(lblCpfFuncionario);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(88, 80, 62, 14);
		contentPane.add(lblEmail);
		
		JLabel lblEmailFuncionario = new JLabel(funcionario.getEmail());
		lblEmailFuncionario.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblEmailFuncionario.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmailFuncionario.setBounds(162, 80, 248, 14);
		contentPane.add(lblEmailFuncionario);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblTelefone.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTelefone.setBounds(75, 115, 75, 14);
		contentPane.add(lblTelefone);
		
		JLabel lblTelefoneFuncionario = new JLabel(funcionario.getTelefone());
		lblTelefoneFuncionario.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblTelefoneFuncionario.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTelefoneFuncionario.setBounds(162, 115, 165, 14);
		contentPane.add(lblTelefoneFuncionario);
		
		JLabel lblEmpresa = new JLabel("Empresa:");
		lblEmpresa.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblEmpresa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmpresa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmpresa.setBounds(85, 150, 70, 14);
		contentPane.add(lblEmpresa);
		
		JLabel lblEmpresaFuncionario = new JLabel(funcionario.getEmpresa().getNomeEmpresa());
		lblEmpresaFuncionario.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblEmpresaFuncionario.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmpresaFuncionario.setBounds(162, 150, 208, 14);
		contentPane.add(lblEmpresaFuncionario);
		
		JLabel lblCargo = new JLabel("Cargo:");
		lblCargo.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblCargo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCargo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCargo.setBounds(87, 185, 63, 14);
		contentPane.add(lblCargo);
		
		JLabel lblCargoFuncionario = new JLabel(funcionario.getCargo());
		lblCargoFuncionario.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblCargoFuncionario.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCargoFuncionario.setBounds(162, 185, 165, 14);
		contentPane.add(lblCargoFuncionario);
		
		JLabel lblEscala = new JLabel("Escala:");
		lblEscala.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblEscala.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEscala.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEscala.setBounds(87, 220, 63, 14);
		contentPane.add(lblEscala);
		
		JLabel lblEscalaFuncionario = new JLabel(funcionario.getEscala());
		lblEscalaFuncionario.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblEscalaFuncionario.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEscalaFuncionario.setBounds(162, 220, 165, 14);
		contentPane.add(lblEscalaFuncionario);
		
		JLabel lblHorarioChegada = new JLabel("Hor\u00E1rio Chegada:");
		lblHorarioChegada.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblHorarioChegada.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHorarioChegada.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHorarioChegada.setBounds(24, 255, 126, 14);
		contentPane.add(lblHorarioChegada);
		
		JLabel lblHorarioChegadaFuncionario = new JLabel(funcionario.getChegada().toString());
		lblHorarioChegadaFuncionario.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblHorarioChegadaFuncionario.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHorarioChegadaFuncionario.setBounds(162, 255, 165, 14);
		contentPane.add(lblHorarioChegadaFuncionario);
		
		JLabel lblHorarioSaida = new JLabel("Hor\u00E1rio Sa\u00EDda:");
		lblHorarioSaida.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblHorarioSaida.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHorarioSaida.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHorarioSaida.setBounds(24, 290, 126, 14);
		contentPane.add(lblHorarioSaida);
		
		JLabel lblHorarioSaidaFuncionario = new JLabel(funcionario.getSaida().toString());
		lblHorarioSaidaFuncionario.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblHorarioSaidaFuncionario.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHorarioSaidaFuncionario.setBounds(162, 290, 165, 14);
		contentPane.add(lblHorarioSaidaFuncionario);
		
		JLabel lblIntervaloSaida = new JLabel("Intervalo Sa\u00EDda:");
		lblIntervaloSaida.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblIntervaloSaida.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIntervaloSaida.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIntervaloSaida.setBounds(48, 325, 102, 14);
		contentPane.add(lblIntervaloSaida);
		
		JLabel lblIntervaloSaidaFuncionario = new JLabel(funcionario.getIntervalo_out().toString());
		lblIntervaloSaidaFuncionario.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblIntervaloSaidaFuncionario.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIntervaloSaidaFuncionario.setBounds(162, 325, 165, 14);
		contentPane.add(lblIntervaloSaidaFuncionario);
		
		JLabel lblIntervaloVolta = new JLabel("Intervalo Volta:");
		lblIntervaloVolta.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblIntervaloVolta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIntervaloVolta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIntervaloVolta.setBounds(48, 360, 102, 14);
		contentPane.add(lblIntervaloVolta);
		
		JLabel lblIntervaloVoltaFuncionario = new JLabel(funcionario.getIntervalo_in().toString());
		lblIntervaloVoltaFuncionario.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblIntervaloVoltaFuncionario.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIntervaloVoltaFuncionario.setBounds(162, 360, 165, 14);
		contentPane.add(lblIntervaloVoltaFuncionario);
		
		wCam = Webcam.getDefault();
		wCam.setViewSize(WebcamResolution.VGA.getSize());
		wCamPanel = new WebcamPanel(wCam);
		wCamPanel.setBounds(444, 10, 440, 330);
		contentPane.add(wCamPanel);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(421, 16, 1, 360);
		contentPane.add(separator);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnBaterPonto)){
			RegPonto ponto = new RegPonto();
			ponto.registrarPonto(funcionario);
			try {
				ponto.registrarPonto((Funcionario)EpontoFachada.getInstance().getPessoaCpf(funcionario.getCpf()),new ImageIcon(wCam.getImage()));
			} catch (FuncionarioNaoEncontradoException | NomeInvalidoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			EpontoFachada.getInstance().adicionarRegistro(ponto);
			JOptionPane.showMessageDialog(null, "Ponto registrado com sucesso às " + ponto.getAgoraFormatada() + ("!"));
			}
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		wCam.close();
		ControladorDeTelas.getInstance().frameLogin();
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
