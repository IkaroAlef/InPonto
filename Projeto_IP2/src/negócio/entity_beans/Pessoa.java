package negócio.entity_beans;

import java.io.Serializable;

import ch.qos.logback.core.net.SyslogOutputStream;
import negócio.entity_beans.exceptionsBeans.NomeInvalidoException;

public abstract class Pessoa implements Serializable{
	protected String nome;
	protected String cpf;
	protected String email;
	protected char[] senha;
	protected int matricula;
	protected String rg;
	protected String telefone;
	protected String cargo;
	protected int codDept;
	protected String rua;
	protected String numero;
	protected String complemento;
	protected String bairro;
	protected String cidade;
	protected String estado;
	protected String cep;
	
	protected Pessoa(String nome, String cpf, String email, char[] senha) {
		this.setNome(nome);
		this.setCpf(cpf);
		this.setEmail(email);
		this.setSenha(senha);
	}
	
	
	protected Pessoa(String nome, String cpf, String email, char[] senha, int matricula, String rg, String telefone,
			String cargo, int codDept, String rua, String numero, String complemento, String bairro, String cidade,
			String estado, String cep) {
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		this.matricula = matricula;
		this.rg = rg;
		this.telefone = telefone;
		this.cargo = cargo;
		this.codDept = codDept;
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
	}



	protected Pessoa(){
		
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome)  {
		if (nome!=null && nome!= " ")
			this.nome = nome;
//		else throw new NomeInvalidoException();
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public char[] getSenha() {
		return senha;
	}

	public void setSenha(char[] senha) {
		this.senha = senha;
	}
	
	public int getMatricula() {
		return matricula;
	}



	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}



	public String getRg() {
		return rg;
	}



	public void setRg(String rg) {
		this.rg = rg;
	}



	public String getTelefone() {
		return telefone;
	}



	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}



	public String getCargo() {
		return cargo;
	}



	public void setCargo(String cargo) {
		this.cargo = cargo;
	}



	public int getCodDept() {
		return codDept;
	}



	public void setCodDept(int codDept) {
		this.codDept = codDept;
	}



	public String getRua() {
		return rua;
	}



	public void setRua(String rua) {
		this.rua = rua;
	}



	public String getNumero() {
		return numero;
	}



	public void setNumero(String numero) {
		this.numero = numero;
	}



	public String getComplemento() {
		return complemento;
	}



	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}



	public String getBairro() {
		return bairro;
	}



	public void setBairro(String bairro) {
		this.bairro = bairro;
	}



	public String getCidade() {
		return cidade;
	}



	public void setCidade(String cidade) {
		this.cidade = cidade;
	}



	public String getEstado() {
		return estado;
	}



	public void setEstado(String estado) {
		this.estado = estado;
	}



	public String getCep() {
		return cep;
	}



	public void setCep(String cep) {
		this.cep = cep;
	}



	public boolean igualNome(String nome){
		if (nome!=null && this.nome.equalsIgnoreCase(nome))
				return true;
		else return false;
	}

	public boolean igualCpf(String cpf){
		if (this.cpf.equalsIgnoreCase(cpf))
				return true;
		else return false;
	}
	
}
