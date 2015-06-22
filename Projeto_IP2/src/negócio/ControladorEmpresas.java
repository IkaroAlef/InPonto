package negócio;

import java.util.ArrayList;

import dados.IRepositorioEmpresas;
import dados.RepEmpresa;
import dados.exceptionsDados.CnpjNaoEncontradoException;
import dados.exceptionsDados.EmpresaNaoEncontradaException;
import negócio.entity_beans.Empresa;

public class ControladorEmpresas {

	private IRepositorioEmpresas repositorioEmpresas;
	
	public ArrayList<Empresa> getEmpresas (String conteudo){
		return repositorioEmpresas.getEmpresas (conteudo);
	}	
	
	public  ControladorEmpresas (){ 
		repositorioEmpresas = RepEmpresa.getInstance();
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
	public Empresa buscaEmpresaNome(String nomeEmpresa) throws EmpresaNaoEncontradaException{
		return repositorioEmpresas.buscarEmpresaNome(nomeEmpresa);
	}
	public Empresa buscaEmpresaCNPJ(String cnpj) throws CnpjNaoEncontradoException{
		return repositorioEmpresas.buscarEmpresaCNPJ(cnpj);
	}
	
	//Indice
	public void buscarIndiceNomeEmpresa(String nomeEmpresa) throws EmpresaNaoEncontradaException{
		repositorioEmpresas.buscarIndiceNomeEmpresa(nomeEmpresa);
	}
	public void buscarIndiceCNPJ(String cnpj) throws CnpjNaoEncontradoException{
		repositorioEmpresas.buscarIndiceCNPJ(cnpj);
	}
	
	public int tamanhoLista(){
		return repositorioEmpresas.tamanhoLista();
	}
}

