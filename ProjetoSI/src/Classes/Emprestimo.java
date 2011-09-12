package Classes;

import java.util.Random;


public class Emprestimo {
	
	private Usuario beneficiado;
	private Usuario Emprestador;
	
	private int duracao;
	
	private String idRequisicao;
	private String idEmprestimo;
	
	private boolean requisicaoEmprestimo;
	private boolean emprestimoAprovado;
	private boolean devolucao;
	private boolean devolvido;
	private boolean requisitarDevolucao;
	
	
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
		devolucao = false;
		devolvido = false;
		requisitarDevolucao = false;
		
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
	
	public boolean isDevolucao() {
		return devolucao;
	}

	public void setDevolucao(boolean devolucao) {
		this.devolucao = devolucao;
	}

	public boolean isDevolvido() {
		return devolvido;
	}

	public void setDevolvido(boolean devolvido) {
		this.devolvido = devolvido;
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
		//idRequisicao = null;
		return idEmprestimo;
	}

	public void requisitarDevolucao() {
		this.requisitarDevolucao = true;
	}

	public boolean isRequisitarDevolucao() {
		return requisitarDevolucao;
	}
	
	

}
