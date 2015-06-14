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
import java.io.Serializable;
import java.nio.file.Paths;

import dados.exceptionsDados.FuncionarioNaoEncontradoException;
import neg�cio.entity_beans.Funcionario;
import neg�cio.entity_beans.Pessoa;

public class RepPessoas implements Serializable,IRepositorioPessoas {
	private ArrayList<Pessoa>pessoas;
	
	public RepPessoas(){
		pessoas = new ArrayList<Pessoa>(); //instancia o arrayList
	}
	
	public RepPessoas(ArrayList<Pessoa> pessoas){
		this.pessoas = pessoas;
	}
	
	public void adicionarPessoa(Pessoa pessoa){ //cadastra um funcionario no arrayList
		this.pessoas.add(pessoa);
	}
	
	public String imprimir(int i){ //retorna um funcionario na posi��o i no arrayList
		//fazer teste de I
		return String.valueOf(this.pessoas.get(i));
	}
	
	public int buscarIndiceNome(String nome) throws FuncionarioNaoEncontradoException{ //retorna o �ndice do Funcionario cujo nome � igual ao nome da busca. Retorna -1 caso n�o encontre
		int resultado=0;
		for (int i=0;i<this.pessoas.size();i++){
			if (nome!= null && this.pessoas.get(i).igualNome(nome)){
				resultado = i;
			}
			else throw new FuncionarioNaoEncontradoException(nome);
		}
		return resultado;
	}
	
	public int buscarIndiceCpf(String cpf) throws FuncionarioNaoEncontradoException{ //retorna o �ndice do Funcionario cujo cpf � igual ao nome da busca. Retorna -1 caso n�o encontre
		int resultado=0;
		for (int i=0;i<this.pessoas.size();i++){
			if (this.pessoas.get(i).igualCpf(cpf)){
				resultado=i;
			}
			else throw new FuncionarioNaoEncontradoException(cpf);
		}
		return resultado;
	}
	
	public Pessoa buscaPessoaNome(String nome) throws FuncionarioNaoEncontradoException{ //retorna o Objeto Funcionario a partir do nome
		int i = buscarIndiceNome(nome);
		if (i < 0)
			throw new FuncionarioNaoEncontradoException (nome);
		return this.pessoas.get(i);
	}
	
	public Funcionario buscaPessoaCpf(String cpf) throws FuncionarioNaoEncontradoException{ //retorna o Objeto Funcionario a partir do cpf
		return (Funcionario) this.pessoas.get(buscarIndiceCpf(cpf));
	}
	
	public void deletarPessoa(String nome){
		pessoas.remove(nome);
	}
	
	public void deletarPessoa(int i){
		pessoas.remove(i);
	}
	public void editar(int i,Pessoa pessoa){
		this.pessoas.set(i, pessoa);
	}
	
	public static void criarBase() throws IOException{
		if (!Paths.get("C:\\Dados\\").toFile().exists()){ //se a pasta n�o existe, ent�o cria com os arquivos abaixo
			File file = new File ("C:\\Dados\\");
			file.mkdir(); //cria a pasta
		}
		FileOutputStream dados = new FileOutputStream("C:\\Dados\\dados.dados");
		dados.close();
	}		

	public void exportar() throws IOException{
		if (!Paths.get("C:\\Dados\\").toFile().exists()){ //se a pasta n�o existe, ent�o cria com os arquivos abaixo
			File file = new File ("C:\\Dados\\");
			file.mkdir(); //cria a pasta
		}
		FileOutputStream dados = new FileOutputStream("C:\\Dados\\dados.dados"); //arquivo que armazena os dados
		ObjectOutputStream gravarArq = new ObjectOutputStream(dados);
		gravarArq.writeObject(this.pessoas);	
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
