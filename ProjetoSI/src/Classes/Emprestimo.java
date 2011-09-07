package Classes;

import java.util.Random;


public class Emprestimo {
	
	Usuario beneficiado;
	Usuario Emprestador;
	
	int duracao;
	
	String idRequisicao;
	String idEmprestimo;
	
	boolean requisicaoEmprestimo;
	boolean emprestimoAprovado;
	
	
	public Emprestimo(Usuario beneficiado, int duracao)throws Exception{
		if (beneficiado==null){
			throw new Exception("Beneficiado nao pode ser igual a null");
		}
		else if(duracao<=0){
			throw new Exception("Duracao inválida");
		}
		this.beneficiado = beneficiado;
		this.duracao = duracao;
		requisicaoEmprestimo  = true;
		emprestimoAprovado = false;
		
	}
	
	public Usuario getBeneficiado(){
		return beneficiado;
	}
	
	public String getIDRequisicao(){
		return idRequisicao;
	}
	
	public String getIDEmprestimo(){
		return idEmprestimo;
	}
	
	public boolean emprestimoFoiAprovado(){
		return emprestimoAprovado;
	}
	
	public String aprovarEmprestimo()throws Exception{
		/*if (emprestimoAprovado){
			throw new Exception("Empréstimo já aprovado");
		}*/
		emprestimoAprovado = true;
		requisicaoEmprestimo = false;
		return gerarIDEmprestimo();
	}
	
	
	public String gerarIDRequisicao(){
		idRequisicao = beneficiado.getID() + "Requisicao" + (new Random()).nextInt(1000);
		return idRequisicao;
	}
	
	public String gerarIDEmprestimo(){
		idEmprestimo = beneficiado.getID() + "Emprestimo" + (new Random()).nextInt(1000);
		idRequisicao = null;
		return idEmprestimo;
	}

}
