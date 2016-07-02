package conexaoBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.api.jdbc.Statement;

public class FabricaDeConexao {
	
	public Connection getConexao(String user, String senha) throws SQLException{
		Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/inponto?autoReconnect=true&useSSL=false&serverTimezone=America/Recife",user,senha);
		return conexao;
	}
	
}
