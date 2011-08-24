package Classes;

import java.util.ArrayList;

/**
 * Classe que guarda as Mensagens de um Usuario
 * @author ARTHUR SENA, IGOR GOMES, RENAN PINTO, RODOLFO DE LIMA
 *
 */

public class GerenciadorMensagens {
	
	private ArrayList<Mensagem> listaDeMensagensRecebidas;
	private ArrayList<Mensagem> listaDeMensagensEnviadas;
	
	
	/**
	 * Inicia os Atributos da Classe
	 */
	
	public GerenciadorMensagens(){
		listaDeMensagensRecebidas = new ArrayList<Mensagem>();
	}
	
	/**
	 * Recupera a Lista com todas as Mensagens Recebida do Usuario
	 * @return 
	 *         Uma Lista com todas as mensagens do Usuario
	 */
	
	public ArrayList<Mensagem> getListaDeMensagensRecebidas(){
		return listaDeMensagensRecebidas;
	}
	
	/**
	 * Recupera a Lista com todas as Mensagens Envidas do Usuario
	 * @return 
	 *         Uma Lista com todas as mensagens enviadas do Usuario
	 */
	public ArrayList<Mensagem> getListaDeMensagensEnviadas(){
		return listaDeMensagensEnviadas;
	}
	
}
