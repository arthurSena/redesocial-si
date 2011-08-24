package Classes;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe Principal do Sistema
 * @author ARTHUR SENA, IGOR GOMES, RENAN PINTO, RODOLFO DE LIMA
 * @version 1.0
 */


public class MainTeste {
	
	private static RedeSocial rede = new RedeSocial();
	
	public static void main(String[] args) {
		
		while (true){
			imprimirMenuPrincipal();
			String entrada = recebeEntrada();
			
			if (entrada.equals("1")){
				fazerLogin();
			}
			else if(entrada.equals("2")){
				cadastrarUsuario();
			}
			else if(entrada.equals("3")){
				System.out.println("Sistema finalizado com Sucesso!");
				break;
			}
			else{
				System.out.println("Opcao Invalida!");
			}
		}
		
	}
	//---------------------#REALIZA LOGIN DO USUARIO#--------------------------
	private static void fazerLogin(){
		if (rede.getListaDeUsuarios().isEmpty()){
			System.out.println("Nao ha nenhum usuario cadastrado!");
		}
		else{
			System.out.print("Digite seu Login: ");
			String login = recebeEntrada();
			System.out.print("Digite sua Senha: ");
			String senha = recebeEntrada();
			
			Usuario usr = localizarUsuario(login, senha);
			if (usr == null){
				System.out.println("Usuario Nao Encontrado");
			}
			else{
				menuDoUsuario(usr);
			}
		}
	}
	//---------------------------------------------------------------------------
	
	
	private static void menuDoUsuario(Usuario usr){
		
		System.out.println("\nMostra perfil de usuario!!\n");
		System.out.println("Olá sr(a): " + usr.getNome() + "\n"); 
		
		while (true){
			opcoesQueOUsuarioPodeFazer(usr);
			String entrada = recebeEntrada();
			
			if (entrada.equals("1")){
				cadastrarItem(usr);
			}
			else if(entrada.equals("2")){
				localizarUsuario();
			}
			else if(entrada.equals("3")){
				System.out.println("Usuario saiu com sucesso!");
				break;
			}
			else{
				System.out.println("Opcao Invalida!");
			}
		}
	}
	
	//---------------------------#LOCALIZA UM USUARIO#-------------------------------------
	private static void localizarUsuario(){
		System.out.println("Voce deseja localizar Usuarios atraves de: \n"+
				            "1)Nome\n2)Endereco");
		
		String opcao = recebeEntrada();
		
		if (opcao.equals("1")){
			System.out.println("Digite o nome: ");
			String nome = recebeEntrada();
			try{
				ArrayList<Usuario> listaDeUsuario = rede.buscarUsuarioPorNome(nome);
				System.out.println(listaDeUsuario.size() + "Usuarios tem o nome de "+nome);
				
				//TODO temos que imprimir o perfil de cada usuario com aquele nome
			}
			
			catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		else if(opcao.equals("2")){
			String estado, cidade, rua, bairro, numero, cep;
			System.out.println("\n----------------ENDERECO------------");
			
			System.out.print("Estado: ");
			estado = recebeEntrada();
			
			System.out.print("Cidade: ");
			cidade = recebeEntrada();
			
			System.out.print("Bairro: ");
			bairro = recebeEntrada();
			
			System.out.print("Rua: ");
			rua = recebeEntrada();
			
			System.out.print("Numero: ");
			numero = recebeEntrada();
			
			System.out.print("Cep: ");
			cep = recebeEntrada();
			
			try{
				Endereco endereco = new Endereco(estado, cidade, rua, bairro, numero, cep);
				ArrayList<Usuario> listaDeUsuarios = rede.buscarUsuarioPorEndereco(endereco);
				System.out.println(listaDeUsuarios.size()+" Usuarios satisfazem esse endereco");
				
				//TODO temos que imprimir o perfil de cada usuario
			}
			
			catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		else{
			System.out.println("\nOpcao Invalida");
		}
	}
	
	
	//-----------------------#IMPRIMI MENU PRINCIPAL#----------------------------
	
	private static void imprimirMenuPrincipal(){
		System.out.println("#---------BEM VINDO A NOSSA REDE SOCIAL--------#\n" +
		           " O que voce deseja fazer?                     \n" +
		           " 1) Fazer Login\n 2) Quero Me Cadastrar\n 3) Sair"   );
	}
	
	//---------------------------------------------------------------------------
	
	//-------------------------#RECEBE UMA ENTRADA VIA TECLADO#------------------
	
	private static String recebeEntrada(){
		Scanner sc = new Scanner(System.in);
		String entrada = sc.nextLine();
		return entrada;
	}
	//---------------------------------------------------------------------------
	
	//-------------#LOCALIZA UM USUARIO DA REDE ATRAVES DA SENHA E LOGIN#-------------------
	private static Usuario localizarUsuario(String login, String senha){
		
		Usuario usr = null;
		
		for(Usuario usuarios: rede.getListaDeUsuarios()){
			if (usuarios.getLogin().equals(login) && usuarios.getSenha().equals(senha)){
				usr= usuarios;
			}
		}return usr;
		
	}
	//-----------------------------------------------------------------------------
	
	//-----------------------#CADASTRAR UM USUARIO NO SISTEMA#----------------------
	
	private static void cadastrarUsuario(){
		String nomeCompleto,senha ,login;
		String estado, cidade, rua, bairro, numero, cep;
		Endereco endereco = null;		
		
		System.out.println("----------#BEM-VINDO AO SISTEMA DE CADASTRO#----------\n"+
				            "AVISO: Nao se preocupe, seus dados pessoais sao sigilosos e ficarao guardados conosco!");
		
		System.out.print("\nNome completo: ");
		nomeCompleto = recebeEntrada();
		
		System.out.println("Seu Login: ");
		login = recebeEntrada();
		
		System.out.println("Sua Senha: ");
		senha= recebeEntrada();
		
		System.out.println("\n----------------ENDERECO------------");
		
		System.out.print("Estado: ");
		estado = recebeEntrada();
		
		System.out.print("Cidade: ");
		cidade = recebeEntrada();
		
		System.out.print("Bairro: ");
		bairro = recebeEntrada();
		
		System.out.print("Rua: ");
		rua = recebeEntrada();
		
		System.out.print("Numero: ");
		numero = recebeEntrada();
		
		System.out.print("Cep: ");
		cep = recebeEntrada();
		
		try{
			endereco = new Endereco(estado, cidade, rua, bairro, numero, cep);
			Usuario novoUsuario = new Usuario(nomeCompleto, login, senha, endereco);
			rede.adicionaUsuario(novoUsuario);
			System.out.println("Cadastro Realizado com Sucesso!\n");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	//----------------------------CADASTRAR UM ITEM NO SISTEMA------------------------------------------------
	
	private static void cadastrarItem(Usuario usr){
		String nome, descricao, categoria;
				
		System.out.print("Digite o nome do item: ");
		nome = recebeEntrada();
		
		System.out.print("Digite a descicao do item: ");
		descricao = recebeEntrada();
		
		//TODO Acho que devemos criar um enum para a categoria
			
		while (true){
			imprimirMenuItens();
			String entrada = recebeEntrada();
			
			if (entrada.equals("1")){
				categoria = "Livro";
				break;
			}
			else if(entrada.equals("2")){
				categoria =  "Filme";
				break;
			}
			else if(entrada.equals("3")){
				categoria = "Jogos";
				break;
			}
			else{
				System.out.println("Opcao Invalida!");
			}
		}
		
		try {
			usr.getGerenciadorItens().adicionarItemParaEmprestar(new Item(nome, descricao, categoria));
			System.out.println(categoria + " cadastrado com sucesso\n");
		} catch (Exception e) {
			System.out.print(e.getLocalizedMessage());
			//TODO depois arrumar isso;
		}
		
	}
	
	
	//---------------------------------------------------------------------------
	
	//-----------------------#IMPRIMI MENU Itens#----------------------------
	
	private static void imprimirMenuItens(){
		System.out.print("Digite a categoria do item:\n " +
		           "1) Livro\n 2) Filme\n 3) Jogos"   );
	}
	
	//---------------------------------------------------------------------------
	
	
	//-----------------------#IMPRIMI MENU Usuario#----------------------------
	
	//TODO Depois mudar nome desse metodo ta ridiculo. =]
	private static void opcoesQueOUsuarioPodeFazer(Usuario usr){
		System.out.print("O que deseja fazer?\n " + 
        "1) Cadastrar novo item\n 2) Localizar Usuario\n 3) Sair\n"   );
		
	}
	
	
	
}
