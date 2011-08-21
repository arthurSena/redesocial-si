package Classes;

import java.util.Scanner;

/**
 * 
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
			
			if (localizarUsuario(login, senha)==null){
				System.out.println("Usuario Nao Encontrado");
			}
			else{
				System.out.println("ENTRA NO MENU DO USUARIO!!!");
			}
		}
	}
	//---------------------------------------------------------------------------
	
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
	
	//----------------------------#LOCALIZA UM USUARIO DA REDE#-------------------
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
	//----------------------------------------------------------------------------------------

}
