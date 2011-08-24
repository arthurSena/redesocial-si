package Classes;

import java.util.ArrayList;

/**
 * Classe que guarda as amizades de um Usuario
 * @author ARTHUR SENA, IGOR GOMES, RENAN PINTO, RODOLFO DE LIMA
 *
 */

public class GerenciadorAmizades {

	private ArrayList<Usuario> listaDeAmigos;
	private ArrayList<Usuario> listaDeProvaveisAmigos;
	
	/**
	 * Inicia os Atributos da Classe
	 */
	
	public GerenciadorAmizades(){
		listaDeAmigos = new ArrayList<Usuario>();
		listaDeProvaveisAmigos = new ArrayList<Usuario>();
	}

	/**
	 * Recupera a Lista de Amigos do Usuario 
	 * @return Lista de Amigos do Usuario
	 */
	
	public ArrayList<Usuario> getListaDeAmigos() {
		return listaDeAmigos;
	}
	
	/**
	 * Recupera a Lista com os Amigos que enviaram convite pra Usuario
	 * @return A Lista com os provaveis Amigos do Usuario
	 */
	
	public ArrayList<Usuario> getListaDeProvaveisAmigos() {
		return listaDeProvaveisAmigos;
	}
	
	/**
	 * Adiciona um Usuario na Lista de Amigos de outro Usuario
	 * @param usr 
	 *          Usuario que sera adicionado na lista de amigos
	 * @throws Exception
	 *          Caso o usuario seja igual a null
	 */
	
	public void adicionarAmigo(Usuario usr)throws Exception{
		if (usr==null){
			throw new Exception("Usuario nao pode ser igual a null");
		}
		else if(listaDeAmigos.contains(usr)){
			throw new Exception("Usuario ja eh seu Amigo");
		}
		else if(!listaDeProvaveisAmigos.contains(usr)){
			throw new Exception("Voce nao enviou convite para " + usr.getNome() + "ou ele nao solicitou sua amizade");
		}
		listaDeAmigos.add(usr);
		listaDeProvaveisAmigos.remove(usr);
	}
	
	/**
	 * Adiciona um Usuario na Lista de Provaveis Amigos de outro Usuario
	 * @param usr 
	 *          Usuario que sera adicionado na lista de provaveis amigos
	 * @throws Exception
	 *          Caso o usuario seja igual a null
	 */
	public void adicionarProvavelAmigo(Usuario usr)throws Exception{
		if (usr==null){
			throw new Exception("Usuario nao pode ser igual a null");
		}
		else if(!listaDeProvaveisAmigos.contains(usr)){
			listaDeProvaveisAmigos.add(usr);
		}
	}
	
	/**
	 * Diz se um Usuario eh amigo de Outro Usuario
	 * @param usr
	 *         Usuario que sera analizado
	 * @return
	 *        True, caso sejam amigos
	 *        False, caso contrario
	 * @throws Exception
	 *       Caso o Usuario seja igual a Null
	 */
	
	public boolean ehMeuAmigo(Usuario usr)throws Exception{
		if (usr==null){
			throw new Exception("Usuario nao pode ser igual a Null");
		}
		return listaDeAmigos.contains(usr);
	}
	
	/**
	 * Diz se um Usuario eh um provavel amigo de Outro Usuario
	 * @param usr
	 *         Usuario que sera analizado
	 * @return
	 *        True, caso sejam amigos
	 *        False, caso contrario
	 * @throws Exception
	 *       Caso o Usuario seja igual a Null
	 */
	
	public boolean ehUmProvavelAmigo(Usuario usr)throws Exception{
		if (usr==null){
			throw new Exception("Usuario nao pode ser igual a Null");
		}
		return listaDeProvaveisAmigos.contains(usr);
	}
	
	/**
	 * Remove um Usuario da sua lista de amigos
	 * @param usr
	 *          Usuario a ser removido
	 * @throws Exception
	 *         Caso Usuario seja igual a Null 
	 *         ou nao seja seu amigo
	 */
	
	public void removerAmigo(Usuario usr)throws Exception{
		if (usr==null){
			throw new Exception("Usuario nao pode ser igual a Null");
		}
		else if(!listaDeAmigos.contains(usr)){
			throw new Exception("Usuario nao eh seu amigo");
		}
		listaDeAmigos.remove(usr);
	}
	
	/**
	 * Remove um Usuario da sua lista de provaveis amigos
	 * @param usr
	 *          Usuario a ser removido
	 * @throws Exception
	 *         Caso Usuario seja igual a Null 
	 *         ou nao seja seu amigo
	 */
	public void removerProvavelAmigo(Usuario usr)throws Exception{
		if (usr==null){
			throw new Exception("Usuario nao pode ser igual a Null");
		}
		else if(!listaDeProvaveisAmigos.contains(usr)){
			throw new Exception("Usuario nao eh seu provavel amigo");
		}
		
		listaDeProvaveisAmigos.remove(usr);
	}
	
}
