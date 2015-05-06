//Classe para Registro de Ponto
package regras.entity_beans;

import java.time.LocalDateTime;

public class RegPonto {
	private LocalDateTime agora;
	private Funcionario funcionario;
	
	public RegPonto(){
		
	}
	
	public void registrarPonto(Funcionario funcionario){
		if(funcionario!=null){
			this.agora=LocalDateTime.now();
			this.funcionario=funcionario;
		}
	}
}