package Testes;

import org.junit.Before;

import Classes.Item;

public class TesteItem {
	
	Item item; 
	
	@Before public void construtor() throws Exception{
		item = new Item("Item 1", "Filme de aventura", "filme");
	}
	
	

}
