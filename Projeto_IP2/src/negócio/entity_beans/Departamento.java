package negócio.entity_beans;

public class Departamento {
	
	private int codigo;
	private String nome;
	private int totalDeProjetos;
	
	public Departamento(int codigo, String nome, int totalDeProjetos) {
		this.codigo = codigo;
		this.nome = nome;
		this.totalDeProjetos = totalDeProjetos;
	}
	
	public int getCodigo() {
		return codigo;
	}	
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getTotalDeProjetos() {
		return totalDeProjetos;
	}
	public void setTotalDeProjetos(int totalDeProjetos) {
		this.totalDeProjetos = totalDeProjetos;
	}	
}
