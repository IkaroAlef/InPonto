import dados.Funcionario;
import java.time.LocalTime;
import java.util.Scanner;

public class TestePrincipal {
	private static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		LocalTime chegada;
		int hora;
		int minutos;
		Funcionario funcionario = new Funcionario();
		System.out.print("Digite a hora: ");
		hora = sc.nextInt();
		System.out.print("Digite os minutos: ");
		minutos = sc.nextInt();
		chegada=LocalTime.of(hora, minutos);
		funcionario.setChegada(chegada);
		System.out.println("Hora de chegada: "+funcionario.getChegada());
		
	}

}
