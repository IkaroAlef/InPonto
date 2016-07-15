package gui;

import java.awt.EventQueue;
import java.awt.SplashScreen;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import negócio.entity_beans.Admin;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

import conexaoBD.FabricaDeConexao;
import negócio.entity_beans.Funcionario;
import negócio.entity_beans.Pessoa;
import negócio.entity_beans.RegPonto;

public class ControladorDeTelas extends JFrame {

	private static final long serialVersionUID = 1L;
	private FrameAdmin1 frameAdmin1;
	private FrameAdminCadastroEmpresa frameAdminCadEmpresa = new FrameAdminCadastroEmpresa();
	private FrameAdminCadastroFuncionario frameAdminCadFuncionario;
	private FrameLogin frameLogin;
	private WebcamQRCodeExample frameQR;
	private FrameAdminCadastroProjeto frameAdminCadProjeto = new FrameAdminCadastroProjeto();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		JWindow window = new JWindow();
		try {
			window.getContentPane().add(
			    new JLabel("", new ImageIcon(new URL("https://pbs.twimg.com/profile_images/2643407135/bbae446e0aa3fcf8918d892f2f3f694b.png")), SwingConstants.CENTER));
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		window.setBounds(500, 150, 300, 200);
		window.setVisible(true);
		try {
		    Thread.sleep(5000);
		} catch (InterruptedException e) {
			
		}
		
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						ControladorDeTelas.getInstance().frameLogin = new FrameLogin();
						ControladorDeTelas.getInstance().frameQR = new WebcamQRCodeExample();
						ControladorDeTelas.getInstance().frameLogin.setVisible(true);
						ControladorDeTelas.getInstance().frameQR.setVisible(true);
						window.dispose();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
	}

	
	public static ControladorDeTelas instance;
	
	public static ControladorDeTelas getInstance(){
		if(instance==null)
			instance = new ControladorDeTelas();
		
		return instance;
	}
	
	private ControladorDeTelas(){
		
	}
	
	public void frameLogin(){
		this.frameLogin.setVisible(true);
	}
	
	public void offLogin(){
		this.frameLogin.setVisible(false);
		this.frameLogin.dispose();
	}
	
	public void offQR(){
		this.frameQR.setVisible(false);
		this.frameQR.dispose();
	}
	
	public void loginProximaTela (Pessoa pessoa){
		boolean logou = false;
		String cpf = pessoa.getCpf();
		System.out.println(cpf);
		String dbCPF;
		try {
			FabricaDeConexao bd = new FabricaDeConexao();
            Connection con = bd.getConexao("login", "bancodedados");
//            Statement stmt = (Statement) con.createStatement();
            ResultSet rsFunc = con.createStatement().executeQuery("SELECT CPF FROM FUNCIONARIO; ");
            ResultSet rsGerente = con.createStatement().executeQuery("SELECT CPF FROM GERENTE; ");
            ResultSet rsCoord = con.createStatement().executeQuery("SELECT CPF FROM COORDENADOR; ");
            while (logou==false && rsFunc.next()){
            	dbCPF = rsFunc.getString("CPF");
            	if (dbCPF.equals(cpf)){
            		logou = true;
            		this.offLogin();
            		this.frameLogin.setVisible(false);
            		this.frameLogin.dispose();
            		frameFuncionario((Funcionario) pessoa);
            		break;
            	}
            }
            while (logou==false && rsGerente.next()){
            	while (rsGerente.next()){
                	dbCPF = rsGerente.getString("CPF");
                	if (dbCPF.equals(cpf)){
                		logou = true;
                		this.frameLogin.setVisible(false);
                		this.frameLogin.dispose();
                		new FrameAdmin1((Admin) pessoa).setVisible(true);
                		break;
                	}
                }
            }
            while (logou==false && rsCoord.next()){
            	while (rsCoord.next()){
                	dbCPF = rsCoord.getString("CPF");
                	if (dbCPF.equals(cpf)){
                		logou = true;
                		this.frameLogin.setVisible(false);
                		this.frameLogin.dispose();
                		new FrameAdmin1((Admin) pessoa).setVisible(true);
                		break;
                	}
                }
            }
            rsFunc.close();
            rsGerente.close();
            rsCoord.close();
            con.close();
            
//		if (pessoa instanceof Funcionario)
//			frameFuncionario((Funcionario) pessoa);
//		else 
//			new FrameAdmin1((Admin) pessoa).setVisible(true);	
		}catch (SQLException e){
			
		}
	}
	public void frameFuncionario(Funcionario funcionario){
		new FrameFuncionario(funcionario).setVisible(true);
	}
	
	public void frameAdmin1 (){
		frameAdmin1.setVisible(true);
	}
	
	public void frameCadastrarEmpresa (){
		frameAdminCadEmpresa.setVisible(true);
	}
	
	public void frameCadastrarProjeto(){
		frameAdminCadProjeto.setVisible(true);
	}
	
	public void frameCadastrarFuncionario(){
		frameAdminCadFuncionario = new FrameAdminCadastroFuncionario();
		frameAdminCadFuncionario.setVisible(true);
	}
	
	public void frameAdmin2(Funcionario funcionario){
		new FrameAdmin2(funcionario).setVisible(true);
	}	
	
	public void framePontosComFotos(ArrayList<RegPonto> pontos){
		new FramePontosComFotos(pontos).setVisible(true);
	}
	
	public void frameEditarFuncionario (Funcionario funcionario){
		new FrameAdminEditarFuncionario(funcionario).setVisible(true);
	}
	
	public void frameCadastrarAdmin(){
		new FrameAdminCadastroAdmin().setVisible(true);
	}
	
	public void frameEditarAdmin(Admin admin){
		new FrameAdminEditarAdmin(admin).setVisible(true);
	}
	
	
}
