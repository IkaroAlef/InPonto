package dados;

import java.io.IOException;
import java.util.ArrayList;

import dados.exceptionsDados.FuncionarioNaoEncontradoException;
import negócio.entity_beans.Pessoa;

public interface IRepositorioPessoas {
	
	void adicionarPessoa(Pessoa pessoa);
	int buscarIndiceNome(String nome) throws FuncionarioNaoEncontradoException;
	int buscarIndiceCpf(String cpf) throws FuncionarioNaoEncontradoException;
	Pessoa buscaPessoaNome(String nome) throws FuncionarioNaoEncontradoException;
	Pessoa buscaPessoaCpf(String cpf) throws FuncionarioNaoEncontradoException;
	void deletarPessoa(String nome);
	void deletarPessoa(int i);
	void editar(int i,Pessoa pessoa);
	String getString(int i);
	Pessoa getObject(int i);
	ArrayList<Pessoa> getPessoas(String conteudo);
	String[] linhaFuncionario(int i);
	int tamanhoLista();
	void exportar() throws IOException;
	
}
