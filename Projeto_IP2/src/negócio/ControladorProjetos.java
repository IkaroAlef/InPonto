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
		
		PreparedStatement ps = con.prepareStatement("INSERT INTO projeto VALUES (?,?,?,?,?,?)");
		ps.setInt(1, projeto.getCodigo());
		ps.setInt(2, projeto.getHoras());
		ps.setString(3, projeto.getDataInicio());
		ps.setString(4, projeto.getDataFim());
		ps.setString(5, projeto.getCoordenador());
		ps.setString(6, projeto.getDepartamento());		
		ps.execute();
		con.commit();
		ps.close();
		con.close();
	}
}
