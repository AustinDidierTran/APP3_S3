package app3;

/** @author Ahmed Khoumsi */

/** Classe representant une feuille d'AST
 */
public class NoeudAST extends ElemAST {

  // Attributs
	Terminal terminal;
	ElemAST gauche;
	ElemAST droite;
	
  /** Constructeur pour l'initialisation d'attributs
   */
  public NoeudAST(Terminal t) { // avec arguments
    //
	  terminal = t;
  }

  public NoeudAST(char op) {
	  terminal.chaine = "" + op;
  }
  
  public NoeudAST(Terminal t, ElemAST a, ElemAST b){
	  terminal = t;
	  gauche = a;
	  droite = b;
  }
  
  /** Evaluation de noeud d'AST
   */
  public int EvalAST( ) {
	  if (terminal.chaine.equals("+"))
	  {
		  return gauche.EvalAST() + droite.EvalAST();
	  }
	  else if (terminal.chaine.equals("-"))
	  {
		  return gauche.EvalAST() - droite.EvalAST();
	  }
	  else if (terminal.chaine.equals("*"))
	  {
		  return gauche.EvalAST() * droite.EvalAST();
	  }
	  else if (terminal.chaine.equals("/"))
	  {
		  try
		  {
			  return gauche.EvalAST() / droite.EvalAST();
		  }
		  catch (Exception e)
		  {
			  ErreurEvalAST("Cannot divide by 0 !");
		  }
	  }
	  return 0;
  }

  /** Lecture de noeud d'AST
   */
  public String LectAST( ) {
     //
	  return gauche.LectAST() + " " + terminal.chaine + " " + droite.LectAST();
  }

}


