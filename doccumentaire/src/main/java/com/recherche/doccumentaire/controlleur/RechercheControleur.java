package com.recherche.doccumentaire.controlleur;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recherche")
public class RechercheControleur {

	@RequestMapping
	public String getRechercher()
	{
		return "recherche";
	}
	
}
