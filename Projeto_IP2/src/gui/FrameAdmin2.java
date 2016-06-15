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
import qrCode.GerarQRCode;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Font;


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
	private JButton btnQrCode;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameAdmin2 frame = new FrameAdmin2((Funcionario) EpontoFachada.getInstance().getPessoaNome("Ikaro Alef"));
					frame.setVisible(true);
				} catch (FuncionarioNaoEncontradoException e1){
					JOptionPane.showMessageDialog(null, e1.getMessage());
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
		contentPane.setBackground(new Color(169, 169, 169));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		fotoPadrao = funcionario.getFotoPadrao();
		
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
		lblLegenda.setForeground(new Color(255, 255, 255));
		lblLegenda.setToolTipText("Passe o mouse sobre as cores");
		lblLegenda.setBounds(368, 364, 63, 14);
		contentPane.add(lblLegenda);
		
		jcalendar = new JCalendar();
		jcalendar.getDayChooser().getDayPanel().setBackground(new Color(211, 211, 211));
		jcalendar.getDayChooser().setAutoscrolls(true);
		jcalendar.setBounds(10, 11, 500, 350);
		Component[] componentesDias = jcalendar.getDayChooser().getDayPanel().getComponents();
		for (int i = 7; i < 49; i++){
			componentesDias[i].addMouseListener(this);
		}
		jcalendar.getMonthChooser().addPropertyChangeListener("month",this);
		jcalendar.getYearChooser().addPropertyChangeListener("year",this);
		contentPane.add(jcalendar);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(211, 211, 211));
		panel.setBounds(538, 39, 325, 524);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setForeground(new Color(128, 128, 128));
		lblNome.setBounds(27, 247, 46, 14);
		panel.add(lblNome);
		
		JLabel lblCpf = new JLabel("Cpf:");
		lblCpf.setForeground(new Color(128, 128, 128));
		lblCpf.setBounds(27, 266, 46, 14);
		panel.add(lblCpf);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setForeground(new Color(128, 128, 128));
		lblEmail.setBounds(27, 283, 46, 22);
		panel.add(lblEmail);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setForeground(new Color(128, 128, 128));
		lblTelefone.setBounds(27, 307, 63, 14);
		panel.add(lblTelefone);
		
		JLabel lblEmpresa = new JLabel("Empresa:");
		lblEmpresa.setForeground(new Color(128, 128, 128));
		lblEmpresa.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblEmpresa.setBounds(27, 324, 63, 22);
		panel.add(lblEmpresa);
		
		JLabel lblCargo = new JLabel("Cargo:");
		lblCargo.setForeground(new Color(128, 128, 128));
		lblCargo.setBounds(27, 350, 46, 14);
		panel.add(lblCargo);
		
		JLabel lblEscala = new JLabel("Escala:");
		lblEscala.setForeground(new Color(128, 128, 128));
		lblEscala.setBounds(27, 367, 46, 22);
		panel.add(lblEscala);
		
		JLabel lblHorarioChegada = new JLabel("Hor\u00E1rio Chegada:");
		lblHorarioChegada.setForeground(new Color(128, 128, 128));
		lblHorarioChegada.setBounds(27, 392, 102, 14);
		panel.add(lblHorarioChegada);
		
		JLabel lblHorarioSaida = new JLabel("Hor\u00E1rio Sa\u00EDda:");
		lblHorarioSaida.setForeground(new Color(128, 128, 128));
		lblHorarioSaida.setBounds(27, 412, 102, 14);
		panel.add(lblHorarioSaida);
		
		JLabel lblIntervaloSaida = new JLabel("Intervalo Sa\u00EDda:");
		lblIntervaloSaida.setForeground(new Color(128, 128, 128));
		lblIntervaloSaida.setBounds(27, 434, 102, 14);
		panel.add(lblIntervaloSaida);
		
		JLabel lblIntervaloVolta = new JLabel("Intervalo Volta:");
		lblIntervaloVolta.setForeground(new Color(128, 128, 128));
		lblIntervaloVolta.setBounds(27, 459, 85, 14);
		panel.add(lblIntervaloVolta);
		
		JLabel lblNomeFuncionario = new JLabel(funcionario.getNome());
		lblNomeFuncionario.setBounds(139, 247, 165, 14);
		panel.add(lblNomeFuncionario);
		
		JLabel lblCpfFuncionario = new JLabel(funcionario.getCpf());
		lblCpfFuncionario.setBounds(139, 266, 165, 14);
		panel.add(lblCpfFuncionario);
		
		JLabel lblEmailFuncionario = new JLabel(funcionario.getEmail());
		lblEmailFuncionario.setBounds(139, 287, 165, 14);
		panel.add(lblEmailFuncionario);
		
		JLabel lblTelefoneFuncionario = new JLabel(funcionario.getTelefone());
		lblTelefoneFuncionario.setBounds(139, 307, 165, 14);
		panel.add(lblTelefoneFuncionario);
		
		JLabel lblEmpresaFuncionario = new JLabel(funcionario.getEmpresa().getNomeEmpresa());
		lblEmpresaFuncionario.setBounds(139, 328, 165, 14);
		panel.add(lblEmpresaFuncionario);
		
		JLabel lblCargoFuncionario = new JLabel(funcionario.getCargo());
		lblCargoFuncionario.setBounds(139, 350, 165, 14);
		panel.add(lblCargoFuncionario);
		
		JLabel lblEscalaFuncionario = new JLabel(funcionario.getEscala());
		lblEscalaFuncionario.setBounds(139, 371, 165, 14);
		panel.add(lblEscalaFuncionario);
		
		JLabel lblHorarioChegadaFuncionario = new JLabel(funcionario.getChegada().toString());
		lblHorarioChegadaFuncionario.setBounds(139, 392, 165, 14);
		panel.add(lblHorarioChegadaFuncionario);
		
		JLabel lblHorarioSaidaFuncionario = new JLabel(funcionario.getSaida().toString());
		lblHorarioSaidaFuncionario.setBounds(139, 412, 165, 14);
		panel.add(lblHorarioSaidaFuncionario);
		
		JLabel lblIntervaloSaidaFuncionario = new JLabel(funcionario.getIntervalo_out().toString());
		lblIntervaloSaidaFuncionario.setBounds(139, 434, 165, 14);
		panel.add(lblIntervaloSaidaFuncionario);
		
		JLabel lblIntervaloVoltaFuncionario = new JLabel(funcionario.getIntervalo_in().toString());
		lblIntervaloVoltaFuncionario.setBounds(139, 459, 165, 14);
		panel.add(lblIntervaloVoltaFuncionario);
		lblFoto = new JLabel(fotoPadrao);
		lblFoto.setBounds(53, 11, 232, 208);
		panel.add(lblFoto);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(245, 245, 245));
		panel_1.setBounds(538, 11, 325, 28);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblInformaes = new JLabel("INFORMA\u00C7\u00D5ES");
		lblInformaes.setForeground(new Color(0, 0, 0));
		lblInformaes.setBounds(133, 0, 107, 25);
		panel_1.add(lblInformaes);
		
		JPanel panel_2 = new JPanel();
		panel_2.setForeground(new Color(169, 169, 169));
		panel_2.setBackground(new Color(169, 169, 169));
		panel_2.setBounds(53, 432, 433, 130);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(225, 121, -39, -108);
		panel_2.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(0, 0, 0));
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(225, 101, -21, -56);
		panel_2.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setForeground(new Color(192, 192, 192));
		separator_2.setBounds(205, 11, 10, 108);
		panel_2.add(separator_2);
		
		btnIniciarFerias = new JButton("Iniciar Férias ou Licença");
		btnIniciarFerias.setForeground(new Color(0, 0, 0));
		btnIniciarFerias.setBounds(230, 22, 185, 23);
		btnIniciarFerias.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_2.add(btnIniciarFerias);
		
		btnEditar = new JButton("Editar");
		btnEditar.setForeground(new Color(0, 0, 0));
		btnEditar.setBounds(230, 56, 185, 23);
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_2.add(btnEditar);
		
		JLabel lblTotalFaltas = new JLabel("Total Faltas: ");
		lblTotalFaltas.setForeground(new Color(255, 0, 0));
		lblTotalFaltas.setBounds(20, 42, 96, 18);
		panel_2.add(lblTotalFaltas);
		
		JLabel lblTotalComAtrasos = new JLabel("Total Com Atrasos: ");
		lblTotalComAtrasos.setForeground(new Color(255, 255, 0));
		lblTotalComAtrasos.setBounds(20, 80, 119, 14);
		panel_2.add(lblTotalComAtrasos);
		
		JLabel lblTotalSemAtrasos = new JLabel("Total Sem Atrasos: ");
		lblTotalSemAtrasos.setForeground(new Color(0, 128, 0));
		lblTotalSemAtrasos.setBounds(20, 57, 119, 23);
		panel_2.add(lblTotalSemAtrasos);
		
		lblTotalRed = new JLabel();
		lblTotalRed.setBounds(101, 44, 25, 14);
		panel_2.add(lblTotalRed);
		
		lblTotalYellow = new JLabel();
		lblTotalYellow.setBounds(135, 80, 46, 14);
		panel_2.add(lblTotalYellow);
		
		lblTotalGreen = new JLabel();
		lblTotalGreen.setBounds(135, 58, 46, 18);
		panel_2.add(lblTotalGreen);
		
		btnQrCode = new JButton("Gerar QRCode");
		btnQrCode.setForeground(new Color(0, 0, 0));
		btnQrCode.setBounds(230, 90, 187, 23);
		panel_2.add(btnQrCode);
		btnQrCode.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnQrCode.addActionListener(this);
		
		btnEditar.addActionListener(this);
		btnIniciarFerias.addActionListener(this);
		this.ColorirCalendario(jcalendar.getMonthChooser().getMonth(),jcalendar.getYearChooser().getYear());
		}
	
	public void ColorirCalendario(int mes, int ano){
		mes++; //o array de dias no JCalendar começa do 0. Por isso preciso somar 1 pra passar o mes correto para os metodos abaixo
		
		JPanel jPanelDays = jcalendar.getDayChooser().getDayPanel();
		Component component[] = jPanelDays.getComponents(); //componnentes do quadro Dias
		
		int qtdComponentesInvisiveis=0; //qntd de Components Invisiveis da primeira semana do mês (já que nem sempre o dia 1 começa no Domingo, e quando nao começa, os componentes continuam existindo porém invisíveis)
		for (int i=7; i<=13;i++){
			if(!component[i].isVisible())
				qtdComponentesInvisiveis++;
		}
		
		int totalGreen = 0; //armazenará o total de dias sem atraso no mes e ano
		int totalYellow = 0; //armazenará o total de dias cem atraso no mes e ano
		lblTotalGreen.setText("0");
		lblTotalYellow.setText("0");
		lblTotalRed.setText("0");
		
		ArrayList <RegPonto> pontosDoFuncionario = null;
		try {
			pontosDoFuncionario = EpontoFachada.getInstance().getPontosDoFuncionario(funcionario.getCpf(),mes,ano);
			
			 if(EpontoFachada.getInstance().dispensaContains(funcionario)){
					ArrayList <Dispensa> dispensas = EpontoFachada.getInstance().getDispensas(funcionario);
					for (int j = 0; j < dispensas.size(); j++){
						LocalDateTime dataInicio = dispensas.get(j).getInicio();
						int qtdDias = dispensas.get(j).getQtdDias();
						int inicio = dataInicio.getDayOfMonth() + 6 + qtdComponentesInvisiveis;
						int fim = dataInicio.plusDays(qtdDias-1).getDayOfMonth() + 6 + qtdComponentesInvisiveis; //-1 pq o primeiro dia já conta como dispensa.
					/*
						if(mes == dataInicio.getMonthValue()){
							component[inicio].setBackground(Color.blue);
						}
						if(mes == dataInicio.plusDays(qtdDias).getMonthValue()){
							component[fim].setBackground(Color.blue);
						}
						*/
						
						if (mes == dataInicio.getMonthValue()){
							if (dataInicio.getMonthValue() > dataInicio.plusDays(qtdDias).getMonthValue()){
								for(int i = inicio ; i < fim ; i++)
									component[i].setBackground(Color.blue);
							}else
								for(int i = inicio ; i < 49 ; i++)
									component[i].setBackground(Color.blue);
							
						}else if (mes == dataInicio.plusDays(qtdDias).getMonthValue()){
							for(int i = 7 ; i < fim ; i++)
								component[i].setBackground(Color.blue);
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
							if(EpontoFachada.getInstance().isDiaCorreto(funcionario.getCpf(), pontosDoFuncionario.get(j).getAgora().getDayOfMonth(), mes, ano)){
								int i = pontosDoFuncionario.get(j).getAgora().getDayOfMonth() + qtdComponentesInvisiveis + 6;
								component[i].setBackground(Color.green);
							}
							else if (EpontoFachada.getInstance().isDiaAtrasado(funcionario.getCpf(), pontosDoFuncionario.get(j).getAgora().getDayOfMonth(), mes, ano)){
								int i = pontosDoFuncionario.get(j).getAgora().getDayOfMonth() + qtdComponentesInvisiveis + 6;
								component[i].setBackground(Color.yellow);
							}
						
					}
					if(funcionario.getEscala().equals("Seg. à Sex")){
						int max=49;
						if(EpontoFachada.getInstance().getPontosDoFuncionario(funcionario.getCpf(), mes-1, ano).isEmpty())
							comeca = diaPrimeiroPonto + qtdComponentesInvisiveis;
						else 
							comeca = 7;
						
						if(mes==LocalDateTime.now().getMonthValue() )
							max = LocalDateTime.now().getDayOfMonth()+6+qtdComponentesInvisiveis;
							
						for (int i = comeca; i < max; i++) {
							if( i % 7!=0  //tirar sabados e domingos e componentes ja coloridos
								&& i!=13 
								&& i!=20 
								&& i!=27 
								&& i!=34 
								&& i!=41 
								&& !component[i].getBackground().equals(Color.green) 
								&& !component[i].getBackground().equals(Color.yellow)
								&& !component[i].getBackground().equals(Color.blue)) 
								component[i].setBackground(Color.red);	
							
						}
					}
					//Mostrar a quantidade Total de pontos corretos, atrasos e faltas.
					
					totalGreen = EpontoFachada.getInstance().getTotalDiasCorretos(this.funcionario.getCpf(), mes, ano);
					totalYellow = EpontoFachada.getInstance().getTotalDiasAtrasado(this.funcionario.getCpf(), mes, ano);
					int totalRed=0;
					int qtdComponentesInvUltimaLinha=0; //qntd de Components Invisiveis da primeira semana do mês (já que nem sempre o dia 1 começa no Domingo, e quando nao começa, os componentes continuam existindo porém invisíveis)
					for (int i=35; i<49;i++){
						if(!component[i].isVisible())
							qtdComponentesInvUltimaLinha++;
					}
					
					for (int i = 7 + qtdComponentesInvisiveis; i < 49 - qtdComponentesInvUltimaLinha; i++){
						if(component[i].getBackground().equals(Color.red))
							totalRed++;
					}
					lblTotalGreen.setText(String.valueOf(totalGreen));
					lblTotalYellow.setText(String.valueOf(totalYellow));
					lblTotalRed.setText(String.valueOf(totalRed));
					}
		}catch (FuncionarioNaoEncontradoException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
		
		}
		
	@Override
	public void propertyChange(PropertyChangeEvent e) { 
		if(e.getSource().equals(jcalendar.getMonthChooser())){ //para atualizar o calendario caso o mes seja mudado
			this.ColorirCalendario(jcalendar.getMonthChooser().getMonth(), jcalendar.getYearChooser().getYear());
		}else{ 
			if(e.getSource().equals(jcalendar.getYearChooser())){ //para atualizar o calendario caso o ano seja mudado
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
		else if(arg0.getSource().equals(btnQrCode)){
			try {
				GerarQRCode.gerar(funcionario);
				JOptionPane.showMessageDialog(null, "QRCode gerado com sucesso na sua pasta de Imagens.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
					JOptionPane.showMessageDialog(null, e1.getMessage());
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
