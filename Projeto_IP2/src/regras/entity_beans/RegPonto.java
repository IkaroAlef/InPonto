//Classe para Registro de Ponto
package regras.entity_beans;

import java.time.LocalDateTime;

public class RegPonto {
	Funcionario funcionario;
	LocalDateTime chegada, //chegada ao trabalho
	saida, //sa�da do trabalho (largou)
	intervalo_in, //voltou do intervalo)
	intervalo_out; //saiu para o intervalo
}
