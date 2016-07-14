//Classe para representar Funcionario
package negócio.entity_beans;

import java.awt.Image;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.ImageIcon;

import negócio.entity_beans.exceptionsBeans.NomeInvalidoException;

public class Funcionario extends Pessoa implements Serializable {
	private String CPF_Coord;
	private String CTPS;
	private String PIS;
	private Date dt_admissao;
	private Date dt_demissao;
	private int cod_Eqp;
	private Empresa empresa;
	private String cargo;
	private String escala;
	private LocalTime chegada,saida,intervalo_in,intervalo_out; //chegada, saida, voltou do intervalo, saiu para o intervalo
	private Image fotoPadrao;
	
	public Funcionario(String nome, String cpf,String email,char[] senha,String telefone, 
			Empresa empresa, String cargo, String escala, LocalTime chegada, LocalTime saida, 
			LocalTime intervalo_in, LocalTime intervalo_out) throws NomeInvalidoException{
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
	
	public Funcionario(String nome,
	String cpf,
	String email,
	char[] senha,
	int matricula,
	String rg,
	String telefone,
	String cargo,
	int codDept,
	String rua,
	String numero,
	String complemento,
	String bairro,
	String cidade,
	String estado,
	String cep,
	String CPF_Coord,
	String CTPS,
	String PIS,
	Date dt_admissao,
	Date dt_demissao,
	int cod_Eqp,
	Empresa empresa,
	String escala,
	LocalTime chegada, LocalTime saida, LocalTime intervalo_in, LocalTime intervalo_out) {
		super(nome, cpf, email, senha, matricula, rg, telefone, cargo, codDept, rua, numero, complemento, bairro, cidade, estado, cep);
		this.CPF_Coord = CPF_Coord;
		this.CTPS = CTPS;
		this.PIS = PIS;
		this.dt_admissao = dt_admissao;
		this.dt_demissao = dt_demissao;
		this.cod_Eqp = cod_Eqp;
		this.empresa = empresa;
		this.cargo = cargo;
		this.escala = escala;
		this.chegada = chegada;
		this.saida = saida;
		this.intervalo_in = intervalo_in;
		this.intervalo_out = intervalo_out;
		
	}


	public Funcionario (){
		
	}
	
	public String getCPF_Coord() {
		return CPF_Coord;
	}

	public void setCPF_Coord(String cPF_Coord) {
		CPF_Coord = cPF_Coord;
	}

	public String getCTPS() {
		return CTPS;
	}

	public void setCTPS(String cTPS) {
		CTPS = cTPS;
	}

	public String getPIS() {
		return PIS;
	}

	public void setPIS(String pIS) {
		PIS = pIS;
	}

	public Date getDt_admissao() {
		return dt_admissao;
	}

	public void setDt_admissao(Date dt_admissao) {
		this.dt_admissao = dt_admissao;
	}

	public Date getDt_demissao() {
		return dt_demissao;
	}

	public void setDt_demissao(Date dt_demissao) {
		this.dt_demissao = dt_demissao;
	}

	public int getCod_Eqp() {
		return cod_Eqp;
	}

	public void setCod_Eqp(int cod_Eqp) {
		this.cod_Eqp = cod_Eqp;
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
	
	public Image getFotoPadrao() {
		return fotoPadrao;
	}

	public void setFotoPadrao(Image fotoPadrao) {
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
