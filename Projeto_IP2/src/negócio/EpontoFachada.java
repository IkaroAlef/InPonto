package negócio;

import java.util.ArrayList;

import negócio.entity_beans.Dispensa;
import negócio.entity_beans.Empresa;
import negócio.entity_beans.Funcionario;
import negócio.entity_beans.Pessoa;
import negócio.entity_beans.RegPonto;
import dados.exceptionsDados.CnpjNaoEncontradoException;
import dados.exceptionsDados.EmpresaNaoEncontradaException;
import dados.exceptionsDados.FuncionarioNaoEncontradoException;

public class EpontoFachada {

	private ControladorPessoas pessoas = new ControladorPessoas();
	private ControladorEmpresas empresas = new ControladorEmpresas();
	private ControladorPontos pontos = new ControladorPontos();
	private ControladorDispensas dispensas = new ControladorDispensas();
	
	private static EpontoFachada instance;
	
	public static EpontoFachada getInstance(){
		if(instance==null)
			instance = new EpontoFachada();
		
		return instance;
	}
	
	private EpontoFachada() {

	}
	
	public void adicionarDispensa(Dispensa dispensa) {
		dispensas.adicionarDispensa(dispensa);
	}

	public Dispensa getDispensa(int i) {
		return dispensas.getDispensa(i);
	}

	public ArrayList<Dispensa> getDispensas(Funcionario funcionario) {
		return dispensas.getDispensas(funcionario);
	}

	public boolean dispensaContains(Funcionario funcionario) {
		return dispensas.dispensaContains(funcionario);
	}
	
	public void adicionarPessoa(int i, Pessoa pessoa) {
		pessoas.adicionarPessoa(i, pessoa);
	}
	

	public ArrayList<Pessoa> getPessoas(String conteudo) {
		return pessoas.getPessoas(conteudo);
	}

	public void adicionarPessoa(Pessoa pessoa) {
		pessoas.adicionarPessoa(pessoa);
	}

	public boolean validarLogin(String nome, char[] senhaDigitada) throws FuncionarioNaoEncontradoException {
		return pessoas.validarLogin(nome, senhaDigitada);
	}

	public int getSizePessoas() {
		return pessoas.tamanhoLista();
	}
	
	public int getSizeEmpresas(){
		return empresas.tamanhoLista();
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

	public Pessoa buscarPessoaNome(String nome)
			throws FuncionarioNaoEncontradoException {
		return pessoas.buscarPessoaNome(nome);
	}

	public Pessoa buscarPessoaCpf(String cpf)
			throws FuncionarioNaoEncontradoException {
		return pessoas.buscarPessoaCpf(cpf);
	}

	public void deletarPessoas(String[] nome) throws FuncionarioNaoEncontradoException {
		pessoas.deletarPessoas(nome);
	}

	public void deletarPessoa(int i) {
		pessoas.deletarPessoa(i);
	}

	public void editar(int i, Pessoa pessoa) {
		pessoas.editar(i, pessoa);
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

	public Empresa buscaEmpresaNome(String nomeEmpresa)
			throws EmpresaNaoEncontradaException {
		return empresas.buscaEmpresaNome(nomeEmpresa);
	}

	public Empresa buscaEmpresaCNPJ(String cnpj) throws CnpjNaoEncontradoException {
		return empresas.buscaEmpresaCNPJ(cnpj);
	}

	public void buscarIndiceNomeEmpresa(String nomeEmpresa)
			throws EmpresaNaoEncontradaException {
		empresas.buscarIndiceNomeEmpresa(nomeEmpresa);
	}

	public void buscarIndiceCNPJ(String cnpj) throws CnpjNaoEncontradoException {
		empresas.buscarIndiceCNPJ(cnpj);
	}

	public void adicionarRegistro(RegPonto ponto) {
		pontos.adicionarRegistro(ponto);
	}

	public ArrayList<RegPonto> getPontosDoFuncionario(String cpf)
			throws FuncionarioNaoEncontradoException {
		return pontos.getPontosDoFuncionario(cpf);
	}
	
	public ArrayList<RegPonto> getPontosDoFuncionario(String cpf, int mes, int ano)
			throws FuncionarioNaoEncontradoException{
		return pontos.getPontosDoFuncionario(cpf, mes, ano);
	}
	
	public ArrayList<RegPonto> getPontosDoFuncionario(String cpf, int dia, int mes, int ano)
			throws FuncionarioNaoEncontradoException{
		return pontos.getPontosDoFuncionario(cpf, dia, mes, ano);
	}

	public boolean isDiaCorreto(String cpf, int dia, int mes, int ano)
			throws FuncionarioNaoEncontradoException {
		return pontos.isDiaCorreto(cpf, dia, mes, ano);
	}

	public boolean isDiaAtrasado(String cpf, int dia, int mes, int ano)
			throws FuncionarioNaoEncontradoException {
		return pontos.isDiaAtrasado(cpf, dia, mes, ano);
	}

	public int totalChegadaCorreta(String cpf)
			throws FuncionarioNaoEncontradoException {
		return pontos.totalChegadaCorreta(cpf);
	}

	public int totalSaidaCorreta(String cpf)
			throws FuncionarioNaoEncontradoException {
		return pontos.totalSaidaCorreta(cpf);
	}

	public int totalIntervalo_InCorreta(String cpf)
			throws FuncionarioNaoEncontradoException {
		return pontos.totalIntervalo_InCorreta(cpf);
	}

	public int totalIntervalo_OutCorreta(String cpf)
			throws FuncionarioNaoEncontradoException {
		return pontos.totalIntervalo_OutCorreta(cpf);
	}
	
}
