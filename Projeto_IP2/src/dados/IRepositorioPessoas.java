package dados;

import java.io.IOException;
import java.util.ArrayList;

import dados.exceptionsDados.FuncionarioNaoEncontradoException;
import negócio.entity_beans.Pessoa;

public interface IRepositorioPessoas {
	
	void adicionarPessoa(Pessoa pessoa);
	int buscarIndiceNome(String nome) throws FuncionarioNaoEncontradoException;
	int buscarIndiceCpf(String cpf) throws FuncionarioNaoEncontradoException;
	Pessoa buscarPessoaNome(String nome) throws FuncionarioNaoEncontradoException;
	Pessoa buscarPessoaCpf(String cpf) throws FuncionarioNaoEncontradoException;
	void deletarPessoa(String nome) throws FuncionarioNaoEncontradoException;
	void deletarPessoa(int i);
	void editar(int i,Pessoa pessoa);
	String getString(int i);
	Pessoa getObject(int i);
	ArrayList<Pessoa> getPessoas(String conteudo);
	int tamanhoLista();
	void exportar() throws IOException;
	void adicionarPessoa(int i, Pessoa pessoa);
	
}
