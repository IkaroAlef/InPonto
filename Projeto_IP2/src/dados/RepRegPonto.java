package dados;

import regras.entity_beans.RegPonto;

import java.io.File;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.io.FileOutputStream;

public class RepRegPonto {
	private ArrayList <RegPonto> repositorio;
	
	public RepRegPonto(){
		repositorio = new ArrayList<RegPonto>();
	}
	
	public void adicionarRegistro(RegPonto ponto){
		repositorio.add(ponto);
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
