package Testes;

import junit.framework.Assert;
import junit.framework.AssertionFailedError;

import org.junit.Before;
import org.junit.Test;

import Classes.GerenciadorAmizades;
import Classes.Usuario;

public class TesteGerenciadorDeAmizades {
	
	GerenciadorAmizades gerenciadorAmizades;
	Usuario usuario, usuario2, usuario3;
	
	@Before public void construtor() throws Exception{
		gerenciadorAmizades = new GerenciadorAmizades();
		
		usuario = new Usuario("Nome1", "login1", "endereco1");
		usuario2 = new Usuario("Nome2", "login2", "endereco2");
		usuario3 = new Usuario("Nome3", "login3", "endereco3");
	}
	
	@Test public void testaAdicionarAmigoException() throws Exception{
		gerenciadorAmizades.adicionarAmigo(usuario);
		
		try {
			gerenciadorAmizades.adicionarAmigo(usuario);
			Assert.fail();
		} catch (Exception e){
			Assert.assertEquals("Erro ao adicionar amigo novamente", "Os usuários já são amigos", e.getMessage());
		}
		
		try {
			gerenciadorAmizades.adicionarAmigo(null);
			Assert.fail();
		} catch (Exception e){
			Assert.assertEquals("Erro ao adicionar amigo null", "Usuario nao pode ser igual a null", e.getMessage());
		}
		
		try {
			gerenciadorAmizades.adicionarAmigo2(null);
			Assert.fail();
		} catch (Exception e){
			Assert.assertEquals("Erro ao adicionar null", "Usuario nao pode ser igual a null", e.getMessage());
		}
	}
	
	@Test public void testaAdicionarProvavelAmigo(){
		try {
			gerenciadorAmizades.adicionarProvavelAmigo(null);
			Assert.fail();
		} catch (Exception e){
			Assert.assertEquals("Erro ao adicionar provavelm amigo", "Usuario nao pode ser igual a null", e.getMessage());
		}
	}
	
	@Test public void testaEhMeuAmigo(){
		try {
			gerenciadorAmizades.ehMeuAmigo(null);
			Assert.fail();
		} catch (Exception e){
			Assert.assertEquals("Erro no eh amigo", "Usuario nao pode ser igual a Null", e.getMessage());
		}
	}

}
