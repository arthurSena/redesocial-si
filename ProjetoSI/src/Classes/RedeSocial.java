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

	public String localizarUsuario(String idSessao, String chave, String atributo) throws Exception {
		return getGerenciadorUsuarios().localizarUsuario(idSessao, chave,
				atributo);
	}

	public String getAtributoItem(String idItem, String atributo)throws Exception {
		return getGerenciadorUsuarios().buscarDonoItem(idItem)
				.getGerenciadorItens().getAtributoItem(idItem, atributo);
	}

	public String cadastrarItem(String idSessao, String nome, String descricao, String categoria) throws Exception {
		Usuario usuario = getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		Item it = new Item(nome, descricao, categoria);
		
		return getGerenciadorUsuarios().cadastrarItem(usuario, it);
	}

	public void criarUsuario(String login, String nome, String endereco)throws Exception {
		getGerenciadorUsuarios().criarUsuario(login, nome, endereco);
	}

	public String abrirSessao(String login) throws Exception {
		Usuario usr = getGerenciadorUsuarios().buscarUsuarioPorLogin(login);
		return getGerenciadorUsuarios().abrirSessao(usr);
	}

	public String getAtributoUsuario(String login, String atributo) throws Exception {
		return getGerenciadorUsuarios().getAtributoUsuario(login, atributo);
	}
	
	public String getAmigos(String idSessao) throws Exception {
		Usuario usuario = getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		
		return getGerenciadorUsuarios().getAmigos(usuario);
	}

	public String getAmigos(String idSessao, String login) throws Exception {		
		Usuario usuario = getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		Usuario usuario2 = getGerenciadorUsuarios().buscarUsuarioPorLogin(login);
		
		return getGerenciadorUsuarios().getAmigos(usuario, usuario2);
	}

	public String getItens(String idSessao) throws Exception {
		Usuario usuario = getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		
		return getGerenciadorUsuarios().getItens(usuario);

	}

	public String getItens(String idSessao, String login) throws Exception {
		Usuario usuario = getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		Usuario usuario2 = getGerenciadorUsuarios().buscarUsuarioPorLogin(login);
		
		return getGerenciadorUsuarios().getItens(usuario, usuario2);
	}
	
	public String requisitarEmprestimo(String idSessao, String idItem, int dias) throws Exception {
		Usuario usuario = getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		Item item = getGerenciadorUsuarios().buscarItemPorID(idItem);
		
		return getGerenciadorUsuarios().requisitarEmprestimo(usuario, item, dias);
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

		} catch (Exception e){
			
			
		}
		return this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).getGerenciadorItens().aprovarRequisicaoEmprestimo(ehDonoDoItem,usuariosSaoAmigos,this.getGerenciadorUsuarios().requisicaoEmprestimoExiste(idRequisicaoEmprestimo),idRequisicaoEmprestimo);
	}
	
	
	public void devolverItem(String idSessao, String idEmprestimo) throws Exception{
		Usuario usuario = this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		Usuario usuario2 = this.getGerenciadorUsuarios().buscarUsuarioEmprestador2(idEmprestimo);
		Item item = this.getGerenciadorUsuarios().buscarItemIdEmprestimo(idEmprestimo);
		
		getGerenciadorUsuarios().devolverItem(usuario, usuario2, item);	
	}
		
	
	
	public void confirmarTerminoEmprestimo(String idSessao, String idEmprestimo) throws Exception{
		Usuario usuario2 = this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		this.getGerenciadorUsuarios().buscarUsuarioEmprestador2(idEmprestimo);
		Item item = this.getGerenciadorUsuarios().buscarItemIdEmprestimo(idEmprestimo);
		
		getGerenciadorUsuarios().confirmarTerminoEmprestimo(usuario2, item);
	}
	
	public String enviarMensagem (String idSessao, String destinatario, String assunto, String mensagem) throws Exception{
		Usuario usuario = this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		Usuario usuario2 = getGerenciadorUsuarios().buscarUsuarioPorDestinatario(destinatario);
		
		return getGerenciadorUsuarios().enviarMensagem(usuario, usuario2, assunto, mensagem);
	}
	
	//TODO aqui um metodo tem q usar o outro, tipo enviarMensagem tem q usar o enviarMensagem passando o idEmprestimo
	public String enviarMensagem (String idSessao, String destinatario, String assunto, String mensagem, String idRequisicaoEmprestimo) throws Exception{

		return this.getGerenciadorUsuarios().enviarMensagem(idSessao, destinatario, assunto, mensagem, idRequisicaoEmprestimo);
		
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
		
		this.getGerenciadorUsuarios().requisitarDevolucao(idSessao, idEmprestimo);
		
	}
		
	public void adicionarDias(int dias){
	
		this.getGerenciadorUsuarios().simularPassagemDoTempo(dias);
		
	}
	
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
	
	
	public String pesquisarItem (String idSessao, String chave, String atributo, String tipoOrdenacao, String criterioOrdenacao) throws Exception{
		return this.getGerenciadorUsuarios().pesquisarItem(this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao) ,chave, atributo, tipoOrdenacao, criterioOrdenacao);
	}
	 
	
	public void desfazerAmizade (String idSessao, String loginAmigo) throws Exception{
		Usuario	usuario = this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		Usuario	usuario2 = this.getGerenciadorUsuarios().buscarUsuarioPorLogin(loginAmigo);
		this.getGerenciadorUsuarios().desfazerAmizade(usuario, usuario2);
	} 
	
	public void apagarItem (String idSessao, String idItem) throws Exception{
		Usuario usuario = this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		Item item = this.getGerenciadorUsuarios().buscarDonoItem(idItem).getGerenciadorItens().buscarItemPorID(idItem);
		usuario.getGerenciadorItens().apagarItem(item);
	}
	
	
	public String getRanking(String idSessao, String categoria) throws Exception{
		return this.getGerenciadorUsuarios().getRanking(idSessao, categoria);
	}
	
	
}
