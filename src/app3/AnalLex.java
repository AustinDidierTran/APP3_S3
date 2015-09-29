package app3;

/** @author Ahmed Khoumsi */

/** Cette classe effectue l'analyse lexicale
 */
public class AnalLex {

	int iCptChar;
	boolean boolIsLastCharUnderscore;
	String strEquation;
	int iIdErreur;
	
// Attributs
//  ...

	
/** Constructeur pour l'initialisation d'attribut(s)
 */
  public AnalLex( ) 
  {  // arguments possibles
    //
  }

  public AnalLex(String s ) 
  {
	  strEquation = s;
  }

/** resteTerminal() retourne :
      false  si tous les terminaux de l'expression arithmetique ont ete retournes
      true s'il reste encore au moins un terminal qui n'a pas ete retourne 
 */
  public boolean resteTerminal( ) 
  {
	  if (strEquation.length() <= iCptChar)
		  return false;
	  else
		  return true;
  }
  
  
/** prochainTerminal() retourne le prochain terminal
      Cette methode est une implementation d'un AEF
 */  
  
  public Terminal prochainTerminal( ) 
  {
     //
	 String strTerminal= "";
	 boolIsLastCharUnderscore = false;
	  
	 //Lettre majuscule
	 if(strEquation.charAt(iCptChar) >= 0x41 && strEquation.charAt(iCptChar) <= 0x5A)
	 {	
		 strTerminal +=  strEquation.charAt(iCptChar); 
	     boolean isLetter = true; 
		 do
		 {
			 iCptChar++;
			 if (!(resteTerminal()))
			 {
				  if (boolIsLastCharUnderscore){
					  String err = "";
					  err += "_";
					  iIdErreur = 3;
					  ErreurLex(err);
				  }
				  isLetter = false;  
			 }
			 //Lettre majuscule, minuscule et _
			 else if(strEquation.charAt(iCptChar) >= 0x41 && strEquation.charAt(iCptChar) <= 0x5A ||
					 strEquation.charAt(iCptChar) >= 0x61 && strEquation.charAt(iCptChar) <= 0x7A ||
					 strEquation.charAt(iCptChar) == '_')
			 {
				  if (strEquation.charAt(iCptChar) == '_')
				  {
					  if (boolIsLastCharUnderscore)
					  {
						  String err = "";
						  err += strEquation.charAt(iCptChar);	
						  iIdErreur = 2;
						  ErreurLex(err);
					  }
					  else
					  {
						  boolIsLastCharUnderscore = true;
					  }
				  }
				  else
				  {
					  boolIsLastCharUnderscore = false;
				  }
				  strTerminal +=  strEquation.charAt(iCptChar); 
			 }
			 else
				  isLetter = false;
		  
		 }while(isLetter);
	  }
	 //Chiffre (0-9)
	  else if(strEquation.charAt(iCptChar) >= 0x30 && strEquation.charAt(iCptChar) <= 0x39)
	  {
		 strTerminal +=  strEquation.charAt(iCptChar);
		 boolean isNumber = true;
		
		 do
		 {
			 iCptChar++;
			 if (!(resteTerminal()))
				 isNumber = false;  
			 else if(strEquation.charAt(iCptChar) >= 0x30 && strEquation.charAt(iCptChar) <= 0x39)
				 strTerminal +=  strEquation.charAt(iCptChar); 	
			 else
				 isNumber = false;	
		 }while(isNumber);
	 }
	 else if(strEquation.charAt(iCptChar) == '+' || strEquation.charAt(iCptChar) == '-' ||
			 strEquation.charAt(iCptChar) == '*' || strEquation.charAt(iCptChar) == '/' ||
			 strEquation.charAt(iCptChar) == '(' || strEquation.charAt(iCptChar) == ')')
	 {
		 strTerminal +=  strEquation.charAt(iCptChar); 
		 iCptChar++;
	 }
	 else
	 {
		//Caractère inconnu
		 String err = "";
		 err += strEquation.charAt(iCptChar);	
		 iIdErreur = 1;
		 ErreurLex(err);
		 iCptChar++;
	 }
	 
     Terminal t = new Terminal(strTerminal);	  
	 return t;
  }

 
/** ErreurLex() envoie un message d'erreur lexicale
 */ 
  public void ErreurLex(String s) 
  {	
	  if (!(s.isEmpty()))
	  {
		  if (iIdErreur == 1)
		  {
			  System.out.println("Erreur : Caractere inconnu ' " + s + " '" + "\n");
		  }
		  else if (iIdErreur == 2)
		  {
			  System.out.println("Erreur : Il y a deux _ de suite \n");
		  }
		  else if (iIdErreur == 3)
		  {
			  System.out.println("Erreur : Le dernier caractere est _ \n");
		  }
		  else
		  {
			  System.out.println("Erreur inconnue \n");
		  }
			  
		  System.exit(1);
	  }
  }

  
  public static void execute(String[] args){
	  String toWrite = "";
	    System.out.println("Debut d'analyse lexicale");
	    if (args.length == 0){
	    args = new String [2];
	            args[0] = "ExpArith.txt";
	            args[1] = "ResultatLexical.txt";
	    }
	    Reader r = new Reader(args[0]);
	    
	    AnalLex lexical = new AnalLex(r.toString()); // Creation de l'analyseur lexical

	    // Execution de l'analyseur lexical
	    Terminal t = null;
	    while(lexical.resteTerminal()){
	      t = lexical.prochainTerminal();
	      toWrite +=t.chaine + "\n" ;  // toWrite contient le resultat
	    }				   //    d'analyse lexicale
	    System.out.println(toWrite); 	// Ecriture de toWrite sur la console
	    Writer w = new Writer(args[1],toWrite); // Ecriture de toWrite dans fichier args[1]
	    System.out.println("Fin d'analyse lexicale");
  }
  
  //Methode principale a lancer pour tester l'analyseur lexical
  public static void main(String[] args) {
    execute(args);
  }
}