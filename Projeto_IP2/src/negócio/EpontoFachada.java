package negócio;

import java.io.IOException;
import java.util.ArrayList;

import negócio.entity_beans.Empresa;
import negócio.entity_beans.Funcionario;
import negócio.entity_beans.Pessoa;
import dados.exceptionsDados.CnpjNaoEncontradoException;
import dados.exceptionsDados.EmpresaNaoEncontradaException;
import dados.exceptionsDados.FuncionarioNaoEncontradoException;

public class EpontoFachada {

	private static ControladorPessoas pessoas = new ControladorPessoas();
	private ControladorEmpresas empresas = new ControladorEmpresas();

	private static EpontoFachada instance;
	
	private EpontoFachada() {

	}
	
	public void adicionarPessoa(int i, Pessoa pessoa) {
		pessoas.adicionarPessoa(i, pessoa);
	}
	
	public static EpontoFachada getInstance(){
		if(instance==null)
			instance = new EpontoFachada();
		
		return instance;
	}

	public ArrayList<Pessoa> getPessoas(String conteudo) {
		return pessoas.getPessoas(conteudo);
	}

	public void adicionarPessoa(Pessoa pessoa) {
		pessoas.adicionarPessoa(pessoa);
	}

	public static boolean validarLogin(String nome, char[] senhaDigitada) throws FuncionarioNaoEncontradoException {
		return pessoas.validarLogin(nome, senhaDigitada);
	}

	public int tamanhoLista() {
		return pessoas.tamanhoLista();
	}

	public String getString(int i) {
		return pessoas.getString(i);
	}

	public Pessoa getObject(int i) {
		return pessoas.getObject(i);
	}

	public int buscarIndiceNome(String nome)
			throws FuncionarioNaoEncontradoException {
		return pessoas.buscarIndiceNome(nome);
	}

	public int buscarIndiceCpf(String cpf)
			throws FuncionarioNaoEncontradoException {
		return pessoas.buscarIndiceCpf(cpf);
	}

	public Pessoa buscaPessoaNome(String nome)
			throws FuncionarioNaoEncontradoException {
		return pessoas.buscaPessoaNome(nome);
	}

	public Funcionario buscaPessoaCpf(String cpf)
			throws FuncionarioNaoEncontradoException {
		return pessoas.buscaPessoaCpf(cpf);
	}

	public void deletarPessoa(String nome) throws FuncionarioNaoEncontradoException {
		pessoas.deletarPessoa(nome);
	}

	public void deletarPessoa(int i) {
		pessoas.deletarPessoa(i);
	}

	public void editar(int i, Pessoa pessoa) {
		pessoas.editar(i, pessoa);
	}

	public void exportar() throws IOException {
		pessoas.exportar();
	}

	public ArrayList<Empresa> getEmpresas(String conteudo) {
		return empresas.getEmpresas(conteudo);
	}

	public void adicionarEmpresa(Empresa empresa) {
		empresas.adicionarEmpresa(empresa);
	}

	public void deletarEmpresa(String nomeEmpresa) {
		empresas.deletarEmpresa(nomeEmpresa);
	}

	public void deletarEmpresa(int i) {
		empresas.deletarEmpresa(i);
	}

	public void editarEmpresa(int i, Empresa empresa) {
		empresas.editarEmpresa(i, empresa);
	}

	public void buscaEmpresaNome(String nomeEmpresa)
			throws EmpresaNaoEncontradaException {
		empresas.buscaEmpresaNome(nomeEmpresa);
	}

	public void buscaEmpresaCNPJ(String cnpj) throws CnpjNaoEncontradoException {
		empresas.buscaEmpresaCNPJ(cnpj);
	}

	public void buscarIndiceNomeEmpresa(String nomeEmpresa)
			throws EmpresaNaoEncontradaException {
		empresas.buscarIndiceNomeEmpresa(nomeEmpresa);
	}

	public void buscarIndiceCNPJ(String cnpj) throws CnpjNaoEncontradoException {
		empresas.buscarIndiceCNPJ(cnpj);
	}
	
}
