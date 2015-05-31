package exceptionsDados;

public class FuncionarioNaoEncontradoException extends Exception {
	private String dado;
	
	public FuncionarioNaoEncontradoException(String dado) {
		super("Funcionario n�o encontrado");
		this.dado=dado;
	}

	public String getDado() {
		return dado;
	}

	public void setDado(String dado) {
		this.dado = dado;
	}

}
