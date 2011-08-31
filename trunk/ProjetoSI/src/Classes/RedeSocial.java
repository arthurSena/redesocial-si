package Classes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.ls.LSInput;



public class RedeSocial {
	
	List<Usuario> listaDeUsuarios;
	List<Usuario> listaDeUsuariosLogados;
	
	public RedeSocial(){
		listaDeUsuarios = new ArrayList<Usuario>();
		listaDeUsuariosLogados = new ArrayList<Usuario>();
	}
	
	public void zerarSistema(){
		listaDeUsuarios =new ArrayList<Usuario>();
		listaDeUsuariosLogados = new ArrayList<Usuario>();
	}
	
	public void encerrarSistema(){
		// TODO tem que salvar os dados dos usuarios em algum local
	}
	
	public void aprovarAmizade(String idSessao, String login)throws Exception{
		if (!stringValida(idSessao)){
			throw new Exception("Sessão inválida");
		}
		else if(buscarUsuarioPorID(idSessao)==null){
			throw new Exception("Sessão inexistente");
		}
		else if (!stringValida(login)){
			throw new Exception("Login inválido");
		}
		else if (buscarUsuarioPorLogin(login)==null){
			throw new Exception("Login inexistente");
		}
		buscarUsuarioPorID(idSessao).getGerenciadorAmizades().adicionarAmigo(buscarUsuarioPorLogin(login));
		buscarUsuarioPorLogin(login).getGerenciadorAmizades().adicionarAmigo(buscarUsuarioPorID(idSessao));
	}
	
	
	public String getRequisicoesDeAmizade(String idSessao) throws Exception{
		if (!stringValida(idSessao)){
			throw new Exception("Sessão inválida");
		}
		else if(buscarUsuarioPorID(idSessao)==null){
			throw new Exception("Sessão inexistente");
		}
		else if(buscarUsuarioPorID(idSessao).getGerenciadorAmizades().getListaDeProvaveisAmigos().isEmpty()){
			return ("Não há requisições");
		}
		else{
			String requisicoes = "";
			for (Usuario usr : buscarUsuarioPorID(idSessao).getGerenciadorAmizades().getListaDeProvaveisAmigos()){
				requisicoes+= usr.getLogin() + "; ";
			}
		
			return formatarRequisicoes(requisicoes);
		}
	}
	
	public String ehAmigo(String idSessao, String login) throws Exception{
		if (!stringValida(idSessao)){
			throw new Exception("Sessão inválida");
		}
		else if(buscarUsuarioPorID(idSessao)==null){
			throw new Exception("Sessão inexistente");
		}
		else if (!stringValida(login)){
			throw new Exception("Login inválido");
		}
		else if (buscarUsuarioPorLogin(login)==null){
			throw new Exception("Login inexistente");
		}
		else{
			if(buscarUsuarioPorID(idSessao).getGerenciadorAmizades().ehMeuAmigo(buscarUsuarioPorLogin(login))){
				return "True";
			}
			else{
				return "False";
			}
		}
	}
	
	public void requisitarAmizade(String idSessao, String login)throws Exception{
		if (!stringValida(idSessao)){
			throw new Exception("Sessão inválida");
		}
		else if(buscarUsuarioPorID(idSessao)==null){
			throw new Exception("Sessão inexistente");
		}
		else if (!stringValida(login)){
			throw new Exception("Login inválido");
		}
		else if (buscarUsuarioPorLogin(login)==null){
			throw new Exception("Login inexistente");
		}
		//buscarUsuarioPorID(idSessao).getGerenciadorAmizades().adicionarProvavelAmigo(buscarUsuarioPorLogin(login));
		buscarUsuarioPorLogin(login).getGerenciadorAmizades().adicionarProvavelAmigo(buscarUsuarioPorID(idSessao));
	}
	
	public String localizarUsuario(String idSessao, String chave, String atributo)throws Exception{
		
		if (!stringValida(idSessao)){
			throw new Exception("Sessao inválida");
		}
		else if(buscarUsuarioPorID(idSessao)==null){
			throw new Exception("Sessao inexistente");
		}
		else if (!stringValida(chave)){
			throw new Exception("Palavra-chave inválida");
		}
		else if (!stringValida(atributo)){
			throw new Exception("Atributo inválido");
		}
		
		else if(atributo.equals("nome")){
			return buscarPerfisDeUsuarios(idSessao, chave, atributo);
		}
		else if(atributo.equals("endereco")){
			return buscarPerfisDeUsuarios(idSessao, chave, atributo);
		}
		else{
			throw new Exception("Atributo inexistente");
		}
	}
	
	
	
	
	

	public String getAtributoItem(String idItem, String atributo)throws Exception{
		
		if (!stringValida(idItem)){
			throw new Exception("Identificador do item é inválido");
		}
		else if(!stringValida(atributo)){
			throw new Exception("Atributo inválido");
		}
		else if(buscarItemPorID(idItem)==null){
			throw new Exception("Item inexistente");
		}
		else if(atributo.equals("nome")){
			return buscarItemPorID(idItem).getNome();
		}
		else if(atributo.equals("descricao")){
			return buscarItemPorID(idItem).getDescricao();
		}
		else if(atributo.equals("categoria")){
			return buscarItemPorID(idItem).getCategoria();
		}
		else{
			throw new Exception("Atributo inexistente");
		}
	}
	
	public String cadastrarItem(String idSessao, String nome, String descricao, String categoria)throws Exception{
		if (!stringValida(idSessao)){
			throw new Exception("Sessao inválida");
		}
		else if(buscarUsuarioPorID(idSessao)==null){
			throw new Exception("Sessao inexistente");
		}
		Item it = new Item(nome, descricao, categoria);
		String id = it.gerarID(quantidadeDeItensLogados() + 1);

		buscarUsuarioPorID(idSessao).getGerenciadorItens().adicionarItem(it);
		
		return id;
	}
	
	public void criarUsuario(String login, String nome, String endereco)throws Exception{
		Usuario usr = new Usuario(nome, login, endereco);
		if (logiEhUsado(login)){
			throw new Exception("Já existe um usuário com este login");
		}
		
		listaDeUsuarios.add(usr);
	}
	
	public String abrirSessao(String login) throws Exception{
		if (!stringValida(login)){
			throw new Exception("Login inválido");
		}
		Usuario usr = buscarUsuarioPorLogin(login);
		if (usr==null){
			System.out.println(listaDeUsuarios);
			throw new Exception("Usuário inexistente");
		}
		String id = usr.gerarID();
		listaDeUsuariosLogados.add(usr);
		return id;
		
	}
	
	public String getAtributoUsuario(String login, String atributo)throws Exception{
		if (!stringValida(login)){
			throw new Exception("Login inválido");
		}
		else if(!stringValida(atributo)){
			throw new Exception("Atributo inválido");
		}
		
		Usuario usr = buscarUsuarioPorLogin(login);
		
		if (usr==null){
			throw new Exception("Usuário inexistente");
		}
		else if(atributo.equals("nome")){
			return usr.getNome();
		}
		
		else if(atributo.equals("endereco")){
			return usr.getEndereco();
		}
		
		else if(atributo.equals("login")){
			return usr.getLogin();
		}
		else{
			throw new Exception("Atributo inexistente");
		}
	}
	
	private Item buscarItemPorID(String id){
		for (Usuario usr: listaDeUsuariosLogados){
			for (Item it : usr.getGerenciadorItens().getListaMeusItens()){
				
				if (it.getID().equals(id)){
					return it;
				}
			}
		}return null;
	}
	
	private Usuario buscarUsuarioPorLogin(String login){
		for (Usuario usr: listaDeUsuarios){
			if (usr.getLogin().equals(login)){
				return usr;
			}
		}return null;
	}
	
	private boolean stringValida(String string){
        if (string==null || string.isEmpty()){
            return false;
        }
        return true;
    }
	
	private boolean logiEhUsado(String login){
		for (Usuario usr: listaDeUsuarios){
			if (usr.getLogin().equals(login)){
				return true;
			}
		}return false;
	}
	
	private String buscarPerfisDeUsuarios(String id, String chave, String atributo){
 		String listaUsuarios = "";
 		int cont = 0;
 		
 		for (int i = listaDeUsuarios.size()-1 ; i>=0; i--){
 			Usuario usr = listaDeUsuarios.get(i) ;
 			if (!(usr.getID().equals(id))){
 				if (atributo.equals("nome") && usr.getNome().toLowerCase().contains(chave.toLowerCase())){
 					if (cont==0){
 						listaUsuarios += usr.visualizarPerfil();
 					}
 					else{
 						listaUsuarios += usr.visualizarPerfil() + "; ";
 						
 					}
 					cont++;
 				}
 				else if(atributo.equals("endereco") && usr.getEndereco().toLowerCase().contains(chave.toLowerCase())){
 					if (cont==0){
 						listaUsuarios += usr.visualizarPerfil();
 					}
 					else{
 						listaUsuarios += "; " + usr.visualizarPerfil();
 					}
 					cont++;
 				}
 			}
 		}
 		if(listaUsuarios.equals("")){
 			return "Nenhum usuário encontrado";
 		}
 		return listaUsuarios;
 	}
	
	private Usuario buscarUsuarioPorID(String id){
		for (Usuario usr: listaDeUsuariosLogados){
			if (usr.getID().equals(id)){
				return usr;
			}
		}return null;
	}
	
	private int quantidadeDeItensLogados(){
		int cont = 0;
		
		for (Usuario usr : listaDeUsuariosLogados){
			cont +=usr.getGerenciadorItens().getListaMeusItens().size();
		}
		return cont;
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
	
	public String getAmigos(String idSessao)  {
		
		if (buscarUsuarioPorID(idSessao).getGerenciadorAmizades().getListaDeAmigos().size() == 0)
			return "O usuário não possui amigos";
		
		else {
			return buscarUsuarioPorID(idSessao).getGerenciadorAmizades().stringDeAmigos();
		}
	}
	
	public String getAmigos(String idSessao, String login){
		
		if (buscarUsuarioPorID(idSessao).getGerenciadorAmizades().getListaDeAmigos().size() == 0)
			return "O usuário não possui amigos";
		
		else {
			buscarUsuarioPorID(idSessao).getGerenciadorAmizades().getListaDeAmigos().contains(login);
		}
		
		
		return this.getAmigos(idSessao);
		
	}
	
}
