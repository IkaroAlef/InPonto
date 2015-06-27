package gui;

import javax.swing.JFrame;

import negócio.EpontoFachada;
import negócio.entity_beans.Funcionario;
import negócio.entity_beans.Pessoa;

public class ControladorDeTelas extends JFrame {
	public static FrameAdmin1 frameAdmin1 = new FrameAdmin1();
	public static FrameAdminCadastroEmpresa frameAdminCadEmpresa = new FrameAdminCadastroEmpresa();
	public static FrameAdminCadastroFuncionario frameAdminCadFuncionario = new FrameAdminCadastroFuncionario();
	
	public static ControladorDeTelas instance;
	
	public static ControladorDeTelas getInstance(){
		if(instance==null)
			instance = new ControladorDeTelas();
		
		return instance;
	}
	
	private ControladorDeTelas(){
		
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
		frameAdminCadFuncionario.setVisible(true);
	}
	
	public void frameAdmin2(Funcionario funcionario){
		new FrameAdmin2(funcionario).setVisible(true);
	}	
	
}
