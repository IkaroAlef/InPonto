//Repositorio de Empresas.
package dados;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import dados.exceptionsDados.CnpjNaoEncontradoException;
import dados.exceptionsDados.EmpresaNaoEncontradaException;
import negócio.entity_beans.Empresa;
import negócio.entity_beans.Pessoa;

public class RepEmpresa implements IRepositorioEmpresas ,Serializable{

	private ArrayList<Empresa>empresas;
	
	private static RepEmpresa instance;
	
	public RepEmpresa(){
		empresas = new ArrayList<Empresa>();
	}
	
	public RepEmpresa(ArrayList<Empresa> empresas){
		this.empresas = empresas;
	}
	
	public static IRepositorioEmpresas getInstance() {
        if (instance == null) {
            instance = lerDoArquivo();
        }
        return instance;
    }
	
	//Cadastro de empresa no array.
	public void adicionarEmpresa(Empresa empresa){
		this.empresas.add(empresa);
		this.salvarArquivo();
	}
	
	//Retorna Empresa.
	public String imprimir(int i){ 
		return String.valueOf(this.empresas.get(i));
	}
	
	//Busca pelo nomeEmpresa e Retorna o indice da empresa no array.
	public int buscarIndiceNomeEmpresa(String nomeEmpresa) throws EmpresaNaoEncontradaException{ 
		int resultado=-1;
		for (int i=0;i<this.empresas.size();i++){
			if (nomeEmpresa!= null && this.empresas.get(i).igualNome(nomeEmpresa)){
				resultado = i;
			}
		}
		if(resultado == -1)
			throw new EmpresaNaoEncontradaException(nomeEmpresa);

		return resultado;
	}
	
	//Busca pelo CNPJ e Retorna o indice da empresa no array.
	public int buscarIndiceCNPJ(String cnpj) throws CnpjNaoEncontradoException{ 
		int resultado=-1;
		for (int i=0;i<this.empresas.size();i++){
			if (this.empresas.get(i).igualCNPJ(cnpj)){
				resultado=i;
			}
		}
		if (resultado == -1)
			throw new CnpjNaoEncontradoException(cnpj);
		return resultado;
	}
	
	//Buscar Empresa pelo nome.
	public Empresa buscarEmpresaNome(String nomeEmpresa) throws EmpresaNaoEncontradaException{ 
		return this.empresas.get(buscarIndiceNomeEmpresa(nomeEmpresa));
	}
	
	//Buscar Empresa pelo CNPJ.
	public Empresa buscarEmpresaCNPJ(String cnpj) throws CnpjNaoEncontradoException{ 
		return this.empresas.get(buscarIndiceCNPJ(cnpj));
	}
	
	//Deleta um Empresa pelo Nome.
	public void deletarEmpresa(String nomeEmpresa){
		empresas.remove(nomeEmpresa);
		this.salvarArquivo();
	}
	
	public void editarEmpresa(int i,Empresa empresa){
		this.empresas.set(i, empresa);
		this.salvarArquivo();
	}

	public void deletarEmpresa(int i) {
		empresas.remove(i);
		this.salvarArquivo();
	}

	@Override
	public ArrayList<Empresa> getEmpresas(String conteudo) {
		ArrayList<Empresa> retorno = new ArrayList<Empresa>();
		if(conteudo==null)
			return this.empresas;
		else{
			for(int i=0;i<empresas.size();i++){
				if(empresas.get(i).getNomeEmpresa().toUpperCase().contains(conteudo.toUpperCase()))
					retorno.add(empresas.get(i));
			}
		}
		return retorno;
	}
	
	private static RepEmpresa lerDoArquivo() {
        RepEmpresa instanciaLocal = null;

        File in = new File("empresas.dat");
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(in);
            ois = new ObjectInputStream(fis);
            Object o = ois.readObject();
            instanciaLocal = (RepEmpresa) o;
        } catch (Exception e) {
            instanciaLocal = new RepEmpresa();
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
        File out = new File("empresas.dat");
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

	@Override
	public int tamanhoLista() {
		return empresas.size();
	}
	}