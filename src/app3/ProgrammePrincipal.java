package app3;

public class ProgrammePrincipal {

	
	
	public static void main(String[] args){
	
		AnalLex anal = new AnalLex();
		DescenteRecursive dr = new DescenteRecursive("ResultatLexical.txt");
		anal.execute(args);
		dr.execute(args);
		
		System.out.println("Fin du programme principal");
	}
}
