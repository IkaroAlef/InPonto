package gui;

import javax.swing.JFrame;

import negócio.entity_beans.Funcionario;

public class ControladorDeTelas extends JFrame {

	public static void LoginToAdm (){
		
		FrameAdmin1 frame;
//		FrameLogin frame2;
		try {
			frame = new FrameAdmin1();
//			frame2= new FrameLogin();
			frame.setVisible(true);
//			frame2.setVisible(false);
			
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} 
	}
	
	public static void Admin1ToAdminCadastrarFuncionario(){
		FrameAdminCadastroFuncionario frame2;
		try {
			frame2= new FrameAdminCadastroFuncionario();
			frame2.setVisible(true);
			
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} 
	}
	
	public static void CadastrarEmpresa (){
		FrameAdminCadastroEmpresa frame;
		try {
			frame= new FrameAdminCadastroEmpresa();
			frame.setVisible(true);
			
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}	
	}
	
	public static void Admin2(Funcionario funcionario){
		FrameAdmin2 frame;
		try {
			frame= new FrameAdmin2(funcionario);
			frame.setVisible(true);
			
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}	
	}	
	
}
