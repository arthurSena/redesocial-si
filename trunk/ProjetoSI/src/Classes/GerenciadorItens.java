package Classes;

import java.util.ArrayList;
import java.util.List;


public class GerenciadorItens {
	
	private List<Item> listaMeusItens;
	private List<Item> itensPraEmprestar;
	private List<Item> itensPraDevolver;
	
	public GerenciadorItens(){
		listaMeusItens = new ArrayList<Item>();
		itensPraDevolver = new ArrayList<Item>();
		itensPraEmprestar = new ArrayList<Item>();
	}
	
	public void adicionarItem(Item it)throws Exception{
		if (it==null){
			throw new Exception("Item nao pode ser igual a null");
		}
		listaMeusItens.add(it);
	}

	public List<Item> getListaMeusItens() {
		return listaMeusItens;
	}

	public void setListaMeusItens(List<Item> listaMeusItens) {
		this.listaMeusItens = listaMeusItens;
	}

	public List<Item> getItensPraEmprestar() {
		return itensPraEmprestar;
	}

	public void setItensPraEmprestar(List<Item> itensPraEmprestar) {
		this.itensPraEmprestar = itensPraEmprestar;
	}

	public List<Item> getItensPraDevolver() {
		return itensPraDevolver;
	}

	public void setItensPraDevolver(List<Item> itensPraDevolver) {
		this.itensPraDevolver = itensPraDevolver;
	}
	
	public String stringDeItens(){
		String resp = "";
		
		for (Item item : getListaMeusItens()){
			resp += item.getNome() + "; ";
		}
		
		
		return formatarRequisicoes(resp);
	}
	
	//TODO depois arrumar isso
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
	
	

}
