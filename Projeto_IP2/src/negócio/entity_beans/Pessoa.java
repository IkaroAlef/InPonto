package negócio.entity_beans;

import java.io.Serializable;

import javafx.scene.control.PasswordField;
import negócio.entity_beans.exceptionsBeans.NomeInvalidoException;

public abstract class Pessoa implements Serializable{
	protected String nome;
	protected String cpf;
	protected String email;
	protected char[] senha;
	
	protected Pessoa(String nome, String cpf, String email, char[] senha) {
		this.setNome(nome);
		this.setCpf(cpf);
		this.setEmail(email);
		this.setSenha(senha);
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
	
	public boolean igualNome(String nome){
		if (nome!=null && this.nome.equalsIgnoreCase(nome))
				return true;
		else return false;
	}

	public boolean igualCpf(String cpf){
		if (this.cpf.equals(cpf))
				return true;
		else return false;
	}

}
