package dados;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import negócio.entity_beans.Dispensa;
import negócio.entity_beans.Funcionario;

public class RepDispensas implements Serializable, IRepositorioDispensas {
	private ArrayList <Dispensa> dispensas = null; 
	private static RepDispensas instance;
	
	
	public RepDispensas() {
		this.dispensas = new ArrayList <Dispensa>();
	}
	
	public static IRepositorioDispensas getInstance() {
        if (instance == null) {
            instance = lerDoArquivo();
        }
        return instance;
    }
	
	public void adicionarDispensa(Dispensa dispensa){
		dispensas.add(dispensa);
		salvarArquivo();
	}
	
	public Dispensa getDispensa(int i){
		return dispensas.get(i);
	}

	public ArrayList<Dispensa> getDispensas(Funcionario funcionario){
		ArrayList <Dispensa> dispensas = new ArrayList <Dispensa>();
		for (int i = 0; i < this.dispensas.size(); i++){
			if (this.dispensas.get(i).getFuncionario().equals(funcionario))
					dispensas.add(this.dispensas.get(i));		
		}
		return dispensas;
	}
	
	public boolean dispensaContains(Funcionario funcionario){
		boolean retorno = false;
		for (int i = 0; i < this.dispensas.size(); i++){
			if (this.dispensas.get(i).getFuncionario().equals(funcionario))
					retorno = true;		
		}
		return retorno;

	}
	
	private static RepDispensas lerDoArquivo() {
        RepDispensas instanciaLocal = null;

        File in = new File("dispensas.dat");
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(in);
            ois = new ObjectInputStream(fis);
            Object o = ois.readObject();
            instanciaLocal = (RepDispensas) o;
        } catch (Exception e) {
            instanciaLocal = new RepDispensas();
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
        File out = new File("dispensas.dat");
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
