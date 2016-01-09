package com.recherche.doccumentaire.metier;

import java.util.ArrayList;
import java.util.List;

public class Doccuments {
	
	private float score;
	private String titre;
	private String texte;
	private List<String> listeString=new ArrayList<String>();
	
	public List<String> getListeString() {
		return listeString;
	}
	public void setListeString(List<String> listeString) {
		this.listeString = listeString;
	}
	//getters and setters
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getTexte() {
		return texte;
	}
	public void setTexte(String texte) {
		this.texte = texte;
	}

}
