package negócio;

import java.io.IOException;
import java.util.ArrayList;

import negócio.entity_beans.Empresa;
import negócio.entity_beans.Funcionario;
import negócio.entity_beans.Pessoa;
import dados.RepEmpresa;
import dados.RepPessoas;
import dados.exceptionsDados.CnpjNaoEncontradoException;
import dados.exceptionsDados.EmpresaNaoEncontradaException;
import dados.exceptionsDados.FuncionarioNaoEncontradoException;

public class EpontoFachada {

	private static EpontoFachada instance;
	private ControladorPessoas controladorPessoas = new ControladorPessoas(new RepPessoas());
	private ControladorEmpresas controladorEmpresas = new ControladorEmpresas(new RepEmpresa());
	
	public EpontoFachada() {

	}
	
	public static EpontoFachada getInstance(){
		if(instance==null)
			instance = new EpontoFachada();
		return instance;
	}

	public ArrayList<Pessoa> getPessoas(String conteudo) {
		return controladorPessoas.getPessoas(conteudo);
	}

	public void adicionarPessoa(Pessoa pessoa) {
		controladorPessoas.adicionarPessoa(pessoa);
	}

	public boolean validarLogin(String nome, char[] senhaDigitada)
			throws FuncionarioNaoEncontradoException {
		return controladorPessoas.validarLogin(nome, senhaDigitada);
	}

	public String[] linhaTabela(int i) {
		return controladorPessoas.linhaTabela(i);
	}

	public int tamanhoLista() {
		return controladorPessoas.tamanhoLista();
	}

	public String getString(int i) {
		return controladorPessoas.getString(i);
	}

	public Pessoa getObject(int i) {
		return controladorPessoas.getObject(i);
	}

	public int buscarIndiceNome(String nome)
			throws FuncionarioNaoEncontradoException {
		return controladorPessoas.buscarIndiceNome(nome);
	}

	public int buscarIndiceCpf(String cpf)
			throws FuncionarioNaoEncontradoException {
		return controladorPessoas.buscarIndiceCpf(cpf);
	}

	public Pessoa buscaPessoaNome(String nome)
			throws FuncionarioNaoEncontradoException {
		return controladorPessoas.buscaPessoaNome(nome);
	}

	public Funcionario buscaPessoaCpf(String cpf)
			throws FuncionarioNaoEncontradoException {
		return controladorPessoas.buscaPessoaCpf(cpf);
	}

	public void deletarPessoa(String nome) {
		controladorPessoas.deletarPessoa(nome);
	}

	public void deletarPessoa(int i) {
		controladorPessoas.deletarPessoa(i);
	}

	public void editar(int i, Pessoa pessoa) {
		controladorPessoas.editar(i, pessoa);
	}

	public void exportar() throws IOException {
		controladorPessoas.exportar();
	}

	public ArrayList<Empresa> getEmpresas(String conteudo) {
		return controladorEmpresas.getEmpresas(conteudo);
	}

	public void adicionarEmpresa(Empresa empresa) {
		controladorEmpresas.adicionarEmpresa(empresa);
	}

	public void deletarEmpresa(String nomeEmpresa) {
		controladorEmpresas.deletarEmpresa(nomeEmpresa);
	}

	public void deletarEmpresa(int i) {
		controladorEmpresas.deletarEmpresa(i);
	}

	public void editarEmpresa(int i, Empresa empresa) {
		controladorEmpresas.editarEmpresa(i, empresa);
	}

	public void buscaEmpresaNome(String nomeEmpresa)
			throws EmpresaNaoEncontradaException {
		controladorEmpresas.buscaEmpresaNome(nomeEmpresa);
	}

	public void buscaEmpresaCNPJ(String cnpj) throws CnpjNaoEncontradoException {
		controladorEmpresas.buscaEmpresaCNPJ(cnpj);
	}

	public void buscarIndiceNomeEmpresa(String nomeEmpresa)
			throws EmpresaNaoEncontradaException {
		controladorEmpresas.buscarIndiceNomeEmpresa(nomeEmpresa);
	}

	public void buscarIndiceCNPJ(String cnpj) throws CnpjNaoEncontradoException {
		controladorEmpresas.buscarIndiceCNPJ(cnpj);
	}
	
}
