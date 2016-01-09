package com.recherche.doccumentaire.controlleur;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.recherche.doccumentaire.metier.Doccuments;
import com.recherche.doccumentaire.metier.Indexeur;

@Controller
@RequestMapping("/resultats")
public class ResultatsControleur {
	
	private List<Doccuments> listeDocuments;
	private Indexeur indexeur;
	
	@RequestMapping(method=RequestMethod.GET)
	public String getResultats(ModelMap model, @RequestParam("requette") String requette)
	{
		String[] tabRequette = requette.split(" ");
		
			
		listeDocuments=new ArrayList<Doccuments>();
		indexeur=new Indexeur();
		if(requette!=null)
		{
			indexeur.searchAndDisplay(requette, listeDocuments);
			//decoupage des termes de la requette si possible
			List<String> termesRequettes = new ArrayList<String>();
			if(tabRequette.length!=0)
			{
				for(int i=0; i<tabRequette.length; i++)
				{
					termesRequettes.add(tabRequette[i]);
				}
				
				model.addAttribute("termesRequettes",termesRequettes);
			}
			model.addAttribute("requette",requette);
			model.addAttribute("listeDocuments",listeDocuments);
			model.addAttribute("tailleListe",listeDocuments.size());
			model.addAttribute("temps",indexeur.getTemps());
			model.addAttribute("nbDocuments",indexeur.getNbDoccuments());
			
			if(requette.contains(" "))
				model.addAttribute("compose",1);
			if(!requette.contains(" "))			
				model.addAttribute("compose",0);
			if(requette.contains("*"))
			{
				model.addAttribute("compose",2);
				model.addAttribute("Prefix",requette.substring(0,requette.indexOf("*")));
			}
			
		}		
		
		return "resultats";
	}

}
