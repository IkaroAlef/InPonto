//Classe para Registro de Ponto
package regras.entity_beans;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class RegPonto {
	private LocalDateTime agora;
	private Funcionario funcionario;
	
	public RegPonto(){
		
	}
	
	public RegPonto(LocalDateTime agora, Funcionario funcionario){
		this.agora=agora;
		this.funcionario=funcionario;
	}
	
	public void registrarPonto(Funcionario funcionario){
		if(funcionario!=null){
			this.agora=LocalDateTime.now();
			this.funcionario=funcionario;
		}
	}
	
	public Funcionario getFuncionario(){
		return this.funcionario;
	}
	
	public String toString(){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YY HH:mm");
		return ("   Nome: "+funcionario.getNome()+
				"\n   Data e Hora do Ponto: "+agora.format(formatter));
	}
	
	public boolean chegadaCorreta(){
		if (agora.toLocalTime().equals((LocalTime)funcionario.getChegada())){
			return true;
		}
		else return false;					
	}
}