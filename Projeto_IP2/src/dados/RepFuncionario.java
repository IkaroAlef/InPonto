//Repositorio de Funcionarios
package dados;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;

import regras.entity_beans.Funcionario;

import java.io.Serializable;
import java.nio.file.Paths;

public class RepFuncionario implements Serializable {
	private ArrayList<Funcionario>funcionarios;
	
	public RepFuncionario(){
		funcionarios = new ArrayList<Funcionario>(); //instancia o arrayList
	}
	
	public RepFuncionario(ArrayList<Funcionario> funcionarios){
		this.funcionarios = funcionarios;
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
			if (nome!= null && this.funcionarios.get(i).igualNome(nome)){
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
	
	public Funcionario buscaFuncionarioNome(String nome){ //retorna o Objeto Funcionario a partir do nome
		return this.funcionarios.get(buscarNome(nome));
	}
	
	public Funcionario buscaFuncionarioCpf(String cpf){ //retorna o Objeto Funcionario a partir do cpf
		return this.funcionarios.get(buscarCpf(cpf));
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
	
	public static void criarBase() throws IOException{
		if (!Paths.get("C:\\Dados\\").toFile().exists()){ //se a pasta não existe, então cria com os arquivos abaixo
			File file = new File ("C:\\Dados\\");
			file.mkdir(); //cria a pasta
		}
		FileOutputStream dados = new FileOutputStream("C:\\Dados\\dados.dados");
		dados.close();
	}		

	public void exportar() throws IOException{
		if (!Paths.get("C:\\Dados\\").toFile().exists()){ //se a pasta não existe, então cria com os arquivos abaixo
			File file = new File ("C:\\Dados\\");
			file.mkdir(); //cria a pasta
		}
		FileOutputStream dados = new FileOutputStream("C:\\Dados\\dados.dados"); //arquivo que armazena os dados
		ObjectOutputStream gravarArq = new ObjectOutputStream(dados);
		gravarArq.writeObject(this.funcionarios);	
//		System.out.println(this.funcionarios.get(0));
		dados.close();	
	}
	
	public static ArrayList <Funcionario> importar() throws IOException{
		FileInputStream Arq = new FileInputStream("C:\\Dados\\dados.dados");
		ObjectInputStream recuperarArq = new ObjectInputStream(Arq); 
		ArrayList <Funcionario> funcionarios = new ArrayList <Funcionario>();
		try {
			funcionarios = (ArrayList <Funcionario>) recuperarArq.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Arq.close();
//		System.out.println(funcionarios.get(0));
		return funcionarios;
	}
	
}
