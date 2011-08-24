package Classes;

/**
 * Esta Classe representa um endereco residencial que eh representado por um estado, cidade, rua, 
 * bairro, numero e cep
 * @author ARTHUR SENA, IGOR GOMES, RODOLFO DE LIMA, RENAN PINTO
 * @version 1.0
 */

public class Endereco {
   
    private String estado;
    private String cidade;
    private String rua;
    private String bairro;
    private String numero;
    private String cep;
   
    /**
     * Inicia os Atributos da Classe
     * @param estado Estado Federativo do Brasil
     * @param cidade Cidade do respectivo Estado
     * @param rua Rua da respectiva Cidade
     * @param bairro Bairro da respectiva Cidade
     * @param numero numero da Casa
     * @throws Exception Caso algum dos parametros apresente caracteres invalidos
     */
   
    public Endereco(String estado, String cidade, String rua, String bairro, String numero, String cep) throws Exception{
    	if (!stringValida(estado)){
    		 throw new Exception("Dado(s) Invalido(s)");
    	}
    	
    	else if(!stringValida(cidade)){
    		 throw new Exception("Dado(s) Invalido(s)");
    	}
    	
    	else if(!stringValida(rua)){
   		 throw new Exception("Dado(s) Invalido(s)");
    	}
    	
    	else if(!stringValida(bairro)){
   		 throw new Exception("Dado(s) Invalido(s)");
    	}
    	
    	else if(!stringValida(numero)){
   		 throw new Exception("Dado(s) Invalido(s)");
    	}
    	
    	else if(!stringValida(cep)){
   		 throw new Exception("Dado(s) Invalido(s)");
    	}
    	
    	else if(!cepValido(cep)){
   		 throw new Exception("Dado(s) Invalido(s)");
    	}
    	
    	this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.numero = numero;
        this.rua = rua;
        this.cep = cep;
    }
   
    /**
     * Recupera o Estado
     * @return Recupera o Estado
     */
   
    public String getEstado(){
        return estado;
    }
   
    /**
     * Recupera a Cidade
     * @return Recupera a Cidade
     */
    public String getCidade(){
        return cidade;
    }
   
    /**
     * Recupera a Rua
     * @return Recupera a Rua
     */
   
    public String getRua(){
        return rua;
    }
   
    /**
     * Recupera o Bairro
     * @return Recupera o Bairro
     */
   
    public String getBairro(){
        return bairro;
    }
   
    /**
     * Recupera o Numero
     * @return Recupera o Numero
     */
   
    public String numero(){
        return numero;
    }
   
    /**
     * Recupera o Cep
     * @return Recupera o Cep
     */
   
    public String getCep(){
        return cep;
    }
   
    /**
     * Altera o Estado do funcionario
     * @param novoEstado Novo Estado do Endereco
     * @throws Exception
     */
   
    public void setEstado(String novoEstado)throws Exception{
        this.estado = novoEstado;
    }
   
    /**
     * Altera a Cidade do funcionario
     * @param novaCidade
     */
   
    public void setCidade(String novaCidade){
        this.cidade = novaCidade;
    }
   
    /**
     * Altera a rua do funcionario
     * @param novaRua
     */
   
    public void setRua(String novaRua){
        this.rua = novaRua;
    }
   
    /**
     * Altera o Bairro do funcionario
     * @param novoBairro
     */
   
    public void setBairro(String novoBairro){
        this.bairro = novoBairro;
    }
   
    /**
     * Altera o numero do funcionario
     * @param novoNumero
     */
   
    public void setNumero(String novoNumero){
        this.numero = novoNumero;
    }
   
    /**
     * Altera o CEP do funcionario
     * @param novoCEP
     */
   
    public void setCEP(String novoCEP)throws Exception{
        this.cep = novoCEP;
    }
   
    /**
     * Retorna uma string que representa os atributos da classe
     */
   
    public String toString(){
        return "Estado: "+ estado + "Cidade: " + cidade + "Bairro: " + bairro + "Rua: " + rua + "Numero: " + numero + "CEP: " + cep;
     }
   
    /**
     * Verifica se dois enderecos sao iguais
     */
   
    public boolean equals(Object obj){
        if (!(obj instanceof Endereco)){
            return false;
        }
       
        Endereco endereco = (Endereco) obj;
       
        if (endereco.getBairro().equals(bairro) && endereco.getCep().equals(cep) && endereco.getCidade().equals(cidade) && endereco.getEstado().equals(estado)
                && endereco.getRua().equals(rua)){
            return true;
        }
        else{
            return false;
        }
       
    }
   
    private boolean stringValida(String string){
        if (string==null || string.isEmpty()){
            return false;
        }
        return true;
    }
    
    private boolean cepValido(String cep){
    	 try{
             Integer.parseInt(cep);
             return true;
         }
        
         catch(NumberFormatException e){
             return false;
         }
    }
    
}