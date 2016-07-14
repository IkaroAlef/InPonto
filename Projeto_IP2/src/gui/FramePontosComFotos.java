package gui;

import java.awt.Image;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import negócio.entity_beans.RegPonto;

public class FramePontosComFotos extends JFrame {

	private JPanel contentPane;
	private Image foto[];
	private JLabel lblFoto[];
	private JLabel lblPontos[];
	private static DateTimeFormatter formatadorData =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FramePontosComFotos frame = new FramePontosComFotos();
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
	public FramePontosComFotos(ArrayList<RegPonto> pontos) {
		super("Pontos do dia: " + formatadorData.format(pontos.get(0).getAgora()));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 842, 220);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		int tamanho = pontos.size();
		String ponto[] = new String[tamanho];
		foto = new Image[tamanho];
		lblFoto = new JLabel[tamanho];
		lblPontos = new JLabel[tamanho];
		
		for (int i = 0; i<pontos.size(); i++){
			
			foto[i] = pontos.get(i).getFotoPonto();
			lblFoto[i] = new JLabel(new ImageIcon(foto[i]));
			lblFoto[i].setLocation(5+(i*210), 11);	
			lblFoto[i].setSize(192, 148);
			contentPane.add(lblFoto[i]);
			DateTimeFormatter formatador =  DateTimeFormatter.ofPattern("HH:mm:ss");
			ponto[i] = formatador.format(pontos.get(i).getAgora()); 		
			lblPontos[i] = new JLabel(ponto[i]);
			lblPontos[i].setLocation(80+(i*210), 100);
			lblPontos[i].setSize(190,150);
			contentPane.add(lblPontos[i]);
		}
	}
}

