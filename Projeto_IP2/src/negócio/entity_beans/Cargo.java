package negócio.entity_beans;

public class Cargo {
	private int codigo;
	private double vt_por_hora;
	private String nome;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getVt_por_hora() {
		return vt_por_hora;
	}
	public void setVt_por_hora(double vt_por_hora) {
		this.vt_por_hora = vt_por_hora;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
}
