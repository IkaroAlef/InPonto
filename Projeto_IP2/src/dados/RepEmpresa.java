//Repositorio de Empresas.
package dados;

import java.util.ArrayList;

import dados.exceptionsDados.CnpjNaoEncontradoException;
import dados.exceptionsDados.EmpresaNaoEncontradaException;
import negócio.entity_beans.Empresa;

public class RepEmpresa {

	private ArrayList<Empresa>empresas;
	
	public RepEmpresa(){
		empresas = new ArrayList<Empresa>();
	}
	
	public RepEmpresa(ArrayList<Empresa> empresas){
		this.empresas = empresas;
	}
	
	//Cadastro de empresa no array.
	public void adicionaEmpresa(Empresa empresa){
		this.empresas.add(empresa);
	}
	
	//Retorna Empresa.
	public String imprimir(int i){ 
		return String.valueOf(this.empresas.get(i));
	}
	
	//Busca pelo nomeEmpresa e Retorna o indice da empresa no array.
	public int buscarIndiceNomeEmpresa(String nomeEmpresa) throws EmpresaNaoEncontradaException{ 
		int resultado=0;
		for (int i=0;i<this.empresas.size();i++){
			if (nomeEmpresa!= null && this.empresas.get(i).igualNome(nomeEmpresa)){
				resultado = i;
			}
			else throw new EmpresaNaoEncontradaException(nomeEmpresa);
		}
		return resultado;
	}
	
	//Busca pelo CNPJ e Retorna o indice da empresa no array.
	public int buscarIndiceCNPJ(String cnpj) throws CnpjNaoEncontradoException{ 
		int resultado=0;
		for (int i=0;i<this.empresas.size();i++){
			if (this.empresas.get(i).igualCNPJ(cnpj)){
				resultado=i;
			}
			else throw new CnpjNaoEncontradoException(cnpj);
		}
		return resultado;
	}
	
	//Buscar Empresa pelo nome.
	public Empresa buscaEmpresaNome(String nomeEmpresa) throws EmpresaNaoEncontradaException{ 
		return this.empresas.get(buscarIndiceNomeEmpresa(nomeEmpresa));
	}
	
	//Buscar Empresa pelo CNPJ.
	public Empresa buscaEmpresaCNPJ(String cnpj) throws CnpjNaoEncontradoException{ 
		return this.empresas.get(buscarIndiceCNPJ(cnpj));
	}
	
	//Deleta um Empresa pelo Nome.
	public void deletarEmpresa(String nomeEmpresa){
		empresas.remove(nomeEmpresa);
	}
	
	public void editarEmpresa(int i,Empresa empresa){
		this.empresas.set(i, empresa);
	}
}
