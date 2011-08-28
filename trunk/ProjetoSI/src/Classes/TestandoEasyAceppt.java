package Classes;
import java.util.ArrayList;
import java.util.List;



import easyaccept.EasyAcceptFacade;

import Classes.RedeSocial;

public class TestandoEasyAceppt {
	
	public static void main(String[] args) {
		
		 List<String> files = new ArrayList<String>();

         files.add("US01.txt");
         files.add("US02.txt");
         files.add("US03.txt");
         
         RedeSocial rede  = new RedeSocial();

         EasyAcceptFacade eaFacade = new EasyAcceptFacade(rede, files);

         eaFacade.executeTests();

         System.out.println(eaFacade.getCompleteResults());


		
	}

}
