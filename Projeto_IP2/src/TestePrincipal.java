import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;
//import regras.entity_beans.*;








import negócio.EpontoFachada;
import negócio.entity_beans.Empresa;
import negócio.entity_beans.Funcionario;
import negócio.entity_beans.RegPonto;
import dados.IRepositorioPessoas;
import dados.RepPessoas;
import dados.RepRegPonto;
import dados.IRepositorioPontos;
import dados.exceptionsDados.FuncionarioNaoEncontradoException;

public class TestePrincipal{
	private static Scanner sc = new Scanner(System.in);
	public static void main(String[] args)throws Exception {
		EpontoFachada fachada = EpontoFachada.getInstance();
		
		char[] senha = {'1','2','3','4'};
		Empresa empresa = new Empresa("UFRPE","2414","25","235");
		Funcionario funcionario3 = new Funcionario("Lima","123","lima@gmail",senha,"telefone", empresa,"Estudante","8h/dia",LocalTime.of(8,0),LocalTime.of(12,0),LocalTime.of(10,0),LocalTime.of(10,15));
		fachada.adicionarPessoa(funcionario3);
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
			
			funcionario = new Funcionario("Ikaro","103","ikaroalef@gmail.com",senha,"telefone",empresa,"Estudante","8h/dia",chegada,saida,intervalo_in,intervalo_out);
			Funcionario funcionario1 = new Funcionario("Alef","1234","alef@gmail.com",senha,"telefone",empresa,"Estudante","8h/dia",chegada,saida,intervalo_in,intervalo_out);
			fachada.adicionarPessoa(funcionario);
			fachada.adicionarPessoa(funcionario1);
			System.out.println("Sair?");
			if(sc.nextLine().equals("sim"))
				sair=true;
			break;
		
		case 2: 
			System.out.print("Nome do funcionario que deseja buscar: ");
			try{
			System.out.println(fachada.getString(fachada.buscarIndiceNome(sc.nextLine())));
			}catch (FuncionarioNaoEncontradoException e){
				System.out.println(e.getMessage());
			}
			System.out.println("Sair?");
			if(sc.nextLine().equals("sim"))
				sair=true;
			break;
		case 3:
			RegPonto ponto = new RegPonto();
			System.out.println("CPF que deseja registrar ponto: ");
			String cpf = sc.nextLine();
			ponto.registrarPonto((Funcionario) fachada.buscarPessoaCpf(cpf));
			fachada.adicionarRegistro(ponto);
			System.out.println("Sair?");
			if(sc.nextLine().equals("sim"))
				sair=true;
			break;
		case 4: 
			System.out.println("CPF do funcionario que deseja todos os pontos: ");
			cpf = sc.nextLine();
			for (RegPonto p: fachada.getPontosDoFuncionario(cpf)){
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
//			fachada.adicionarRegistro(new RegPonto(LocalDateTime.of(2015,05,15,8,0),funcionario3));
//      	fachada.adicionarRegistro(new RegPonto(LocalDateTime.of(2015,05,16,8,15),funcionario3));
//			fachada.adicionarRegistro(new RegPonto(LocalDateTime.of(2015,05,17,7,45),funcionario3));
//			fachada.adicionarRegistro(new RegPonto(LocalDateTime.of(2015,05,18,8,0),funcionario3));
			System.out.println(fachada.totalChegadaCorreta(cpf1));
			break;
		case 7:
			RegPonto ponto2 = new RegPonto();
			System.out.println("Hora que deseja registrar ponto: ");
			int hora2 = sc.nextInt();
			sc.nextLine();
			System.out.println("CPF do funcionario: ");
			cpf = sc.nextLine();
			fachada.adicionarRegistro(new RegPonto(LocalDateTime.of(2015,06,22,hora2,0),((Funcionario) fachada.buscarPessoaCpf(cpf))));
			fachada.adicionarRegistro(new RegPonto(LocalDateTime.of(2015,06,23,hora2,0),((Funcionario) fachada.buscarPessoaCpf(cpf))));
			fachada.adicionarRegistro(new RegPonto(LocalDateTime.of(2015,06,24,hora2,0),((Funcionario) fachada.buscarPessoaCpf(cpf))));
			fachada.adicionarRegistro(new RegPonto(LocalDateTime.of(2015,06,25,hora2,0),((Funcionario) fachada.buscarPessoaCpf(cpf))));
			fachada.adicionarRegistro(new RegPonto(LocalDateTime.of(2015,06,26,hora2+1,0),((Funcionario) fachada.buscarPessoaCpf(cpf))));
			fachada.adicionarRegistro(new RegPonto(LocalDateTime.of(2015,06,29,hora2+1,0),((Funcionario) fachada.buscarPessoaCpf(cpf))));
			System.out.println("Sair?");
			if(sc.nextLine().equals("sim"))
				sair=true;
			break;
		}
				
		}while(!sair);
	}

}
