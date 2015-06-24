//Classe para Registro de Ponto
package negócio.entity_beans;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegPonto {
	private LocalDateTime agora;
	private Funcionario funcionario;
	
	public RegPonto(){
		
	}
	
	public LocalDateTime getAgora() {
		return agora;
	}
	
	public String getAgoraFormatada(){
		DateTimeFormatter formatador =  DateTimeFormatter.ofPattern("HH:mm 'do dia' dd/MM/yyyy");
		String dataFormatada = formatador.format(this.agora);
		
		return dataFormatada;
	}
	
	public Funcionario getFuncionario(){
		return this.funcionario;
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
	
	
	public String toString(){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YY HH:mm");
		return ("   Nome: "+funcionario.getNome()+
				"\n   Data e Hora do Ponto: "+agora.format(formatter));
	}
	
	public boolean chegadaCorreta(){
		if (agora.toLocalTime().equals(funcionario.getChegada())){
			return true;
		}
		else return false;					
	}
	
	public boolean saidaCorreta(){
		if (agora.toLocalTime().equals(funcionario.getSaida())){
			return true;
		}
		else return false;					
	}
	
	public boolean intervalo_InCorreta(){
		if (agora.toLocalTime().equals(funcionario.getIntervalo_in())){
			return true;
		}
		else return false;					
	}
	
	public boolean intervalo_OutCorreta(){
		if (agora.toLocalTime().equals(funcionario.getIntervalo_out())){
			return true;
		}
		else return false;					
	}
}