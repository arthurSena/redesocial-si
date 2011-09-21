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
				enviarMensagem();
			}
			else if(entrada.equals("6")){
				lerMensagem();
			}
			else if(entrada.equals("7")){
				visualizarPerfilDosAmigos();
			}
			else if(entrada.equals("8")){
				fazerEmprestimo();
			}
			else if(entrada.equals("9")){
				verRankingUsuarios();
			}
			else if(entrada.equals("10")){
				aprovarEmprestimo();
			}
			else if(entrada.equals("11")){
				aprovarAmizade();
			}
			else if(entrada.equals("15")){
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
	private static void visualizarPerfilDosAmigos() throws Exception{
		
		System.out.print("Digite o Login do Usuario que vc quer ver o perfil: ");
		String login = recebeEntrada();
		
		System.out.println("Amigos: " + rede.getAmigos(idSessao, login));
		System.out.println("Itens: " + rede.getItens(idSessao, login));

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
			String idItem = rede.cadastrarItem(idSessao, nome, descricao, categoria);
			System.out.println("ID do Item cadastrado: "+idItem);
			System.out.println("Item cadastrado com Sucesso!!!\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
//---------------------------------------------------------------------------

	private static void requisicaoDeAmizade() throws Exception{
		System.out.print("Digite o login de quem voce deseja a Amizade: ");
		String loginAmigo = recebeEntrada();
		rede.requisitarAmizade(idSessao, loginAmigo);
		System.out.println("Um Pedido de Amizade foi enviado para " + loginAmigo);
	}
	
	private static void enviarMensagem() throws Exception{
		String destinatario, assunto, mensagem;
		
		System.out.print("Digite o Login do Destinatario: ");
		destinatario =  recebeEntrada();
		
		System.out.print("Digite o Assunto da Mensagem: ");
		assunto = recebeEntrada();
		
		System.out.print("Digite a Mensagem: ");
		mensagem = recebeEntrada();
		
		String idMensagem = rede.enviarMensagem(idSessao, destinatario, assunto, mensagem);
		System.out.println("idMensagem: " + idMensagem);
	}
	
	private static void fazerEmprestimo() throws Exception{
		String idItem;
		int dias;
		System.out.println("Digite o ID do Item a ser Emprestado: ");
		idItem = recebeEntrada();
		
		System.out.println("Digita a Quantade de dias que vc qr passar com o Item: ");
		dias = Integer.parseInt(recebeEntrada());
		String idRequisicao = rede.requisitarEmprestimo(idSessao, idItem, dias);
		System.out.println("ID de Requisicao de Emprestimo: " + idRequisicao);
		System.out.println("O Emprestimo foi requisitado!!!\n");
	}
	
	private static void lerMensagem() throws Exception{
		System.out.print("Digite o ID da Mensagem que vc quer Ler: ");
		String idTopico = recebeEntrada();
		System.out.println(rede.lerMensagens(idSessao, idTopico));

		while(true){
			System.out.println("Voce deseja Responder Mensagem: \n1)SIM 2)NAO");
			String opcao = recebeEntrada();
			
			if(opcao.equals("1")){
				System.out.print("Digite a Resposta: ");
				String msg = recebeEntrada();
				
				System.out.print("Digite o Login do Destinatario: ");
				String destinatario = recebeEntrada();
				
				System.out.print("Digite o Assunto da Mensagem: ");
				String assunto = recebeEntrada();
				rede.enviarMensagem(msg, destinatario, assunto, msg);
			}
			else if(opcao.equals("2")){
				break;
			}
			else{
				System.out.println("Opcao Invalida!!!\n");
			}
		}
		
	}
	
	
	private static void aprovarEmprestimo() throws Exception{
		System.out.print("Digite o ID da requisicao de Emprestimo a ser Aprovada: ");
		String idRequisicaoEmprestimo = recebeEntrada();
		rede.aprovarEmprestimo(idSessao, idRequisicaoEmprestimo);
	}
	
	private static void verRankingUsuarios() throws Exception {
		
		System.out.println("Digite a Categoria: ");
		String categoria = recebeEntrada();
		System.out.println(rede.getRanking(idSessao, categoria));
		
		
	}
	
	
	private static void aprovarAmizade() throws Exception{
		System.out.println("Digite o Login do Usuario: ");
		String login = recebeEntrada();
		rede.aprovarAmizade(idSessao, login);
	}
	
//-----------------------#IMPRIMI MENU Usuario#----------------------------
	
	private static void opcoesDoUsuario(){
		System.out
				.print("O que deseja fazer?\n "
						+ "1) Cadastrar novo item\n 2) Localizar Usuario\n 3) Visualizar Meu Perfil\n 4) Adicionar Amigo\n 5)Enviar Mensagens\n 6)Ler Mensagens\n 7)Visualizar Perfil dos Amigos\n 8)Fazer Emprestimo\n 9)Ver Ranking dos Usuarios 10)Aprovar Emprestimo\n 11)Aprovar Amizade");
	}
	
	
}
