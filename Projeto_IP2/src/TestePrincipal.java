import java.time.LocalTime;
import java.util.Scanner;

import dados.RepositorioFunc;
import regras.entity_beans.Funcionario;

public class TestePrincipal {
	private static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		RepositorioFunc repositorio = new RepositorioFunc();
		LocalTime chegada;
		int hora;
		int minutos;
		Funcionario funcionario = new Funcionario();
		System.out.print("Nome: ");
		funcionario.setNome(sc.nextLine());
		System.out.print("CPF: ");
		funcionario.setCpf(sc.nextLine());
		System.out.print("Digite a hora: ");
		hora = sc.nextInt();
		System.out.print("Digite os minutos: ");
		minutos = sc.nextInt();
		chegada=LocalTime.of(hora, minutos);
		funcionario.setChegada(chegada);
		
		repositorio.cadastrar(funcionario);
		
		System.out.print("Buscar Funcionario na posição: ");
		int i;
		i=sc.nextInt();
		System.out.println(repositorio.buscar(i));
	}

}
