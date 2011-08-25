package Classes;

/**
 * Esta Classe representa um Usuário da Rede Social
 * 
 * @author ARTHUR SENA, RODOLFO DE LIMA, RENAN PINTO, IGOR GOMES
 * @version 1.0
 * 
 */

public class Usuario {
	
	private String nome;
	private String login;
	private String senha;
	private Endereco end;
	
	private GerenciadorAmizades gerenciaAmizade;
	private GerenciadorMensagens gerenciaMensagens;
	private GerenciadorItens gerenciaItens;

	/**
	 * Inicia os Atributos da Classe
	 * @param nome 
	 *           Nome do Usuario
	 * @param login 
	 *           Login do Usuario
	 * @param senha 
	 *           Senha do Usuario
	 * @param end
	 *           Endereco do Usuario
	 * @throws Exception
	 *           Lanca excecao quando qualquer um dos parametros for Invalido
	 */
	
	public Usuario(String nome, String login, String senha, Endereco end)throws Exception{
		if (!stringValida(nome)){
			throw new Exception("Nome Invalido");
		}
		else if (!stringValida(login)){
			throw new Exception("Login Invalido");
		}
		else if (!stringValida(senha)){
			throw new Exception("Senha Invalida");
		}
		
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.end = end;
		gerenciaAmizade = new GerenciadorAmizades();
		gerenciaItens = new GerenciadorItens();
		gerenciaMensagens = new GerenciadorMensagens();
	}

	/**
	 * Recupera o Login
	 * @return Login do Usuario
	 */
	
	public String getLogin() {
		return login;
	}
	
	/**
	 * Recupera o Nome
	 * @return Nome do Usuario
	 */
	
	public String getNome() {
		return nome;
	}
	
	
    /**
     * Altera o Nome do Usuario
     * 
     * @param nome 
     *           Novo Nome do Usuario
     * @throws Exception 
     *           Caso o Nome seja Invalido
     */
	
	public void setNome(String nome) throws Exception {
		if (!stringValida(nome)){
			throw new Exception("Dado(s) Invalido(s)");
		}
		this.nome = nome;
	}

	/**
	 * Recupera a Senha
	 * @return Senha do Usuario
	 */
	public String getSenha() {
		return senha;
	}
    
	/**
	 * Altera a Senha do Usuario
	 * 
	 * @param senha 
	 *           Nova Senha do Usuario
	 * @throws Exception
	 *            Caso a Senha esteja invalida
	 */
	public void setSenha(String senha) throws Exception {
		if (!stringValida(senha)){
			throw new Exception("Dado(s) Invalido(s)");
		}
		this.senha = senha;
	}

	/**
	 * Recupera o Endereco do Usuario 
	 * @return Endereco do Usuario
	 */
	public Endereco getEnd() {
		return end;
	}
	
	/**
	 * Altera o Endereco do Usuario
	 * @param end 
	 *          Novo Endereco do Usuario
	 */
	
	public void setEnd(Endereco end) {
		this.end = end;
	}
	
	
	/**
	 * Recupera o Gerenciador de Amizades do Usuario
	 * @return 
	 *      O gerenciador de amizades do usuario
	 */
	
	public GerenciadorAmizades getGerenciadorAmizades(){
		return gerenciaAmizade;
	}
	
	/**
	 * Recupera o Gerenciador de itens do Usuario
	 * @return 
	 *      O gerenciador de itens do usuario
	 */
	
	public GerenciadorItens getGerenciadorItens(){
		return gerenciaItens;
	}
	
	/**
	 * Recupera o Gerenciador de Mensagens do Usuario
	 * @return 
	 *      O gerenciador de mensagens do usuario
	 */
	
	public GerenciadorMensagens getGerenciadorMensagens(){
		return gerenciaMensagens;
	}
	
	/**
	 * Recupera o Perfil do Usuario
	 * @return
	 *        Uma String que representa o perfil do Usuario
	 */
	
	public String perfilUsuario(){
		String amigosDoUsuario = "";
		String itensDoUsuario = "";
		
		if(!gerenciaAmizade.getListaDeAmigos().isEmpty()){
			for (Usuario usr : gerenciaAmizade.getListaDeAmigos()){
				amigosDoUsuario+= "Nome: " + usr.getNome() + "\n";
			}
		}
		
		if(!gerenciaItens.getMeusItens().isEmpty()){
			for (Item it : gerenciaItens.getMeusItens()){
				itensDoUsuario+= "Nome do Item: "+"it.getNome()" + "\n";
			}
		}

		return "\n-----PERFIL DO USUARIO-----\n" + "Nome do Usuario: " + getNome() + "\n" + getEnd().toString()+ "\n" + amigosDoUsuario + itensDoUsuario;
		
	}
	
	/**
	 * Compara Dois Usuarios
	 * @param usr
	 * @return True, Caso os dois Usuarios sejam Iguais
	 *         False, Caso contrario
	 */
	
	public boolean equals(Usuario usr){
		boolean resp = false;
		
		if (!(usr instanceof Usuario)){
           resp = false;
        }

		else if (usr.getLogin().equals(this.login) && usr.getSenha().equals(this.senha) && 
				usr.getNome().equals(this.nome) && usr.getEnd().equals(this.end)){
			resp = true;
		}
		
		return resp;
	}
	
	private boolean stringValida(String string){
        if (string==null || string.isEmpty()){
            return false;
        }
        return true;
    }
	


	

}
