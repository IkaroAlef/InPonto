package negócio.entity_beans;

import java.io.Serializable;

import javafx.scene.control.PasswordField;

public class Admin extends Pessoa implements Serializable{
	
	public Admin(String nome, String cpf, String email, char[] senha){
		super (nome,cpf,email,senha);
	}

}
