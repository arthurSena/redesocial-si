package Classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Esta Classe representa um Usu�rio da Rede Social
 * 
 * @author ARTHUR SENA, RODOLFO DE LIMA, RENAN PINTO, IGOR GOMES
 * @version 1.0
 * 
 */

public class Usuario {
	
	private String nome;
	private String login;
	private String end;
	private String ID;
	
	private GerenciadorAmizades gerenciaAmizade;
	private GerenciadorMensagens gerenciaMensagens;
	private GerenciadorItens gerenciaItens;


	/**
	 * Inicia os Atributos da Classe
	 * @param nome 
	 *           Nome do Usuario
	 * @param login 
	 *           Login do Usuario
	 * @param senha 
	 *           Senha do Usuario
	 * @param end
	 *           Endereco do Usuario
	 * @throws Exception
	 *           Lanca excecao quando qualquer um dos parametros for Invalido
	 */
	
	public Usuario(String nome, String login, String end)throws Exception{
		if (!stringValida(nome)){
			throw new Exception("Nome inválido");
		}
		else if (!stringValida(login)){
			throw new Exception("Login inválido");
		}
		else if(!stringValida(end)){
			throw new Exception("Endereco inválido");
		}
		
		this.nome = nome;
		this.login = login;
		this.end = end;
		this.ID = gerarID();
		gerenciaAmizade = new GerenciadorAmizades();
		gerenciaItens = new GerenciadorItens();
		gerenciaMensagens = new GerenciadorMensagens();
	}
	
	public String visualizarPerfil(){
		return nome + " - " + this.end;
	}
	
	public String getID(){
		return ID;
	}
	
	/**
	 * Gera um ID pro Usuario toda vez que uma sessao eh iniciada
	 * @return
	 *        ID do Usuario
	 */
	
	public String gerarID(){
		this.ID += getNome() + "-" + (new Random()).nextInt(1000);
		return this.ID;
	}
	
	
	/**
	 * Recupera o Endereco do Usuario
	 * @return 
	 *        Endereco do Usuario
	 */
	
	public String getEndereco(){
		return end;
	}

	/**
	 * Recupera o Login
	 * @return Login do Usuario
	 */
	
	public String getLogin() {
		return login;
	}
	
	/**
	 * Recupera o Nome
	 * @return Nome do Usuario
	 */
	
	public String getNome() {
		return nome;
	}
	
	
    /**
     * Altera o Nome do Usuario
     * 
     * @param nome 
     *           Novo Nome do Usuario
     * @throws Exception 
     *           Caso o Nome seja Invalido
     */
	
	public void setNome(String nome) throws Exception {
		if (!stringValida(nome)){
			throw new Exception("Dado(s) Invalido(s)");
		}
		this.nome = nome;
	}

    
	
	
	/**
	 * Recupera o Gerenciador de Amizades do Usuario
	 * @return 
	 *      O gerenciador de amizades do usuario
	 */
	
	public GerenciadorAmizades getGerenciadorAmizades(){
		return gerenciaAmizade;
	}
	
	/**
	 * Recupera o Gerenciador de itens do Usuario
	 * @return 
	 *      O gerenciador de itens do usuario
	 */
	
	public GerenciadorItens getGerenciadorItens(){
		return gerenciaItens;
	}
	
	/**
	 * Recupera o Gerenciador de Mensagens do Usuario
	 * @return 
	 *      O gerenciador de mensagens do usuario
	 */
	
	public GerenciadorMensagens getGerenciadorMensagens(){
		return gerenciaMensagens;
	}
	
	public boolean equals(Usuario usr){
		if (!(usr instanceof Usuario)){
			return false;
		}
		
		else if(usr.getLogin().equals(this.login)){
			return true;
		}
		
		else{
			return false;
		}
	}
	
	private boolean stringValida(String string){
        if (string==null || string.isEmpty()){
            return false;
        }
        return true;
    }
	
	private String nomeModificado(String nome){
		String nomeID = (login.charAt(0) + "").toUpperCase();
		for (int i = 1; i <nome.length();i++){
			nomeID += login.charAt(i);
		}return nomeID;
	}
	
	public String pesquisarItem(String chave, String atributo, String tipoOrdenacao, String criterioOrdenacao){
		String resposta = "";
		
		if(criterioOrdenacao.equals("dataCriacao")){
			if (tipoOrdenacao.equals("crescente")){
				for (Usuario usuario : this.getGerenciadorAmizades().getListaDeAmigos()){
					if (resposta.equals("")){
						resposta += usuario.getGerenciadorItens().buscarItemCadastrado(chave, atributo, tipoOrdenacao, criterioOrdenacao);
						
					} else if (!resposta.equals("") && !usuario.getGerenciadorItens().buscarItemCadastrado(chave, atributo, tipoOrdenacao, criterioOrdenacao).equals("")){
						resposta += "; " + usuario.getGerenciadorItens().buscarItemCadastrado(chave, atributo, tipoOrdenacao, criterioOrdenacao);
					}
				}
			} else {
				for (int i = this.getGerenciadorAmizades().getListaDeAmigos().size() - 1; i >= 0; i--){
					if (resposta.equals("")){
						resposta += this.getGerenciadorAmizades().getListaDeAmigos().get(i).getGerenciadorItens().buscarItemCadastrado(chave, atributo, tipoOrdenacao, criterioOrdenacao);
						
					} else if (!resposta.equals("") && !this.getGerenciadorAmizades().getListaDeAmigos().get(i).getGerenciadorItens().buscarItemCadastrado(chave, atributo, tipoOrdenacao, criterioOrdenacao).equals("")){
						resposta += "; " + this.getGerenciadorAmizades().getListaDeAmigos().get(i).getGerenciadorItens().buscarItemCadastrado(chave, atributo, tipoOrdenacao, criterioOrdenacao);
					}
				}
			}
		}
		else if(criterioOrdenacao.equals("reputacao")){
			if (tipoOrdenacao.equals("crescente")){
				
				List<Usuario> lista = new ArrayList<Usuario>();
				//List<Usuario> lista = this.getGerenciadorAmizades().getListaDeAmigos();
				for(Usuario usr:this.getGerenciadorAmizades().getListaDeAmigos()){
					lista.add(usr);
				}
				while(!lista.isEmpty()){
					
					Usuario usr = usuarioComMenorReputacaoDaLista(lista);
					if (resposta.equals("")){
						resposta += usr.getGerenciadorItens().buscarItemCadastrado(chave, atributo, tipoOrdenacao, criterioOrdenacao);}
					else{
						resposta += "; "+usr.getGerenciadorItens().buscarItemCadastrado(chave, atributo, tipoOrdenacao, criterioOrdenacao);
					}
					lista.remove(usr);
				}
			}
			else if(tipoOrdenacao.equals("decrescente")){
				
				List<Usuario> lista = new ArrayList<Usuario>();
				//List<Usuario> lista = this.getGerenciadorAmizades().getListaDeAmigos();
				for(Usuario usr:this.getGerenciadorAmizades().getListaDeAmigos()){
					lista.add(usr);
				}
				
				while(!lista.isEmpty()){
					Usuario usr = usuarioComMaisAltaReputacaoDaLista(lista);
					
					if (resposta.equals("")){
						resposta += usr.getGerenciadorItens().buscarItemCadastrado(chave, atributo, tipoOrdenacao, criterioOrdenacao);}
					else{
						resposta += "; "+usr.getGerenciadorItens().buscarItemCadastrado(chave, atributo, tipoOrdenacao, criterioOrdenacao);
					}
					lista.remove(usr);
				}
			}
		}
//		if (tipoOrdenacao.equals("crescente")){
//			for (Usuario usuario : this.getGerenciadorAmizades().getListaDeAmigos()){
//				if (resposta.equals("")){
//					resposta += usuario.getGerenciadorItens().buscarItemCadastrado(chave, atributo, tipoOrdenacao, criterioOrdenacao);
//					
//				} else if (!resposta.equals("") && !usuario.getGerenciadorItens().buscarItemCadastrado(chave, atributo, tipoOrdenacao, criterioOrdenacao).equals("")){
//					resposta += "; " + usuario.getGerenciadorItens().buscarItemCadastrado(chave, atributo, tipoOrdenacao, criterioOrdenacao);
//				}
//			}
//		} else {
//			for (int i = this.getGerenciadorAmizades().getListaDeAmigos().size() - 1; i >= 0; i--){
//				if (resposta.equals("")){
//					resposta += this.getGerenciadorAmizades().getListaDeAmigos().get(i).getGerenciadorItens().buscarItemCadastrado(chave, atributo, tipoOrdenacao, criterioOrdenacao);
//					
//				} else if (!resposta.equals("") && !this.getGerenciadorAmizades().getListaDeAmigos().get(i).getGerenciadorItens().buscarItemCadastrado(chave, atributo, tipoOrdenacao, criterioOrdenacao).equals("")){
//					resposta += "; " + this.getGerenciadorAmizades().getListaDeAmigos().get(i).getGerenciadorItens().buscarItemCadastrado(chave, atributo, tipoOrdenacao, criterioOrdenacao);
//				}
//			}
//		}
		
		
		return resposta;
	}
	private Usuario usuarioComMaisAltaReputacaoDaLista(List<Usuario> lista){
		Usuario usuario = lista.get(0);
		for(Usuario usr: lista){
			if (usr.getReputacao()>usuario.getReputacao()){
				usuario = usr;
			}
		}return usuario;
	}
	
	private Usuario usuarioComMenorReputacaoDaLista(List<Usuario> lista){
		Usuario usuario = lista.get(0);
		for(Usuario usr: lista){
			if (usr.getReputacao()<usuario.getReputacao()){
				usuario = usr;
			}
		}return usuario;
	}
	
	public int getReputacao(){
		return getGerenciadorItens().quantEmprestimosCompletados();
	}
	
	

	
}