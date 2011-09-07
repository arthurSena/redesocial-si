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
	
	public String getAtributoItem(String idItem, String atributo)throws Exception{
		if(!stringValida(atributo)){
            throw new Exception("Atributo inválido");
		}
		else if(atributo.equals("nome")){
            return buscarItemPorID(idItem).getNome();
		}
		else if(atributo.equals("descricao")){
            return buscarItemPorID(idItem).getDescricao();
		}
		else if(atributo.equals("categoria")){
            return buscarItemPorID(idItem).getCategoria();
		}
		else{
            throw new Exception("Atributo inexistente");
        }
	}
	
	public String stringDeItens(){
		if (getListaMeusItens().isEmpty()){
			 return "O usuário não possui itens cadastrados";
		}
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
	
	public int getQuantidadeMeusItens(){
		return listaMeusItens.size();
	}
	
	public String getEmprestimo(Usuario usr1,Usuario usr,String tipo)throws Exception{
		if (!stringValida(tipo)){
			throw new Exception("Tipo inválido");
		}
		else if (tipo.equals("beneficiado")){
			String retorno = "";
			if (usr1==null){
				
				return ("Não há empréstimos deste tipo");
			}
			for (Item it: usr1.getGerenciadorItens().getListaMeusItens()){
				if (it.getEmprestimo()!=null && it.getEmprestimo().emprestimoFoiAprovado() && it.getEmprestimo().getBeneficiado().equals(usr)){
					retorno += usr1.getLogin() + "-" + it.getEmprestimo().getBeneficiado().getLogin() + ":" + it.getNome() + ":Andamento";
				}
			}
			if (retorno.isEmpty()){
				return ("Não há empréstimos deste tipo");
			}
			return retorno;
		}
		else if(tipo.equals("emprestador")){
			String retorno = "";
			for (Item it: listaMeusItens){
				if (it.getEmprestimo()!=null && it.getEmprestimo().emprestimoFoiAprovado()){
					retorno += usr.getLogin()+ "-" + it.getEmprestimo().getBeneficiado().getLogin() + ":" + it.getNome() + ":Andamento";
				}
			}
			if (retorno.isEmpty()){
				return ("Não há empréstimos deste tipo");
			}
			return retorno;
		}
		else if(tipo.equals("todos")){
			String retorno = "";
			
			int cont = 0;
			for (Item it: listaMeusItens){
				if (it.getEmprestimo()!=null && it.getEmprestimo().emprestimoFoiAprovado()){
					if (cont==0){
						retorno += usr.getLogin()+ "-" + it.getEmprestimo().getBeneficiado().getLogin() + ":" + it.getNome() + ":Andamento";
						cont++;
					}
					else{
						retorno += "; "+usr.getLogin()+ "-" + it.getEmprestimo().getBeneficiado().getLogin() + ":" + it.getNome() + ":Andamento";
						cont++;
					}
				}
			}
			if (usr1!=null){
				for (Item it: usr1.getGerenciadorItens().getListaMeusItens()){
					if (it.getEmprestimo()!=null && it.getEmprestimo().emprestimoFoiAprovado() && it.getEmprestimo().getBeneficiado().equals(usr)){
						if (cont==0){
							retorno += usr1.getLogin() + "-" + it.getEmprestimo().getBeneficiado().getLogin() + ":" + it.getNome() + ":Andamento";
							cont++;
						}
						else{
							retorno += "; "+usr1.getLogin() + "-" + it.getEmprestimo().getBeneficiado().getLogin() + ":" + it.getNome() + ":Andamento";
							cont++;
						}
					}
				}
			}
			if (retorno.isEmpty()){
				return ("Não há empréstimos deste tipo");
			}
			return retorno;
		}
		else{
			throw new Exception("Tipo inexistente");
		}
	}
	
	public String requisitarEmprestimos(Usuario beneficiado,String idItem, int dias) throws Exception{
		if (buscarItemPorID(idItem).getEmprestimo()!=null && buscarItemPorID(idItem).getEmprestimo().getBeneficiado().equals(beneficiado)){
			throw new Exception("Requisicao já solicitada");
		}
		return buscarItemPorID(idItem).criarRequisicaoEmprestimo(beneficiado, dias);
	}
	
	public String aprovarRequisicaoEmprestimo(String idRequisicaoEmprestimo) throws Exception{
		if (!stringValida(idRequisicaoEmprestimo)){
			throw new Exception("Identificador da requisição de empréstimo é inválido");
		}
		for (Item it: listaMeusItens){
			if (it.getEmprestimo()!=null && it.getEmprestimo().getIDRequisicao().equals(idRequisicaoEmprestimo)){
				return it.getEmprestimo().aprovarEmprestimo();
			}
		}
		throw new Exception("Requisição de empréstimo inexistente");
		//throw new Exception("O empréstimo só pode ser aprovado pelo dono do item");
	}
	
	private boolean stringValida(String string) {
		if (string == null || string.isEmpty()) {
			return false;
		}
		return true;
	}
	
	private Item buscarItemPorID(String id) {
		for (Item it : getListaMeusItens()) {
			if (it.getID().equals(id)) {
				return it;
			}
		}
		return null;
	}

}
