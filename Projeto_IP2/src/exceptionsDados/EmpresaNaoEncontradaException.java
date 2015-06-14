package exceptionsDados;

public class EmpresaNaoEncontradaException extends Exception {

private String dado;
	
	public EmpresaNaoEncontradaException(String dado) {
		super("Empresa n�o encontrada");
		this.dado=dado;
	}

	public String getDado() {
		return dado;
	}

	public void setDado(String dado) {
		this.dado = dado;
	}
}
