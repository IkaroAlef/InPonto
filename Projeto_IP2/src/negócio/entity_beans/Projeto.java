package negócio.entity_beans;

import java.sql.Date;

public class Projeto {
	private int codigo;
	private int horas;
	private String descricao;
	private Date dataInicio;
	private Date dataFim;
	private String coordenador;
	private int departamento;
	private String cnpj;
	
	public Projeto(int horas, String descricao, Date dataInicio, Date dataFim, String coordenador, int departamento, String cnpj) {
		this.horas = horas;
		this.descricao = descricao;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.coordenador = coordenador;
		this.departamento = departamento;
		this.cnpj = cnpj;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCoordenador() {
		return coordenador;
	}

	public void setCoordenador(String coordenador) {
		this.coordenador = coordenador;
	}

	public int getDepartamento() {
		return departamento;
	}

	public void setDepartamento(int departamento) {
		this.departamento = departamento;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

}
