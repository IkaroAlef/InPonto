package negócio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

import com.mysql.cj.api.jdbc.Statement;

import conexaoBD.FabricaDeConexao;
import dados.IRepositorioPessoas;
import dados.RepPessoas;
import dados.exceptionsDados.*;
import negócio.entity_beans.*;
import negócio.entity_beans.exceptionsBeans.NomeInvalidoException;

public class ControladorPessoas {
	private IRepositorioPessoas repositorioPessoas;

	public ControladorPessoas(){
		this.repositorioPessoas = RepPessoas.getInstance();
		 if(repositorioPessoas.tamanhoLista() == 0){
			 char[] senha = {'1','2','3','6','9'};
    	Pessoa pessoa = new Admin("AdminSuper","020715","ikaroalef@gmail.com",senha);
    	RepPessoas.getInstance().adicionarPessoa(pessoa);
    	}
	}
	
	public ArrayList<Pessoa> getPessoas(String conteudo) {
		return repositorioPessoas.getPessoas(conteudo);
	}
		
	
	public void adicionarPessoa(Pessoa pessoa){
		repositorioPessoas.adicionarPessoa(pessoa);
	}
		
	public void adicionarPessoa(int i, Pessoa pessoa) {
		repositorioPessoas.adicionarPessoa(i, pessoa);
	}

	public boolean validarLogin(String dado, char[] senhaDigitada) throws FuncionarioNaoEncontradoException{
		boolean estaCorreto=false;
		String dbCPF, dbSenha;
		try {
			FabricaDeConexao bd = new FabricaDeConexao();
            Connection con = bd.getConexao("login", "bancodedados");
            Statement stmt = (Statement) con.createStatement();
            //stmt.executeQuery("SELECT CPF, senha FROM pessoa;");
            ResultSet rs = stmt.executeQuery("SELECT CPF, senha FROM pessoa;");	//stmt.getResultSet();
            while(rs.next()){
                dbCPF = rs.getString("CPF");
                dbSenha = rs.getString("senha");
                if(dbCPF.equals(dado) && Arrays.equals(senhaDigitada, dbSenha.toCharArray())){
                    estaCorreto = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return estaCorreto;
		
//		versão antiga com os repositorios em arquivo
//		if(senhaDigitada.length != repositorioPessoas.buscarPessoaCpf(dado).getSenha().length)
//			estaCorreto=false;
//			
//		else
//			estaCorreto=Arrays.equals(senhaDigitada, repositorioPessoas.buscarPessoaCpf(dado).getSenha());
//		
	}
	
	public int tamanhoLista(){
		return repositorioPessoas.tamanhoLista();
	}

	public String getString(int i) {
		return repositorioPessoas.getString(i);
	}

	public Pessoa getObject(int i) {
		return repositorioPessoas.getObject(i);
	}

	public int buscarIndiceNome(String nome)
			throws FuncionarioNaoEncontradoException {
		return repositorioPessoas.buscarIndiceNome(nome);
	}

	public int buscarIndiceCpf(String cpf)
			throws FuncionarioNaoEncontradoException {
		return repositorioPessoas.buscarIndiceCpf(cpf);
	}

	public Pessoa buscarPessoaNome(String nome)
			throws FuncionarioNaoEncontradoException {
		return repositorioPessoas.buscarPessoaNome(nome);
	}

	public Pessoa buscarPessoaCpf(String cpf) throws FuncionarioNaoEncontradoException, NomeInvalidoException {
		Pessoa pessoa = null;
		String dbCPF;
		
		try {
			FabricaDeConexao bd = new FabricaDeConexao();
			Connection con = bd.getConexao("admin", "bancodedados");
//			Statement stmt = (Statement) con.createStatement();
			ResultSet rsFunc = con.createStatement().executeQuery("SELECT * FROM FUNCIONARIO JOIN PESSOA; ");
            ResultSet rsGerente = con.createStatement().executeQuery("SELECT * FROM GERENTE JOIN PESSOA; ");
            ResultSet rsCoord = con.createStatement().executeQuery("SELECT * FROM COORDENADOR JOIN PESSOA; ");
            while (rsFunc.next()){
            	dbCPF = rsFunc.getString("CPF");
            	if (dbCPF.equals(cpf)){
            		//84493610941  123454   login e senha teste
            		String telefone = rsFunc.getString("telefone"); 
            		Empresa empresa = new Empresa(); 
            		int carg = rsFunc.getInt("cargo");
            		ResultSet rsCarg = con.createStatement().executeQuery("SELECT nome FROM CARGO WHERE cargo.codigo="+carg+";");
            		rsCarg.next();
            		String cargo = rsCarg.getString("nome");
            		String escala = "nada" ;
            		LocalTime chegada = LocalTime.of(8, 00);
            		LocalTime saida = LocalTime.of(12, 00); 
            		LocalTime intervalo_in = LocalTime.of(9, 00); 
            		LocalTime intervalo_out = LocalTime.of(9, 30);
            		
            		String nome = rsFunc.getString("nome");
            		String email = rsFunc.getString("email");
            		char[] senha = rsFunc.getString("senha").toCharArray();
            		pessoa = new Funcionario(nome, dbCPF, email, senha, telefone, empresa,cargo,escala,chegada,saida,intervalo_in,intervalo_out);
            		break;
            	}
            }
            while (rsGerente.next()){
            	while (rsGerente.next()){
                	dbCPF = rsGerente.getString("CPF");
                	if (dbCPF.equals(cpf)){
                		break;
                	}
                }
            }
            while (rsCoord.next()){
            	while (rsCoord.next()){
                	dbCPF = rsCoord.getString("CPF");
                	if (dbCPF.equals(cpf)){
                		break;
                	}
                }
            }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//return repositorioPessoas.buscarPessoaCpf(cpf);
		return pessoa;
	}

	public void deletarPessoas(String[] nome) throws FuncionarioNaoEncontradoException {
		repositorioPessoas.deletarPessoas(nome);
	}

	public void deletarPessoa(int i) {
		repositorioPessoas.deletarPessoa(i);
	}

	public void editar(int i, Pessoa pessoa) {
		repositorioPessoas.editar(i, pessoa);
	}
	
}
