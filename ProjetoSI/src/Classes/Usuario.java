package Classes;

import java.util.ArrayList;


/**
 * Esta Classe representa um Usuário da Rede Social
 * 
 * @author ARTHUR SENA, RODOLFO DE LIMA, RENAN PINTO, IGOR GOMES
 * @version 1.0
 * 
 */

public class Usuario {
	
	String nome;
	String login;
	String senha;
	Endereco end;
	
	ArrayList<Usuario> listaDeAmigos = new ArrayList<Usuario>();
	ArrayList<Usuario> listaDeProvaveisAmigos = new ArrayList<Usuario>();
	
	ArrayList<Item> itensPraEmprestar = new ArrayList<Item>();
	ArrayList<Item> itensPraDevolver = new ArrayList<Item>();

	ArrayList<Mensagem> listaDeMensagens = new ArrayList<Mensagem>();
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
	
	public Usuario(String nome, String login, String senha, Endereco end)throws Exception{
		if (!stringValida(nome)){
			throw new Exception("Dado(s) Invalido(s)");
		}
		else if (!stringValida(login)){
			throw new Exception("Dado(s) Invalido(s)");
		}
		else if (!stringValida(senha)){
			throw new Exception("Dado(s) Invalido(s)");
		}
		
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.end = end;
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
	 * Recupera a Senha
	 * @return Senha do Usuario
	 */
	public String getSenha() {
		return senha;
	}
    
	/**
	 * Altera a Senha do Usuario
	 * 
	 * @param senha 
	 *           Nova Senha do Usuario
	 * @throws Exception
	 *            Caso a Senha esteja invalida
	 */
	public void setSenha(String senha) throws Exception {
		if (!stringValida(senha)){
			throw new Exception("Dado(s) Invalido(s)");
		}
		this.senha = senha;
	}

	/**
	 * Recupera o Endereco do Usuario 
	 * @return Endereco do Usuario
	 */
	public Endereco getEnd() {
		return end;
	}
	
	/**
	 * Altera o Endereco do Usuario
	 * @param end 
	 *          Novo Endereco do Usuario
	 */
	
	public void setEnd(Endereco end) {
		this.end = end;
	}

	/**
	 * Recupera a Lista de Amigos do Usuario 
	 * @return Lista de Amigos do Usuario
	 */
	
	public ArrayList<Usuario> getListaDeAmigos() {
		return listaDeAmigos;
	}

	/**
	 * Altera a Lista de Amigos do Usuario
	 * @param listaDeAmigos 
	 *                 Nova Lista de Amigos do Usuario
	 */
	
	public void setListaDeAmigos(ArrayList<Usuario> listaDeAmigos) {
		this.listaDeAmigos = listaDeAmigos;
	}

	/**
	 * Recupera os Itens que o Usuario pode emprestas
	 * @return Uma Array contendo os itens que o Usuario pode emprestar
	 */
	
	public ArrayList<Item> getItensPraEmprestar() {
		return itensPraEmprestar;
	}

	/**
	 * Altera a Lista de Itens que o Usuario pode emprestar
	 * @param itensPraEmprestar
	 *              Uma array com os novos itens que o Usuario pode emprestar
	 */
	
	public void setItensPraEmprestar(ArrayList<Item> itensPraEmprestar) {
		this.itensPraEmprestar = itensPraEmprestar;
	}

	/**
	 * Recupera a Lista com os Amigos que enviaram convite pra Usuario
	 * @return A Lista com os provaveis Amigos do Usuario
	 */
	
	public ArrayList<Usuario> getListaDeProvaveisAmigos() {
		return listaDeProvaveisAmigos;
	}
    
	/**
	 * Altera a Lista de Provaveis Amigos do Usuario
	 * @param listaDeProvaveisAmigos 
	 *                    Uma nova lista com os Usuarios que enviaram convite
	 */
	
	public void setListaDeProvaveisAmigos(ArrayList<Usuario> listaDeProvaveisAmigos) {
		this.listaDeProvaveisAmigos = listaDeProvaveisAmigos;
	}

	/**
	 * Compara Dois Usuarios
	 * @param usr
	 * @return True, Caso os dois Usuarios sejam Iguais
	 *         False, Caso contrario
	 */
	
	public boolean equals(Usuario usr){
		boolean resp = false;
		
		if (!(usr instanceof Usuario)){
           resp = false;
        }

		else if (usr.getLogin().equals(this.login) && usr.getSenha().equals(this.senha) && 
				usr.getNome().equals(this.nome) && usr.getEnd().equals(this.end)){
			resp = true;
		}
		
		return resp;
	}
	
	private boolean stringValida(String string){
        if (string==null || string.isEmpty()){
            return false;
        }
        return true;
    }
	


	

}
