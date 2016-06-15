package qrCode;
import java.util.StringTokenizer;

import com.barcodelib.barcode.QRCode;

import negócio.entity_beans.Pessoa;

public class GerarQRCode {
	
	public static void gerar(Pessoa pessoa) throws Exception{
		
		String qrcodeInfo = criptografar(pessoa);
		QRCode barcode = new QRCode();
 		barcode.setData(qrcodeInfo);
 		barcode.setDataMask(50);
 		barcode.setVersion(10);
 		barcode.setEcl(QRCode.ECL_M);
 		
 		barcode.setUOM(0);
 		barcode.setModuleSize(5.000f);
        barcode.setLeftMargin(80.000f);
        barcode.setRightMargin(80.000f);
        barcode.setTopMargin(80.000f);
        barcode.setBottomMargin(80.000f);
        barcode.setResolution(72);
        barcode.setRotate(0);
        
        //Local onde o qr code vai está
        
        barcode.renderBarcode("C://Users//"+System.getProperty("user.name")+"//Pictures//"+pessoa.getCpf()+".png");
	}
	
	public static String toString (Pessoa pessoa){
		String retorno = pessoa.getCpf() + "+";
		for(int i = 0; i < pessoa.getSenha().length; i++){
			retorno = retorno + pessoa.getSenha()[i];
		}
		return retorno;
	}
	
	public static String criptografar(Pessoa pessoa){
		String crip = toString(pessoa);              //Pega o toString e coloca em crip
		char[] nova = new char[crip.length()];      //vetor de char do tamanho da String
		int inversao;                               //vai receber o valor, da tabela ascii, referente a um char da string e somar 127 
		String fim;									//Vai ser a string final, com as informações criptografadas
		
		for (int i = 0; i < crip.length(); i++){    //Laço para percorrer a String, char por char, e poder fazer a transformação para
			inversao = crip.charAt(i) + 10;        // o novo char e armazena-lo no vetor nova 
			nova[i] = (char)inversao;
		}//*/
		
		fim = Character.toString(nova[0]);          //Inicializa a nova string 
		//fim.concat(Character.toString(nova[1]));		
		for(int i = 1; i<nova.length; i++){         //Laço para percorrer o vetor nova e formar fim, através de concatenação 
			fim = fim + nova[i];
			//System.out.println(fim);
		}
		return fim;//*/                             //Retorna a informação criptografada
	}
	
	//Faz a descriptografia da string passada como parametro, nela contem o cpf e a senha do funcionario
	public static String descriptografar(String info){
			String crip = info;
			char[] nova = new char[crip.length()];
			int inversao;
			String fim;
			
			for (int i = 0; i < crip.length(); i++){
				inversao = crip.charAt(i) - 10;
				nova[i] = (char)inversao;
			}
			
			fim = Character.toString(nova[0]);
			//fim.concat(Character.toString(nova[1]));		
			for(int i = 1; i<nova.length; i++){
				fim = fim + nova[i];
				//System.out.println(fim);
			}
			
//			StringTokenizer st = new StringTokenizer(fim, "+", false);
//			String cpf;
//			char senha[];
//			while(st.hasMoreTokens()){
//				cpf = st.nextToken();
//				senha = st.nextToken().toCharArray();
//			}
			return fim;
			
		}
}
