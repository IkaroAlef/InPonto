import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
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
		RepRegPonto pontos = new RepRegPonto();
		int op;
		boolean sair=false;
		do{
		System.out.println("\n1- Cadastrar"
						+ "\n2- Consultar Funcionario"
						+ "\n3- Registrar Ponto"
						+ "\n4- Todos os pontos de um funcionario");
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
			break;
		case 4: 
			System.out.println("CPF do funcionario que deseja todos os pontos: ");
			cpf = sc.nextLine();
			for (RegPonto p: pontos.pontosDoFuncionario(cpf, repFuncionario)){
				System.out.println(p);
			}
		}
		}while(!sair);
	}

}
