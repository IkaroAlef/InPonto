package dados;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalTime;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.Serializable;

import dados.exceptionsDados.FuncionarioNaoEncontradoException;
import negócio.EpontoFachada;
import negócio.entity_beans.Funcionario;
import negócio.entity_beans.RegPonto;


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
	
	public ArrayList <RegPonto> getPontosDoFuncionario(String cpf) throws FuncionarioNaoEncontradoException{ //procurar pontos desse CPF
		ArrayList <RegPonto> pontosDoFuncionario = new ArrayList <RegPonto>();
		Funcionario funcionario = (Funcionario) EpontoFachada.getInstance().buscarPessoaCpf(cpf);
		for (int i=0;i<this.repositorio.size();i++){
			if (repositorio.get(i).getFuncionario().equals(funcionario))
				pontosDoFuncionario.add(repositorio.get(i));
		}
		
		return pontosDoFuncionario;
	}
	
	public ArrayList <RegPonto> getPontosDoFuncionario(String cpf, int mes, int ano) throws FuncionarioNaoEncontradoException{ //procurar pontos desse CPF nesse mes e ano
		ArrayList <RegPonto> pontosDoFuncionario = new ArrayList <RegPonto>();
		Funcionario funcionario = (Funcionario) EpontoFachada.getInstance().buscarPessoaCpf(cpf);
		for (int i=0;i<this.repositorio.size();i++){
			if (repositorio.get(i).getFuncionario().equals(funcionario) && repositorio.get(i).getAgora().getMonthValue()==mes && repositorio.get(i).getAgora().getYear() == ano)
				pontosDoFuncionario.add(repositorio.get(i));
		}

		return pontosDoFuncionario;
	}
	
	
	public int totalChegadaCorreta(String cpf) throws FuncionarioNaoEncontradoException{ //retorna o total de pontos de chegada corretos (Sem atrasos e Sem faltas)
		int cont=0;
		ArrayList <RegPonto> pontosDoFuncionario = this.getPontosDoFuncionario(cpf);
		for (int i=0; i < pontosDoFuncionario.size(); i++){
			if (pontosDoFuncionario.get(i).chegadaCorreta()){
				cont++;
			}
		}
		return cont;
	}
	
	public int totalSaidaCorreta(String cpf) throws FuncionarioNaoEncontradoException{ //retorna o total de pontos de saida corretos (Sem atrasos e Sem faltas)
		int cont=0;
		ArrayList <RegPonto> pontosDoFuncionario = this.getPontosDoFuncionario(cpf);
		for (int i=0;i<pontosDoFuncionario.size();i++){
			if (pontosDoFuncionario.get(i).saidaCorreta())
				cont++;
		}
		return cont;
	}
	
	public int totalIntervalo_InCorreta(String cpf) throws FuncionarioNaoEncontradoException{ //retorna o total de pontos de Volta do Intervalo corretos (Sem atrasos e Sem faltas)
		int cont=0;
		ArrayList <RegPonto> pontosDoFuncionario = this.getPontosDoFuncionario(cpf);
		for (int i=0;i<pontosDoFuncionario.size();i++){
			if (pontosDoFuncionario.get(i).intervalo_InCorreta())
				cont++;
		}
		return cont;
	}
	
	public int totalIntervalo_OutCorreta(String cpf) throws FuncionarioNaoEncontradoException{ //retorna o total de pontos de Saída pro Intervalo corretos (Sem atrasos e Sem faltas)
		int cont=0;
		ArrayList <RegPonto> pontosDoFuncionario = this.getPontosDoFuncionario(cpf);
		for (int i=0;i<pontosDoFuncionario.size();i++){
			if (pontosDoFuncionario.get(i).intervalo_OutCorreta())
				cont++;
		}
		return cont;
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