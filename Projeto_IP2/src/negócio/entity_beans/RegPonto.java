//Classe para Registro de Ponto
package negócio.entity_beans;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class RegPonto implements Serializable {
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
	
	public boolean chegadaCorreta(){ //Chegada correta com tolerância de 15 minutos
		LocalTime ponto = LocalTime.of(agora.getHour(),agora.getMinute());
		boolean retorno=false;
		
		for(int i=0; i<=15; i++){
			if (ponto.equals(this.funcionario.getChegada())){
				retorno = true;
				return retorno;
					
			}else{
				if(ponto.isBefore(this.funcionario.getChegada()))
					ponto=ponto.plusMinutes(1);
				else
					if(ponto.isAfter(this.funcionario.getChegada()))
						ponto=ponto.minusMinutes(1);
				}
		}
		return retorno;	
	}
	
	public boolean chegadaAtrasada(){ //Chegada atrasada
		boolean retorno=false;
		
		retorno=!this.chegadaCorreta();
		
		return retorno;	
	}
	
	public boolean saidaCorreta(){//Saida correta com tolerância de 15 minutos
		LocalTime ponto = LocalTime.of(agora.getHour(),agora.getMinute());
		boolean retorno=false;
		
		for(int i=0; i<=15; i++){
			if (ponto.equals(funcionario.getSaida())){
				retorno = true;
				return retorno;
			
			}else{
				if(ponto.isBefore(this.funcionario.getSaida()))
					ponto=ponto.plusMinutes(1);
				else
					if(ponto.isAfter(this.funcionario.getSaida()))
						ponto=ponto.minusMinutes(1);
				}
		}
		return retorno;	
		}
	
	public boolean intervalo_InCorreta(){ //Ida pro Intervalo correta com tolerância de 15 minutos
		LocalTime ponto = LocalTime.of(agora.getHour(),agora.getMinute());
		boolean retorno=false;
		
		for(int i=0; i<=15; i++){
			if (ponto.equals(funcionario.getIntervalo_in())){
				retorno = true;
				return retorno;			
			}else{
				if(ponto.isBefore(this.funcionario.getIntervalo_in()))
					ponto=ponto.plusMinutes(1);
				else
					if(ponto.isAfter(this.funcionario.getIntervalo_in()))
						ponto=ponto.minusMinutes(1);
				}				
			}
		return retorno;
	}
	
	public boolean intervalo_OutCorreta(){ //volta do Intervalo correta com tolerância de 15 minutos
		LocalTime ponto = LocalTime.of(agora.getHour(),agora.getMinute());
		boolean retorno=false;
		
		for(int i=0; i<=15; i++){
			if (ponto.equals(funcionario.getIntervalo_out())){
				retorno = true;
				return retorno;			
			}else{
				if(ponto.isBefore(this.funcionario.getIntervalo_out()))
					ponto=ponto.plusMinutes(1);
				else
					if(ponto.isAfter(this.funcionario.getIntervalo_out()))
						ponto=ponto.minusMinutes(1);
				}				
			}
		return retorno;					
	}
	
	@Override
	public boolean equals(Object obj){
		if (obj instanceof RegPonto && this.agora.equals(obj)){
			return true;
		}
		return false;
	}
}