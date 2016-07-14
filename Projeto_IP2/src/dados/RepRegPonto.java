package dados;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.Serializable;

import dados.exceptionsDados.FuncionarioNaoEncontradoException;
import negócio.EpontoFachada;
import negócio.entity_beans.Funcionario;
import negócio.entity_beans.RegPonto;
import negócio.entity_beans.exceptionsBeans.NomeInvalidoException;


public class RepRegPonto implements Serializable,IRepositorioPontos {
	private ArrayList <RegPonto> repositorio;
	
	private static RepRegPonto instance;
	
	public RepRegPonto(){
		repositorio = new ArrayList<RegPonto>();
	}
	
	public static IRepositorioPontos getInstance(){
		if (instance==null)
			instance = lerDoArquivo();
		
		return instance;	
	}
	
	public void adicionarRegistro(RegPonto ponto){
		repositorio.add(ponto);
		salvarArquivo();
	}
	
	public ArrayList <RegPonto> getPontosDoFuncionario(String cpf) throws FuncionarioNaoEncontradoException, NomeInvalidoException, IOException{ //procurar pontos desse CPF
		ArrayList <RegPonto> pontosDoFuncionario = new ArrayList <RegPonto>();
		Funcionario funcionario = (Funcionario) EpontoFachada.getInstance().getPessoaCpf(cpf);
		for (int i=0;i<this.repositorio.size();i++){
			if (repositorio.get(i).getFuncionario().equals(funcionario))
				pontosDoFuncionario.add(repositorio.get(i));
		}
		
		return pontosDoFuncionario;
	}
	
	public ArrayList <RegPonto> getPontosDoFuncionario(String cpf, int mes, int ano) throws FuncionarioNaoEncontradoException, NomeInvalidoException, IOException{ //procurar pontos desse CPF nesse mes e ano
		ArrayList <RegPonto> pontosDoFuncionario = new ArrayList <RegPonto>();
		Funcionario funcionario = (Funcionario) EpontoFachada.getInstance().getPessoaCpf(cpf);
		for (int i=0;i<this.repositorio.size();i++){
			if (repositorio.get(i).getFuncionario().equals(funcionario) && repositorio.get(i).getAgora().getMonthValue()==mes && repositorio.get(i).getAgora().getYear() == ano)
				pontosDoFuncionario.add(repositorio.get(i));
		}

		return pontosDoFuncionario;
	}
	
	public ArrayList <RegPonto> getPontosDoFuncionario(String cpf,int dia, int mes, int ano) throws FuncionarioNaoEncontradoException, NomeInvalidoException, IOException{ //procurar pontos desse CPF nesse mes e ano
		ArrayList <RegPonto> pontosDoFuncionario = new ArrayList <RegPonto>();
		Funcionario funcionario = (Funcionario) EpontoFachada.getInstance().getPessoaCpf(cpf);
		for (int i=0;i<this.repositorio.size();i++){
			if (repositorio.get(i).getFuncionario().equals(funcionario) && repositorio.get(i).getAgora().getYear() == ano && repositorio.get(i).getAgora().getMonthValue()==mes && repositorio.get(i).getAgora().getDayOfMonth() == dia)
				pontosDoFuncionario.add(repositorio.get(i));
		}

		return pontosDoFuncionario;
	}
	
	
	public int totalChegadaCorreta(String cpf) throws FuncionarioNaoEncontradoException, NomeInvalidoException, IOException{ //retorna o total de pontos de chegada corretos (Sem atrasos e Sem faltas)
		int cont=0;
		ArrayList <RegPonto> pontosDoFuncionario = this.getPontosDoFuncionario(cpf);
		for (int i=0; i < pontosDoFuncionario.size(); i++){
			if (pontosDoFuncionario.get(i).isChegadaCorreta()){
				cont++;
			}
		}
		return cont;
	}
	
	public int totalSaidaCorreta(String cpf) throws FuncionarioNaoEncontradoException, NomeInvalidoException, IOException{ //retorna o total de pontos de saida corretos (Sem atrasos e Sem faltas)
		int cont=0;
		ArrayList <RegPonto> pontosDoFuncionario = this.getPontosDoFuncionario(cpf);
		for (int i=0;i<pontosDoFuncionario.size();i++){
			if (pontosDoFuncionario.get(i).isSaidaCorreta())
				cont++;
		}
		return cont;
	}
	
	public int totalIntervalo_InCorreta(String cpf) throws FuncionarioNaoEncontradoException, NomeInvalidoException, IOException{ //retorna o total de pontos de Volta do Intervalo corretos (Sem atrasos e Sem faltas)
		int cont=0;
		ArrayList <RegPonto> pontosDoFuncionario = this.getPontosDoFuncionario(cpf);
		for (int i=0;i<pontosDoFuncionario.size();i++){
			if (pontosDoFuncionario.get(i).isIntervalo_InCorreta())
				cont++;
		}
		return cont;
	}
	
	public int totalIntervalo_OutCorreta(String cpf) throws FuncionarioNaoEncontradoException, NomeInvalidoException, IOException{ //retorna o total de pontos de Saída pro Intervalo corretos (Sem atrasos e Sem faltas)
		int cont=0;
		ArrayList <RegPonto> pontosDoFuncionario = this.getPontosDoFuncionario(cpf);
		for (int i=0;i<pontosDoFuncionario.size();i++){
			if (pontosDoFuncionario.get(i).isIntervalo_OutCorreta())
				cont++;
		}
		return cont;
	}
	
	public boolean isDiaCorreto(String cpf, int dia, int mes, int ano) throws FuncionarioNaoEncontradoException, NomeInvalidoException, IOException{
		boolean retorno=false;
		ArrayList <RegPonto> pontos = getPontosDoFuncionario(cpf, dia, mes, ano);
		retorno = pontos.get(0).isChegadaCorreta() && pontos.get(1).isIntervalo_OutCorreta() && pontos.get(2).isIntervalo_InCorreta() && pontos.get(3).isSaidaCorreta();
		return retorno;
	}

	public boolean isDiaAtrasado(String cpf, int dia, int mes, int ano) throws FuncionarioNaoEncontradoException, NomeInvalidoException, IOException{
		boolean retorno=false;
			retorno = !isDiaCorreto(cpf, dia, mes, ano);
		return retorno;
	}
	
	private static RepRegPonto lerDoArquivo() {
        RepRegPonto instanciaLocal = null;

        File in = new File("pontos.dat");
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(in);
            ois = new ObjectInputStream(fis);
            Object o = ois.readObject();
            instanciaLocal = (RepRegPonto) o;
        } catch (Exception e) {
            instanciaLocal = new RepRegPonto();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {/* Silent exception */
                }
            }
        }

        return instanciaLocal;
    }
	
	public static void salvarArquivo() {
        if (instance == null) {
            return;
        }
        File out = new File("pontos.dat");
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        
        try {
            fos = new FileOutputStream(out);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(instance);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try { oos.close(); } catch (IOException e) {/*Silent*/}
            }
        }
    }

	
}