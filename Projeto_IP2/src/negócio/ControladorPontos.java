package negócio;

import java.time.Month;
import java.util.ArrayList;

import negócio.entity_beans.RegPonto;
import negócio.entity_beans.exceptionsBeans.NomeInvalidoException;
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

	public ArrayList<RegPonto> getPontosDoFuncionario(String cpf) throws FuncionarioNaoEncontradoException, NomeInvalidoException {
		return pontos.getPontosDoFuncionario(cpf);
	}
	
	public ArrayList<RegPonto> getPontosDoFuncionario(String cpf, int mes, int ano) throws FuncionarioNaoEncontradoException, NomeInvalidoException{
		return pontos.getPontosDoFuncionario(cpf, mes, ano);
	}
	
	public ArrayList<RegPonto> getPontosDoFuncionario(String cpf, int dia, int mes, int ano)
			throws FuncionarioNaoEncontradoException, NomeInvalidoException{
		return pontos.getPontosDoFuncionario(cpf, dia, mes, ano);
	}	

	public boolean isDiaCorreto(String cpf, int dia, int mes, int ano)
			throws FuncionarioNaoEncontradoException, NomeInvalidoException {
		return pontos.isDiaCorreto(cpf, dia, mes, ano);
	}
	
	public int getTotalDiasCorretos(String cpf, int mes, int ano)
			throws FuncionarioNaoEncontradoException, NomeInvalidoException {
		int total = 0;
		ArrayList <RegPonto> pontos = getPontosDoFuncionario(cpf, mes, ano);

		for (int i = 0; i < pontos.size(); i+=4){
			if(isDiaCorreto(cpf, pontos.get(i).getAgora().getDayOfMonth(), mes, ano))
					total++;
			}
		 return total;
	}
	
	public int getTotalDiasAtrasado(String cpf, int mes, int ano)
			throws FuncionarioNaoEncontradoException, NomeInvalidoException {
		int total = 0;
		ArrayList <RegPonto> pontos = getPontosDoFuncionario(cpf, mes, ano);

		for (int i = 0; i < pontos.size(); i+=4){
			if(isDiaAtrasado(cpf, pontos.get(i).getAgora().getDayOfMonth(), mes, ano))
					total++;
			}
		 return total;
	}	

	public boolean isDiaAtrasado(String cpf, int dia, int mes, int ano)
			throws FuncionarioNaoEncontradoException, NomeInvalidoException {
		return pontos.isDiaAtrasado(cpf, dia, mes, ano);
	}

	public int totalChegadaCorreta(String cpf)
			throws FuncionarioNaoEncontradoException, NomeInvalidoException {
		return pontos.totalChegadaCorreta(cpf);
	}

	public int totalSaidaCorreta(String cpf)
			throws FuncionarioNaoEncontradoException, NomeInvalidoException {
		return pontos.totalSaidaCorreta(cpf);
	}

	public int totalIntervalo_InCorreta(String cpf)
			throws FuncionarioNaoEncontradoException, NomeInvalidoException {
		return pontos.totalIntervalo_InCorreta(cpf);
	}

	public int totalIntervalo_OutCorreta(String cpf)
			throws FuncionarioNaoEncontradoException, NomeInvalidoException {
		return pontos.totalIntervalo_OutCorreta(cpf);
	}
	
	

}
