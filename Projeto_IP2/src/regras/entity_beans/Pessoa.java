package regras.entity_beans;

import java.io.Serializable;

import regras.entity_beans.exceptionsBeans.NomeInvalidoException;

public abstract class Pessoa implements Serializable{
	protected String nome;
	protected String cpf;
	protected String email;
	protected String senha;
	
	protected Pessoa(String nome, String cpf, String email, String senha) throws NomeInvalidoException{
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

	public void setNome(String nome) throws NomeInvalidoException {
		if (nome!=null && nome!= " ")
			this.nome = nome;
		else throw new NomeInvalidoException();
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
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
