package Classes;

import java.util.Random;

/**
 * Classe que representa uma Mensagem enviada ou recebida
 * @author ARTHUR SENA, IGOR GOMES, RENAN PINTO, RODOLFO DE LIMA
 *
 */

public class Mensagem {

	String tipoDaMensagem;
	String corpoDaMensagem; 
	String assunto;
	Usuario destinatario;
	String idMensagem;
	
	/**
	 * Inicia os Atributos da Classe
	 * @param tipoDaMensagem
	 *                Indica qual eh o tipo da Mensagem 
	 * @param corpoMensagem
	 *                A Mensagem 
	 * @param assunto
	 *                Indica do que se trata a Mensagem
	 */
	
	public Mensagem(Usuario destinatario, String assunto, String mensagem){
		this.destinatario = destinatario;
		this.assunto = assunto;
		this.tipoDaMensagem = "offtopic";
		this.corpoDaMensagem = mensagem;
		this.idMensagem = geraId();
	}
	
	
	public Mensagem(Usuario destinatario, String assunto, String mensagem, String idRequisicaoEmprestimo){
		this.destinatario = destinatario;
		this.assunto = assunto;
		this.tipoDaMensagem = "negociacao";
		this.corpoDaMensagem = mensagem;
		this.idMensagem = geraId();
	}

	public String getTipoDaMensagem() {
		return tipoDaMensagem;
	}
	
	public Usuario getDestinatario(){
		return destinatario;
	}

	public String getCorpoDaMensagem() {
		return corpoDaMensagem;
	}
	
	public String getAssunto() {
		return assunto;
	}
	
	public String getIdMensagem() {
		return idMensagem;
	}

	private String geraId(){
		return this.destinatario.getLogin() + "-" + this.getTipoDaMensagem() + (new Random()).nextInt(1000);
	}


	public void addMensagem(String mensagem) {
		this.corpoDaMensagem += "; " + mensagem;		
	}
	
}
