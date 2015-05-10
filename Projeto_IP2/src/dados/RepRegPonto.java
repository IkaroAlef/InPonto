package dados;

import regras.entity_beans.RegPonto;
import regras.entity_beans.Funcionario;

import java.io.File;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.Serializable;


public class RepRegPonto implements Serializable {
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