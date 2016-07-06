package gui;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import negócio.entity_beans.Admin;

import javax.swing.JFrame;

import com.mysql.cj.api.jdbc.Statement;

import conexaoBD.FabricaDeConexao;
import negócio.entity_beans.Funcionario;
import negócio.entity_beans.Pessoa;
import negócio.entity_beans.RegPonto;

public class ControladorDeTelas extends JFrame {
	private FrameAdmin1 frameAdmin1;
	private FrameAdminCadastroEmpresa frameAdminCadEmpresa = new FrameAdminCadastroEmpresa();
	private FrameAdminCadastroFuncionario frameAdminCadFuncionario;
	private FrameLogin frameLogin = new FrameLogin();
	
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
	}
	
	public void loginProximaTela (Pessoa pessoa){
		boolean logou = false;
		String cpf = pessoa.getCpf();
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
            		frameFuncionario((Funcionario) pessoa);
            		break;
            	}
            }
            while (logou==false && rsGerente.next()){
            	while (rsGerente.next()){
                	dbCPF = rsFunc.getString("CPF");
                	if (dbCPF.equals(cpf)){
                		logou = true;
                		new FrameAdmin1((Admin) pessoa).setVisible(true);
                		break;
                	}
                }
            }
            while (logou==false && rsCoord.next()){
            	while (rsCoord.next()){
                	dbCPF = rsFunc.getString("CPF");
                	if (dbCPF.equals(cpf)){
                		logou = true;
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
