package dados;

import java.util.ArrayList;

import negócio.entity_beans.Dispensa;
import negócio.entity_beans.Funcionario;

public interface IRepositorioDispensas{
	void adicionarDispensa(Dispensa dispensa);
	Dispensa getDispensa(int i);
	ArrayList<Dispensa> getDispensas(Funcionario funcionario);
	boolean dispensaContains(Funcionario funcionario);
}
