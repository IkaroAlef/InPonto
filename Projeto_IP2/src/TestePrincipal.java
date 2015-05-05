import java.time.LocalTime;
import java.util.Scanner;

import dados.RepFuncionario;
import regras.entity_beans.Funcionario;

public class TestePrincipal {
	private static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		RepFuncionario repositorio = new RepFuncionario();
		Funcionario funcionario;
		LocalTime chegada,
		saida,
		intervalo_in,
		intervalo_out;
		String nome,
		cpf,
		email,
		senha,
		empresa,
		cargo,
		escala;
		int hora;
		int minutos;
		
		System.out.print("Nome: ");
		nome=sc.nextLine();
		System.out.print("CPF: ");
		cpf=sc.nextLine();
		System.out.print("Email: ");
		email=sc.nextLine();
		System.out.print("Senha:");
		senha=sc.nextLine();
		System.out.print("Empresa: ");
		empresa=sc.nextLine();
		System.out.print("Cargo: ");
		cargo=sc.nextLine();
		System.out.print("Escala: ");
		escala=sc.nextLine();
		System.out.print("Digite a hora de chegada: ");
		hora = sc.nextInt();
		System.out.print("Digite os minutos de chegada: ");
		minutos = sc.nextInt();
		chegada=LocalTime.of(hora, minutos);
		saida=LocalTime.of(hora, minutos);
		intervalo_in=LocalTime.of(hora, minutos);
		intervalo_out=LocalTime.of(hora, minutos);
		sc.nextLine(); //o nextInt não lê o final da linha, o que causa problema no proxime nextLine (o que le o nome), entao pus esse nextLine pra absorver o resto do último nextInt
		
		repositorio.cadastrar(new Funcionario(nome,cpf,email,senha,empresa,cargo,escala,chegada,saida,intervalo_in,intervalo_out));
		
		System.out.print("Nome do funcionario que deseja buscar: ");
		System.out.println(repositorio.imprimir(repositorio.buscarNome(sc.nextLine())));
		System.out.print(" CPF do funcionario que deseja buscar: ");
		System.out.println(repositorio.imprimir(repositorio.buscarCpf(sc.nextLine())));
	}

}
