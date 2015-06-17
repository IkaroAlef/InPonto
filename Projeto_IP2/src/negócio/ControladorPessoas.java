package negócio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import dados.IRepositorioPessoas;
import dados.exceptionsDados.*;
import negócio.entity_beans.*;

public class ControladorPessoas {
		
	public ArrayList<Pessoa> getPessoas(String conteudo) {
		return repositorioPessoas.getPessoas(conteudo);
	}

	private IRepositorioPessoas repositorioPessoas;
	private char[] senha = {'1','2','3','4'};
	private Pessoa pessoa = new Admin("Admin","123","ika",senha);
		
	public ControladorPessoas(IRepositorioPessoas instance){
		repositorioPessoas = instance;
		repositorioPessoas.adicionarPessoa(pessoa);
	}
	
	public void adicionarPessoa(Pessoa pessoa){
		repositorioPessoas.adicionarPessoa(pessoa);
	}
		
	public boolean validarLogin(String nome, char[] senhaDigitada) throws FuncionarioNaoEncontradoException{
		boolean estaCorreto=true;
		
		if(senhaDigitada.length != repositorioPessoas.buscaPessoaNome(nome).getSenha().length)
			estaCorreto=false;
			
		else
			estaCorreto=Arrays.equals(senhaDigitada, repositorioPessoas.buscaPessoaNome(nome).getSenha());
		
		return estaCorreto;
	}
	
	public String[] linhaTabela(int i){
		return repositorioPessoas.linhaFuncionario(i);
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

	public Pessoa buscaPessoaNome(String nome)
			throws FuncionarioNaoEncontradoException {
		return repositorioPessoas.buscaPessoaNome(nome);
	}

	public Funcionario buscaPessoaCpf(String cpf)
			throws FuncionarioNaoEncontradoException {
		return (Funcionario) repositorioPessoas.buscaPessoaCpf(cpf);
	}

	public void deletarPessoa(String nome) {
		repositorioPessoas.deletarPessoa(nome);
	}

	public void deletarPessoa(int i) {
		repositorioPessoas.deletarPessoa(i);
	}

	public void editar(int i, Pessoa pessoa) {
		repositorioPessoas.editar(i, pessoa);
	}

	public void exportar() throws IOException {
		repositorioPessoas.exportar();
	}
	
	
}
