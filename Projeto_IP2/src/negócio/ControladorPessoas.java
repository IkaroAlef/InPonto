package neg�cio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import com.mysql.cj.api.jdbc.Statement;

import conexaoBD.FabricaDeConexao;
import dados.IRepositorioPessoas;
import dados.RepPessoas;
import dados.exceptionsDados.*;
import neg�cio.entity_beans.*;

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
		
//		vers�o antiga com os repositorios em arquivo
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

	public Pessoa buscarPessoaCpf(String cpf)
			throws FuncionarioNaoEncontradoException {
		return repositorioPessoas.buscarPessoaCpf(cpf);
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
