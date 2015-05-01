//Repositorio de Funcionarios
package dados;

import java.util.ArrayList;
import regras.entity_beans.Funcionario;

public class RepFuncionario {
	private ArrayList<Funcionario>funcionario;
	
	public RepFuncionario(){
		funcionario = new ArrayList<Funcionario>(); //instancia o arrayList
	}
	
	public void cadastrar(Funcionario funcionario){ //cadastra um funcionario no arrayList
		this.funcionario.add(funcionario);
	}
	
	public String imprimir(int i){ //retorna um funcionario na posição i no arrayList
		return String.valueOf(this.funcionario.get(i));
	}
	
	public int buscarNome(String nome){ //retorna o índice do Funcionario cujo nome é igual ao nome da busca. Retorna -1 caso não encontre
		int i;
		for (i=0;i<this.funcionario.size();i++){
			if (this.funcionario.get(i).igualNome(nome)){
				return i;
			}
		}
		return -1;
	}
	
	public int buscarCpf(String cpf){ //retorna o índice do Funcionario cujo cpf é igual ao nome da busca. Retorna -1 caso não encontre
		int i;
		for (i=0;i<this.funcionario.size();i++){
			if (this.funcionario.get(i).igualCpf(cpf)){
				return i;
			}
		}
		return -1;
	}
	
	public void deletar(String nome){
		funcionario.remove(nome);
	}
	
	public void deletar(int i){
		funcionario.remove(i);
	}
	public void editar(int i,Funcionario funcionario){
		
	}
	
}
