package com.recherche.doccumentaire.controlleur;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.xml.sax.SAXException;

import com.recherche.doccumentaire.metier.Indexeur;

@Controller
@RequestMapping("/index")
public class CreateIndexControleur {
	
	private Indexeur indexeur;
	
	@RequestMapping
	public String getIndex()
	{
		return "index";
	}
	
	
	@RequestMapping(method=RequestMethod.POST)
	public void createIndex(ModelMap model, HttpServletRequest request)
	{
		File fic = new File(System.getProperty("catalina.home")+"Dictionnaire.txt");
		indexeur = new Indexeur();
		try {				
				if(!fic.exists())
					indexeur.parserTout(System.getProperty("catalina.home")+"/fichiersdezippés");
			} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		
		if(indexeur.Createindex())		
			model.addAttribute("succes","Index créer avec succes");
		else
			model.addAttribute("erreur", "l'index existe deja");			
		
		
	}

}
