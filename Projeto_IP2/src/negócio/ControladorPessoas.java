package negócio;

import java.util.Arrays;

import dados.RepPessoas;
import dados.exceptionsDados.*;
import negócio.entity_beans.*;

public class ControladorPessoas {
		
	private RepPessoas repositorioPessoas;
	private char[] senha = {'1','2','3','4'};
	private Pessoa pessoa = new Admin("Admin","123","ika",senha);
		
	public ControladorPessoas(){
		repositorioPessoas = new RepPessoas();
		repositorioPessoas.adicionarPessoa(pessoa);
	}
	
	public void adicionarPessoa(Pessoa pessoa){
		repositorioPessoas.adicionarPessoa(pessoa);
	}
		
	public boolean validarLogin(String nome, char[] senhaDigitada) throws FuncionarioNaoEncontradoException{
		boolean estaCorreto=true;
		
		if(senhaDigitada.length != repositorioPessoas.buscaPessoaNome(nome).getSenha().length)
			estaCorreto=false;
			
		else
			estaCorreto=Arrays.equals(senhaDigitada, repositorioPessoas.buscaPessoaNome(nome).getSenha());
		
		return estaCorreto;
	}
	
	public String[] linhaTabela(int i){
		return repositorioPessoas.linhaFuncionario(i);
	}
	
	public int tamanhoLista(){
		return repositorioPessoas.tamanhoLista();
	}
	
	public Object[][] conteudoTabela(){
		Object[][] retorno = new Object[1][4];
//		repositorioPessoas.tamanhoLista()
		for (int i=0;i<1;i++){
				retorno[i][0]=repositorioPessoas.getObject(i).getNome();
				retorno[i][1]=repositorioPessoas.getObject(i).getCpf();
				retorno[i][2]=repositorioPessoas.getObject(i).getEmail();
				retorno[i][3]=((Funcionario) repositorioPessoas.getObject(i)).getTelefone();
				retorno[i][4]=((Funcionario) repositorioPessoas.getObject(i)).getCargo();
		}
		return retorno;
	}
	
}
