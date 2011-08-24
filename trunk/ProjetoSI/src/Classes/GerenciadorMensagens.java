package Classes;

import java.util.ArrayList;

/**
 * Classe que guarda as Mensagens de um Usuario
 * @author ARTHUR SENA, IGOR GOMES, RENAN PINTO, RODOLFO DE LIMA
 *
 */

public class GerenciadorMensagens {
	
	private ArrayList<Mensagem> listaDeMensagens;
	
	
	/**
	 * Inicia os Atributos da Classe
	 */
	
	public GerenciadorMensagens(){
		listaDeMensagens = new ArrayList<Mensagem>();
	}
	
	/**
	 * Recupera a Lista com todas as Mensagens do Usuario
	 * @return 
	 *         Uma Lista com todas as mensagens do Usuario
	 */
	
	public ArrayList<Mensagem> getListaDeMensagens(){
		return listaDeMensagens;
	}
	
	
}
