package dados;

import java.util.ArrayList;

import dados.exceptionsDados.CnpjNaoEncontradoException;
import dados.exceptionsDados.EmpresaNaoEncontradaException;
import negócio.entity_beans.Empresa;

public interface IRepositorioEmpresas {

	void adicionarEmpresa(Empresa empresa);
	int buscarIndiceNomeEmpresa(String nomeEmpresa) throws EmpresaNaoEncontradaException;
	int buscarIndiceCNPJ(String cnpj) throws CnpjNaoEncontradoException;
	Empresa buscarEmpresaNome(String nomeEmpresa) throws EmpresaNaoEncontradaException;
	Empresa buscarEmpresaCNPJ(String cnpj) throws CnpjNaoEncontradoException;
	void deletarEmpresa(String nomeEmpresa);
	void deletarEmpresa(int i);
	void editarEmpresa(int i,Empresa empresa);
	String imprimir(int i);
	int tamanhoLista();

	ArrayList<Empresa> getEmpresas(String conteudo);
	ArrayList<Empresa> getEmpresas();
}
