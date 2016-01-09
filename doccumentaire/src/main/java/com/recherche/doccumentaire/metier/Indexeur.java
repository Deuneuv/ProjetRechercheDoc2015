package com.recherche.doccumentaire.metier;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Indexeur {

	private static String indexFolderName;	
	private IndexWriter index;
	private boolean flagIndex;
	private  List<Document> listeDocuments; 
	private long temps;
	private int nbDoccuments;

	public int getNbDoccuments() {
		return nbDoccuments;
	}

	public void setNbDoccuments(int nbDoccuments) {
		this.nbDoccuments = nbDoccuments;
	}

	public float getTemps() {
		return temps;
	}

	public void setTemps(long temps) {
		this.temps = temps;
	}

	/**
	 * constructeur
	 */
	public Indexeur()
	{
		listeDocuments= new ArrayList<Document>();	
		String filename= System.getProperty("catalina.home");
		indexFolderName=filename+"/Index_DIR";
	}

	//=============gettesrs et setters===============//

	public static String getIndexFolderName() {
		return indexFolderName;
	}

	public static void setIndexFolderName(String indexFolderName) {
		Indexeur.indexFolderName = indexFolderName;
	}

	public boolean isFlagIndex() {
		return flagIndex;
	}

	public void setFlagIndex(boolean flagIndex) {
		this.flagIndex = flagIndex;
	}

	public IndexWriter getIndex() {
		return index;
	}


	public void setIndex(IndexWriter index) {
		this.index = index;
	}
	//=============================================//

	/**
	 * methode de création de Doccument=======================================================
	 * @param id==============================================================================
	 * @param titre===========================================================================
	 * @param texte===========================================================================
	 * @return================================================================================
	 */
	public Document createDocument (String id, String titre, String texte) {

		// Créer un document vide
		Document doc = new Document();

		// Créer le champ id
		doc.add( new Field ("id", id, Field.Store.YES, Field.Index.UN_TOKENIZED));

		// Créer le champ titre
		doc.add(new Field ("titre", titre, Field.Store.YES, Field.Index.TOKENIZED));

		// Créer le champ texte
		doc.add(new Field ("texte", texte, Field.Store.YES, Field.Index.TOKENIZED));

		return doc;
	}

	/**
	 * =======================methode de création de l'index =================================
	 * @return================================================================================
	 * =======================================================================================
	 */
	public boolean Createindex () {

		File dir = new File (indexFolderName);	
		if (dir.exists()) {
			System.out.println("Impossible de créer un index dans le répertoire 'INDEX_DIR' , " +
					"veuillez le supprimer d'abord.");
			return false;
		}	

		try {
			// Création de l'index 
			index = new IndexWriter(indexFolderName, new StandardAnalyzer(), true);
			if (!listeDocuments.isEmpty())
			{
				for(int i = 0; i<listeDocuments.size(); i++)
				{
					index.addDocument(listeDocuments.get(i));
				}
				index.close();
			}
			else 
			{
				System.out.println("L'index est vide créer l'index dabord! ");
			}        

		} catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}    
		return true;
	}

	/**
	 * =============================================================================================
	 * @param nomFic================================================================================
	 * @throws ParserConfigurationException=========================================================
	 * @throws SAXException=========================================================================
	 * @throws IOException==========================================================================
	 */
	public void parser(String nomFic) throws ParserConfigurationException, SAXException, IOException 
	{
		//========== prologue=======================================================//
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		final DocumentBuilder builder = factory.newDocumentBuilder();     
		final org.w3c.dom.Document document= builder.parse(new File(nomFic));  

		//========== element racine ==============================================//
		final Element racine = document.getDocumentElement();       
		final NodeList racineNoeuds = racine.getChildNodes();
		final int nbRacineNoeuds = racineNoeuds.getLength();

		String filename = System.getProperty("catalina.home");      
		File fic = new File(filename+"/Dictionnaire.txt");
		if(!fic.exists())
		{
			fic.createNewFile();

			FileWriter friter = new FileWriter(fic,true);

			for (int i = 0; i<nbRacineNoeuds; i++) {

				if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
					final Element Doccument = (Element) racineNoeuds.item(i);  

					//récupération du titre du document
					Element Docno = (Element) Doccument.getElementsByTagName("DOCNO").item(0);
					String nomDoccument = Docno.getTextContent();

					friter.write("==================="+nomDoccument+"==================="); 
					
					//récupération du texte du document
					Element texte = (Element) Doccument.getElementsByTagName("TEXT").item(0);  
					String texte1 = texte.getTextContent();
					friter.write(texte1);
					//création de l'objet document et ajout dans la liste des documents
					org.apache.lucene.document.Document doc = createDocument(String.valueOf(i), nomDoccument, texte1);
					if(!listeDocuments.contains(doc))
						listeDocuments.add(doc);

					friter.flush();	
				}           

			} 
			friter.close();
		}
	}


	/**
	 * Methode parser tout pour parser tous les fichiers==============================================
	 * @param nomFic==================================================================================
	 * @throws ParserConfigurationException===========================================================
	 * @throws SAXException===========================================================================
	 * @throws IOException============================================================================
	 */
	public void parserTout(String nomFic) throws ParserConfigurationException, SAXException, IOException
	{
		
		File repertoire = new File(nomFic);		
		String[] listeFichiers = repertoire.list();		
		for(int i=0; i<listeFichiers.length; i++)
		{
			this.parser(nomFic+"/"+listeFichiers[i]);
		}
		
	}


	/**
	 * méthode recherche d'un mot par pertinence=============================================
	 * @param criteria=======================================================================
	 * @return===============================================================================
	 */
	public boolean searchAndDisplay (String criteria, List<Doccuments> listeDoc) {

		long tempsprecedent = System.currentTimeMillis();
		try {
			IndexSearcher searcher = new IndexSearcher(indexFolderName);
			QueryParser parser = new QueryParser("texte", new StandardAnalyzer());
			Query query = parser.parse(criteria);			

			//récupération par ordre décroissant des documents avec leur score	
			TopDocs topDocs = searcher.search(query,null, 20);	
			nbDoccuments=topDocs.totalHits;
			ScoreDoc[] scoreDocArray = topDocs.scoreDocs;	
			for(ScoreDoc scoredoc: scoreDocArray){
								
				Document doc = searcher.doc(scoredoc.doc);
				Doccuments doccument = new Doccuments();
				doccument.setScore(scoredoc.score);
				doccument.setTitre(doc.get("titre"));
				String tmptexte=doc.get("texte").replaceAll("\n", " ");
				doccument.setTexte(tmptexte);			 
				String[] tabString = tmptexte.split(" ");
				List<String> tmpList=Arrays.asList(tabString);
				doccument.setListeString(tmpList);
				listeDoc.add(doccument);

			}

		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
		long tempsActuel = System.currentTimeMillis();
		temps = tempsActuel-tempsprecedent; 

		return true;

	}

}