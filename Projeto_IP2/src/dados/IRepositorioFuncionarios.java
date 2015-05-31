package dados;

import exceptionsDados.FuncionarioNaoEncontradoException;
import regras.entity_beans.Funcionario;

public interface IRepositorioFuncionarios {
	
	void adicionarFuncionario(Funcionario funcionario);
	int buscarIndiceNome(String nome) throws FuncionarioNaoEncontradoException;
	int buscarIndiceCpf(String cpf) throws FuncionarioNaoEncontradoException;
	Funcionario buscaFuncionarioNome(String nome) throws FuncionarioNaoEncontradoException;
	Funcionario buscaFuncionarioCpf(String cpf) throws FuncionarioNaoEncontradoException;
	void deletarFuncionario(String nome);
	void deletarFuncionario(int i);
	void editar(int i,Funcionario funcionario);
	String imprimir(int i);
	
}
