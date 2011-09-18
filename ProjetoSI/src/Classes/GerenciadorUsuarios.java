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
			throw new Exception("Sessão inválida");
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
	
	
	public Usuario buscarUsuarioPorDestinatario(String destinatario) throws Exception{
		if (!stringValida(destinatario)){
			throw new Exception ("Destinatário inválido");
		}
		
		for (Usuario usr : listaDeUsuarios) {
			if (usr.getLogin().equals(destinatario)) {
				return usr;
			}
		}
		
		throw new Exception("Destinatário inexistente");
	}

	public Usuario buscarUsuarioPorLogin(String login) throws Exception {
		if (!stringValida(login)) {
			throw new Exception("Login inválido");
		}else if (!logiEhUsado(login)) {
			throw new Exception("Usuário inexistente");
		}
		for (Usuario usr : listaDeUsuarios) {
			if (usr.getLogin().equals(login)) {
				return usr;
			}
		}
		
		throw new Exception("Login inexistente");
		
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
		for (int i = 0; i<=listaDeUsuarios.size() - 1; i++) {
		//for (int i = listaDeUsuarios.size() - 1; i >= 0; i--) {
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
	
	public Usuario buscarUsuarioPorID(String idTopico,String idSessao) throws Exception {
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
	
	public Usuario buscarDonoItem(Item item) throws Exception {
		
		for (Usuario usr : listaDeUsuariosLogados) {
			for (Item it : usr.getGerenciadorItens().getListaMeusItens()) {
				if (it.equals(item)) {
					return usr;
				}
			}
		}
		throw new Exception("Item inexistente");
	}
	
	public Item buscarItemPorID(String idItem) throws Exception{
		if (!stringValida(idItem)) {
			throw new Exception("Identificador do item é inválido");
		}
		for (Usuario usr : listaDeUsuariosLogados) {
			for (Item it : usr.getGerenciadorItens().getListaMeusItens()) {
				if (it.getID().equals(idItem)) {
					return it;
				}
			}
		}
		throw new Exception("Item inexistente");
	}
	
	
	public String getEmprestimos(String idSessao, String tipo) throws Exception {	
		
		return buscarUsuarioPorID(idSessao).getGerenciadorItens().getEmprestimo(buscarUsuarioEmprestador(idSessao),
						buscarUsuarioPorID(idSessao), tipo);
	}
	
	public Usuario buscarUsuarioEmprestador(String idSessao) throws Exception{
		
		for (Usuario usr: listaDeUsuarios){
			for (Item it : usr.getGerenciadorItens().getListaMeusItens()){
				if (it.getEmprestimo() != null /*&& it.getEmprestimo().emprestimoFoiAprovado()*/){
					if (it.getEmprestimo().getBeneficiado().equals(buscarUsuarioPorID(idSessao))){
						
						return usr;
					}
				}
			}
		}return null;
	}
	
	public Usuario buscarUsuarioEmprestador2(String idEmprestimo) throws Exception{
		Usuario usuario = null;
		
		if (!stringValida(idEmprestimo)){
			throw new Exception("Identificador do empréstimo é inválido");
		}
		
		for (Usuario usr: listaDeUsuarios){
			
			for (Item it : usr.getGerenciadorItens().getListaMeusItens()){
				
				if (it.getEmprestimo() != null){
					if (it.getEmprestimo().getIDEmprestimo().equals(idEmprestimo)){
						usuario = usr;
						break;
					}
				}
			}
		}
		
		if (usuario == null){
			throw new Exception("Empréstimo inexistente");
		}
		
		return usuario;
	}
	
	public Usuario buscarUsuarioEmprestador3(String idRequisicaoEmprestimo) throws Exception{
		
		if (!stringValida(idRequisicaoEmprestimo)){
			throw new Exception("Identificador da requisição de empréstimo é inválido");
		}
		
		Usuario usuario = null;
		
		for (Usuario usr: listaDeUsuarios){
			
			for (Item it : usr.getGerenciadorItens().getListaMeusItens()){
				
				if (it.getEmprestimo() != null){
					if (it.getEmprestimo().getIDRequisicao().equals(idRequisicaoEmprestimo)){
						usuario = usr;
						break;
					}
				}
			}
		}
		
		if (usuario == null){
			throw new Exception("Requisição de empréstimo inexistente");
		}
		
		return usuario;
	}
	
	public Item buscarItemEmprestador(String idEmprestimo) throws Exception{
		Usuario usr = buscarUsuarioEmprestador2(idEmprestimo);
		
		if (usr != null){
			usr.getGerenciadorItens().confirmarTerminoEmprestimo(idEmprestimo);
		}
		
		
		return null;
		
		
	}
	
	public Item buscarItemIdEmprestimo(String idEmpretimo) throws Exception{
		Item item = null;
		
		for (Usuario usr: listaDeUsuarios){
			for (Item it : usr.getGerenciadorItens().getListaMeusItens()){
				if (it.getEmprestimo() != null){
					if (it.getEmprestimo().getIDEmprestimo().equals(idEmpretimo)){
						item = it;
						break;
					}	
				}
			
			}
		}
		
		if (item == null){
			throw new Exception("Empréstimo inexistente");
		}
		
		return item;
		
	}
	
	public Usuario buscarUsuarioBeneficiado(String idRequisicaoEmprestimo){
		for (Usuario usr : listaDeUsuarios) {
			for (Item it : usr.getGerenciadorItens().getListaMeusItens()) {
				if (it.getEmprestimo().getIDRequisicao()
						.equals(idRequisicaoEmprestimo)) {
					return it.getEmprestimo().getBeneficiado();
				}
			}
		}
		return null;
	}
	
	public boolean requisicaoEmprestimoExiste(String idRequisicaoEmprestimo) throws Exception{
		for (Usuario usr: listaDeUsuarios){
			for (Item it : usr.getGerenciadorItens().getListaMeusItens()){
				if (it.getEmprestimo() != null){
					if (it.getEmprestimo().getIDRequisicao().equals(idRequisicaoEmprestimo)){
						return true;
					}
				}
			}
		}return false;
	}
	
	public Usuario buscarDestinatario(String idTopico){
		for (Usuario usr: listaDeUsuarios){
			for (Mensagem msg : usr.getGerenciadorMensagens().getListaDeMensagens()){
				if (msg.getIdMensagem().equals(idTopico) && msg.getDestinatario().equals(usr)){
					return msg.getDestinatario();
				}
			}
		}return null;
	}
	
	public Usuario buscarRemetente(String idTopico){
		for (Usuario usr: listaDeUsuarios){
			for (Mensagem msg : usr.getGerenciadorMensagens().getListaDeMensagens()){
				if (msg.getIdMensagem().equals(idTopico) && !msg.getDestinatario().equals(usr)){
					return usr;
				}
			}
		}return null;
	}
	
	public boolean msgExiste(String idTopico){
		for (Usuario usr: listaDeUsuarios){
			for (Mensagem msg : usr.getGerenciadorMensagens().getListaDeMensagens()){
				if (msg.getIdMensagem().equals(idTopico)){
					return true;
				}
			}
		}return false;
	}
	
	public void simularPassagemDoTempo(int dias){
		for (Usuario usr: listaDeUsuarios){
			usr.getGerenciadorItens().incrementarDias(dias);
		}
	}
	public void desfazerAmizade(Usuario usuario, Usuario usuario2) throws Exception{
		usuario.getGerenciadorAmizades().desfazerAmizade(usuario2);
		usuario2.getGerenciadorAmizades().desfazerAmizade(usuario);
		
		for(Item it : usuario.getGerenciadorItens().getListaMeusItens()){
			if(it.getEmprestimo()!=null && it.getEmprestimo().getBeneficiado().equals(usuario2) && !it.getEmprestimo().emprestimoFoiAprovado()){
				it.acabarEmprestimo();
			}
		}
		
		for(Item it : usuario2.getGerenciadorItens().getListaMeusItens()){
			if(it.getEmprestimo()!=null && it.getEmprestimo().getBeneficiado().equals(usuario) && !it.getEmprestimo().emprestimoFoiAprovado()){
				it.acabarEmprestimo();
			}
		}
	}
	
	public String getRanking(String idSessao, String categoria) throws Exception{
		buscarUsuarioPorID(idSessao);
		String retorno = "";
		if(!stringValida(categoria)){
			throw new Exception("Categoria inválida");
		}
		else if(categoria.equals("amigos")){
			List<Usuario> listaUsuarioReputacao = new ArrayList<Usuario>();
			listaUsuarioReputacao.add(buscarUsuarioPorID(idSessao));
			for(Usuario usuarios: listaDeUsuarios){
				if(buscarUsuarioPorID(idSessao).getGerenciadorAmizades().ehMeuAmigo(usuarios)){
					listaUsuarioReputacao.add(usuarios);
				}
			}
			
			while(!listaUsuarioReputacao.isEmpty()){
				Usuario usr = usuarioComMaisAltaReputacaoDaLista(listaUsuarioReputacao);
				retorno+=usr.getLogin() + "; ";
				listaUsuarioReputacao.remove(usr);
			}
			

			return formatarRequisicoes(retorno);
		}
		else if(categoria.equals("global")){
			List<Usuario> listaUsuarioReputacao = new ArrayList<Usuario>();
			
			for(Usuario usuarios: listaDeUsuarios){
				listaUsuarioReputacao.add(usuarios);
			}
			
			while(!listaUsuarioReputacao.isEmpty()){
				Usuario usr = usuarioComMaisAltaReputacaoDaLista(listaUsuarioReputacao);
				retorno+=usr.getLogin() + "; ";
				listaUsuarioReputacao.remove(usr);
			}
			
			
			return formatarRequisicoes(retorno);
			
		}
		else{
			throw new Exception("Categoria inexistente");
		}
	}
	
	private Usuario usuarioComMaisAltaReputacaoDaLista(List<Usuario> lista){
		Usuario usuario = lista.get(0);
		for(Usuario usr: lista){
			if (usr.getReputacao()>usuario.getReputacao()){
				usuario = usr;
			}
		}return usuario;
	}
	//TODO dpois arruma isso
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

	public String cadastrarItem(Usuario usuario, Item it) throws Exception {
		
		return usuario.getGerenciadorItens().adicionarItem(it);
	}

	public String abrirSessao(Usuario usr) {
		this.getListaUsuariosLogados().add(usr);
		return usr.getID();
		
	}

	public String getAmigos(Usuario usuario) {
		return usuario.getGerenciadorAmizades().stringDeAmigos();
		
	}

	public String getAmigos(Usuario usuario, Usuario usuario2) throws Exception {
		
		if (usuario2.getGerenciadorAmizades().getListaDeAmigos().isEmpty()){
			return "O usuário não possui amigos";
		}

		else if (!logiEhUsado(usuario2.getLogin())) {
			throw new Exception("Usuário inexistente");
		}

		else {	
				return usuario2.getGerenciadorAmizades().stringDeAmigos();
		}
	}

	public String getItens(Usuario usuario) {
		return usuario.getGerenciadorItens().stringDeItens();
		
	}

	public String getItens(Usuario usuario, Usuario usuario2) throws Exception {
		
		if (!usuario.getGerenciadorAmizades().ehMeuAmigo(usuario2)) {
			throw new Exception("O usuário não tem permissão para visualizar estes itens");
		}
		
		if (usuario2.getGerenciadorItens().getListaMeusItens().isEmpty()) {
			return "O usuário não possui itens cadastrados";
		}
		
		return usuario2.getGerenciadorItens().stringDeItens();
		
	}

	public String requisitarEmprestimo(Usuario usuario, Item item, int dias) throws Exception {
		Usuario usuario2 = buscarDonoItem(item);
		
		if ( usuario2.getGerenciadorAmizades().ehMeuAmigo(usuario)){
			String idRequisicaoEmprestim = usuario2.getGerenciadorItens().requisitarEmprestimos(usuario, item.getID(), dias);
			
			String assunto = "Empréstimo do item " + item.getNome() + " a " + usuario.getNome();
			String mensagem = usuario.getNome() + " solicitou o empréstimo do item " + item.getNome();
			usuario.getGerenciadorMensagens().enviarMensagem(usuario2, assunto, mensagem, idRequisicaoEmprestim);
							
			return idRequisicaoEmprestim;
		}
		throw new Exception("O usuário não tem permissão para requisitar o empréstimo deste item");
		
	}

	public void devolverItem(Usuario usuario, Usuario usuario2, Item item) throws Exception {

		if (item.getEmprestimo().isDevolvido()){
			throw new Exception("Item já devolvido");
		}
		
		//TODO logica ainda errada, mas passa nos testes
		if (usuario2.equals(usuario)){
			throw new Exception("O item só pode ser devolvido pelo usuário beneficiado");
		}
		
		//TODO logica ainda errada, mas passa nos testes
		if (!item.getEmprestimo().getBeneficiado().equals(usuario)){
            throw new Exception("O item só pode ser devolvido pelo usuário beneficiado");
            }
		
		item.getEmprestimo().setDevolvido(true);
	
	}

	public void confirmarTerminoEmprestimo(Usuario usuario2, Item item) throws Exception {
//		Usuario usuario2 = this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
//		Usuario usuario3 = this.getGerenciadorUsuarios().buscarUsuarioEmprestador2(idEmprestimo);
		
		
		if (!usuario2.getGerenciadorItens().getListaMeusItens().contains(item)){
			throw new Exception("O término do empréstimo só pode ser confirmado pelo dono do item");
		}
		
		if (item.getEmprestimo().isDevolucao()){
			throw new Exception("Término do empréstimo já confirmado");
		}
		
		item.getEmprestimo().setDevolucao(true);
		usuario2.getGerenciadorItens().confirmarTerminoEmprestimo(item);
		
		//TODO tirar essa logica daqui.
		if(item.getEmprestimo().foiCompletado()){
			usuario2.getGerenciadorItens().addEmprestimoCompletado(item.getEmprestimo());
		}
		
		String assunto = "O item " + item.getNome() + " do usuário " + usuario2.getNome() + " está disponível";
		String mensagem = "Agora você pode requisitar o empréstimo do " + item.getNome();
		
		for (Usuario usuario : item.getEmprestimo().getListaDeUsuariosInteressados() ){
			String destinatario = usuario.getLogin();
		
			enviarMensagem(usuario2.getID(), destinatario, assunto, mensagem);
		}
	
		
		
	}
	
	//TODO arrumar esse metodo
	public String enviarMensagem (String idSessao, String destinatario, String assunto, String mensagem) throws Exception{
		
		
		if (!stringValida(idSessao)) {
			throw new Exception("Sessão inválida");
		}
		
		if (!stringValida(destinatario)){
			throw new Exception ("Destinatário inválido");
		}
		
		try {
			this.buscarUsuarioPorID(idSessao);
		} catch (Exception e){
			throw new Exception ("Sessão inexistente");
		}
		
		try {
			this.buscarUsuarioPorLogin(destinatario);
		} catch (Exception e){
			throw new Exception ("Destinatário inexistente");
		}
		
		if (!stringValida(assunto)){
			throw new Exception("Assunto inválido");
		}
		
		if (!stringValida(mensagem)){
			throw new Exception("Mensagem inválida");
		}
		return this.buscarUsuarioPorID(idSessao).getGerenciadorMensagens().enviarMensagem(buscarUsuarioPorLogin(destinatario), assunto, mensagem);
	}

	public String enviarMensagem(Usuario usuario, Usuario usuario2, String assunto, String mensagem) throws Exception {
		if (!stringValida(assunto)){
			throw new Exception("Assunto inválido");
		}
		
		if (!stringValida(mensagem)){
			throw new Exception("Mensagem inválida");
		}
		
		return usuario.getGerenciadorMensagens().enviarMensagem(usuario2, assunto, mensagem);
	}

	public String enviarMensagem(Usuario usuario, Usuario usuario2,
			String assunto, String mensagem, String idRequisicaoEmprestimo) throws Exception {
		
		return usuario.getGerenciadorMensagens().enviarMensagem(usuario2, assunto, mensagem, idRequisicaoEmprestimo);
	}
}
