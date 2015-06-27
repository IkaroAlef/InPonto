package dados;

import java.util.ArrayList;

import dados.exceptionsDados.FuncionarioNaoEncontradoException;
import negócio.entity_beans.RegPonto;

public interface IRepositorioPontos {
	
	void adicionarRegistro(RegPonto ponto);
	
	ArrayList <RegPonto> getPontosDoFuncionario(String cpf) throws FuncionarioNaoEncontradoException;
	ArrayList <RegPonto> getPontosDoFuncionario(String cpf, int mes, int ano) throws FuncionarioNaoEncontradoException;
	
	int totalChegadaCorreta(String cpf) throws FuncionarioNaoEncontradoException;
	int totalSaidaCorreta(String cpf) throws FuncionarioNaoEncontradoException;
	int totalIntervalo_InCorreta(String cpf) throws FuncionarioNaoEncontradoException;
	int totalIntervalo_OutCorreta(String cpf) throws FuncionarioNaoEncontradoException;
	
	
	
}
