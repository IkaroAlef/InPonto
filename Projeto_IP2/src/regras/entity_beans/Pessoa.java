package regras.entity_beans;

public class Pessoa {
	protected String nome;
	protected String cpf;
	protected String email;
	protected String senha;
	
	protected Pessoa(String nome, String cpf, String email, String senha){
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

	public void setNome(String nome) {
		this.nome = nome;
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
