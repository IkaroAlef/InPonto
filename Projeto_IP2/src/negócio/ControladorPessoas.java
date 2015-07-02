package negócio;

import java.util.ArrayList;
import java.util.Arrays;

import dados.IRepositorioPessoas;
import dados.RepPessoas;
import dados.exceptionsDados.*;
import negócio.entity_beans.*;

public class ControladorPessoas {
	private IRepositorioPessoas repositorioPessoas;

	public ControladorPessoas(){
		this.repositorioPessoas = RepPessoas.getInstance();
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
		boolean estaCorreto=true;
		
		if(senhaDigitada.length != repositorioPessoas.buscarPessoaCpf(dado).getSenha().length)
			estaCorreto=false;
			
		else
			estaCorreto=Arrays.equals(senhaDigitada, repositorioPessoas.buscarPessoaCpf(dado).getSenha());
		
		return estaCorreto;
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
