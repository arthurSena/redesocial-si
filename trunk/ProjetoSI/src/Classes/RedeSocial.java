package Classes;


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
				.adicionarAmigo2(
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
		//Usuario usuario = getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		
		if (!stringValida(idSessao)) {
			throw new Exception("Sessão inválida");
		}
		
		try {
			this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
			
		} catch (Exception e){
			throw new Exception ("Sessão inexistente");
		}
		
		
		
		Usuario usuario2 = getGerenciadorUsuarios().buscarUsuarioPorLogin(login);
		
		if (usuario2.getGerenciadorAmizades().getListaDeAmigos().isEmpty()){
			return "O usuário não possui amigos";
		}

		else if (!getGerenciadorUsuarios().logiEhUsado(login)) {
			throw new Exception("Usuário inexistente");
		}

		else {	
				return usuario2.getGerenciadorAmizades().stringDeAmigos();

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
			
			try {
				String assunto = "Empréstimo do item " + this.getGerenciadorUsuarios().buscarDonoItem(idItem).getGerenciadorItens().buscarItemPorID(idItem).getNome() + " a " + this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).getNome();
				String mensagem = this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).getNome() + " solicitou o empréstimo do item " + this.getGerenciadorUsuarios().buscarDonoItem(idItem).getGerenciadorItens().buscarItemPorID(idItem).getNome();
				
				enviarMensagem(idSessao, getGerenciadorUsuarios().buscarDonoItem(idItem).getLogin(), assunto, mensagem, idRequisicaoEmprestim);
				
			} catch (Exception e){
				//System.out.println(e.getMessage());
			}
			return idRequisicaoEmprestim;
		}
		throw new Exception("O usuário não tem permissão para requisitar o empréstimo deste item");

	}
	
	public String getEmprestimos(String idSessao, String tipo)throws Exception{
		return this.getGerenciadorUsuarios().getEmprestimos(idSessao, tipo);
	}
	
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
			System.out.println("a");
			
			if (!usuario.getGerenciadorAmizades().ehMeuAmigo(usuario2)){
				throw new Exception ("Requisição de empréstimo inexistente");
				
			}
		} catch (Exception e){
//			if (e.getMessage().equals("Requisição de empréstimo inexistente")){
//				throw new Exception("Requisição de empréstimo inexistente");
//			}
		}
		
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
		
		if (!this.getGerenciadorUsuarios().buscarUsuarioEmprestador2(idEmprestimo).getGerenciadorItens().buscarItemIdEmprestimo(idEmprestimo).getEmprestimo().getBeneficiado().equals(getGerenciadorUsuarios().buscarUsuarioPorID(idSessao))){
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
		this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).getGerenciadorItens().confirmarTerminoEmprestimo(this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).getGerenciadorItens().buscarItemIdEmprestimo(idEmprestimo));
		
		if(this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).getGerenciadorItens().buscarItemIdEmprestimo(idEmprestimo).getEmprestimo().foiCompletado()){
			
			this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).getGerenciadorItens().addEmprestimoCompletado(this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).getGerenciadorItens().buscarItemIdEmprestimo(idEmprestimo).getEmprestimo());
		}
		
		String assunto = "O item " + this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).getGerenciadorItens().buscarItemIdEmprestimo(idEmprestimo).getNome() + " do usuário " + this.getGerenciadorUsuarios().buscarDonoItem(this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).getGerenciadorItens().buscarItemIdEmprestimo(idEmprestimo).getID()).getNome() + " está disponível";
		String mensagem = "Agora você pode requisitar o empréstimo do " + this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).getGerenciadorItens().buscarItemIdEmprestimo(idEmprestimo).getNome();
		 
		
		for (Usuario usuario : this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).getGerenciadorItens().buscarItemIdEmprestimo(idEmprestimo).getEmprestimo().getListaDeUsuariosInteressados() ){
			String destinatario = usuario.getLogin();
			enviarMensagem(idSessao, destinatario, assunto, mensagem);
		}
		
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
			throw new Exception ("Destinatário inválido");
		}
		
		try {
			this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		} catch (Exception e){
			throw new Exception ("Sessão inexistente");
		}
		
		try {
			getGerenciadorUsuarios().buscarUsuarioPorLogin(destinatario);
		} catch (Exception e){
			throw new Exception ("Destinatário inexistente");
		}
		
		if (!stringValida(assunto)){
			throw new Exception("Assunto inválido");
		}
		
		if (!stringValida(mensagem)){
			throw new Exception("Mensagem inválida");
		}
		return this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).getGerenciadorMensagens().enviarMensagem(getGerenciadorUsuarios().buscarUsuarioPorLogin(destinatario), assunto, mensagem);
	}
	
	public String enviarMensagem (String idSessao, String destinatario, String assunto, String mensagem, String idRequisicaoEmprestimo) throws Exception{
		if (!stringValida(idSessao)) {
			throw new Exception("Sessão inválida");
		}
		
		if (!stringValida(destinatario)){
			throw new Exception ("Destinatário inválido");
		}
		
		try {
			this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
			
		} catch (Exception e){
			throw new Exception ("Sessão inexistente");
		}
		
		try {
			getGerenciadorUsuarios().buscarUsuarioPorLogin(destinatario);
		} catch (Exception e){
			throw new Exception ("Destinatário inexistente");
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
		if (this.getGerenciadorUsuarios().buscarUsuarioEmprestador3(idRequisicaoEmprestimo) == null){
			throw new Exception("Requisição de empréstimo inexistente");
		}
		
		if (!this.getGerenciadorUsuarios().buscarUsuarioEmprestador3(idRequisicaoEmprestimo).equals(this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao)) && !this.getGerenciadorUsuarios().buscarUsuarioBeneficiado(idRequisicaoEmprestimo).equals(getGerenciadorUsuarios().buscarUsuarioPorID(idSessao))){
			throw new Exception("O usuário não participa deste empréstimo");
		}
		
		return this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).getGerenciadorMensagens().enviarMensagem(getGerenciadorUsuarios().buscarUsuarioPorLogin(destinatario), assunto, mensagem, idRequisicaoEmprestimo);
	}
	
	public String lerTopicos (String idSessao, String tipo) throws Exception{
		
		return this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).getGerenciadorMensagens().lerTopicos(tipo);
	}
	
	public String lerMensagens (String idSessao, String idTopico) throws Exception{
		boolean resp1 = false;
		boolean resp2 = false;
		
		if (!this.getGerenciadorUsuarios().msgExiste(idTopico)){
			resp2=true;
		}
		
		if (!this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).equals(getGerenciadorUsuarios().buscarDestinatario(idTopico)) && !this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).equals(getGerenciadorUsuarios().buscarRemetente(idTopico))){
			resp1 = true;
		}
		
		return this.getGerenciadorUsuarios().buscarUsuarioPorID(idTopico,idSessao).getGerenciadorMensagens().lerMensagens(resp1,resp2,idTopico);
	}
	
	public void requisitarDevolucao(String idSessao, String idEmprestimo) throws Exception{
		
		if (!stringValida(idSessao)) {
			throw new Exception("Sessão inválida");
		}
		
		try {
			this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
			
		} catch (Exception e){
			throw new Exception ("Sessão inexistente");
		}
		
		if (!stringValida(idEmprestimo)){
			throw new Exception("Identificador do empréstimo é inválido");
		}
		
		if (this.getGerenciadorUsuarios().buscarUsuarioEmprestador2(idEmprestimo) == null){
			throw new Exception("Empréstimo inexistente");
		}
		
		try {
			boolean testa = (!this.getGerenciadorUsuarios().buscarUsuarioEmprestador2(idEmprestimo).equals(this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao)) && !this.getGerenciadorUsuarios().buscarUsuarioBeneficiado(idEmprestimo).equals(getGerenciadorUsuarios().buscarUsuarioPorID(idSessao)));	
		}catch (Exception e){
			throw new Exception("O usuário não tem permissão para requisitar a devolução deste item");
		}	
		
		if (this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).getGerenciadorItens().buscarItemIdEmprestimo(idEmprestimo).getEmprestimo().isDevolucao()){
			throw new Exception ("Item já devolvido");
		}
		
		if (this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).getGerenciadorItens().buscarItemIdEmprestimo(idEmprestimo).getEmprestimo().isDevolvido()){
			throw new Exception ("Item já devolvido");
		}
		
		
		
		if (this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).getGerenciadorItens().buscarItemIdEmprestimo(idEmprestimo).getEmprestimo().isRequisitarDevolucao()){
			throw new Exception ("Devolução já requisitada");
		}
		
		this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).getGerenciadorItens().buscarItemIdEmprestimo(idEmprestimo).getEmprestimo().requisitarDevolucao();
		
		try {
			String assunto = "Empréstimo do item " + this.getGerenciadorUsuarios().buscarUsuarioEmprestador2(idEmprestimo).getGerenciadorItens().buscarItemIdEmprestimo(idEmprestimo).getNome() + " a " + this.getGerenciadorUsuarios().buscarUsuarioEmprestador2(idEmprestimo).getGerenciadorItens().buscarItemIdEmprestimo(idEmprestimo).getEmprestimo().getBeneficiado().getNome();
			String mensagem = this.getGerenciadorUsuarios().buscarDonoItem(this.getGerenciadorUsuarios().buscarUsuarioEmprestador2(idEmprestimo).getGerenciadorItens().buscarItemIdEmprestimo(idEmprestimo).getID()).getNome() + " solicitou a devolução do item " + this.getGerenciadorUsuarios().buscarUsuarioEmprestador2(idEmprestimo).getGerenciadorItens().buscarItemIdEmprestimo(idEmprestimo).getNome();
			String destinatario = this.getGerenciadorUsuarios().buscarUsuarioEmprestador2(idEmprestimo).getGerenciadorItens().buscarItemIdEmprestimo(idEmprestimo).getEmprestimo().getBeneficiado().getLogin();
			
			enviarMensagem(idSessao, destinatario, assunto, mensagem, this.getGerenciadorUsuarios().buscarUsuarioEmprestador2(idEmprestimo).getGerenciadorItens().buscarItemIdEmprestimo(idEmprestimo).getEmprestimo().getIDRequisicao());
		} catch (Exception e){
			System.out.println(e.getLocalizedMessage());
		}
			}
		
	public void adicionarDias(int dias){
	
		this.getGerenciadorUsuarios().simularPassagemDoTempo(dias);
		
	}
	
	public void registrarInteresse(String idSessao, String idItem) throws Exception{
		if (!stringValida(idSessao)) {
			throw new Exception("Sessão inválida");
		}
		
		try {
			this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
			
		} catch (Exception e){
			throw new Exception ("Sessão inexistente");
		}
		
		if (this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).equals(this.getGerenciadorUsuarios().buscarDonoItem(idItem))){
			throw new Exception ("O usuário não pode registrar interesse no próprio item");
		}
			
		Item item = this.getGerenciadorUsuarios().buscarDonoItem(idItem).getGerenciadorItens().buscarItemPorID(idItem);
		Usuario usuario = this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		
		if (item.getEmprestimo() == null){
			throw new Exception("O usuário não tem permissão para registrar interesse neste item");
		}
		
		item.getEmprestimo().registrarInteresse(usuario);
		
	}
	
	
	public String pesquisarItem (String idSessao, String chave, String atributo, String tipoOrdenacao, String criterioOrdenacao) throws Exception{
		if (!stringValida(idSessao)) {
			throw new Exception("Sessão inválida");
		}
		
		try {
			this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
			
		} catch (Exception e){
			throw new Exception ("Sessão inexistente");
		}
		
		if (!stringValida(chave)) {
			throw new Exception("Chave inválida");
		}
		
		if (!stringValida(atributo)) {
			throw new Exception("Atributo inválido");
		}
		
		if (!atributoValido(atributo)) {
			throw new Exception("Atributo inexistente");
		}
		
		if (!stringValida(tipoOrdenacao)) {
			throw new Exception("Tipo inválido de ordenação");
		}
		
		if (!tipoOrdenacaoValido(tipoOrdenacao)) {
			throw new Exception("Tipo de ordenação inexistente");
		}
		
		
		if (!stringValida(criterioOrdenacao)) {
			throw new Exception("Critério inválido de ordenação");
		}
		
		if (!criterioOrdenacaoValido(criterioOrdenacao)) {
			throw new Exception("Critério de ordenação inexistente");
		}
		
		String resp = "";
		resp = this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).pesquisarItem(chave, atributo, tipoOrdenacao, criterioOrdenacao);
		
		if (resp.equals("")){
			resp = "Nenhum item encontrado";
		}
		
		return resp;
	}
	 
	
	private boolean atributoValido(String atributo){
		
		if (atributo.equals("nome") || atributo.equals("descricao") || atributo.equals("categoria")){
			return true;
		}
		
		return false;
	}
	
	private boolean tipoOrdenacaoValido(String tipoOrdenacao){
		
		if (tipoOrdenacao.equals("crescente") || tipoOrdenacao.equals("decrescente")){
			return true;
		}
		
		return false;
	}
	
	private boolean criterioOrdenacaoValido(String criterioOrdenacao){
		
		if (criterioOrdenacao.equals("dataCriacao") || criterioOrdenacao.equals("reputacao")){
			return true;
		}
		return false;
	}
	
	public void desfazerAmizade (String idSessao, String loginAmigo) throws Exception{
		
		
		if (!stringValida(idSessao)) {
			throw new Exception("Sessão inválida");
		}
		
		try {
			this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
			
		} catch (Exception e){
			throw new Exception ("Sessão inexistente");
		}
		
		if (!stringValida(loginAmigo)) {
			throw new Exception("Login inválido");
		}
		
		if (!getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).getGerenciadorAmizades().ehMeuAmigo(getGerenciadorUsuarios().buscarUsuarioPorLogin(loginAmigo))) {
			throw new Exception("Amizade inexistente");
		}
		
		Usuario usuario =  null;
		Usuario usuario2 = null;
		
		
		try{
			usuario = this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
			usuario2 = this.getGerenciadorUsuarios().buscarUsuarioPorLogin(loginAmigo);
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		
		
		this.getGerenciadorUsuarios().desfazerAmizade(usuario, usuario2);
		
	} 
	
	public void apagarItem (String idSessao, String idItem) throws Exception{
		if (!stringValida(idSessao)) {
			throw new Exception("Sessão inválida");
		}
		
		try {
			this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
			
		} catch (Exception e){
			throw new Exception ("Sessão inexistente");
		}
		
		if (!stringValida(idItem)) {
			throw new Exception("Identificador do item é inválido");
		}
		
		
		
//		if (this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).getGerenciadorItens().buscarItemPorID(idItem) == null){
//			throw new Exception("Item inexistente");
//		}
		
		
		
		Usuario usuario = this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		Item item = this.getGerenciadorUsuarios().buscarDonoItem(idItem).getGerenciadorItens().buscarItemPorID(idItem);
		
		if (!usuario.getGerenciadorItens().getListaMeusItens().contains(item)){
			throw new Exception("O usuário não tem permissão para apagar este item");
		}
		
		usuario.getGerenciadorItens().apagarItem(item);

	}
	
	
	public String getRanking(String idSessao, String categoria) throws Exception{
		return this.getGerenciadorUsuarios().getRanking(idSessao, categoria);
	}
	
	
}
