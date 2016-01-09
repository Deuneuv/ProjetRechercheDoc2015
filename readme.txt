============================= readme 1.0 ============================

l'application "documentaire" est une application web de recherche d'information
qui a été créer en JavaEE avec les outils Spring, Lucene, Maven ...
pour l'utiliser il faut avoir au moyen java JRE 7 installé sur son pc et apache-tomcat 7

===================== installation de java 7 ====================================================
sudo apt-get install openjdk-7-jre 
une fois installé on peut vérifier à partir de la console la version de java installé en
tapant la commande:
$ java -version


===================== installation de tomcat =====================================================
installation de apache-tomcat 7

1- se rendre sur la page de téléchargement de tomcat 7: https://tomcat.apache.org/download-70.cgi
et télécharger tomcat 7

2- une fois le téléchargement terminé, decompréssé l'archive dans son répertoire personnel

3- configurer les utilisateurs et les roles en editant le fichier apache-tomcat-7-x/conf/tomcat-users.xml

et décommenter les users et leurs roles

  <!-- <role rolename="tomcat"/>
  <role rolename="role1"/>
  <role rolename="manager-gui"/>
  <user username="tomcat" password="tomcat" roles="tomcat"/>
  <user username="both" password="tomcat" roles="tomcat,role1"/>
  <user username="role1" password="tomcat" roles="role1"/>
  <user username="tomcat" password="secret" roles="manager-gui"/> -->

4- tester le fonctionnement de tomcat 
en lançant le serveur à partir du terminal 
$ cd /home/rep_perso/apache-tomact-7.x/bin && ./startup.sh

puis ouvrir un navigateur et entrer l'url:  http://localhost:8080

======================== deploiement de l'application dans tomcat===============================
1- une fois la page la page d'acueil de tomcat affichée on peut cliquer sur le bouton manager app

2- puis entrez le nom d'utilisateur par défaut: tomcat, et le mot de passe par défaut: secret
si tout va bien arrive à la page manager-app: Gestionnaire d''applications WEB Tomcat

3- puis sur le champs fichier war à déployer sur le serveur, on clique sur parcourir et on 
sélectionne le fichier war à déployer et on clique sur déployer

deuxième méthode:
il suffit juste de décompresser le fichier war dans le dossier webapps de apache-tomcat-7.x

ensuite copier le dossier qui contient les fichiers à parser sur la racine de tomcat
puis être sur que le dossier porte le nom de "fichiersdezippés"


====================== lancement de l'application =============================================
les urls de l'application sont:

==== la page d'accueil===================
http://localhost:8080/doccumentaire/index 

avec cette page on peut créer l'index pour pouvoir effectuer une recherche 
cliqué sur le bouton OK du champs indexer

puis cliquer sur le lien rechercher pour effectuer une recherche
