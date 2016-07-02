//Classe para representar Funcionario
package neg�cio.entity_beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.ImageIcon;

import neg�cio.entity_beans.exceptionsBeans.NomeInvalidoException;

public class Funcionario extends Pessoa implements Serializable {
	protected String CPF_Coord;
	protected String CTPS;
	protected String PIS;
	protected LocalDate dt_admissao;
	protected LocalDate dt_demiissao;
	protected char[] senha;
	private Empresa empresa;
	private String cargo;
	private String escala;
	private LocalTime chegada,saida,intervalo_in,intervalo_out; //chegada, saida, voltou do intervalo, saiu para o intervalo
	private ImageIcon fotoPadrao;
	
	public Funcionario(String nome, String cpf,String email,char[] senha,String telefone, Empresa empresa, String cargo, String escala, LocalTime chegada, LocalTime saida, LocalTime intervalo_in, LocalTime intervalo_out) throws NomeInvalidoException{
		super(nome,cpf,email,senha);
		this.setTelefone(telefone);
		this.setEmpresa(empresa);
		this.setCargo(cargo);
		this.setEscala(escala);
		this.setChegada(chegada);
		this.setSaida(saida);
		this.setIntervalo_in(intervalo_in);
		this.setIntervalo_out(intervalo_out);
	}
	
	public Funcionario (){
		
	}
	
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getEscala() {
		return escala;
	}

	public void setEscala(String escala) {
		this.escala = escala;
	}

	public LocalTime getChegada() {
		return chegada;
	}

	public void setChegada(LocalTime chegada) {
		this.chegada = chegada;
	}

	public LocalTime getSaida() {
		return saida;
	}

	public void setSaida(LocalTime saida) {
		this.saida = saida;
	}

	public LocalTime getIntervalo_in() {
		return intervalo_in;
	}

	public void setIntervalo_in(LocalTime intervalo_in) {
		this.intervalo_in = intervalo_in;
	}

	public LocalTime getIntervalo_out() {
		return intervalo_out;
	}

	public void setIntervalo_out(LocalTime intervalo_out) {
		this.intervalo_out = intervalo_out;
	}
	
	public ImageIcon getFotoPadrao() {
		return fotoPadrao;
	}

	public void setFotoPadrao(ImageIcon fotoPadrao) {
		this.fotoPadrao = fotoPadrao;
	}

	public String toString(){
		return ("Nome: "+getNome()+
				" \nCPF: "+getCpf()+
				"\nE-mail:"+getEmail()+
				"\nHora de Chegada: "+String.valueOf(getChegada()))+
				"\nCargo: "+getCargo()+
				"\nEmpresa: "+getEmpresa();
	}
	
	public boolean equals(Funcionario funcionario){
		if (funcionario!=null){
			return  this.nome.equalsIgnoreCase( funcionario.getNome() ) &&
					this.cpf.equalsIgnoreCase( funcionario.getCpf() );
		}
		return false;
	}
	
}
