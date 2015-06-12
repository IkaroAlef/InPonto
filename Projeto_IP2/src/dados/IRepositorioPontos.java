package dados;

import java.util.ArrayList;

import negócio.entity_beans.RegPonto;
import exceptionsDados.FuncionarioNaoEncontradoException;

public interface IRepositorioPontos {
	
	void adicionarRegistro(RegPonto ponto);
	
	ArrayList <RegPonto> pontosDoFuncionario(String cpf, IRepositorioPessoas repFuncionario) throws FuncionarioNaoEncontradoException;
	
	int totalChegadaCorreta(String cpf, IRepositorioPessoas repFuncionario) throws FuncionarioNaoEncontradoException;
	int totalSaidaCorreta(String cpf, IRepositorioPessoas repFuncionario) throws FuncionarioNaoEncontradoException;
	int totalIntervalo_InCorreta(String cpf, IRepositorioPessoas repFuncionario) throws FuncionarioNaoEncontradoException;
	int totalIntervalo_OutCorreta(String cpf, IRepositorioPessoas repFuncionario) throws FuncionarioNaoEncontradoException;
	
	
	
}
