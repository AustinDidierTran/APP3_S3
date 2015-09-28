package app3;

/** @author Ahmed Khoumsi */

/** Classe representant une feuille d'AST
 */
public class NoeudAST extends ElemAST {

  // Attributs

char Operateur;
	
  /** Constructeur pour l'initialisation d'attributs
   */
  public NoeudAST(char op) { // avec arguments
    //
	  Operateur = op;
  }

 
  /** Evaluation de noeud d'AST
   */
  public int EvalAST( ) {
	  if (Operateur == '+')
	  {
		  return gauche.EvalAST() + droite.EvalAST();
	  }
	  else if (Operateur == '-')
	  {
		  return gauche.EvalAST() - droite.EvalAST();
	  }
	  else if (Operateur == '*')
	  {
		  return gauche.EvalAST() * droite.EvalAST();
	  }
	  else if (Operateur == '/')
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
	  return Character.toString(Operateur);
  }

}


