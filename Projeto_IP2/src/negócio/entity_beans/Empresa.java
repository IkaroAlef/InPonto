package negócio.entity_beans;

import negócio.entity_beans.exceptionsBeans.CNPJInvalidoException;
import negócio.entity_beans.exceptionsBeans.NomeInvalidoException;



public class Empresa {

	private String nomeEmpresa;
	private String cnpj;
	private String endereco;
	private String contato;
	
	public Empresa ( String nomeEmpresa,String cnpj, String endereço, String contato) throws NomeInvalidoException, CNPJInvalidoException{
	
		this.setNomeEmpresa(nomeEmpresa);
		this.setCnpj(cnpj);
		this.setEndereco(endereço);
		this.setContato(contato);
	}
	public Empresa(){
		
	}
	
	// Assumindo que a Empresa ja tenha feito a devida garantia das propiedades do Nome fantasia/marca da Empresa.
	// Assim duas empresas não podem ter mesmo nome, mesmo sendo nome fantasia.
	public void setNomeEmpresa(String nomeEmpresa) throws NomeInvalidoException {
		if (nomeEmpresa!=null && nomeEmpresa!= " ")
			this.nomeEmpresa = nomeEmpresa;
		else throw new NomeInvalidoException();
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public String getCnpj() {
		return cnpj;
	}

	// CNPJ é unico para toda empresa
	public void setCnpj(String cnpj)throws CNPJInvalidoException {
		if (cnpj!=null && cnpj!= " ")
			this.cnpj = cnpj;
		else throw new CNPJInvalidoException();
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}
	
	public boolean igualNome(String nomeEmpresa) {
		if (nomeEmpresa!=null && this.nomeEmpresa.equalsIgnoreCase(nomeEmpresa))
			return true;
	else return false;
		}
	
	public boolean igualCNPJ(String cnpj){
		if (this.cnpj.equals(cnpj))
					return true;
			else return false;
		}
	}

	


