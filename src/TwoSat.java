public class TwoSat {
    public static boolean checkConsistency(int[] composantes) {
        int n = composantes.length / 2;  // Le nombre de variables (chaque variable a deux littéraux)

        for (int i = 0; i < n; i++) {
            // Vérifie si une variable et sa négation sont dans la même composante fortement connexe
            if (composantes[i] == composantes[i + n]) {
                return false; // Inconsistante, donc la formule est insatisfiable
            }
        }
        return true; // Consistante, donc la formule est satisfiable
    }
}
