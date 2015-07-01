package neg�cio;

import java.util.ArrayList;

import neg�cio.entity_beans.RegPonto;
import dados.IRepositorioPontos;
import dados.RepRegPonto;
import dados.exceptionsDados.FuncionarioNaoEncontradoException;

public class ControladorPontos {
	private IRepositorioPontos pontos;
	
	public ControladorPontos() {
		this.pontos = RepRegPonto.getInstance();
	}

	public void adicionarRegistro(RegPonto ponto) {
		pontos.adicionarRegistro(ponto);
	}

	public ArrayList<RegPonto> getPontosDoFuncionario(String cpf)
			throws FuncionarioNaoEncontradoException {
		return pontos.getPontosDoFuncionario(cpf);
	}
	
	public ArrayList<RegPonto> getPontosDoFuncionario(String cpf, int mes, int ano)
			throws FuncionarioNaoEncontradoException{
		return pontos.getPontosDoFuncionario(cpf, mes, ano);
	}
	
	public ArrayList<RegPonto> getPontosDoFuncionario(String cpf, int dia, int mes, int ano)
			throws FuncionarioNaoEncontradoException{
		return pontos.getPontosDoFuncionario(cpf, dia, mes, ano);
	}	

	public boolean isDiaCorreto(String cpf, int dia, int mes, int ano)
			throws FuncionarioNaoEncontradoException {
		return pontos.isDiaCorreto(cpf, dia, mes, ano);
	}

	public boolean isDiaAtrasado(String cpf, int dia, int mes, int ano)
			throws FuncionarioNaoEncontradoException {
		return pontos.isDiaAtrasado(cpf, dia, mes, ano);
	}

	public int totalChegadaCorreta(String cpf)
			throws FuncionarioNaoEncontradoException {
		return pontos.totalChegadaCorreta(cpf);
	}

	public int totalSaidaCorreta(String cpf)
			throws FuncionarioNaoEncontradoException {
		return pontos.totalSaidaCorreta(cpf);
	}

	public int totalIntervalo_InCorreta(String cpf)
			throws FuncionarioNaoEncontradoException {
		return pontos.totalIntervalo_InCorreta(cpf);
	}

	public int totalIntervalo_OutCorreta(String cpf)
			throws FuncionarioNaoEncontradoException {
		return pontos.totalIntervalo_OutCorreta(cpf);
	}
	
	

}
