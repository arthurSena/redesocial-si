package Classes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.ls.LSInput;

public class RedeSocial {

	GerenciadorUsuarios gerenciadorUsu;

	public RedeSocial() {
		gerenciadorUsu = new GerenciadorUsuarios();
	}

	public void zerarSistema() {
		gerenciadorUsu = new GerenciadorUsuarios();
	}

	public void encerrarSistema() {
		// TODO tem que salvar os dados dos usuarios em algum local
	}

	public GerenciadorUsuarios getGerenciadorUsuarios() {
		return gerenciadorUsu;
	}

	public void aprovarAmizade(String idSessao, String login) throws Exception {
		this.getGerenciadorUsuarios()
				.buscarUsuarioPorID(idSessao)
				.getGerenciadorAmizades()
				.adicionarAmigo(
						getGerenciadorUsuarios().buscarUsuarioPorLogin(login));
		this.getGerenciadorUsuarios()
				.buscarUsuarioPorLogin(login)
				.getGerenciadorAmizades()
				.adicionarAmigo(
						getGerenciadorUsuarios().buscarUsuarioPorID(idSessao));
	}

	public String getRequisicoesDeAmizade(String idSessao) throws Exception {
		return this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao)
				.getGerenciadorAmizades().getRequisicoesDeAmizade();
	}

	public String ehAmigo(String idSessao, String login) throws Exception {

		if (this.getGerenciadorUsuarios()
				.buscarUsuarioPorID(idSessao)
				.getGerenciadorAmizades()
				.ehMeuAmigo(
						getGerenciadorUsuarios().buscarUsuarioPorLogin(login))) {
			return "True";
		} else {
			return "False";
		}

	}

	public void requisitarAmizade(String idSessao, String login)
			throws Exception {
		getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		this.getGerenciadorUsuarios()
				.buscarUsuarioPorLogin(login)
				.getGerenciadorAmizades()
				.adicionarProvavelAmigo(
						getGerenciadorUsuarios().buscarUsuarioPorID(idSessao));

	}

	public String localizarUsuario(String idSessao, String chave,
			String atributo) throws Exception {
		return getGerenciadorUsuarios().localizarUsuario(idSessao, chave,
				atributo);
	}

	public String getAtributoItem(String idItem, String atributo)
			throws Exception {
		return getGerenciadorUsuarios().buscarDonoItem(idItem)
				.getGerenciadorItens().getAtributoItem(idItem, atributo);
	}

	public String cadastrarItem(String idSessao, String nome, String descricao,
			String categoria) throws Exception {
		Item it = new Item(nome, descricao, categoria);
		String id = it.gerarID(this.getGerenciadorUsuarios().quantDeItensDosUsuariosLogados() + 1);
		getGerenciadorUsuarios().buscarUsuarioPorID(idSessao)
				.getGerenciadorItens().adicionarItem(it);
		return id;
	}

	public void criarUsuario(String login, String nome, String endereco)
			throws Exception {
		getGerenciadorUsuarios().criarUsuario(login, nome, endereco);
	}

	public String abrirSessao(String login) throws Exception {
		if (!getGerenciadorUsuarios().logiEhUsado(login)) {
			throw new Exception("Usuário inexistente");
		}
		Usuario usr = getGerenciadorUsuarios().buscarUsuarioPorLogin(login);
		String id = usr.gerarID();
		getGerenciadorUsuarios().getListaUsuariosLogados().add(usr);
		return id;

	}

	public String getAtributoUsuario(String login, String atributo)
			throws Exception {
		return getGerenciadorUsuarios().getAtributoUsuario(login, atributo);
	}

	/**
	 * Retorna a lista de amigos que o usuario passado como idSessao tem. Isso
	 * em forma de string
	 * 
	 * @param idSessao
	 * @return
	 */
	public String getAmigos(String idSessao) throws Exception {

		return getGerenciadorUsuarios().buscarUsuarioPorID(idSessao)
				.getGerenciadorAmizades().stringDeAmigos();

	}

	/**
	 * Retorna a lista de amigos que o usuario passado como login tem. Isso em
	 * forma de string
	 * 
	 * @param idSessao
	 * @param login
	 * @return
	 */
	public String getAmigos(String idSessao, String login) throws Exception {

		if (getGerenciadorUsuarios().buscarUsuarioPorID(idSessao)
				.getGerenciadorAmizades().getListaDeAmigos().isEmpty()) {
			return "O usuário não possui amigos";
		}

		else if (!getGerenciadorUsuarios().logiEhUsado(login)) {
			throw new Exception("Login inexistente");
		}

		else {
			return getGerenciadorUsuarios().buscarUsuarioPorID(idSessao)
					.getGerenciadorAmizades().buscaPerfil(login)
					.getGerenciadorAmizades().stringDeAmigos();
		}
	}

	public String getItens(String idSessao) throws Exception {

		return getGerenciadorUsuarios().buscarUsuarioPorID(idSessao)
				.getGerenciadorItens().stringDeItens();

	}

	public String getItens(String idSessao, String login) throws Exception {

		if (!getGerenciadorUsuarios()
				.buscarUsuarioPorID(idSessao)
				.getGerenciadorAmizades()
				.ehMeuAmigo(
						getGerenciadorUsuarios().buscarUsuarioPorLogin(login))) {
			throw new Exception(
					"O usuário não tem permissão para visualizar estes itens");
		}

		if (getGerenciadorUsuarios().buscarUsuarioPorID(idSessao)
				.getGerenciadorAmizades().buscaPerfil(login)
				.getGerenciadorItens().getListaMeusItens().isEmpty()) {
			return "O usuário não possui itens cadastrados";
		}

		else {
			return getGerenciadorUsuarios().buscarUsuarioPorID(idSessao)
					.getGerenciadorAmizades().buscaPerfil(login)
					.getGerenciadorItens().stringDeItens();
		}

	}
	
	public String requisitarEmprestimo(String idSessao, String idItem, int dias) throws Exception {
		Usuario usr = getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		if (getGerenciadorUsuarios().buscarDonoItem(idItem).getGerenciadorAmizades().ehMeuAmigo(getGerenciadorUsuarios().buscarUsuarioPorID(idSessao))){
			return getGerenciadorUsuarios()
			.buscarDonoItem(idItem)
			.getGerenciadorItens()
			.requisitarEmprestimos(usr,idItem, dias);
		}
		throw new Exception("O usuário não tem permissão para requisitar o empréstimo deste item");

	}
	
	public String getEmprestimos(String idSessao, String tipo)throws Exception{
		return this.getGerenciadorUsuarios().getEmprestimos(idSessao, tipo);
	}
	
	public String aprovarEmprestimo(String idSessao, String idRequisicaoEmprestimo)throws Exception{
		/*if(this.getGerenciadorUsuarios().buscarUsuarioEmprestador2(idRequisicaoEmprestimo).equals(this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao))){
			throw new Exception("O empréstimo só pode ser aprovado pelo dono do item");
		}*/
		return this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).getGerenciadorItens().aprovarRequisicaoEmprestimo(idRequisicaoEmprestimo);
	}
}
