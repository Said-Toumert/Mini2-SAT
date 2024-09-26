import java.util.LinkedList;
import java.util.Stack;

public class Kosaraju {

    public static LinkedList<Integer> parcours(Parser parser, String filename) {
        LinkedList<Integer> parcours = new LinkedList<>();


        Graph<String> graph = parser.parse(filename);
        int n = graph.order();
        boolean[] marque = new boolean[n];
        Stack<Integer> pile = new Stack<>();

        pile.push(0);

        while (!pile.isEmpty()) {
            int sommet = pile.pop();
            if (!marque[sommet]) {
                marque[sommet] = true;
                parcours.add(sommet);


                LinkedList<Integer> voisins = graph.getVoisins(sommet);
                for (int voisinId : voisins) {
                    if (!marque[voisinId]) {
                        pile.push(voisinId);
                    }
                }
            }
        }

        return parcours;
    }
}
