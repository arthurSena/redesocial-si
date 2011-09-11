package Classes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe que guarda as Mensagens de um Usuario
 * @author ARTHUR SENA, IGOR GOMES, RENAN PINTO, RODOLFO DE LIMA
 *
 */

public class GerenciadorMensagens {
	
	private List<Mensagem> listaDeMensagens;
//	private ArrayList<Mensagem> listaDeMensagensEnviadas;
	
	
	/**
	 * Inicia os Atributos da Classe
	 */
	
	public GerenciadorMensagens(){
		listaDeMensagens = new LinkedList<Mensagem>();
//		listaDeMensagensEnviadas = new ArrayList<Mensagem>();
	}
	
	/**
	 * Recupera a Lista com todas as Mensagens Recebida do Usuario
	 * @return 
	 *         Uma Lista com todas as mensagens do Usuario
	 */
	
	public List<Mensagem> getListaDeMensagens(){
		return listaDeMensagens;
	}
	
//	public void addMensagemEnviada(Mensagem mensagem){
//		this.getListaDeMensagensEnviadas().add(mensagem);
//	}
	
	public void addMensagem(Mensagem mensagem){
		this.getListaDeMensagens().add(mensagem);
	}
	
	/**
	 * Recupera a Lista com todas as Mensagens Envidas do Usuario
	 * @return 
	 *         Uma Lista com todas as mensagens enviadas do Usuario
	 */
//	public ArrayList<Mensagem> getListaDeMensagensEnviadas(){
//		return listaDeMensagensEnviadas;
//	}
	
	private boolean assuntoExiste(String assunto){
		
		for (Mensagem msg : this.getListaDeMensagens()){
			if (msg.getAssunto().equals(assunto)){
				return true;
			}
		}
		
		return false;
	}
	
	
	public String enviarMensagem(Usuario destinatario, String assunto, String mensagem){
		Mensagem msgReturn = null;
		
		if (assuntoExiste(assunto)){
			for (Mensagem msg : this.getListaDeMensagens()){
				if (msg.getAssunto().equals(assunto)){
					msg.addMensagem(mensagem);
					msgReturn = msg;
				}
			}
		} else{

			Mensagem msg = new Mensagem(destinatario, assunto, mensagem);
			this.addMensagem(msg);
			destinatario.getGerenciadorMensagens().addMensagem(msg);
			msgReturn = msg;
			
			
		}
		return msgReturn.getIdMensagem();
	}
	
	public String enviarMensagem(Usuario destinatario, String assunto, String mensagem, String idRequisicaoEmprestimo){
		Mensagem msgReturn = null;
		
		if (assuntoExiste(assunto)){
			for (Mensagem msg : this.getListaDeMensagens()){
				if (msg.getAssunto().equals(assunto)){
					msg.addMensagem(mensagem);
					msgReturn = msg;
				}
			}
		} else{

			Mensagem msg = new Mensagem(destinatario, assunto, mensagem, idRequisicaoEmprestimo);
			this.addMensagem(msg);
			destinatario.getGerenciadorMensagens().addMensagem(msg);
			msgReturn = msg;
			
			
		}
		return msgReturn.getIdMensagem();
	}
	
	private String formatarRequisicoes(String requisicoes){
		String retorno = "";
		for (int i =0;i< requisicoes.split("; ").length;i++){
			if (i==requisicoes.split("; ").length-1){
				retorno +=requisicoes.split("; ")[i];
				break;
			}
			retorno += requisicoes.split("; ")[i] + "; ";
			
		}return retorno;
	}
	
	public String lerTopicos(String tipo) throws Exception {
		String repString = "";
		
		if (this.getListaDeMensagens().size() > 1){
			for (Mensagem msg : this.getListaDeMensagens()){
				if (msg.getTipoDaMensagem().equals(tipo) || tipo.equals("todos")){
					repString += msg.getAssunto() + "; ";
				}
				
			}
			
			repString = formatarRequisicoes(repString);
			
		} else{
			for (Mensagem msg : this.getListaDeMensagens()){
				if (msg.getTipoDaMensagem().equals(tipo) || tipo.equals("todos")){
					repString += msg.getAssunto();
				}
			}
		}
		
		if (repString.equals("")){
			repString = "Não há tópicos criados";
		}
		return repString;
		
	}

	public String lerMensagens(String idTopico) {
		String reString = "";
		
		for (Mensagem msg : this.getListaDeMensagens()){
			if (msg.getIdMensagem().equals(idTopico)){
				reString += msg.getCorpoDaMensagem();
			}
		}
		return reString;
	}
	
}
