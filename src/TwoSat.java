import java.util.ArrayList;
import java.util.List;

public class TwoSat {
    public static boolean checkConsistency(int[] composantes) {
        int n = composantes.length / 2;  // Le nombre de variables (chaque variable a deux littéraux)

        for (int i = 0; i < n; i++) {
            // Vérifie si une variable et sa négation sont dans la même composante fortement connexe
            if (composantes[i] == composantes[i + n]) {
                System.out.println(get_Compo(composantes, composantes[i]));

                return false; // Inconsistante, donc la formule est insatisfiable
            }
        }
        return true; // Consistante, donc la formule est satisfiable
    }

    public static ArrayList<Integer> get_Compo (int[] composantes,int id) {
        ArrayList<Integer> liste = new ArrayList();
        int n = composantes.length;

        for (int i = 0; i < composantes.length; i++) {
            if (composantes[i] == id)  {
                if (i>=(n/2)){
                    liste.add(-(i-(n/2)+1));
                }else {
                    liste.add(i + 1);
                }
            }
        }
        return liste;
    }
}
