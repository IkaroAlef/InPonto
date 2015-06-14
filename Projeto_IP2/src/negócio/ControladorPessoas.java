package negócio;

import java.util.Arrays;

import dados.RepPessoas;
import exceptionsDados.*;
import negócio.entity_beans.*;

public class ControladorPessoas {
		
	private RepPessoas repositorioPessoas;
	private char[] senha = {'1','2','3','4'};
	private Pessoa pessoa = new Admin("Admin","123","ika",senha);
		
	public ControladorPessoas(){
		repositorioPessoas = new RepPessoas();
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
	
		
}
