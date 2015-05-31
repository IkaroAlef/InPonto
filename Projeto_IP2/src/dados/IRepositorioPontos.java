package dados;

import java.util.ArrayList;

import exceptionsDados.FuncionarioNaoEncontradoException;
import regras.entity_beans.RegPonto;

public interface IRepositorioPontos {
	
	void adicionarRegistro(RegPonto ponto);
	
	ArrayList <RegPonto> pontosDoFuncionario(String cpf, IRepositorioFuncionarios repFuncionario) throws FuncionarioNaoEncontradoException;
	
	int totalChegadaCorreta(String cpf, IRepositorioFuncionarios repFuncionario) throws FuncionarioNaoEncontradoException;
	int totalSaidaCorreta(String cpf, IRepositorioFuncionarios repFuncionario) throws FuncionarioNaoEncontradoException;
	int totalIntervalo_InCorreta(String cpf, IRepositorioFuncionarios repFuncionario) throws FuncionarioNaoEncontradoException;
	int totalIntervalo_OutCorreta(String cpf, IRepositorioFuncionarios repFuncionario) throws FuncionarioNaoEncontradoException;
	
	
	
}
