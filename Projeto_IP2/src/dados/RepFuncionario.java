package dados;
import java.util.ArrayList;

import regras.entity_beans.Funcionario;

public class RepFuncionario {
	private ArrayList<Funcionario>funcionario;
	
	public RepFuncionario(){
		funcionario = new ArrayList<Funcionario>();
	}
	
	public void cadastrar(Funcionario funcionario){
		this.funcionario.add(funcionario);
	}
	
	public String buscar(int i){
		return String.valueOf(this.funcionario.get(i));
	}
	
	
}
