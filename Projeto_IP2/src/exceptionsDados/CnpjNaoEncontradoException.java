package exceptionsDados;

public class CnpjNaoEncontradoException extends Exception {

private String dado;
	
	public CnpjNaoEncontradoException(String dado) {
		super("Cnpj nao encontrado");
		this.dado=dado;
	}

	public String getDado() {
		return dado;
	}

	public void setDado(String dado) {
		this.dado = dado;
	}
}
