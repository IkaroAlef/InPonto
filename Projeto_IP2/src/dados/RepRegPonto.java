package dados;

import regras.entity_beans.RegPonto;
import regras.entity_beans.Funcionario;

import java.io.File;
import java.io.ObjectOutputStream;
import java.time.LocalTime;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.Serializable;


public class RepRegPonto implements Serializable,IRepositorioPontos {
	private ArrayList <RegPonto> repositorio;
	
	public RepRegPonto(){
		repositorio = new ArrayList<RegPonto>();
	}
	
	public void adicionarRegistro(RegPonto ponto){
		repositorio.add(ponto);
	}
	
	public ArrayList <RegPonto> pontosDoFuncionario(String cpf, RepFuncionario repFuncionario){ //procurar pontos desse CPF nesse Repositorio de Funcionarios
		ArrayList <RegPonto> pontosDoFuncionario = new ArrayList <RegPonto>();
		//Falta buscar o Funcionario e depois buscar os pontos desse funcionario no arrayList de Pontos;
		Funcionario funcionario = repFuncionario.buscaFuncionarioCpf(cpf);
		for (int i=0;i<this.repositorio.size();i++){
			if (repositorio.get(i).getFuncionario().equals(funcionario))
				pontosDoFuncionario.add(repositorio.get(i));
		}
		
		return pontosDoFuncionario;
	}
	
	public int totalChegadaCorreta(String cpf, RepFuncionario repFuncionario){ //retorna o total de pontos de chegada corretos (Sem atrasos e Sem faltas)
		int cont=0;
		ArrayList <RegPonto> pontosDoFuncionario = pontosDoFuncionario (cpf,repFuncionario);
		for (int i=0;i<pontosDoFuncionario.size();i++){
			if (pontosDoFuncionario.get(i).chegadaCorreta())
				cont++;
		}
		return cont;
	}
	
	public int totalSaidaCorreta(String cpf, RepFuncionario repFuncionario){ //retorna o total de pontos de saida corretos (Sem atrasos e Sem faltas)
		int cont=0;
		ArrayList <RegPonto> pontosDoFuncionario = pontosDoFuncionario (cpf,repFuncionario);
		for (int i=0;i<pontosDoFuncionario.size();i++){
			if (pontosDoFuncionario.get(i).saidaCorreta())
				cont++;
		}
		return cont;
	}
	
	public int totalIntervalo_InCorreta(String cpf, RepFuncionario repFuncionario){ //retorna o total de pontos de Volta do Intervalo corretos (Sem atrasos e Sem faltas)
		int cont=0;
		ArrayList <RegPonto> pontosDoFuncionario = pontosDoFuncionario (cpf,repFuncionario);
		for (int i=0;i<pontosDoFuncionario.size();i++){
			if (pontosDoFuncionario.get(i).intervalo_InCorreta())
				cont++;
		}
		return cont;
	}
	
	public int totalIntervalo_OutCorreta(String cpf, RepFuncionario repFuncionario){ //retorna o total de pontos de Saída pro Intervalo corretos (Sem atrasos e Sem faltas)
		int cont=0;
		ArrayList <RegPonto> pontosDoFuncionario = pontosDoFuncionario (cpf,repFuncionario);
		for (int i=0;i<pontosDoFuncionario.size();i++){
			if (pontosDoFuncionario.get(i).intervalo_OutCorreta())
				cont++;
		}
		return cont;
	}
	
	public void gravarDisco() throws Exception{
		if (!new File("C:\\Pontos\\pontos.pontos").exists()){ //se a pasta não existe, então cria com os arquivos abaixo
			new File("C:\\Pontos\\pontos.pontos").mkdir(); // criar pasta 
			
			FileOutputStream pontos = new FileOutputStream("C:\\Pontos\\pontos.pontos"); //arquivo que armazena o Histórico de Pontos. Não grava aqui. Só no registro de Pontos.
			ObjectOutputStream gravarArq = new ObjectOutputStream(pontos);
			gravarArq.writeObject(this.repositorio);
			pontos.close();
		}
	}	
}