package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import dados.exceptionsDados.FuncionarioNaoEncontradoException;
import negócio.EpontoFachada;
import negócio.entity_beans.exceptionsBeans.NomeInvalidoException;
import qrCode.GerarQRCode;


public class WebcamQRCodeExample extends JFrame implements Runnable, ThreadFactory {

	private static final long serialVersionUID = 6441489157408381878L;

	private Executor executor = Executors.newSingleThreadExecutor(this);

	private Webcam webcam = null;
	private WebcamPanel panel = null;
	
	
	private String a;

	public WebcamQRCodeExample() {
		super();

		getContentPane().setLayout(new FlowLayout());
		setTitle("Login por QRCode");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension size = WebcamResolution.QVGA.getSize();

//		a = null;
		webcam = Webcam.getWebcams().get(0);
		webcam.setViewSize(size);

		panel = new WebcamPanel(webcam);
		panel.setPreferredSize(size);

		getContentPane().add(panel);

		pack();
		setVisible(true);

		executor.execute(this);
		
	}
	
	public void closeWeb(){
		this.webcam.close();
	}
	

	@Override
	public void run() {

		do {
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			Result result = null;
			BufferedImage image = null;

			if (webcam.isOpen()) {

				if ((image = webcam.getImage()) == null) {
					continue;
				}

				LuminanceSource source = new BufferedImageLuminanceSource(image);
				BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

				try {
					result = new MultiFormatReader().decode(bitmap);
				} catch (NotFoundException e) {
					// fall thru, it means there is no QR code in image
				}
			}

			if (result != null) {
				String aux;
				aux = GerarQRCode.descriptografar(result.getText());
				this.setPessoa(aux);
				StringTokenizer st = new StringTokenizer(this.getPessoa(), "+", false);
				String cpf = null;
				char[] senha = null;
				while(st.hasMoreTokens()){
					cpf = st.nextToken();
					senha = st.nextToken().toCharArray();
				}
				try {
					if(EpontoFachada.getInstance().validarLogin(cpf, senha)){
						webcam.close();
						ControladorDeTelas.getInstance().offLogin();
						JOptionPane.showMessageDialog(null, "Login efetuado com sucesso.");
						ControladorDeTelas.getInstance().loginProximaTela(EpontoFachada.getInstance().getPessoaCpf(cpf));
						this.setVisible(false);
						ControladorDeTelas.getInstance().offLogin();
					}
				} catch (FuncionarioNaoEncontradoException | NomeInvalidoException | IOException e) {
					JOptionPane.showMessageDialog(null, "Funcionário não encontrado");
				}
				
			}

		} while (true);
	}

	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r, "example-runner");
		t.setDaemon(true);
		return t;
	}
	
	public void setPessoa(String a){
		this.a = a;
	}
	
	public String getPessoa(){
		return this.a;
	}

	public static void main(String[] args) {
		new WebcamQRCodeExample();
	}
}
