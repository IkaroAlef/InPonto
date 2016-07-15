package negócio;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.time.Month;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import conexaoBD.FabricaDeConexao;
import negócio.entity_beans.Funcionario;
import negócio.entity_beans.RegPonto;
import negócio.entity_beans.exceptionsBeans.NomeInvalidoException;
import dados.IRepositorioPontos;
import dados.RepRegPonto;
import dados.exceptionsDados.FuncionarioNaoEncontradoException;

public class ControladorPontos {
	private IRepositorioPontos pontos;
	
	public ControladorPontos() {
		this.pontos = RepRegPonto.getInstance();
	}

	public void adicionarRegistro(RegPonto ponto) throws SQLException{
		FabricaDeConexao bd = new FabricaDeConexao();
		Connection con = bd.getConexao("func", "bancodedados");
		con.setAutoCommit(false);
		PreparedStatement ps = con.prepareStatement("INSERT INTO reg_ponto(`rData`, `hora_min`, `fotoPonto`, `CPF_Func`) VALUES(?,?,?,?);");
		Date data = Date.valueOf(ponto.getAgora().toLocalDate());
		Time hora_min = Time.valueOf(ponto.getAgora().toLocalTime());
		ps.setDate(1, data);
		ps.setTime(2,hora_min);
		
		//Conversão Image para LongBlob para adicionar no banco
		Image img = (ponto.getFotoPonto());
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
		
		ps.setBinaryStream(3, bais);
		ps.setString(4,ponto.getCpf());
		
		ps.executeUpdate();
		con.commit();
		
		ps.close();
		con.close();
		//pontos.adicionarRegistro(ponto);
	}

	public ArrayList<RegPonto> getPontosDoFuncionario(String cpf) throws FuncionarioNaoEncontradoException, NomeInvalidoException, IOException {
		return pontos.getPontosDoFuncionario(cpf);
	}
	
	public ArrayList<RegPonto> getPontosDoFuncionario(String cpf, int mes, int ano) throws FuncionarioNaoEncontradoException, NomeInvalidoException, IOException{
		return pontos.getPontosDoFuncionario(cpf, mes, ano);
	}
	
	public ArrayList<RegPonto> getPontosDoFuncionario(String cpf, int dia, int mes, int ano)
			throws FuncionarioNaoEncontradoException, NomeInvalidoException, IOException{
		return pontos.getPontosDoFuncionario(cpf, dia, mes, ano);
	}	

	public boolean isDiaCorreto(String cpf, int dia, int mes, int ano)
			throws FuncionarioNaoEncontradoException, NomeInvalidoException, IOException {
		return pontos.isDiaCorreto(cpf, dia, mes, ano);
	}
	
	public int getTotalDiasCorretos(String cpf, int mes, int ano)
			throws FuncionarioNaoEncontradoException, NomeInvalidoException, IOException {
		int total = 0;
		ArrayList <RegPonto> pontos = getPontosDoFuncionario(cpf, mes, ano);

		for (int i = 0; i < pontos.size(); i+=4){
			if(isDiaCorreto(cpf, pontos.get(i).getAgora().getDayOfMonth(), mes, ano))
					total++;
			}
		 return total;
	}
	
	public int getTotalDiasAtrasado(String cpf, int mes, int ano)
			throws FuncionarioNaoEncontradoException, NomeInvalidoException, IOException {
		int total = 0;
		ArrayList <RegPonto> pontos = getPontosDoFuncionario(cpf, mes, ano);

		for (int i = 0; i < pontos.size(); i+=4){
			if(isDiaAtrasado(cpf, pontos.get(i).getAgora().getDayOfMonth(), mes, ano))
					total++;
			}
		 return total;
	}	

	public boolean isDiaAtrasado(String cpf, int dia, int mes, int ano)
			throws FuncionarioNaoEncontradoException, NomeInvalidoException, IOException {
		return pontos.isDiaAtrasado(cpf, dia, mes, ano);
	}

	public int totalChegadaCorreta(String cpf)
			throws FuncionarioNaoEncontradoException, NomeInvalidoException, IOException {
		return pontos.totalChegadaCorreta(cpf);
	}

	public int totalSaidaCorreta(String cpf)
			throws FuncionarioNaoEncontradoException, NomeInvalidoException, IOException {
		return pontos.totalSaidaCorreta(cpf);
	}

	public int totalIntervalo_InCorreta(String cpf)
			throws FuncionarioNaoEncontradoException, NomeInvalidoException, IOException {
		return pontos.totalIntervalo_InCorreta(cpf);
	}

	public int totalIntervalo_OutCorreta(String cpf)
			throws FuncionarioNaoEncontradoException, NomeInvalidoException, IOException {
		return pontos.totalIntervalo_OutCorreta(cpf);
	}
	
	

}
