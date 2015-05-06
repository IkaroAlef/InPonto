//Repositorio de Funcionarios
package dados;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import regras.entity_beans.Funcionario;

public class RepFuncionario {
	private ArrayList<Funcionario>funcionarios;
	
	public RepFuncionario(){
		funcionarios = new ArrayList<Funcionario>(); //instancia o arrayList
	}
	
	public void adicionarFuncionario(Funcionario funcionario){ //cadastra um funcionario no arrayList
		this.funcionarios.add(funcionario);
	}
	
	public String imprimir(int i){ //retorna um funcionario na posição i no arrayList
		//fazer teste de I
		return String.valueOf(this.funcionarios.get(i));
	}
	
	public int buscarNome(String nome){ //retorna o índice do Funcionario cujo nome é igual ao nome da busca. Retorna -1 caso não encontre
		int i;
		for (i=0;i<this.funcionarios.size();i++){
			if (this.funcionarios.get(i).igualNome(nome)){
				return i;
			}
		}
		return -1;
	}
	
	public int buscarCpf(String cpf){ //retorna o índice do Funcionario cujo cpf é igual ao nome da busca. Retorna -1 caso não encontre
		int i;
		for (i=0;i<this.funcionarios.size();i++){
			if (this.funcionarios.get(i).igualCpf(cpf)){
				return i;
			}
		}
		return -1;
	}
	
	public void deletarFuncionario(String nome){
		funcionarios.remove(nome);
	}
	
	public void deletarFuncionario(int i){
		funcionarios.remove(i);
	}
	public void editar(int i,Funcionario funcionario){
		this.funcionarios.set(i, funcionario);
	}

	public void gravarDisco() throws IOException{
		if (!new File("C:\\Dados\\dados.dados").exists()){ //se a pasta não existe, então cria com os arquivos abaixo
			new File("C:\\Dados\\dados.dados").mkdir(); // criar pasta 
			
			FileOutputStream dados = new FileOutputStream("C:\\Dados\\dados.dados"); //arquivo que armazena os dados
			ObjectOutputStream gravarArq = new ObjectOutputStream(dados);
			gravarArq.writeObject(this.funcionarios);
			dados.close();
			
		}
	}
	
	
}
