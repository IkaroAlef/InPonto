package negócio.entity_beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Admin extends Pessoa implements Serializable{
	
	private ArrayList <Empresa> empresas;
	
	public Admin(String nome, String cpf, String email, char[] senha){
		super (nome,cpf,email,senha);
		empresas = new ArrayList<Empresa>();
	}
	
	public ArrayList<Empresa> getEmpresas(){
		return empresas;
	}
	
	public String getStringEmpresas(){
		String retorno = "";
		for (int i = 0; i<empresas.size(); i++)
			retorno+=empresas.get(i).getNomeEmpresa()+", ";
		return retorno;
	}
	
	public void adicionarEmpresa(Empresa empresa){
		empresas.add(empresa);
	}
	
	public void adicionarEmpresas(List<Empresa> list){
		for(int i = 0; i < list.size(); i++){
			this.empresas.add(list.get(i));
		}
	}
}
