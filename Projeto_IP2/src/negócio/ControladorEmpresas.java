package negócio;

import java.util.ArrayList;

import dados.IRepositorioEmpresas;
import dados.exceptionsDados.CnpjNaoEncontradoException;
import dados.exceptionsDados.EmpresaNaoEncontradaException;
import negócio.entity_beans.Empresa;
import negócio.entity_beans.exceptionsBeans.CNPJInvalidoException;
import negócio.entity_beans.exceptionsBeans.NomeInvalidoException;

public class ControladorEmpresas {

	private IRepositorioEmpresas repositorioEmpresas;
	
	public ArrayList<Empresa> getEmpresas (String conteudo){
		return repositorioEmpresas.getEmpresas (conteudo);
	}	
	
	public  ControladorEmpresas (IRepositorioEmpresas instance){ 
		repositorioEmpresas = instance;
	}
	
	//CRUD
	//cadastrar empresa
	public void adicionarEmpresa (Empresa empresa){
		repositorioEmpresas.adicionarEmpresa(empresa);
	}
	
	//deletar empresa
	public void deletarEmpresa(String nomeEmpresa){
		repositorioEmpresas.deletarEmpresa(nomeEmpresa);
	}
	
	public void deletarEmpresa( int i){
		repositorioEmpresas.deletarEmpresa(i);
	}
	
	//editar empresa
	public void editarEmpresa(int i, Empresa empresa){
		repositorioEmpresas.editarEmpresa(i, empresa);
	}
	
	// Buscar
	public void buscaEmpresaNome(String nomeEmpresa) throws EmpresaNaoEncontradaException{
		repositorioEmpresas.buscaEmpresaNome(nomeEmpresa);
	}
	public void buscaEmpresaCNPJ(String cnpj) throws CnpjNaoEncontradoException{
		repositorioEmpresas.buscaEmpresaCNPJ(cnpj);
	}
	
	//Indice
	public void buscarIndiceNomeEmpresa(String nomeEmpresa) throws EmpresaNaoEncontradaException{
		repositorioEmpresas.buscarIndiceNomeEmpresa(nomeEmpresa);
	}
	public void buscarIndiceCNPJ(String cnpj) throws CnpjNaoEncontradoException{
		repositorioEmpresas.buscarIndiceCNPJ(cnpj);
	}
}

