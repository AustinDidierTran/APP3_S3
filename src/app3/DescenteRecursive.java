package app3;

/** @author Ahmed Khoumsi */

/** Cette classe effectue l'analyse syntaxique
 */
public class DescenteRecursive {

  // Attributs

	String input;
	AnalLex anal;
	Terminal currentTerminal;
	
/** Constructeur de DescenteRecursive :
      - recoit en argument le nom du fichier contenant l'expression a analyser
      - pour l'initalisation d'attribut(s)
 */
	
public DescenteRecursive(String in) {
    Reader r = new Reader(in);
	anal = new AnalLex(r.toString());
	currentTerminal = anal.prochainTerminal();
}


/** AnalSynt() effectue l'analyse syntaxique et construit l'AST.
 *    Elle retourne une reference sur la racine de l'AST construit
 */
public ElemAST AnalSynt() {
	
	ElemAST n = Exp();
	
	return n;
	
}

public ElemAST Exp(){
	
	ElemAST node1 = U();
	
	if(currentTerminal.equals('+') || currentTerminal.equals('-')){
		
		Terminal oldTerminal = currentTerminal;
		currentTerminal = anal.prochainTerminal();
		
		ElemAST node2 = Exp();
		return new NoeudAST(oldTerminal, node1, node2);
	}
	
	return node1;
	
}

public ElemAST U(){
	
	ElemAST node1 = V();
	
	
	
	return node1;
}
public ElemAST V(){
	ElemAST e = new NoeudAST('e');
	return e;
}

public boolean verifierSynt(String input) {
	
	boolean bOperande = true;			//Tells if next input is an operande or not
	int nbParentheses = 0;
	
	for(String s: input.split("\n")){
		
		System.out.println(s);
		
		if(s.equals("(")){
			nbParentheses++;
		}
		else if(s.equals(")")){
			nbParentheses--;
			if(nbParentheses < 0){
				return false;
			}
		}
		else if(isAnOperande(s)){
			
			if(!bOperande){
				return false;
			}
			bOperande = false;
		}
		else if(isAnOperateur(s)){
			
			if(bOperande){
				return false;
			}
			bOperande = true;	
		}
	}
	
	if(nbParentheses != 0){
		return false;
	}
	if(bOperande == true){
		return false;
	}
	return true;
}

public boolean isAnOperande(String s){

	return s.charAt(0) >= 0x41 && s.charAt(0) <= 0x5A || s.charAt(0) >= 0x30 && s.charAt(0) <= 0x39;
}

public boolean isAnOperateur(String s){
	
	return s.charAt(0) == '+' || s.charAt(0) == '-' || s.charAt(0) == '*' || s.charAt(0) == '/';
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

