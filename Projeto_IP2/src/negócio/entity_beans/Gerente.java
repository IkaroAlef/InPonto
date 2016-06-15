package negócio.entity_beans;

public class Gerente extends Admin{
	private Empresa empresa; 
	
	public Gerente(String nome, String cpf, String email, char[] senha) {
		super(nome, cpf, email, senha);
		// TODO Auto-generated constructor stub
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

}
