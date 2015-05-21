package dados;

import regras.entity_beans.Funcionario;

public interface IRepositorioFuncionarios {
	
	void adicionarFuncionario(Funcionario funcionario);
	int buscarNome(String nome);
	int buscarCpf(String cpf);
	Funcionario buscaFuncionarioNome(String nome);
	Funcionario buscaFuncionarioCpf(String cpf);
	void deletarFuncionario(String nome);
	void deletarFuncionario(int i);
	void editar(int i,Funcionario funcionario);
	String imprimir(int i);
	
}
