package dados;

import java.util.ArrayList;

import regras.entity_beans.RegPonto;

public interface IRepositorioPontos {
	
	void adicionarRegistro(RegPonto ponto);
	
	ArrayList <RegPonto> pontosDoFuncionario(String cpf, RepFuncionario repFuncionario);
	
	int totalChegadaCorreta(String cpf, RepFuncionario repFuncionario);
	int totalSaidaCorreta(String cpf, RepFuncionario repFuncionario);
	int totalIntervalo_InCorreta(String cpf, RepFuncionario repFuncionario);
	int totalIntervalo_OutCorreta(String cpf, RepFuncionario repFuncionario);
	
	
	
}
