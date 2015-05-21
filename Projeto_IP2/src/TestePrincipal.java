import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;
//import regras.entity_beans.*;


import dados.IRepositorioFuncionarios;
import dados.RepFuncionario;
import dados.RepRegPonto;
import regras.entity_beans.Funcionario;
import regras.entity_beans.RegPonto;
import dados.IRepositorioPontos;

public class TestePrincipal{
	private static Scanner sc = new Scanner(System.in);
	public static void main(String[] args)throws Exception {
		IRepositorioFuncionarios repFuncionario = new RepFuncionario();
		IRepositorioPontos pontos = new RepRegPonto();
		Funcionario funcionario3 = new Funcionario("Lima","123","lima@gmail","1234","UFRPE","Estudante","8h/dia",LocalTime.of(8,0),LocalTime.of(12,0),LocalTime.of(10,0),LocalTime.of(10,15));
		repFuncionario.adicionarFuncionario(funcionario3);
		int op;
		boolean sair=false;
		do{
		System.out.println("\n1- Cadastrar"
						+ "\n2- Consultar Funcionario"
						+ "\n3- Registrar Ponto"
						+ "\n4- Todos os pontos de um funcionario"
						+ "\n5- Sair"
						+ "\n6- Total de pontos corretos de chegada de um funcionario");
		op = sc.nextInt();
		sc.nextLine();
		switch (op){
		case 1:
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
			
			funcionario = new Funcionario("Ikaro","12345","ikaroalef@gmail.com","1234","UFRPE","Estudante","8h/dia",chegada,saida,intervalo_in,intervalo_out);
			Funcionario funcionario1 = new Funcionario("Alef","1234","alef@gmail.com","1234","UFRPE","Estudante","8h/dia",chegada,saida,intervalo_in,intervalo_out);
			repFuncionario.adicionarFuncionario(funcionario);
			repFuncionario.adicionarFuncionario(funcionario1);
//			repFuncionario.exportar();
//			System.out.println(repFuncionario.imprimir(repFuncionario.buscarNome("Ikaro")));
			System.out.println("Sair?");
			if(sc.nextLine().equals("sim"))
				sair=true;
			break;
		
		case 2: 
			//repFuncionario = new RepFuncionario (RepFuncionario.importar());
			System.out.print("Nome do funcionario que deseja buscar: ");
			System.out.println(repFuncionario.imprimir(repFuncionario.buscarNome(sc.nextLine())));

			System.out.println("Sair?");
			if(sc.nextLine().equals("sim"))
				sair=true;
			break;
		case 3:
			RegPonto ponto = new RegPonto();
			System.out.println("CPF que deseja registrar ponto: ");
			String cpf = sc.nextLine();
			ponto.registrarPonto(repFuncionario.buscaFuncionarioCpf(cpf));
			pontos.adicionarRegistro(ponto);
			System.out.println("Sair?");
			if(sc.nextLine().equals("sim"))
				sair=true;
			break;
		case 4: 
			System.out.println("CPF do funcionario que deseja todos os pontos: ");
			cpf = sc.nextLine();
			for (RegPonto p: pontos.pontosDoFuncionario(cpf, repFuncionario)){
				System.out.println(p);
			}
			System.out.println("Sair?");
			if(sc.nextLine().equals("sim"))
				sair=true;
			break;
		case 5:
			sair=true;
			break;
		case 6:
			System.out.println("CPF do funcionario que deseja todos os pontos corretos: ");
			String cpf1 = sc.nextLine();
			pontos.adicionarRegistro(new RegPonto(LocalDateTime.of(2015,05,15,8,0),funcionario3));
			pontos.adicionarRegistro(new RegPonto(LocalDateTime.of(2015,05,16,8,0),funcionario3));
			pontos.adicionarRegistro(new RegPonto(LocalDateTime.of(2015,05,17,8,0),funcionario3));
			pontos.adicionarRegistro(new RegPonto(LocalDateTime.of(2015,05,18,8,0),funcionario3));
			System.out.println(pontos.totalChegadaCorreta(cpf1, repFuncionario));
			break;
		}
		}while(!sair);
	}

}
