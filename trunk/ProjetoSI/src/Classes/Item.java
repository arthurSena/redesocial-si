package Classes;


public class Item {
	
	private String nome;
	private String descricao;
    private String categoria;
    private String idItem;
    private Emprestimo emprestimo;
    
    public Item(String nome, String descricao, String categoria) throws Exception{
    	if (!stringValida(nome)){
			throw new Exception("Nome inválido");
		}
		else if (!stringValida(descricao)){
			throw new Exception("Descricao inválida");
		}
		else if (!stringValida(categoria)){
			throw new Exception("Categoria inválida");
		}
		else if(!categoriaValida(categoria)){
			throw new Exception("Categoria inexistente");
		}
    	this.idItem = "";
    	this.nome = nome;
    	this.descricao = descricao;
    	this.categoria = categoria;
    }
    
    public String getNome(){
    	return nome;
    }
    
    public String getDescricao(){
    	return descricao;
    }
    
    public String getCategoria(){
    	return categoria;
    }
    
    public String getID(){
    	return idItem;
    }
    
    public String gerarID(int quantItens){
		idItem += "${item" +quantItens + "}";
		return idItem;
	}
    
    public String criarRequisicaoEmprestimo(Usuario beneficiado, int duracao) throws Exception{
    	emprestimo = new Emprestimo(beneficiado, duracao);
    	emprestimo.requisitarEmprestimo();
    	return emprestimo.gerarIDRequisicao();
    }
    
    public Emprestimo getEmprestimo(){
    	return emprestimo;
    }
    
    public boolean equals(Item it){
    	if (!(it instanceof Item)){
    		return false;
    	}
    	
    	return it.getID().equals(this.idItem);
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
    
    
    public String pesquisa(String chave, String atributo){
    	
    	if (atributo.equals("nome")){
    		String[] resp = this.getNome().split(" ");
    		
    		for ( String string : resp ){
    			if (string.equalsIgnoreCase(chave)){
    				return this.getNome();
    			}    			
    		}
    	} else if (atributo.equals("descricao")){
    		String[] resp = this.getDescricao().split(" ");
    		
    		for ( String string : resp){
    			if (string.equalsIgnoreCase(chave)){
    				return this.getNome();
    			}
    		}
    	} else if (atributo.equals("categoria")){
    		if (this.getCategoria().equalsIgnoreCase(chave)){
    			return this.getNome();
    		}
    	} else {
    		//TODO Lanca execao 
    	}
    	
    	
    	return "";
    }
}
