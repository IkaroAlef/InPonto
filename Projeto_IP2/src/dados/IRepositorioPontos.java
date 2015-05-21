package dados;

import java.util.ArrayList;

import regras.entity_beans.RegPonto;

public interface IRepositorioPontos {
	
	void adicionarRegistro(RegPonto ponto);
	
	ArrayList <RegPonto> pontosDoFuncionario(String cpf, IRepositorioFuncionarios repFuncionario);
	
	int totalChegadaCorreta(String cpf, IRepositorioFuncionarios repFuncionario);
	int totalSaidaCorreta(String cpf, IRepositorioFuncionarios repFuncionario);
	int totalIntervalo_InCorreta(String cpf, IRepositorioFuncionarios repFuncionario);
	int totalIntervalo_OutCorreta(String cpf, IRepositorioFuncionarios repFuncionario);
	
	
	
}
