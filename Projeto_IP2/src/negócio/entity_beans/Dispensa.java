package negócio.entity_beans;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Dispensa implements Serializable {
	private Funcionario funcionario;
	private LocalDateTime inicio;
	private int qtdDias;
	
	public Dispensa(Funcionario funcionario, LocalDateTime inicio, int qtdDias) {
		this.funcionario = funcionario;
		this.inicio = inicio;
		this.qtdDias = qtdDias;
	}

	public LocalDateTime getInicio() {
		return inicio;
	}

	public void setInicio(LocalDateTime inicio) {
		this.inicio = inicio;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public int getQtdDias() {
		return qtdDias;
	}

	public void setQtdDias(int qtdDias) {
		this.qtdDias = qtdDias;
	}

}
