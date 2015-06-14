package dados;

import negócio.entity_beans.Empresa;
import exceptionsDados.CnpjNaoEncontradoException;
import exceptionsDados.EmpresaNaoEncontradaException;

public interface IRepositorioEmpresas {

	void adicionarEmpresa(Empresa empresa);
	
	int buscarIndiceNomeEmpresa(String nomeEmpresa) throws EmpresaNaoEncontradaException;
	
	int buscarIndiceCNPJ(String cnpj) throws CnpjNaoEncontradoException;
	
	Empresa buscaEmpresaNome(String nomeEmpresa) throws EmpresaNaoEncontradaException;
	
	Empresa buscaEmpresaCNPJ(String cnpj) throws CnpjNaoEncontradoException;
	
	void deletarEmpresa(String nomeEmpresa);
	
	void deletarEmpresa(int i);
	
	void editarEmpresa(int i,Empresa empresa);
	
	String imprimir(int i);
}
