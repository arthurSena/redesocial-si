package Classes;


/**
 * 
 * Classe responsavel por se comunicar com gerenciador de usuario. (Funciona como fachada)
 * 
 * @author ARTHUR SENA, RODOLFO DE LIMA, RENAN PINTO, IGOR GOMES
 * @version 1.0
 *
 */

public class Fachada {

	GerenciadorUsuarios gerenciadorUsu;

	/**
	 * Inicializa gerenciador de usuarios
	 */
	public Fachada() {
		gerenciadorUsu = new GerenciadorUsuarios();
	}

	/**
	 * Zera o sistema
	 */
	public void zerarSistema() {
		gerenciadorUsu = new GerenciadorUsuarios();
	}

	/**
	 * Responsavel por encerrar o sistema
	 */
	public void encerrarSistema() {
		// TODO tem que salvar os dados dos usuarios em algum local
	}

	/**
	 * Responsavel por retornar o gerenciador de usuario
	 * @return Gerenciador de usuario
	 */
	public GerenciadorUsuarios getGerenciadorUsuarios() {
		return gerenciadorUsu;
	}

	/**
	 * Responsavel por aprovar amizade entre dois usuarios
	 * @param idSessao Sessao do usuario 
	 * @param login Login do usuario
	 * @throws Exception Caso idSessao ou login sejam invalidos
	 */
	public void aprovarAmizade(String idSessao, String login) throws Exception {
		this.getGerenciadorUsuarios()
				.buscarUsuarioPorID(idSessao)
				.getGerenciadorAmizades()
				.adicionarAmigo2(
						getGerenciadorUsuarios().buscarUsuarioPorLogin(login));
		this.getGerenciadorUsuarios()
				.buscarUsuarioPorLogin(login)
				.getGerenciadorAmizades()
				.adicionarAmigo(
						getGerenciadorUsuarios().buscarUsuarioPorID(idSessao));
	}

	/**
	 * Responsavel pelas requisicoes de amizade entre dois usuarios
	 * @param idSessao IdSessao do usuario
	 * @return IdRequisicao de amizade
	 * @throws Exception Caso a idSessao seja invalida
	 */
	public String getRequisicoesDeAmizade(String idSessao) throws Exception {
		return this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao)
				.getGerenciadorAmizades().getRequisicoesDeAmizade();
	}

	/**
	 * Responsavel por verificar se dois usuarios sao amigos
	 * @param idSessao IdSessao do usuario
	 * @param login Login do usuario
	 * @return true se forem amigos ou false caso contrario
	 * @throws Exception Caso idSessao ou login sejam invalidos
	 */
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

	/**
	 * Responsavel por requisitar amizade entre dois usuarios
	 * @param idSessao IdSessao do usuario
	 * @param login Login do usuario
	 * @throws Exception Caso idSessao ou login sejam invalidos
	 */
	public void requisitarAmizade(String idSessao, String login)
			throws Exception {
		getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		this.getGerenciadorUsuarios()
				.buscarUsuarioPorLogin(login)
				.getGerenciadorAmizades()
				.adicionarProvavelAmigo(
						getGerenciadorUsuarios().buscarUsuarioPorID(idSessao));

	}

	/**
	 * Responsavel por localizar usuario apartir de uma idSessao, chave e atributo
	 * @param idSessao IdSessao do usuario
	 * @param chave Plavra chave que deve ser encontrada
	 * @param atributo Atributo do usuario que pode ser nome ou endereco
	 * @return Retorna a busca
	 * @throws Exception Caso um dos parametros sejam invalidos
	 */
	public String localizarUsuario(String idSessao, String chave, String atributo) throws Exception {
		return getGerenciadorUsuarios().localizarUsuario(idSessao, chave,
				atributo);
	}

	/**
	 * Responsavel por recuperar um determinado atributo do item 
	 * @param idItem ID do item
	 * @param atributo atribudo que deve ser recuperado
	 * @return Retorna o atributo do item
	 * @throws Exception Caso uma das entradas sejam invalidas
	 */
	public String getAtributoItem(String idItem, String atributo)throws Exception {
		return getGerenciadorUsuarios().buscarDonoItem(idItem)
				.getGerenciadorItens().getAtributoItem(idItem, atributo);
	}

	/**
	 * 
	 * @param idSessao
	 * @param nome
	 * @param descricao
	 * @param categoria
	 * @return
	 * @throws Exception
	 */
	public String cadastrarItem(String idSessao, String nome, String descricao, String categoria) throws Exception {
		Usuario usuario = getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		Item it = new Item(nome, descricao, categoria);
		
		return getGerenciadorUsuarios().cadastrarItem(usuario, it);
	}

	/**
	 * 
	 * @param login
	 * @param nome
	 * @param endereco
	 * @throws Exception
	 */
	public void criarUsuario(String login, String nome, String endereco)throws Exception {
		getGerenciadorUsuarios().criarUsuario(login, nome, endereco);
	}

	/**
	 * 
	 * @param login
	 * @return
	 * @throws Exception
	 */
	public String abrirSessao(String login) throws Exception {
		Usuario usr = getGerenciadorUsuarios().buscarUsuarioPorLogin(login);
		return getGerenciadorUsuarios().abrirSessao(usr);
	}

	/**
	 * 
	 * @param login
	 * @param atributo
	 * @return
	 * @throws Exception
	 */
	public String getAtributoUsuario(String login, String atributo) throws Exception {
		return getGerenciadorUsuarios().getAtributoUsuario(login, atributo);
	}
	
	/**
	 * 
	 * @param idSessao
	 * @return
	 * @throws Exception
	 */
	public String getAmigos(String idSessao) throws Exception {
		Usuario usuario = getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		
		return getGerenciadorUsuarios().getAmigos(usuario);
	}

	/**
	 * 
	 * @param idSessao
	 * @param login
	 * @return
	 * @throws Exception
	 */
	public String getAmigos(String idSessao, String login) throws Exception {		
		getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		Usuario usuario2 = getGerenciadorUsuarios().buscarUsuarioPorLogin(login);
		
		return getGerenciadorUsuarios().getAmigos(usuario2);
	}

	/**
	 * 
	 * @param idSessao
	 * @return
	 * @throws Exception
	 */
	public String getItens(String idSessao) throws Exception {
		Usuario usuario = getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		
		return getGerenciadorUsuarios().getItens(usuario);

	}

	/**
	 * 
	 * @param idSessao
	 * @param login
	 * @return
	 * @throws Exception
	 */
	public String getItens(String idSessao, String login) throws Exception {
		Usuario usuario = getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		Usuario usuario2 = getGerenciadorUsuarios().buscarUsuarioPorLogin(login);
		
		return getGerenciadorUsuarios().getItens(usuario, usuario2);
	}
	
	/**
	 * 
	 * @param idSessao
	 * @param idItem
	 * @param dias
	 * @return
	 * @throws Exception
	 */
	public String requisitarEmprestimo(String idSessao, String idItem, int dias) throws Exception {
		Usuario usuario = getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		Item item = getGerenciadorUsuarios().buscarItemPorID(idItem);
		
		return getGerenciadorUsuarios().requisitarEmprestimo(usuario, item, dias);
	}
	
	/**
	 * 
	 * @param idSessao
	 * @param tipo
	 * @return
	 * @throws Exception
	 */
	public String getEmprestimos(String idSessao, String tipo)throws Exception{
		return this.getGerenciadorUsuarios().getEmprestimos(idSessao, tipo);
	}
	
	/**
	 * 
	 * @param idSessao
	 * @param idRequisicaoEmprestimo
	 * @return
	 * @throws Exception
	 */
	public String aprovarEmprestimo(String idSessao, String idRequisicaoEmprestimo)throws Exception{
		Usuario usuario = null;
		Usuario usuario2 = null;
		boolean usuariosSaoAmigos = true;
		
		boolean ehDonoDoItem = true;
		try {
			usuario = this.getGerenciadorUsuarios().buscarUsuarioBeneficiado(idRequisicaoEmprestimo);
			usuario2 = this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);

			usuariosSaoAmigos = usuario.getGerenciadorAmizades().ehMeuAmigo(usuario2);
			ehDonoDoItem = getGerenciadorUsuarios().buscarUsuarioEmprestador(idSessao).equals(getGerenciadorUsuarios().buscarUsuarioPorID(idSessao));

		} catch (Exception e){
			
			
		}
		return this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).getGerenciadorItens().aprovarRequisicaoEmprestimo(ehDonoDoItem,usuariosSaoAmigos,this.getGerenciadorUsuarios().requisicaoEmprestimoExiste(idRequisicaoEmprestimo),idRequisicaoEmprestimo);
	}
	
	/**
	 * 
	 * @param idSessao
	 * @param idEmprestimo
	 * @throws Exception
	 */
	public void devolverItem(String idSessao, String idEmprestimo) throws Exception{
		Usuario usuario = this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		Usuario usuario2 = this.getGerenciadorUsuarios().buscarUsuarioEmprestador2(idEmprestimo);
		Item item = this.getGerenciadorUsuarios().buscarItemIdEmprestimo(idEmprestimo);
		
		getGerenciadorUsuarios().devolverItem(usuario, usuario2, item);	
	}
		
	/**
	 * 
	 * @param idSessao
	 * @param idEmprestimo
	 * @throws Exception
	 */
	public void confirmarTerminoEmprestimo(String idSessao, String idEmprestimo) throws Exception{
		Usuario usuario2 = this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		this.getGerenciadorUsuarios().buscarUsuarioEmprestador2(idEmprestimo);
		Item item = this.getGerenciadorUsuarios().buscarItemIdEmprestimo(idEmprestimo);
		
		getGerenciadorUsuarios().confirmarTerminoEmprestimo(usuario2, item);
	}
	
	/**
	 * 
	 * @param idSessao
	 * @param destinatario
	 * @param assunto
	 * @param mensagem
	 * @return
	 * @throws Exception
	 */
	public String enviarMensagem (String idSessao, String destinatario, String assunto, String mensagem) throws Exception{
		this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		getGerenciadorUsuarios().buscarUsuarioPorDestinatario(destinatario);
		
		return getGerenciadorUsuarios().enviarMensagem(idSessao, destinatario, assunto, mensagem);
	}
	
	/**
	 * 
	 * @param idSessao
	 * @param destinatario
	 * @param assunto
	 * @param mensagem
	 * @param idRequisicaoEmprestimo
	 * @return
	 * @throws Exception
	 */
	public String enviarMensagem (String idSessao, String destinatario, String assunto, String mensagem, String idRequisicaoEmprestimo) throws Exception{

		return this.getGerenciadorUsuarios().enviarMensagem(idSessao, destinatario, assunto, mensagem, idRequisicaoEmprestimo);
		
	}
	
	/**
	 * 
	 * @param idSessao
	 * @param tipo
	 * @return
	 * @throws Exception
	 */
	public String lerTopicos (String idSessao, String tipo) throws Exception{
		
		return this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).getGerenciadorMensagens().lerTopicos(tipo);
	}
	
	/**
	 * 
	 * @param idSessao
	 * @param idTopico
	 * @return
	 * @throws Exception
	 */
	public String lerMensagens (String idSessao, String idTopico) throws Exception{
		boolean resp1 = false;
		boolean resp2 = false;
		
		if (!this.getGerenciadorUsuarios().msgExiste(idTopico)){
			resp2=true;
		}
		
		if (!this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).equals(getGerenciadorUsuarios().buscarDestinatario(idTopico)) && !this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).equals(getGerenciadorUsuarios().buscarRemetente(idTopico))){
			resp1 = true;
		}
		
		return this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).getGerenciadorMensagens().lerMensagens(resp1,resp2,idTopico);
	}
	
	/**
	 * 
	 * @param idSessao
	 * @param idEmprestimo
	 * @throws Exception
	 */
	public void requisitarDevolucao(String idSessao, String idEmprestimo) throws Exception{
		
		this.getGerenciadorUsuarios().requisitarDevolucao(idSessao, idEmprestimo);
		
	}
	
	/**
	 * 
	 * @param dias
	 */
	public void adicionarDias(int dias){
	
		this.getGerenciadorUsuarios().simularPassagemDoTempo(dias);
		
	}
	
	/**
	 * 
	 * @param idSessao
	 * @param idItem
	 * @throws Exception
	 */
	public void registrarInteresse(String idSessao, String idItem) throws Exception{
		Usuario usuario = this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		if (this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).equals(this.getGerenciadorUsuarios().buscarDonoItem(idItem))){
			throw new Exception ("O usuário não pode registrar interesse no próprio item");
		}
		Item item = this.getGerenciadorUsuarios().buscarDonoItem(idItem).getGerenciadorItens().buscarItemPorID(idItem);
		if (item.getEmprestimo() == null){
			throw new Exception("O usuário não tem permissão para registrar interesse neste item");
		}
		item.getEmprestimo().registrarInteresse(usuario);	
	}
	
	/**
	 * 
	 * @param idSessao
	 * @param chave
	 * @param atributo
	 * @param tipoOrdenacao
	 * @param criterioOrdenacao
	 * @return
	 * @throws Exception
	 */
	public String pesquisarItem (String idSessao, String chave, String atributo, String tipoOrdenacao, String criterioOrdenacao) throws Exception{
		return this.getGerenciadorUsuarios().pesquisarItem(this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao) ,chave, atributo, tipoOrdenacao, criterioOrdenacao);
	}
	 
	/**
	 * 
	 * @param idSessao
	 * @param loginAmigo
	 * @throws Exception
	 */
	public void desfazerAmizade (String idSessao, String loginAmigo) throws Exception{
		Usuario	usuario = this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		Usuario	usuario2 = this.getGerenciadorUsuarios().buscarUsuarioPorLogin(loginAmigo);
		this.getGerenciadorUsuarios().desfazerAmizade(usuario, usuario2);
	} 
	
	/**
	 * 
	 * @param idSessao
	 * @param idItem
	 * @throws Exception
	 */
	public void apagarItem (String idSessao, String idItem) throws Exception{
		Usuario usuario = this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		Item item = this.getGerenciadorUsuarios().buscarDonoItem(idItem).getGerenciadorItens().buscarItemPorID(idItem);
		usuario.getGerenciadorItens().apagarItem(item);
	}
	
	/**
	 * 
	 * @param idSessao
	 * @param categoria
	 * @return
	 * @throws Exception
	 */
	public String getRanking(String idSessao, String categoria) throws Exception{
		return this.getGerenciadorUsuarios().getRanking(idSessao, categoria);
	}
	
	
}
