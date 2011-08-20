package Classes;

import java.util.ArrayList;

/**
 * Esta Classe representa a Rede Social que criamos, onde nela se encontra as principais
 * funcionalidades do Sistema
 * 
 * @author ARTHUR SENA, IGOR GOMES, RENAN PINTO, RODOLFO DE LIMA
 * @version 1.0
 */

public class RedeSocial {
   
	private ArrayList<Usuario> listaDeUsuarios;
	
	/**
	 * Inicia os atributos da Classe
	 */
	
	public RedeSocial(){
		listaDeUsuarios = new ArrayList<Usuario>();
	}
	
	/**
	 * Adiciona um Usuario na Rede Social, caso este ainda nao esteja nela
	 * 
	 * @param usr Usuario que sera adicionado
	 * 
	 * */
	
	public void adicionaUsuario(Usuario usr)throws Exception{
		if (usr == null){
			throw new Exception("Usuario nao pode ser igual a null");
		}
		else if (listaDeUsuarios.contains(usr)){
			throw new Exception("Usuario ja esta cadastrado no Sistema");
		}
		
		else if (loginESenhaEhUltilizado(usr.getLogin(), usr.getSenha())){
			throw new Exception("Login e Senha ja estao sendo Ultilizados");
		}
		listaDeUsuarios.add(usr);
	}
	
	/**
	 * Envia um Convite de Amizade do Usuario usr1 para o Usuario usr2
	 * @param usr1 
	 *           Usuario que manda o convite
	 * @param usr2
	 *           Usuario que recebera o convite
	 *          
	 */
	
	public void enviarConvite(Usuario usr1, Usuario usr2)throws Exception{
		
		
		if (usr1==null || usr2==null){
			throw new Exception("Usuario nao pode ser igual a Null");
		}
		
	    else if (!listaDeUsuarios.contains(usr1)){
			throw new Exception(usr1.getNome() + "nao esta cadastrado no sistema");
		}
		
	    else if (!listaDeUsuarios.contains(usr2)){
			throw new Exception(usr2.getNome() + "nao esta cadastrado no sistema");
		}
		
	    else if(usr2.getListaDeProvaveisAmigos().contains(usr1)){
	    	throw new Exception("Um convite seu ja foi enviado para " + usr2.getNome());
	    }
		
		usr2.getListaDeProvaveisAmigos().add(usr1);

	}
		
	
	/**
	 * Cria um Vinculo de Amizade entre dois Usuarios distintos
	 * @param usr1 
	 *          Usuario que sera amigo de usr2
	 * @param usr2
	 *          Usuario que sera amigo de usr1 
	 */
	
	
	public void criarAmizade(Usuario usr1, Usuario usr2)throws Exception{
		
		if (!listaDeUsuarios.contains(usr1)){
			throw new Exception(usr1.getNome() + "nao esta cadastrado no Sistema");
		}
		
		else if(!listaDeUsuarios.contains(usr2)){
			throw new Exception(usr2.getNome() + "nao esta cadastrado no Sistema");
		}
		
		else if (!usr1.getListaDeAmigos().contains(usr2)){
			usr1.getListaDeAmigos().add(usr2);
		}
		else if (!usr2.getListaDeAmigos().contains(usr1)){
			usr2.getListaDeAmigos().add(usr1);
		}
		
	}
	
	/**
	 * Elimina o Vinculo de Amizade entra duas Pessoas
	 * @param usr1 
	 *          Usuario 1
	 * @param usr2
	 *          Usuario 2 
	 * @throws Exception
	 *          Caso um dos Usuarios nao esteja no Sistema ou
	 *          Caso os usuarios nao sejam amigos
	 */
	
	public void desfazerAmizade(Usuario usr1, Usuario usr2)throws Exception{
		if (!listaDeUsuarios.contains(usr1)){
			throw new Exception(usr1.getNome() + "nao esta cadastrado no Sistema");
		}
		
		else if(!listaDeUsuarios.contains(usr2)){
			throw new Exception(usr2.getNome() + "nao esta cadastrado no Sistema");
		}
		
		else if(!usr1.getListaDeAmigos().contains(usr2)){
			throw new Exception(usr2.getNome() + "nao eh amigo de" + usr1.getNome());
		}
		
		else if(!usr2.getListaDeAmigos().contains(usr1)){
			throw new Exception(usr1.getNome() + "nao eh amigo de" + usr2.getNome());
		}
		
		usr1.getListaDeAmigos().remove(usr2);
		usr2.getListaDeAmigos().remove(usr1);
	}
	
	/**
	 * Remove um Usuario da Rede Social
	 * @param usr 
	 *           Usuario a ser removido
	 * @throws Exception
	 *           Caso o Usuario seja igual a null ou nao esteja cadastrado
	 */
	
	public void removerUsuario(Usuario usr)throws Exception{
		
		if (usr==null){
			throw new Exception("Usuario nao pode ser igual a null");
		}
		else if (!listaDeUsuarios.contains(usr)){
			throw new Exception("Usuario nao esta cadastrado no Sistema");
		}
		listaDeUsuarios.remove(usr);	
		for (Usuario usuarios: listaDeUsuarios){
			
			if (usuarios.getListaDeAmigos().contains(usr)){
				usuarios.getListaDeAmigos().remove(usr);
			}
			
			if (usuarios.getListaDeProvaveisAmigos().contains(usr)){
				usuarios.getListaDeProvaveisAmigos().remove(usr);
			}
			
		}
	}
	
	
	
	/**
	 * Recupera todos os Usuarios com determinado nome
	 * @param nome
	 *          Nome a ser Pesquisado
	 * @return 
	 *          Uma lista com todos os Usuarios que tem aquele nome
	 */
	
	public ArrayList<Usuario> buscarUsuarioPorNome(String nome){
		ArrayList<Usuario> listaDeNomes = new ArrayList<Usuario>();
		
		for (Usuario usr: listaDeUsuarios){
			if (usr.getNome().equals(nome)){
				listaDeNomes.add(usr);
			}
		}return listaDeNomes;
	}
	
	/**
	 * Recupera todos os usuarios com determinado Endereco
	 * @param end 
	 *           Endereco a ser pesquisado
	 * @return
	 *           Uma lista com todos os Usuarios que tem aquele endereco
	 */
	
	public ArrayList<Usuario> buscarUsuarioPorEndereco(Endereco end){
		ArrayList<Usuario> listaDeEndereco = new ArrayList<Usuario>();
		
		for (Usuario usr: listaDeUsuarios){
			if(usr.getEnd().equals(end)){
				listaDeEndereco.add(usr);
			}
		}
		return listaDeEndereco;
	}
	
	private boolean loginESenhaEhUltilizado(String login, String senha){
		boolean resp = false;
		
		for (Usuario usr: listaDeUsuarios){
			if (usr.getLogin().equals(login) && usr.getSenha().equals(senha)){
				resp = true;
				break;
			}
		}return resp;
	}
	
}
