//Classe para Registro de Ponto
package regras.entity_beans;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
//import java.io.PrintWriter;


public class RegPonto {
//	Funcionario funcionario;
	LocalDateTime chegada, //chegada ao trabalho
	saida, //saída do trabalho (largou)
	intervalo_in, //voltou do intervalo)
	intervalo_out; //saiu para o intervalo
	
	public RegPonto(){
		
	}
	
	public static void RegistrarPonto(String cpf) throws Exception{
		FileWriter pontos = new FileWriter("C:\\"+cpf+"\\"+cpf+".pontos",true); //arquivo que armazena o Histórico de Pontos. Não grava aqui. Só no registro de Pontos.
		PrintWriter gravar = new PrintWriter(pontos);
		String agora = String.valueOf(LocalDateTime.now());
		gravar.append(agora+"\n");
		pontos.close();
	}

}
