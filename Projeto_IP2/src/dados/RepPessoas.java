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
import negócio.entity_beans.Funcionario;
import negócio.entity_beans.Pessoa;

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
	
	public String getString(int i){ //retorna um funcionario na posição i no arrayList
		return pessoas.get(i).toString();
	}
	
	public Pessoa getObject(int i){
		return pessoas.get(i);
	}
	
	public ArrayList<Pessoa> getPessoas(String conteudo){ //caso o parametro seja null, retorna Todo o Array. Caso contrário, retorna as pessoas cujo nome contém o parâmetro
		ArrayList<Pessoa> retorno = new ArrayList<Pessoa>();
		if(conteudo==null)
			return this.pessoas;
		else{
			for(int i=0;i<pessoas.size();i++){
				if(pessoas.get(i).getNome().toUpperCase().contains(conteudo.toUpperCase()))
					retorno.add(pessoas.get(i));
			}
		}
		return retorno;
		
	}
	
	public String[] linhaFuncionario(int i){
		String[] linha= new String[5];
		if (pessoas.get(i) instanceof Funcionario){
			linha[0] = pessoas.get(i).getNome();
			linha[1] = pessoas.get(i).getCpf();
			linha[2] = pessoas.get(i).getEmail();
			linha[3] = ((Funcionario) pessoas.get(i)).getTelefone();
			linha[4] = ((Funcionario) pessoas.get(i)).getCargo();
		}
		return linha;
	}
	
	public int buscarIndiceNome(String nome) throws FuncionarioNaoEncontradoException{ //retorna o índice do Funcionario cujo nome é igual ao nome da busca. Retorna -1 caso não encontre
		int resultado=0;
		for (int i=0;i<this.pessoas.size();i++){
			if (nome!= null && this.pessoas.get(i).igualNome(nome)){
				resultado = i;
			}
			else throw new FuncionarioNaoEncontradoException(nome);
		}
		return resultado;
	}
	
	public int buscarIndiceCpf(String cpf) throws FuncionarioNaoEncontradoException{ //retorna o índice do Funcionario cujo cpf é igual ao nome da busca. Retorna -1 caso não encontre
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
	
	public Pessoa buscaPessoaCpf(String cpf) throws FuncionarioNaoEncontradoException{ //retorna o Objeto Funcionario a partir do cpf
		return this.pessoas.get(buscarIndiceCpf(cpf));
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
	
	public int tamanhoLista(){
		return pessoas.size();
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
