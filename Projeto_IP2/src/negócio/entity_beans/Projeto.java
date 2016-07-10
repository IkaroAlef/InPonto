package negócio.entity_beans;

import javax.xml.crypto.Data;

public class Projeto {
	private int codigo;
	private int horas;
	private Data dataInicio;
	private Data dataFim;
	private Coordenador coordenador;
	private Departamento departamento;
	
	public Projeto(int codigo, int horas, Data dataInicio, Data dataFim, Tarefa tarefa, Coordenador coordenador, Departamento departamento) {
		this.codigo = codigo;
		this.horas = horas;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.coordenador = coordenador;
		this.departamento = departamento;
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

	public Data getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Data dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Data getDataFim() {
		return dataFim;
	}

	public void setDataFim(Data dataFim) {
		this.dataFim = dataFim;
	}

	public String getCoordenador() {
		return coordenador.getCpf();
	}

	public void setCoordenador(Coordenador coordenador) {
		this.coordenador = coordenador;
	}

	public int getDepartamento() {
		return departamento.getCodigo();
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
}
