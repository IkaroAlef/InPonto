import java.time.LocalTime;
import java.util.Scanner;
//import regras.entity_beans.*;



import dados.RepFuncionario;
import dados.RepRegPonto;
import regras.entity_beans.Funcionario;
import regras.entity_beans.RegPonto;

public class TestePrincipal{
	private static Scanner sc = new Scanner(System.in);
	public static void main(String[] args)throws Exception {
		RepFuncionario repFuncionario = new RepFuncionario();
		Funcionario funcionario;
		LocalTime chegada,
		saida,
		intervalo_in,
		intervalo_out;
		int hora;
		int minutos;
		
		System.out.print("Digite a hora de chegada: ");
		hora = sc.nextInt();
		System.out.print("Digite os minutos de chegada: ");
		minutos = sc.nextInt();
		
		chegada=LocalTime.of(hora, minutos);
		saida=LocalTime.of(hora, minutos);
		intervalo_in=LocalTime.of(hora, minutos);
		intervalo_out=LocalTime.of(hora, minutos);
		sc.nextLine(); //o nextInt não lê o final da linha, o que causa problema no proxime nextLine (o que le o nome), entao pus esse nextLine pra absorver o resto do último nextInt
		
		funcionario = new Funcionario("Ikaro Alef","103.440.224-20","ikaroalef@gmail.com","1234","UFRPE","Estudante","8h/dia",chegada,saida,intervalo_in,intervalo_out);
		repFuncionario.adicionarFuncionario(funcionario);
		
		System.out.print("Nome do funcionario que deseja buscar: ");
		System.out.println(repFuncionario.imprimir(repFuncionario.buscarNome(sc.nextLine())));
		
		RegPonto ponto = new RegPonto();
		RepRegPonto repPonto= new RepRegPonto();
		
		ponto.registrarPonto(funcionario);
		repPonto.adicionarRegistro(ponto);
	}

}
