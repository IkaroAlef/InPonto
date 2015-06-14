package dados;

import negócio.entity_beans.Pessoa;
import exceptionsDados.FuncionarioNaoEncontradoException;

public interface IRepositorioPessoas {
	
	void adicionarPessoa(Pessoa pessoa);
	int buscarIndiceNome(String nome) throws FuncionarioNaoEncontradoException;
	int buscarIndiceCpf(String cpf) throws FuncionarioNaoEncontradoException;
	Pessoa buscaPessoaNome(String nome) throws FuncionarioNaoEncontradoException;
	Pessoa buscaPessoaCpf(String cpf) throws FuncionarioNaoEncontradoException;
	void deletarPessoa(String nome);
	void deletarPessoa(int i);
	void editar(int i,Pessoa pessoa);
	String imprimir(int i);
	
}
