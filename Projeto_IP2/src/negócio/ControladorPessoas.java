package negócio;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.mysql.cj.api.jdbc.Statement;
//import com.mysql.cj.jdbc.PreparedStatement;

import conexaoBD.FabricaDeConexao;
import dados.IRepositorioPessoas;
import dados.RepPessoas;
import dados.exceptionsDados.*;
import negócio.entity_beans.*;
import negócio.entity_beans.exceptionsBeans.NomeInvalidoException;

public class ControladorPessoas {
	private IRepositorioPessoas repositorioPessoas;

	public ControladorPessoas(){
		this.repositorioPessoas = RepPessoas.getInstance();
		 if(repositorioPessoas.tamanhoLista() == 0){
			 char[] senha = {'1','2','3','6','9'};
    	Pessoa pessoa = new Admin("AdminSuper","020715","ikaroalef@gmail.com",senha);
    	RepPessoas.getInstance().adicionarPessoa(pessoa);
    	}
	}
	
	public ArrayList<Pessoa> getPessoas(String conteudo) {
		return repositorioPessoas.getPessoas(conteudo);
	}
		
	
	public void adicionarPessoa(Pessoa pessoa) throws SQLException{
		FabricaDeConexao bd = new FabricaDeConexao();
		Connection con = null ;
		if (pessoa instanceof Funcionario){
				con = bd.getConexao("admin", "bancodedados");
				con.setAutoCommit(false);
				
				//Pessoa
				PreparedStatement ps = con.prepareStatement("INSERT INTO pessoa VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				ps.setString(1, pessoa.getNome());
				ps.setString(2, pessoa.getCpf());
				ps.setInt(3, pessoa.getMatricula());
				ps.setString(4, pessoa.getEmail());
				
				//Conversão Image para LongBlob para adicionar no banco
				Image img = ((Funcionario) pessoa).getFotoPadrao().getImage();
				BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
				Graphics2D g2d = bi.createGraphics();
				g2d.drawImage(img, 0, 0, null);
				g2d.dispose();
				
				ByteArrayOutputStream baos = null;
				try {
				    baos = new ByteArrayOutputStream();
				    ImageIO.write(bi, "png", baos);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
				    try {
				        baos.close();
				    } catch (Exception e) {
				    }
				}
				ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
				
				ps.setBinaryStream(5, bais);
				ps.setString(6, pessoa.getRg());
				ps.setString(7, String.valueOf(pessoa.getSenha()));
				ps.setString(8, pessoa.getTelefone());
				ps.setInt(9, 1); //CORRIGIR cargo
				ps.setInt(10, 1); //CORRIGIR  codDept
				ps.setString(11, pessoa.getRua());
				ps.setString(12, pessoa.getNumero());
				ps.setString(13, pessoa.getComplemento());
				ps.setString(14, pessoa.getBairro());
				ps.setString(15, pessoa.getCidade());
				ps.setString(16, pessoa.getEstado());
				ps.setString(17, pessoa.getCep());
				ps.execute();
				
				//Funcionario
				PreparedStatement psFunc = con.prepareStatement("INSERT INTO funcionario VALUES (?,?,?,?,?,?,?)");
				psFunc.setString(1, ((Funcionario) pessoa).getCTPS());
				psFunc.setString(2, ((Funcionario) pessoa).getPIS());
				psFunc.setDate(3, ((Funcionario) pessoa).getDt_admissao());
				psFunc.setDate(4, ((Funcionario) pessoa).getDt_demissao()); 
				psFunc.setString(5, ((Funcionario) pessoa).getCpf());
				psFunc.setString(6, ((Funcionario) pessoa).getCPF_Coord());  
				psFunc.setInt(7, ((Funcionario) pessoa).getCod_Eqp());
				psFunc.execute();
				
				con.commit();
				ps.close();
				psFunc.close();
				con.close();				
			}
		}
		
//		repositorioPessoas.adicionarPessoa(pessoa);
		
	public void adicionarPessoa(int i, Pessoa pessoa) {
		repositorioPessoas.adicionarPessoa(i, pessoa);
	}

	public boolean validarLogin(String dado, char[] senhaDigitada) throws FuncionarioNaoEncontradoException{
		boolean estaCorreto=false;
		String dbCPF, dbSenha;
		try {
			FabricaDeConexao bd = new FabricaDeConexao();
            Connection con = bd.getConexao("login", "bancodedados");
            Statement stmt = (Statement) con.createStatement();
            //stmt.executeQuery("SELECT CPF, senha FROM pessoa;");
            ResultSet rs = stmt.executeQuery("SELECT CPF, senha FROM pessoa;");	//stmt.getResultSet();
            while(rs.next()){
                dbCPF = rs.getString("CPF");
                dbSenha = rs.getString("senha");
                if(dbCPF.equals(dado) && Arrays.equals(senhaDigitada, dbSenha.toCharArray())){
                    estaCorreto = true;
                }
            }
            stmt.close();
            con.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return estaCorreto;
		
//		versão antiga com os repositorios em arquivo
//		if(senhaDigitada.length != repositorioPessoas.buscarPessoaCpf(dado).getSenha().length)
//			estaCorreto=false;
//			
//		else
//			estaCorreto=Arrays.equals(senhaDigitada, repositorioPessoas.buscarPessoaCpf(dado).getSenha());
//		
	}
	
	public int tamanhoLista(){
		return repositorioPessoas.tamanhoLista();
	}

	public String getString(int i) {
		return repositorioPessoas.getString(i);
	}

	public Pessoa getObject(int i) {
		return repositorioPessoas.getObject(i);
	}

	public int buscarIndiceNome(String nome)
			throws FuncionarioNaoEncontradoException {
		return repositorioPessoas.buscarIndiceNome(nome);
	}

	public int buscarIndiceCpf(String cpf)
			throws FuncionarioNaoEncontradoException {
		return repositorioPessoas.buscarIndiceCpf(cpf);
	}

	public Pessoa buscarPessoaNome(String nome)
			throws FuncionarioNaoEncontradoException {
		return repositorioPessoas.buscarPessoaNome(nome);
	}

	public Pessoa buscarPessoaCpf(String cpf) throws FuncionarioNaoEncontradoException, NomeInvalidoException {
		Pessoa pessoa = null;
		String dbCPF;
		
		try {
			FabricaDeConexao bd = new FabricaDeConexao();
			Connection con = bd.getConexao("admin", "bancodedados");
//			Statement stmt = (Statement) con.createStatement();
			ResultSet rsFunc = con.createStatement().executeQuery("SELECT * FROM funcionario JOIN PESSOA WHERE funcionario.cpf=pessoa.cpf; ");
            ResultSet rsGerente = con.createStatement().executeQuery("SELECT * FROM gerente JOIN PESSOA WHERE gerente.cpf=pessoa.cpf; ");
            ResultSet rsCoord = con.createStatement().executeQuery("SELECT * FROM coordenador JOIN PESSOA WHERE coordenador.cpf=pessoa.cpf; ");
            boolean achou = false;
            while (rsFunc.next() && achou==false){
            	dbCPF = rsFunc.getString("CPF");
            	if (dbCPF.equals(cpf)){
            		//84493610941  123454   login e senha teste
            		achou=true;
            		String telefone = rsFunc.getString("telefone"); 
            		Empresa empresa = new Empresa(); 
            		int carg = rsFunc.getInt("cargo");
            		ResultSet rsCarg = con.createStatement().executeQuery("SELECT nome FROM CARGO WHERE cargo.codigo="+carg+";");
            		rsCarg.next();
            		String cargo = rsCarg.getString("nome");
            		String escala = "nada" ;
            		LocalTime chegada = LocalTime.of(8, 00);
            		LocalTime saida = LocalTime.of(12, 00); 
            		LocalTime intervalo_in = LocalTime.of(9, 00); 
            		LocalTime intervalo_out = LocalTime.of(9, 30);
            		
            		String nome = rsFunc.getString("nome");
            		String email = rsFunc.getString("email");
            		char[] senha = rsFunc.getString("senha").toCharArray();
            		pessoa = new Funcionario(nome, dbCPF, email, senha, telefone, empresa,cargo,escala,chegada,saida,intervalo_in,intervalo_out);
            		break;
            	}
            }
            while (rsGerente.next()&& achou==false){
            	while (rsGerente.next()){
                	dbCPF = rsGerente.getString("CPF");
                	if (dbCPF.equals(cpf)){
                		//a  123485   login e senha teste
                		achou=true;
                		String nome = rsGerente.getString("nome");
                		String email = rsGerente.getString("email");
                		char[] senha = rsGerente.getString("senha").toCharArray();
                		pessoa = new Admin(nome, dbCPF, email, senha);
                		break;
                	}
                }
            }
            while (rsCoord.next() && achou==false){
            	while (rsCoord.next()){
                	dbCPF = rsCoord.getString("CPF");
                	if (dbCPF.equals(cpf)){
                		achou=true;
                		break;
                	}
                }
            }
            con.close(); 
            
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		//return repositorioPessoas.buscarPessoaCpf(cpf);
		return pessoa;
	}

	public void deletarPessoas(String[] nome) throws FuncionarioNaoEncontradoException {
		repositorioPessoas.deletarPessoas(nome);
	}

	public void deletarPessoa(int i) {
		repositorioPessoas.deletarPessoa(i);
	}

	public void editar(int i, Pessoa pessoa) {
		repositorioPessoas.editar(i, pessoa);
	}
	
}
