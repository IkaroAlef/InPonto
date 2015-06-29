package neg�cio;

import java.util.ArrayList;

import neg�cio.entity_beans.Dispensa;
import neg�cio.entity_beans.Funcionario;
import dados.IRepositorioDispensas;
import dados.RepDispensas;

public class ControladorDispensas {
	private IRepositorioDispensas dispensas;
	
	public ControladorDispensas() {
		this.dispensas = RepDispensas.getInstance();
	}

	public void adicionarDispensa(Dispensa dispensa) {
		dispensas.adicionarDispensa(dispensa);
	}

	public Dispensa getDispensa(int i) {
		return dispensas.getDispensa(i);
	}

	public ArrayList<Dispensa> getDispensas(Funcionario funcionario) {
		return dispensas.getDispensas(funcionario);
	}
	
	public boolean dispensaContains(Funcionario funcionario){
		return dispensas.dispensaContains(funcionario);
	}

}
