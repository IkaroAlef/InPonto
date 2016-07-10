package negócio.entity_beans;

public class Projeto {
	private int codigo;
	private int horas;
	private String descricao;
	private String dataInicio;
	private String dataFim;
	private String coordenador;
	private String departamento;
	
	public Projeto(int codigo, int horas, String descricao, String dataInicio, String dataFim, String coordenador, String departamento) {
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

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDataFim() {
		return dataFim;
	}

	public void setDataFim(String dataFim) {
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

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
}
