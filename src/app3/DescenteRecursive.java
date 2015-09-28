package app3;

/** @author Ahmed Khoumsi */

/** Cette classe effectue l'analyse syntaxique
 */
public class DescenteRecursive {

  // Attributs

/** Constructeur de DescenteRecursive :
      - recoit en argument le nom du fichier contenant l'expression a analyser
      - pour l'initalisation d'attribut(s)
 */
	
public DescenteRecursive(String in) {
    //
}


/** AnalSynt() effectue l'analyse syntaxique et construit l'AST.
 *    Elle retourne une reference sur la racine de l'AST construit
 */
public ElemAST AnalSynt(String in, int Etat) {
   //
	ElemAST t = null;
	int Curseur = in.length() -1;
	int ValeurPlusFaibleOperateur = 999999;
	int ValeurActuelle = 0;
	int PositionPlusFaibleOperateur = -1;
	while (Curseur >= 0)
	{
		if (in.charAt(Curseur) == ')')
		{
			ValeurActuelle++;
		}
		else if (in.charAt(Curseur) == '(')
		{
			ValeurActuelle--;
		}
		
		if (Etat == 0)
		{
			if ((in.charAt(Curseur) == '+' || 
				in.charAt(Curseur) == '-') && ValeurActuelle < ValeurPlusFaibleOperateur)
			{
				PositionPlusFaibleOperateur = Curseur;
			}
		}
		else if (Etat == 1)
		{
			if ((in.charAt(Curseur) == 'x' || 
					in.charAt(Curseur) == '/') && ValeurActuelle < ValeurPlusFaibleOperateur)
				{
					PositionPlusFaibleOperateur = Curseur;
				}
		}
		Curseur--;
	}
	if (PositionPlusFaibleOperateur >= 0)
	{
		t = new NoeudAST(in.charAt(PositionPlusFaibleOperateur));
		t.gauche = AnalSynt(in.substring(0, PositionPlusFaibleOperateur - 1), 0);
		t.droite = AnalSynt(in.substring(PositionPlusFaibleOperateur + 1, in.length() - 1), 0);
	}
	else if (Etat == 0) // Pas de +/-, on cherche un * ou /
	{
		t = AnalSynt(in, 1);
	}
	else // Pas d'opérateur, on a un chiffre qui est une feuille
	{
		t = new FeuilleAST(in);
	}
	
	return t;
}


// Methode pour chaque symbole non-terminal de la grammaire retenue
// ... 
// ...

/** ErreurSynt() envoie un message d'erreur syntaxique
 */
public void ErreurSynt(String s)
{
    //
}



  //Methode principale a lancer pour tester l'analyseur syntaxique 
  public static void main(String[] args) {
    String toWriteLect = "";
    String toWriteEval = "";

    System.out.println("Debut d'analyse syntaxique");
    if (args.length == 0){
      args = new String [2];
      args[0] = "ExpArith.txt";
      args[1] = "ResultatSyntaxique.txt";
    }
    DescenteRecursive dr = new DescenteRecursive(args[0]);
    try {
      ElemAST RacineAST = dr.AnalSynt();
      toWriteLect += "Lecture de l'AST trouve : " + RacineAST.LectAST() + "\n";
      System.out.println(toWriteLect);
      toWriteEval += "Evaluation de l'AST trouve : " + RacineAST.EvalAST() + "\n";
      System.out.println(toWriteEval);
      Writer w = new Writer(args[1],toWriteLect+toWriteEval); // Ecriture de toWrite 
                                                              // dans fichier args[1]
    } catch (Exception e) {
      System.out.println(e);
      e.printStackTrace();
      System.exit(51);
    }
    System.out.println("Analyse syntaxique terminee");
  }

}

