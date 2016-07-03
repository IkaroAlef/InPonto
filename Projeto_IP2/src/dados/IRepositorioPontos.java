package dados;

import java.util.ArrayList;

import dados.exceptionsDados.FuncionarioNaoEncontradoException;
import neg�cio.entity_beans.RegPonto;
import neg�cio.entity_beans.exceptionsBeans.NomeInvalidoException;

public interface IRepositorioPontos {
	
	void adicionarRegistro(RegPonto ponto);
	
	ArrayList <RegPonto> getPontosDoFuncionario(String cpf) throws FuncionarioNaoEncontradoException, NomeInvalidoException;
	ArrayList <RegPonto> getPontosDoFuncionario(String cpf, int mes, int ano) throws FuncionarioNaoEncontradoException, NomeInvalidoException;
	ArrayList <RegPonto> getPontosDoFuncionario(String cpf,int dia, int mes, int ano) throws FuncionarioNaoEncontradoException, NomeInvalidoException;
	
	int totalChegadaCorreta(String cpf) throws FuncionarioNaoEncontradoException, NomeInvalidoException;
	int totalSaidaCorreta(String cpf) throws FuncionarioNaoEncontradoException, NomeInvalidoException;
	int totalIntervalo_InCorreta(String cpf) throws FuncionarioNaoEncontradoException, NomeInvalidoException;
	int totalIntervalo_OutCorreta(String cpf) throws FuncionarioNaoEncontradoException, NomeInvalidoException;
	
	boolean isDiaCorreto(String cpf, int dia, int mes, int ano) throws FuncionarioNaoEncontradoException, NomeInvalidoException;
	boolean isDiaAtrasado(String cpf, int dia, int mes, int ano) throws FuncionarioNaoEncontradoException, NomeInvalidoException;
	
	
	
}
