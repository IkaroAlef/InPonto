//Repositorio de Funcionarios
package dados;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.Serializable;
import java.nio.file.Paths;

import dados.exceptionsDados.FuncionarioNaoEncontradoException;
import negócio.entity_beans.Admin;
import negócio.entity_beans.Funcionario;
import negócio.entity_beans.Pessoa;

public class RepPessoas implements Serializable,IRepositorioPessoas {
	private ArrayList<Pessoa>pessoas;
	
	private static RepPessoas instance;
	
	public RepPessoas(){
		pessoas = new ArrayList<Pessoa>(); //instancia o arrayList
	}
	
	public RepPessoas(ArrayList<Pessoa> pessoas){
		this.pessoas = pessoas;
	}
	
	public static IRepositorioPessoas getInstance() {
        if (instance == null) {
        	char[] senha = {'1','2','3','4'};
        	Pessoa pessoa = new Admin("Admin","123","ika",senha);
        	RepPessoas.getInstance().adicionarPessoa(pessoa);	
            instance = lerDoArquivo();
        }
        return instance;
    }
	
	public void adicionarPessoa(Pessoa pessoa){ //cadastra um funcionario no arrayList
		this.pessoas.add(pessoa);
		RepPessoas.salvarArquivo();
	}
	
	public void adicionarPessoa(int i, Pessoa pessoa){ //cadastra um funcionario no arrayList na posição especifica
		this.pessoas.add(i,pessoa);
		RepPessoas.salvarArquivo();
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
		
	public int buscarIndiceNome(String nome) throws FuncionarioNaoEncontradoException{ //retorna o índice do Funcionario cujo nome é igual ao nome da busca. Retorna -1 caso não encontre
		int resultado=-1;
		for (int i=0;i<this.pessoas.size();i++){
			if (nome != null && this.pessoas.get(i).igualNome(nome)){
				resultado = i;
			} 
		}
		if(resultado==-1)
			throw new FuncionarioNaoEncontradoException(nome);
		return resultado;
	}
	
	public int buscarIndiceCpf(String cpf) throws FuncionarioNaoEncontradoException{ //retorna o índice do Funcionario cujo cpf é igual ao nome da busca. Retorna -1 caso não encontre
		int resultado=-1;
		for (int i=0;i<this.pessoas.size();i++){
			if (cpf != null && this.pessoas.get(i).igualCpf(cpf)){
				resultado=i;
			}
		}
		if(resultado==-1)
			throw new FuncionarioNaoEncontradoException(cpf);
		return resultado;
	}
	
	public Pessoa buscarPessoaNome(String nome) throws FuncionarioNaoEncontradoException{ //retorna o Objeto Funcionario a partir do nome
		int i = buscarIndiceNome(nome);
		if (i < 0)
			throw new FuncionarioNaoEncontradoException (nome);
		return this.pessoas.get(i);
	}
	
	public Pessoa buscarPessoaCpf(String cpf) throws FuncionarioNaoEncontradoException{ //retorna o Objeto Funcionario a partir do cpf
		return this.pessoas.get(buscarIndiceCpf(cpf));
	}
	
	public void deletarPessoas(String[] nomes) throws FuncionarioNaoEncontradoException{
		for (int i = 0; i<nomes.length; i++){
			Pessoa pessoa  = this.buscarPessoaNome(nomes[i]);
			pessoas.remove(pessoa);
		}
		RepPessoas.salvarArquivo();
	}
	
	public void deletarPessoa(int i){
		pessoas.remove(i);
		RepPessoas.salvarArquivo();
	}
	public void editar(int i,Pessoa pessoa){
		this.pessoas.set(i, pessoa);
		RepPessoas.salvarArquivo();
	}
	
	public int tamanhoLista(){
		return pessoas.size();
	}
			
	private static RepPessoas lerDoArquivo() {
        RepPessoas instanciaLocal = null;

        File in = new File("pessoas.dat");
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(in);
            ois = new ObjectInputStream(fis);
            Object o = ois.readObject();
            instanciaLocal = (RepPessoas) o;
        } catch (Exception e) {
            instanciaLocal = new RepPessoas();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {/* Silent exception */
                }
            }
        }

        return instanciaLocal;
    }
	
	public static void salvarArquivo() {
        if (instance == null) {
            return;
        }
        File out = new File("pessoas.dat");
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        
        try {
            fos = new FileOutputStream(out);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(instance);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try { oos.close(); } catch (IOException e) {/*Silent*/}
            }
        }
    }

	
}
