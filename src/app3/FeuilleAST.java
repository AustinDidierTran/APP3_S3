package app3;

/** @author Ahmed Khoumsi */

/** Classe representant une feuille d'AST
 */
public class FeuilleAST extends ElemAST {

  // Attribut(s)
String Nombre;

/**Constructeur pour l'initialisation d'attribut(s)
 */
  public FeuilleAST(String i) {  // avec arguments
    //
	  Nombre = i;
  }


  /** Evaluation de feuille d'AST
   */
  public int EvalAST( ) {
    //
	  return Integer.parseInt(Nombre);
  }


 /** Lecture de chaine de caracteres correspondant a la feuille d'AST
  */
  public String LectAST( ) {
    //
	  
	  return Nombre;
  }

}
