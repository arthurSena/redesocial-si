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
	private static String idSessao = null;
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
		System.out.print("Digite seu Login: ");
		String login = recebeEntrada();
		try{
			idSessao=rede.abrirSessao(login);
			menuDoUsuario(login);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		
	}
	//---------------------------------------------------------------------------
//	
//	
	private static void menuDoUsuario(String login) throws Exception{
		System.out.println("Ola sr(a): " + rede.getAtributoUsuario(login, "nome") + "\n"); 
		
		while (true){
			opcoesDoUsuario();
			String entrada = recebeEntrada();
			
			if (entrada.equals("1")){
				cadastrarItem();
			}
			else if(entrada.equals("2")){
				localizarUsuario();
			}
			else if(entrada.equals("3")){
				visualizarPerfil();
			}
			else if(entrada.equals("4")){
			      requisicaoDeAmizade();
			}
			else if(entrada.equals("5")){
				idSessao = null;
				System.out.println("Usuario saiu com sucesso!");
				break;
			}
			else{
				System.out.println("Opcao Invalida!");
			}
		}
	}
	
	
	private static void visualizarPerfil() throws Exception{
		String amigos = rede.getAmigos(idSessao);
		String itens = rede.getItens(idSessao);
		System.out.println("Amigos: " + amigos + "\n");
		System.out.println("Itens: " + itens + "\n");
	}
//	------------------------#IMPRIMI O PERFIL DOS AMIGOS DO USUARIO#------------------------
	private static void visualizarPerfilDosAmigos(Usuario usr){
		
		

	}
//	
//	
	//---------------------------#LOCALIZA UM USUARIO#-------------------------------------
	private static void localizarUsuario(){

		System.out.print("Digite a Palvra-Chave: ");
		String chave = recebeEntrada();
		
		System.out.print("Digite o Atributo: ");
		String atributo = recebeEntrada();
		
		try {
			String resposta = rede.localizarUsuario(idSessao, chave, atributo);
			System.out.println("Usuarios Localizados: " + resposta + "\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	//-----------------------#IMPRIMI MENU PRINCIPAL#----------------------------
	
	private static void imprimirMenuPrincipal(){
		System.out.println("#---------BEM VINDO A NOSSA REDE SOCIAL--------#\n" +
		           " O que voce deseja fazer?                     \n" +
		           " 1) Fazer Login\n 2) Quero Me Cadastrar\n 3) Sair"   );
	}
//	
//	//---------------------------------------------------------------------------
//	
	//-------------------------#RECEBE UMA ENTRADA VIA TECLADO#------------------
	
	private static String recebeEntrada(){
		Scanner sc = new Scanner(System.in);
		String entrada = sc.nextLine().trim();
		return entrada;
	}
	//---------------------------------------------------------------------------
	
	//-----------------------#CADASTRAR UM USUARIO NO SISTEMA#----------------------
	
	private static void cadastrarUsuario(){
		String nomeCompleto ,login, endereco;
		System.out.println("----------#BEM-VINDO AO SISTEMA DE CADASTRO#----------\n"+
				            "AVISO: Nao se preocupe, seus dados pessoais sao sigilosos e ficarao guardados conosco!");
		
		System.out.print("\nNome completo: ");
		nomeCompleto = recebeEntrada();
		
		System.out.print("Seu Login: ");
		login = recebeEntrada();
		
		System.out.print("Endereco: ");
		endereco = recebeEntrada();
		
		try{
			rede.criarUsuario(login, nomeCompleto, endereco);
			System.out.println("Usuario Cadastrado com Sucesso!!!");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	//----------------------------CADASTRAR UM ITEM NO SISTEMA------------------------------------------------
	
	private static void cadastrarItem(){
		String nome, descricao, categoria;
				
		System.out.print("Digite o nome do item: ");
		nome = recebeEntrada();
		
		System.out.print("Digite a descicao do item: ");
		descricao = recebeEntrada();
		
		System.out.print("Digite a categoria do item: ");
		categoria = recebeEntrada();
		
		try {
			rede.cadastrarItem(idSessao, nome, descricao, categoria);
			System.out.println("Item cadastrado com Sucesso!!!\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
//---------------------------------------------------------------------------

	private static void requisicaoDeAmizade() throws Exception{
		System.out.print("Digite o de quem voce deseja a Amizade: ");
		String loginAmigo = recebeEntrada();
		rede.requisitarAmizade(idSessao, loginAmigo);
	}
	
	
//-----------------------#IMPRIMI MENU Usuario#----------------------------
	
	private static void opcoesDoUsuario(){
		System.out.print("O que deseja fazer?\n " + 
        "1) Cadastrar novo item\n 2) Localizar Usuario\n 3) Visualizar Meu Perfil\n 4) Adicionar Amigo\n 5)Deslogar\n"   );
		
	}
}
