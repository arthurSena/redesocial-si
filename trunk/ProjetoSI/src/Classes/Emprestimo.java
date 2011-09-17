package Classes;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;


public class Emprestimo {
	
	private Usuario beneficiado;
	private Usuario Emprestador;
	
	private int duracao;
	private String dataDevolucao;
	
	private String idRequisicao;
	private String idEmprestimo;
	
	private boolean requisicaoEmprestimo;
	private boolean emprestimoAprovado;
	private boolean devolucao;
	private boolean devolvido;
	private boolean requisitarDevolucao;
	private List<Usuario> listaDeUsuariosInteressados;
	private String dataDeDevolucao;
	private GregorianCalendar calendario;
	
	public Emprestimo(Usuario beneficiado, int duracao)throws Exception{
		if (beneficiado==null){
			throw new Exception("Beneficiado nao pode ser igual a null");
		}
		else if(duracao<=0){
			throw new Exception("Duracao inválida");
		}
		
		this.listaDeUsuariosInteressados = new ArrayList<Usuario>();
		this.beneficiado = beneficiado;
		this.duracao = duracao;
		requisicaoEmprestimo  = true;
		emprestimoAprovado = false;
		devolucao = false;
		devolvido = false;
		requisitarDevolucao = false;
		dataDevolucao = null;
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
		GregorianCalendar calendario = new GregorianCalendar();
		calendario.add(Calendar.DATE, this.duracao);
		this.dataDeDevolucao = DateFormat.getDateInstance().format(calendario.getTime());
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
		calendario = new  GregorianCalendar();
		this.requisitarDevolucao = true;
	}

	public boolean isRequisitarDevolucao() {
		
		return requisitarDevolucao;
	}
	
	public List<Usuario> getListaDeUsuariosInteressados() {
		return listaDeUsuariosInteressados;
	}

	public void registrarInteresse (Usuario usuario){
		if (!getListaDeUsuariosInteressados().contains(usuario)){
			this.listaDeUsuariosInteressados.add(usuario);
		}
	}
	
	public boolean tempoEmprestimoExpiro(){
		boolean retorno = false;
		String[] data1 = this.dataDeDevolucao.split("/");
		String[] dataHJ = DateFormat.getDateInstance().format(this.calendario.getTime()).split("/");
		if (data1[2].equals(dataHJ[2])){
			if (data1[1].equals(dataHJ[1])){
				if (Integer.parseInt(data1[0])>=(Integer.parseInt(dataHJ[0]))){
					retorno = false;
				}
				else{
					retorno = true;
				}
			}
		}
		else if (data1[2].equals(dataHJ[2])){
			
			if (Integer.parseInt(data1[1])>=(Integer.parseInt(dataHJ[1]))){
				retorno = false;
			}
			else{
				retorno = true;
			}
		}
		else if(Integer.parseInt(data1[2])>=(Integer.parseInt(dataHJ[2]))){
			retorno =false;
		}
		else{
			retorno = true;
		}
		return retorno;
	}
	
	public void adicionarDias(int dias){
		if (calendario==null){
			calendario = new GregorianCalendar();			
		}
		calendario.add(Calendar.DATE, dias);
	}
}

