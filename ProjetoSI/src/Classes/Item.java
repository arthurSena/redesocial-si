package Classes;



/**
 * Esta Classe representa um item que podera ser emprestado pelo Usuario da Rede Social
 * @author ARTHUR SENA, RODOLFO DE LIMA, RENAN PINTO, IGOR GOMES
 * @version 1.0 
 */


public class Item {
	
	String nome;
	String descricao;
    String categoria;
    Usuario donoDoItem;
    
    /**
     * 
     * @param nome 
     *            Nome do Item
     * @param descricao
     *            Descricao do Item
     * @param categoria
     *            Categoria do Item
     * @throws Exception 
     *            Retorna Excecao caso algum dos parametros seja invalido 
     */
    
    public Item(String nome, String descricao, String categoria) throws Exception{
    	if (!stringValida(nome)){
			throw new Exception("Nome Invalido");
		}
		else if (!stringValida(descricao)){
			throw new Exception("Descricao Invalida");
		}
		else if (!stringValida(categoria) || !categoriaValida(categoria)){
			throw new Exception("Categoria Invalida");
		}
    	
    	this.nome = nome;
    	this.descricao = descricao;
    	this.categoria = categoria;
    }
    
   /**
    * Recupera o Nome do Item
    * @return Nome do Item
    */
    
    public String getNome() {
		return nome;
	}

    /**
     * Altera o Nome do Item
     * 
     * @param nome 
     *           Novo Nome do item
     * @throws Exception
     *           Caso o Nome seja vazio ou igual a Null
     */
    
	public void setNome(String nome)throws Exception {
	    if (!stringValida(nome)){
			throw new Exception("Nome Invalido");
		}
		this.nome = nome;
	}

	/**
	 * Recupera a Descricao do Item
	 * @return Retorna a Descricao do Item
	 */
	
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Altera a Descricao do Item
	 * @param descricao 
	 *             Nova Descricao do Item,
	 * @throws Exception
	 *             Caso a Descrica seja igual a null ou esteja Vazia
	 */
	
	public void setDescricao(String descricao)throws Exception{
		if (!stringValida(descricao)){
			throw new Exception("Descricao Invalida");
		}
		this.descricao = descricao;
	}

	/**
	 * Recupera a Categoria do Item
	 * @return Categoria do Item
	 */
	
	public String getCategoria() {
		return categoria;
	}

	/**
	 * Altera a Categoria do Item 
	 * @param categoria
	 *              Nova Categoria do Item
	 * @throws Exception
	 *              Caso o Parametro seja Invalido
	 */
	
	public void setCategoria(String categoria)throws Exception {
		if (!stringValida(categoria)){
			throw new Exception("Categoria nao pode ser vazia ou igual a null");
		}
		else if(!categoriaValida(categoria)){
			throw new Exception("Categoria Invalida");
		}
		this.categoria = categoria;
	}
	
	/**
	 * Analisa se dois itens sao ou nao iguais
	 * @param it 
	 *         Item a ser comparado
	 * @return
	 *         True, Caso os itens sejam iguais
	 *         False, Caso Contrario
	 */
	
	public boolean equals(Item it){
		boolean resp = false;
		
		if (!(it instanceof Item)){
            resp = false;
        }
		
		else if (it.getCategoria().equals(this.categoria) && it.getDescricao().equals(this.descricao) 
				&& it.getNome().equals(this.nome)){
			resp = true;
		}
		
		return resp;
	}
	
	/**
	 * Recupera o Usuario dono do Item
	 * 
	 * @return
	 *        Usuario dono do Item
	 */
	
	public Usuario getDonoItem(){
		return donoDoItem;
	}
	
	/**
	 * Altera o Usuario Dono do Item
	 * @param usr
	 *           Usuario Dono do Item
	 * @throws Exception
	 *           Caso o Parametro esteja igual a Null
	 */
	
	public void setDonoItem(Usuario usr)throws Exception{
		if (usr==null){
			throw new Exception("Usuario nao pode ser igual a Null");
		}
		this.donoDoItem = usr;	
	}

	/**
 	* Retorna uma String que representa a Classe
 	*/
	
	@Override
	public String toString() {
		return "Item [nome=" + nome + ", descricao=" + descricao
				+ ", categoria=" + categoria + "]";
	}

	private boolean stringValida(String string){
        if (string==null || string.isEmpty()){
            return false;
        }
        return true;
    }
    
    private boolean categoriaValida(String categoria){
    	boolean resp = false;
    	if (categoria.toLowerCase().equals("livro") || categoria.toLowerCase().equals("filme") || categoria.toLowerCase().equals("jogo")){
    		resp = true;
    	}
    	return resp;
    }

}
