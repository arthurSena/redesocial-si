package Classes;

import java.util.ArrayList;
import java.util.List;


public class GerenciadorItens {
	
	private List<Item> listaMeusItens;
	private List<Item> itensPraEmprestar;
	private List<Item> itensPraDevolver;
	private List<Emprestimo> listaDeEmprestimosCompletados;
	
	public GerenciadorItens(){
		listaMeusItens = new ArrayList<Item>();
		itensPraDevolver = new ArrayList<Item>();
		itensPraEmprestar = new ArrayList<Item>();
		listaDeEmprestimosCompletados = new ArrayList<Emprestimo>();
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
	
	public int quantEmprestimosCompletados(){
		return this.listaDeEmprestimosCompletados.size();
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

			if (retorno.contains("; ")){
				retorno += requisicoes.split("; ")[i];
			}
			else{
				retorno += requisicoes.split("; ")[i] + "; ";

			}
			
			
		}return retorno;
	}
	
	public int getQuantidadeMeusItens(){
		return listaMeusItens.size();
	}
	
	public String getEmprestimo(Usuario usr1, Usuario usr, String tipo)throws Exception{
		if (!stringValida(tipo)){
			throw new Exception("Tipo inválido");
		}
		else if (tipo.equals("beneficiado")){
			String retorno = "";
			if (usr1==null){
				
				return ("Não há empréstimos deste tipo");
			}
			for (Item it: usr1.getGerenciadorItens().getListaMeusItens()){
				if (it.getEmprestimo()!=null && it.getEmprestimo().emprestimoFoiAprovado() && !it.getEmprestimo().isRequisitarDevolucao() && (!it.getEmprestimo().isDevolucao()) && it.getEmprestimo().getBeneficiado().equals(usr)){
					retorno += usr1.getLogin() + "-" + it.getEmprestimo().getBeneficiado().getLogin() + ":" + it.getNome() + ":Andamento; ";
				}
			}
			if (retorno.isEmpty()){
				return ("Não há empréstimos deste tipo");
			}
			return formatarRequisicoes(retorno);
		}
		else if(tipo.equals("emprestador")){
			String retorno = "";
			for (Item it: listaMeusItens){
				if (it.getEmprestimo()!=null && it.getEmprestimo().emprestimoFoiAprovado() && !it.getEmprestimo().isRequisitarDevolucao() && (!it.getEmprestimo().isDevolucao())){
					retorno += usr.getLogin()+ "-" + it.getEmprestimo().getBeneficiado().getLogin() + ":" + it.getNome() + ":Andamento; ";
				}
			}
			if (retorno.isEmpty()){
				return ("Não há empréstimos deste tipo");
			}
			return formatarRequisicoes(retorno);
		}
		else if(tipo.equals("todos")){
			String retorno = "";
			
			int cont = 0;
			for (Item it: listaMeusItens){
				if (it.getEmprestimo()!=null && it.getEmprestimo().isDevolucao() && !it.getEmprestimo().isRequisitarDevolucao()){
					retorno += usr.getLogin()+ "-" + it.getEmprestimo().getBeneficiado().getLogin() + ":" + it.getNome() + ":Completado; ";
					cont++;
				}
				
				else if (it.getEmprestimo()!=null && it.getEmprestimo().isDevolucao() && !it.getEmprestimo().tempoEmprestimoNaoExpiro() && (it.getEmprestimo().isDevolucao())){
					retorno += usr.getLogin()+ "-" + it.getEmprestimo().getBeneficiado().getLogin() + ":" + it.getNome() + ":Completado; ";
					cont++;
				}
				
				else if (it.getEmprestimo()!=null && it.getEmprestimo().isRequisitarDevolucao() && it.getEmprestimo().tempoEmprestimoNaoExpiro()){
					retorno += usr.getLogin()+ "-" + it.getEmprestimo().getBeneficiado().getLogin() + ":" + it.getNome() + ":Cancelado; ";
					cont++;
				}
				
				else if (it.getEmprestimo()!=null && it.getEmprestimo().emprestimoFoiAprovado() && !it.getEmprestimo().isRequisitarDevolucao() && (!it.getEmprestimo().isDevolucao())){
					if (cont==0){
						retorno += usr.getLogin()+ "-" + it.getEmprestimo().getBeneficiado().getLogin() + ":" + it.getNome() + ":Andamento; ";
						cont++;
					}
					else{
						retorno += "; "+usr.getLogin()+ "-" + it.getEmprestimo().getBeneficiado().getLogin() + ":" + it.getNome() + ":Andamento; ";
						cont++;
					}
				}
				
				else if (it.getEmprestimo()!=null && it.getEmprestimo().emprestimoFoiAprovado() && !it.getEmprestimo().tempoEmprestimoNaoExpiro() && (!it.getEmprestimo().isDevolucao())){
					if (cont==0){
						retorno += usr.getLogin()+ "-" + it.getEmprestimo().getBeneficiado().getLogin() + ":" + it.getNome() + ":Andamento; ";
						cont++;
					}
					else{
						retorno += "; "+usr.getLogin()+ "-" + it.getEmprestimo().getBeneficiado().getLogin() + ":" + it.getNome() + ":Andamento; ";
						cont++;
					}
				}
			}
			if (usr1!=null){
				for (Item it: usr1.getGerenciadorItens().getListaMeusItens()){
					if (it.getEmprestimo()!=null && it.getEmprestimo().isDevolucao() && !it.getEmprestimo().isRequisitarDevolucao()){
						retorno += usr1.getLogin()+ "-" + it.getEmprestimo().getBeneficiado().getLogin() + ":" + it.getNome() + ":Completado; ";
						cont++;
					}
					else if (it.getEmprestimo()!=null && it.getEmprestimo().isDevolucao() && !it.getEmprestimo().tempoEmprestimoNaoExpiro() && (it.getEmprestimo().isDevolucao())){
						retorno += usr1.getLogin()+ "-" + it.getEmprestimo().getBeneficiado().getLogin() + ":" + it.getNome() + ":Completado; ";
						cont++;
					}
					
					else if (it.getEmprestimo()!=null && it.getEmprestimo().isRequisitarDevolucao() && it.getEmprestimo().tempoEmprestimoNaoExpiro()){
						retorno += usr1.getLogin()+ "-" + it.getEmprestimo().getBeneficiado().getLogin() + ":" + it.getNome() + ":Cancelado; ";
						cont++;
					}
					
					else if (it.getEmprestimo()!=null && it.getEmprestimo().emprestimoFoiAprovado() /*&& !it.getEmprestimo().isRequisitarDevolucao()*/ && (!it.getEmprestimo().isDevolucao()) && it.getEmprestimo().getBeneficiado().equals(usr)){
						if (cont==0){
							retorno += usr1.getLogin() + "-" + it.getEmprestimo().getBeneficiado().getLogin() + ":" + it.getNome() + ":Andamento; ";
							cont++;
						}
						else{
							retorno += "; "+usr1.getLogin() + "-" + it.getEmprestimo().getBeneficiado().getLogin() + ":" + it.getNome() + ":Andamento; ";
							cont++;
						}
					}
				}
			}
			if (retorno.isEmpty()){
				return ("Não há empréstimos deste tipo");
			}
			return formatarRequisicoes(retorno);
		}
		else{
			throw new Exception("Tipo inexistente");
		}
	}
	
	public String requisitarEmprestimos(Usuario beneficiado,String idItem, int dias) throws Exception{
		if (buscarItemPorID(idItem).getEmprestimo()!=null && buscarItemPorID(idItem).getEmprestimo().getBeneficiado().equals(beneficiado) && buscarItemPorID(idItem).getEmprestimo().emprestimoFoiRequisitado()){
			throw new Exception("Requisição já solicitada");
		}

		return buscarItemPorID(idItem).criarRequisicaoEmprestimo(beneficiado, dias);
	}
	
	public String aprovarRequisicaoEmprestimo(boolean requisicaoExiste, String idRequisicaoEmprestimo) throws Exception{
		if (!stringValida(idRequisicaoEmprestimo)){
			throw new Exception("Identificador da requisição de empréstimo é inválido");
		}
		else if (!requisicaoExiste){
			throw new Exception("Requisição de empréstimo inexistente");
		}
		for (Item it: listaMeusItens){
			if (it.getEmprestimo()!=null && it.getEmprestimo().getIDRequisicao().equals(idRequisicaoEmprestimo) && !it.getEmprestimo().emprestimoFoiAprovado()){
				return it.getEmprestimo().aprovarEmprestimo();
			}
			else if(it.getEmprestimo()!=null && it.getEmprestimo().getIDRequisicao().equals(idRequisicaoEmprestimo) && it.getEmprestimo().emprestimoFoiAprovado()){
				throw new Exception("Empréstimo já aprovado");
			}	
		}
		
		if (!requisicaoExiste){
			throw new Exception("Requisição de empréstimo inexistente");
		}
		//throw new Exception("Requisição de empréstimo inexistente");
		throw new Exception("O empréstimo só pode ser aprovado pelo dono do item");
	}
	
	private boolean stringValida(String string) {
		if (string == null || string.isEmpty()) {
			return false;
		}
		return true;
	}
	
	public Item buscarItemPorID(String id) {
		for (Item it : getListaMeusItens()) {
			if (it.getID().equals(id)) {
				return it;
			}
		}
		return null;
	}
	
	public Item buscarItemIdEmprestimo(String idEmpretimo){
		
		for (Item it : getListaMeusItens()){
			if (it.getEmprestimo() != null){
				if (it.getEmprestimo().getIDEmprestimo().equals(idEmpretimo)){
					return it;
				}
			}
			
		}
		
		return null;
		
	}
	
	
	
	public void confirmarTerminoEmprestimo(String idEmprestimo){
		
	}
	
	public String confirmarTerminoEmprestimo(Usuario usr, Item item){
		String retorno = "";
		
		for (Item it: listaMeusItens){
			if (!it.equals(item)){
				retorno += usr.getLogin()+ "-" + it.getEmprestimo().getBeneficiado().getLogin() + ":" + it.getNome() + ":Andamento";
			} else {
				
				retorno += usr.getLogin()+ "-" + it.getEmprestimo().getBeneficiado().getLogin() + ":" + it.getNome() + ":Completado";
			}
		}
		return retorno;
		
	}
	
	public void incrementarDias(int dias){
		for (Item it: listaMeusItens){
			if (it.getEmprestimo()!=null){
				it.getEmprestimo().adicionarDias(dias);
			}
		}
	}
	
	public String buscarItemCadastrado(String chave, String atributo, String tipoOrdenacao, String criterioOrdenacao){
		String resp = "";
		
		for (Item item : this.getListaMeusItens()){
			if (!item.pesquisa(chave, atributo).equals("")){
				resp += item.pesquisa(chave, atributo);
			}
		}
		
		return resp;
	}
	
	public void apagarItem(Item item){
//		if (this.getListaMeusItens().contains(item) && !itensPraEmprestar.contains(item)){
//			//TODO lancar erro pois nao pode excluir item se ele ta emprstado
//			
//		} else {
			this.getListaMeusItens().remove(item);
//		}
	}
	
	public void addEmprestimoCompletado(Emprestimo emp){
		this.listaDeEmprestimosCompletados.add(emp);
	}

}
