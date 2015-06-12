package negócio;

import dados.RepPessoas;
import negócio.entity_beans.*;

public class ControladorPessoas {
		RepPessoas repositorioPessoas;
		
		public ControladorPessoas(){
			repositorioPessoas = new RepPessoas();
		}
		
		public boolean validarLogin(String nome, String senha){
			return true;
		}
		
}
