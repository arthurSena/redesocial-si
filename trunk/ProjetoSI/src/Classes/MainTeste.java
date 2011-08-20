package Classes;

import java.util.Scanner;

/**
 * 
 * @author ARTHUR SENA, IGOR GOMES, RENAN PINTO, RODOLFO DE LIMA
 * @version 1.0
 */


public class MainTeste {
	
	private static RedeSocial rede = new RedeSocial();
	
	public static void main(String[] args) {
		
		
		System.out.println("---------BEM VINDO A NOSSA REDE SOCIAL--------\n" +
				           " O que voce deseja fazer?                     \n" +
				           " 1) Fazer Login\n 2) Quero Me Cadastrar\n 3) Sair"   );
		 
		String entrada = recebeEntrada();
		
		
		
		
		
	}
	
	private static String recebeEntrada(){
		Scanner sc = new Scanner(System.in);
		String entrada = sc.nextLine();
		return entrada;
	}
	

}
