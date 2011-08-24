package Classes;

import java.util.ArrayList;

/**
 * Classe que guarda os Itens de um usuario
 * @author ARTHUR SENA, IGOR GOMES, RENAN PINTO, RODOLFO DE LIMA 
 *
 */


public class GerenciadorItens {
	
	private ArrayList<Item> listaMeusItens;
	private ArrayList<Item> itensPraEmprestar;
	private ArrayList<Item> itensPraDevolver;
	
	/**
	 * Inicia os Atributos da Classe
	 */
	
	public GerenciadorItens(){
		listaMeusItens = new ArrayList<Item>();
		itensPraEmprestar = new ArrayList<Item>();
		itensPraDevolver = new ArrayList<Item>();
	}
	

	/**
	 * Recupera os Itens que o Usuario pode emprestas
	 * @return Uma Array contendo os itens que o Usuario pode emprestar
	 */
	
	public ArrayList<Item> getItensPraEmprestar() {
		return itensPraEmprestar;
	}
	
	/**
	 * Recupera todos os itens que o Usuario tem 
	 * @return
	 *         Uma Array com todos os Itens que o Usuario possui
	 */
	
	public ArrayList<Item> getMeusItens() {
		return listaMeusItens;
	}
	
	/**
	 * Recupera todos os Itens que o Usuario tera que devolver
	 * @return
	 *        Uma Array com todos os Itens que o Usuario tem que devolver
	 */
	
	public ArrayList<Item> getItensDevolver(){
		return itensPraDevolver;
	}
	
	/**
	 * Adiciona um Item na lista de Itens do Usuario
	 * @param it
	 *         Item a ser adicionado
	 * @throws Exception
	 *         Caso o Item seja igual a Null
	 */
	
	public void adicionarMeusItens(Item it )throws Exception{
		if (it == null){
			throw new Exception("Item nao pode ser igual a null");
		}
		listaMeusItens.add(it);
	}
	
	/**
	 * Adiciona um Item na lista de Itens Pra Devolver do Usuario
	 * @param it
	 *         Item a ser adicionado
	 * @throws Exception
	 *         Caso o Item seja igual a Null
	 */
	
	public void adicionarItemPraDevolver(Item it)throws Exception{
		if (it == null){
			throw new Exception("Item nao pode ser igual a null");
		}
		itensPraDevolver.add(it);
	}
	
	/**
	 * Adiciona um Item na lista de Itens Pra Emprestar do Usuario
	 * @param it
	 *         Item a ser adicionado
	 * @throws Exception
	 *         Caso o Item seja igual a Null
	 */
	
	public void adicionarItemParaEmprestar(Item it)throws Exception{
		if (it == null){
			throw new Exception("Item nao pode ser igual a null");
		}
		itensPraEmprestar.add(it);
	}
	
	/**
	 * Remove um item que voce pode emprestar
	 * @param it
	 *         Item a remover
	 * @throws Exception
	 *         Caso seja null ou nao esteja na lista de itens pra emprestar
	 */
	
	public void removerItemParaEmprestar(Item it)throws Exception{
		if (it == null){
			throw new Exception("Item nao pode ser igual a null");
		}
		else if(!itensPraEmprestar.contains(it)){
			throw new Exception("Item nao esta na lista de Itens que voce pode Emprestar");
		}
		itensPraEmprestar.remove(it);
	}
	
	/**
	 * Remove Item que 
	 * @param it
	 *         Item a Remover
	 * @throws Exception
	 *         Caso seja null ou nao esta na lista
	 */
	
	public void removerItemPraDevolver(Item it)throws Exception{
		if (it == null){
			throw new Exception("Item nao pode ser igual a null");
		}
		else if(!itensPraDevolver.contains(it)){
			throw new Exception("Item nao esta na lista de Itens que voce tem que devolver");
		}
		itensPraDevolver.remove(it);
	}
	
	/**
	 * Remove um Item do Usuario
	 * @param it
	 *         Item a ser removido
	 * @throws Exception
	 *        Caso seja null, ou nao esta na lista ou esta sendo 
	 *        ultilizado no momento
	 */
	public void removerItem(Item it) throws Exception{
		if (it == null){
			throw new Exception("Item nao pode ser igual a null");
		}
		else if(itensPraEmprestar.contains(it)){
			throw new Exception("Item nao pode ser removido, pois esta emprestado");
		}
		else if (!listaMeusItens.contains(it)){
			throw new Exception("Voce nao possui este item");
		}
		listaMeusItens.remove(it);
	}
	
}
