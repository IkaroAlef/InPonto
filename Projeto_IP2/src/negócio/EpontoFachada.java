package negócio;

import dados.RepPessoas;

public class EpontoFachada {

	private static EpontoFachada instance;
	private ControladorPessoas controladorPessoa = new ControladorPessoas(new RepPessoas());
	
	public EpontoFachada() {

	}
	
	public static EpontoFachada getInstance(){
		if(instance==null)
			instance = new EpontoFachada();
		return instance;
	}
	
	

}
