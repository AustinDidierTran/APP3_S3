package app3;

/** @author Ahmed Khoumsi */

/** Classe representant une feuille d'AST
 */
public class FeuilleAST extends ElemAST {

  // Attribut(s)
	Terminal terminal;

	public FeuilleAST(){
		terminal = null;
	}
/**Constructeur pour l'initialisation d'attribut(s)
 */
  public FeuilleAST(String s) {  // avec arguments
    //
	  terminal = new Terminal();
	  terminal.chaine = s;
  }
  public FeuilleAST(Terminal t){
	  terminal = t;
  }

  /** Evaluation de feuille d'AST
   */
  public int EvalAST( ) {
    //
	  try
	  {
		  Integer.parseInt(terminal.chaine);
	  }
	  catch (Exception ex)
	  {
		  ErreurEvalAST(ex.toString());
	  }
	  return Integer.parseInt(terminal.chaine);
  }


 /** Lecture de chaine de caracteres correspondant a la feuille d'AST
  */
  public String LectAST( ) {
    //
	  
	  return terminal.chaine;
  }

}
