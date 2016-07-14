package dados;

import java.io.IOException;
import java.util.ArrayList;

import dados.exceptionsDados.FuncionarioNaoEncontradoException;
import negócio.entity_beans.RegPonto;
import negócio.entity_beans.exceptionsBeans.NomeInvalidoException;

public interface IRepositorioPontos {
	
	void adicionarRegistro(RegPonto ponto);
	
	ArrayList <RegPonto> getPontosDoFuncionario(String cpf) throws FuncionarioNaoEncontradoException, NomeInvalidoException, IOException;
	ArrayList <RegPonto> getPontosDoFuncionario(String cpf, int mes, int ano) throws FuncionarioNaoEncontradoException, NomeInvalidoException, IOException;
	ArrayList <RegPonto> getPontosDoFuncionario(String cpf,int dia, int mes, int ano) throws FuncionarioNaoEncontradoException, NomeInvalidoException, IOException;
	
	int totalChegadaCorreta(String cpf) throws FuncionarioNaoEncontradoException, NomeInvalidoException, IOException;
	int totalSaidaCorreta(String cpf) throws FuncionarioNaoEncontradoException, NomeInvalidoException, IOException;
	int totalIntervalo_InCorreta(String cpf) throws FuncionarioNaoEncontradoException, NomeInvalidoException, IOException;
	int totalIntervalo_OutCorreta(String cpf) throws FuncionarioNaoEncontradoException, NomeInvalidoException, IOException;
	
	boolean isDiaCorreto(String cpf, int dia, int mes, int ano) throws FuncionarioNaoEncontradoException, NomeInvalidoException, IOException;
	boolean isDiaAtrasado(String cpf, int dia, int mes, int ano) throws FuncionarioNaoEncontradoException, NomeInvalidoException, IOException;
	
	
	
}
