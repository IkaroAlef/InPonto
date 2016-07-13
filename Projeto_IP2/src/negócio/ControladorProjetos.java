package negócio;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import conexaoBD.FabricaDeConexao;
import negócio.entity_beans.Projeto;

public class ControladorProjetos {

	public void adicionarProjeto(Projeto projeto) throws SQLException{
		FabricaDeConexao bd = new FabricaDeConexao();
		Connection con = null;
		con = bd.getConexao("admin", "bancodedados");
		con.setAutoCommit(false);
		
		PreparedStatement ps = con.prepareStatement("INSERT INTO projeto (`dataFim`, `dataInicio`, `descricao`, `qtdHoras`, `cod_Dept`, `CPF_Coord`, `CNPJ`) VALUES (?,?,?,?,?,?)");
		ps.setDate(1, projeto.getDataFim());
		ps.setDate(2, projeto.getDataInicio());
		ps.setString(3, projeto.getDescricao());
		ps.setInt(4, projeto.getHoras());
		ps.setInt(5, projeto.getDepartamento());
		ps.setString(6, projeto.getCoordenador());				
		ps.setString(7, projeto.getCoordenador());
		ps.execute();
		con.commit();
		ps.close();
		con.close();
	}
}
