package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import com.toedter.calendar.JCalendar;

import dados.exceptionsDados.FuncionarioNaoEncontradoException;
import negócio.EpontoFachada;
import negócio.entity_beans.Dispensa;
import negócio.entity_beans.Funcionario;
import negócio.entity_beans.RegPonto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JTextField;


public class FrameAdmin2 extends JFrame implements PropertyChangeListener, ActionListener, MouseListener, WindowListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JCalendar jcalendar;
	private Funcionario funcionario;
	private JButton btnIniciarFerias;
	private JButton btnEditar;
	private ImageIcon fotoPadrao;
	private JLabel lblFoto;
	private JTextField txtGreen;
	private JTextField txtYellow;
	private JTextField txtRed;
	private JTextField txtBlue;
	private JLabel lblTotalGreen;
	private JLabel lblTotalYellow;
	private JLabel lblTotalRed;
	
	
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
		setBounds(100, 100, 900, 658);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		fotoPadrao = funcionario.getFotoPadrao();
		lblFoto = new JLabel(fotoPadrao);
		lblFoto.setLocation(551, 9);
		lblFoto.setSize(323, 277);
		contentPane.add(lblFoto);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(606, 308, 46, 14);
		contentPane.add(lblNome);
		
		JLabel lblNomeFuncionario = new JLabel(funcionario.getNome());
		lblNomeFuncionario.setBounds(679, 308, 165, 14);
		contentPane.add(lblNomeFuncionario);
		
		JLabel lblCpf = new JLabel("Cpf:");
		lblCpf.setBounds(616, 333, 46, 14);
		contentPane.add(lblCpf);
		
		JLabel lblCpfFuncionario = new JLabel(funcionario.getCpf());
		lblCpfFuncionario.setBounds(679, 333, 165, 14);
		contentPane.add(lblCpfFuncionario);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(606, 358, 46, 14);
		contentPane.add(lblEmail);
		
		JLabel lblEmailFuncionario = new JLabel(funcionario.getEmail());
		lblEmailFuncionario.setBounds(679, 358, 165, 14);
		contentPane.add(lblEmailFuncionario);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(589, 383, 63, 14);
		contentPane.add(lblTelefone);
		
		JLabel lblTelefoneFuncionario = new JLabel(funcionario.getTelefone());
		lblTelefoneFuncionario.setBounds(679, 383, 165, 14);
		contentPane.add(lblTelefoneFuncionario);
		
		JLabel lblEmpresa = new JLabel("Empresa:");
		lblEmpresa.setBounds(589, 408, 63, 14);
		contentPane.add(lblEmpresa);
		
		JLabel lblEmpresaFuncionario = new JLabel(funcionario.getEmpresa().getNomeEmpresa());
		lblEmpresaFuncionario.setBounds(679, 408, 165, 14);
		contentPane.add(lblEmpresaFuncionario);
		
		JLabel lblCargo = new JLabel("Cargo:");
		lblCargo.setBounds(606, 433, 46, 14);
		contentPane.add(lblCargo);
		
		JLabel lblCargoFuncionario = new JLabel(funcionario.getCargo());
		lblCargoFuncionario.setBounds(679, 433, 165, 14);
		contentPane.add(lblCargoFuncionario);
		
		JLabel lblEscala = new JLabel("Escala:");
		lblEscala.setBounds(606, 458, 46, 14);
		contentPane.add(lblEscala);
		
		JLabel lblEscalaFuncionario = new JLabel(funcionario.getEscala());
		lblEscalaFuncionario.setBounds(679, 458, 165, 14);
		contentPane.add(lblEscalaFuncionario);
		
		JLabel lblHorarioChegada = new JLabel("Hor\u00E1rio Chegada:");
		lblHorarioChegada.setBounds(551, 483, 102, 14);
		contentPane.add(lblHorarioChegada);
		
		JLabel lblHorarioChegadaFuncionario = new JLabel(funcionario.getChegada().toString());
		lblHorarioChegadaFuncionario.setBounds(679, 483, 165, 14);
		contentPane.add(lblHorarioChegadaFuncionario);
		
		JLabel lblHorarioSaida = new JLabel("Hor\u00E1rio Sa\u00EDda:");
		lblHorarioSaida.setBounds(567, 508, 102, 14);
		contentPane.add(lblHorarioSaida);
		
		JLabel lblHorarioSaidaFuncionario = new JLabel(funcionario.getSaida().toString());
		lblHorarioSaidaFuncionario.setBounds(679, 508, 165, 14);
		contentPane.add(lblHorarioSaidaFuncionario);
		
		JLabel lblIntervaloSaida = new JLabel("Intervalo Sa\u00EDda:");
		lblIntervaloSaida.setBounds(567, 533, 102, 14);
		contentPane.add(lblIntervaloSaida);
		
		JLabel lblIntervaloSaidaFuncionario = new JLabel(funcionario.getIntervalo_out().toString());
		lblIntervaloSaidaFuncionario.setBounds(679, 533, 165, 14);
		contentPane.add(lblIntervaloSaidaFuncionario);
		
		JLabel lblIntervaloVolta = new JLabel("Intervalo Volta:");
		lblIntervaloVolta.setBounds(567, 558, 85, 14);
		contentPane.add(lblIntervaloVolta);
		
		JLabel lblIntervaloVoltaFuncionario = new JLabel(funcionario.getIntervalo_in().toString());
		lblIntervaloVoltaFuncionario.setBounds(679, 558, 165, 14);
		contentPane.add(lblIntervaloVoltaFuncionario);
		
		jcalendar = new JCalendar();
		jcalendar.getDayChooser().setAutoscrolls(true);
		jcalendar.setBounds(10, 11, 500, 350);
		Component[] componentesDias = jcalendar.getDayChooser().getDayPanel().getComponents();
		for (int i = 7; i < 49; i++){
			componentesDias[i].addMouseListener(this);
		}
		jcalendar.getMonthChooser().addPropertyChangeListener(this);
		jcalendar.getYearChooser().addPropertyChangeListener(this);
		this.ColorirCalendario(jcalendar.getMonthChooser().getMonth(),jcalendar.getYearChooser().getYear());
		contentPane.add(jcalendar);
		
		btnIniciarFerias = new JButton("Iniciar Férias ou Licença");
		btnIniciarFerias.setBounds(563, 585, 177, 23);
		btnIniciarFerias.addActionListener(this);
		contentPane.add(btnIniciarFerias);
		
		txtGreen = new JTextField();
		txtGreen.setEditable(false);
		txtGreen.setToolTipText("Sem Atrasos");
		txtGreen.setBounds(423, 364, 14, 14);
		contentPane.add(txtGreen);
		txtGreen.setColumns(10);
		txtGreen.setBackground(Color.green);
		
		txtYellow = new JTextField();
		txtYellow.setEditable(false);
		txtYellow.setToolTipText("Com atrasos");
		txtYellow.setColumns(10);
		txtYellow.setBackground(Color.YELLOW);
		txtYellow.setBounds(448, 364, 14, 14);
		contentPane.add(txtYellow);
		
		txtRed = new JTextField();
		txtRed.setEditable(false);
		txtRed.setToolTipText("Faltou");
		txtRed.setColumns(10);
		txtRed.setBackground(Color.RED);
		txtRed.setBounds(472, 364, 14, 14);
		contentPane.add(txtRed);
		
		txtBlue = new JTextField();
		txtBlue.setEditable(false);
		txtBlue.setToolTipText("F\u00E9rias ou Dispensa");
		txtBlue.setColumns(10);
		txtBlue.setBackground(Color.BLUE);
		txtBlue.setBounds(496, 364, 14, 14);
		contentPane.add(txtBlue);
		
		JLabel lblLegenda = new JLabel("Legenda: ");
		lblLegenda.setToolTipText("Passe o mouse em cima das cores");
		lblLegenda.setBounds(350, 364, 63, 14);
		contentPane.add(lblLegenda);
		
		JLabel lblTotalSemAtrasos = new JLabel("Total Sem Atrasos: ");
		lblTotalSemAtrasos.setBounds(10, 383, 118, 14);
		contentPane.add(lblTotalSemAtrasos);
		
		JLabel lblTotalComAtrasos = new JLabel("Total Com Atrasos: ");
		lblTotalComAtrasos.setBounds(10, 408, 118, 14);
		contentPane.add(lblTotalComAtrasos);
		
		JLabel lblTotalFaltas = new JLabel("Total Faltas: ");
		lblTotalFaltas.setBounds(10, 433, 118, 14);
		contentPane.add(lblTotalFaltas);
		
		lblTotalGreen = new JLabel("");
		lblTotalGreen.setBounds(138, 383, 46, 14);
		contentPane.add(lblTotalGreen);
		
		lblTotalYellow = new JLabel("");
		lblTotalYellow.setBounds(138, 408, 46, 14);
		contentPane.add(lblTotalYellow);
		
		lblTotalRed = new JLabel("");
		lblTotalRed.setBounds(138, 433, 46, 14);
		contentPane.add(lblTotalRed);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(755, 583, 89, 23);
		btnEditar.addActionListener(this);
		contentPane.add(btnEditar);
		
		
		}
	
	public void ColorirCalendario(int mes, int ano){
		mes++; //o array de dias no JCalendar começa do 0. Por isso preciso somar 1 pra passar o mes correto para os metodos abaixo
		
		JPanel jPanelDays = jcalendar.getDayChooser().getDayPanel();
		Component component[] = jPanelDays.getComponents(); //componnentes do quadro Dias
		
		ArrayList <RegPonto> pontosDoFuncionario = null;
		try {
			pontosDoFuncionario = EpontoFachada.getInstance().getPontosDoFuncionario(funcionario.getCpf(),mes,ano);
		} catch (FuncionarioNaoEncontradoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally{
			
			int qtdComponentesInvisiveis=0; //qntd de Components Invisiveis da primeira semana do mês (já que nem sempre o dia 1 começa no Domingo, e quando nao começa, os componentes continuam existindo porém invisíveis)
			for (int i=7; i<=13;i++){
				if(!component[i].isVisible())
					qtdComponentesInvisiveis++;
			}
			
			 if(EpontoFachada.getInstance().dispensaContains(funcionario)){
//					System.out.println("teste");
					ArrayList <Dispensa> dispensas = EpontoFachada.getInstance().getDispensas(funcionario);
					for (int j = 0; j < dispensas.size(); j++){
						LocalDateTime dataInicio = dispensas.get(j).getInicio();
						int qtdDias = dispensas.get(j).getQtdDias();
						int inicio = dataInicio.getDayOfMonth() + 6 + qtdComponentesInvisiveis;
						int fim = dataInicio.plusDays(qtdDias).getDayOfMonth() + 6 + qtdComponentesInvisiveis;
					
						if(mes == dataInicio.getMonthValue()){
							component[inicio].setBackground(Color.blue);
						}
						if(mes == dataInicio.plusDays(qtdDias).getMonthValue()){
							component[fim].setBackground(Color.blue);
						}
					}
				}
			
			if (pontosDoFuncionario.size()==0){
				JOptionPane.showMessageDialog(null, "Nao há pontos registrados nesse mês e ano.");
			
			}else{
					int diaPrimeiroPonto = pontosDoFuncionario.get(0).getAgora().getDayOfMonth() + 6; //dia do primeiro ponto do funcionario (+6 por causa do painel de Dias que de 0 a 6 representa os nomes dos dias da semana)
					
					int comeca = 7;
					if(pontosDoFuncionario.get(0).getAgora().getMonthValue()==mes){
						comeca = diaPrimeiroPonto + qtdComponentesInvisiveis; //representa o indice do componente em que se deve começar a Colorir
					}
					for (int j = 0; j < pontosDoFuncionario.size(); j+=4){			
						try {
							if(EpontoFachada.getInstance().isDiaCorreto(funcionario.getCpf(), pontosDoFuncionario.get(j).getAgora().getDayOfMonth(), mes, ano)){
								int i = pontosDoFuncionario.get(j).getAgora().getDayOfMonth() + qtdComponentesInvisiveis + 6;
								component[i].setBackground(Color.green);
							}
							else if (EpontoFachada.getInstance().isDiaAtrasado(funcionario.getCpf(), pontosDoFuncionario.get(j).getAgora().getDayOfMonth(), mes, ano)){
								int i = pontosDoFuncionario.get(j).getAgora().getDayOfMonth() + qtdComponentesInvisiveis + 6;
								component[i].setBackground(Color.yellow);
							}
						} catch (FuncionarioNaoEncontradoException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
					}
					if(funcionario.getEscala().equals("Seg. à Sex")){
						int max=49;
						if(jcalendar.getMonthChooser().getMonth()+1==LocalDateTime.now().getMonthValue())
							max = LocalDateTime.now().getDayOfMonth()+6+qtdComponentesInvisiveis;
							comeca = diaPrimeiroPonto + qtdComponentesInvisiveis;
						for (int i = comeca; i < max; i++) {
							if( i % 7!=0  //tirar sabados e domingos
								&& i!=13 
								&& i!=20 
								&& i!=27 
								&& i!=34 
								&& i!=41 
								&& !component[i].getBackground().equals(Color.green) 
								&& !component[i].getBackground().equals(Color.yellow)) 
								component[i].setBackground(Color.red);	
							
						}
					}
					//Mostrar a quantidade Total de pontos corretos, atrasos e faltas.
					int totalGreen=0, totalYellow=0, totalRed=0;
					int qtdComponentesInvUltimaLinha=0; //qntd de Components Invisiveis da primeira semana do mês (já que nem sempre o dia 1 começa no Domingo, e quando nao começa, os componentes continuam existindo porém invisíveis)
					for (int i=35; i<49;i++){
						if(!component[i].isVisible())
							qtdComponentesInvUltimaLinha++;
					}
					
					for (int i = 7 + qtdComponentesInvisiveis; i < 49 - qtdComponentesInvUltimaLinha; i++){
						if(component[i].getBackground().equals(Color.green))
							totalGreen++;
						else if(component[i].getBackground().equals(Color.yellow))
							totalYellow++;
						else if(component[i].getBackground().equals(Color.red))
							totalRed++;
					}
//System.out.println(String.valueOf(totalGreen + "\n"));
//					lblTotalGreen.setText(String.valueOf(totalGreen));
//					lblTotalYellow.setText(String.valueOf(totalYellow));
//					lblTotalRed.setText(String.valueOf(totalRed));
					}
			}
		}
		
	@Override
	public void propertyChange(PropertyChangeEvent e) {
		if(e.getSource().equals(jcalendar.getMonthChooser())){
			this.ColorirCalendario(jcalendar.getMonthChooser().getMonth(), jcalendar.getYearChooser().getYear());
		}else{ 
			if(e.getSource().equals(jcalendar.getYearChooser())		){
				this.ColorirCalendario(jcalendar.getMonthChooser().getMonth(), jcalendar.getYearChooser().getYear());
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource().equals(btnIniciarFerias)){
			DateTimeFormatter formatador =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
			int qtdDias = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de Dias: "));
			Dispensa dispensa = new Dispensa(funcionario, LocalDateTime.now().plusDays(1) ,qtdDias);
			EpontoFachada.getInstance().adicionarDispensa(dispensa);
			JOptionPane.showMessageDialog(null, "Dispensa adicionada com sucesso. Data de retorno das férias: "+ formatador.format(LocalDateTime.now().plusDays(qtdDias+1)));
		}
		else if(arg0.getSource().equals(btnEditar)){
			ControladorDeTelas.getInstance().frameEditarFuncionario(funcionario);
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		if (e.getClickCount() == 2) {
				JPanel jPanelDays = jcalendar.getDayChooser().getDayPanel();
				Component components[] = jPanelDays.getComponents(); //componentes do quadro Dias
				
				int qtdComponentesInvisiveis=0; //qntd de Components Invisiveis da primeira semana do mês (já que nem sempre o dia 1 começa no Domingo, e quando nao começa, os componentes continuam existindo porém invisíveis)
				for (int i=7; i<=13;i++){
					if(!components[i].isVisible())
						qtdComponentesInvisiveis++;
				}
				int dia = jcalendar.getDayChooser().getDay();
				int mes = jcalendar.getMonthChooser().getMonth() +1; //+1 porque o getMonth considera Janeiro como 0.
				int ano = jcalendar.getYearChooser().getYear();
				
				ArrayList<RegPonto> pontos = null;
				try {
					pontos = EpontoFachada.getInstance().getPontosDoFuncionario(funcionario.getCpf(), dia, mes, ano);
				} catch (FuncionarioNaoEncontradoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if(pontos.size()==0) //se nao houver pontos registrados
					JOptionPane.showMessageDialog(null, "Não há pontos registrados neste dia.", "Pontos", JOptionPane.INFORMATION_MESSAGE);
				
				/*
				else if(components[dia+6+qtdComponentesInvisiveis].getBackground().equals(Color.red)){ //se faltou  **** não está funcionando, quando dou um click num dia, ele muda a cor.
					System.out.println("vermelho");
					JOptionPane.showMessageDialog(null, "O funcionario faltou neste dia.", "Pontos", JOptionPane.INFORMATION_MESSAGE);
				}
				*/
				else{
					ControladorDeTelas.getInstance().framePontosComFotos(pontos);
					//CHAMAR AQUI
					//
					//
					//
					//A TELA PONTOS COM FOTOS
					}
				this.ColorirCalendario(mes-1, ano); //-1 pq neste metodo eu somo 1, e no metodo Colorir soma-se 1 ao mes (ou seja, ficaria +2)
			}
		}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
			
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
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
