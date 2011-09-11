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
			return "true";
		} else {
			return "false";
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
			throw new Exception("Usuário inexistente");
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
			String idRequisicaoEmprestim = getGerenciadorUsuarios().buscarDonoItem(idItem).getGerenciadorItens().requisitarEmprestimos(usr,idItem, dias);
//			String assunto = "Empréstimo do item " + this.getGerenciadorUsuarios().buscarDonoItem(idItem).getGerenciadorItens().buscarItemPorID(idItem).getNome() + " a " + this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).getNome();
//			String mensagem = this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).getNome() + " solicitou o empréstimo do item " + this.getGerenciadorUsuarios().buscarDonoItem(idItem).getGerenciadorItens().buscarItemPorID(idItem).getNome();
//			
//			enviarMensagem(idSessao, getGerenciadorUsuarios().buscarDonoItem(idItem).getLogin(), assunto, mensagem, idRequisicaoEmprestim);
			return idRequisicaoEmprestim;
		}
		throw new Exception("O usuário não tem permissão para requisitar o empréstimo deste item");

	}
	
	public String getEmprestimos(String idSessao, String tipo)throws Exception{
		return this.getGerenciadorUsuarios().getEmprestimos(idSessao, tipo);
	}
	
	public String aprovarEmprestimo(String idSessao, String idRequisicaoEmprestimo)throws Exception{
		/*if(!this.getGerenciadorUsuarios().requisicaoEmprestimoExiste(idRequisicaoEmprestimo)){
			throw new Exception("Requisição de empréstimo inexistente");
		}*/
		
		return this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).getGerenciadorItens().aprovarRequisicaoEmprestimo(this.getGerenciadorUsuarios().requisicaoEmprestimoExiste(idRequisicaoEmprestimo),idRequisicaoEmprestimo);
	}
	
	
	public void devolverItem(String idSessao, String idEmprestimo) throws Exception{
		this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		if (!stringValida(idEmprestimo)){
			throw new Exception("Identificador do empréstimo é inválido");
		}
		
		if (this.getGerenciadorUsuarios().buscarUsuarioEmprestador2(idEmprestimo) == null){
			throw new Exception("Empréstimo inexistente");
		}
		
		if (this.getGerenciadorUsuarios().buscarUsuarioEmprestador2(idEmprestimo).getGerenciadorItens().buscarItemIdEmprestimo(idEmprestimo) == null){
			throw new Exception("Empréstimo inexistente");
		}
		
		if (this.getGerenciadorUsuarios().buscarUsuarioEmprestador2(idEmprestimo).getGerenciadorItens().buscarItemIdEmprestimo(idEmprestimo).getEmprestimo().isDevolvido()){
			throw new Exception("Item já devolvido");
		}
		
		if (this.getGerenciadorUsuarios().buscarUsuarioEmprestador2(idEmprestimo).equals(getGerenciadorUsuarios().buscarUsuarioPorID(idSessao))){
			throw new Exception("O item só pode ser devolvido pelo usuário beneficiado");
		}
		
		this.getGerenciadorUsuarios().buscarUsuarioEmprestador2(idEmprestimo).getGerenciadorItens().buscarItemIdEmprestimo(idEmprestimo).getEmprestimo().setDevolvido(true);
		}
		
	
	
	public void confirmarTerminoEmprestimo(String idSessao, String idEmprestimo) throws Exception{
		this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		
		if (!stringValida(idEmprestimo)){
			throw new Exception("Identificador do empréstimo é inválido");
		}
		
		if (this.getGerenciadorUsuarios().buscarUsuarioEmprestador2(idEmprestimo) == null){
			throw new Exception("Empréstimo inexistente");
		}
		
		if (!this.getGerenciadorUsuarios().buscarUsuarioEmprestador2(idEmprestimo).equals(getGerenciadorUsuarios().buscarUsuarioPorID(idSessao))){
			throw new Exception("O término do empréstimo só pode ser confirmado pelo dono do item");
		}
		
		if (this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).getGerenciadorItens().buscarItemIdEmprestimo(idEmprestimo).getEmprestimo().isDevolucao()){
			throw new Exception("Término do empréstimo já confirmado");
		}
		
		this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).getGerenciadorItens().buscarItemIdEmprestimo(idEmprestimo).getEmprestimo().setDevolucao(true);

		
	}
	
	private boolean stringValida(String string) {
		if (string == null || string.isEmpty()) {
			return false;
		}
		return true;
	}
	
	
	public String enviarMensagem (String idSessao, String destinatario, String assunto, String mensagem) throws Exception{		
		if (!stringValida(idSessao)) {
			throw new Exception("Sessão inválida");
		}
		
		if (!stringValida(destinatario)){
			throw new Exception ("Destinário inválido");
		}
		
		try {
			this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		} catch (Exception e){
			throw new Exception ("Sessão inexistente");
		}
		
		try {
			getGerenciadorUsuarios().buscarUsuarioPorLogin(destinatario);
		} catch (Exception e){
			throw new Exception ("Destinário inexistente");
		}
		
		if (!stringValida(assunto)){
			throw new Exception("Assunto inválido");
		}
		
		if (!stringValida(mensagem)){
			throw new Exception("Mensagem inválida");
		}
		
		
		
		
//		
//		try {
//			this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
//		} catch (Exception e){
//			throw new Exception ("Sessão inexistente");
//		}
//		
//		
//		
//		try {
//			this.getGerenciadorUsuarios().buscarUsuarioPorID(destinatario);
//		} catch (Exception e){
//			throw new Exception ("Destinário inexistente");
//		}
		return this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).getGerenciadorMensagens().enviarMensagem(getGerenciadorUsuarios().buscarUsuarioPorLogin(destinatario), assunto, mensagem);
	}
	
	public String enviarMensagem (String idSessao, String destinatario, String assunto, String mensagem, String idRequisicaoEmprestimo) throws Exception{
		if (!stringValida(idSessao)) {
			throw new Exception("Sessão inválida");
		}
		
		if (!stringValida(destinatario)){
			throw new Exception ("Destinário inválido");
		}
		
		try {
			this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		} catch (Exception e){
			throw new Exception ("Sessão inexistente");
		}
		
		try {
			getGerenciadorUsuarios().buscarUsuarioPorLogin(destinatario);
		} catch (Exception e){
			throw new Exception ("Destinário inexistente");
		}
		
		if (!stringValida(mensagem)){
			throw new Exception("Mensagem inválida");
		}
		
		if (!stringValida(assunto)){
			throw new Exception("Assunto inválido");
		}
		
		if (!stringValida(idRequisicaoEmprestimo)){
			throw new Exception("Identificador da requisição de empréstimo é inválido");
		}
		
		try {
			this.getGerenciadorUsuarios().buscarUsuarioEmprestador2(idRequisicaoEmprestimo);
		} catch (Exception e) {
			throw new Exception("Requisição de empréstimo inexistente");
		}
		
		return this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).getGerenciadorMensagens().enviarMensagem(getGerenciadorUsuarios().buscarUsuarioPorLogin(destinatario), assunto, mensagem, idRequisicaoEmprestimo);
	}
	
	public String lerTopicos (String idSessao, String tipo) throws Exception{
		return this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).getGerenciadorMensagens().lerTopicos(tipo);
	}
	
	public String lerMensagens (String idSessao, String idTopico) throws Exception{
		return this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).getGerenciadorMensagens().lerMensagens(idTopico);
	}
	
	
	
}
