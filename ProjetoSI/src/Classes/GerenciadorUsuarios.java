package Classes;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorUsuarios {

	List<Usuario> listaDeUsuarios;
	List<Usuario> listaDeUsuariosLogados;

	public GerenciadorUsuarios() {
		listaDeUsuarios = new ArrayList<Usuario>();
		listaDeUsuariosLogados = new ArrayList<Usuario>();
	}
	
	public List<Usuario> getListaUsuarios(){
		return listaDeUsuarios;
	}
	
	public List<Usuario> getListaUsuariosLogados(){
		return listaDeUsuariosLogados;
	}
	
	 public String getAtributoUsuario(String login, String atributo)throws Exception{
         if (!stringValida(login)){
                 throw new Exception("Login inválido");
         }
         else if(!stringValida(atributo)){
                 throw new Exception("Atributo inválido");
         }
         else if (!logiEhUsado(login)){
             throw new Exception("Usuário inexistente");
         }
         Usuario usr = buscarUsuarioPorLogin(login);
         
         
         if(atributo.equals("nome")){
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
	
	public void criarUsuario(String login, String nome, String endereco)throws Exception{
        Usuario usr = new Usuario(nome, login, endereco);
        if (logiEhUsado(login)){
                throw new Exception("Já existe um usuário com este login");
        }
        
        listaDeUsuarios.add(usr);
}

	public String localizarUsuario(String idSessao, String chave,
			String atributo) throws Exception {

		if (!stringValida(idSessao)) {
			throw new Exception("Sessao inválida");
		} else if (buscarUsuarioPorID(idSessao) == null) {
			throw new Exception("Sessão inexistente");
		} else if (!stringValida(chave)) {
			throw new Exception("Palavra-chave inválida");
		} else if (!stringValida(atributo)) {
			throw new Exception("Atributo inválido");
		}

		else if (atributo.equals("nome")) {
			return buscarPerfisDeUsuarios(idSessao, chave, atributo);
		} else if (atributo.equals("endereco")) {
			return buscarPerfisDeUsuarios(idSessao, chave, atributo);
		} else {
			throw new Exception("Atributo inexistente");
		}

	}
	

	public Usuario buscarUsuarioPorLogin(String login) throws Exception {
		if (!stringValida(login)) {
			throw new Exception("Login inválido");
		}else if (!logiEhUsado(login)) {
			throw new Exception("Login inexistente");
		}
		for (Usuario usr : listaDeUsuarios) {
			if (usr.getLogin().equals(login)) {
				return usr;
			}
		}
		return null;
	}

	private boolean stringValida(String string) {
		if (string == null || string.isEmpty()) {
			return false;
		}
		return true;
	}

	public boolean logiEhUsado(String login) throws Exception {
		if (!stringValida(login)) {
			throw new Exception("Login inválido");
		}
		for (Usuario usr : listaDeUsuarios) {
			if (usr.getLogin().equals(login)) {
				return true;
			}
		}
		return false;
	}

	private String buscarPerfisDeUsuarios(String id, String chave,
			String atributo) {
		String listaUsuarios = "";
		int cont = 0;

		for (int i = listaDeUsuarios.size() - 1; i >= 0; i--) {
			Usuario usr = listaDeUsuarios.get(i);
			if (!(usr.getID().equals(id))) {
				if (atributo.equals("nome")
						&& usr.getNome().toLowerCase()
								.contains(chave.toLowerCase())) {
					if (cont == 0) {
						listaUsuarios += usr.visualizarPerfil();
					} else {
						listaUsuarios += usr.visualizarPerfil() + "; ";

					}
					cont++;
				} else if (atributo.equals("endereco")
						&& usr.getEndereco().toLowerCase()
								.contains(chave.toLowerCase())) {
					if (cont == 0) {
						listaUsuarios += usr.visualizarPerfil();
					} else {
						listaUsuarios += "; " + usr.visualizarPerfil();
					}
					cont++;
				}
			}
		}
		if (listaUsuarios.equals("")) {
			return "Nenhum usuário encontrado";
		}
		return listaUsuarios;
	}

	public Usuario buscarUsuarioPorID(String idSessao) throws Exception {
		if (!stringValida(idSessao)) {
			throw new Exception("Sessão inválida");
		}
		for (Usuario usr : listaDeUsuariosLogados) {
			if (usr.getID().equals(idSessao)) {
				return usr;
			}
		}
		throw new Exception("Sessão inexistente");
	}
	
	public int quantDeItensDosUsuariosLogados(){
		int cont = 0;
		for (Usuario usr: listaDeUsuariosLogados){
			cont+= (usr.getGerenciadorItens().getQuantidadeMeusItens());
		}return cont;
	}
	
	public Usuario buscarDonoItem(String idItem) throws Exception {
		if (!stringValida(idItem)) {
			throw new Exception("Identificador do item é inválido");
		}
		for (Usuario usr : listaDeUsuariosLogados) {
			for (Item it : usr.getGerenciadorItens().getListaMeusItens()) {
				if (it.getID().equals(idItem)) {
					return usr;
				}
			}
		}
		throw new Exception("Item inexistente");
	}

}
