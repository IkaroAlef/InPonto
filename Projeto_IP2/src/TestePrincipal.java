import java.time.LocalTime;
import java.util.Scanner;

import dados.RepFuncionario;
import regras.entity_beans.Funcionario;

public class TestePrincipal {
	private static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		RepFuncionario repositorio = new RepFuncionario();
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
		sc.nextLine(); //o nextInt não lê o final da linha, o que causa problema no proxime nextLine (o que le o nome), entao pus esse nextLine pra absorver o resto do último nextInt
		chegada=LocalTime.of(hora, minutos);
		funcionario.setChegada(chegada);
		
		repositorio.cadastrar(funcionario);
		
		System.out.print("Nome do funcionario que deseja buscar: ");
		System.out.println(repositorio.imprimir(repositorio.buscarNome(sc.nextLine())));
		System.out.print("CPF do funcionario que deseja buscar: ");
		System.out.println(repositorio.imprimir(repositorio.buscarCpf(sc.nextLine())));
	}

}
