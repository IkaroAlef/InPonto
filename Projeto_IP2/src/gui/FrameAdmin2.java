package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import com.toedter.calendar.JCalendar;

import dados.exceptionsDados.FuncionarioNaoEncontradoException;
import negócio.EpontoFachada;
import negócio.entity_beans.Funcionario;
import negócio.entity_beans.RegPonto;

import java.util.ArrayList;

public class FrameAdmin2 extends JFrame implements PropertyChangeListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JCalendar jcalendar;
	private Funcionario funcionario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameAdmin2 frame = new FrameAdmin2((Funcionario) EpontoFachada.getInstance().buscarPessoaNome("Ikaro"));
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
	public FrameAdmin2(Funcionario funcionario) {		
		super("Dados de " + funcionario.getNome());
		
		this.funcionario = funcionario;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFotoPadroAqui = new JLabel("FOTO PADR\u00C3O AQUI");
		lblFotoPadroAqui.setBounds(699, 180, 129, 14);
		contentPane.add(lblFotoPadroAqui);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(590, 235, 46, 14);
		contentPane.add(lblNome);
		
		JLabel lblNomeFuncionario = new JLabel(funcionario.getNome());
		lblNomeFuncionario.setBounds(663, 235, 165, 14);
		contentPane.add(lblNomeFuncionario);
		
		JLabel lblCpf = new JLabel("Cpf:");
		lblCpf.setBounds(590, 260, 46, 14);
		contentPane.add(lblCpf);
		
		JLabel lblCpfFuncionario = new JLabel(funcionario.getCpf());
		lblCpfFuncionario.setBounds(663, 260, 165, 14);
		contentPane.add(lblCpfFuncionario);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(590, 285, 46, 14);
		contentPane.add(lblEmail);
		
		JLabel lblEmailFuncionario = new JLabel(funcionario.getEmail());
		lblEmailFuncionario.setBounds(663, 285, 165, 14);
		contentPane.add(lblEmailFuncionario);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(590, 310, 46, 14);
		contentPane.add(lblTelefone);
		
		JLabel lblTelefoneFuncionario = new JLabel(funcionario.getTelefone());
		lblTelefoneFuncionario.setBounds(663, 310, 165, 14);
		contentPane.add(lblTelefoneFuncionario);
		
		JLabel lblEmpresa = new JLabel("Empresa:");
		lblEmpresa.setBounds(590, 335, 46, 14);
		contentPane.add(lblEmpresa);
		
		JLabel lblEmpresaFuncionario = new JLabel(funcionario.getEmpresa().getNomeEmpresa());
		lblEmpresaFuncionario.setBounds(663, 335, 165, 14);
		contentPane.add(lblEmpresaFuncionario);
		
		JLabel lblCargo = new JLabel("Cargo:");
		lblCargo.setBounds(590, 360, 46, 14);
		contentPane.add(lblCargo);
		
		JLabel lblCargoFuncionario = new JLabel(funcionario.getCargo());
		lblCargoFuncionario.setBounds(663, 360, 165, 14);
		contentPane.add(lblCargoFuncionario);
		
		JLabel lblEscala = new JLabel("Escala:");
		lblEscala.setBounds(590, 385, 46, 14);
		contentPane.add(lblEscala);
		
		JLabel lblEscalaFuncionario = new JLabel(funcionario.getEscala());
		lblEscalaFuncionario.setBounds(663, 385, 165, 14);
		contentPane.add(lblEscalaFuncionario);
		
		JLabel lblHorarioChegada = new JLabel("Hor\u00E1rio Chegada:");
		lblHorarioChegada.setBounds(551, 410, 85, 14);
		contentPane.add(lblHorarioChegada);
		
		JLabel lblHorarioChegadaFuncionario = new JLabel(funcionario.getChegada().toString());
		lblHorarioChegadaFuncionario.setBounds(663, 410, 165, 14);
		contentPane.add(lblHorarioChegadaFuncionario);
		
		JLabel lblHorarioSaida = new JLabel("Hor\u00E1rio Sa\u00EDda:");
		lblHorarioSaida.setBounds(551, 435, 85, 14);
		contentPane.add(lblHorarioSaida);
		
		JLabel lblHorarioSaidaFuncionario = new JLabel(funcionario.getSaida().toString());
		lblHorarioSaidaFuncionario.setBounds(663, 435, 165, 14);
		contentPane.add(lblHorarioSaidaFuncionario);
		
		JLabel lblIntervaloSaida = new JLabel("Intervalo Sa\u00EDda:");
		lblIntervaloSaida.setBounds(551, 460, 85, 14);
		contentPane.add(lblIntervaloSaida);
		
		JLabel lblIntervaloSaidaFuncionario = new JLabel(funcionario.getIntervalo_out().toString());
		lblIntervaloSaidaFuncionario.setBounds(663, 460, 165, 14);
		contentPane.add(lblIntervaloSaidaFuncionario);
		
		JLabel lblIntervaloVolta = new JLabel("Intervalo Volta:");
		lblIntervaloVolta.setBounds(551, 485, 85, 14);
		contentPane.add(lblIntervaloVolta);
		
		JLabel lblIntervaloVoltaFuncionario = new JLabel(funcionario.getIntervalo_in().toString());
		lblIntervaloVoltaFuncionario.setBounds(663, 485, 165, 14);
		contentPane.add(lblIntervaloVoltaFuncionario);
		
		jcalendar = new JCalendar();
		jcalendar.getDayChooser().setAutoscrolls(true);
		jcalendar.setBounds(10, 11, 500, 350);
		contentPane.add(jcalendar);
		this.ColorirCalendario(jcalendar.getMonthChooser().getMonth(),jcalendar.getYearChooser().getYear());
		jcalendar.getMonthChooser().addPropertyChangeListener(this);
		jcalendar.getYearChooser().addPropertyChangeListener(this);
	}
	
	public void ColorirCalendario(int mes, int ano){
		mes++; //o array de dias no JCalendar começa do 0. Por isso preciso somar 1 pra passar o mes correto para os metodos abaixo
		
		JPanel jPanelDays = jcalendar.getDayChooser().getDayPanel();
		Component component[] = jPanelDays.getComponents(); //componnentes do quadro Dias
		
		ArrayList <RegPonto> pontosDoFuncionario = null;
		try {
			pontosDoFuncionario = EpontoFachada.getInstance().getPontosDoFuncionario(funcionario.getCpf(),mes,ano);
		} catch (FuncionarioNaoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		int diaPrimeiroPonto = pontosDoFuncionario.get(0).getAgora().getDayOfMonth() + 6; //dia do primeiro ponto do funcionario (+6 por causa do painel de Dias que de 0 a 6 representa os nomes dos dias da semana)
		
		int qtdComponentesInvisiveis=0; //qntd de Components Invisiveis da primeira semana do mês (já que nem sempre o dia 1 começa no Domingo, e quando nao começa, os componentes continuam existindo porém invisíveis)
		for (int i=7; i<=13;i++){
			if(!component[i].isVisible())
				qtdComponentesInvisiveis++;
		}
		
//		int comeca = diaPrimeiroPonto+qtdComponentesInvisiveis; //representa o indice do componente em que se deve começar a Colorir
		
//		for (int i = comeca; i < 49; i++) {
//			if( i % 7!=0 && i!=13 && i!=20 && i!=27 && i!=34 && i!=41){ //tirar sabados e domingos, porém, hoje 27/06/2015 as 12h acredito que corrigi. 
		for (int j = 0; j < pontosDoFuncionario.size(); j++){			
			if(pontosDoFuncionario.get(j).chegadaCorreta()){
				int i = pontosDoFuncionario.get(j).getAgora().getDayOfMonth() + qtdComponentesInvisiveis + 6;
				component[i].setBackground(Color.green);
			}
			else if (pontosDoFuncionario.get(j).chegadaAtrasada()){
				int i = pontosDoFuncionario.get(j).getAgora().getDayOfMonth() + qtdComponentesInvisiveis + 6;
					component[i].setBackground(Color.yellow);
				} 
//					component[i].setBackground(Color.red);
		}
	}

//	this.ColorirCalendario(jcalendar.getMonthChooser().getMonth()+1, jcalendar.getYearChooser().getYear());

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		if(e.getSource().equals(jcalendar.getMonthChooser())){
			this.ColorirCalendario(jcalendar.getMonthChooser().getMonth(), jcalendar.getYearChooser().getYear());
		}else if(e.getSource().equals(jcalendar.getYearChooser())){
			this.ColorirCalendario(jcalendar.getMonthChooser().getMonth(), jcalendar.getYearChooser().getYear());
		}
	}
}
