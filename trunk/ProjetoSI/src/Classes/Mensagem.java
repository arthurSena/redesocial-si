package Classes;

import java.util.GregorianCalendar;

/**
 * Classe que representa uma Mensagem enviada ou recebida
 * @author ARTHUR SENA, IGOR GOMES, RENAN PINTO, RODOLFO DE LIMA
 *
 */

public class Mensagem {

	String tipoDaMensagem;
	String corpoDaMensagem; 
	String assunto;
	
	/**
	 * Inicia os Atributos da Classe
	 * @param tipoDaMensagem
	 *                Indica qual eh o tipo da Mensagem 
	 * @param corpoMensagem
	 *                A Mensagem 
	 * @param assunto
	 *                Indica do que se trata a Mensagem
	 */
	
	public Mensagem(String tipoDaMensagem, String corpoMensagem, String assunto){
		this.assunto = assunto;
		this.tipoDaMensagem = tipoDaMensagem;
		this.corpoDaMensagem = corpoMensagem;
	}
	
	
}
