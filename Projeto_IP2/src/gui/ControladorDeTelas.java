package gui;

import java.util.ArrayList;

import javax.swing.JFrame;

import neg�cio.EpontoFachada;
import neg�cio.entity_beans.Funcionario;
import neg�cio.entity_beans.Pessoa;
import neg�cio.entity_beans.RegPonto;

public class ControladorDeTelas extends JFrame {
	private FrameAdmin1 frameAdmin1 = new FrameAdmin1();
	private FrameAdminCadastroEmpresa frameAdminCadEmpresa = new FrameAdminCadastroEmpresa();
	private FrameAdminCadastroFuncionario frameAdminCadFuncionario;
	private FrameLogin frameLogin = new FrameLogin();
	private FrameAdminCadastroAdmin frameCadastroAdmin = new FrameAdminCadastroAdmin();
	
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
	
	public void loginProximaTela (Pessoa pessoa){
		if (pessoa instanceof Funcionario)
			frameFuncionario((Funcionario) pessoa);
		else 
			frameAdmin1();	
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
		frameCadastroAdmin.setVisible(true);
	}
	
}
