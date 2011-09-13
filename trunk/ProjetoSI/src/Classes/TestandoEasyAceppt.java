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
		 files.add("US04.txt");
		 files.add("US05.txt");
		 files.add("US06.txt");
         files.add("US07.txt");
         files.add("US08.txt");
         files.add("US09.txt");
         RedeSocial rede  = new RedeSocial();
         
         EasyAcceptFacade eaFacade = new EasyAcceptFacade(rede, files);
        // EasyAcceptFacade eaFacade1 = new EasyAcceptFacade(facade, files)

         eaFacade.executeTests();

         System.out.println(eaFacade.getCompleteResults());


		
	}

}
